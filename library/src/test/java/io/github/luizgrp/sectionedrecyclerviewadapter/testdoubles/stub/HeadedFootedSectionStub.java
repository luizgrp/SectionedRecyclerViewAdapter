package io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;

/**
 * A stub of StatelessSection with header and footer.
 */
public class HeadedFootedSectionStub extends Section {

    private final int contentItemsTotal;

    public HeadedFootedSectionStub(final int contentItemsTotal) {
        super(SectionParameters.builder()
                .itemResourceId(-1)
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
    public RecyclerView.ViewHolder getItemViewHolder(final View view) {
        return null;
    }

    @Override
    public void onBindItemViewHolder(final RecyclerView.ViewHolder holder, final int position) {

    }
}
