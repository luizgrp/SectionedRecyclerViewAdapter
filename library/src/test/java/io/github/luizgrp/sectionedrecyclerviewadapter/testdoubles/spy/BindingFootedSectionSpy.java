package io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;

/**
 * A spy of Section with footer to check if onBind methods are being called.
 */
public class BindingFootedSectionSpy extends Section {

    public boolean onBindItemViewHolderWasCalled = false;
    public boolean onBindFooterViewHolderWasCalled = false;
    public boolean onBindLoadingViewHolderWasCalled = false;
    public boolean onBindFailedViewHolderWasCalled = false;

    final int contentItemsTotal;

    public BindingFootedSectionSpy(int contentItemsTotal) {
        super(-1, -1, -1, -1 , -1);

        // remove header
        this.setHasHeader(false);

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
    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {
        onBindFooterViewHolderWasCalled = true;
    }

    @Override
    public void onBindLoadingViewHolder(RecyclerView.ViewHolder holder) {
        onBindLoadingViewHolderWasCalled = true;
    }

    @Override
    public void onBindFailedViewHolder(RecyclerView.ViewHolder holder) {
        onBindFailedViewHolderWasCalled = true;
    }
}
