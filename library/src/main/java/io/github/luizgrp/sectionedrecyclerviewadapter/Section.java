package io.github.luizgrp.sectionedrecyclerviewadapter;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Abstract Section to be used with {@link SectionedRecyclerViewAdapter}.
 */
@SuppressWarnings("WeakerAccess")
public abstract class Section {

    public enum State {
        LOADING,
        LOADED,
        FAILED,
        EMPTY
    }

    private State state = State.LOADED;

    private boolean visible = true;

    @SuppressWarnings({"PMD.BeanMembersShouldSerialize", "PMD.AvoidFieldNameMatchingMethodName"})
    private boolean hasHeader;
    @SuppressWarnings({"PMD.BeanMembersShouldSerialize", "PMD.AvoidFieldNameMatchingMethodName"})
    private boolean hasFooter;

    @LayoutRes
    private final Integer itemResourceId;
    @LayoutRes
    private final Integer headerResourceId;
    @LayoutRes
    private final Integer footerResourceId;
    @LayoutRes
    private final Integer loadingResourceId;
    @LayoutRes
    private final Integer failedResourceId;
    @LayoutRes
    private final Integer emptyResourceId;
    private final boolean itemViewWillBeProvided;
    private final boolean headerViewWillBeProvided;
    private final boolean footerViewWillBeProvided;
    private final boolean loadingViewWillBeProvided;
    private final boolean failedViewWillBeProvided;
    private final boolean emptyViewWillBeProvided;

    /**
     * Create a Section object based on {@link SectionParameters}.
     *
     * @param sectionParameters section parameters
     */
    public Section(final SectionParameters sectionParameters) {
        this.itemResourceId = sectionParameters.itemResourceId;
        this.headerResourceId = sectionParameters.headerResourceId;
        this.footerResourceId = sectionParameters.footerResourceId;
        this.loadingResourceId = sectionParameters.loadingResourceId;
        this.failedResourceId = sectionParameters.failedResourceId;
        this.emptyResourceId = sectionParameters.emptyResourceId;
        this.itemViewWillBeProvided = sectionParameters.itemViewWillBeProvided;
        this.headerViewWillBeProvided = sectionParameters.headerViewWillBeProvided;
        this.footerViewWillBeProvided = sectionParameters.footerViewWillBeProvided;
        this.loadingViewWillBeProvided = sectionParameters.loadingViewWillBeProvided;
        this.failedViewWillBeProvided = sectionParameters.failedViewWillBeProvided;
        this.emptyViewWillBeProvided = sectionParameters.emptyViewWillBeProvided;

        this.hasHeader = this.headerResourceId != null || this.headerViewWillBeProvided;
        this.hasFooter = this.footerResourceId != null || this.footerViewWillBeProvided;
    }

    /**
     * Set the State of this Section.
     *
     * @param state state of this section
     */
    public final void setState(final State state) {
        switch (state) {
            case LOADING:
                if (loadingResourceId == null && !loadingViewWillBeProvided) {
                    throw new IllegalStateException(
                            "Resource id for 'loading state' should be provided or 'loadingViewWillBeProvided' should be set");
                }
                break;
            case FAILED:
                if (failedResourceId == null && !failedViewWillBeProvided) {
                    throw new IllegalStateException("Resource id for 'failed state' should be provided or 'failedViewWillBeProvided' should be set");
                }
                break;
            case EMPTY:
                if (emptyResourceId == null && !emptyViewWillBeProvided) {
                    throw new IllegalStateException("Resource id for 'empty state' should be provided or 'emptyViewWillBeProvided' should be set");
                }
                break;
            default:
                break;
        }

        this.state = state;
    }

    /**
     * Return the current State of this Section.
     *
     * @return current state of this section
     */
    public final State getState() {
        return state;
    }

    /**
     * Check if this Section is visible.
     *
     * @return true if this Section is visible
     */
    public final boolean isVisible() {
        return visible;
    }

    /**
     * Set if this Section is visible.
     *
     * @param visible true if this Section is visible
     */
    public final void setVisible(final boolean visible) {
        this.visible = visible;
    }

    /**
     * Check if this Section has a header.
     *
     * @return true if this Section has a header
     */
    public final boolean hasHeader() {
        return hasHeader;
    }

