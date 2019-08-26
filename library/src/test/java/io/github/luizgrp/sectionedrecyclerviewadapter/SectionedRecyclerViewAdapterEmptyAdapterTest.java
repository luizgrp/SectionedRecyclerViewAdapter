package io.github.luizgrp.sectionedrecyclerviewadapter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/*
 * Unit tests for SectionedRecyclerViewAdapter
 */
@SuppressWarnings({"PMD.MethodNamingConventions"})
public class SectionedRecyclerViewAdapterEmptyAdapterTest {

    @Mock
    private Section section;

    private SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        sectionedRecyclerViewAdapter = new SectionedRecyclerViewAdapter();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenEmptyAdapter_whenGetPositionInSection_thenThrowsException() {
        // When
        sectionedRecyclerViewAdapter.getPositionInSection(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenEmptyAdapter_whenOnBindViewHolder_thenThrowsException() {
        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onBindViewHolder(null, 0);
    }

    @Test
    public void givenEmptyAdapter_whenAddSectionWithSection_thenSucceeds() {
        // When
        final String result = sectionedRecyclerViewAdapter.addSection(section);

        // Then
        assertThat(sectionedRecyclerViewAdapter.getSection(result), is(section));
        assertThat(sectionedRecyclerViewAdapter.getCopyOfSectionsMap().get(result), is(section));
    }

    @Test
    public void givenEmptyAdapter_whenAddSectionWithTag_thenSucceeds() {
        // Given
        final String tag = "tag";

        // When
        sectionedRecyclerViewAdapter.addSection(tag, section);

        // Then
        assertThat(sectionedRecyclerViewAdapter.getSection(tag), is(section));
        assertThat(sectionedRecyclerViewAdapter.getCopyOfSectionsMap().get(tag), is(section));
    }

    @Test
    public void givenEmptyAdapter_whenAddSectionWithIndex_thenSucceeds() {
        // When
        final String result = sectionedRecyclerViewAdapter.addSection(0, section);

        // Then
        assertThat(sectionedRecyclerViewAdapter.getSection(result), is(section));
        assertThat(sectionedRecyclerViewAdapter.getCopyOfSectionsMap().get(result), is(section));
    }

    @Test
    public void givenEmptyAdapter_whenRemoveSectionWithTag_thenFailsSilently() {
        // Given
        final String tag = "tag";

        // When
        sectionedRecyclerViewAdapter.removeSection(tag);

        // Then
        assertTrue(sectionedRecyclerViewAdapter.getCopyOfSectionsMap().isEmpty());
    }

    @Test
    public void givenEmptyAdapter_whenRemoveSectionWithSection_thenFailsSilently() {
        // When
        sectionedRecyclerViewAdapter.removeSection(mock(Section.class));

        // Then
        assertTrue(sectionedRecyclerViewAdapter.getCopyOfSectionsMap().isEmpty());
    }

    @Test
    public void givenEmptyAdapter_whenGetItemCount_thenReturnsZero() {
        // When
        final int result = sectionedRecyclerViewAdapter.getItemCount();

        // Then
        assertThat(result, is(0));
    }

    @Test
    public void givenEmptyAdapter_whenIsEmpty_thenReturnsTrue() {
        // When
        boolean result = sectionedRecyclerViewAdapter.getCopyOfSectionsMap().isEmpty();

        // Then
        assertTrue(result);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenEmptyAdapter_whenGetSectionWithIndex_thenThrowsException() {
        // When
        sectionedRecyclerViewAdapter.getSection(2);
    }

    @Test
    public void givenEmptyAdapter_whenGetSectionWithTag_thenReturnsNull() {
        // Given
        final String tag = "tag";

        // When
        Section result = sectionedRecyclerViewAdapter.getSection(tag);

        // Then
        assertNull(result);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenEmptyAdapter_whenGetSectionForPosition_thenThrowsException() {
        // When
        sectionedRecyclerViewAdapter.getSectionForPosition(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenEmptyAdapter_whenGetSectionItemViewType_thenThrowsException() {
        // When
        sectionedRecyclerViewAdapter.getSectionItemViewType(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenEmptyAdapter_whenGetItemViewType_thenThrowsException() {
        // When
        sectionedRecyclerViewAdapter.getItemViewType(0);
    }

    @Test
    public void givenEmptyAdapter_whenOnCreateViewHolder_thenReturnsNull() {
        // When
        @SuppressWarnings("ConstantConditions")
        Object result = sectionedRecyclerViewAdapter.onCreateViewHolder(null, 0);

        // Then
        //noinspection ConstantConditions
        assertNull(result);
    }
}
