package io.github.luizgrp.sectionedrecyclerviewadapter;

/**
 * Collection of helper methods to identify the position of:
 * - the section in the adapter
 * - the section components in the adapter
 * - section items in the section itself
 */
/* default */ interface SectionPositionIdentifier {

    /**
     * Helper method that returns the position of header in the adapter.
     *
     * @return position of the header in the adapter
     */
    int getHeaderPosition();

    /**
     * Helper method that returns the position of footer in the adapter.
     *
     * @return position of the footer in the adapter
     */
    int getFooterPosition();

    /**
     * Return the section position in the adapter.
     *
     * @return position of the section in the adapter
     */
    int getSectionPosition();

    /**
     * Helper method that receives position in relation to the section, and returns the position in
     * the adapter.
     *
     * @param position position of the item in the section
     * @return position of the item in the adapter
     */
    int getPositionInAdapter(final int position);

    /**
     * Return the item position in the section.
     *
     * @param position position of the item in the adapter
     * @return position of the item in the section
     */
    int getPositionInSection(final int position);
}