    /**
     * Set if this Section has header.
     *
     * @param hasHeader true if this Section has a header
     */
    public final void setHasHeader(final boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    /**
     * Check if this Section has a footer.
     *
     * @return true if this Section has a footer
     */
    public final boolean hasFooter() {
        return hasFooter;
    }

    /**
     * Set if this Section has footer.
     *
     * @param hasFooter true if this Section has a footer
     */
    public final void setHasFooter(final boolean hasFooter) {
        this.hasFooter = hasFooter;
    }

    /**
     * Return whether the item view is provided through {@link #getItemView(ViewGroup)}.
     * If false, the item view is inflated using the resource from {@link #getItemResourceId()}.
     *
     * @return whether the item view is provided through {@link #getItemView(ViewGroup)}.
     */
    public final boolean isItemViewWillBeProvided() {
        return itemViewWillBeProvided;
    }

    /**
     * Return the layout resource id of the item.
     *
     * @return layout resource id of the item
     */
    public final Integer getItemResourceId() {
        return itemResourceId;
    }

    /**
     * Return whether the header view is provided through {@link #getHeaderView(ViewGroup)}.
     * If false, the header view is inflated using the resource from
     * {@link #getHeaderResourceId()}.
     *
     * @return whether the header view is provided through {@link #getHeaderView(ViewGroup)}.
     */
    public final boolean isHeaderViewWillBeProvided() {
        return headerViewWillBeProvided;
    }

    /**
     * Return the layout resource id of the header.
     *
     * @return layout resource id of the header
     */
    public final Integer getHeaderResourceId() {
        return headerResourceId;
    }

    /**
     * Return whether the footer view is provided through {@link #getFooterView(ViewGroup)}.
     * If false, the footer view is inflated using the resource from
     * {@link #getFooterResourceId()}.
     *
     * @return whether the footer view is provided through {@link #getFooterView(ViewGroup)}.
     */
    public final boolean isFooterViewWillBeProvided() {
        return footerViewWillBeProvided;
    }

    /**
     * Return the layout resource id of the footer.
     *
     * @return layout resource id of the footer
     */
    public final Integer getFooterResourceId() {
        return footerResourceId;
    }

    /**
     * Return whether the loading view is provided through {@link #getLoadingView(ViewGroup)}.
     * If false, the loading view is inflated using the resource from
     * {@link #getLoadingResourceId()}.
     *
     * @return whether the loading view is provided through {@link #getLoadingView(ViewGroup)}.
     */
    public final boolean isLoadingViewWillBeProvided() {
        return loadingViewWillBeProvided;
    }

    /**
     * Return the layout resource id of the loading view.
     *
     * @return layout resource id of the loading view
     */
    public final Integer getLoadingResourceId() {
        return loadingResourceId;
    }

    /**
     * Return whether the failed view is provided through {@link #getFailedView(ViewGroup)}.
     * If false, the failed view is inflated using the resource from
     * {@link #getFailedResourceId()}.
     *
     * @return whether the failed view is provided through {@link #getFailedView(ViewGroup)}.
     */
    public final boolean isFailedViewWillBeProvided() {
        return failedViewWillBeProvided;
    }

    /**
     * Return the layout resource id of the failed view.
     *
     * @return layout resource id of the failed view
     */
    public final Integer getFailedResourceId() {
        return failedResourceId;
    }

    /**
     * Return whether the empty view is provided through {@link #getEmptyView(ViewGroup)}.
     * If false, the empty view is inflated using the resource from
     * {@link #getEmptyResourceId()}.
     *
     * @return whether the empty view is provided through {@link #getEmptyView(ViewGroup)}.
     */
    public final boolean isEmptyViewWillBeProvided() {
        return emptyViewWillBeProvided;
    }

    /**
     * Return the layout resource id of the empty view.
     *
     * @return layout resource id of the empty view
     */
    public final Integer getEmptyResourceId() {
        return emptyResourceId;
    }

    /**
     * Bind the data to the ViewHolder for the Content of this Section, that can be the Items,
     * Loading view or Failed view, depending on the current state of the section.
     *
     * @param holder   ViewHolder for the Content of this Section
     * @param position position of the item in the Section, not in the RecyclerView
     */
    public final void onBindContentViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch (state) {
            case LOADING:
                onBindLoadingViewHolder(holder);
                break;
            case LOADED:
                onBindItemViewHolder(holder, position);
                break;
            case FAILED:
                onBindFailedViewHolder(holder);
                break;
            case EMPTY:
                onBindEmptyViewHolder(holder);
                break;
            default:
                throw new IllegalStateException("Invalid state");
        }
    }

