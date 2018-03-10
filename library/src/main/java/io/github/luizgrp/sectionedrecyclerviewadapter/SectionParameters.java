package io.github.luizgrp.sectionedrecyclerviewadapter;

import android.support.annotation.LayoutRes;

/**
 * Class used as constructor parameters of {@link Section}.
 */
@SuppressWarnings({"WeakerAccess", "PMD.AvoidFieldNameMatchingMethodName"})
public final class SectionParameters {
    @LayoutRes
    public final Integer itemResourceId;
    @LayoutRes
    public final Integer headerResourceId;
    @LayoutRes
    public final Integer footerResourceId;
    @LayoutRes
    public final Integer loadingResourceId;
    @LayoutRes
    public final Integer failedResourceId;
    @LayoutRes
    public final Integer emptyResourceId;
    public final boolean itemViewWillBeProvided;
    public final boolean headerViewWillBeProvided;
    public final boolean footerViewWillBeProvided;
    public final boolean loadingViewWillBeProvided;
    public final boolean failedViewWillBeProvided;
    public final boolean emptyViewWillBeProvided;

    private SectionParameters(Builder builder) {
        this.itemResourceId = builder.itemResourceId;
        this.headerResourceId = builder.headerResourceId;
        this.footerResourceId = builder.footerResourceId;
        this.loadingResourceId = builder.loadingResourceId;
        this.failedResourceId = builder.failedResourceId;
        this.emptyResourceId = builder.emptyResourceId;
        this.itemViewWillBeProvided = builder.itemViewWillBeProvided;
        this.headerViewWillBeProvided = builder.headerViewWillBeProvided;
        this.footerViewWillBeProvided = builder.footerViewWillBeProvided;
        this.loadingViewWillBeProvided = builder.loadingViewWillBeProvided;
        this.failedViewWillBeProvided = builder.failedViewWillBeProvided;
        this.emptyViewWillBeProvided = builder.emptyViewWillBeProvided;

        if (itemResourceId != null && itemViewWillBeProvided) {
            throw new IllegalArgumentException(
                    "itemResourceId and itemViewWillBeProvided cannot both be set");
        } else if (itemResourceId == null && !itemViewWillBeProvided) {
            throw new IllegalArgumentException(
                    "Either itemResourceId or itemViewWillBeProvided must be set");
        }
        if (headerResourceId != null && headerViewWillBeProvided) {
            throw new IllegalArgumentException(
                    "headerResourceId and headerViewWillBeProvided cannot both be set");
        }
        if (footerResourceId != null && footerViewWillBeProvided) {
            throw new IllegalArgumentException(
                    "footerResourceId and footerViewWillBeProvided cannot both be set");
        }
        if (loadingResourceId != null && loadingViewWillBeProvided) {
            throw new IllegalArgumentException(
                    "loadingResourceId and loadingViewWillBeProvided cannot both be set");
        }
        if (failedResourceId != null && failedViewWillBeProvided) {
            throw new IllegalArgumentException(
                    "failedResourceId and failedViewWillBeProvided cannot both be set");
        }
        if (emptyResourceId != null && emptyViewWillBeProvided) {
            throw new IllegalArgumentException(
                    "emptyResourceId and emptyViewWillBeProvided cannot both be set");
        }
    }

    /**
     * Builder static factory method with mandatory parameters of {@link Section} (namely none).
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder of {@link SectionParameters}.
     */
    public static class Builder {
        @LayoutRes
        private Integer itemResourceId;
        @LayoutRes
        private Integer headerResourceId;
        @LayoutRes
        private Integer footerResourceId;
        @LayoutRes
        private Integer loadingResourceId;
        @LayoutRes
        private Integer failedResourceId;
        @LayoutRes
        private Integer emptyResourceId;
        private boolean itemViewWillBeProvided;
        private boolean headerViewWillBeProvided;
        private boolean footerViewWillBeProvided;
        private boolean loadingViewWillBeProvided;
        private boolean failedViewWillBeProvided;
        private boolean emptyViewWillBeProvided;

