package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example8;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionAdapterListUpdateCallback;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.R;

public class Example8Fragment extends Fragment implements NameSection.ClickListener {

    private static final Random RANDOM = new Random();

    private SectionedRecyclerViewAdapter sectionedAdapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_ex8, container, false);

        sectionedAdapter = new SectionedRecyclerViewAdapter();

        final GridLayoutManager glm = new GridLayoutManager(getContext(), 2);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (sectionedAdapter.getSectionItemViewType(position) == SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED) {
                    return 1;
                }
                return 2;
            }
        });

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(glm);
        recyclerView.setAdapter(sectionedAdapter);

        addNewSectionToAdapter();

        addNewSectionToAdapter();

        view.findViewById(R.id.fabAdd).setOnClickListener(view1 -> addNewSectionToAdapter());

        return view;
    }

    private void addNewSectionToAdapter() {
        final String randomNumber = getRandomStringNumber();

        final NameSection section = new NameSection(getString(R.string.group_title, randomNumber),
                this);

        sectionedAdapter.addSection(section);

        final int sectionPos = sectionedAdapter.getAdapterForSection(section).getSectionPosition();

        sectionedAdapter.notifyItemInserted(sectionPos);

        recyclerView.smoothScrollToPosition(sectionPos);
    }

    @NonNull
    private String getRandomStringNumber() {
        return String.valueOf(RANDOM.nextInt(100000));
    }

    @Override
    public void onItemRootViewClicked(@NonNull final NameSection section, final int itemAdapterPosition) {
        if (itemAdapterPosition != RecyclerView.NO_POSITION) {
            int positionInSection = sectionedAdapter.getPositionInSection(itemAdapterPosition);

            section.remove(positionInSection);

            sectionedAdapter.getAdapterForSection(section).notifyItemRemoved(positionInSection);
        }
    }

    @Override
    public void onHeaderAddButtonClicked(@NonNull final NameSection section) {
        final int positionToInsertItemAt = 0;

        section.add(positionToInsertItemAt, createPerson());

        if (section.getState() == Section.State.LOADED) {
            sectionedAdapter.getAdapterForSection(section).notifyItemInserted(positionToInsertItemAt);
        }
    }

    @Override
    public void onHeaderClearButtonClicked(@NonNull final NameSection section) {
        int contentItemsTotal = section.getContentItemsTotal();

        section.clear();

        if (section.getState() == Section.State.LOADED) {
            sectionedAdapter.getAdapterForSection(section).notifyItemRangeRemoved(0, contentItemsTotal);
        }
    }

    @Override
    public void onHeaderShuffleButtonClicked(@NonNull final NameSection section) {
        final SectionAdapterListUpdateCallback adapterListUpdateCallback = new SectionAdapterListUpdateCallback(sectionedAdapter.getAdapterForSection(section));
        final List<Person> oldList = section.getList();
        final List<Person> newList = new ArrayList<>(oldList);
        Collections.shuffle(newList);

        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new PersonListDiffCallback(oldList, newList));

        section.clear();
        section.addAll(newList);

        diffResult.dispatchUpdatesTo(adapterListUpdateCallback);
    }

    @Override
    public void onHeaderRemoveButtonClicked(@NonNull final NameSection section) {
        final int sectionItemsTotal = section.getSectionItemsTotal();
        final int sectionPosition = sectionedAdapter.getAdapterForSection(section).getSectionPosition();

        sectionedAdapter.removeSection(section);

        if (section.getState() == Section.State.LOADED) {
            sectionedAdapter.notifyItemRangeRemoved(
                    sectionPosition,
                    sectionItemsTotal
            );
        }
    }

    @Override
    public void onHeaderLoadedButtonClicked(@NonNull final NameSection section) {
        final Section.State previousState = section.getState();
        if (previousState == Section.State.LOADED) return;

        section.setState(Section.State.LOADED);
        sectionedAdapter.getAdapterForSection(section).notifyStateChangedToLoaded(previousState);
    }

    @Override
    public void onHeaderLoadingButtonClicked(@NonNull final NameSection section) {
        final Section.State previousState = section.getState();
        if (previousState == Section.State.LOADING) return;

        final SectionAdapter sectionAdapter = sectionedAdapter.getAdapterForSection(section);
        final int previousItemsQty = section.getContentItemsTotal();

        section.setState(Section.State.LOADING);

        if (previousState == Section.State.LOADED) {
            sectionAdapter.notifyStateChangedFromLoaded(previousItemsQty);
        } else {
            sectionAdapter.notifyNotLoadedStateChanged(previousState);
        }
    }

    @Override
    public void onHeaderFailedButtonClicked(@NonNull final NameSection section) {
        final Section.State previousState = section.getState();
        if (previousState == Section.State.FAILED) return;

        final SectionAdapter sectionAdapter = sectionedAdapter.getAdapterForSection(section);
        final int previousItemsQty = section.getContentItemsTotal();

        section.setState(Section.State.FAILED);

        if (previousState == Section.State.LOADED) {
            sectionAdapter.notifyStateChangedFromLoaded(previousItemsQty);
        } else {
            sectionAdapter.notifyNotLoadedStateChanged(previousState);
        }
    }

    @Override
    public void onHeaderEmptyButtonClicked(@NonNull final NameSection section) {
        final Section.State previousState = section.getState();
        if (previousState == Section.State.EMPTY) return;

        final SectionAdapter sectionAdapter = sectionedAdapter.getAdapterForSection(section);
        final int previousItemsQty = section.getContentItemsTotal();

        section.setState(Section.State.EMPTY);

        if (previousState == Section.State.LOADED) {
            sectionAdapter.notifyStateChangedFromLoaded(previousItemsQty);
        } else {
            sectionAdapter.notifyNotLoadedStateChanged(previousState);
        }
    }

    private Person createPerson() {
        String[] names = getResources().getStringArray(R.array.names);

        String randomName = names[RANDOM.nextInt(names.length)];

        return new Person(randomName, "ID #" + getRandomStringNumber(), getRandomImage(randomName));
    }

    private int getRandomImage(final String name) {
        return name.hashCode() % 2 == 0 ? R.drawable.ic_face_black_48dp : R.drawable.ic_tag_faces_black_48dp;
    }
}
