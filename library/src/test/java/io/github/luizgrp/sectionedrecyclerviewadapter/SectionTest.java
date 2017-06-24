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
        SectionParameters sectionParameters = new SectionParameters.Builder(1)
                .failedResourceId(2)
                .footerResourceId(3)
                .headerResourceId(4)
                .loadingResourceId(5)
                .build();

        // When
        Section section = getSection(sectionParameters);
        int result = section.getItemResourceId();
        int result2 = section.getFailedResourceId();
        int result3 = section.getFooterResourceId();
        int result4 = section.getHeaderResourceId();
        int result5 = section.getLoadingResourceId();
        boolean hasHeader = section.hasHeader();
        boolean hasFooter = section.hasFooter();

        // Then
        assertThat(result, is(1));
        assertThat(result2, is(2));
        assertThat(result3, is(3));
        assertThat(result4, is(4));
        assertThat(result5, is(5));
        assertThat(hasHeader, is(true));
        assertThat(hasFooter, is(true));
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
