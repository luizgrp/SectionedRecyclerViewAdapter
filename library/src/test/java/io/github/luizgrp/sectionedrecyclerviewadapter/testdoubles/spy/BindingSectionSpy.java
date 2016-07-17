package io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;

/**
 * A spy of Section with no header or footer to check if onBind methods are being called.
 */
public class BindingSectionSpy extends Section {

    public boolean onBindItemViewHolderWasCalled = false;

    final int contentItemsTotal;

    public BindingSectionSpy(int contentItemsTotal) {
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
}
