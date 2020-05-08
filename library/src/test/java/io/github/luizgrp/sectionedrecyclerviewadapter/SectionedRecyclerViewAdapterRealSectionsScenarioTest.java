package io.github.luizgrp.sectionedrecyclerviewadapter;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.junit.Before;
import org.junit.Test;

/*
 * Unit tests for SectionedRecyclerViewAdapter
 */
@SuppressWarnings({"PMD.MethodNamingConventions"})
public class SectionedRecyclerViewAdapterRealSectionsScenarioTest {

    private static final int ITEMS_QTY = 10;
    private static final int HASH_CODE = 1;

    private SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter;

    @Before
    public void setUp() {
        sectionedRecyclerViewAdapter = new SectionedRecyclerViewAdapter();
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenSectionsWithOverriddenEqualsAndHashCode_whenAddSectionWithSection_thenThrowsException() {
        // Given
        final SectionImpl section1 = new SectionImpl();
        sectionedRecyclerViewAdapter.addSection(section1);
        final SectionImpl section2 = new SectionImpl();

        // When
        sectionedRecyclerViewAdapter.addSection(section2);
    }

    private static class SectionImpl extends Section {

        SectionImpl() {
            super(SectionParameters.builder()
                    .itemResourceId(-1)
                    .emptyResourceId(-1)
                    .failedViewWillBeProvided()
                    .loadingViewWillBeProvided()
                    .build());
        }

        @Override
        public int hashCode() {
            return HASH_CODE;
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            if (obj == null) return false;
            if (!(obj instanceof Section)) return false;
            return this.hashCode() == obj.hashCode();
        }

        @Override
        public int getContentItemsTotal() {
            return ITEMS_QTY;
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return null;
        }

        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

        }
    }
}
