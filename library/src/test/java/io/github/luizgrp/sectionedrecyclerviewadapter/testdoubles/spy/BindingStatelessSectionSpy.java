package io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * A spy of StatelessSection with no header or footer to check if onBind methods are being called.
 */
public class BindingStatelessSectionSpy extends StatelessSection {

    public boolean onBindItemViewHolderWasCalled = false;

    private final int contentItemsTotal;

    public BindingStatelessSectionSpy(int contentItemsTotal) {
        super(new SectionParameters.Builder(-1)
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
}
