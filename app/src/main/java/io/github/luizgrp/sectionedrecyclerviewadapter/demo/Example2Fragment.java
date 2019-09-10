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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class Example2Fragment extends Fragment {

    private SectionedRecyclerViewAdapter sectionedAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ex2, container, false);

        sectionedAdapter = new SectionedRecyclerViewAdapter();

        sectionedAdapter.addSection(new NewsSection(NewsSection.WORLD));
        sectionedAdapter.addSection(new NewsSection(NewsSection.BUSINESS));
        sectionedAdapter.addSection(new NewsSection(NewsSection.TECHNOLOGY));
        sectionedAdapter.addSection(new NewsSection(NewsSection.SPORTS));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(sectionedAdapter);

        return view;
    }

    private class NewsSection extends Section {

        final static int WORLD = 0;
        final static int BUSINESS = 1;
        final static int TECHNOLOGY = 2;
        final static int SPORTS = 3;

        String title;
        List<String> list;
        int imgPlaceholderResId;

        NewsSection(int topic) {
            super(SectionParameters.builder()
                    .itemResourceId(R.layout.section_ex2_item)
                    .headerResourceId(R.layout.section_ex2_header)
                    .footerResourceId(R.layout.section_ex2_footer)
                    .build());

            switch (topic) {
                case WORLD:
                    this.title = getString(R.string.world_topic);
                    this.list = getNews(R.array.news_world);
                    this.imgPlaceholderResId = R.drawable.ic_public_black_48dp;
                    break;
                case BUSINESS:
                    this.title = getString(R.string.biz_topic);
                    this.list = getNews(R.array.news_biz);
                    this.imgPlaceholderResId = R.drawable.ic_business_black_48dp;
                    break;
                case TECHNOLOGY:
                    this.title = getString(R.string.tech_topic);
                    this.list = getNews(R.array.news_tech);
                    this.imgPlaceholderResId = R.drawable.ic_devices_other_black_48dp;
                    break;
                case SPORTS:
                    this.title = getString(R.string.sports_topic);
                    this.list = getNews(R.array.news_sports);
                    this.imgPlaceholderResId = R.drawable.ic_directions_run_black_48dp;
                    break;
            }
        }

        private List<String> getNews(int arrayResource) {
            return new ArrayList<>(Arrays.asList(getResources().getStringArray(arrayResource)));
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

            footerHolder.rootView.setOnClickListener(v ->
                    Toast.makeText(
                            getContext(),
                            String.format(
                                    "Clicked on footer of Section %s",
                                    title
                            ),
                            Toast.LENGTH_SHORT
                    ).show()
            );
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;

        HeaderViewHolder(View view) {
            super(view);

            tvTitle = view.findViewById(R.id.tvTitle);
        }
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;

        FooterViewHolder(View view) {
            super(view);

            rootView = view;
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
            imgItem = view.findViewById(R.id.imgItem);
            tvHeader = view.findViewById(R.id.tvHeader);
            tvDate = view.findViewById(R.id.tvDate);
        }
    }
}
