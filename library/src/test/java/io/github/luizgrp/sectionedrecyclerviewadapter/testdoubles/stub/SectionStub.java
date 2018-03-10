package io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;

/**
 * A stub of Section with no header or footer.
 */
public class SectionStub extends Section {

    private final int contentItemsTotal;

    public SectionStub(int contentItemsTotal) {
        super(SectionParameters.builder()
                .itemResourceId(-1)
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

    }
}
