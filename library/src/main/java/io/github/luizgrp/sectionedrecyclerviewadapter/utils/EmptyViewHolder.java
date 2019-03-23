package io.github.luizgrp.sectionedrecyclerviewadapter.utils;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * A concrete class of an empty ViewHolder.
 * Should be used to avoid the boilerplate of creating a ViewHolder class for simple case
 * scenarios.
 */
public class EmptyViewHolder extends RecyclerView.ViewHolder {
    public EmptyViewHolder(final View itemView) {
        super(itemView);
    }
}
