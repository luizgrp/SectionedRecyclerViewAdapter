package io.github.luizgrp.sectionedrecyclerviewadapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import static io.github.luizgrp.sectionedrecyclerviewadapter.Section.State;

/**
 * A custom RecyclerView Adapter that allows {@link Section Sections} to be added to it.
 * Sections are displayed in the same order they were added.
 */
@SuppressWarnings({"WeakerAccess", "SameParameterValue", "PMD.CollapsibleIfStatements"})
public class SectionedRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_HEADER = 0;
    public static final int VIEW_TYPE_FOOTER = 1;
    public static final int VIEW_TYPE_ITEM_LOADED = 2;
    public static final int VIEW_TYPE_LOADING = 3;
    public static final int VIEW_TYPE_FAILED = 4;
    public static final int VIEW_TYPE_EMPTY = 5;

    private final Map<String, Section> sections;
    private final Map<String, Integer> sectionViewTypeNumbers;

    private int viewTypeCount = 0;
    private static final int VIEW_TYPE_QTY = 6;

    public SectionedRecyclerViewAdapter() {
        sections = new LinkedHashMap<>();
        sectionViewTypeNumbers = new LinkedHashMap<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;

        for (Map.Entry<String, Integer> entry : sectionViewTypeNumbers.entrySet()) {
            if (viewType >= entry.getValue() && viewType < entry.getValue() + VIEW_TYPE_QTY) {

                Section section = sections.get(entry.getKey());
                int sectionViewType = viewType - entry.getValue();

                switch (sectionViewType) {
                    case VIEW_TYPE_HEADER:
                        viewHolder = getHeaderViewHolder(parent, section);
                        break;
                    case VIEW_TYPE_FOOTER:
                        viewHolder = getFooterViewHolder(parent, section);
                        break;
                    case VIEW_TYPE_ITEM_LOADED:
                        viewHolder = getItemViewHolder(parent, section);
                        break;
                    case VIEW_TYPE_LOADING:
                        viewHolder = getLoadingViewHolder(parent, section);
                        break;
                    case VIEW_TYPE_FAILED:
                        viewHolder = getFailedViewHolder(parent, section);
                        break;
                    case VIEW_TYPE_EMPTY:
                        viewHolder = getEmptyViewHolder(parent, section);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid viewType");
                }
            }
        }

        return viewHolder;
    }

    @VisibleForTesting
    View inflate(@LayoutRes int layoutResourceId, ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutResourceId, parent, false);
    }

    private RecyclerView.ViewHolder getItemViewHolder(ViewGroup parent, Section section) {
        View view;
        if (section.isItemViewWillBeProvided()) {
            view = section.getItemView(parent);
            if (view == null) {
                throw new NullPointerException("Section.getItemView() returned null");
            }
        } else {
            Integer resId = section.getItemResourceId();
            if (resId == null) {
                throw new NullPointerException("Missing 'item' resource id");
            }
            view = inflate(resId, parent);
        }
        return section.getItemViewHolder(view);
    }

    private RecyclerView.ViewHolder getHeaderViewHolder(ViewGroup parent, Section section) {
        View view;
        if (section.isHeaderViewWillBeProvided()) {
            view = section.getHeaderView(parent);
            if (view == null) {
                throw new NullPointerException("Section.getHeaderView() returned null");
            }
        } else {
            Integer resId = section.getHeaderResourceId();
            if (resId == null) {
                throw new NullPointerException("Missing 'header' resource id");
            }
            view = inflate(resId, parent);
        }
        return section.getHeaderViewHolder(view);
    }

    private RecyclerView.ViewHolder getFooterViewHolder(ViewGroup parent, Section section) {
        View view;
        if (section.isFooterViewWillBeProvided()) {
            view = section.getFooterView(parent);
            if (view == null) {
                throw new NullPointerException("Section.getFooterView() returned null");
            }
        } else {
            Integer resId = section.getFooterResourceId();
            if (resId == null) {
                throw new NullPointerException("Missing 'footer' resource id");
            }
            view = inflate(resId, parent);
        }
        return section.getFooterViewHolder(view);
    }

    private RecyclerView.ViewHolder getLoadingViewHolder(ViewGroup parent, Section section) {
        View view;
        if (section.isLoadingViewWillBeProvided()) {
            view = section.getLoadingView(parent);
            if (view == null) {
                throw new NullPointerException("Section.getLoadingView() returned null");
            }
        } else {
            Integer resId = section.getLoadingResourceId();
            if (resId == null) {
                throw new NullPointerException("Missing 'loading' resource id");
            }
            view = inflate(resId, parent);
        }
        return section.getLoadingViewHolder(view);
    }

    private RecyclerView.ViewHolder getFailedViewHolder(ViewGroup parent, Section section) {
        View view;
        if (section.isFailedViewWillBeProvided()) {
            view = section.getFailedView(parent);
            if (view == null) {
                throw new NullPointerException("Section.getFailedView() returned null");
            }
        } else {
            Integer resId = section.getFailedResourceId();
            if (resId == null) {
                throw new NullPointerException("Missing 'failed' resource id");
            }
            view = inflate(resId, parent);
        }
        return section.getFailedViewHolder(view);
    }

    private RecyclerView.ViewHolder getEmptyViewHolder(ViewGroup parent, Section section) {
        View view;
        if (section.isEmptyViewWillBeProvided()) {
            view = section.getEmptyView(parent);
            if (view == null) {
                throw new NullPointerException("Section.getEmptyView() returned null");
            }
        } else {
            Integer resId = section.getEmptyResourceId();
            if (resId == null) {
                throw new NullPointerException("Missing 'empty' resource id");
            }
            view = inflate(resId, parent);
        }
        return section.getEmptyViewHolder(view);
    }

    /**
     * Add a section to this recyclerview.
     *
     * @param tag     unique identifier of the section
     * @param section section to be added
     */
    public void addSection(String tag, Section section) {
        this.sections.put(tag, section);
        this.sectionViewTypeNumbers.put(tag, viewTypeCount);
        viewTypeCount += VIEW_TYPE_QTY;
    }

    /**
     * Add a section to this recyclerview with a random tag.
     *
     * @param section section to be added
     * @return generated tag
     */
    public String addSection(Section section) {
        String tag = UUID.randomUUID().toString();

        addSection(tag, section);

        return tag;
    }

    /**
     * Return the section with the tag provided.
     *
     * @param tag unique identifier of the section
     * @return section
     */
    public Section getSection(String tag) {
        return this.sections.get(tag);
    }

    /**
     * Remove section from this recyclerview.
     *
     * @param section section to be removed
     */
    public void removeSection(Section section) {
        String tag = null;
        for (final Map.Entry<String, Section> entry : this.sections.entrySet()) {
            if (entry.getValue() == section) {
                tag = entry.getKey();
            }
        }

        if (tag != null) {
            this.removeSection(tag);
        }
    }

    /**
     * Remove section from this recyclerview.
     *
     * @param tag unique identifier of the section
     */
    public void removeSection(String tag) {
        this.sections.remove(tag);
        this.sectionViewTypeNumbers.remove(tag);
    }

    /**
     * Remove all sections from this recyclerview.
     */
    public void removeAllSections() {
        this.sections.clear();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int currentPos = 0;

        for (Map.Entry<String, Section> entry : sections.entrySet()) {
            Section section = entry.getValue();

            // ignore invisible sections
            if (!section.isVisible()) {
                continue;
            }

            int sectionTotal = section.getSectionItemsTotal();

            // check if position is in this section
            if (position >= currentPos && position <= (currentPos + sectionTotal - 1)) {

                if (section.hasHeader()) {
                    if (position == currentPos) {
                        // delegate the binding to the section header
                        getSectionForPosition(position).onBindHeaderViewHolder(holder);
                        return;
                    }
                }

                if (section.hasFooter()) {
                    if (position == (currentPos + sectionTotal - 1)) {
                        // delegate the binding to the section header
                        getSectionForPosition(position).onBindFooterViewHolder(holder);
                        return;
                    }
                }

                // delegate the binding to the section content
                getSectionForPosition(position).onBindContentViewHolder(holder, getPositionInSection(position));
                return;
            }

            currentPos += sectionTotal;
        }

        throw new IndexOutOfBoundsException("Invalid position");
    }

    @Override
    public int getItemCount() {
        int count = 0;

        for (Map.Entry<String, Section> entry : sections.entrySet()) {
            Section section = entry.getValue();

            // ignore invisible sections
            if (!section.isVisible()) {
                continue;
            }

            count += section.getSectionItemsTotal();
        }

        return count;
    }

    @Override
    public int getItemViewType(int position) {
        /*
         Each Section has 6 "viewtypes":
         1) header
         2) footer
         3) items
         4) loading
         5) load failed
         6) empty
         */
        int currentPos = 0;

        for (Map.Entry<String, Section> entry : sections.entrySet()) {
            Section section = entry.getValue();

            // ignore invisible sections
            if (!section.isVisible()) {
                continue;
            }

            int sectionTotal = section.getSectionItemsTotal();

            // check if position is in this section
            if (position >= currentPos && position <= (currentPos + sectionTotal - 1)) {

                int viewType = sectionViewTypeNumbers.get(entry.getKey());

                if (section.hasHeader()) {
                    if (position == currentPos) {
                        return viewType;
                    }
                }

                if (section.hasFooter()) {
                    if (position == (currentPos + sectionTotal - 1)) {
                        return viewType + 1;
                    }
                }

                switch (section.getState()) {
                    case LOADED:
                        return viewType + 2;
                    case LOADING:
                        return viewType + 3;
                    case FAILED:
                        return viewType + 4;
                    case EMPTY:
                        return viewType + 5;
                    default:
                        throw new IllegalStateException("Invalid state");
                }
            }

            currentPos += sectionTotal;
        }

        throw new IndexOutOfBoundsException("Invalid position");
    }

    /**
     * Returns the Section ViewType of an item based on the position in the adapter.
     *
     * @param position position in the adapter
     * @return one of the view types:
     * <ul>
     * <li>SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER</li>
     * <li>SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER</li>
     * <li>SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED</li>
     * <li>SectionedRecyclerViewAdapter.VIEW_TYPE_LOADING</li>
     * <li>SectionedRecyclerViewAdapter.VIEW_TYPE_FAILED</li>
     * <li>SectionedRecyclerViewAdapter.VIEW_TYPE_EMPTY</li>
     * </ul>
     */
    public int getSectionItemViewType(int position) {
        int viewType = getItemViewType(position);

        return viewType % VIEW_TYPE_QTY;
    }

    /**
     * Returns the Section object for a position in the adapter.
     *
     * @param position position in the adapter
     * @return Section object for that position
     */
    public Section getSectionForPosition(int position) {

        int currentPos = 0;

        for (Map.Entry<String, Section> entry : sections.entrySet()) {
            Section section = entry.getValue();

            // ignore invisible sections
            if (!section.isVisible()) {
                continue;
            }

            int sectionTotal = section.getSectionItemsTotal();

            // check if position is in this section
            if (position >= currentPos && position <= (currentPos + sectionTotal - 1)) {
                return section;
            }

            currentPos += sectionTotal;
        }

        throw new IndexOutOfBoundsException("Invalid position");
    }

    /**
     * @deprecated Use {@link #getPositionInSection} instead.
     */
    @Deprecated
    public int getSectionPosition(int position) {
        return getPositionInSection(position);
    }

    /**
     * Return the item position relative to the section.
     *
     * @param position position of the item in the adapter
     * @return position of the item in the section
     */
    public int getPositionInSection(int position) {
        int currentPos = 0;

        for (Map.Entry<String, Section> entry : sections.entrySet()) {
            Section section = entry.getValue();

            // ignore invisible sections
            if (!section.isVisible()) {
                continue;
            }

            int sectionTotal = section.getSectionItemsTotal();

            // check if position is in this section
            if (position >= currentPos && position <= (currentPos + sectionTotal - 1)) {
                return position - currentPos - (section.hasHeader() ? 1 : 0);
            }

            currentPos += sectionTotal;
        }

        throw new IndexOutOfBoundsException("Invalid position");
    }

    /**
     * Return the section position in the adapter.
     *
     * @param tag unique identifier of the section
     * @return position of the section in the adapter
     */
    public int getSectionPosition(String tag) {
        Section section = getValidSectionOrThrowException(tag);

        return getSectionPosition(section);
    }

    /**
     * Return the section position in the adapter.
     *
     * @param section a visible section of this adapter
     * @return position of the section in the adapter
     */
    public int getSectionPosition(Section section) {
        int currentPos = 0;

        for (Map.Entry<String, Section> entry : sections.entrySet()) {
            Section loopSection = entry.getValue();

            // ignore invisible sections
            if (!loopSection.isVisible()) {
                continue;
            }

            if (loopSection == section) {
                return currentPos;
            }

            int sectionTotal = loopSection.getSectionItemsTotal();

            currentPos += sectionTotal;
        }

        throw new IllegalArgumentException("Invalid section");
    }

    /**
     * Return a copy of the map with all sections of this adapter.
     *
     * @return a copy of the map with all sections
     */
    @NonNull
    public Map<String, Section> getCopyOfSectionsMap() {
        Map<String, Section> copyOfSections;
        synchronized (sections) {
            copyOfSections = new LinkedHashMap<>(sections);
        }
        return copyOfSections;
    }

    /**
     * Helper method that receives position in relation to the section, and returns the position in
     * the adapter.
     *
     * @param tag      unique identifier of the section
     * @param position position of the item in the section
     * @return position of the item in the adapter
     */
    public int getPositionInAdapter(String tag, int position) {
        Section section = getValidSectionOrThrowException(tag);

        return getPositionInAdapter(section, position);
    }

    /**
     * Helper method that receives position in relation to the section, and returns the position in
     * the adapter.
     *
     * @param section  a visible section of this adapter
     * @param position position of the item in the section
     * @return position of the item in the adapter
     */
    public int getPositionInAdapter(Section section, int position) {
        return getSectionPosition(section) + (section.hasHeader() ? 1 : 0) + position;
    }

    /**
     * Helper method that returns the position of header in the adapter.
     *
     * @param tag unique identifier of the section
     * @return position of the header in the adapter
     */
    public int getHeaderPositionInAdapter(String tag) {
        Section section = getValidSectionOrThrowException(tag);

        return getHeaderPositionInAdapter(section);
    }

    /**
     * Helper method that returns the position of header in the adapter.
     *
     * @param section a visible section of this adapter
     * @return position of the header in the adapter
     */
    public int getHeaderPositionInAdapter(Section section) {
        if (!section.hasHeader()) {
            throw new IllegalStateException("Section doesn't have a header");
        }

        return getSectionPosition(section);
    }

    /**
     * Helper method that returns the position of footer in the adapter.
     *
     * @param tag unique identifier of the section
     * @return position of the footer in the adapter
     */
    public int getFooterPositionInAdapter(String tag) {
        Section section = getValidSectionOrThrowException(tag);

        return getFooterPositionInAdapter(section);
    }

    /**
     * Helper method that returns the position of header in the adapter.
     *
     * @param section a visible section of this adapter
     * @return position of the footer in the adapter
     */
    public int getFooterPositionInAdapter(Section section) {
        if (!section.hasFooter()) {
            throw new IllegalStateException("Section doesn't have a footer");
        }

        return getSectionPosition(section) + section.getSectionItemsTotal() - 1;
    }

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link #notifyItemInserted}.
     *
     * @param tag      unique identifier of the section
     * @param position position of the item in the section
     */
    public void notifyItemInsertedInSection(String tag, int position) {
        callSuperNotifyItemInserted(getPositionInAdapter(tag, position));
    }

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link #notifyItemInserted}.
     *
     * @param section  a visible section of this adapter
     * @param position position of the item in the section
     */
    public void notifyItemInsertedInSection(Section section, int position) {
        callSuperNotifyItemInserted(getPositionInAdapter(section, position));
    }

    @VisibleForTesting
    void callSuperNotifyItemInserted(int position) {
        super.notifyItemInserted(position);
    }

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link #notifyItemRangeInserted}.
     *
     * @param tag           unique identifier of the section
     * @param positionStart position of the first item that was inserted in the section
     * @param itemCount     number of items inserted in the section
     */
    public void notifyItemRangeInsertedInSection(String tag, int positionStart, int itemCount) {
        callSuperNotifyItemRangeInserted(getPositionInAdapter(tag, positionStart), itemCount);
    }

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link #notifyItemRangeInserted}.
     *
     * @param section       a visible section of this adapter
     * @param positionStart position of the first item that was inserted in the section
     * @param itemCount     number of items inserted in the section
     */
    public void notifyItemRangeInsertedInSection(Section section, int positionStart, int itemCount) {
        callSuperNotifyItemRangeInserted(getPositionInAdapter(section, positionStart), itemCount);
    }

    @VisibleForTesting
    void callSuperNotifyItemRangeInserted(int positionStart, int itemCount) {
        super.notifyItemRangeInserted(positionStart, itemCount);
    }

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link #notifyItemRemoved}.
     *
     * @param tag      unique identifier of the section
     * @param position position of the item in the section
     */
    public void notifyItemRemovedFromSection(String tag, int position) {
        callSuperNotifyItemRemoved(getPositionInAdapter(tag, position));
    }

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link #notifyItemRemoved}.
     *
     * @param section  a visible section of this adapter
     * @param position position of the item in the section
     */
    public void notifyItemRemovedFromSection(Section section, int position) {
        callSuperNotifyItemRemoved(getPositionInAdapter(section, position));
    }

    @VisibleForTesting
    void callSuperNotifyItemRemoved(int position) {
        super.notifyItemRemoved(position);
    }

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link #notifyItemRangeRemoved}.
     *
     * @param tag           unique identifier of the section
     * @param positionStart previous position of the first item that was removed from the section
     * @param itemCount     number of items removed from the section
     */
    public void notifyItemRangeRemovedFromSection(String tag, int positionStart, int itemCount) {
        callSuperNotifyItemRangeRemoved(getPositionInAdapter(tag, positionStart), itemCount);
    }

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link #notifyItemRangeRemoved}.
     *
     * @param section       a visible section of this adapter
     * @param positionStart previous position of the first item that was removed from the section
     * @param itemCount     number of items removed from the section
     */
    public void notifyItemRangeRemovedFromSection(Section section, int positionStart, int itemCount) {
        callSuperNotifyItemRangeRemoved(getPositionInAdapter(section, positionStart), itemCount);
    }

    @VisibleForTesting
    void callSuperNotifyItemRangeRemoved(int positionStart, int itemCount) {
        super.notifyItemRangeRemoved(positionStart, itemCount);
    }

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link #notifyItemChanged}.
     *
     * @param tag      unique identifier of the section
     * @param position position of the item in the section
     */
    public void notifyItemChangedInSection(String tag, int position) {
        callSuperNotifyItemChanged(getPositionInAdapter(tag, position));
    }

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link #notifyItemChanged}.
     *
     * @param section  a visible section of this adapter
     * @param position position of the item in the section
     */
    public void notifyItemChangedInSection(Section section, int position) {
        callSuperNotifyItemChanged(getPositionInAdapter(section, position));
    }

    /**
     * Helper method that calculates the relative header position in the adapter and calls
     * {@link #notifyItemChanged}.
     *
     * @param tag unique identifier of the section
     */
    public void notifyHeaderChangedInSection(String tag) {
        notifyHeaderChangedInSection(getValidSectionOrThrowException(tag));
    }

    /**
     * Helper method that calculates the relative header position in the adapter and calls
     * {@link #notifyItemChanged}.
     *
     * @param section a visible section of this adapter
     */
    public void notifyHeaderChangedInSection(Section section) {
        callSuperNotifyItemChanged(getHeaderPositionInAdapter(section));
    }

    /**
     * Helper method that calculates the relative footer position in the adapter and calls
     * {@link #notifyItemChanged}.
     *
     * @param tag unique identifier of the section
     */
    public void notifyFooterChangedInSection(String tag) {
        notifyFooterChangedInSection(getValidSectionOrThrowException(tag));
    }

    /**
     * Helper method that calculates the relative footer position in the adapter and calls
     * {@link #notifyItemChanged}.
     *
     * @param section a visible section of this adapter
     */
    public void notifyFooterChangedInSection(Section section) {
        callSuperNotifyItemChanged(getFooterPositionInAdapter(section));
    }

    @VisibleForTesting
    void callSuperNotifyItemChanged(int position) {
        super.notifyItemChanged(position);
    }

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link #notifyItemRangeChanged}.
     *
     * @param tag           unique identifier of the section
     * @param positionStart position of the first item that was changed in the section
     * @param itemCount     number of items changed in the section
     */
    public void notifyItemRangeChangedInSection(String tag, int positionStart, int itemCount) {
        callSuperNotifyItemRangeChanged(getPositionInAdapter(tag, positionStart), itemCount);
    }

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link #notifyItemRangeChanged}.
     *
     * @param section       a visible section of this adapter
     * @param positionStart position of the first item that was changed in the section
     * @param itemCount     number of items changed in the section
     */
    public void notifyItemRangeChangedInSection(Section section, int positionStart, int itemCount) {
        callSuperNotifyItemRangeChanged(getPositionInAdapter(section, positionStart), itemCount);
    }

    @VisibleForTesting
    void callSuperNotifyItemRangeChanged(int positionStart, int itemCount) {
        super.notifyItemRangeChanged(positionStart, itemCount);
    }

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link #notifyItemRangeChanged}.
     *
     * @param tag           unique identifier of the section
     * @param positionStart position of the first item that was inserted in the section
     * @param itemCount     number of items inserted in the section
     * @param payload       optional parameter, use null to identify a "full" update
     */
    public void notifyItemRangeChangedInSection(String tag, int positionStart, int itemCount,
                                                Object payload) {
        callSuperNotifyItemRangeChanged(getPositionInAdapter(tag, positionStart), itemCount, payload);
    }

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link #notifyItemRangeChanged}.
     *
     * @param section       a visible section of this adapter
     * @param positionStart position of the first item that was inserted in the section
     * @param itemCount     number of items inserted in the section
     * @param payload       optional parameter, use null to identify a "full" update
     */
    public void notifyItemRangeChangedInSection(Section section, int positionStart, int itemCount,
                                                Object payload) {
        callSuperNotifyItemRangeChanged(getPositionInAdapter(section, positionStart), itemCount, payload);
    }

    @VisibleForTesting
    void callSuperNotifyItemRangeChanged(int positionStart, int itemCount, Object payload) {
        super.notifyItemRangeChanged(positionStart, itemCount, payload);
    }

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link #notifyItemMoved}.
     *
     * @param tag          unique identifier of the section
     * @param fromPosition previous position of the item in the section
     * @param toPosition   new position of the item in the section
     */
    public void notifyItemMovedInSection(String tag, int fromPosition, int toPosition) {
        callSuperNotifyItemMoved(getPositionInAdapter(tag, fromPosition), getPositionInAdapter(tag, toPosition));
    }

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link #notifyItemMoved}.
     *
     * @param section      a visible section of this adapter
     * @param fromPosition previous position of the item in the section
     * @param toPosition   new position of the item in the section
     */
    public void notifyItemMovedInSection(Section section, int fromPosition, int toPosition) {
        callSuperNotifyItemMoved(getPositionInAdapter(section, fromPosition), getPositionInAdapter(section, toPosition));
    }

    @VisibleForTesting
    void callSuperNotifyItemMoved(int fromPosition, int toPosition) {
        super.notifyItemMoved(fromPosition, toPosition);
    }

    /**
     * Helper method that calls {@link #notifyItemChanged} with the position of the {@link State}
     * view holder in the adapter. Useful to be called after changing the State from
     * LOADING/FAILED/EMPTY to LOADING/FAILED/EMPTY.
     *
     * @param tag           unique identifier of the section
     * @param previousState previous state of section
     */
    public void notifyNotLoadedStateChanged(String tag, State previousState) {
        Section section = getValidSectionOrThrowException(tag);

        notifyNotLoadedStateChanged(section, previousState);
    }

    /**
     * Helper method that calls {@link #notifyItemChanged} with the position of the {@link State}
     * view holder in the adapter. Useful to be called after changing the State from
     * LOADING/ FAILED/ EMPTY to LOADING/ FAILED/ EMPTY.
     *
     * @param section       a visible section of this adapter
     * @param previousState previous state of section
     */
    public void notifyNotLoadedStateChanged(Section section, State previousState) {
        State state = section.getState();

        if (state == previousState) {
            throw new IllegalStateException("No state changed");
        }

        if (previousState == State.LOADED) {
            throw new IllegalStateException("Use notifyStateChangedFromLoaded");
        }

        if (state == State.LOADED) {
            throw new IllegalStateException("Use notifyStateChangedToLoaded");
        }

        notifyItemChangedInSection(section, 0);
    }

    /**
     * Helper method that calls {@link #notifyItemChanged} and {@link #notifyItemInserted} with
     * the position of the {@link State} view holder in the adapter. Useful to be called after
     * changing the State from LOADING/ FAILED/ EMPTY to LOADED.
     *
     * @param tag           unique identifier of the section
     * @param previousState previous state of section
     */
    public void notifyStateChangedToLoaded(String tag, State previousState) {
        Section section = getValidSectionOrThrowException(tag);

        notifyStateChangedToLoaded(section, previousState);
    }

    /**
     * Helper method that calls {@link #notifyItemChanged} and {@link #notifyItemInserted} with
     * the position of the {@link State} view holder in the adapter. Useful to be called after
     * changing the State from LOADING/ FAILED/ EMPTY to LOADED.
     *
     * @param section       a visible section of this adapter
     * @param previousState previous state of section
     */
    public void notifyStateChangedToLoaded(Section section, State previousState) {
        State state = section.getState();

        if (state == previousState) {
            throw new IllegalStateException("No state changed");
        }

        if (state != State.LOADED) {
            if (previousState == State.LOADED) {
                throw new IllegalStateException("Use notifyStateChangedFromLoaded");
            } else {
                throw new IllegalStateException("Use notifyNotLoadedStateChanged");
            }
        }

        int contentItemsTotal = section.getContentItemsTotal();

        if (contentItemsTotal == 0) {
            notifyItemRemovedFromSection(section, 0);
        } else {
            notifyItemChangedInSection(section, 0);

            if (contentItemsTotal > 1) {
                notifyItemRangeInsertedInSection(section, 1, contentItemsTotal - 1);
            }
        }
    }

    /**
     * Helper method that calls {@link #notifyItemRangeRemoved} and {@link #notifyItemChanged} with
     * the position of the {@link State} view holder in the adapter. Useful to be called after
     * changing the State from LOADED to LOADING/ FAILED/ EMPTY.
     *
     * @param tag                       unique identifier of the section
     * @param previousContentItemsCount previous content items count of section
     */
    public void notifyStateChangedFromLoaded(String tag, int previousContentItemsCount) {
        Section section = getValidSectionOrThrowException(tag);

        notifyStateChangedFromLoaded(section, previousContentItemsCount);
    }

    /**
     * Helper method that calls {@link #notifyItemRangeRemoved} and {@link #notifyItemChanged} with
     * the position of the {@link State} view holder in the adapter. Useful to be called after
     * changing the State from LOADED to LOADING/ FAILED/ EMPTY.
     *
     * @param section                   a visible section of this adapter
     * @param previousContentItemsCount previous content items count of section
     */
    public void notifyStateChangedFromLoaded(Section section, int previousContentItemsCount) {
        State state = section.getState();

        if (state == State.LOADED) {
            throw new IllegalStateException("Use notifyStateChangedToLoaded");
        }

        if (previousContentItemsCount == 0) {
            notifyItemInsertedInSection(section, 0);
        } else {
            if (previousContentItemsCount > 1) {
                notifyItemRangeRemovedFromSection(section, 1, previousContentItemsCount - 1);
            }

            notifyItemChangedInSection(section, 0);
        }
    }

    /**
     * Helper method that calls {@link #notifyItemInserted} with the position of the section's
     * header in the adapter. Useful to be called after changing the visibility of the section's
     * header to visible with {@link Section#setHasHeader}.
     *
     * @param tag unique identifier of the section
     */
    public void notifyHeaderInsertedInSection(String tag) {
        Section section = getValidSectionOrThrowException(tag);

        notifyHeaderInsertedInSection(section);
    }

    /**
     * Helper method that calls {@link #notifyItemInserted} with the position of the section's
     * header in the adapter. Useful to be called after changing the visibility of the section's
     * header to visible with {@link Section#setHasHeader}.
     *
     * @param section a visible section of this adapter
     */
    public void notifyHeaderInsertedInSection(Section section) {
        int headerPosition = getHeaderPositionInAdapter(section);

        callSuperNotifyItemInserted(headerPosition);
    }

    /**
     * Helper method that calls {@link #notifyItemInserted} with the position of the section's
     * footer in the adapter. Useful to be called after changing the visibility of the section's
     * footer to visible with {@link Section#setHasFooter}.
     *
     * @param tag unique identifier of the section
     */
    public void notifyFooterInsertedInSection(String tag) {
        Section section = getValidSectionOrThrowException(tag);

        notifyFooterInsertedInSection(section);
    }

    /**
     * Helper method that calls {@link #notifyItemInserted} with the position of the section's
     * footer in the adapter. Useful to be called after changing the visibility of the section's
     * footer to visible with {@link Section#setHasFooter}.
     *
     * @param section a visible section of this adapter
     */
    public void notifyFooterInsertedInSection(Section section) {
        int footerPosition = getFooterPositionInAdapter(section);

        callSuperNotifyItemInserted(footerPosition);
    }

    /**
     * Helper method that calls {@link #notifyItemRemoved} with the position of the section's
     * header in the adapter. Useful to be called after changing the visibility of the section's
     * header to invisible with {@link Section#setHasHeader}.
     *
     * @param tag unique identifier of the section
     */
    public void notifyHeaderRemovedFromSection(String tag) {
        Section section = getValidSectionOrThrowException(tag);

        notifyHeaderRemovedFromSection(section);
    }

    /**
     * Helper method that calls {@link #notifyItemRemoved} with the position of the section's
     * header in the adapter. Useful to be called after changing the visibility of the section's
     * header to invisible with {@link Section#setHasHeader}.
     *
     * @param section a visible section of this adapter
     */
    public void notifyHeaderRemovedFromSection(Section section) {
        int position = getSectionPosition(section);

        callSuperNotifyItemRemoved(position);
    }

    /**
     * Helper method that calls {@link #notifyItemRemoved} with the position of the section's
     * footer in the adapter. Useful to be called after changing the visibility of the section's
     * footer to invisible with {@link Section#setHasFooter}.
     *
     * @param tag unique identifier of the section
     */
    public void notifyFooterRemovedFromSection(String tag) {
        Section section = getValidSectionOrThrowException(tag);

        notifyFooterRemovedFromSection(section);
    }

    /**
     * Helper method that calls {@link #notifyItemRemoved} with the position of the section's
     * footer in the adapter. Useful to be called after changing the visibility of the section's
     * footer to invisible with {@link Section#setHasFooter}.
     *
     * @param section a visible section of this adapter
     */
    public void notifyFooterRemovedFromSection(Section section) {
        int position = getSectionPosition(section) + section.getSectionItemsTotal();

        callSuperNotifyItemRemoved(position);
    }

    /**
     * Helper method that calls {@link #notifyItemRangeInserted} with the position of the section
     * in the adapter. Useful to be called after changing the visibility of the section to visible
     * with {@link Section#setVisible}.
     *
     * @param tag unique identifier of the section
     */
    public void notifySectionChangedToVisible(String tag) {
        Section section = getValidSectionOrThrowException(tag);

        notifySectionChangedToVisible(section);
    }

    /**
     * Helper method that calls {@link #notifyItemRangeInserted} with the position of the section
     * in the adapter. Useful to be called after changing the visibility of the section to visible
     * with {@link Section#setVisible}.
     *
     * @param section a visible section of this adapter
     */
    public void notifySectionChangedToVisible(Section section) {
        if (!section.isVisible()) {
            throw new IllegalStateException("This section is not visible.");
        }

        int sectionPosition = getSectionPosition(section);

        int sectionItemsTotal = section.getSectionItemsTotal();

        callSuperNotifyItemRangeInserted(sectionPosition, sectionItemsTotal);
    }

    /**
     * Helper method that calls {@link #notifyItemRangeInserted} with the position of the section
     * in the adapter. Useful to be called after changing the visibility of the section to invisible
     * with {@link Section#setVisible}.
     *
     * @param tag                     unique identifier of the section
     * @param previousSectionPosition previous section position
     */
    public void notifySectionChangedToInvisible(String tag, int previousSectionPosition) {
        Section section = getValidSectionOrThrowException(tag);

        notifySectionChangedToInvisible(section, previousSectionPosition);
    }

    /**
     * Helper method that calls {@link #notifyItemRangeInserted} with the position of the section
     * in the adapter. Useful to be called after changing the visibility of the section to invisible
     * with {@link Section#setVisible}.
     *
     * @param section                 an invisible section of this adapter
     * @param previousSectionPosition previous section position
     */
    public void notifySectionChangedToInvisible(Section section, int previousSectionPosition) {
        if (section.isVisible()) {
            throw new IllegalStateException("This section is not visible.");
        }

        int sectionItemsTotal = section.getSectionItemsTotal();

        callSuperNotifyItemRangeRemoved(previousSectionPosition, sectionItemsTotal);
    }

    @NonNull
    private Section getValidSectionOrThrowException(String tag) {
        Section section = getSection(tag);

        if (section == null) {
            throw new IllegalArgumentException("Invalid tag: " + tag);
        }

        return section;
    }

    /**
     * A concrete class of an empty ViewHolder.
     * Should be used to avoid the boilerplate of creating a ViewHolder class for simple case
     * scenarios.
     */
    public static class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
