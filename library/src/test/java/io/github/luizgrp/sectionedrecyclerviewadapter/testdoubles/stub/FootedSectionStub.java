package io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * A stub of StatelessSection with footer.
 */
public class FootedSectionStub extends Section {

    final int contentItemsTotal;

    public FootedSectionStub(int contentItemsTotal) {
        super(-1, -1, -1, -1, -1);

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

    }
}
