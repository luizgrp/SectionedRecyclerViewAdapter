package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example8;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
                if (sectionedAdapter.getSectionItemViewType(position) == SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER) {
                    return 2;
                }
                return 1;
            }
        });

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(glm);
        recyclerView.setAdapter(sectionedAdapter);

        addNewSectionToAdapter();

        addNewSectionToAdapter();

        view.findViewById(R.id.btnAdd).setOnClickListener(view1 -> addNewSectionToAdapter());

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
    public void onHeaderRootViewClicked(@NonNull final NameSection section, final int itemAdapterPosition) {
        if (itemAdapterPosition != RecyclerView.NO_POSITION) {
            int sectionItemsTotal = section.getSectionItemsTotal();

            sectionedAdapter.removeSection(section);

            sectionedAdapter.notifyItemRangeRemoved(itemAdapterPosition, sectionItemsTotal);
        }
    }

    @Override
    public void onHeaderAddButtonClicked(@NonNull final NameSection section) {
        final int positionToInsertItemAt = 0;

        section.add(positionToInsertItemAt, createPerson());

        sectionedAdapter.getAdapterForSection(section).notifyItemInserted(positionToInsertItemAt);
    }

    @Override
    public void onHeaderClearButtonClicked(@NonNull final NameSection section) {
        int contentItemsTotal = section.getContentItemsTotal();

        section.clear();

        sectionedAdapter.getAdapterForSection(section).notifyItemRangeRemoved(0, contentItemsTotal);
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
