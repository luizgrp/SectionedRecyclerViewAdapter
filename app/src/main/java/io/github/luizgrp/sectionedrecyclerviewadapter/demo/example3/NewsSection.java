package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example3;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.R;
import io.github.luizgrp.sectionedrecyclerviewadapter.utils.EmptyViewHolder;

final class NewsSection extends Section {

    final static int WORLD = 0;
    final static int BUSINESS = 1;
    final static int TECHNOLOGY = 2;
    final static int SPORTS = 3;

    private final int topic;
    private final String title;
    private final ClickListener clickListener;
    private final List<News> list;


    NewsSection(final int topic, @NonNull final String title, @NonNull final ClickListener clickListener) {

        super(SectionParameters.builder()
                .itemResourceId(R.layout.section_ex3_item)
                .headerResourceId(R.layout.section_ex3_header)
                .footerResourceId(R.layout.section_ex3_footer)
                .failedResourceId(R.layout.section_ex3_failed)
                .loadingResourceId(R.layout.section_ex3_loading)
                .build());

        this.topic = topic;
        this.title = title;
        this.clickListener = clickListener;

        this.list = new ArrayList<>();
    }

    void setList(final List<News> list) {
        this.list.clear();
        this.list.addAll(list);
    }

    @Override
    public int getContentItemsTotal() {
        return list.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(final View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ItemViewHolder itemHolder = (ItemViewHolder) holder;

        final News news = list.get(position);

        itemHolder.tvHeader.setText(news.header);
        itemHolder.tvDate.setText(news.date);
        itemHolder.imgItem.setImageResource(news.icon);

        itemHolder.rootView.setOnClickListener(v ->
                clickListener.onItemRootViewClicked(this, itemHolder.getAdapterPosition())
        );
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(final View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(final RecyclerView.ViewHolder holder) {
        final HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

        headerHolder.tvTitle.setText(title);
    }

    @Override
    public RecyclerView.ViewHolder getFooterViewHolder(final View view) {
        return new FooterViewHolder(view);
    }

    @Override
    public void onBindFooterViewHolder(final RecyclerView.ViewHolder holder) {
        final FooterViewHolder footerHolder = (FooterViewHolder) holder;

        footerHolder.rootView.setOnClickListener(v ->
                clickListener.onFooterRootViewClicked(this, footerHolder.getAdapterPosition())
        );
    }

    @Override
    public RecyclerView.ViewHolder getLoadingViewHolder(final View view) {
        return new EmptyViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder getFailedViewHolder(final View view) {
        return new FailedViewHolder(view);
    }

    @Override
    public void onBindFailedViewHolder(final RecyclerView.ViewHolder holder) {
        final FailedViewHolder footerHeader = (FailedViewHolder) holder;

        footerHeader.rootView.setOnClickListener(v ->
                clickListener.onFailedRootViewClicked(this)
        );
    }

    int getTopic() {
        return topic;
    }

    interface ClickListener {

        void onItemRootViewClicked(final NewsSection section, final int itemAdapterPosition);

        void onFooterRootViewClicked(final NewsSection section, final int itemAdapterPosition);

        void onFailedRootViewClicked(final NewsSection section);
    }
}
