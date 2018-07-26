package io.github.luizgrp.sectionedrecyclerviewadapter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.HeadedFootedSectionStub;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.HeadedFootedStatelessSectionStub;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.StatelessSectionStub;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SuppressWarnings({"PMD.MethodNamingConventions"})
public class SectionedRecyclerViewAdapterNotifyTest {

    private static final int ITEMS_QTY = 10;
    private static final String SECTION_TAG = "tag";

    @Spy
    private SectionedRecyclerViewAdapter spySectionedRecyclerViewAdapter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void notifyItemInsertedInSectionUsingTag_withAdapterWithManySections_callsSuperNotifyItemInserted() {
        // Given
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
    public void notifyAllItemsInsertedInSectionUsingTag_withAdapterWithManySections_callsSuperNotifyItemRangeInserted() {
        // Given
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeInserted(anyInt(), anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, new HeadedFootedStatelessSectionStub(ITEMS_QTY));

        // When
        spySectionedRecyclerViewAdapter.notifyAllItemsInsertedInSection(SECTION_TAG);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeInserted(11, ITEMS_QTY);
    }

    @Test
    public void notifyAllItemsInsertedInSection_withAdapterWithManySections_callsSuperNotifyItemRangeInserted() {
        // Given
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeInserted(anyInt(), anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedStatelessSectionStub headedFootedStatelessSectionStub = new HeadedFootedStatelessSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(headedFootedStatelessSectionStub);

        // When
        spySectionedRecyclerViewAdapter.notifyAllItemsInsertedInSection(headedFootedStatelessSectionStub);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeInserted(11, ITEMS_QTY);
    }

    @Test
    public void notifyItemRangeInsertedInSectionUsingTag_withAdapterWithManySections_callsSuperNotifyItemRangeInserted() {
        // Given
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
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();
        sectionAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        sectionAdapter.addSection(SECTION_TAG, new StatelessSectionStub(ITEMS_QTY));

        // When
        sectionAdapter.notifyHeaderChangedInSection(SECTION_TAG);
    }

    @Test
    public void notifyFooterChangedInSectionUsingTag_withAdapterWithManySections_callsSuperNotifyItemChangedInSection() {
        // Given
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
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();
        sectionAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        sectionAdapter.addSection(SECTION_TAG, new StatelessSectionStub(ITEMS_QTY));

        // When
        sectionAdapter.notifyFooterChangedInSection(SECTION_TAG);
    }

    @Test
    public void notifyAllItemsChangedInSectionUsingTag_withAdapterWithManySections_callsSuperNotifyItemRangeChanged() {
        // Given
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeChanged(anyInt(), anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, new HeadedFootedStatelessSectionStub(ITEMS_QTY));

        // When
        spySectionedRecyclerViewAdapter.notifyAllItemsChangedInSection(SECTION_TAG);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeChanged(11, ITEMS_QTY);
    }

    @Test
    public void notifyAllItemsChangedInSectionUsingSection_withAdapterWithManySections_callsSuperNotifyItemRangeChanged() {
        // Given
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeChanged(anyInt(), anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedStatelessSectionStub headedFootedStatelessSectionStub = new HeadedFootedStatelessSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(headedFootedStatelessSectionStub);

        // When
        spySectionedRecyclerViewAdapter.notifyAllItemsChangedInSection(headedFootedStatelessSectionStub);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeChanged(11, ITEMS_QTY);
    }

    @Test
    public void notifyItemRangeChangedInSectionUsingTag_withAdapterWithManySections_callsSuperNotifyItemRangeChanged() {
        // Given
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
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(Section.State.LOADING);

        // When
        Section.State previousState = headedFootedSectionStub.getState();
        headedFootedSectionStub.setState(Section.State.EMPTY);
        spySectionedRecyclerViewAdapter.notifyNotLoadedStateChanged(SECTION_TAG, previousState);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(11);
    }

    @Test(expected = IllegalStateException.class)
    public void notifyNotLoadedStateChangedUsingTag_withNoStateChange_throwsException() {
        // Given
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(Section.State.LOADING);

        // When
        spySectionedRecyclerViewAdapter.notifyNotLoadedStateChanged(SECTION_TAG, headedFootedSectionStub.getState());
    }

    @Test(expected = IllegalStateException.class)
    public void notifyNotLoadedStateChangedUsingTag_withLoadedAsPreviousState_throwsException() {
        // Given
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(Section.State.LOADING);

        // When
        spySectionedRecyclerViewAdapter.notifyNotLoadedStateChanged(SECTION_TAG, Section.State.LOADED);
    }

    @Test(expected = IllegalStateException.class)
    public void notifyNotLoadedStateChangedUsingTag_withLoadedAsCurrentState_throwsException() {
        // Given
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(Section.State.LOADING);

        // When
        Section.State previousState = headedFootedSectionStub.getState();
        headedFootedSectionStub.setState(Section.State.LOADED);
        spySectionedRecyclerViewAdapter.notifyNotLoadedStateChanged(SECTION_TAG, previousState);
    }

    @Test
    public void notifyStateChangedToLoadedUsingTag_withAdapterWithManySections_callsNotifyItemChanged_callsNotifyItemInserted() {
        // Given
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeInserted(anyInt(), anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(Section.State.LOADING);

        // When
        Section.State previousState = headedFootedSectionStub.getState();
        headedFootedSectionStub.setState(Section.State.LOADED);
        spySectionedRecyclerViewAdapter.notifyStateChangedToLoaded(SECTION_TAG, previousState);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(11);
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeInserted(12, 9);
    }

    @Test(expected = IllegalStateException.class)
    public void notifyStateChangedToLoadedUsingTag_withNoStateChange_throwsException() {
        // Given
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(Section.State.LOADED);

        // When
        spySectionedRecyclerViewAdapter.notifyStateChangedToLoaded(SECTION_TAG, headedFootedSectionStub.getState());
    }

    @Test(expected = IllegalStateException.class)
    public void notifyStateChangedToLoadedUsingTag_withCurrentStateNotLoadedAndLoadedAsPreviousState_throwsException() {
        // Given
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(Section.State.LOADED);

        // When
        Section.State previousState = headedFootedSectionStub.getState();
        headedFootedSectionStub.setState(Section.State.EMPTY);
        spySectionedRecyclerViewAdapter.notifyStateChangedToLoaded(SECTION_TAG, previousState);
    }

    @Test(expected = IllegalStateException.class)
    public void notifyStateChangedToLoadedUsingTag_withCurrentStateNotLoadedAndPreviousStateNotLoaded_throwsException() {
        // Given
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(Section.State.LOADING);

        // When
        Section.State previousState = headedFootedSectionStub.getState();
        headedFootedSectionStub.setState(Section.State.EMPTY);
        spySectionedRecyclerViewAdapter.notifyStateChangedToLoaded(SECTION_TAG, previousState);
    }

    @Test
    public void notifyStateChangedToLoadedUsingTag_withContentItemsTotal0_callsNotifyItemRemoved() {
        // Given
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRemoved(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(0);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(Section.State.LOADING);

        // When
        Section.State previousState = headedFootedSectionStub.getState();
        headedFootedSectionStub.setState(Section.State.LOADED);
        spySectionedRecyclerViewAdapter.notifyStateChangedToLoaded(SECTION_TAG, previousState);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemRemoved(11);
    }

    @Test
    public void notifyStateChangedToLoadedUsingTag_withContentItemsTotal1_callsNotifyItemChanged() {
        // Given
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(1);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(Section.State.LOADING);

        // When
        Section.State previousState = headedFootedSectionStub.getState();
        headedFootedSectionStub.setState(Section.State.LOADED);
        spySectionedRecyclerViewAdapter.notifyStateChangedToLoaded(SECTION_TAG, previousState);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(11);
        verify(spySectionedRecyclerViewAdapter, never()).callSuperNotifyItemRangeInserted(anyInt(), anyInt());
    }

    @Test
    public void notifyStateChangedFromLoadedUsingTag_withAdapterWithManySections_callsNotifyItemRangeRemoved_callsNotifyItemChanged() {
        // Given
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeRemoved(anyInt(), anyInt());
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(Section.State.LOADED);

        // When
        int previousContentItemsTotal = headedFootedSectionStub.getContentItemsTotal();
        headedFootedSectionStub.setState(Section.State.EMPTY);
        spySectionedRecyclerViewAdapter.notifyStateChangedFromLoaded(SECTION_TAG, previousContentItemsTotal);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeRemoved(12, 9);
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(11);
    }

    @Test(expected = IllegalStateException.class)
    public void notifyStateChangedFromLoadedUsingTag_withLoadedAsCurrentState_throwsException() {
        // Given
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemRangeRemoved(anyInt(), anyInt());
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(ITEMS_QTY);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(Section.State.LOADED);

        // When
        spySectionedRecyclerViewAdapter.notifyStateChangedFromLoaded(SECTION_TAG, headedFootedSectionStub.getContentItemsTotal());
    }

    @Test
    public void notifyStateChangedFromLoadedUsingTag_withPreviousContentItemsCount0_callsNotifyItemInserted() {
        // Given
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemInserted(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(0);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(Section.State.LOADED);

        // When
        int previousContentItemsTotal = headedFootedSectionStub.getContentItemsTotal();
        headedFootedSectionStub.setState(Section.State.EMPTY);
        spySectionedRecyclerViewAdapter.notifyStateChangedFromLoaded(SECTION_TAG, previousContentItemsTotal);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemInserted(11);
    }

    @Test
    public void notifyStateChangedFromLoadedUsingTag_withPreviousContentItemsCount1_callsNotifyItemChanged() {
        // Given
        doNothing().when(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(anyInt());

        spySectionedRecyclerViewAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));
        HeadedFootedSectionStub headedFootedSectionStub = new HeadedFootedSectionStub(1);
        spySectionedRecyclerViewAdapter.addSection(SECTION_TAG, headedFootedSectionStub);
        headedFootedSectionStub.setState(Section.State.LOADED);

        // When
        int previousContentItemsTotal = headedFootedSectionStub.getContentItemsTotal();
        headedFootedSectionStub.setState(Section.State.EMPTY);
        spySectionedRecyclerViewAdapter.notifyStateChangedFromLoaded(SECTION_TAG, previousContentItemsTotal);

        // Then
        verify(spySectionedRecyclerViewAdapter).callSuperNotifyItemChanged(11);
        verify(spySectionedRecyclerViewAdapter, never()).callSuperNotifyItemRangeRemoved(anyInt(), anyInt());
    }

    @Test
    public void notifyHeaderInsertedInSectionUsingTag_withAdapterWithManySections_callsSuperNotifyItemInserted() {
        // Given
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
}
