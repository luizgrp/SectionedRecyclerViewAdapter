package io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;

/**
 * A spy of Section with header and footer to check if onBind methods are being called.
 */
public class BindingHeadedFootedSectionSpy extends Section {

    public boolean onBindItemViewHolderWasCalled = false;
    public boolean onBindHeaderViewHolderWasCalled = false;
    public boolean onBindFooterViewHolderWasCalled = false;
    public boolean onBindLoadingViewHolderWasCalled = false;
    public boolean onBindFailedViewHolderWasCalled = false;
    public boolean onBindEmptyViewHolderWasCalled = false;

    private final int contentItemsTotal;

    public BindingHeadedFootedSectionSpy(int contentItemsTotal) {
        super(new SectionParameters.Builder(-1)
                .headerResourceId(-1)
                .footerResourceId(-1)
                .failedResourceId(-1)
                .loadingResourceId(-1)
                .emptyResourceId(-1)
                .build());

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

    @Override
    public void onBindLoadingViewHolder(RecyclerView.ViewHolder holder) {
        onBindLoadingViewHolderWasCalled = true;
    }

    @Override
    public void onBindFailedViewHolder(RecyclerView.ViewHolder holder) {
        onBindFailedViewHolderWasCalled = true;
    }

    @Override
    public void onBindEmptyViewHolder(RecyclerView.ViewHolder holder) {
        onBindEmptyViewHolderWasCalled = true;
    }
}
