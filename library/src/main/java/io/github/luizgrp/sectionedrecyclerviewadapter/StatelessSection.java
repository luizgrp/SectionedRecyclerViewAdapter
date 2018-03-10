package io.github.luizgrp.sectionedrecyclerviewadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Abstract {@link Section} with no states.
 */
public abstract class StatelessSection extends Section {

    /**
     * Create a stateless Section object based on {@link SectionParameters}.
     *
     * @param sectionParameters section parameters
     */
    public StatelessSection(SectionParameters sectionParameters) {
        super(sectionParameters);

        if (sectionParameters.loadingResourceId != null) {
            throw new IllegalArgumentException("Stateless section shouldn't have a loading state resource");
        }

        if (sectionParameters.loadingViewWillBeProvided) {
            throw new IllegalArgumentException("Stateless section shouldn't have loadingViewWillBeProvided set");
        }

        if (sectionParameters.failedResourceId != null) {
            throw new IllegalArgumentException("Stateless section shouldn't have a failed state resource");
        }

        if (sectionParameters.failedViewWillBeProvided) {
            throw new IllegalArgumentException("Stateless section shouldn't have failedViewWillBeProvided set");
        }

        if (sectionParameters.emptyResourceId != null) {
            throw new IllegalArgumentException("Stateless section shouldn't have an empty state resource");
        }

        if (sectionParameters.emptyViewWillBeProvided) {
            throw new IllegalArgumentException("Stateless section shouldn't have emptyViewWillBeProvided set");
        }
    }

    // Override these methods to make them final.

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

    @Override
    public final void onBindEmptyViewHolder(RecyclerView.ViewHolder holder) {
        super.onBindEmptyViewHolder(holder);
    }

    @Override
    public final RecyclerView.ViewHolder getEmptyViewHolder(View view) {
        return super.getEmptyViewHolder(view);
    }
}
