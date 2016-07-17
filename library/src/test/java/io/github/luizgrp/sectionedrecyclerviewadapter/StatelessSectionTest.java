package io.github.luizgrp.sectionedrecyclerviewadapter;

import org.junit.Test;

import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy.BindingFootedStatelessSectionSpy;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy.BindingHeadedFootedStatelessSectionSpy;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy.BindingHeadedStatelessSectionSpy;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy.BindingStatelessSectionSpy;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.StatelessSectionStub;

import static org.junit.Assert.assertTrue;

/**
 * @author Gustavo Pagani
 */
public class StatelessSectionTest {

    final int ITEMS_QTY = 10;

    @Test
    public void onBindViewHolder_StatelessSection_isCalled() {
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();

        StatelessSectionStub sectionStub = new StatelessSectionStub(ITEMS_QTY);
        sectionAdapter.addSection(sectionStub);
        BindingStatelessSectionSpy sectionSpy = new BindingStatelessSectionSpy(ITEMS_QTY);
        sectionAdapter.addSection(sectionSpy);

        // StatelessSection - Items [10-19]
        sectionAdapter.onBindViewHolder(null, 10);

        assertTrue(sectionSpy.onBindItemViewHolderWasCalled);
    }

    @Test
    public void onBindViewHolder_HeadedStatelessSection_isCalled() {
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();

        StatelessSectionStub sectionStub = new StatelessSectionStub(ITEMS_QTY);
        sectionAdapter.addSection(sectionStub);
        BindingHeadedStatelessSectionSpy sectionSpy = new BindingHeadedStatelessSectionSpy(ITEMS_QTY);
        sectionAdapter.addSection(sectionSpy);

        // HeadedStatelessSection - Header [10]
        sectionAdapter.onBindViewHolder(null, 10);
        // HeadedStatelessSection - Items [11-20]
        sectionAdapter.onBindViewHolder(null, 11);

        assertTrue(sectionSpy.onBindHeaderViewHolderWasCalled);
        assertTrue(sectionSpy.onBindItemViewHolderWasCalled);
    }

    @Test
    public void onBindViewHolder_FootedStatelessSection_isCalled() {
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();

        StatelessSectionStub sectionStub = new StatelessSectionStub(ITEMS_QTY);
        sectionAdapter.addSection(sectionStub);
        BindingFootedStatelessSectionSpy sectionSpy = new BindingFootedStatelessSectionSpy(ITEMS_QTY);
        sectionAdapter.addSection(sectionSpy);

        // FootedStatelessSection - Items [11-20]
        sectionAdapter.onBindViewHolder(null, 10);
        // FootedStatelessSection - Footer [20]
        sectionAdapter.onBindViewHolder(null, 20);

        assertTrue(sectionSpy.onBindItemViewHolderWasCalled);
        assertTrue(sectionSpy.onBindFooterViewHolderWasCalled);
    }

    @Test
    public void onBindViewHolder_HeadedFootedStatelessSection_isCalled() {
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();

        StatelessSectionStub sectionStub = new StatelessSectionStub(ITEMS_QTY);
        sectionAdapter.addSection(sectionStub);
        BindingHeadedFootedStatelessSectionSpy sectionSpy = new BindingHeadedFootedStatelessSectionSpy(ITEMS_QTY);
        sectionAdapter.addSection(sectionSpy);

        // HeadedFootedStatelessSection - Header [10]
        sectionAdapter.onBindViewHolder(null, 10);
        // HeadedFootedStatelessSection - Item [11]
        sectionAdapter.onBindViewHolder(null, 11);
        // HeadedFootedStatelessSection - Footer [21]
        sectionAdapter.onBindViewHolder(null, 21);

        assertTrue(sectionSpy.onBindHeaderViewHolderWasCalled);
        assertTrue(sectionSpy.onBindItemViewHolderWasCalled);
        assertTrue(sectionSpy.onBindFooterViewHolderWasCalled);
    }
}
