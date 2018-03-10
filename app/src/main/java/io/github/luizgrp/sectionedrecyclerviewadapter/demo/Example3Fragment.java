package io.github.luizgrp.sectionedrecyclerviewadapter.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class Example3Fragment extends Fragment {

    private Handler mHandler = new Handler();

    private SectionedRecyclerViewAdapter sectionAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ex3, container, false);

        sectionAdapter = new SectionedRecyclerViewAdapter();

        NewsSection worldNews = new NewsSection(NewsSection.WORLD);
        NewsSection bizNews = new NewsSection(NewsSection.BUSINESS);
        NewsSection techNews = new NewsSection(NewsSection.TECHNOLOGY);
        NewsSection sportsNews = new NewsSection(NewsSection.SPORTS);

        sectionAdapter.addSection(worldNews);
        sectionAdapter.addSection(bizNews);
        sectionAdapter.addSection(techNews);
        sectionAdapter.addSection(sportsNews);

        loadNews(worldNews);
        loadNews(bizNews);
        loadNews(techNews);
        loadNews(sportsNews);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(sectionAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = ((AppCompatActivity) getActivity());
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setTitle(R.string.nav_example3);
            }
        }
    }

    @Override
    public void onDetach() {
        mHandler.removeCallbacksAndMessages(null);

        super.onDetach();
    }

    private void loadNews(final NewsSection section) {
        int timeInMills = new Random().nextInt((7000 - 3000) + 1) + 3000;

        section.setState(Section.State.LOADING);
        section.setHasFooter(false);
        sectionAdapter.notifyDataSetChanged();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int failed = new Random().nextInt((3 - 1) + 1) + 1;

                if (failed == 1) {
                    section.setState(Section.State.FAILED);
                } else {
                    int arrayResource;
                    switch (section.getTopic()) {
                        case NewsSection.WORLD:
                            arrayResource = R.array.news_world;
                            break;
                        case NewsSection.BUSINESS:
                            arrayResource = R.array.news_biz;
                            break;
                        case NewsSection.TECHNOLOGY:
                            arrayResource = R.array.news_tech;
                            break;
                        case NewsSection.SPORTS:
                            arrayResource = R.array.news_sports;
                            break;
                        default:
                            throw new IllegalStateException("Invalid topic");
                    }
                    section.setList(getNews(arrayResource));
                    section.setState(Section.State.LOADED);
                    section.setHasFooter(true);
                }

                sectionAdapter.notifyDataSetChanged();
            }
        }, timeInMills);
    }

    private List<String> getNews(int arrayResource) {
        return new ArrayList<>(Arrays.asList(getResources().getStringArray(arrayResource)));
    }

    private class NewsSection extends Section {

        final static int WORLD = 0;
        final static int BUSINESS = 1;
        final static int TECHNOLOGY = 2;
        final static int SPORTS = 3;

        final int topic;

        String title;
        List<String> list;
        int imgPlaceholderResId;

        NewsSection(int topic) {
            super(SectionParameters.builder()
                    .itemResourceId(R.layout.section_ex3_item)
                    .headerResourceId(R.layout.section_ex3_header)
                    .footerResourceId(R.layout.section_ex3_footer)
                    .failedResourceId(R.layout.section_ex3_failed)
                    .loadingResourceId(R.layout.section_ex3_loading)
                    .build());

            this.topic = topic;
            this.list = Collections.emptyList();

            switch (topic) {
                case WORLD:
                    this.title = getString(R.string.world_topic);
                    this.imgPlaceholderResId = R.drawable.ic_public_black_48dp;
                    break;
                case BUSINESS:
                    this.title = getString(R.string.biz_topic);
                    this.imgPlaceholderResId = R.drawable.ic_business_black_48dp;
                    break;
                case TECHNOLOGY:
                    this.title = getString(R.string.tech_topic);
                    this.imgPlaceholderResId = R.drawable.ic_devices_other_black_48dp;
                    break;
                case SPORTS:
                    this.title = getString(R.string.sports_topic);
                    this.imgPlaceholderResId = R.drawable.ic_directions_run_black_48dp;
                    break;
            }
        }

        int getTopic() {
            return topic;
        }

        public void setList(List<String> list) {
            this.list = list;
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

            String[] item = list.get(position).split("\\|");

            itemHolder.tvHeader.setText(item[0]);
            itemHolder.tvDate.setText(item[1]);
            itemHolder.imgItem.setImageResource(imgPlaceholderResId);

            itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(),
                            String.format("Clicked on position #%s of Section %s",
                                    sectionAdapter.getPositionInSection(itemHolder.getAdapterPosition()),
                                    title),
                            Toast.LENGTH_SHORT).show();
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
        }

        @Override
        public RecyclerView.ViewHolder getFooterViewHolder(View view) {
            return new FooterViewHolder(view);
        }

        @Override
        public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {
            FooterViewHolder footerHolder = (FooterViewHolder) holder;

            footerHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), String.format("Clicked on footer of Section %s", title), Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public RecyclerView.ViewHolder getFailedViewHolder(View view) {
            return new FailedViewHolder(view);
        }

        @Override
        public void onBindFailedViewHolder(RecyclerView.ViewHolder holder) {
            FailedViewHolder footerHeader = (FailedViewHolder) holder;

            footerHeader.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadNews(NewsSection.this);
                }
            });
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;

        HeaderViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;

        FooterViewHolder(View view) {
            super(view);

            rootView = view;
        }
    }

    private class FailedViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;

        FailedViewHolder(View itemView) {
            super(itemView);

            rootView = itemView.findViewById(R.id.rootView);
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final ImageView imgItem;
        private final TextView tvHeader;
        private final TextView tvDate;

        ItemViewHolder(View view) {
            super(view);

            rootView = view;
            imgItem = (ImageView) view.findViewById(R.id.imgItem);
            tvHeader = (TextView) view.findViewById(R.id.tvHeader);
            tvDate = (TextView) view.findViewById(R.id.tvDate);
        }
    }
}
