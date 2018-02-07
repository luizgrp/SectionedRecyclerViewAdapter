package io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * A spy of StatelessSection with header to check if onBind methods are being called.
 */
public class BindingHeadedStatelessSectionSpy extends StatelessSection {

    public boolean onBindItemViewHolderWasCalled = false;
    public boolean onBindHeaderViewHolderWasCalled = false;

    private final int contentItemsTotal;

    public BindingHeadedStatelessSectionSpy(int contentItemsTotal) {
        super(SectionParameters.builder(-1)
                .headerResourceId(-1)
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
}
