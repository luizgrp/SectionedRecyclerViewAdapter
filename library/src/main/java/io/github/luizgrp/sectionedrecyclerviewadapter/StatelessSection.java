package io.github.luizgrp.sectionedrecyclerviewadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Abstract {@link Section} with no states.
 */
public abstract class StatelessSection extends Section {

    /**
     * Create a stateless Section object without header and footer
     * @param itemResourceId layout resource for its items
     */
    public StatelessSection(int itemResourceId) {
        super();
        this.itemResourceId = itemResourceId;
    }

    /**
     * Create a stateless Section object, with a custom header but without footer
     * @param headerResourceId layout resource for its header
     * @param itemResourceId layout resource for its items
     */
    public StatelessSection(int headerResourceId, int itemResourceId) {
        this(itemResourceId);
        this.headerResourceId = headerResourceId;
        this.hasHeader = true;
    }

    /**
     * Create a stateless Section object, with a custom header and a custom footer
     * @param headerResourceId layout resource for its header
     * @param footerResourceId layout resource for its footer
     * @param itemResourceId layout resource for its items
     */
    public StatelessSection(int headerResourceId, int footerResourceId, int itemResourceId) {
        this(headerResourceId, itemResourceId);
        this.footerResourceId = footerResourceId;
        this.hasFooter = true;
    }

    @Override
    public final void onBindLoadingViewHolder(RecyclerView.ViewHolder holder) {
        super.onBindLoadingViewHolder(holder);
    }

    @Override
    public final RecyclerView.ViewHolder getLoadingViewHolder(View view) {
        return super.getLoadingViewHolder(view);
    }

    @Override
    public final void onBindFailedViewHolder(RecyclerView.ViewHolder holder) {
        super.onBindFailedViewHolder(holder);
    }

    @Override
    public final RecyclerView.ViewHolder getFailedViewHolder(View view) {
        return super.getFailedViewHolder(view);
    }
}
