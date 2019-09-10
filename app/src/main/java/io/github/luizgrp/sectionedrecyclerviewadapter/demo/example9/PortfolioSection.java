package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example9;

import android.view.View;

import java.util.List;

import androidx.annotation.ColorInt;
import androidx.recyclerview.widget.RecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.R;

class PortfolioSection extends Section {

    private final List<PortfolioItem> list;

    PortfolioSection(List<PortfolioItem> list) {
        super(SectionParameters.builder()
                .headerResourceId(R.layout.section_ex9_portfolio_header)
                .itemResourceId(R.layout.section_ex9_portfolio_item)
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
        return new PortfolioItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final PortfolioItemViewHolder itemHolder = (PortfolioItemViewHolder) holder;

        final PortfolioItem item = list.get(position);

        itemHolder.tvCode.setText(item.code);
        itemHolder.tvHoldingQty.setText(String.valueOf(item.holdingQty));
        itemHolder.tvHoldingPrice.setText(String.valueOf(item.holdingPrice));
        itemHolder.tvPrice.setText(String.valueOf(item.price));
        itemHolder.tvDelta.setText(String.valueOf(item.delta));
        itemHolder.tvDelta.setTextColor(item.deltaColor);
    }

    @Override
    public void onBindItemViewHolder(final RecyclerView.ViewHolder holder, final int position,
                                     final List<Object> payloads) {
        final PortfolioItemViewHolder itemHolder = (PortfolioItemViewHolder) holder;

        final PortfolioItem item = list.get(position);

        for (Object obj : payloads) {
            if (obj instanceof ItemPriceUpdate) {
                itemHolder.tvHoldingPrice.setText(String.valueOf(item.holdingPrice));
                itemHolder.tvPrice.setText(String.valueOf(item.price));
                itemHolder.tvDelta.setText(String.valueOf(item.delta));
                itemHolder.tvDelta.setTextColor(item.deltaColor);
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new PortfolioHeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, List<Object> payloads) {
        final PortfolioHeaderViewHolder headerHolder = (PortfolioHeaderViewHolder) holder;

        headerHolder.tvHoldingsPriceTotal.setText((String) payloads.get(payloads.size() - 1));
    }

    void updateItemPrice(final int index, final String price, final String delta,
                         final @ColorInt int deltaColor, final String holdingPrice) {
        PortfolioItem portfolioItem = list.get(index);

        portfolioItem.price = price;
        portfolioItem.delta = delta;
        portfolioItem.deltaColor = deltaColor;
        portfolioItem.holdingPrice = holdingPrice;
    }

    static class ItemPriceUpdate {
    }
}
