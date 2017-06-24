package io.github.luizgrp.sectionedrecyclerviewadapter;

import org.junit.Before;
import org.junit.Test;

import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy.BindingFootedSectionSpy;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy.BindingHeadedFootedSectionSpy;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy.BindingHeadedSectionSpy;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy.BindingSectionSpy;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.SectionStub;

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
}
