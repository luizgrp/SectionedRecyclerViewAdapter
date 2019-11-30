package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example2;

import android.view.View;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.R;

final class NewsSection extends Section {

    private final String title;
    private final List<News> list;
    private final ClickListener clickListener;

    NewsSection(@NonNull final String title, @NonNull final List<News> list,
                @NonNull final ClickListener clickListener) {

        super(SectionParameters.builder()
                .itemResourceId(R.layout.section_ex2_item)
                .headerResourceId(R.layout.section_ex2_header)
                .footerResourceId(R.layout.section_ex2_footer)
                .build());

        this.title = title;
        this.list = list;
        this.clickListener = clickListener;
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

        footerHolder.rootView.setOnClickListener(v -> clickListener.onFooterRootViewClicked(this, footerHolder.getAdapterPosition()));
    }

    interface ClickListener {

        void onItemRootViewClicked(@NonNull final NewsSection section, final int itemAdapterPosition);

        void onFooterRootViewClicked(@NonNull final NewsSection section, final int itemAdapterPosition);
    }
}
