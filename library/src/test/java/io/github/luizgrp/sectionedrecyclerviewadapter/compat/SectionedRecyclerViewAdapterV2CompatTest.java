package io.github.luizgrp.sectionedrecyclerviewadapter.compat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionAdapter;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SectionedRecyclerViewAdapterV2CompatTest {

    private final String tag = "tag";

    @Mock
    private Section section;
    @Mock
    private SectionAdapter sectionAdapter;

    @Spy
    private SectionedRecyclerViewAdapterV2Compat cut;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        doReturn(sectionAdapter).when(cut).getAdapterForSection(tag);
        doReturn(sectionAdapter).when(cut).getAdapterForSection(section);
    }

    @Test
    public void givenSection_whenGetFooterPositionInAdapterWithSection_thenCallsNewClass() {
        // Given
        final int index = 10;
        when(sectionAdapter.getFooterPosition()).thenReturn(index);

        // When
        int result = cut.getFooterPositionInAdapter(section);

        // Then
        verify(sectionAdapter).getFooterPosition();
        assertThat(result, is(index));
    }

    @Test
    public void givenTag_whenGetFooterPositionInAdapterWithTag_thenCallsNewClass() {
        // Given
        final int index = 10;
        when(sectionAdapter.getFooterPosition()).thenReturn(index);

        // When
        int result = cut.getFooterPositionInAdapter(tag);

        // Then
        verify(sectionAdapter).getFooterPosition();
        assertThat(result, is(index));
    }

    @Test
    public void givenSection_whenGetHeaderPositionInAdapterWithSection_thenCallsNewClass() {
        // Given
        final int index = 10;
        when(sectionAdapter.getHeaderPosition()).thenReturn(index);

        // When
        int result = cut.getHeaderPositionInAdapter(section);

        // Then
        verify(sectionAdapter).getHeaderPosition();
        assertThat(result, is(index));
    }

    @Test
    public void givenTag_whenGetHeaderPositionInAdapterWithTag_thenCallsNewClass() {
        // Given
        final int index = 10;
        when(sectionAdapter.getHeaderPosition()).thenReturn(index);

        // When
        int result = cut.getHeaderPositionInAdapter(tag);

        // Then
        verify(sectionAdapter).getHeaderPosition();
        assertThat(result, is(index));
    }

    @Test
    public void givenSection_whenGetPositionInAdapter_thenCallsNewClass() {
        // Given
        final int position = 10;
        final int adapterPosition = 20;
        when(sectionAdapter.getPositionInAdapter(position)).thenReturn(adapterPosition);

        // When
        int result = cut.getPositionInAdapter(section, position);

        // Then
        verify(sectionAdapter).getPositionInAdapter(position);
        assertThat(result, is(adapterPosition));
    }

    @Test
    public void givenTag_whenGetPositionInAdapter_thenCallsNewClass() {
        // Given
        final int position = 10;
        final int adapterPosition = 20;
        when(sectionAdapter.getPositionInAdapter(position)).thenReturn(adapterPosition);

        // When
        int result = cut.getPositionInAdapter(tag, position);

        // Then
        verify(sectionAdapter).getPositionInAdapter(position);
        assertThat(result, is(adapterPosition));
    }

    @Test
    public void givenSection_whenGetSectionPosition_thenCallsNewClass() {
        // Given
        final int position = 10;
        when(sectionAdapter.getSectionPosition()).thenReturn(position);

        // When
        int result = cut.getSectionPosition(section);

        // Then
        verify(sectionAdapter).getSectionPosition();
        assertThat(result, is(position));
    }

    @Test
    public void givenTag_whenGetSectionPosition_thenCallsNewClass() {
        // Given
        final int position = 10;
        when(sectionAdapter.getSectionPosition()).thenReturn(position);

        // When
        int result = cut.getSectionPosition(tag);

        // Then
        verify(sectionAdapter).getSectionPosition();
        assertThat(result, is(position));
    }

    @Test
    public void givenSection_whenNotifyAllItemsChangedInSection_thenCallsNewClass() {
        // When
        cut.notifyAllItemsChangedInSection(section);

        // Then
        verify(sectionAdapter).notifyAllItemsChanged();
    }

    @Test
    public void givenTag_whenNotifyAllItemsChangedInSection_thenCallsNewClass() {
        // When
        cut.notifyAllItemsChangedInSection(tag);

        // Then
        verify(sectionAdapter).notifyAllItemsChanged();
    }

    @Test
    public void givenSection_whenNotifyAllItemsInsertedInSection_thenCallsNewClass() {
        // When
        cut.notifyAllItemsInsertedInSection(section);

        // Then
        verify(sectionAdapter).notifyAllItemsInserted();
    }

    @Test
    public void givenTag_whenNotifyAllItemsInsertedInSection_thenCallsNewClass() {
        // When
        cut.notifyAllItemsInsertedInSection(tag);

        // Then
        verify(sectionAdapter).notifyAllItemsInserted();
    }

    @Test
    public void givenSection_whenNotifyFooterChangedInSection_thenCallsNewClass() {
        // When
        cut.notifyFooterChangedInSection(section);

        // Then
        verify(sectionAdapter).notifyFooterChanged();
    }

    @Test
    public void givenTag_whenNotifyFooterChangedInSection_thenCallsNewClass() {
        // When
        cut.notifyFooterChangedInSection(tag);

        // Then
        verify(sectionAdapter).notifyFooterChanged();
    }

    @Test
    public void givenSection_whenNotifyFooterInsertedInSection_thenCallsNewClass() {
        // When
        cut.notifyFooterInsertedInSection(section);

        // Then
        verify(sectionAdapter).notifyFooterInserted();
    }

    @Test
    public void givenTag_whenNotifyFooterInsertedInSection_thenCallsNewClass() {
        // When
        cut.notifyFooterInsertedInSection(tag);

        // Then
        verify(sectionAdapter).notifyFooterInserted();
    }

    @Test
    public void givenSection_whenNotifyFooterRemovedFromSection_thenCallsNewClass() {
        // When
        cut.notifyFooterRemovedFromSection(section);

        // Then
        verify(sectionAdapter).notifyFooterRemoved();
    }

    @Test
    public void givenTag_whenNotifyFooterRemovedFromSection_thenCallsNewClass() {
        // When
        cut.notifyFooterRemovedFromSection(tag);

        // Then
        verify(sectionAdapter).notifyFooterRemoved();
    }

    @Test
    public void givenSection_whenNotifyHeaderChangedInSection_thenCallsNewClass() {
        // When
        cut.notifyHeaderChangedInSection(section);

        // Then
        verify(sectionAdapter).notifyHeaderChanged();
    }

    @Test
    public void givenTag_whenNotifyHeaderChangedInSection_thenCallsNewClass() {
        // When
        cut.notifyHeaderChangedInSection(tag);

        // Then
        verify(sectionAdapter).notifyHeaderChanged();
    }

    @Test
    public void givenSection_whenNotifyHeaderInsertedInSection_thenCallsNewClass() {
        // When
        cut.notifyHeaderInsertedInSection(section);

        // Then
        verify(sectionAdapter).notifyHeaderInserted();
    }

    @Test
    public void givenTag_whenNotifyHeaderInsertedInSection_thenCallsNewClass() {
        // When
        cut.notifyHeaderInsertedInSection(tag);

        // Then
        verify(sectionAdapter).notifyHeaderInserted();
    }

    @Test
    public void givenSection_whenNotifyHeaderRemovedFromSection_thenCallsNewClass() {
        // When
        cut.notifyHeaderRemovedFromSection(section);

        // Then
        verify(sectionAdapter).notifyHeaderRemoved();
    }

    @Test
    public void givenTag_whenNotifyHeaderRemovedFromSection_thenCallsNewClass() {
        // When
        cut.notifyHeaderRemovedFromSection(tag);

        // Then
        verify(sectionAdapter).notifyHeaderRemoved();
    }

    @Test
    public void givenSection_whenNotifyItemChangedInSection_thenCallsNewClass() {
        // Given
        final int position = 10;

        // When
        cut.notifyItemChangedInSection(section, position);

        // Then
        verify(sectionAdapter).notifyItemChanged(position);
    }

    @Test
    public void givenTag_whenNotifyItemChangedInSection_thenCallsNewClass() {
        // Given
        final int position = 10;

        // When
        cut.notifyItemChangedInSection(tag, position);

        // Then
        verify(sectionAdapter).notifyItemChanged(position);
    }

    @Test
    public void givenSection_whenNotifyItemInsertedInSection_thenCallsNewClass() {
        // Given
        final int position = 10;

        // When
        cut.notifyItemInsertedInSection(section, position);

        // Then
        verify(sectionAdapter).notifyItemInserted(position);
    }

    @Test
    public void givenTag_whenNotifyItemInsertedInSection_thenCallsNewClass() {
        // Given
        final int position = 10;

        // When
        cut.notifyItemInsertedInSection(tag, position);

        // Then
        verify(sectionAdapter).notifyItemInserted(position);
    }

    @Test
    public void givenSection_whenNotifyItemMovedInSection_thenCallsNewClass() {
        // Given
        final int fromPosition = 10;
        final int toPosition = 20;

        // When
        cut.notifyItemMovedInSection(section, fromPosition, toPosition);

        // Then
        verify(sectionAdapter).notifyItemMoved(fromPosition, toPosition);
    }

    @Test
    public void givenTag_whenNotifyItemMovedInSection_thenCallsNewClass() {
        // Given
        final int fromPosition = 10;
        final int toPosition = 20;

        // When
        cut.notifyItemMovedInSection(tag, fromPosition, toPosition);

        // Then
        verify(sectionAdapter).notifyItemMoved(fromPosition, toPosition);
    }

    @Test
    public void givenSection_whenNotifyItemRangeChangedInSection_thenCallsNewClass() {
        // Given
        final int positionStart = 10;
        final int itemCount = 20;

        // When
        cut.notifyItemRangeChangedInSection(section, positionStart, itemCount);

        // Then
        verify(sectionAdapter).notifyItemRangeChanged(positionStart, itemCount);
    }

    @Test
    public void givenSectionAndPayload_whenNotifyItemRangeChangedInSection_thenCallsNewClass() {
        // Given
        final int positionStart = 10;
        final int itemCount = 20;
        final Object payload = new Object();

        // When
        cut.notifyItemRangeChangedInSection(section, positionStart, itemCount, payload);

        // Then
        verify(sectionAdapter).notifyItemRangeChanged(positionStart, itemCount, payload);
    }

    @Test
    public void givenTag_whenNotifyItemRangeChangedInSection_thenCallsNewClass() {
        // Given
        final int positionStart = 10;
        final int itemCount = 20;

        // When
        cut.notifyItemRangeChangedInSection(tag, positionStart, itemCount);

        // Then
        verify(sectionAdapter).notifyItemRangeChanged(positionStart, itemCount);
    }

    @Test
    public void givenTagAndPayload_whenNotifyItemRangeChangedInSection_thenCallsNewClass() {
        // Given
        final int positionStart = 10;
        final int itemCount = 20;
        final Object payload = new Object();

        // When
        cut.notifyItemRangeChangedInSection(tag, positionStart, itemCount, payload);

        // Then
        verify(sectionAdapter).notifyItemRangeChanged(positionStart, itemCount, payload);
    }

    @Test
    public void givenSection_whenNotifyItemRangeInsertedInSection_thenCallsNewClass() {
        // Given
        final int positionStart = 10;
        final int itemCount = 20;

        // When
        cut.notifyItemRangeInsertedInSection(section, positionStart, itemCount);

        // Then
        verify(sectionAdapter).notifyItemRangeInserted(positionStart, itemCount);
    }

    @Test
    public void givenTag_whenNotifyItemRangeInsertedInSection_thenCallsNewClass() {
        // Given
        final int positionStart = 10;
        final int itemCount = 20;

        // When
        cut.notifyItemRangeInsertedInSection(tag, positionStart, itemCount);

        // Then
        verify(sectionAdapter).notifyItemRangeInserted(positionStart, itemCount);
    }

    @Test
    public void givenSection_whenNotifyItemRangeRemovedFromSection_thenCallsNewClass() {
        // Given
        final int positionStart = 10;
        final int itemCount = 20;

        // When
        cut.notifyItemRangeRemovedFromSection(section, positionStart, itemCount);

        // Then
        verify(sectionAdapter).notifyItemRangeRemoved(positionStart, itemCount);
    }

    @Test
    public void givenTag_whenNotifyItemRangeRemovedFromSection_thenCallsNewClass() {
        // Given
        final int positionStart = 10;
        final int itemCount = 20;

        // When
        cut.notifyItemRangeRemovedFromSection(tag, positionStart, itemCount);

        // Then
        verify(sectionAdapter).notifyItemRangeRemoved(positionStart, itemCount);
    }

    @Test
    public void givenSection_whenNotifyItemRemovedFromSection_thenCallsNewClass() {
        // Given
        final int position = 10;

        // When
        cut.notifyItemRemovedFromSection(section, position);

        // Then
        verify(sectionAdapter).notifyItemRemoved(position);
    }

    @Test
    public void givenTag_whenNotifyItemRemovedFromSection_thenCallsNewClass() {
        // Given
        final int position = 10;

        // When
        cut.notifyItemRemovedFromSection(tag, position);

        // Then
        verify(sectionAdapter).notifyItemRemoved(position);
    }

    @Test
    public void givenSection_whenNotifyNotLoadedStateChanged_thenCallsNewClass() {
        // Given
        final Section.State previousState = Section.State.EMPTY;

        // When
        cut.notifyNotLoadedStateChanged(section, previousState);

        // Then
        verify(sectionAdapter).notifyNotLoadedStateChanged(previousState);
    }

    @Test
    public void givenTag_whenNotifyNotLoadedStateChanged_thenCallsNewClass() {
        // Given
        final Section.State previousState = Section.State.EMPTY;

        // When
        cut.notifyNotLoadedStateChanged(tag, previousState);

        // Then
        verify(sectionAdapter).notifyNotLoadedStateChanged(previousState);
    }

    @Test
    public void givenSection_whenNotifySectionChangedToInvisible_thenCallsNewClass() {
        // Given
        final int previousSectionPosition = 10;

        // When
        cut.notifySectionChangedToInvisible(section, previousSectionPosition);

        // Then
        verify(sectionAdapter).notifySectionChangedToInvisible(previousSectionPosition);
    }

    @Test
    public void givenTag_whenNotifySectionChangedToInvisible_thenCallsNewClass() {
        // Given
        final int previousSectionPosition = 10;

        // When
        cut.notifySectionChangedToInvisible(tag, previousSectionPosition);

        // Then
        verify(sectionAdapter).notifySectionChangedToInvisible(previousSectionPosition);
    }

    @Test
    public void givenSection_whenNotifySectionChangedToVisible_thenCallsNewClass() {
        // When
        cut.notifySectionChangedToVisible(section);

        // Then
        verify(sectionAdapter).notifySectionChangedToVisible();
    }

    @Test
    public void givenTag_whenNotifySectionChangedToVisible_thenCallsNewClass() {
        // When
        cut.notifySectionChangedToVisible(tag);

        // Then
        verify(sectionAdapter).notifySectionChangedToVisible();
    }

    @Test
    public void givenSection_whenNotifyStateChangedFromLoaded_thenCallsNewClass() {
        // Given
        final int previousSectionPosition = 10;

        // When
        cut.notifyStateChangedFromLoaded(section, previousSectionPosition);

        // Then
        verify(sectionAdapter).notifyStateChangedFromLoaded(previousSectionPosition);
    }

    @Test
    public void givenTag_whenNotifyStateChangedFromLoaded_thenCallsNewClass() {
        // Given
        final int previousSectionPosition = 10;

        // When
        cut.notifyStateChangedFromLoaded(tag, previousSectionPosition);

        // Then
        verify(sectionAdapter).notifyStateChangedFromLoaded(previousSectionPosition);
    }

    @Test
    public void givenSection_whenNotifyStateChangedToLoaded_thenCallsNewClass() {
        // Given
        final Section.State previousState = Section.State.EMPTY;

        // When
        cut.notifyStateChangedToLoaded(section, previousState);

        // Then
        verify(sectionAdapter).notifyStateChangedToLoaded(previousState);
    }

    @Test
    public void givenTag_whenNotifyStateChangedToLoaded_thenCallsNewClass() {
        // Given
        final Section.State previousState = Section.State.EMPTY;

        // When
        cut.notifyStateChangedToLoaded(tag, previousState);

        // Then
        verify(sectionAdapter).notifyStateChangedToLoaded(previousState);
    }
}