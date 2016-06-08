package io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * A spy of StatelessSection with header and footer to check if onBind methods are being called.
 */
public class BindingHeadedFootedStatelessSectionSpy extends StatelessSection {

    public boolean onBindItemViewHolderWasCalled = false;
    public boolean onBindHeaderViewHolderWasCalled = false;
    public boolean onBindFooterViewHolderWasCalled = false;

    final int contentItemsTotal;

    public BindingHeadedFootedStatelessSectionSpy(int contentItemsTotal) {
        super(-1, -1, -1);

        this.contentItemsTotal = contentItemsTotal;
    }

    @Override
    public int getContentItemsTotal() {
        return contentItemsTotal;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return null;
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindItemViewHolderWasCalled = true;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        onBindHeaderViewHolderWasCalled = true;
    }

    @Override
    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {
        onBindFooterViewHolderWasCalled = true;
    }
}
