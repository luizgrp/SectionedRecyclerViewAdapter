package io.github.luizgrp.sectionedrecyclerviewadapter.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class Example8Fragment extends Fragment {

    private static final Random RANDOM = new Random();

    private SectionedRecyclerViewAdapter sectionedAdapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ex8, container, false);

        sectionedAdapter = new SectionedRecyclerViewAdapter();

        GridLayoutManager glm = new GridLayoutManager(getContext(), 2);
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
        String randomNumber = getRandomStringNumber();
        String sectionTag = String.format("section%sTag", randomNumber);

        NameSection section = new NameSection(sectionTag,
                getString(R.string.group_title, randomNumber));

        sectionedAdapter.addSection(sectionTag, section);

        int sectionPos = sectionedAdapter.getAdapterForSection(sectionTag).getSectionPosition();

        sectionedAdapter.notifyItemInserted(sectionPos);

        recyclerView.smoothScrollToPosition(sectionPos);
    }

    @NonNull
    private String getRandomStringNumber() {
        return String.valueOf(RANDOM.nextInt(100000));
    }

    private Person getRandomName() {
        String[] names = getResources().getStringArray(R.array.names);

        String[] randomName = names[RANDOM.nextInt(names.length)].split("\\|");

        return new Person(randomName[0], "ID #" + getRandomStringNumber());
    }

    private class NameSection extends Section {

        final String TAG;
        final String title;
        final List<Person> list;

        NameSection(String tag, String title) {
            super(SectionParameters.builder()
                    .itemResourceId(R.layout.section_ex8_item)
                    .headerResourceId(R.layout.section_ex8_header)
                    .build());

            this.TAG = tag;
            this.title = title;
            this.list = new ArrayList<>();
        }

        @Override
        public int getContentItemsTotal() {
            return list.size();
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

            final ItemViewHolder itemHolder = (ItemViewHolder) holder;

            String name = list.get(position).getName();
            String category = list.get(position).getId();

            itemHolder.tvItem.setText(name);
            itemHolder.tvSubItem.setText(category);
            itemHolder.imgItem.setImageResource(name.hashCode() % 2 == 0 ? R.drawable.ic_face_black_48dp : R.drawable.ic_tag_faces_black_48dp);

            itemHolder.rootView.setOnClickListener(v -> {
                final int adapterPosition = itemHolder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    int positionInSection = sectionedAdapter.getPositionInSection(adapterPosition);

                    list.remove(positionInSection);

                    sectionedAdapter.getAdapterForSection(TAG).notifyItemRemoved(positionInSection);
                }
            });
        }

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            final HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

            headerHolder.tvTitle.setText(title);

            headerHolder.rootView.setOnClickListener(view -> {
                final int adapterPosition = headerHolder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    final int sectionItemsTotal = getSectionItemsTotal();

                    sectionedAdapter.removeSection(TAG);

                    sectionedAdapter.notifyItemRangeRemoved(adapterPosition, sectionItemsTotal);
                }
            });

            headerHolder.btnAdd.setOnClickListener(v -> {
                final int positionToInsertItemAt = 0;

                list.add(positionToInsertItemAt, getRandomName());

                sectionedAdapter.getAdapterForSection(TAG).notifyItemInserted(positionToInsertItemAt);
            });

            headerHolder.btnClear.setOnClickListener(v -> {
                final int contentItemsTotal = getContentItemsTotal();

                list.clear();

                sectionedAdapter.getAdapterForSection(TAG).notifyItemRangeRemoved(0, contentItemsTotal);
            });
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final TextView tvTitle;
        private final Button btnAdd;
        private final Button btnClear;

        HeaderViewHolder(View view) {
            super(view);

            rootView = view;
            tvTitle = view.findViewById(R.id.tvTitle);
            btnAdd = view.findViewById(R.id.btnAdd);
            btnClear = view.findViewById(R.id.btnClear);
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final ImageView imgItem;
        private final TextView tvItem;
        private final TextView tvSubItem;

        ItemViewHolder(View view) {
            super(view);

            rootView = view;
            imgItem = view.findViewById(R.id.imgItem);
            tvItem = view.findViewById(R.id.tvItem);
            tvSubItem = view.findViewById(R.id.tvSubItem);
        }
    }

    private class Person {
        final String name;
        final String id;

        Person(String name, String id) {
            this.name = name;
            this.id = id;
        }

        String getName() {
            return name;
        }

        String getId() {
            return id;
        }
    }
}
