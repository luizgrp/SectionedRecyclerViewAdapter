package io.github.luizgrp.sectionedrecyclerviewadapter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.github.luizgrp.sectionedrecyclerviewadapter.tools.Scenario3;
import io.github.luizgrp.sectionedrecyclerviewadapter.tools.TestScenario;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings({"PMD.MethodNamingConventions"})
public class SectionNotifierScenario3Test {

    @Mock
    private SectionedRecyclerViewAdapter sectionedAdapter;

    private Section section;

    private SectionNotifier cut;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        TestScenario scenario = new Scenario3();
        section = scenario.getSection();

        when(sectionedAdapter.getSections()).thenReturn(scenario.getSections());

        cut = new SectionAdapter(sectionedAdapter, section);
    }

    @Test
    public void givenSectionHasNoItems_whenNotifyStateChangedToLoaded_thenNotifyItemRemoved() {
        // Given
        Section.State previousState = Section.State.LOADING;
        when(section.getState()).thenReturn(Section.State.LOADED);

        // When
        cut.notifyStateChangedToLoaded(previousState);

        // Then
        verify(sectionedAdapter).notifyItemRemoved(13);
    }
}