    /**
     * Return the total of items of this Section, including content items (according to the section
     * state) plus header and footer.
     *
     * @return total of items of this section
     */
    public final int getSectionItemsTotal() {
        int contentItemsTotal;

        switch (state) {
            case LOADING:
                contentItemsTotal = 1;
                break;
            case LOADED:
                contentItemsTotal = getContentItemsTotal();
                break;
            case FAILED:
                contentItemsTotal = 1;
                break;
            case EMPTY:
                contentItemsTotal = 1;
                break;
            default:
                throw new IllegalStateException("Invalid state");
        }

        return contentItemsTotal + (hasHeader ? 1 : 0) + (hasFooter ? 1 : 0);
    }

    /**
     * Return the total of items of this Section.
     *
     * @return total of items of this Section
     */
    public abstract int getContentItemsTotal();

    /**
     * Creates the View for a single Item. This must be implemented if and only if
     * {@link #isItemViewWillBeProvided()} is true.
     *
     * @param parent The parent view. Note that there is no need to attach the new view.
     * @return View for an Item of this Section.
     */
    public View getItemView(@SuppressWarnings("unused") final ViewGroup parent) {
        throw new UnsupportedOperationException(
                "You need to implement getItemView() if you set itemViewWillBeProvided");
    }

    /**
     * Return the ViewHolder for a single Item of this Section.
     *
     * @param view View created by getItemView or inflated resource returned by getItemResourceId
     * @return ViewHolder for the Item of this Section
     */
    public abstract RecyclerView.ViewHolder getItemViewHolder(View view);

