package io.github.luizgrp.sectionedrecyclerviewadapter.tools;

import org.apache.commons.collections4.map.ListOrderedMap;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;

/**
 * Represents a scenario for tests.
 */
public interface TestScenario {

    /**
     * Map of sections that should be available in the adapter
     *
     * @return map of sections
     */
    ListOrderedMap<String, Section> getSections();

    /**
     * Reference to the section that will be used for tests
     *
     * @return section under test
     */
    Section getSection();

    /**
     * Section tag
     *
     * @return tag
     */
    String getSectionTag();

    /**
     * Quantity of items in the section under test
     * @return quantity of items
     */
    int getSectionItemsTotal();

    /**
     * Quantity of content items in the section under test
     *
     * @return quantity of content items
     */
    int getSectionContentItemsTotal();

    /**
     * Position of the section in the adapter
     *
     * @return position
     */
    int getSectionPositionInAdapter();

    /**
     * Position of the header of the section in the adapter
     *
     * @return position
     */
    int getSectionHeaderPositionInAdapter();

    /**
     * Position of the footer of the section in the adapter
     *
     * @return position
     */
    int getSectionFooterPositionInAdapter();

    /**
     * Position of the first content item of the section in the adapter
     *
     * @return position
     */
    int getFirstSectionContentItemPositionInAdapter();

    /**
     * Position of the last content item of the section in the adapter
     *
     * @return position
     */
    int getLastSectionContentItemPositionInAdapter();

    /**
     * Position of the first item of the section in the Section.
     * This should always return zero, it exists for readability purposes.
     *
     * @return position
     */
    int getFirstSectionItemPositionInSection();

    /**
     * Position of the last item of the section in the Section.
     *
     * @return position
     */
    int getLastSectionItemPositionInSection();
}
