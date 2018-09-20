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

import static io.github.luizgrp.sectionedrecyclerviewadapter.Section.State;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/*
 * Unit tests for SectionedRecyclerViewAdapter
 */
@SuppressWarnings({"PMD.MethodNamingConventions"})
public class SectionedRecyclerViewAdapterTest {

    private static final int ITEMS_QTY = 10;
    private static final String SECTION_TAG = "tag";

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

    @Test
    public void getPositionInSection_withAdapterWithInvisibleSection_returnsCorrectPosition() {
        // Given
        addHeadedFootedStatelessSectionStubToAdapter();
        addInvisibleStatelessSectionStubToAdapter();
        addHeadedFootedStatelessSectionStubToAdapter();

        // When
        int result = sectionAdapter.getPositionInSection(13);
        int result2 = sectionAdapter.getPositionInSection(22);

        // Then
        assertThat(result, is(0));
        assertThat(result2, is(9));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getSectionPositionUsingTag_withEmptyAdapter_throwsException() {
        // When
        sectionAdapter.getSectionPosition(SECTION_TAG);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getSectionPositionUsingTag_withInvalidTag_throwsException() {
        // Given
        addStatelessSectionStubToAdapter();
        addStatelessSectionStubToAdapter();

        // When
        sectionAdapter.getSectionPosition(SECTION_TAG);
    }

    @Test
    public void getSectionPositionUsingTag_callsGetSectionPositionUsingSection() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);

        SectionStub sectionStub = new SectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, sectionStub);

        // When
        spySectionedRecyclerViewAdapter.getSectionPosition(SECTION_TAG);

