package io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * A stub of StatelessSection with header and footer.
 */
public class HeadedFootedStatelessSectionStub extends StatelessSection {

    private final int contentItemsTotal;

    public HeadedFootedStatelessSectionStub(final int contentItemsTotal) {
        super(SectionParameters.builder()
                .itemResourceId(-1)
                .headerResourceId(-1)
                .footerResourceId(-1)
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
