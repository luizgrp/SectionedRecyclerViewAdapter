package io.github.luizgrp.sectionedrecyclerviewadapter.demo;

import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

    private final String TOP_RATED_MOVIES_SECTION_TAG = "topRatedMoviesSectionTag";
    private final String MOST_POPULAR_MOVIES_SECTION_TAG = "mostPopularMoviesSectionTag";

    private SectionedRecyclerViewAdapter sectionAdapter;

    private MovieSection topRatedMoviesSection;
    private MovieSection mostPopularMoviesSection;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ex8, container, false);

        topRatedMoviesSection = new MovieSection(TOP_RATED_MOVIES_SECTION_TAG,
                getString(R.string.top_rated_movies_topic), R.array.top_rated_movies);
        mostPopularMoviesSection = new MovieSection(MOST_POPULAR_MOVIES_SECTION_TAG,
                getString(R.string.most_popular_movies_topic), R.array.most_popular_movies);

        sectionAdapter = new SectionedRecyclerViewAdapter();
        sectionAdapter.addSection(TOP_RATED_MOVIES_SECTION_TAG, topRatedMoviesSection);
        sectionAdapter.addSection(MOST_POPULAR_MOVIES_SECTION_TAG, mostPopularMoviesSection);

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

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(glm);
        recyclerView.setAdapter(sectionAdapter);

        view.findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                onAddClicked();
            }
        });

        return view;
    }

    private String getRandomMovie(@ArrayRes int arrayRes) {
        String[] movies = getResources().getStringArray(arrayRes);
        return movies[RANDOM.nextInt(movies.length)];
    }

    class MovieSection extends StatelessSection {

        final String TAG;
        String title;
        @ArrayRes int arrayRes;
        List<Movie> list;

        public MovieSection(String tag, String title, @ArrayRes int arrayRes) {
            super(R.layout.section_ex8_header, R.layout.section_ex8_item);

            this.TAG = tag;
            this.title = title;
            this.arrayRes = arrayRes;
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
            String category = list.get(position).getCategory();

            itemHolder.tvItem.setText(name);
            itemHolder.tvSubItem.setText(category);
            itemHolder.imgItem.setImageResource(R.drawable.ic_movie_black_48dp);

            itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(sectionAdapter.getSectionPosition(itemHolder.getAdapterPosition()));

                    sectionAdapter.notifyItemRemoved(itemHolder.getAdapterPosition());
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

                    String[] array = getRandomMovie(arrayRes).split("\\|");
                    list.add(positionToInsertItemAt, new Movie(array[0], array[1]));

                    sectionAdapter.notifyItemInsertedInSection(TAG, positionToInsertItemAt);
                }
            });
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final Button btnAdd;

        public HeaderViewHolder(View view) {
            super(view);

            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            btnAdd = (Button) view.findViewById(R.id.btnAdd);
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

    class Movie {
        String name;
        String category;

        public Movie(String name, String category) {
            this.name = name;
            this.category = category;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }
}
