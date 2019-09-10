package io.github.luizgrp.sectionedrecyclerviewadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.apache.commons.collections4.map.ListOrderedMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A custom RecyclerView Adapter that allows {@link Section Sections} to be added to it.
 * Sections are displayed in the same order they were added.
 */
@SuppressWarnings({"WeakerAccess", "PMD.CollapsibleIfStatements"})
public class SectionedRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_HEADER = 0;
    public static final int VIEW_TYPE_FOOTER = 1;
    public static final int VIEW_TYPE_ITEM_LOADED = 2;
    public static final int VIEW_TYPE_LOADING = 3;
    public static final int VIEW_TYPE_FAILED = 4;
    public static final int VIEW_TYPE_EMPTY = 5;

    private final transient ListOrderedMap<String, Section> sections;
    private final transient Map<String, Integer> sectionViewTypeNumbers;
    private final transient Map<Section, SectionAdapter> sectionAdapters;

    private transient int viewTypeCount;
    private static final int VIEW_TYPE_QTY = 6;

    public SectionedRecyclerViewAdapter() {
        super();

        sections = new ListOrderedMap<>();
        sectionViewTypeNumbers = new LinkedHashMap<>();
        sectionAdapters = new HashMap<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        RecyclerView.ViewHolder viewHolder = null;

        for (final Map.Entry<String, Integer> entry : sectionViewTypeNumbers.entrySet()) {
            if (viewType >= entry.getValue() && viewType < entry.getValue() + VIEW_TYPE_QTY) {

                final Section section = sections.get(entry.getKey());
                final int sectionViewType = viewType - entry.getValue();

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

    private RecyclerView.ViewHolder getItemViewHolder(final ViewGroup parent, final Section section) {
        View view;
        if (section.isItemViewWillBeProvided()) {
            view = section.getItemView(parent);
            if (view == null) {
                throw new NullPointerException("Section.getItemView() returned null");
            }
        } else {
            final Integer resId = section.getItemResourceId();
            if (resId == null) {
                throw new NullPointerException("Missing 'item' resource id");
            }
            view = inflate(resId, parent);
        }
        return section.getItemViewHolder(view);
    }

    private RecyclerView.ViewHolder getHeaderViewHolder(final ViewGroup parent, final Section section) {
        View view;
        if (section.isHeaderViewWillBeProvided()) {
            view = section.getHeaderView(parent);
            if (view == null) {
                throw new NullPointerException("Section.getHeaderView() returned null");
            }
        } else {
            final Integer resId = section.getHeaderResourceId();
            if (resId == null) {
                throw new NullPointerException("Missing 'header' resource id");
            }
            view = inflate(resId, parent);
        }
        return section.getHeaderViewHolder(view);
    }

    private RecyclerView.ViewHolder getFooterViewHolder(final ViewGroup parent, final Section section) {
        View view;
        if (section.isFooterViewWillBeProvided()) {
            view = section.getFooterView(parent);
            if (view == null) {
                throw new NullPointerException("Section.getFooterView() returned null");
            }
        } else {
            final Integer resId = section.getFooterResourceId();
            if (resId == null) {
                throw new NullPointerException("Missing 'footer' resource id");
            }
            view = inflate(resId, parent);
        }
        return section.getFooterViewHolder(view);
    }

    private RecyclerView.ViewHolder getLoadingViewHolder(final ViewGroup parent, final Section section) {
        View view;
        if (section.isLoadingViewWillBeProvided()) {
            view = section.getLoadingView(parent);
            if (view == null) {
                throw new NullPointerException("Section.getLoadingView() returned null");
            }
        } else {
            final Integer resId = section.getLoadingResourceId();
            if (resId == null) {
                throw new NullPointerException("Missing 'loading' resource id");
            }
            view = inflate(resId, parent);
        }
        return section.getLoadingViewHolder(view);
    }

    private RecyclerView.ViewHolder getFailedViewHolder(final ViewGroup parent, final Section section) {
        View view;
        if (section.isFailedViewWillBeProvided()) {
            view = section.getFailedView(parent);
            if (view == null) {
                throw new NullPointerException("Section.getFailedView() returned null");
            }
        } else {
            final Integer resId = section.getFailedResourceId();
            if (resId == null) {
                throw new NullPointerException("Missing 'failed' resource id");
            }
            view = inflate(resId, parent);
        }
        return section.getFailedViewHolder(view);
    }

    private RecyclerView.ViewHolder getEmptyViewHolder(final ViewGroup parent, final Section section) {
        View view;
        if (section.isEmptyViewWillBeProvided()) {
            view = section.getEmptyView(parent);
            if (view == null) {
                throw new NullPointerException("Section.getEmptyView() returned null");
            }
        } else {
            final Integer resId = section.getEmptyResourceId();
            if (resId == null) {
                throw new NullPointerException("Missing 'empty' resource id");
            }
            view = inflate(resId, parent);
        }
        return section.getEmptyViewHolder(view);
    }

    /**
     * Add a section to this recyclerview with a random tag.
     *
     * @param section section to be added
     * @return generated tag
     */
    public String addSection(final Section section) {
        final String tag = generateSectionTag();

        addSection(tag, section);

        return tag;
    }

    /**
     * Add a section to this recyclerview.
     *
     * @param tag     unique identifier of the section
     * @param section section to be added
     */
    public void addSection(final String tag, final Section section) {
        addSection(this.sections.size(), tag, section);
    }

    /**
     * Add a section to this recyclerview at the specific index.
     *
     * @param index   the index at which the section should be inserted
     * @param tag     unique identifier of the section
     * @param section section should be added
     */
    public void addSection(final int index, final String tag, final Section section) {
        this.sections.put(index, tag, section);
        addSectionViewTypeNumbers(tag);
        sectionAdapters.put(section, new SectionAdapter(this, section));
    }

    /**
     * Add a section at the specific position to this recyclerview with a random tag.
     *
     * @param index   the index at which the section should be inserted
     * @param section section should be added
     * @return generated tag
     */
    public String addSection(final int index, final Section section) {
        final String tag = generateSectionTag();

        addSection(index, tag, section);

        return tag;
    }

    private String generateSectionTag() {
        return UUID.randomUUID().toString();
    }

    private void addSectionViewTypeNumbers(final String tag) {
        this.sectionViewTypeNumbers.put(tag, viewTypeCount);
        viewTypeCount += VIEW_TYPE_QTY;
    }

    /**
     * Return the section with the tag provided.
     *
     * @param tag unique identifier of the section
     * @return section
     */
    public Section getSection(final String tag) {
        return this.sections.get(tag);
    }

    /**
     * Remove section from this recyclerview.
     *
     * @param section section to be removed
     */
    public void removeSection(final Section section) {
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
    public void removeSection(final String tag) {
        final Section section = this.sections.remove(tag);
        this.sectionViewTypeNumbers.remove(tag);
        this.sectionAdapters.remove(section);
    }

    /**
     * Remove all sections from this recyclerview.
     */
    public void removeAllSections() {
        this.sections.clear();
        this.sectionViewTypeNumbers.clear();
        this.sectionAdapters.clear();
        this.viewTypeCount = 0;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        internalOnBindViewHolder(holder, position, null);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position,
                                 @NonNull final List<Object> payloads) {
        if (payloads.isEmpty()) {
            // empty list requires full update as per documentation
            super.onBindViewHolder(holder, position, payloads);
            return;
        }

        internalOnBindViewHolder(holder, position, payloads);
    }

    private void internalOnBindViewHolder(@NonNull final RecyclerView.ViewHolder holder,
                                          final int position, final List<Object> payloads) {

        int currentPos = 0;

        for (final Map.Entry<String, Section> entry : sections.entrySet()) {
            final Section section = entry.getValue();

            // ignore invisible sections
            if (!section.isVisible()) {
                continue;
            }

            final int sectionTotal = section.getSectionItemsTotal();

            // check if position is in this section
            if (position >= currentPos && position <= (currentPos + sectionTotal - 1)) {

                if (section.hasHeader()) {
                    if (position == currentPos) {
                        // delegate the binding to the section header
                        if (payloads == null) {
                            getSectionForPosition(position).onBindHeaderViewHolder(holder);
                        } else {
                            getSectionForPosition(position).onBindHeaderViewHolder(holder, payloads);
                        }
                        return;
                    }
                }

                if (section.hasFooter()) {
                    if (position == currentPos + sectionTotal - 1) {
                        // delegate the binding to the section header
                        if (payloads == null) {
                            getSectionForPosition(position).onBindFooterViewHolder(holder);
                        } else {
                            getSectionForPosition(position).onBindFooterViewHolder(holder, payloads);
                        }
                        return;
                    }
                }

                // delegate the binding to the section content
                onBindContentViewHolder(getSectionForPosition(position), holder, position, payloads);
                return;
            }

            currentPos += sectionTotal;
        }

        throw new IndexOutOfBoundsException("Invalid position");
    }

    private void onBindContentViewHolder(@NonNull final Section section,
                                         @NonNull final RecyclerView.ViewHolder holder,
                                         final int position,
                                         final List<Object> payloads) {
        switch (section.getState()) {
            case LOADING:
                if (payloads == null) {
                    section.onBindLoadingViewHolder(holder);
                } else {
                    section.onBindLoadingViewHolder(holder, payloads);
                }
                break;
            case LOADED:
                if (payloads == null) {
                    section.onBindItemViewHolder(holder, getPositionInSection(position));
                } else {
                    section.onBindItemViewHolder(holder, getPositionInSection(position), payloads);
                }
                break;
            case FAILED:
                if (payloads == null) {
                    section.onBindFailedViewHolder(holder);
                } else {
                    section.onBindFailedViewHolder(holder, payloads);
                }
                break;
            case EMPTY:
                if (payloads == null) {
                    section.onBindEmptyViewHolder(holder);
                } else {
                    section.onBindEmptyViewHolder(holder, payloads);
                }
                break;
            default:
                throw new IllegalStateException("Invalid state");
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;

        for (final Map.Entry<String, Section> entry : sections.entrySet()) {
            final Section section = entry.getValue();

            // ignore invisible sections
            if (!section.isVisible()) {
                continue;
            }

            count += section.getSectionItemsTotal();
        }

        return count;
    }

    @Override
    public int getItemViewType(final int position) {
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

        for (final Map.Entry<String, Section> entry : sections.entrySet()) {
            final Section section = entry.getValue();

            // ignore invisible sections
            if (!section.isVisible()) {
                continue;
            }

            final int sectionTotal = section.getSectionItemsTotal();

            // check if position is in this section
            if (position >= currentPos && position <= (currentPos + sectionTotal - 1)) {

                final int viewType = sectionViewTypeNumbers.get(entry.getKey());

                if (section.hasHeader()) {
                    if (position == currentPos) {
                        return viewType;
                    }
                }

                if (section.hasFooter()) {
                    if (position == currentPos + sectionTotal - 1) {
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
     * Calculates the section adapter item view type from a view type from the adapter.
     *
     * @param itemViewType adapter view type
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
    public int getSectionItemViewTypeForAdapterViewType(final int itemViewType) {
        return itemViewType % VIEW_TYPE_QTY;
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
    public int getSectionItemViewType(final int position) {
        final int viewType = getItemViewType(position);

        return getSectionItemViewTypeForAdapterViewType(viewType);
    }

    /**
     * Returns the Section object for a position in the adapter.
     *
     * @param position position in the adapter
     * @return Section object for that position
     */
    public Section getSectionForPosition(final int position) {

        int currentPos = 0;

        for (final Map.Entry<String, Section> entry : sections.entrySet()) {
            final Section section = entry.getValue();

            // ignore invisible sections
            if (!section.isVisible()) {
                continue;
            }

            final int sectionTotal = section.getSectionItemsTotal();

            // check if position is in this section
            if (position >= currentPos && position <= (currentPos + sectionTotal - 1)) {
                return section;
            }

            currentPos += sectionTotal;
        }

        throw new IndexOutOfBoundsException("Invalid position");
    }

    /**
     * Return the item position relative to the section.
     *
     * @param position position of the item in the adapter
     * @return position of the item in the section
     */
    public int getPositionInSection(final int position) {
        int currentPos = 0;

        for (final Map.Entry<String, Section> entry : sections.entrySet()) {
            final Section section = entry.getValue();

            // ignore invisible sections
            if (!section.isVisible()) {
                continue;
            }

            final int sectionTotal = section.getSectionItemsTotal();

            // check if position is in this section
            if (position >= currentPos && position <= (currentPos + sectionTotal - 1)) {
                return position - currentPos - (section.hasHeader() ? 1 : 0);
            }

            currentPos += sectionTotal;
        }

        throw new IndexOutOfBoundsException("Invalid position");
    }

    /**
     * Return the map with all sections.
     * Should only be used by the library itself.
     *
     * @return map with all sections
     */
    @SuppressWarnings("PMD.DefaultPackage")
    /* default */ ListOrderedMap<String, Section> getSections() {
        return sections;
    }

    /**
     * Return a copy of the map with all sections of this adapter.
     *
     * @return a copy of the map with all sections
     */
    @NonNull
    public Map<String, Section> getCopyOfSectionsMap() {
        return ListOrderedMap.listOrderedMap(sections);
    }

    /**
     * Return the number of sections of this adapter.
     *
     * @return number of sections
     */
    public int getSectionCount() {
        return sections.size();
    }

    /**
     * Return the Section at the provided index in the adapter.
     *
     * @param index index in the adapter
     * @return Section at the provided index
     */
    public Section getSection(final int index) {
        return sections.getValue(index);
    }

    /**
     * Returns the index of the first occurrence of the specified section in this adapter, or -1 if
     * this adapter does not contain the section. Note that, the visibility of section is being
     * ignored.
     *
     * @param section section to search for
     * @return the index of the first occurrence of the specified section in this adapter, or -1 if
     * this adapter does not contain the section
     */
    public int getSectionIndex(final Section section) {
        int index = 0;

        for (final Map.Entry<String, Section> entry : this.sections.entrySet()) {
            if (entry.getValue() == section) {
                return index;
            }
            index++;
        }

        return -1;
    }

    public SectionAdapter getAdapterForSection(final String tag) {
        final Section section = getValidSectionOrThrowException(tag);

        return getAdapterForSection(section);
    }

    public SectionAdapter getAdapterForSection(final Section section) {
        final SectionAdapter sectionAdapter = sectionAdapters.get(section);

        if (sectionAdapter == null) {
            throw new IllegalArgumentException("Invalid section");
        }

        return sectionAdapter;
    }

    // in order to allow this class to be unit tested
    @VisibleForTesting
    public View inflate(@LayoutRes final int layoutResourceId, final ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutResourceId, parent, false);
    }

    @NonNull
    private Section getValidSectionOrThrowException(final String tag) {
        final Section section = getSection(tag);

        if (section == null) {
            throw new IllegalArgumentException("Invalid tag: " + tag);
        }

        return section;
    }
}
