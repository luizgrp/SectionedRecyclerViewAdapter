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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for {@link SectionedRecyclerViewAdapter}
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
    public void getSectionPositionUsingTag_withAdapterWithInvisibleSection_returnsCorrectPosition() {
        // Given
        addStatelessSectionStubToAdapter();
        addInvisibleStatelessSectionStubToAdapter();

        sectionAdapter.addSection(SECTION_TAG, new StatelessSectionStub(ITEMS_QTY));

        // When
        int result = sectionAdapter.getSectionPosition(SECTION_TAG);

        // Then
        assertThat(result, is(10));
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
    public void addSection_withEmptyAdapter_succeeds() {
        // Given
        Section section = new StatelessSectionStub(ITEMS_QTY);

        // When
        sectionAdapter.addSection(SECTION_TAG, section);

        // Then
        assertSame(sectionAdapter.getSection(SECTION_TAG), section);
        assertSame(sectionAdapter.getSectionsMap().get(SECTION_TAG), section);
    }

    @Test
    public void addSectionWithTag_withEmptyAdapter_succeeds() {
        // Given
        Section section = new StatelessSectionStub(ITEMS_QTY);

        // When
        sectionAdapter.addSection(SECTION_TAG, section);

        // Then
        assertSame(sectionAdapter.getSection(SECTION_TAG), section);
        assertSame(sectionAdapter.getSectionsMap().get(SECTION_TAG), section);
    }

    @Test
    public void getSectionWithTag_withRemovedSection_returnsNull() {
        // Given
        sectionAdapter.addSection(SECTION_TAG, new StatelessSectionStub(ITEMS_QTY));

        // When
        sectionAdapter.removeSection(SECTION_TAG);

        // Then
        assertNull(sectionAdapter.getSection(SECTION_TAG));
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
        addStatelessSectionStubToAdapter();
        addInvisibleStatelessSectionStubToAdapter();

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
        addStatelessSectionStubToAdapter();
        addInvisibleStatelessSectionStubToAdapter();

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
        assertTrue(sectionAdapter.getSectionsMap().isEmpty());
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

    @Test(expected = IllegalStateException.class)
    public void getFooterPositionInAdapterUsingTag_withAdapterWithManySections_throwsIllegalStateException() {
        sectionAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        sectionAdapter.addSection(SECTION_TAG, new StatelessSectionStub(ITEMS_QTY));

        // When
        sectionAdapter.getFooterPositionInAdapter(SECTION_TAG);
    }

    @Test
    public void notifyItemInsertedInSectionUsingTag_withAdapterWithManySections_callsSuperNotifyItemInserted() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemInserted(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, new HeadedFootedStatelessSectionStub(ITEMS_QTY));

        // When
        spySectionedRecyclerViewAdapter.notifyItemInsertedInSection(SECTION_TAG, 0);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemInserted(11);
    }

    @Test
    public void notifyItemInsertedInSectionUsingSection_withAdapterWithManySections_callsSuperNotifyItemInserted() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemInserted(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedStatelessSectionStub headedFootedStatelessSectionStub = new HeadedFootedStatelessSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(headedFootedStatelessSectionStub);

        // When
        spySectionedRecyclerViewAdapter.notifyItemInsertedInSection(headedFootedStatelessSectionStub, 0);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemInserted(11);
    }

    @Test
    public void notifyItemRangeInsertedInSectionUsingTag_withAdapterWithManySections_callsSuperNotifyItemRangeInserted() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeInserted(anyInt(), anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, new HeadedFootedStatelessSectionStub(ITEMS_QTY));

        // When
        spySectionedRecyclerViewAdapter.notifyItemRangeInsertedInSection(SECTION_TAG, 0, 4);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeInserted(11, 4);
    }

    @Test
    public void notifyItemRangeInsertedInSectionUsingSection_withAdapterWithManySections_callsSuperNotifyItemRangeInserted() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeInserted(anyInt(), anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedStatelessSectionStub headedFootedStatelessSectionStub = new HeadedFootedStatelessSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(headedFootedStatelessSectionStub);

        // When
        spySectionedRecyclerViewAdapter.notifyItemRangeInsertedInSection(headedFootedStatelessSectionStub, 0, 4);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeInserted(11, 4);
    }

    @Test
    public void notifyItemRemovedFromSectionUsingTag_withAdapterWithManySections_callsSuperNotifyItemRemoved() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRemoved(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, new HeadedFootedStatelessSectionStub(ITEMS_QTY));

        // When
        spySectionedRecyclerViewAdapter.notifyItemRemovedFromSection(SECTION_TAG, 0);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemRemoved(11);
    }

    @Test
    public void notifyItemRemovedFromSectionUsingSection_withAdapterWithManySections_callsSuperNotifyItemRemoved() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRemoved(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedStatelessSectionStub headedFootedStatelessSectionStub = new HeadedFootedStatelessSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(headedFootedStatelessSectionStub);

        // When
        spySectionedRecyclerViewAdapter.notifyItemRemovedFromSection(headedFootedStatelessSectionStub, 0);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemRemoved(11);
    }

    @Test
    public void notifyItemRangeRemovedInSectionUsingTag_withAdapterWithManySections_callsSuperNotifyItemRangeRemoved() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeRemoved(anyInt(), anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, new HeadedFootedStatelessSectionStub(ITEMS_QTY));

        // When
        spySectionedRecyclerViewAdapter.notifyItemRangeRemovedFromSection(SECTION_TAG, 0, 4);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeRemoved(11, 4);
    }

    @Test
    public void notifyItemRangeRemovedInSectionUsingSection_withAdapterWithManySections_callsSuperNotifyItemRangeRemoved() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeRemoved(anyInt(), anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedStatelessSectionStub headedFootedStatelessSectionStub = new HeadedFootedStatelessSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(headedFootedStatelessSectionStub);

        // When
        spySectionedRecyclerViewAdapter.notifyItemRangeRemovedFromSection(headedFootedStatelessSectionStub, 0, 4);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeRemoved(11, 4);
    }

    @Test
    public void notifyItemChangedInSectionUsingTag_withAdapterWithManySections_callsSuperNotifyItemChangedInSection() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, new HeadedFootedStatelessSectionStub(ITEMS_QTY));

        // When
        spySectionedRecyclerViewAdapter.notifyItemChangedInSection(SECTION_TAG, 0);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(11);
    }

    @Test
    public void notifyItemChangedInSectionUsingSection_withAdapterWithManySections_callsSuperNotifyItemChangedInSection() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedStatelessSectionStub headedFootedStatelessSectionStub = new HeadedFootedStatelessSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(headedFootedStatelessSectionStub);

        // When
        spySectionedRecyclerViewAdapter.notifyItemChangedInSection(headedFootedStatelessSectionStub, 0);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(11);
    }

    @Test
    public void notifyHeaderChangedInSectionUsingTag_withAdapterWithManySections_callsSuperNotifyItemChangedInSection() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, new HeadedFootedStatelessSectionStub(ITEMS_QTY));

        // When
        spySectionedRecyclerViewAdapter.notifyHeaderChangedInSection(SECTION_TAG);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(10);
    }

    @Test(expected = IllegalStateException.class)
    public void notifyHeaderChangedInSectionUsingTag_withAdapterWithManySections_throwsIllegalStateException() {
        // Given
        sectionAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        sectionAdapter.addSection(SECTION_TAG, new StatelessSectionStub(ITEMS_QTY));

        // When
        sectionAdapter.notifyHeaderChangedInSection(SECTION_TAG);
    }

    @Test
    public void notifyFooterChangedInSectionUsingTag_withAdapterWithManySections_callsSuperNotifyItemChangedInSection() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, new HeadedFootedStatelessSectionStub(ITEMS_QTY));

        // When
        spySectionedRecyclerViewAdapter.notifyFooterChangedInSection(SECTION_TAG);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(21);
    }

    @Test(expected = IllegalStateException.class)
    public void notifyFooterChangedInSectionUsingTag_withAdapterWithManySections_throwsIllegalStateException() {
        // Given
        sectionAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        sectionAdapter.addSection(SECTION_TAG, new StatelessSectionStub(ITEMS_QTY));

        // When
        sectionAdapter.notifyFooterChangedInSection(SECTION_TAG);
    }
    
    @Test
    public void notifyItemRangeChangedInSectionUsingTag_withAdapterWithManySections_callsSuperNotifyItemRangeChanged() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeChanged(anyInt(), anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, new HeadedFootedStatelessSectionStub(ITEMS_QTY));

        // When
        spySectionedRecyclerViewAdapter.notifyItemRangeChangedInSection(SECTION_TAG, 0, 4);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeChanged(11, 4);
    }

    @Test
    public void notifyItemRangeChangedInSectionUsingSection_withAdapterWithManySections_callsSuperNotifyItemRangeChanged() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeChanged(anyInt(), anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedStatelessSectionStub headedFootedStatelessSectionStub = new HeadedFootedStatelessSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(headedFootedStatelessSectionStub);

        // When
        spySectionedRecyclerViewAdapter.notifyItemRangeChangedInSection(headedFootedStatelessSectionStub, 0, 4);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeChanged(11, 4);
    }

    @Test
    public void notifyItemRangeChangedInSectionWithPayloadUsingTag_withAdapterWithManySections_callsSuperNotifyItemRangeChanged() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeChanged(anyInt(), anyInt(), any());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, new HeadedFootedStatelessSectionStub(ITEMS_QTY));

        // When
        spySectionedRecyclerViewAdapter.notifyItemRangeChangedInSection(SECTION_TAG, 0, 4, null);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeChanged(11, 4, null);
    }

    @Test
    public void notifyItemRangeChangedInSectionWithPayloadUsingSection_withAdapterWithManySections_callsSuperNotifyItemRangeChanged() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeChanged(anyInt(), anyInt(), any());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedStatelessSectionStub headedFootedStatelessSectionStub = new HeadedFootedStatelessSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(headedFootedStatelessSectionStub);

        // When
        spySectionedRecyclerViewAdapter.notifyItemRangeChangedInSection(headedFootedStatelessSectionStub, 0, 4, null);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeChanged(11, 4, null);
    }

    @Test
    public void notifyItemMovedInSectionUsingTag_withAdapterWithManySections_callsSuperNotifyItemMoved() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemMoved(anyInt(), anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, new HeadedFootedStatelessSectionStub(ITEMS_QTY));

        // When
        spySectionedRecyclerViewAdapter.notifyItemMovedInSection(SECTION_TAG, 0, 4);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemMoved(11, 15);
    }

    @Test
    public void notifyItemMovedInSectionUsingSection_withAdapterWithManySections_callsSuperNotifyItemMoved() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemMoved(anyInt(), anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedStatelessSectionStub headedFootedStatelessSectionStub = new HeadedFootedStatelessSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(headedFootedStatelessSectionStub);

        // When
        spySectionedRecyclerViewAdapter.notifyItemMovedInSection(headedFootedStatelessSectionStub, 0, 4);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemMoved(11, 15);
    }

    @Test
    public void notifyNotLoadedStateChangedUsingTag_withAdapterWithManySections_callsNotifyItemChanged() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(State.LOADING);

        // When
        State previousState = headedFootedSectionStub.getState();
        headedFootedSectionStub.setState(State.EMPTY);
        spySectionedRecyclerViewAdapter.notifyNotLoadedStateChanged(SECTION_TAG, previousState);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(11);
    }

    @Test(expected = IllegalStateException.class)
    public void  notifyNotLoadedStateChangedUsingTag_withNoStateChange_throwsException() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(State.LOADING);

        // When
        spySectionedRecyclerViewAdapter.notifyNotLoadedStateChanged(SECTION_TAG, headedFootedSectionStub.getState());
    }

    @Test(expected = IllegalStateException.class)
    public void  notifyNotLoadedStateChangedUsingTag_withLoadedAsPreviousState_throwsException() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(State.LOADING);

        // When
        spySectionedRecyclerViewAdapter.notifyNotLoadedStateChanged(SECTION_TAG, State.LOADED);
    }

    @Test(expected = IllegalStateException.class)
    public void  notifyNotLoadedStateChangedUsingTag_withLoadedAsCurrentState_throwsException() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(State.LOADING);

        // When
        State previousState = headedFootedSectionStub.getState();
        headedFootedSectionStub.setState(State.LOADED);
        spySectionedRecyclerViewAdapter.notifyNotLoadedStateChanged(SECTION_TAG, previousState);
    }

    @Test
    public void notifyStateChangedToLoadedUsingTag_withAdapterWithManySections_callsNotifyItemChanged_callsNotifyItemInserted() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeInserted(anyInt(), anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(State.LOADING);

        // When
        State previousState = headedFootedSectionStub.getState();
        headedFootedSectionStub.setState(State.LOADED);
        spySectionedRecyclerViewAdapter.notifyStateChangedToLoaded(SECTION_TAG, previousState);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(11);
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeInserted(12, 9);
    }

    @Test(expected = IllegalStateException.class)
    public void notifyStateChangedToLoadedUsingTag_withNoStateChange_throwsException() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(State.LOADED);

        // When
        spySectionedRecyclerViewAdapter.notifyStateChangedToLoaded(SECTION_TAG, headedFootedSectionStub.getState());
    }

    @Test(expected = IllegalStateException.class)
    public void notifyStateChangedToLoadedUsingTag_withCurrentStateNotLoadedAndLoadedAsPreviousState_throwsException() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(State.LOADED);

        // When
        State previousState = headedFootedSectionStub.getState();
        headedFootedSectionStub.setState(State.EMPTY);
        spySectionedRecyclerViewAdapter.notifyStateChangedToLoaded(SECTION_TAG, previousState);
    }

    @Test(expected = IllegalStateException.class)
    public void notifyStateChangedToLoadedUsingTag_withCurrentStateNotLoadedAndPreviousStateNotLoaded_throwsException() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(State.LOADING);

        // When
        State previousState = headedFootedSectionStub.getState();
        headedFootedSectionStub.setState(State.EMPTY);
        spySectionedRecyclerViewAdapter.notifyStateChangedToLoaded(SECTION_TAG, previousState);
    }

    @Test
    public void notifyStateChangedToLoadedUsingTag_withContentItemsTotal0_callsNotifyItemRemoved() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRemoved(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(0);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(State.LOADING);

        // When
        State previousState = headedFootedSectionStub.getState();
        headedFootedSectionStub.setState(State.LOADED);
        spySectionedRecyclerViewAdapter.notifyStateChangedToLoaded(SECTION_TAG, previousState);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemRemoved(11);
    }

    @Test
    public void notifyStateChangedToLoadedUsingTag_withContentItemsTotal1_callsNotifyItemChanged() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(1);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(State.LOADING);

        // When
        State previousState = headedFootedSectionStub.getState();
        headedFootedSectionStub.setState(State.LOADED);
        spySectionedRecyclerViewAdapter.notifyStateChangedToLoaded(SECTION_TAG, previousState);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(11);
        verify(spySectionedRecyclerViewAdapter, never()).callSuperNotifyItemRangeInserted(anyInt(), anyInt());
    }

    @Test
    public void notifyStateChangedFromLoadedUsingTag_withAdapterWithManySections_callsNotifyItemRangeRemoved_callsNotifyItemChanged() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeRemoved(anyInt(), anyInt());
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(State.LOADED);

        // When
        int previousContentItemsTotal = headedFootedSectionStub.getContentItemsTotal();
        headedFootedSectionStub.setState(State.EMPTY);
        spySectionedRecyclerViewAdapter.notifyStateChangedFromLoaded(SECTION_TAG, previousContentItemsTotal);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeRemoved(12, 9);
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(11);
    }

    @Test(expected = IllegalStateException.class)
    public void notifyStateChangedFromLoadedUsingTag_withLoadedAsCurrentState_throwsException() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeRemoved(anyInt(), anyInt());
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(State.LOADED);

        // When
        spySectionedRecyclerViewAdapter.notifyStateChangedFromLoaded(SECTION_TAG, headedFootedSectionStub.getContentItemsTotal());
    }

    @Test
    public void notifyStateChangedFromLoadedUsingTag_withPreviousContentItemsCount0_callsNotifyItemInserted() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemInserted(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(0);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(State.LOADED);

        // When
        int previousContentItemsTotal = headedFootedSectionStub.getContentItemsTotal();
        headedFootedSectionStub.setState(State.EMPTY);
        spySectionedRecyclerViewAdapter.notifyStateChangedFromLoaded(SECTION_TAG, previousContentItemsTotal);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemInserted(11);
    }

    @Test
    public void notifyStateChangedFromLoadedUsingTag_withPreviousContentItemsCount1_callsNotifyItemChanged() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(1);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(State.LOADED);

        // When
        int previousContentItemsTotal = headedFootedSectionStub.getContentItemsTotal();
        headedFootedSectionStub.setState(State.EMPTY);
        spySectionedRecyclerViewAdapter.notifyStateChangedFromLoaded(SECTION_TAG, previousContentItemsTotal);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(11);
        verify(spySectionedRecyclerViewAdapter, never()).callSuperNotifyItemRangeRemoved(anyInt(), anyInt());
    }

    @Test
    public void notifyHeaderInsertedInSectionUsingTag_withAdapterWithManySections_callsSuperNotifyItemInserted() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemInserted(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        headedFootedSectionStub.setHasHeader(false);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);

        // When
        headedFootedSectionStub.setHasHeader(true);
        spySectionedRecyclerViewAdapter.notifyHeaderInsertedInSection(SECTION_TAG);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemInserted(10);
    }

    @Test
    public void notifyFooterInsertedInSectionUsingTag_withAdapterWithManySections_callsSuperNotifyItemInserted() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemInserted(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        headedFootedSectionStub.setHasFooter(false);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);

        // When
        headedFootedSectionStub.setHasFooter(true);
        spySectionedRecyclerViewAdapter.notifyFooterInsertedInSection(SECTION_TAG);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemInserted(21);
    }

    @Test
    public void notifyHeaderRemovedFromSectionUsingTag_withAdapterWithManySections_callsSuperNotifyItemRemoved() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRemoved(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        headedFootedSectionStub.setHasHeader(true);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);

        // When
        headedFootedSectionStub.setHasHeader(false);
        spySectionedRecyclerViewAdapter.notifyHeaderRemovedFromSection(SECTION_TAG);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemRemoved(10);
    }

    @Test
    public void notifyFooterRemovedFromSectionUsingTag_withAdapterWithManySections_callsSuperNotifyItemRemoved() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRemoved(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        headedFootedSectionStub.setHasFooter(true);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);

        // When
        headedFootedSectionStub.setHasFooter(false);
        spySectionedRecyclerViewAdapter.notifyFooterRemovedFromSection(SECTION_TAG);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemRemoved(21);
    }

    @Test(expected = IllegalStateException.class)
    public void notifySectionChangedToVisibleUsingTag_withInvisibleSection_throwsException() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeInserted(anyInt(), anyInt());

        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        headedFootedSectionStub.setVisible(false);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);

        // When
        spySectionedRecyclerViewAdapter.notifySectionChangedToVisible(SECTION_TAG);
    }

    @Test(expected = IllegalStateException.class)
    public void notifySectionChangedToInvisibleUsingTag_withVisibleSection_throwsException() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeRemoved(anyInt(), anyInt());
        
        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        headedFootedSectionStub.setVisible(true);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);

        // When
        int previousSectionPosition = spySectionedRecyclerViewAdapter.getSectionPosition(headedFootedSectionStub);
        spySectionedRecyclerViewAdapter.notifySectionChangedToInvisible(SECTION_TAG, previousSectionPosition);
    }

    @Test
    public void notifySectionChangedToVisibleUsingTag_withAdapterWithManySections_callsSuperNotifyItemRangeInserted() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeInserted(anyInt(), anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        headedFootedSectionStub.setVisible(false);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);

        // When
        headedFootedSectionStub.setVisible(true);
        spySectionedRecyclerViewAdapter.notifySectionChangedToVisible(SECTION_TAG);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeInserted(10, 12);
    }

    @Test
    public void notifySectionChangedToInvisibleUsingTag_withAdapterWithManySections_callsSuperNotifyItemRangeInserted() {
        // Given
        SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter = spy(SectionedRecyclerViewAdapter.class);
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeRemoved(anyInt(), anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        headedFootedSectionStub.setVisible(true);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);

        // When
        int previousSectionPosition = spySectionedRecyclerViewAdapter.getSectionPosition(headedFootedSectionStub);
        headedFootedSectionStub.setVisible(false);
        spySectionedRecyclerViewAdapter.notifySectionChangedToInvisible(SECTION_TAG, previousSectionPosition);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeRemoved(10, 12);
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