        /**
         * Constructor with mandatory parameters of {@link Section} (namely none).
         */
        private Builder() {
        }

        /**
         * Constructor with optional parameter for backward compatibility purposes.
         *
         * @param itemResourceId layout resource for Section's items
         *
         * @deprecated Use {@link #SectionParameters#builder} instead.
         */
        @Deprecated
        public Builder(@LayoutRes int itemResourceId) {
            this.itemResourceId = itemResourceId;
        }

        /**
         * Set layout resource for Section's items.
         *
         * @param itemResourceId layout resource for Section's items
         * @return this builder
         */
        public Builder itemResourceId(@LayoutRes int itemResourceId) {
            this.itemResourceId = itemResourceId;

            return this;
        }

        /**
         * Declare that there will be no {@link #itemResourceId}, as the view will be provided by
         * the Section's implementation directly.
         *
         * @return this builder
         */
        public Builder itemViewWillBeProvided() {
            itemViewWillBeProvided = true;

            return this;
        }

        /**
         * Set layout resource for Section's header.
         *
         * @param headerResourceId layout resource for Section's header
         * @return this builder
         */
        public Builder headerResourceId(@LayoutRes int headerResourceId) {
            this.headerResourceId = headerResourceId;

            return this;
        }

        /**
         * Declare that there will be no {@link #headerResourceId}, as the view will be provided by
         * the Section's implementation directly.
         *
         * @return this builder
         */
        public Builder headerViewWillBeProvided() {
            headerViewWillBeProvided = true;

            return this;
        }

        /**
         * Set layout resource for Section's footer.
         *
         * @param footerResourceId layout resource for Section's footer
         * @return this builder
         */
        public Builder footerResourceId(@LayoutRes int footerResourceId) {
            this.footerResourceId = footerResourceId;

            return this;
        }

        /**
         * Declare that there will be no {@link #footerResourceId}, as the view will be provided by
         * the Section's implementation directly.
         *
         * @return this builder
         */
        public Builder footerViewWillBeProvided() {
            footerViewWillBeProvided = true;

            return this;
        }

        /**
         * Set layout resource for Section's loading state.
         *
         * @param loadingResourceId layout resource for Section's loading state
         * @return this builder
         */
        public Builder loadingResourceId(@LayoutRes int loadingResourceId) {
            this.loadingResourceId = loadingResourceId;

            return this;
        }

        /**
         * Declare that there will be no {@link #loadingResourceId}, as the view will be provided by
         * the Section's implementation directly.
         *
         * @return this builder
         */
        public Builder loadingViewWillBeProvided() {
            loadingViewWillBeProvided = true;

            return this;
        }

        /**
         * Set layout resource for Section's failed state.
         *
         * @param failedResourceId layout resource for Section's failed state
         * @return this builder
         */
        public Builder failedResourceId(@LayoutRes int failedResourceId) {
            this.failedResourceId = failedResourceId;

            return this;
        }

        /**
         * Declare that there will be no {@link #failedResourceId}, as the view will be provided by
         * the Section's implementation directly.
         *
         * @return this builder
         */
        public Builder failedViewWillBeProvided() {
            failedViewWillBeProvided = true;

            return this;
        }

        /**
         * Set layout resource for Section's empty state.
         *
         * @param emptyResourceId layout resource for Section's empty state
         * @return this builder
         */
        public Builder emptyResourceId(@LayoutRes int emptyResourceId) {
            this.emptyResourceId = emptyResourceId;

            return this;
        }

        /**
         * Declare that there will be no {@link #emptyResourceId}, as the view will be provided by
         * the Section's implementation directly.
         *
         * @return this builder
         */
        public Builder emptyViewWillBeProvided() {
            emptyViewWillBeProvided = true;

            return this;
        }

        /**
         * Build an instance of SectionParameters.
         *
         * @return an instance of SectionParameters
         */
        public SectionParameters build() {
            return new SectionParameters(this);
        }
    }
}
