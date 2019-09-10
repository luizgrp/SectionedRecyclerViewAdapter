package io.github.luizgrp.sectionedrecyclerviewadapter.compat;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * This is an implementation of {@link SectionedRecyclerViewAdapter SectionedRecyclerViewAdapter}
 * to help migrating to version 3.0.0 if you don't have much time to update your current code.
 * <p>
 * All the methods from this class where moved to class {@link SectionAdapter SectionAdapter}.
 * In order to retrieve a SectionAdapter from SectionedRecyclerViewAdapter, use the method
 * {@link SectionedRecyclerViewAdapter#getAdapterForSection(Section)} getAdapterForSection}.
 */
@SuppressWarnings({"WeakerAccess", "PMD.AtLeastOneConstructor"})
public class SectionedRecyclerViewAdapterV2Compat extends SectionedRecyclerViewAdapter {

    public int getFooterPositionInAdapter(final Section section) {
        return getAdapterForSection(section).getFooterPosition();
    }

    public int getFooterPositionInAdapter(final String tag) {
        return getAdapterForSection(tag).getFooterPosition();
    }

    public int getHeaderPositionInAdapter(final Section section) {
        return getAdapterForSection(section).getHeaderPosition();
    }

    public int getHeaderPositionInAdapter(final String tag) {
        return getAdapterForSection(tag).getHeaderPosition();
    }

    public int getPositionInAdapter(final Section section, final int position) {
        return getAdapterForSection(section).getPositionInAdapter(position);
    }

    public int getPositionInAdapter(final String tag, final int position) {
        return getAdapterForSection(tag).getPositionInAdapter(position);
    }

    public int getSectionPosition(final Section section) {
        return getAdapterForSection(section).getSectionPosition();
    }

    public int getSectionPosition(final String tag) {
        return getAdapterForSection(tag).getSectionPosition();
    }

    public void notifyAllItemsChangedInSection(final Section section) {
        getAdapterForSection(section).notifyAllItemsChanged();
    }

    public void notifyAllItemsChangedInSection(final String tag) {
        getAdapterForSection(tag).notifyAllItemsChanged();
    }

    public void notifyAllItemsInsertedInSection(final Section section) {
        getAdapterForSection(section).notifyAllItemsInserted();
    }

    public void notifyAllItemsInsertedInSection(final String tag) {
        getAdapterForSection(tag).notifyAllItemsInserted();
    }

    public void notifyFooterChangedInSection(final Section section) {
        getAdapterForSection(section).notifyFooterChanged();
    }

    public void notifyFooterChangedInSection(final String tag) {
        getAdapterForSection(tag).notifyFooterChanged();
    }

    public void notifyFooterInsertedInSection(final Section section) {
        getAdapterForSection(section).notifyFooterInserted();
    }

    public void notifyFooterInsertedInSection(final String tag) {
        getAdapterForSection(tag).notifyFooterInserted();
    }

    public void notifyFooterRemovedFromSection(final Section section) {
        getAdapterForSection(section).notifyFooterRemoved();
    }

    public void notifyFooterRemovedFromSection(final String tag) {
        getAdapterForSection(tag).notifyFooterRemoved();
    }

    public void notifyHeaderChangedInSection(final Section section) {
        getAdapterForSection(section).notifyHeaderChanged();
    }

    public void notifyHeaderChangedInSection(final String tag) {
        getAdapterForSection(tag).notifyHeaderChanged();
    }

    public void notifyHeaderInsertedInSection(final Section section) {
        getAdapterForSection(section).notifyHeaderInserted();
    }

    public void notifyHeaderInsertedInSection(final String tag) {
        getAdapterForSection(tag).notifyHeaderInserted();
    }

    public void notifyHeaderRemovedFromSection(final Section section) {
        getAdapterForSection(section).notifyHeaderRemoved();
    }

    public void notifyHeaderRemovedFromSection(final String tag) {
        getAdapterForSection(tag).notifyHeaderRemoved();
    }

    public void notifyItemChangedInSection(final Section section, final int position) {
        getAdapterForSection(section).notifyItemChanged(position);
    }

    public void notifyItemChangedInSection(final String tag, final int position) {
        getAdapterForSection(tag).notifyItemChanged(position);
    }

    public void notifyItemInsertedInSection(final Section section, final int position) {
        getAdapterForSection(section).notifyItemInserted(position);
    }

    public void notifyItemInsertedInSection(final String tag, final int position) {
        getAdapterForSection(tag).notifyItemInserted(position);
    }

    public void notifyItemMovedInSection(final Section section, final int fromPosition, final int toPosition) {
        getAdapterForSection(section).notifyItemMoved(fromPosition, toPosition);
    }

    public void notifyItemMovedInSection(final String tag, final int fromPosition, final int toPosition) {
        getAdapterForSection(tag).notifyItemMoved(fromPosition, toPosition);
    }

    public void notifyItemRangeChangedInSection(final Section section, final int positionStart, final int itemCount) {
        getAdapterForSection(section).notifyItemRangeChanged(positionStart, itemCount);
    }

    public void notifyItemRangeChangedInSection(final Section section, final int positionStart, final int itemCount, final Object payload) {
        getAdapterForSection(section).notifyItemRangeChanged(positionStart, itemCount, payload);
    }

    public void notifyItemRangeChangedInSection(final String tag, final int positionStart, final int itemCount) {
        getAdapterForSection(tag).notifyItemRangeChanged(positionStart, itemCount);

    }

    public void notifyItemRangeChangedInSection(final String tag, final int positionStart, final int itemCount, final Object payload) {
        getAdapterForSection(tag).notifyItemRangeChanged(positionStart, itemCount, payload);
    }

    public void notifyItemRangeInsertedInSection(final Section section, final int positionStart, final int itemCount) {
        getAdapterForSection(section).notifyItemRangeInserted(positionStart, itemCount);
    }

    public void notifyItemRangeInsertedInSection(final String tag, final int positionStart, final int itemCount) {
        getAdapterForSection(tag).notifyItemRangeInserted(positionStart, itemCount);
    }

    public void notifyItemRangeRemovedFromSection(final Section section, final int positionStart, final int itemCount) {
        getAdapterForSection(section).notifyItemRangeRemoved(positionStart, itemCount);
    }

    public void notifyItemRangeRemovedFromSection(final String tag, final int positionStart, final int itemCount) {
        getAdapterForSection(tag).notifyItemRangeRemoved(positionStart, itemCount);
    }

    public void notifyItemRemovedFromSection(final Section section, final int position) {
        getAdapterForSection(section).notifyItemRemoved(position);
    }

    public void notifyItemRemovedFromSection(final String tag, final int position) {
        getAdapterForSection(tag).notifyItemRemoved(position);
    }

    public void notifyNotLoadedStateChanged(final Section section, final Section.State previousState) {
        getAdapterForSection(section).notifyNotLoadedStateChanged(previousState);
    }

    public void notifyNotLoadedStateChanged(final String tag, final Section.State previousState) {
        getAdapterForSection(tag).notifyNotLoadedStateChanged(previousState);
    }

    public void notifySectionChangedToInvisible(final Section section, final int previousSectionPosition) {
        getAdapterForSection(section).notifySectionChangedToInvisible(previousSectionPosition);
    }

    public void notifySectionChangedToInvisible(final String tag, final int previousSectionPosition) {
        getAdapterForSection(tag).notifySectionChangedToInvisible(previousSectionPosition);
    }

    public void notifySectionChangedToVisible(final Section section) {
        getAdapterForSection(section).notifySectionChangedToVisible();
    }

    public void notifySectionChangedToVisible(final String tag) {
        getAdapterForSection(tag).notifySectionChangedToVisible();
    }

    public void notifyStateChangedFromLoaded(final Section section, final int previousContentItemsCount) {
        getAdapterForSection(section).notifyStateChangedFromLoaded(previousContentItemsCount);
    }

    public void notifyStateChangedFromLoaded(final String tag, final int previousContentItemsCount) {
        getAdapterForSection(tag).notifyStateChangedFromLoaded(previousContentItemsCount);
    }

    public void notifyStateChangedToLoaded(final Section section, final Section.State previousState) {
        getAdapterForSection(section).notifyStateChangedToLoaded(previousState);
    }

    public void notifyStateChangedToLoaded(final String tag, final Section.State previousState) {
        getAdapterForSection(tag).notifyStateChangedToLoaded(previousState);
    }
}
