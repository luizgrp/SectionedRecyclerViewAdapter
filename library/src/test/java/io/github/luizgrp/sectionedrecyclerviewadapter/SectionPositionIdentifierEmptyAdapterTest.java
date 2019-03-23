package io.github.luizgrp.sectionedrecyclerviewadapter;

import org.apache.commons.collections4.map.ListOrderedMap;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class SectionPositionIdentifierEmptyAdapterTest {

    @Mock
    private SectionedRecyclerViewAdapter sectionedAdapter;
    @Mock
    private Section section;

    private SectionPositionIdentifier cut;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(sectionedAdapter.getSections()).thenReturn(new ListOrderedMap<>());

        cut = new SectionAdapter(sectionedAdapter, section);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenEmptyAdapter_whenGetSectionPosition_thenThrowsException() {
        // When
        cut.getSectionPosition();
    }
}
