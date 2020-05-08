package io.github.luizgrp.sectionedrecyclerviewadapter;

import java.util.Map;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

/**
 * This class represents a {@link Section Section} in the {@link SectionedRecyclerViewAdapter SectionedRecyclerViewAdapter}.
 * <p>
 * It provides helper methods for:
 * <ul>
 * <li>identifying position of this {@link Section Section}'s items in the {@link SectionedRecyclerViewAdapter SectionedRecyclerViewAdapter}</li>
 * <li>identifying position of the {@link SectionedRecyclerViewAdapter SectionedRecyclerViewAdapter} in this {@link Section Section}</li>
 * <li>notifying {@link SectionedRecyclerViewAdapter SectionedRecyclerViewAdapter} of item changes in this Section</li>
 * </ul>
 */
public class SectionAdapter implements SectionPositionIdentifier, SectionNotifier {

    private final transient SectionedRecyclerViewAdapter sectionedAdapter;
    private final transient Section section;

    /* default */ SectionAdapter(final SectionedRecyclerViewAdapter sectionedAdapter, final Section section) {
        this.sectionedAdapter = sectionedAdapter;
        this.section = section;
    }

    @VisibleForTesting
    /* default */ Section getSection() {
        return section;
    }

    @Override
    public int getHeaderPosition() {
        if (!section.hasHeader()) {
            throw new IllegalStateException("Section doesn't have a header");
        }

        return getSectionPosition();
    }

    @Override
    public int getFooterPosition() {
        if (!section.hasFooter()) {
            throw new IllegalStateException("Section doesn't have a footer");
        }

        return getSectionPosition() + section.getSectionItemsTotal() - 1;
    }

    @Override
    public int getSectionPosition() {
        int currentPos = 0;

        for (final Map.Entry<String, Section> entry : sectionedAdapter.getSections().entrySet()) {
            final Section loopSection = entry.getValue();

            // ignore invisible sections
            if (!loopSection.isVisible()) {
                continue;
            }

            if (loopSection == section) {
                return currentPos;
            }

            final int sectionTotal = loopSection.getSectionItemsTotal();

            currentPos += sectionTotal;
        }

        throw new IllegalArgumentException("Section is not in the adapter.");
    }

    @Override
    public int getPositionInAdapter(final int position) {
        return getSectionPosition() + (section.hasHeader() ? 1 : 0) + position;
    }

    @Override
    public int getPositionInSection(final int position) {
        return sectionedAdapter.getPositionInSection(position);
    }

    @Override
    public void notifyItemInserted(final int position) {
        sectionedAdapter.notifyItemInserted(getPositionInAdapter(position));
    }

    @Override
    public void notifyAllItemsInserted() {
        sectionedAdapter.notifyItemRangeInserted(
                getPositionInAdapter(0),
                section.getContentItemsTotal()
        );
    }

    @Override
    public void notifyItemRangeInserted(final int positionStart, final int itemCount) {
        sectionedAdapter.notifyItemRangeInserted(
                getPositionInAdapter(positionStart),
                itemCount
        );
    }

    @Override
    public void notifyItemRemoved(final int position) {
        sectionedAdapter.notifyItemRemoved(getPositionInAdapter(position));
    }

    @Override
    public void notifyItemRangeRemoved(final int positionStart, final int itemCount) {
        sectionedAdapter.notifyItemRangeRemoved(
                getPositionInAdapter(positionStart),
                itemCount
        );
    }

    @Override
    public void notifyHeaderChanged() {
        sectionedAdapter.notifyItemChanged(getHeaderPosition());
    }

    @Override
    public void notifyHeaderChanged(@Nullable final Object payload) {
        sectionedAdapter.notifyItemChanged(getHeaderPosition(), payload);
    }

    @Override
    public void notifyFooterChanged() {
        sectionedAdapter.notifyItemChanged(getFooterPosition());
    }

    @Override
    public void notifyFooterChanged(@Nullable final Object payload) {
        sectionedAdapter.notifyItemChanged(getFooterPosition(), payload);
    }

    @Override
    public void notifyItemChanged(final int position) {
        sectionedAdapter.notifyItemChanged(getPositionInAdapter(position));
    }

    @Override
    public void notifyItemChanged(final int position, @Nullable final Object payload) {
        sectionedAdapter.notifyItemChanged(getPositionInAdapter(position), payload);
    }

