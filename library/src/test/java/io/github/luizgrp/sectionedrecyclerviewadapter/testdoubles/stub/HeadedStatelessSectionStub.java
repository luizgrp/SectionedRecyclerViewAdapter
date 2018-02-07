package io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * A stub of StatelessSection with header.
 */
public class HeadedStatelessSectionStub extends StatelessSection {

    private final int contentItemsTotal;

    public HeadedStatelessSectionStub(int contentItemsTotal) {
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

    }
}
