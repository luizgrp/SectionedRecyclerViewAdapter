package io.github.luizgrp.sectionedrecyclerviewadapter;

/**
 * POJO which acts as constructor parameters of {@link Section Sections}.
 */
public class SectionParameters {
    public final int headerResourceId;
    public final int footerResourceId;
    public final int itemResourceId;
    public final int loadingResourceId;
    public final int failedResourceId;

    /**
     * Builder pattern mentioned by Joshua Bloch in Effective Java, 2nd Edition
     */
    public static class Builder {
        private final int itemResourceId;

        private int headerResourceId;
        private int footerResourceId;
        private int loadingResourceId;
        private int failedResourceId;

        /**
         * Create a Builder object to construct SectionParameters
         * @param itemResourceId layout resource for Section's items
         */
        public Builder(int itemResourceId) {
            this.itemResourceId = itemResourceId;
        }

        /**
         * Set layout resource for Section's header of this builder
         * @param headerResourceId layout resource for Section's header
         * @return this builder
         */
        public Builder headerResourceId(int headerResourceId) {
            this.headerResourceId = headerResourceId;

            return this;
        }

        /**
         * Set layout resource for Section's footer of this builder
         * @param footerResourceId layout resource for Section's footer
         * @return this builder
         */
        public Builder footerResourceId(int footerResourceId) {
            this.footerResourceId = footerResourceId;

            return this;
        }

        /**
         * Set layout resource for Section's loading state of this builder
         * @param loadingResourceId layout resource for Section's loading state
         * @return this builder
         */
        public Builder loadingResourceId(int loadingResourceId) {
            this.loadingResourceId = loadingResourceId;

            return this;
        }

        /**
         * Set layout resource for Section's failed state of this builder
         * @param failedResourceId layout resource for Section's failed state
         * @return this builder
         */
        public Builder failedResourceId(int failedResourceId) {
            this.failedResourceId = failedResourceId;

            return this;
        }

        /**
         * Constructs an instance of SectionParameters
         * @return an instance of SectionParameters
         */
        public SectionParameters build() {
            return new SectionParameters(this);
        }
    }

    /**
     * Private constructor. Not for public consumption
     */
    private SectionParameters(Builder builder) {
        this.headerResourceId = builder.headerResourceId;
        this.footerResourceId = builder.footerResourceId;
        this.itemResourceId = builder.itemResourceId;
        this.loadingResourceId = builder.loadingResourceId;
        this.failedResourceId = builder.failedResourceId;
    }
}
