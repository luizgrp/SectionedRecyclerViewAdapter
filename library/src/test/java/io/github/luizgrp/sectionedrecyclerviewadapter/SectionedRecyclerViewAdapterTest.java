package io.github.luizgrp.sectionedrecyclerviewadapter;

import org.junit.Before;
import org.junit.Test;

import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.FootedSectionStub;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.FootedStatelessSectionStub;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.HeadedFootedSectionStub;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.HeadedFootedStatelessSectionStub;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.HeadedSectionStub;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.HeadedStatelessSectionStub;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.SectionStub;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.StatelessSectionStub;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Gustavo Pagani
 */
public class SectionedRecyclerViewAdapterTest {

    private final int ITEMS_QTY = 10;
    private final String SECTION_TAG = "tag";

    private SectionedRecyclerViewAdapter sectionAdapter;

    @Before
    public void setup() {
        sectionAdapter = new SectionedRecyclerViewAdapter();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getPositionInSection_withEmptyAdapter_throwsException() {
        // When
        sectionAdapter.getPositionInSection(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void onBindViewHolder_withEmptyAdapter_throwsException() {
        // When
        sectionAdapter.onBindViewHolder(null, 0);
    }

    @Test
    public void addSection_withEmptyAdapter_succeeds() {
        // Given
        StatelessSectionStub statelessSection = new StatelessSectionStub(ITEMS_QTY);

        // When
        String sectionTag = sectionAdapter.addSection(statelessSection);

        // Then
        assertSame(sectionAdapter.getSection(sectionTag), statelessSection);
        assertSame(sectionAdapter.getSectionsMap().get(sectionTag), statelessSection);
    }

    @Test
    public void addSectionWithTag_withEmptyAdapter_succeeds() {
        // Given
        StatelessSectionStub statelessSection = new StatelessSectionStub(ITEMS_QTY);

        // When
        sectionAdapter.addSection(SECTION_TAG, statelessSection);

        // Then
        assertSame(sectionAdapter.getSection(SECTION_TAG), statelessSection);
        assertSame(sectionAdapter.getSectionsMap().get(SECTION_TAG), statelessSection);
    }

    @Test
    public void getSectionWithTag_withRemovedSection_returnsNull() {
        // Given
        String sectionTag = sectionAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));

        // When
        sectionAdapter.removeSection(sectionTag);

        // Then
        assertNull(sectionAdapter.getSection(sectionTag));
    }

    @Test
    public void getSectionWithTag_withEmptyAdapter_returnsNull() {
        // When
        Section result = sectionAdapter.getSection(SECTION_TAG);

        // Then
        assertNull(result);
    }

    @Test
    public void getItemCount_withEmptyAdapter_isZero() {
        // When
        int result = sectionAdapter.getItemCount();

        // Then
        assertThat(result, is(0));
    }

    @Test
    public void getItemCount_withAdapterWithInvisibleSection_returnsCorrectQuantity() {
        // Given
        sectionAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));

        StatelessSectionStub invisibleStatelessSection = new StatelessSectionStub(ITEMS_QTY);
        invisibleStatelessSection.setVisible(false);
        sectionAdapter.addSection(invisibleStatelessSection);

        // When
        int result = sectionAdapter.getItemCount();

