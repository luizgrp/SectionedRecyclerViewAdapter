package io.github.luizgrp.sectionedrecyclerviewadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.junit.Before;
import org.junit.Test;

import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy.BindingFootedSectionSpy;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy.BindingHeadedFootedSectionSpy;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy.BindingHeadedSectionSpy;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy.BindingSectionSpy;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.SectionStub;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@link Section}
 */
public class SectionTest {

    private final int ITEMS_QTY = 10;

    private SectionedRecyclerViewAdapter sectionAdapter;

    @Before
    public void setup() {
        sectionAdapter = new SectionedRecyclerViewAdapter();
    }

    @Test
    public void onBindViewHolder_Section_isCalled() {
        // Given
        sectionAdapter.addSection(new SectionStub(ITEMS_QTY));

        BindingSectionSpy sectionSpy = new BindingSectionSpy(ITEMS_QTY);
        sectionAdapter.addSection(sectionSpy);

        // When
        // Section - Items [10-19]
        sectionAdapter.onBindViewHolder(null, 10);

        // Then
        assertTrue(sectionSpy.onBindItemViewHolderWasCalled);
    }

    @Test
    public void onBindLoadingViewHolder_Section_isCalled() {
        // Given
        sectionAdapter.addSection(new SectionStub(ITEMS_QTY));

        BindingSectionSpy sectionSpy = new BindingSectionSpy(ITEMS_QTY);
        sectionAdapter.addSection(sectionSpy);

        sectionSpy.setState(Section.State.LOADING);

        // When
        // Section - Loading [10]
        sectionAdapter.onBindViewHolder(null, 10);

        // Then
        assertTrue(sectionSpy.onBindLoadingViewHolderWasCalled);
    }

    @Test
    public void onBindFailedViewHolder_Section_isCalled() {
        // Given
        sectionAdapter.addSection(new SectionStub(ITEMS_QTY));

        BindingSectionSpy sectionSpy = new BindingSectionSpy(ITEMS_QTY);
        sectionAdapter.addSection(sectionSpy);

        sectionSpy.setState(Section.State.FAILED);

        // When
        // Section - Failed [10]
        sectionAdapter.onBindViewHolder(null, 10);

        // Then
        assertTrue(sectionSpy.onBindFailedViewHolderWasCalled);
    }

    @Test
    public void onBindViewHolder_HeadedSection_isCalled() {
        // Given
        sectionAdapter.addSection(new SectionStub(ITEMS_QTY));

        BindingHeadedSectionSpy sectionSpy = new BindingHeadedSectionSpy(ITEMS_QTY);
        sectionAdapter.addSection(sectionSpy);

        // When
        // HeadedSection - Header [10]
        sectionAdapter.onBindViewHolder(null, 10);
        // HeadedSection - Items [11-20]
        sectionAdapter.onBindViewHolder(null, 11);

        // Then
        assertTrue(sectionSpy.onBindHeaderViewHolderWasCalled);
        assertTrue(sectionSpy.onBindItemViewHolderWasCalled);
    }

    @Test
    public void onBindViewHolder_FootedSection_isCalled() {
        // Given
        sectionAdapter.addSection(new SectionStub(ITEMS_QTY));

        BindingFootedSectionSpy sectionSpy = new BindingFootedSectionSpy(ITEMS_QTY);
        sectionAdapter.addSection(sectionSpy);

        // When
        // FootedSection - Items [10-19]
        sectionAdapter.onBindViewHolder(null, 10);
        // FootedSection - Footer [20]
        sectionAdapter.onBindViewHolder(null, 20);

        // Then
        assertTrue(sectionSpy.onBindItemViewHolderWasCalled);
        assertTrue(sectionSpy.onBindFooterViewHolderWasCalled);
    }

    @Test
    public void onBindViewHolder_HeadedFootedSection_isCalled() {
        // Given
        sectionAdapter.addSection(new SectionStub(ITEMS_QTY));

        BindingHeadedFootedSectionSpy sectionSpy = new BindingHeadedFootedSectionSpy(ITEMS_QTY);
        sectionAdapter.addSection(sectionSpy);

        // When
        // HeadedFootedSection - Header [10]
        sectionAdapter.onBindViewHolder(null, 10);
        // HeadedFootedSection - Item [11]
        sectionAdapter.onBindViewHolder(null, 11);
        // HeadedFootedSection - Footer [21]
        sectionAdapter.onBindViewHolder(null, 21);

        // Then
        assertTrue(sectionSpy.onBindHeaderViewHolderWasCalled);
        assertTrue(sectionSpy.onBindItemViewHolderWasCalled);
        assertTrue(sectionSpy.onBindFooterViewHolderWasCalled);
    }

    @Test
    public void build_constructsCorrectSection() {
        // Given
        final int ITEM_ID = 1;
        final int HEADER_ID = 2;
        final int FOOTER_ID = 3;
        final int FAILED_ID = 4;
        final int LOADING_ID = 5;

        SectionParameters sectionParameters = new SectionParameters.Builder(ITEM_ID)
                .headerResourceId(HEADER_ID)
                .footerResourceId(FOOTER_ID)
                .failedResourceId(FAILED_ID)
                .loadingResourceId(LOADING_ID)
                .build();
        Section section = getSection(sectionParameters);

        // When
        int resultItemId = section.getItemResourceId();
        int resultHeaderId = section.getHeaderResourceId();
        int resultFooterId = section.getFooterResourceId();
        int resultFailedId = section.getFailedResourceId();
        int resultLoadingId = section.getLoadingResourceId();
        boolean resultHasHeader = section.hasHeader();
        boolean resultHasFooter = section.hasFooter();

        // Then
        assertThat(resultItemId, is(ITEM_ID));
        assertThat(resultHeaderId, is(HEADER_ID));
        assertThat(resultFooterId, is(FOOTER_ID));
        assertThat(resultFailedId, is(FAILED_ID));
        assertThat(resultLoadingId, is(LOADING_ID));
        assertThat(resultHasHeader, is(true));
        assertThat(resultHasFooter, is(true));
    }

    private Section getSection(SectionParameters sectionParameters) {
        return new Section(sectionParameters) {
            @Override
            public int getContentItemsTotal() {
                return 0;
            }

            @Override
            public RecyclerView.ViewHolder getItemViewHolder(View view) {
                return null;
            }

            @Override
            public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

            }
        };
    }
}
