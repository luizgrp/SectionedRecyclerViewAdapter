package io.github.luizgrp.sectionedrecyclerviewadapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;

/**
 * ListUpdateCallback that dispatches update events to the given {@link SectionAdapter}.
 *
 * @see DiffUtil.DiffResult#dispatchUpdatesTo(ListUpdateCallback)
 */
public class SectionAdapterListUpdateCallback implements ListUpdateCallback {

    @SuppressWarnings("PMD.BeanMembersShouldSerialize")
    private final SectionAdapter sectionAdapter;

    public SectionAdapterListUpdateCallback(final SectionAdapter sectionAdapter) {
        this.sectionAdapter = sectionAdapter;
    }

    @Override
    public void onInserted(final int position, final int count) {
        sectionAdapter.notifyItemRangeInserted(position, count);
    }

    @Override
    public void onRemoved(final int position, final int count) {
        sectionAdapter.notifyItemRangeRemoved(position, count);
    }

    @Override
    public void onMoved(final int fromPosition, final int toPosition) {
        sectionAdapter.notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onChanged(final int position, final int count, @Nullable final Object payload) {
        sectionAdapter.notifyItemRangeChanged(position, count, payload);
    }
}