    @Override
    public void notifyAllItemsChanged() {
        sectionedAdapter.notifyItemRangeChanged(
                getPositionInAdapter(0),
                section.getContentItemsTotal()
        );
    }

    @Override
    public void notifyAllItemsChanged(@Nullable final Object payload) {
        sectionedAdapter.notifyItemRangeChanged(
                getPositionInAdapter(0),
                section.getContentItemsTotal(),
                payload
        );
    }

    @Override
    public void notifyItemRangeChanged(final int positionStart, final int itemCount) {
        sectionedAdapter.notifyItemRangeChanged(
                getPositionInAdapter(positionStart),
                itemCount
        );
    }

    @Override
    public void notifyItemRangeChanged(final int positionStart, final int itemCount, @Nullable final Object payload) {
        sectionedAdapter.notifyItemRangeChanged(
                getPositionInAdapter(positionStart),
                itemCount,
                payload
        );
    }

    @Override
    public void notifyItemMoved(final int fromPosition, final int toPosition) {
        sectionedAdapter.notifyItemMoved(
                getPositionInAdapter(fromPosition),
                getPositionInAdapter(toPosition)
        );
    }

    @Override
    public void notifyNotLoadedStateChanged(final Section.State previousState) {
        final Section.State state = section.getState();

        if (state == previousState) {
            throw new IllegalStateException("No state changed");
        }

        if (previousState == Section.State.LOADED) {
            throw new IllegalStateException("Use notifyStateChangedFromLoaded");
        }

        if (state == Section.State.LOADED) {
            throw new IllegalStateException("Use notifyStateChangedToLoaded");
        }

        notifyItemChanged(0);
    }

    @Override
    public void notifyStateChangedToLoaded(final Section.State previousState) {
        final Section.State state = section.getState();

        if (state == previousState) {
            throw new IllegalStateException("No state changed");
        }

        if (state != Section.State.LOADED) {
            if (previousState == Section.State.LOADED) {
                throw new IllegalStateException("Use notifyStateChangedFromLoaded");
            } else {
                throw new IllegalStateException("Use notifyNotLoadedStateChanged");
            }
        }

        final int contentItemsTotal = section.getContentItemsTotal();

        if (contentItemsTotal == 0) {
            notifyItemRemoved(0);
        } else {
            notifyItemChanged(0);

            if (contentItemsTotal > 1) {
                notifyItemRangeInserted(1, contentItemsTotal - 1);
            }
        }
    }

    @Override
    public void notifyStateChangedFromLoaded(final int previousContentItemsCount) {
        if (previousContentItemsCount < 0) {
            throw new IllegalArgumentException("previousContentItemsCount cannot have a negative value");
        }

        final Section.State state = section.getState();
        if (state == Section.State.LOADED) {
            throw new IllegalStateException("Use notifyStateChangedToLoaded");
        }

        if (previousContentItemsCount == 0) {
            notifyItemInserted(0);
        } else {
            if (previousContentItemsCount > 1) {
                notifyItemRangeRemoved(1, previousContentItemsCount - 1);
            }

            notifyItemChanged(0);
        }
    }

    @Override
    public void notifyHeaderInserted() {
        final int headerPosition = getHeaderPosition();

        sectionedAdapter.notifyItemInserted(headerPosition);
    }

    @Override
    public void notifyFooterInserted() {
        final int footerPosition = getFooterPosition();

        sectionedAdapter.notifyItemInserted(footerPosition);
    }

    @Override
    public void notifyHeaderRemoved() {
        final int position = getSectionPosition();

        sectionedAdapter.notifyItemRemoved(position);
    }

    @Override
    public void notifyFooterRemoved() {
        final int position = getSectionPosition() + section.getSectionItemsTotal();

        sectionedAdapter.notifyItemRemoved(position);
    }

    @Override
    public void notifySectionChangedToVisible() {
        if (!section.isVisible()) {
            throw new IllegalStateException("This section is not visible.");
        }

        final int sectionPosition = getSectionPosition();

        final int sectionItemsTotal = section.getSectionItemsTotal();

        sectionedAdapter.notifyItemRangeInserted(sectionPosition, sectionItemsTotal);
    }

    @Override
    public void notifySectionChangedToInvisible(final int previousSectionPosition) {
        if (section.isVisible()) {
            throw new IllegalStateException("This section is not invisible.");
        }

        final int sectionItemsTotal = section.getSectionItemsTotal();

        sectionedAdapter.notifyItemRangeRemoved(previousSectionPosition, sectionItemsTotal);
    }
}
