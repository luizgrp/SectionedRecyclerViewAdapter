package io.github.luizgrp.sectionedrecyclerviewadapter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.github.luizgrp.sectionedrecyclerviewadapter.tools.Scenario1;
import io.github.luizgrp.sectionedrecyclerviewadapter.tools.TestScenario;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings({"PMD.MethodNamingConventions"})
public class SectionNotifierScenario1Test {

    @Mock
    private SectionedRecyclerViewAdapter sectionedAdapter;

    private TestScenario scenario;
    private Section section;

    private SectionNotifier cut;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        scenario = new Scenario1();
        section = scenario.getSection();

        when(sectionedAdapter.getSections()).thenReturn(scenario.getSections());

        cut = new SectionAdapter(sectionedAdapter, section);
    }

    @Test
    public void givenScenario_whenNotifyItemInserted_thenNotifyItemInsertedIsCalledWithPositionInAdapter() {
        // Given

        // When
        cut.notifyItemInserted(scenario.getFirstSectionItemPositionInSection());

        // Then
        verify(sectionedAdapter).notifyItemInserted(scenario.getFirstSectionContentItemPositionInAdapter());
    }

    @Test
    public void givenScenario_whenNotifyAllItemsInserted_thenNotifyItemRangeInsertedWithPositionRangeInAdapter() {
        // Given

        // When
        cut.notifyAllItemsInserted();

        // Then
        verify(sectionedAdapter).notifyItemRangeInserted(
                scenario.getFirstSectionContentItemPositionInAdapter(),
                scenario.getSectionContentItemsTotal()
        );
    }

    @Test
    public void givenScenario_whenNotifyItemRangeInserted_thenNotifyItemRangeInsertedWithPositionRangeInAdapter() {
        // Given

        // When
        cut.notifyItemRangeInserted(scenario.getFirstSectionItemPositionInSection(), scenario.getSectionContentItemsTotal());

        // Then
        verify(sectionedAdapter).notifyItemRangeInserted(
                scenario.getFirstSectionContentItemPositionInAdapter(),
                scenario.getSectionContentItemsTotal()
        );
    }

    @Test
    public void givenScenario_whenNotifyItemRemoved_thenNotifyItemRemovedWithPositionInAdapter() {
        // Given

        // When
        cut.notifyItemRemoved(scenario.getFirstSectionItemPositionInSection());

        // Then
        verify(sectionedAdapter).notifyItemRemoved(scenario.getFirstSectionContentItemPositionInAdapter());
    }

    @Test
    public void givenScenario_whenNotifyItemRangeRemoved_thenNotifyItemRangeRemovedWithPositionRangeInAdapter() {
        // Given
        int numberOfItemsRemoved = 4;

        // When
        cut.notifyItemRangeRemoved(scenario.getFirstSectionItemPositionInSection(), numberOfItemsRemoved);

        // Then
        verify(sectionedAdapter).notifyItemRangeRemoved(scenario.getFirstSectionContentItemPositionInAdapter(), numberOfItemsRemoved);
    }

    @Test
    public void givenScenario_whenNotifyHeaderChanged_thenNotifyHeaderChangedWithPositionInAdapter() {
        // Given

        // When
        cut.notifyHeaderChanged();

        // Then
        verify(sectionedAdapter).notifyItemChanged(scenario.getSectionHeaderPositionInAdapter());
    }

    @Test
    public void givenPayload_whenNotifyHeaderChanged_thenNotifyHeaderChangedWithPositionInAdapterAndPayload() {
        // Given
        final Object payload = new Object();

        // When
        cut.notifyHeaderChanged(payload);

        // Then
        verify(sectionedAdapter).notifyItemChanged(scenario.getSectionHeaderPositionInAdapter(), payload);
    }

    @Test
    public void givenScenario_whenNotifyFooterChanged_thenNotifyFooterChangedWithAdapterPosition() {
        // Given

        // When
        cut.notifyFooterChanged();

        // Then
        verify(sectionedAdapter).notifyItemChanged(scenario.getSectionFooterPositionInAdapter());
    }

    @Test
    public void givenPayload_whenNotifyFooterChanged_thenNotifyFooterChangedWithAdapterPositionAndPayload() {
        // Given
        final Object payload = new Object();

        // When
        cut.notifyFooterChanged(payload);

        // Then
        verify(sectionedAdapter).notifyItemChanged(scenario.getSectionFooterPositionInAdapter(), payload);
    }

    @Test
    public void givenScenario_whenNotifyItemChanged_thenNotifyItemChangedWithPositionInAdapter() {
        // Given

        // When
        cut.notifyItemChanged(scenario.getFirstSectionItemPositionInSection());

        // Then
        verify(sectionedAdapter).notifyItemChanged(scenario.getFirstSectionContentItemPositionInAdapter());
    }

    @Test
    public void givenPayload_whenNotifyItemChanged_thenNotifyItemChangedWithPositionInAdapterAndPayload() {
        // Given
        final Object payload = new Object();

        // When
        cut.notifyItemChanged(scenario.getFirstSectionItemPositionInSection(), payload);

        // Then
        verify(sectionedAdapter).notifyItemChanged(scenario.getFirstSectionContentItemPositionInAdapter(), payload);
    }

    @Test
    public void givenScenario_whenNotifyAllItemsChanged_thenNotifyItemRangeChangedWithPositionRangeInAdapter() {
        // Given

        // When
        cut.notifyAllItemsChanged();

        // Then
        verify(sectionedAdapter).notifyItemRangeChanged(
                scenario.getFirstSectionContentItemPositionInAdapter(),
                scenario.getSectionContentItemsTotal()
        );
    }

    @Test
    public void givenPayload_whenNotifyAllItemsChanged_thenNotifyItemRangeChangedWithPositionRangeInAdapterAndPayload() {
        // Given
        final Object payload = new Object();

        // When
        cut.notifyAllItemsChanged(payload);

        // Then
        verify(sectionedAdapter).notifyItemRangeChanged(
                scenario.getFirstSectionContentItemPositionInAdapter(),
                scenario.getSectionContentItemsTotal(),
                payload
        );
    }

    @Test
    public void givenScenario_whenNotifyItemRangeChanged_thenNotifyItemRangeChangedWithPositionRangeInAdapter() {
        // Given
        int sectionItemCount = 4;

        // When
        cut.notifyItemRangeChanged(scenario.getFirstSectionItemPositionInSection(), sectionItemCount);

        // Then
        verify(sectionedAdapter).notifyItemRangeChanged(scenario.getFirstSectionContentItemPositionInAdapter(), sectionItemCount);
    }

    @Test
    public void givenPayload_whenNotifyItemRangeChanged_thenNotifyItemRangeChangedWithPositionRangeInAdapterAndPayload() {
        // Given
        int sectionItemCount = 4;
        Object payload = mock(Object.class);

        // When
        cut.notifyItemRangeChanged(scenario.getFirstSectionItemPositionInSection(), sectionItemCount, payload);

        // Then
        verify(sectionedAdapter).notifyItemRangeChanged(scenario.getFirstSectionContentItemPositionInAdapter(), sectionItemCount, payload);
    }

    @Test
    public void givenScenario_whenNotifyItemMoved_thenNotifyItemMovedWithPositionRangeInAdapter() {
        // Given
        int itemFromPositionInSection = 0;
        int itemToPositionInSection = 4;
        int itemFromPositionInAdapter = 13;
        int itemToPositionInAdapter = 17;

        // When
        cut.notifyItemMoved(itemFromPositionInSection, itemToPositionInSection);

        // Then
        verify(sectionedAdapter).notifyItemMoved(itemFromPositionInAdapter, itemToPositionInAdapter);
    }

    @Test
    public void givenDifferentPreviousState_whenNotifyNotLoadedStateChanged_thenNotifyItemChanged() {
        // Given
        Section.State previousState = Section.State.LOADING;
        when(section.getState()).thenReturn(Section.State.FAILED);

        // When
        cut.notifyNotLoadedStateChanged(previousState);

        // Then
        verify(sectionedAdapter).notifyItemChanged(scenario.getFirstSectionContentItemPositionInAdapter());
    }

    @Test(expected = IllegalStateException.class)
    public void givenSamePreviousState_whenNotifyNotLoadedStateChanged_thenThrowsException() {
        // Given
        Section.State previousState = Section.State.LOADING;
        when(section.getState()).thenReturn(Section.State.LOADING);

        // When
        cut.notifyNotLoadedStateChanged(previousState);
    }

    @Test(expected = IllegalStateException.class)
    public void givenPreviousStateIsLoaded_whenNotifyNotLoadedStateChanged_thenThrowsException() {
        // Given
        Section.State previousState = Section.State.LOADED;
        when(section.getState()).thenReturn(Section.State.LOADING);

        // When
        cut.notifyNotLoadedStateChanged(previousState);
    }

    @Test(expected = IllegalStateException.class)
    public void givenPreviousIsLoading_whenNotifyNotLoadedStateChanged_thenThrowsException() {
        // Given
        Section.State previousState = Section.State.LOADING;
        when(section.getState()).thenReturn(Section.State.LOADED);

        // When
        cut.notifyNotLoadedStateChanged(previousState);
    }

    @Test
    public void givenPreviousStateIsLoading_whenNotifyStateChangedToLoaded_thenNotifyItemChanged_andNotifyItemRangeInserted() {
        // Given
        Section.State previousState = Section.State.LOADING;
        when(section.getState()).thenReturn(Section.State.LOADED);

        // When
        cut.notifyStateChangedToLoaded(previousState);

        // Then
        verify(sectionedAdapter).notifyItemChanged(scenario.getFirstSectionContentItemPositionInAdapter());
        verify(sectionedAdapter).notifyItemRangeInserted(
                scenario.getFirstSectionContentItemPositionInAdapter() + 1,
                scenario.getSectionContentItemsTotal() - 1
        );
    }

    @Test(expected = IllegalStateException.class)
    public void givenSamePreviousState_whenNotifyStateChangedToLoaded_thenThrowsException() {
        // Given
        Section.State previousState = Section.State.LOADED;
        when(section.getState()).thenReturn(Section.State.LOADED);

        // When
        cut.notifyStateChangedToLoaded(previousState);
    }

    @Test(expected = IllegalStateException.class)
    public void givenCurrentStateIsNotLoadedAndPreviousStateIsLoaded_whenNotifyStateChangedToLoaded_thenThrowsException() {
        // Given
        Section.State previousState = Section.State.LOADED;
        when(section.getState()).thenReturn(Section.State.LOADING);

        // When
        cut.notifyStateChangedToLoaded(previousState);
    }

    @Test(expected = IllegalStateException.class)
    public void givenCurrentStateIsNotLoadedAndPreviousStateIsNotLoaded_whenNotifyStateChangedToLoaded_thenThrowsException() {
        // Given
        Section.State previousState = Section.State.EMPTY;
        when(section.getState()).thenReturn(Section.State.LOADING);

        // When
        cut.notifyStateChangedToLoaded(previousState);
    }

    @Test
    public void givenSectionHadMoreThanOneItems_whenNotifyStateChangedFromLoaded_thenNotifyItemRangeRemoved_andNotifyItemChanged() {
        // Given
        when(section.getState()).thenReturn(Section.State.LOADING);

        // When
        cut.notifyStateChangedFromLoaded(scenario.getSectionContentItemsTotal());

        // Then
        verify(sectionedAdapter).notifyItemRangeRemoved(
                scenario.getFirstSectionContentItemPositionInAdapter() + 1,
                scenario.getSectionContentItemsTotal() - 1
        );
        verify(sectionedAdapter).notifyItemChanged(scenario.getFirstSectionContentItemPositionInAdapter());
    }

    @Test
    public void givenSectionHadNoItems_whenNotifyStateChangedFromLoaded_thenNotifyItemRangeInserted() {
        // Given
        when(section.getState()).thenReturn(Section.State.LOADING);
        int sectionPreviousContentItemsTotal = 0;

        // When
        cut.notifyStateChangedFromLoaded(sectionPreviousContentItemsTotal);

        // Then
        verify(sectionedAdapter).notifyItemInserted(scenario.getFirstSectionContentItemPositionInAdapter());
    }

    @Test
    public void givenSectionHadOnlyOneItem_whenNotifyStateChangedFromLoaded_thenNotifyItemRangeInserted() {
        // Given
        when(section.getState()).thenReturn(Section.State.LOADING);
        int sectionPreviousContentItemsTotal = 1;

        // When
        cut.notifyStateChangedFromLoaded(sectionPreviousContentItemsTotal);

        // Then
        verify(sectionedAdapter).notifyItemChanged(scenario.getFirstSectionContentItemPositionInAdapter());
        verify(sectionedAdapter, never()).notifyItemRangeRemoved(anyInt(), anyInt());
    }

    @Test(expected = IllegalStateException.class)
    public void givenLoadedAsCurrentState_whenNotifyStateChangedFromLoaded_thenThrowsException() {
        // Given
        when(section.getState()).thenReturn(Section.State.LOADED);
        int sectionPreviousContentItemsTotal = 1;

        // When
        cut.notifyStateChangedFromLoaded(sectionPreviousContentItemsTotal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNegativeValueAsPreviousContentItemsCount_whenNotifyStateChangedFromLoaded_thenThrowsException() {
        // Given
        int sectionPreviousContentItemsTotal = -1;

        // When
        cut.notifyStateChangedFromLoaded(sectionPreviousContentItemsTotal);
    }

    @Test
    public void givenScenario_whenNotifyHeaderInserted_thenNotifyItemInserted() {
        // Given
        int headerPositionInAdapter = 12;

        // When
        cut.notifyHeaderInserted();

        // Then
        verify(sectionedAdapter).notifyItemInserted(headerPositionInAdapter);
    }

    @Test
    public void givenScenario_whenNotifyFooterInserted_thenNotifyItemInserted() {
        // Given
        int footerPositionInAdapter = 23;

        // When
        cut.notifyFooterInserted();

        // Then
        verify(sectionedAdapter).notifyItemInserted(footerPositionInAdapter);
    }

    @Test
    public void givenScenario_whenNotifyHeaderRemoved_thenNotifyItemRemoved() {
        // Given
        int headerPositionInAdapter = 12;

        // When
        cut.notifyHeaderRemoved();

        // Then
        verify(sectionedAdapter).notifyItemRemoved(headerPositionInAdapter);
    }

    @Test
    public void givenScenario_whenNotifyFooterRemoved_thenNotifyItemRemoved() {
        // Given

        // When
        cut.notifyFooterRemoved();

        // Then
        verify(sectionedAdapter).notifyItemRemoved(scenario.getSectionPositionInAdapter() + scenario.getSectionItemsTotal());
    }

    @Test
    public void givenScenario_whenNotifySectionChangedToVisible_thenNotifyItemRangeInserted() {
        // Given

        // When
        cut.notifySectionChangedToVisible();

        // Then
        verify(sectionedAdapter).notifyItemRangeInserted(scenario.getSectionPositionInAdapter(), scenario.getSectionItemsTotal());
    }

    @Test(expected = IllegalStateException.class)
    public void givenSectionIsInvisible_whenNotifySectionChangedToVisible_thenThrowsException() {
        // Given
        when(section.isVisible()).thenReturn(false);

        // When
        cut.notifySectionChangedToVisible();
    }

    @Test
    public void givenPreviousSectionPosition_whenNotifySectionChangedToInvisible_thenNotifyItemRangeInserted() {
        // Given
        when(section.isVisible()).thenReturn(false);
        int previousSectionPosition = 21;

        // When
        cut.notifySectionChangedToInvisible(previousSectionPosition);

        // Then
        verify(sectionedAdapter).notifyItemRangeRemoved(previousSectionPosition, scenario.getSectionItemsTotal());
    }

    @Test(expected = IllegalStateException.class)
    public void givenSectionIsVisible_whenNotifySectionChangedToInvisible_thenThrowsException() {
        // Given
        int previousSectionPosition = 12;

        // When
        cut.notifySectionChangedToInvisible(previousSectionPosition);
    }
}
