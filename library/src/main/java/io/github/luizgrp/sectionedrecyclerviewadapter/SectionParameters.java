package io.github.luizgrp.sectionedrecyclerviewadapter;

/**
 * Class used as constructor parameters of {@link Section}.
 */
public class SectionParameters {
    public final Integer headerResourceId;
    public final Integer footerResourceId;
    public final int itemResourceId;
    public final Integer loadingResourceId;
    public final Integer failedResourceId;
    public final Integer emptyResourceId;

    /**
     * Builder of {@link SectionParameters}
     */
    public static class Builder {
        private final int itemResourceId;

        private Integer headerResourceId;
        private Integer footerResourceId;
        private Integer loadingResourceId;
        private Integer failedResourceId;
        private Integer emptyResourceId;

        /**
         * Constructor with mandatory parameters of {@link Section}
         * @param itemResourceId layout resource for Section's items
         */
        public Builder(int itemResourceId) {
            this.itemResourceId = itemResourceId;
        }

        /**
         * Set layout resource for Section's header
         * @param headerResourceId layout resource for Section's header
         * @return this builder
         */
        public Builder headerResourceId(int headerResourceId) {
            this.headerResourceId = headerResourceId;

            return this;
        }

        /**
         * Set layout resource for Section's footer
         * @param footerResourceId layout resource for Section's footer
         * @return this builder
         */
        public Builder footerResourceId(int footerResourceId) {
            this.footerResourceId = footerResourceId;

            return this;
        }

        /**
         * Set layout resource for Section's loading state
         * @param loadingResourceId layout resource for Section's loading state
         * @return this builder
         */
        public Builder loadingResourceId(int loadingResourceId) {
            this.loadingResourceId = loadingResourceId;

            return this;
        }

        /**
         * Set layout resource for Section's failed state
         * @param failedResourceId layout resource for Section's failed state
         * @return this builder
         */
        public Builder failedResourceId(int failedResourceId) {
            this.failedResourceId = failedResourceId;

            return this;
        }

        /**
         * Set layout resource for Section's empty state
         * @param emptyResourceId layout resource for Section's empty state
         * @return this builder
         */
        public Builder emptyResourceId(int emptyResourceId) {
            this.emptyResourceId = emptyResourceId;

            return this;
        }
        
        /**
         * Build an instance of SectionParameters
         * @return an instance of SectionParameters
         */
        public SectionParameters build() {
            return new SectionParameters(this);
        }
    }

    private SectionParameters(Builder builder) {
        this.headerResourceId = builder.headerResourceId;
        this.footerResourceId = builder.footerResourceId;
        this.itemResourceId = builder.itemResourceId;
        this.loadingResourceId = builder.loadingResourceId;
        this.failedResourceId = builder.failedResourceId;
        this.emptyResourceId = builder.emptyResourceId;
    }
}
