package io.github.luizgrp.sectionedrecyclerviewadapter.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

public class Example8Fragment extends Fragment {

    private static final Random RANDOM = new Random();

    private SectionedRecyclerViewAdapter sectionAdapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ex8, container, false);

        sectionAdapter = new SectionedRecyclerViewAdapter();

        GridLayoutManager glm = new GridLayoutManager(getContext(), 2);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch(sectionAdapter.getSectionItemViewType(position)) {
                    case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
                        return 2;
                    default:
                        return 1;
                }
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(glm);
        recyclerView.setAdapter(sectionAdapter);

        addNewSectionToAdapter();

        addNewSectionToAdapter();

        view.findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewSectionToAdapter();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = ((AppCompatActivity) getActivity());
            if (activity.getSupportActionBar() != null)
                activity.getSupportActionBar().setTitle(R.string.nav_example8);
        }
    }

    private void addNewSectionToAdapter() {
        String randomNumber = getRandomStringNumber();
        String sectionTag = String.format("section%sTag", randomNumber);

        NameSection section = new NameSection(sectionTag,
                getString(R.string.group_title, randomNumber));

        sectionAdapter.addSection(sectionTag, section);

        int sectionPos = sectionAdapter.getSectionPosition(sectionTag);

        sectionAdapter.notifyItemInserted(sectionPos);

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

    class NameSection extends StatelessSection {

        final String TAG;
        String title;
        List<Person> list;

        public NameSection(String tag, String title) {
            super(R.layout.section_ex8_header, R.layout.section_ex8_item);

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

            itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = itemHolder.getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        int positionInSection = sectionAdapter.getPositionInSection(adapterPosition);

                        list.remove(positionInSection);

                        sectionAdapter.notifyItemRemovedFromSection(TAG, positionInSection);
                    }
                }
            });
        }

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

            headerHolder.tvTitle.setText(title);

            headerHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int positionToInsertItemAt = 0;

                    list.add(positionToInsertItemAt, getRandomName());

                    sectionAdapter.notifyItemInsertedInSection(TAG, positionToInsertItemAt);
                }
            });

            headerHolder.btnClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int contentItemsTotal = getContentItemsTotal();

                    list.clear();

                    sectionAdapter.notifyItemRangeRemovedFromSection(TAG, 0, contentItemsTotal);
                }
            });
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final Button btnAdd;
        private final Button btnClear;

        public HeaderViewHolder(View view) {
            super(view);

            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            btnAdd = (Button) view.findViewById(R.id.btnAdd);
            btnClear = (Button) view.findViewById(R.id.btnClear);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final ImageView imgItem;
        private final TextView tvItem;
        private final TextView tvSubItem;

        public ItemViewHolder(View view) {
            super(view);

            rootView = view;
            imgItem = (ImageView) view.findViewById(R.id.imgItem);
            tvItem = (TextView) view.findViewById(R.id.tvItem);
            tvSubItem = (TextView) view.findViewById(R.id.tvSubItem);
        }
    }

    class Person {
        String name;
        String id;

        public Person(String name, String id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
