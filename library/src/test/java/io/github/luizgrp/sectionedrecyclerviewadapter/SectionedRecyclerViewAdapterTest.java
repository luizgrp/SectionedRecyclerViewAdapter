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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

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

    @Test
    public void getPositionInSection_withAdapterWithInvisibleSection_returnsCorrectPosition() {
        // Given
        addHeadedFootedStatelessSectionStubToAdapter();

        Section section = addStatelessSectionStubToAdapter();
        section.setVisible(false);

        addHeadedFootedStatelessSectionStubToAdapter();

        // When
        int result = sectionAdapter.getPositionInSection(13);
        int result2 = sectionAdapter.getPositionInSection(22);

        // Then
        assertThat(result, is(0));
        assertThat(result2, is(9));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getSectionPosition_withEmptyAdapter_throwsException() {
        // When
        sectionAdapter.getSectionPosition(SECTION_TAG);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getSectionPosition_withInvalidTag_throwsException() {
        // Given
        addStatelessSectionStubToAdapter();
        addStatelessSectionStubToAdapter();

        // When
        sectionAdapter.getSectionPosition(SECTION_TAG);
    }

    @Test
    public void getSectionPosition_withAdapterWithInvisibleSection_returnsCorrectPosition() {
        // Given
        addStatelessSectionStubToAdapter();

        sectionAdapter.addSection(SECTION_TAG, new StatelessSectionStub(ITEMS_QTY));

        // When
        int result = sectionAdapter.getSectionPosition(SECTION_TAG);

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

        Section section = addStatelessSectionStubToAdapter();
        section.setVisible(false);

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

        Section section = addStatelessSectionStubToAdapter();
        section.setVisible(false);

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

    @Test
    public void notifyItemInsertedInSection_withAdapterWithManySections_callsSuperNotifyItemInserted() {
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
    public void notifyItemRangeInsertedInSection_withAdapterWithManySections_callsSuperNotifyItemRangeInserted() {
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
    public void notifyItemRemovedFromSection_withAdapterWithManySections_callsSuperNotifyItemRemoved() {
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
    public void notifyItemRangeRemovedInSection_withAdapterWithManySections_callsSuperNotifyItemRangeRemoved() {
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
    public void notifyItemChangedInSection_withAdapterWithManySections_callsSuperNotifyItemChangedInSection() {
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
    public void notifyItemRangeChangedInSection_withAdapterWithManySections_callsSuperNotifyItemRangeChanged() {
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
    public void notifyItemRangeChangedInSectionWithPayload_withAdapterWithManySections_callsSuperNotifyItemRangeChanged() {
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
    public void notifyItemMovedInSection_withAdapterWithManySections_callsSuperNotifyItemMoved() {
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
