package io.github.luizgrp.sectionedrecyclerviewadapter;

import org.junit.Before;
import org.junit.Test;

import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy.BindingFootedStatelessSectionSpy;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy.BindingHeadedFootedStatelessSectionSpy;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy.BindingHeadedStatelessSectionSpy;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy.BindingStatelessSectionSpy;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.StatelessSectionStub;

import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@link StatelessSection}
 */
public class StatelessSectionTest {

    private final int ITEMS_QTY = 10;

    private SectionedRecyclerViewAdapter sectionAdapter;

    @Before
    public void setup() {
        sectionAdapter = new SectionedRecyclerViewAdapter();
    }

    @Test
    public void onBindViewHolder_StatelessSection_isCalled() {
        // Given
        sectionAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));

        BindingStatelessSectionSpy sectionSpy = new BindingStatelessSectionSpy(ITEMS_QTY);
        sectionAdapter.addSection(sectionSpy);

        // When
        // StatelessSection - Items [10-19]
        sectionAdapter.onBindViewHolder(null, 10);

        // Then
        assertTrue(sectionSpy.onBindItemViewHolderWasCalled);
    }

    @Test
    public void onBindViewHolder_HeadedStatelessSection_isCalled() {
        // Given
        sectionAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));

        BindingHeadedStatelessSectionSpy sectionSpy = new BindingHeadedStatelessSectionSpy(ITEMS_QTY);
        sectionAdapter.addSection(sectionSpy);

        // When
        // HeadedStatelessSection - Header [10]
        sectionAdapter.onBindViewHolder(null, 10);
        // HeadedStatelessSection - Items [11-20]
        sectionAdapter.onBindViewHolder(null, 11);

        // Then
        assertTrue(sectionSpy.onBindHeaderViewHolderWasCalled);
        assertTrue(sectionSpy.onBindItemViewHolderWasCalled);
    }

    @Test
    public void onBindViewHolder_FootedStatelessSection_isCalled() {
        // Given
        sectionAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));

        BindingFootedStatelessSectionSpy sectionSpy = new BindingFootedStatelessSectionSpy(ITEMS_QTY);
        sectionAdapter.addSection(sectionSpy);

        // When
        // FootedStatelessSection - Items [10-19]
        sectionAdapter.onBindViewHolder(null, 10);
        // FootedStatelessSection - Footer [20]
        sectionAdapter.onBindViewHolder(null, 20);

        // Then
        assertTrue(sectionSpy.onBindItemViewHolderWasCalled);
        assertTrue(sectionSpy.onBindFooterViewHolderWasCalled);
    }

    @Test
    public void onBindViewHolder_HeadedFootedStatelessSection_isCalled() {
        // Given
        sectionAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));

        BindingHeadedFootedStatelessSectionSpy sectionSpy = new BindingHeadedFootedStatelessSectionSpy(ITEMS_QTY);
        sectionAdapter.addSection(sectionSpy);

        // When
        // HeadedFootedStatelessSection - Header [10]
        sectionAdapter.onBindViewHolder(null, 10);
        // HeadedFootedStatelessSection - Items [11-20]
        sectionAdapter.onBindViewHolder(null, 11);
        // HeadedFootedStatelessSection - Footer [21]
        sectionAdapter.onBindViewHolder(null, 21);

        // Then
        assertTrue(sectionSpy.onBindHeaderViewHolderWasCalled);
        assertTrue(sectionSpy.onBindItemViewHolderWasCalled);
        assertTrue(sectionSpy.onBindFooterViewHolderWasCalled);
    }
}
