package io.github.luizgrp.sectionedrecyclerviewadapter.tools;

import org.apache.commons.collections4.map.ListOrderedMap;

import java.util.UUID;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Four sections in the adapter:
 * <p>
 * 1 - visible section, not headed, not footed in LOADING state
 * 2 - invisible section, not headed, not footed, with 10 items
 * 3 - visible section, headed and footed in FAILED state
 * 4 - visible section, headed and footed in EMPTY state
 * <p>
 * Sections number 1, 3 and 4 are the sections under test.
 */
public class Scenario4 {

    private static final String LOADING_SECTION_TAG = "scenario4LoadingSectionTag";
    private static final String FAILED_SECTION_TAG = "scenario4FailedSectionTag";
    private static final String EMPTY_SECTION_TAG = "scenario4EmptySectionTag";

    private final Section loadingSection;
    private final Section failedSection;
    private final Section emptySection;
    private final ListOrderedMap<String, Section> sections;

    public Scenario4() {
        sections = new ListOrderedMap<>();

        loadingSection = mock(Section.class);
        when(loadingSection.isVisible()).thenReturn(true);
        when(loadingSection.hasHeader()).thenReturn(false);
        when(loadingSection.hasFooter()).thenReturn(false);
        when(loadingSection.getSectionItemsTotal()).thenReturn(1);
        when(loadingSection.getState()).thenReturn(Section.State.LOADING);
        sections.put(LOADING_SECTION_TAG, loadingSection);

        final Section section2 = mock(Section.class);
        when(section2.isVisible()).thenReturn(false);
        when(section2.getSectionItemsTotal()).thenReturn(10);
        when(section2.getState()).thenReturn(Section.State.LOADED);
        sections.put(UUID.randomUUID().toString(), section2);

        failedSection = mock(Section.class);
        when(failedSection.isVisible()).thenReturn(true);
        when(failedSection.hasHeader()).thenReturn(true);
        when(failedSection.hasFooter()).thenReturn(true);
        when(failedSection.getContentItemsTotal()).thenReturn(0);
        when(failedSection.getSectionItemsTotal()).thenReturn(3);
        when(failedSection.getState()).thenReturn(Section.State.FAILED);
        sections.put(FAILED_SECTION_TAG, failedSection);

        emptySection = mock(Section.class);
        when(emptySection.isVisible()).thenReturn(true);
        when(emptySection.hasHeader()).thenReturn(true);
        when(emptySection.hasFooter()).thenReturn(true);
        when(emptySection.getContentItemsTotal()).thenReturn(0);
        when(emptySection.getSectionItemsTotal()).thenReturn(3);
        when(emptySection.getState()).thenReturn(Section.State.EMPTY);
        sections.put(EMPTY_SECTION_TAG, emptySection);
    }

    public ListOrderedMap<String, Section> getSections() {
        return sections;
    }

    public Section getLoadingSection() {
        return loadingSection;
    }

    public Section getFailedSection() {
        return failedSection;
    }

    public Section getEmptySection() {
        return emptySection;
    }

    public String getLoadingSectionTag() {
        return LOADING_SECTION_TAG;
    }

    public String getFailedSectionTag() {
        return FAILED_SECTION_TAG;
    }

    public String getEmptySectionTag() {
        return EMPTY_SECTION_TAG;
    }
}
