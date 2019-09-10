package io.github.luizgrp.sectionedrecyclerviewadapter.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class Example6Fragment extends Fragment {

    private SectionedRecyclerViewAdapter sectionedAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ex6, container, false);

        sectionedAdapter = new SectionedRecyclerViewAdapter();

        sectionedAdapter.addSection(new ExpandableMovieSection(getString(R.string.top_rated_movies_topic), getTopRatedMoviesList()));
        sectionedAdapter.addSection(new ExpandableMovieSection(getString(R.string.most_popular_movies_topic), getMostPopularMoviesList()));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

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
        recyclerView.setLayoutManager(glm);
        recyclerView.setAdapter(sectionedAdapter);

        return view;
    }

    private List<Movie> getTopRatedMoviesList() {
        List<String> arrayList = new ArrayList<>(Arrays.asList(getResources()
                .getStringArray(R.array.top_rated_movies)));

        List<Movie> movieList = new ArrayList<>();

        for (String str : arrayList) {
            String[] array = str.split("\\|");
            movieList.add(new Movie(array[0], array[1]));
        }

        return movieList;
    }

    private List<Movie> getMostPopularMoviesList() {
        List<String> arrayList = new ArrayList<>(Arrays.asList(getResources()
                .getStringArray(R.array.most_popular_movies)));

        List<Movie> movieList = new ArrayList<>();

        for (String str : arrayList) {
            String[] array = str.split("\\|");
            movieList.add(new Movie(array[0], array[1]));
        }

        return movieList;
    }

    private class ExpandableMovieSection extends Section {

        final String title;
        final List<Movie> list;
        boolean expanded = true;

        ExpandableMovieSection(String title, List<Movie> list) {
            super(SectionParameters.builder()
                    .itemResourceId(R.layout.section_ex6_item)
                    .headerResourceId(R.layout.section_ex6_header)
                    .build());

            this.title = title;
            this.list = list;
        }

        @Override
        public int getContentItemsTotal() {
            return expanded ? list.size() : 0;
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
            final ItemViewHolder itemHolder = (ItemViewHolder) holder;

            String name = list.get(position).getName();
            String category = list.get(position).getCategory();

            itemHolder.tvItem.setText(name);
            itemHolder.tvSubItem.setText(category);

            itemHolder.rootView.setOnClickListener(v ->
                    Toast.makeText(
                            getContext(),
                            String.format(
                                    "Clicked on position #%s of Section %s",
                                    sectionedAdapter.getPositionInSection(itemHolder.getAdapterPosition()),
                                    title
                            ),
                            Toast.LENGTH_SHORT
                    ).show()
            );
        }

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            final HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

            headerHolder.tvTitle.setText(title);

            headerHolder.rootView.setOnClickListener(v -> {
                expanded = !expanded;
                headerHolder.imgArrow.setImageResource(
                        expanded ? R.drawable.ic_keyboard_arrow_up_black_18dp : R.drawable.ic_keyboard_arrow_down_black_18dp
                );
                sectionedAdapter.notifyDataSetChanged();
            });
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final TextView tvTitle;
        private final ImageView imgArrow;

        HeaderViewHolder(View view) {
            super(view);

            rootView = view;
            tvTitle = view.findViewById(R.id.tvTitle);
            imgArrow = view.findViewById(R.id.imgArrow);
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final TextView tvItem;
        private final TextView tvSubItem;

        ItemViewHolder(View view) {
            super(view);

            rootView = view;
            tvItem = view.findViewById(R.id.tvItem);
            tvSubItem = view.findViewById(R.id.tvSubItem);
        }
    }

    private class Movie {
        final String name;
        final String category;

        Movie(String name, String category) {
            this.name = name;
            this.category = category;
        }

        String getName() {
            return name;
        }

        String getCategory() {
            return category;
        }
    }
}
