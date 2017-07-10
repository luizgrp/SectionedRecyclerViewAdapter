package io.github.luizgrp.sectionedrecyclerviewadapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;

/**
 * Class that holds a reference to the ViewHolder class to use and the associated layout to inflate
 */

public class CustomViewType {
    private Class<?> viewHolderClass;
    @LayoutRes private int layoutId;

    public <T extends RecyclerView.ViewHolder> CustomViewType(@NonNull Class<T> viewHolderClass, @LayoutRes int layoutId) {
        this.viewHolderClass = viewHolderClass;
        this.layoutId = layoutId;
    }

    /**
     * Checks whether the provided ViewHolder class matches this CustomViewType's ViewHolder class
     * @param holder ViewHolder to check against
     * @return True if they are the same class
     */
    public boolean isInstanceOf(RecyclerView.ViewHolder holder) {
        return holder.getClass() == viewHolderClass;
    }

    /**
     * Get a ViewHolder using the ViewHolder Class and layout resource
     * @param parent ViewGroup parent to inflate the view within
     * @return The ViewHolder
     */
    @Nullable
    public RecyclerView.ViewHolder getViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        try {
            Constructor<?> constructor = viewHolderClass.asSubclass(RecyclerView.ViewHolder.class).getDeclaredConstructor(View.class);
            return (RecyclerView.ViewHolder) constructor.newInstance(view);
        } catch (Exception e) {
            Log.e(this.getClass().getSimpleName(), "Error creating new instance of ViewHolder subclass", e);
        }
        return new SectionedRecyclerViewAdapter.EmptyViewHolder(view);
    }
}