    /**
     * Bind the data to the ViewHolder for an Item of this Section.
     *
     * @param holder   ViewHolder for the Item of this Section
     * @param position position of the item in the Section, not in the RecyclerView
     */
    public abstract void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position);

    /**
     * Creates the View for the Header. This must be implemented if and only if
     * {@link #isHeaderViewWillBeProvided()} is true.
     *
     * @param parent The parent view. Note that there is no need to attach the new view.
     * @return View for the Header of this Section.
     */
    public View getHeaderView(@SuppressWarnings("unused") final ViewGroup parent) {
        throw new UnsupportedOperationException(
                "You need to implement getHeaderView() if you set headerViewWillBeProvided");
    }

    /**
     * Return the ViewHolder for the Header of this Section.
     *
     * @param view View inflated by resource returned by getHeaderResourceId
     * @return ViewHolder for the Header of this Section
     */
    public RecyclerView.ViewHolder getHeaderViewHolder(final View view) {
        return new SectionedRecyclerViewAdapter.EmptyViewHolder(view);
    }

    /**
     * Bind the data to the ViewHolder for the Header of this Section.
     *
     * @param holder ViewHolder for the Header of this Section
     */
    @SuppressWarnings("PMD.EmptyMethodInAbstractClassShouldBeAbstract")
    public void onBindHeaderViewHolder(final RecyclerView.ViewHolder holder) {
        // Nothing to bind here.
    }

    /**
     * Creates the View for the Footer. This must be implemented if and only if
     * {@link #isFooterViewWillBeProvided()} is true.
     *
     * @param parent The parent view. Note that there is no need to attach the new view.
     * @return View for the Footer of this Section.
     */
    public View getFooterView(@SuppressWarnings("unused") final ViewGroup parent) {
        throw new UnsupportedOperationException(
                "You need to implement getFooterView() if you set footerViewWillBeProvided");
    }

    /**
     * Return the ViewHolder for the Footer of this Section.
     *
     * @param view View inflated by resource returned by getFooterResourceId
     * @return ViewHolder for the Footer of this Section
     */
    public RecyclerView.ViewHolder getFooterViewHolder(final View view) {
        return new SectionedRecyclerViewAdapter.EmptyViewHolder(view);
    }

    /**
     * Bind the data to the ViewHolder for the Footer of this Section.
     *
     * @param holder ViewHolder for the Footer of this Section
     */
    @SuppressWarnings("PMD.EmptyMethodInAbstractClassShouldBeAbstract")
    public void onBindFooterViewHolder(final RecyclerView.ViewHolder holder) {
        // Nothing to bind here.
    }

    /**
     * Creates the View for the Loading state. This must be implemented if and only if
     * {@link #isLoadingViewWillBeProvided()} is true.
     *
     * @param parent The parent view. Note that there is no need to attach the new view.
     * @return View for the Loading state of this Section.
     */
    public View getLoadingView(@SuppressWarnings("unused") final ViewGroup parent) {
        throw new UnsupportedOperationException(
                "You need to implement getLoadingView() if you set loadingViewWillBeProvided");
    }

    /**
     * Return the ViewHolder for the Loading state of this Section.
     *
     * @param view View inflated by resource returned by getItemResourceId
     * @return ViewHolder for the Loading state of this Section
     */
    public RecyclerView.ViewHolder getLoadingViewHolder(final View view) {
        return new SectionedRecyclerViewAdapter.EmptyViewHolder(view);
    }

    /**
     * Bind the data to the ViewHolder for Loading state of this Section.
     *
     * @param holder ViewHolder for the Loading state of this Section
     */
    @SuppressWarnings({"EmptyMethod", "PMD.EmptyMethodInAbstractClassShouldBeAbstract"})
    public void onBindLoadingViewHolder(final RecyclerView.ViewHolder holder) {
        // Nothing to bind here.
    }

    /**
     * Creates the View for the Failed state. This must be implemented if and only if
     * {@link #isFailedViewWillBeProvided()} is true.
     *
     * @param parent The parent view. Note that there is no need to attach the new view.
     * @return View for the Failed state of this Section.
     */
    public View getFailedView(@SuppressWarnings("unused") final ViewGroup parent) {
        throw new UnsupportedOperationException(
                "You need to implement getFailedView() if you set failedViewWillBeProvided");
    }

    /**
     * Return the ViewHolder for the Failed state of this Section.
     *
     * @param view View inflated by resource returned by getItemResourceId
     * @return ViewHolder for the Failed of this Section
     */
    public RecyclerView.ViewHolder getFailedViewHolder(final View view) {
        return new SectionedRecyclerViewAdapter.EmptyViewHolder(view);
    }

    /**
     * Bind the data to the ViewHolder for the Failed state of this Section.
     *
     * @param holder ViewHolder for the Failed state of this Section
     */
    @SuppressWarnings({"EmptyMethod", "PMD.EmptyMethodInAbstractClassShouldBeAbstract"})
    public void onBindFailedViewHolder(final RecyclerView.ViewHolder holder) {
        // Nothing to bind here.
    }

    /**
     * Creates the View for the Empty state. This must be implemented if and only if
     * {@link #isEmptyViewWillBeProvided()} is true.
     *
     * @param parent The parent view. Note that there is no need to attach the new view.
     * @return View for the Empty state of this Section.
     */
    public View getEmptyView(@SuppressWarnings("unused") final ViewGroup parent) {
        throw new UnsupportedOperationException(
                "You need to implement getEmptyView() if you set emptyViewWillBeProvided");
    }

    /**
     * Return the ViewHolder for the Empty state of this Section.
     *
     * @param view View inflated by resource returned by getItemResourceId
     * @return ViewHolder for the Empty of this Section
     */
    public RecyclerView.ViewHolder getEmptyViewHolder(final View view) {
        return new SectionedRecyclerViewAdapter.EmptyViewHolder(view);
    }

    /**
     * Bind the data to the ViewHolder for the Empty state of this Section.
     *
     * @param holder ViewHolder for the Empty state of this Section
     */
    @SuppressWarnings({"EmptyMethod", "PMD.EmptyMethodInAbstractClassShouldBeAbstract"})
    public void onBindEmptyViewHolder(final RecyclerView.ViewHolder holder) {
        // Nothing to bind here.
    }
}
