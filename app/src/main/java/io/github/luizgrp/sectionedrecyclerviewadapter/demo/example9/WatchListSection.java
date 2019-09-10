package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example9;

import android.view.View;

import java.util.List;

import androidx.annotation.ColorInt;
import androidx.recyclerview.widget.RecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.R;
import io.github.luizgrp.sectionedrecyclerviewadapter.utils.EmptyViewHolder;

class WatchListSection extends Section {

    private final List<WatchItem> list;

    WatchListSection(List<WatchItem> list) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.section_ex9_watchlist_item)
                .headerResourceId(R.layout.section_ex9_watchlist_header)
                .build());

        setHasHeader(true);

        this.list = list;
    }

    @Override
    public int getContentItemsTotal() {
        return list.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new WatchItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final WatchItemViewHolder itemHolder = (WatchItemViewHolder) holder;

        WatchItem item = list.get(position);

        itemHolder.tvCode.setText(item.code);
        itemHolder.tvPrice.setText(String.valueOf(item.price));
        itemHolder.tvDelta.setText(String.valueOf(item.delta));
        itemHolder.tvDelta.setTextColor(item.deltaColor);
    }

    @Override
    public void onBindItemViewHolder(final RecyclerView.ViewHolder holder, final int position,
                                     final List<Object> payloads) {
        final WatchItemViewHolder itemHolder = (WatchItemViewHolder) holder;

        WatchItem item = list.get(position);

        for (Object obj : payloads) {
            if (obj instanceof ItemPriceUpdate) {
                itemHolder.tvPrice.setText(String.valueOf(item.price));
                itemHolder.tvDelta.setText(String.valueOf(item.delta));
                itemHolder.tvDelta.setTextColor(item.deltaColor);
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new EmptyViewHolder(view);
    }

    void updateItemPrice(final int index, final String price, final String delta,
                         final @ColorInt int deltaColor) {
        WatchItem watchItem = list.get(index);

        watchItem.price = price;
        watchItem.delta = delta;
        watchItem.deltaColor = deltaColor;
    }

    static class ItemPriceUpdate {
    }
}
