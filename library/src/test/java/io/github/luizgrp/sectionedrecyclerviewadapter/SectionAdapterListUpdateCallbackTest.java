package io.github.luizgrp.sectionedrecyclerviewadapter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class SectionAdapterListUpdateCallbackTest {

    @Mock
    private SectionAdapter sectionAdapter;

    private SectionAdapterListUpdateCallback sectionAdapterListUpdateCallback;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        sectionAdapterListUpdateCallback = new SectionAdapterListUpdateCallback(sectionAdapter);
    }

    @Test
    public void givenSectionAdapter_whenOnInserted_thenNotifyItemRangeInsertedIsCalled() {
        // Given
        final int position = 3;
        final int count = 9;

        // When
        sectionAdapterListUpdateCallback.onInserted(position, count);

        // Then
        verify(sectionAdapter).notifyItemRangeInserted(position, count);
    }

    @Test
    public void givenSectionAdapter_whenOnRemoved_thenNotifyItemRangeRemovedIsCalled() {
        // Given
        final int position = 3;
        final int count = 9;

        // When
        sectionAdapterListUpdateCallback.onRemoved(position, count);

        // Then
        verify(sectionAdapter).notifyItemRangeRemoved(position, count);
    }

    @Test
    public void givenSectionAdapter_whenOnMoved_thenNotifyItemMovedIsCalled() {
        // Given
        final int fromPosition = 3;
        final int toPosition = 9;

        // When
        sectionAdapterListUpdateCallback.onMoved(fromPosition, toPosition);

        // Then
        verify(sectionAdapter).notifyItemMoved(fromPosition, toPosition);
    }

    @Test
    public void givenSectionAdapter_whenOnChanged_thenNotifyItemRangeChangedIsCalled() {
        // Given
        final int position = 3;
        final int count = 9;
        final Object payload = new Object();

        // When
        sectionAdapterListUpdateCallback.onChanged(position, count, payload);

        // Then
        verify(sectionAdapter).notifyItemRangeChanged(position, count, payload);
    }
}