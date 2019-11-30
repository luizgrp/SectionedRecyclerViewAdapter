package io.github.luizgrp.sectionedrecyclerviewadapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView.Adapter;

/**
 * Collection of helper methods to notify the adapter of changes in the section items.
 */
/* default */ interface SectionNotifier {

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link Adapter#notifyItemInserted}.
     *
     * @param position position of the item in the section
     */
    void notifyItemInserted(final int position);

    /**
     * Helper method that calculates the relative position of all items of this section in the
     * adapter and calls {@link Adapter#notifyItemRangeInserted}.
     */
    void notifyAllItemsInserted();

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link Adapter#notifyItemRangeInserted}.
     *
     * @param positionStart position of the first item that was inserted in the section
     * @param itemCount     number of items inserted in the section
     */
    void notifyItemRangeInserted(final int positionStart, final int itemCount);

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link Adapter#notifyItemRemoved}.
     *
     * @param position position of the item in the section
     */
    void notifyItemRemoved(final int position);

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link Adapter#notifyItemRangeRemoved}.
     *
     * @param positionStart previous position of the first item that was removed from the section
     * @param itemCount     number of items removed from the section
     */
    void notifyItemRangeRemoved(final int positionStart, final int itemCount);

    /**
     * Helper method that calculates the relative header position in the adapter and calls
     * {@link Adapter#notifyItemChanged}.
     */
    void notifyHeaderChanged();

    /**
     * Helper method that calculates the relative header position in the adapter and calls
     * {@link Adapter#notifyItemChanged}.
     *
     * @param payload optional parameter, use null to identify a "full" update
     */
    void notifyHeaderChanged(@Nullable final Object payload);

    /**
     * Helper method that calculates the relative footer position in the adapter and calls
     * {@link Adapter#notifyItemChanged}.
     */
    void notifyFooterChanged();

    /**
     * Helper method that calculates the relative footer position in the adapter and calls
     * {@link Adapter#notifyItemChanged}.
     *
     * @param payload optional parameter, use null to identify a "full" update
     */
    void notifyFooterChanged(@Nullable final Object payload);

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link Adapter#notifyItemChanged}.
     *
     * @param position position of the item in the section
     */
    void notifyItemChanged(final int position);

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link Adapter#notifyItemChanged}.
     *
     * @param position position of the item in the section
     * @param payload  optional parameter, use null to identify a "full" update
     */
    void notifyItemChanged(final int position, @Nullable final Object payload);

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link Adapter#notifyItemRangeChanged}.
     */
    void notifyAllItemsChanged();

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link Adapter#notifyItemRangeChanged}.
     *
     * @param payload  optional parameter, use null to identify a "full" update
     */
    void notifyAllItemsChanged(@Nullable final Object payload);

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link Adapter#notifyItemRangeChanged}.
     *
     * @param positionStart position of the first item that was changed in the section
     * @param itemCount     number of items changed in the section
     */
    void notifyItemRangeChanged(final int positionStart, final int itemCount);

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link Adapter#notifyItemRangeChanged}.
     *
     * @param positionStart position of the first item that was inserted in the section
     * @param itemCount     number of items inserted in the section
     * @param payload       optional parameter, use null to identify a "full" update
     */
    void notifyItemRangeChanged(final int positionStart, final int itemCount, @Nullable final Object payload);

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link Adapter#notifyItemMoved}.
     *
     * @param fromPosition previous position of the item in the section
     * @param toPosition   new position of the item in the section
     */
    void notifyItemMoved(final int fromPosition, final int toPosition);

    /**
     * Helper method that calls {@link Adapter#notifyItemChanged} with the position of the {@link Section.State}
     * view holder in the adapter. Useful to be called after changing the State from
     * LOADING/FAILED/EMPTY to LOADING/FAILED/EMPTY.
     *
     * @param previousState previous state of section
     */
    void notifyNotLoadedStateChanged(final Section.State previousState);

    /**
     * Helper method that calls {@link Adapter#notifyItemChanged} and {@link Adapter#notifyItemInserted} with
     * the position of the {@link Section.State} view holder in the adapter. Useful to be called after
     * changing the State from LOADING/FAILED/EMPTY to LOADED.
     *
     * @param previousState previous state of section
     */
    void notifyStateChangedToLoaded(final Section.State previousState);

    /**
     * Helper method that calls {@link Adapter#notifyItemRangeRemoved} and {@link Adapter#notifyItemChanged} with
     * the position of the {@link Section.State} view holder in the adapter. Useful to be called after
     * changing the State from LOADED to LOADING/FAILED/EMPTY.
     *
     * @param previousContentItemsCount previous content items count of section
     */
    void notifyStateChangedFromLoaded(final int previousContentItemsCount);

    /**
     * Helper method that calls {@link Adapter#notifyItemInserted} with the position of the section's
     * header in the adapter. Useful to be called after changing the visibility of the section's
     * header to visible with {@link Section#setHasHeader}.
     */
    void notifyHeaderInserted();

    /**
     * Helper method that calls {@link Adapter#notifyItemInserted} with the position of the section's
     * footer in the adapter. Useful to be called after changing the visibility of the section's
     * footer to visible with {@link Section#setHasFooter}.
     */
    void notifyFooterInserted();

    /**
     * Helper method that calls {@link Adapter#notifyItemRemoved} with the position of the section's
     * header in the adapter. Useful to be called after changing the visibility of the section's
     * header to invisible with {@link Section#setHasHeader}.
     */
    void notifyHeaderRemoved();

    /**
     * Helper method that calls {@link Adapter#notifyItemRemoved} with the position of the section's
     * footer in the adapter. Useful to be called after changing the visibility of the section's
     * footer to invisible with {@link Section#setHasFooter}.
     */
    void notifyFooterRemoved();

    /**
     * Helper method that calls {@link Adapter#notifyItemRangeInserted} with the position of the section
     * in the adapter. Useful to be called after changing the visibility of the section to visible
     * with {@link Section#setVisible}.
     */
    void notifySectionChangedToVisible();

    /**
     * Helper method that calls {@link Adapter#notifyItemRangeInserted} with the position of the section
     * in the adapter. Useful to be called after changing the visibility of the section to invisible
     * with {@link Section#setVisible}.
     *
     * @param previousSectionPosition previous section position
     */
    void notifySectionChangedToInvisible(final int previousSectionPosition);
}
