package io.github.luizgrp.sectionedrecyclerviewadapter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.github.luizgrp.sectionedrecyclerviewadapter.tools.Scenario2;
import io.github.luizgrp.sectionedrecyclerviewadapter.tools.TestScenario;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings({"PMD.MethodNamingConventions"})
public class SectionNotifierScenario2Test {

    @Mock
    private SectionedRecyclerViewAdapter sectionedAdapter;

    private TestScenario scenario;
    private Section section;

    private SectionNotifier cut;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        scenario = new Scenario2();
        section = scenario.getSection();

        when(sectionedAdapter.getSections()).thenReturn(scenario.getSections());

        cut = new SectionAdapter(sectionedAdapter, section);
    }

    @Test
    public void givenScenario_whenNotifyStateChangedToLoaded_thenNotifyItemChanged() {
        // Given
        Section.State previousState = Section.State.LOADING;
        when(section.getState()).thenReturn(Section.State.LOADED);

        // When
        cut.notifyStateChangedToLoaded(previousState);

        // Then
        verify(sectionedAdapter).notifyItemChanged(scenario.getFirstSectionContentItemPositionInAdapter());
        verify(sectionedAdapter, never()).notifyItemRangeInserted(anyInt(), anyInt());
    }
}
