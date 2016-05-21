package io.github.luizgrp.sectionedrecyclerviewadapter;

import org.junit.Test;

import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.FootedStatelessSectionStub;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.HeadedFootedStatelessSectionStub;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.HeadedStatelessSectionStub;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.StatelessSectionStub;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SectionedRecyclerViewAdapterTest {

    final int ITEMS_QTY = 10;
    final String SECTION_TAG = "tag";

    @Test
    public void getItemCount_emptyAdapter_isZero() {
        assertThat(new SectionedRecyclerViewAdapter().getItemCount(), is(0));
    }

    @Test
    public void getSectionsMap_emptyAdapter_isEmpty() {
        assertTrue(new SectionedRecyclerViewAdapter().getSectionsMap().isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getSectionPosition_emptyAdapter_throwsException() {
        new SectionedRecyclerViewAdapter().getSectionPosition(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getSectionForPosition_emptyAdapter_throwsException() {
        new SectionedRecyclerViewAdapter().getSectionForPosition(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getItemViewType_emptyAdapter_throwsException() {
        new SectionedRecyclerViewAdapter().getItemViewType(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getSectionItemViewType_emptyAdapter_throwsException() {
        new SectionedRecyclerViewAdapter().getSectionItemViewType(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void onBindViewHolder_emptyAdapter_throwsException() {
        new SectionedRecyclerViewAdapter().onBindViewHolder(null, 0);
    }

    @Test
    public void addSection_emptyAdapter_succeed() {
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();
        StatelessSectionStub sectionStub = new StatelessSectionStub(ITEMS_QTY);

        final String SECTION_TAG = sectionAdapter.addSection(sectionStub);

        assertSame(sectionStub, sectionAdapter.getSection(SECTION_TAG));
        assertSame(sectionStub, sectionAdapter.getSectionsMap().get(SECTION_TAG));
    }

    @Test
    public void addSectionWithTag_emptyAdapter_succeed() {
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();
        StatelessSectionStub sectionStub = new StatelessSectionStub(ITEMS_QTY);

        sectionAdapter.addSection(SECTION_TAG, sectionStub);

        assertSame(sectionStub, sectionAdapter.getSection(SECTION_TAG));
        assertSame(sectionStub, sectionAdapter.getSectionsMap().get(SECTION_TAG));
    }

    @Test
    public void getSectionWithTag_removedSection_returnNull() {
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();
        StatelessSectionStub sectionStub = new StatelessSectionStub(ITEMS_QTY);

        final String SECTION_TAG = sectionAdapter.addSection(sectionStub);
        sectionAdapter.removeSection(SECTION_TAG);

        assertNull(sectionAdapter.getSection(SECTION_TAG));
    }

    @Test
    public void getSectionWithTag_emptyAdapter_returnNull() {
        assertNull(new SectionedRecyclerViewAdapter().getSection(SECTION_TAG));
    }

    @Test
    public void getItemCount_with4VisiblePlus2InvisibleSections_is44() {
        SectionedRecyclerViewAdapter sectionAdapter = getAdapterWithFourDifferentSections();

        // invisible sections don't increase the total item count
        StatelessSectionStub invisibleSectionStub = addStatelessSectionStubToAdapter(sectionAdapter);
        invisibleSectionStub.setVisible(false);

        HeadedStatelessSectionStub invisibleHeadedSectionSub = addHeadedStatelessSectionStubToAdapter(sectionAdapter);
        invisibleHeadedSectionSub.setVisible(false);

        // 10 + 11 + 11 + 12
        assertThat(sectionAdapter.getItemCount(), is(44));
    }

    @Test
    public void getSectionsMap_with4VisiblePlus2InvisibleSections_hasSize6() {
        SectionedRecyclerViewAdapter sectionAdapter = getAdapterWithFourDifferentSections();

        FootedStatelessSectionStub invisibleFootedSectionSub = addFootedStatelessSectionStubToAdapter(sectionAdapter);
        invisibleFootedSectionSub.setVisible(false);

        HeadedFootedStatelessSectionStub invisibleHeadedFootedSectionSub = addHeadedFootedStatelessSectionStubToAdapter(sectionAdapter);
        invisibleHeadedFootedSectionSub.setVisible(false);

        assertThat(sectionAdapter.getSectionsMap().size(), is(6));
    }

    @Test
    public void getItemCount_afterRemoveAll_isZero() {
        SectionedRecyclerViewAdapter sectionAdapter = getAdapterWithFourDifferentSections();

        sectionAdapter.removeAllSections();

        assertThat(sectionAdapter.getItemCount(), is(0));
    }

    @Test
    public void getSectionsMap_afterRemoveAll_isEmpty() {
        SectionedRecyclerViewAdapter sectionAdapter = getAdapterWithFourDifferentSections();

        sectionAdapter.removeAllSections();

        assertTrue(sectionAdapter.getSectionsMap().isEmpty());
    }

    private SectionedRecyclerViewAdapter getAdapterWithFourDifferentSections() {
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();

        addStatelessSectionStubToAdapter(sectionAdapter);
        addHeadedStatelessSectionStubToAdapter(sectionAdapter);
        addFootedStatelessSectionStubToAdapter(sectionAdapter);
        addHeadedFootedStatelessSectionStubToAdapter(sectionAdapter);

        return sectionAdapter;
    }

    private StatelessSectionStub addStatelessSectionStubToAdapter(SectionedRecyclerViewAdapter sectionAdapter) {
        StatelessSectionStub sectionStub = new StatelessSectionStub(ITEMS_QTY);
        sectionAdapter.addSection(sectionStub);
        return sectionStub;
    }

    private HeadedStatelessSectionStub addHeadedStatelessSectionStubToAdapter(SectionedRecyclerViewAdapter sectionAdapter) {
        HeadedStatelessSectionStub headedSectionSub = new HeadedStatelessSectionStub(ITEMS_QTY);
        sectionAdapter.addSection(headedSectionSub);
        return headedSectionSub;
    }

    private FootedStatelessSectionStub addFootedStatelessSectionStubToAdapter(SectionedRecyclerViewAdapter sectionAdapter) {
        FootedStatelessSectionStub footedSectionSub = new FootedStatelessSectionStub(ITEMS_QTY);
        sectionAdapter.addSection(footedSectionSub);
        return footedSectionSub;
    }

    private HeadedFootedStatelessSectionStub addHeadedFootedStatelessSectionStubToAdapter(SectionedRecyclerViewAdapter sectionAdapter) {
        HeadedFootedStatelessSectionStub headedFootedSectionSub = new HeadedFootedStatelessSectionStub(ITEMS_QTY);
        sectionAdapter.addSection(headedFootedSectionSub);
        return headedFootedSectionSub;
    }
}
