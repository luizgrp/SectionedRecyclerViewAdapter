package io.github.luizgrp.sectionedrecyclerviewadapter.tools;

import org.apache.commons.collections4.map.ListOrderedMap;

import java.util.UUID;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Four sections in the adapter:
 *
 * 1 - visible section, headed and footed with 10 items
 * 2 - invisible section, not headed, not footed, with 10 items
 * 3 - visible section, headed and footed with NO items
 * 4 - invisible section, headed and footed with 10 items
 *
 * Section number 3 is the section under test.
 */
public class Scenario3 implements TestScenario {

    private static final String SECTION_TAG = "scenario3SectionTag";

    private final Section section;
    private final ListOrderedMap<String, Section> sections;

    public Scenario3() {
        section = mock(Section.class);
        sections = new ListOrderedMap<>();

        final Section section1 = mock(Section.class);
        when(section1.isVisible()).thenReturn(true);
        when(section1.hasHeader()).thenReturn(true);
        when(section1.hasFooter()).thenReturn(true);
        when(section1.getSectionItemsTotal()).thenReturn(12);
        when(section1.getState()).thenReturn(Section.State.LOADED);
        sections.put(UUID.randomUUID().toString(), section1);

        final Section section2 = mock(Section.class);
        when(section2.isVisible()).thenReturn(false);
        when(section2.getSectionItemsTotal()).thenReturn(10);
        when(section2.getState()).thenReturn(Section.State.LOADED);
        sections.put(UUID.randomUUID().toString(), section2);

        when(section.isVisible()).thenReturn(true);
        when(section.hasHeader()).thenReturn(true);
        when(section.hasFooter()).thenReturn(true);
        when(section.getContentItemsTotal()).thenReturn(0);
        when(section.getSectionItemsTotal()).thenReturn(2);
        when(section.getState()).thenReturn(Section.State.LOADED);
        sections.put(SECTION_TAG, section);

        final Section section4 = mock(Section.class);
        when(section4.isVisible()).thenReturn(false);
        when(section4.hasHeader()).thenReturn(true);
        when(section4.hasFooter()).thenReturn(true);
        when(section4.getSectionItemsTotal()).thenReturn(12);
        when(section4.getState()).thenReturn(Section.State.LOADED);
        sections.put(UUID.randomUUID().toString(), section4);
    }

    @Override
    public ListOrderedMap<String, Section> getSections() {
        return sections;
    }

    @Override
    public Section getSection() {
        return section;
    }

    @Override
    public String getSectionTag() {
        return SECTION_TAG;
    }

    @Override
    public int getSectionItemsTotal() {
        return 2;
    }

    @Override
    public int getSectionContentItemsTotal() {
        return 0;
    }

    @Override
    public int getSectionPositionInAdapter() {
        return 12;
    }

    @Override
    public int getSectionHeaderPositionInAdapter() {
        return 12;
    }

    @Override
    public int getSectionFooterPositionInAdapter() {
        return 13;
    }

    @Override
    public int getFirstSectionContentItemPositionInAdapter() {
        throw new IndexOutOfBoundsException("Section has no contents");
    }

    @Override
    public int getLastSectionContentItemPositionInAdapter() {
        throw new IndexOutOfBoundsException("Section has no contents");
    }

    @Override
    public int getFirstSectionItemPositionInSection() {
        return 0;
    }

    @Override
    public int getLastSectionItemPositionInSection() {
        return 0;
    }
}