        // Then
        verify(spySectionedRecyclerViewAdapter).getSectionPosition(sectionStub);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getSectionPositionUsingSection_withEmptyAdapter_throwsException() {
        // When
        sectionAdapter.getSectionPosition(new StatelessSectionStub(ITEMS_QTY));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getSectionPositionUsingSection_withInvalidSection_throwsException() {
        // Given
        addStatelessSectionStubToAdapter();
        addStatelessSectionStubToAdapter();

        // When
        sectionAdapter.getSectionPosition(new StatelessSectionStub(ITEMS_QTY));
    }

    @Test
    public void getSectionPositionUsingSection_withAdapterWithInvisibleSection_returnsCorrectPosition() {
        // Given
        addStatelessSectionStubToAdapter();
        addInvisibleStatelessSectionStubToAdapter();

        StatelessSectionStub statelessSectionStub = new StatelessSectionStub(ITEMS_QTY);

        sectionAdapter.addSection(statelessSectionStub);

        // When
        int result = sectionAdapter.getSectionPosition(statelessSectionStub);

        // Then
        assertThat(result, is(10));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void onBindViewHolder_withEmptyAdapter_throwsException() {
        // When
        sectionAdapter.onBindViewHolder(null, 0);
    }

    @Test
    public void addSectionUsingSection_withEmptyAdapter_succeeds() {
        // Given
        Section section = new StatelessSectionStub(ITEMS_QTY);

        // When
        String result = sectionAdapter.addSection(section);

        // Then
        assertSame(sectionAdapter.getSection(result), section);
        assertSame(sectionAdapter.getCopyOfSectionsMap().get(result), section);
    }

    @Test
    public void addSectionUsingTag_withEmptyAdapter_succeeds() {
        // Given
        Section section = new StatelessSectionStub(ITEMS_QTY);

        // When
        sectionAdapter.addSection(SECTION_TAG, section);

        // Then
        assertSame(sectionAdapter.getSection(SECTION_TAG), section);
        assertSame(sectionAdapter.getCopyOfSectionsMap().get(SECTION_TAG), section);
    }

    @Test
    public void getSectionWithTag_withRemovedSection_returnsNull() {
        // Given
        sectionAdapter.addSection(SECTION_TAG, new StatelessSectionStub(ITEMS_QTY));
        sectionAdapter.removeSection(SECTION_TAG);

        // When
        Section result = sectionAdapter.getSection(SECTION_TAG);

        // Then
        assertNull(result);
    }

    @Test
    public void getSectionWithTag_withEmptyAdapter_returnsNull() {
        // When
        Section result = sectionAdapter.getSection(SECTION_TAG);

        // Then
        assertNull(result);
    }

    @Test
    public void removeSectionWithTag_withEmptyAdapter_failsSilently() {
        // When
        sectionAdapter.removeSection(SECTION_TAG);

        // Then
        assertTrue(sectionAdapter.getCopyOfSectionsMap().isEmpty());
    }

    @Test
    public void removeSectionWithTag_withInvalidTag_doesNotRemoveAnything() {
        // Given
        addFourStatelessSectionsAndFourSectionsToAdapter();

        // When
        sectionAdapter.removeSection(SECTION_TAG);

        // Then
        assertThat(sectionAdapter.getCopyOfSectionsMap().size(), is(8));
        assertNull(sectionAdapter.getSection(SECTION_TAG));
    }

    @Test
    public void removeSectionWithTag_withAdapterWithManySections_succeeds() {
        // Given
        addFourStatelessSectionsAndFourSectionsToAdapter();
        sectionAdapter.addSection(SECTION_TAG, new StatelessSectionStub(ITEMS_QTY));

        // When
        sectionAdapter.removeSection(SECTION_TAG);

        // Then
        assertThat(sectionAdapter.getCopyOfSectionsMap().size(), is(8));
        assertNull(sectionAdapter.getSection(SECTION_TAG));
    }

    @Test
    public void removeSection_withEmptyAdapter_failsSilently() {
        // When
        sectionAdapter.removeSection(new SectionStub(ITEMS_QTY));

        // Then
        assertTrue(sectionAdapter.getCopyOfSectionsMap().isEmpty());
    }

    @Test
    public void removeSection_withInvalidSection_doesNotRemoveAnything() {
        // Given
        addFourStatelessSectionsAndFourSectionsToAdapter();

        // When
        sectionAdapter.removeSection(new SectionStub(ITEMS_QTY));

        // Then
        assertThat(sectionAdapter.getCopyOfSectionsMap().size(), is(8));
    }

    @Test
    public void removeSection_withAdapterWithManySections_succeeds() {
        // Given
        addFourStatelessSectionsAndFourSectionsToAdapter();
        final StatelessSectionStub section = new StatelessSectionStub(ITEMS_QTY);
        sectionAdapter.addSection(SECTION_TAG, section);

        // When
        sectionAdapter.removeSection(section);

        // Then
        assertThat(sectionAdapter.getCopyOfSectionsMap().size(), is(8));
        assertNull(sectionAdapter.getSection(SECTION_TAG));
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
        addStatelessSectionStubToAdapter();
        addInvisibleStatelessSectionStubToAdapter();

        // When
        int result = sectionAdapter.getItemCount();

        // Then
        assertThat(result, is(ITEMS_QTY));
    }

    @Test
    public void getCopyOfSectionsMap_withEmptyAdapter_isEmpty() {
        // When
        boolean result = sectionAdapter.getCopyOfSectionsMap().isEmpty();

        // Then
        assertTrue(result);
    }

    @Test
    public void getCopyOfSectionsMap_withAdapterWithInvisibleSection_hasCorrectSize() {
        // Given
        addStatelessSectionStubToAdapter();
        addInvisibleStatelessSectionStubToAdapter();

        // When
        int result = sectionAdapter.getCopyOfSectionsMap().size();

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
        addStatelessSectionStubToAdapter();

        Section section = new StatelessSectionStub(ITEMS_QTY);
        sectionAdapter.addSection(SECTION_TAG, section);

        addStatelessSectionStubToAdapter();

        // When
        Section result = sectionAdapter.getSection(SECTION_TAG);

        // Then
        assertSame(result, section);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getSectionForPosition_withEmptyAdapter_throwsException() {
        // When
        sectionAdapter.getSectionForPosition(0);
    }

    @Test
    public void getSectionForPosition_withAdapterWithManySections_returnsCorrectSection() {
        // Given
        Section sectionStub1 = addStatelessSectionStubToAdapter();
        Section sectionStub2 = addStatelessSectionStubToAdapter();
        Section sectionStub3 = addStatelessSectionStubToAdapter();

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
        int viewTypeHeader = sectionAdapter.getSectionItemViewType(32);
        int viewTypeItemStart = sectionAdapter.getSectionItemViewType(33);
        int viewTypeItemEnd = sectionAdapter.getSectionItemViewType(42);
        int viewTypeFooter = sectionAdapter.getSectionItemViewType(43);

        // Then
        assertThat(viewTypeHeader, is(SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER));
        assertThat(viewTypeItemStart, is(SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED));
        assertThat(viewTypeItemEnd, is(SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED));
        assertThat(viewTypeFooter, is(SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER));
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
        int viewTypeHeader = sectionAdapter.getItemViewType(32);
        int viewTypeItemStart = sectionAdapter.getItemViewType(33);
        int viewTypeItemEnd = sectionAdapter.getItemViewType(42);
        int viewTypeFooter = sectionAdapter.getItemViewType(43);

        // Then
        assertThat(viewTypeHeader, is(18));
        assertThat(viewTypeItemStart, is(20));
        assertThat(viewTypeItemEnd, is(20));
        assertThat(viewTypeFooter, is(19));
    }

    @Test
    public void getItemViewType_withAdapterWithManySections_returnsCorrectValuesForSectionWithLoadingState() {
        // Given
        addStatelessSectionStubToAdapter();
        addHeadedStatelessSectionStubToAdapter();
        addFootedStatelessSectionStubToAdapter();
        addHeadedFootedStatelessSectionStubToAdapter();

        Section section = addSectionStubToAdapter();
        section.setState(State.LOADING);

        addHeadedSectionStubToAdapter();
        addFootedSectionStubToAdapter();
        addHeadedFootedSectionStubToAdapter();

        // When
        int viewTypeLoading = sectionAdapter.getItemViewType(44);

        // Then
        assertThat(viewTypeLoading, is(27));
    }

    @Test
    public void getItemViewType_withAdapterWithManySections_returnsCorrectValuesForSectionWithFailedState() {
        // Given
        addStatelessSectionStubToAdapter();
        addHeadedStatelessSectionStubToAdapter();
        addFootedStatelessSectionStubToAdapter();
        addHeadedFootedStatelessSectionStubToAdapter();

        Section section = addSectionStubToAdapter();
        section.setState(State.FAILED);

        addHeadedSectionStubToAdapter();
        addFootedSectionStubToAdapter();
        addHeadedFootedSectionStubToAdapter();

        // When
        int viewTypeFailed = sectionAdapter.getItemViewType(44);

        // Then
        assertThat(viewTypeFailed, is(28));
    }

    @Test
    public void getItemViewType_withAdapterWithManySections_returnsCorrectValuesForSectionWithEmptyState() {
        // Given
        addStatelessSectionStubToAdapter();
        addHeadedStatelessSectionStubToAdapter();
        addFootedStatelessSectionStubToAdapter();
        addHeadedFootedStatelessSectionStubToAdapter();

        Section section = addSectionStubToAdapter();
        section.setState(State.EMPTY);

        addHeadedSectionStubToAdapter();
        addFootedSectionStubToAdapter();
        addHeadedFootedSectionStubToAdapter();

        // When
        int viewTypeEmpty = sectionAdapter.getItemViewType(44);

        // Then
        assertThat(viewTypeEmpty, is(29));
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
        assertTrue(sectionAdapter.getCopyOfSectionsMap().isEmpty());
    }

    @Test
    public void getPositionInAdapterUsingTag_withAdapterWithManySections_returnsCorrectAdapterPosition() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemInserted(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, new HeadedFootedStatelessSectionStub(ITEMS_QTY));

        // When
        int result = spySectionedRecyclerViewAdapter.getPositionInAdapter(SECTION_TAG, 0);

        // Then
        assertThat(result, is(11));
    }

    @Test
    public void getPositionInAdapterUsingSection_withAdapterWithManySections_returnsCorrectAdapterPosition() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemInserted(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedStatelessSectionStub headedFootedStatelessSectionStub = new HeadedFootedStatelessSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(headedFootedStatelessSectionStub);

        // When
        int result = spySectionedRecyclerViewAdapter.getPositionInAdapter(headedFootedStatelessSectionStub, 0);

        // Then
        assertThat(result, is(11));
    }

    @Test
    public void getHeaderPositionInAdapterUsingTag_withAdapterWithManySections_returnsCorrectAdapterHeaderPosition() {
        // Given
        sectionAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        sectionAdapter.addSection(SECTION_TAG, new HeadedFootedStatelessSectionStub(ITEMS_QTY));

        // When
        int result = sectionAdapter.getHeaderPositionInAdapter(SECTION_TAG);

        // Then
        assertThat(result, is(10));
    }

    @Test(expected = IllegalStateException.class)
    public void getHeaderPositionInAdapterUsingTag_withAdapterWithManySections_throwsIllegalStateException() {
        sectionAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        sectionAdapter.addSection(SECTION_TAG, new StatelessSectionStub(ITEMS_QTY));

        // When
        sectionAdapter.getHeaderPositionInAdapter(SECTION_TAG);
    }

    @Test
    public void getFooterPositionInAdapterUsingTag_withAdapterWithManySections_returnsCorrectAdapterFooterPosition() {
        // Given
        sectionAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        sectionAdapter.addSection(SECTION_TAG, new HeadedFootedStatelessSectionStub(ITEMS_QTY));

        // When
        int result = sectionAdapter.getFooterPositionInAdapter(SECTION_TAG);

        // Then
        assertThat(result, is(21));
    }

    @Test
    public void indexOf() {
        StatelessSectionStub statelessSectionStub_0 = new StatelessSectionStub(ITEMS_QTY);
        StatelessSectionStub statelessSectionStub_1 = new StatelessSectionStub(ITEMS_QTY);
        StatelessSectionStub statelessSectionStub_2 = new StatelessSectionStub(ITEMS_QTY);
        StatelessSectionStub statelessSectionStub_3 = new StatelessSectionStub(ITEMS_QTY);

        sectionAdapter.addSection(statelessSectionStub_0);
        sectionAdapter.addSection(SECTION_TAG, statelessSectionStub_1);
        sectionAdapter.addSection(statelessSectionStub_2);

        assertThat(sectionAdapter.indexOf(statelessSectionStub_0), is(0));
        assertThat(sectionAdapter.indexOf(statelessSectionStub_1), is(1));
        assertThat(sectionAdapter.indexOf(statelessSectionStub_2), is(2));
        assertThat(sectionAdapter.indexOf(statelessSectionStub_3), is(-1));

        statelessSectionStub_0.setVisible(false);
        statelessSectionStub_1.setVisible(false);
        statelessSectionStub_2.setVisible(false);
        statelessSectionStub_3.setVisible(false);

        assertThat(sectionAdapter.indexOf(statelessSectionStub_0), is(0));
        assertThat(sectionAdapter.indexOf(statelessSectionStub_1), is(1));
        assertThat(sectionAdapter.indexOf(statelessSectionStub_2), is(2));
        assertThat(sectionAdapter.indexOf(statelessSectionStub_3), is(-1));
    }

    @Test(expected = IllegalStateException.class)
    public void getFooterPositionInAdapterUsingTag_withAdapterWithManySections_throwsIllegalStateException() {
        sectionAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        sectionAdapter.addSection(SECTION_TAG, new StatelessSectionStub(ITEMS_QTY));

        // When
        sectionAdapter.getFooterPositionInAdapter(SECTION_TAG);
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

    private void addInvisibleStatelessSectionStubToAdapter() {
        Section sectionStub = addStatelessSectionStubToAdapter();
        sectionStub.setVisible(false);
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