        // Then
        assertThat(result, is(ITEMS_QTY));
    }

    @Test
    public void getSectionsMap_withEmptyAdapter_isEmpty() {
        // When
        boolean result = sectionAdapter.getSectionsMap().isEmpty();

        // Then
        assertTrue(result);
    }

    @Test
    public void getSectionsMap_withAdapterWithInvisibleSection_hasCorrectSize() {
        // Given
        sectionAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));

        StatelessSectionStub invisibleStatelessSection = new StatelessSectionStub(ITEMS_QTY);
        invisibleStatelessSection.setVisible(false);
        sectionAdapter.addSection(invisibleStatelessSection);

        // When
        int result = sectionAdapter.getSectionsMap().size();

        // Then
        assertThat(result, is(2));
    }

    @Test
    public void getSection_withEmptyAdapter_isNull() {
        // When
        Section result = sectionAdapter.getSection(SECTION_TAG);

        // Then
        assertNull(result);
    }

    @Test
    public void getSection_withAdapterWithManySections_returnsCorrectSection() {
        // Given
        sectionAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));

        StatelessSectionStub statelessSection = new StatelessSectionStub(ITEMS_QTY);
        String sectionTag = sectionAdapter.addSection(statelessSection);

        sectionAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));

        // When
        Section result = sectionAdapter.getSection(sectionTag);

        // Then
        assertSame(result, statelessSection);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getSectionForPosition_withEmptyAdapter_throwsException() {
        // When
        sectionAdapter.getSectionForPosition(0);
    }

    @Test
    public void getSectionForPosition_withAdapterWithManySections_returnsCorrectSection() {
        // Given
        StatelessSectionStub sectionStub1 = addStatelessSectionStubToAdapter();
        StatelessSectionStub sectionStub2 = addStatelessSectionStubToAdapter();
        StatelessSectionStub sectionStub3 = addStatelessSectionStubToAdapter();

        // When
        Section result = sectionAdapter.getSectionForPosition(0);
        Section result2 = sectionAdapter.getSectionForPosition(9);
        Section result3 = sectionAdapter.getSectionForPosition(10);
        Section result4 = sectionAdapter.getSectionForPosition(19);
        Section result5 = sectionAdapter.getSectionForPosition(20);
        Section result6 = sectionAdapter.getSectionForPosition(29);

        // Then
        assertSame(result, sectionStub1);
        assertSame(result2, sectionStub1);
        assertSame(result3, sectionStub2);
        assertSame(result4, sectionStub2);
        assertSame(result5, sectionStub3);
        assertSame(result6, sectionStub3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getSectionItemViewType_withEmptyAdapter_throwsException() {
        // When
        sectionAdapter.getSectionItemViewType(0);
    }

    @Test
    public void getSectionItemViewType_withAdapterWithManySections_returnsCorrectValuesForHeadedFootedStatelessSection() {
        // Given
        addFourStatelessSectionsAndFourSectionsToAdapter();

        // When
        int positionHeader = sectionAdapter.getSectionItemViewType(32);
        int positionItemStart = sectionAdapter.getSectionItemViewType(33);
        int positionItemEnd = sectionAdapter.getSectionItemViewType(42);
        int positionFooter = sectionAdapter.getSectionItemViewType(43);

        // Then
        assertThat(positionHeader, is(SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER));
        assertThat(positionItemStart, is(SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED));
        assertThat(positionItemEnd, is(SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED));
        assertThat(positionFooter, is(SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getItemViewType_withEmptyAdapter_throwsException() {
        // When
        sectionAdapter.getItemViewType(0);
    }

    @Test
    public void getItemViewType_withAdapterWithManySections_returnsCorrectValuesForHeadedFootedStatelessSection() {
        // Given
        addFourStatelessSectionsAndFourSectionsToAdapter();

        // When
        int positionHeader = sectionAdapter.getItemViewType(32);
        int positionItemStart = sectionAdapter.getItemViewType(33);
        int positionItemEnd = sectionAdapter.getItemViewType(42);
        int positionFooter = sectionAdapter.getItemViewType(43);

        // Then
        assertThat(positionHeader, is(15));
        assertThat(positionItemStart, is(17));
        assertThat(positionItemEnd, is(17));
        assertThat(positionFooter, is(16));
    }

    @Test
    public void getItemViewType_withAdapterWithManySections_returnsCorrectValuesForSectionWithLoadingState() {
        // Given
        addStatelessSectionStubToAdapter();
        addHeadedStatelessSectionStubToAdapter();
        addFootedStatelessSectionStubToAdapter();
        addHeadedFootedStatelessSectionStubToAdapter();

        Section section = addSectionStubToAdapter();
        section.setState(Section.State.LOADING);

        addHeadedSectionStubToAdapter();
        addFootedSectionStubToAdapter();
        addHeadedFootedSectionStubToAdapter();

        // When
        int positionLoading = sectionAdapter.getItemViewType(44);

        // Then
        assertThat(positionLoading, is(23));
    }

    @Test
    public void getItemViewType_withAdapterWithManySections_returnsCorrectValuesForSectionWithFailedState() {
        // Given
        addStatelessSectionStubToAdapter();
        addHeadedStatelessSectionStubToAdapter();
        addFootedStatelessSectionStubToAdapter();
        addHeadedFootedStatelessSectionStubToAdapter();

        Section section = addSectionStubToAdapter();
        section.setState(Section.State.FAILED);

        addHeadedSectionStubToAdapter();
        addFootedSectionStubToAdapter();
        addHeadedFootedSectionStubToAdapter();

        // When
        int positionFailed = sectionAdapter.getItemViewType(44);

        // Then
        assertThat(positionFailed, is(24));
    }

    @Test
    public void onCreateViewHolder_withEmptyAdapter_returnsNull() {
        // When
        Object result = sectionAdapter.onCreateViewHolder(null, 0);

        // Then
        assertNull(result);
    }

    @Test(expected = NullPointerException.class)
    public void onCreateViewHolder_withStatelessSection_throwsExceptionForHeader() {
        // Given
        addStatelessSectionStubToAdapter();

        sectionAdapter.onCreateViewHolder(null, SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER);
    }

    @Test(expected = NullPointerException.class)
    public void onCreateViewHolder_withStatelessSection_throwsExceptionForFooter() {
        // Given
        addStatelessSectionStubToAdapter();

        // When
        sectionAdapter.onCreateViewHolder(null, SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER);
    }

    @Test(expected = NullPointerException.class)
    public void onCreateViewHolder_withStatelessSection_throwsExceptionForLoading() {
        // Given
        addStatelessSectionStubToAdapter();

        // When
        sectionAdapter.onCreateViewHolder(null, SectionedRecyclerViewAdapter.VIEW_TYPE_LOADING);
    }

    @Test(expected = NullPointerException.class)
    public void onCreateViewHolder_withStatelessSection_throwsExceptionForFailed() {
        // Given
        addStatelessSectionStubToAdapter();

        // When
        sectionAdapter.onCreateViewHolder(null, SectionedRecyclerViewAdapter.VIEW_TYPE_FAILED);
    }

    @Test
    public void removeAllSections_withAdapterWithManySections_succeeds() {
        // Given
        addFourStatelessSectionsAndFourSectionsToAdapter();

        // When
        sectionAdapter.removeAllSections();

        // Then
        assertThat(sectionAdapter.getItemCount(), is(0));
        assertTrue(sectionAdapter.getSectionsMap().isEmpty());
    }

    private void addFourStatelessSectionsAndFourSectionsToAdapter() {
        addStatelessSectionStubToAdapter();
        addHeadedStatelessSectionStubToAdapter();
        addFootedStatelessSectionStubToAdapter();
        addHeadedFootedStatelessSectionStubToAdapter();

        addSectionStubToAdapter();
        addHeadedSectionStubToAdapter();
        addFootedSectionStubToAdapter();
        addHeadedFootedSectionStubToAdapter();
    }

    private StatelessSectionStub addStatelessSectionStubToAdapter() {
        StatelessSectionStub sectionStub = new StatelessSectionStub(ITEMS_QTY);
        sectionAdapter.addSection(sectionStub);
        return sectionStub;
    }

    private SectionStub addSectionStubToAdapter() {
        SectionStub sectionStub = new SectionStub(ITEMS_QTY);
        sectionAdapter.addSection(sectionStub);
        return sectionStub;
    }

    private HeadedStatelessSectionStub addHeadedStatelessSectionStubToAdapter() {
        HeadedStatelessSectionStub headedSectionSub = new HeadedStatelessSectionStub(ITEMS_QTY);
        sectionAdapter.addSection(headedSectionSub);
        return headedSectionSub;
    }

    private HeadedSectionStub addHeadedSectionStubToAdapter() {
        HeadedSectionStub headedSectionSub = new HeadedSectionStub(ITEMS_QTY);
        sectionAdapter.addSection(headedSectionSub);
        return headedSectionSub;
    }

    private FootedStatelessSectionStub addFootedStatelessSectionStubToAdapter() {
        FootedStatelessSectionStub footedSectionSub = new FootedStatelessSectionStub(ITEMS_QTY);
        sectionAdapter.addSection(footedSectionSub);
        return footedSectionSub;
    }

    private FootedSectionStub addFootedSectionStubToAdapter() {
        FootedSectionStub footedSectionSub = new FootedSectionStub(ITEMS_QTY);
        sectionAdapter.addSection(footedSectionSub);
        return footedSectionSub;
    }

    private HeadedFootedStatelessSectionStub addHeadedFootedStatelessSectionStubToAdapter() {
        HeadedFootedStatelessSectionStub headedFootedSectionSub = new HeadedFootedStatelessSectionStub(ITEMS_QTY);
        sectionAdapter.addSection(headedFootedSectionSub);
        return headedFootedSectionSub;
    }

    private HeadedFootedSectionStub addHeadedFootedSectionStubToAdapter() {
        HeadedFootedSectionStub headedFootedSectionSub = new HeadedFootedSectionStub(ITEMS_QTY);
        sectionAdapter.addSection(headedFootedSectionSub);
        return headedFootedSectionSub;
    }
}
