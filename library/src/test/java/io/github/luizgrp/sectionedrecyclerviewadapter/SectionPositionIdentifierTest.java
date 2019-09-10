package io.github.luizgrp.sectionedrecyclerviewadapter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.github.luizgrp.sectionedrecyclerviewadapter.tools.Scenario1;
import io.github.luizgrp.sectionedrecyclerviewadapter.tools.TestScenario;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SectionPositionIdentifierTest {

    @Mock
    private SectionedRecyclerViewAdapter sectionedAdapter;

    private TestScenario scenario;
    private Section section;

    private SectionPositionIdentifier cut;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        scenario = new Scenario1();
        section = scenario.getSection();

        when(sectionedAdapter.getSections()).thenReturn(scenario.getSections());

        cut = new SectionAdapter(sectionedAdapter, section);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidSection_whenGetSectionPosition_thenThrowsException() {
        // Given
        Section invalidSection = mock(Section.class);
        cut = new SectionAdapter(sectionedAdapter, invalidSection);

        // When
        cut.getSectionPosition();
    }

    @Test
    public void givenScenario_whenGetSectionPosition_thenReturnsCorrectPositionInAdapter() {
        // Given

        // When
        int result = cut.getSectionPosition();

        // Then
        assertThat(result, is(scenario.getSectionPositionInAdapter()));
    }

    @Test
    public void givenScenario_whenGetHeaderPosition_thenReturnsCorrectPositionInAdapter() {
        // Given

        // When
        int result = cut.getHeaderPosition();

        // Then
        assertThat(result, is(scenario.getSectionHeaderPositionInAdapter()));
    }

    @Test(expected = IllegalStateException.class)
    public void givenSectionHasNoHeader_whenGetHeaderPosition_thenReturnsCorrectPositionInAdapter() {
        // Given
        when(section.hasHeader()).thenReturn(false);

        // When
        cut.getHeaderPosition();
    }

    @Test
    public void givenScenario_whenGetFooterPosition_thenReturnsCorrectPositionInAdapter() {
        // Given

        // When
        int result = cut.getFooterPosition();

        // Then
        assertThat(result, is(scenario.getSectionFooterPositionInAdapter()));
    }

    @Test(expected = IllegalStateException.class)
    public void givenSectionHasNoFooter_whenGetHeaderPosition_thenReturnsCorrectPositionInAdapter() {
        // Given
        when(section.hasFooter()).thenReturn(false);

        // When
        cut.getFooterPosition();
    }

    @Test
    public void givenFirstSectionItemPositionInSection_whenGetPositionInAdapter_thenReturnsCorrectPositionInAdapter() {
        // Given
        int positionInAdapter = scenario.getFirstSectionItemPositionInSection();

        // When
        int result = cut.getPositionInAdapter(positionInAdapter);

        // Then
        assertThat(result, is(scenario.getFirstSectionContentItemPositionInAdapter()));
    }

    @Test
    public void givenFirstSectionContentItemPositionInAdapter_whenGetPositionInSection_thenReturnsCorrectPositionInSection() {
        // Given
        int positionInAdapter = scenario.getFirstSectionContentItemPositionInAdapter();

        // When
        int result = cut.getPositionInSection(positionInAdapter);

        // Then
        assertThat(result, is(scenario.getFirstSectionItemPositionInSection()));
    }
}
