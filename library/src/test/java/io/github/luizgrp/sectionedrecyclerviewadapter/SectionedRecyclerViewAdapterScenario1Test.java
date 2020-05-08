package io.github.luizgrp.sectionedrecyclerviewadapter;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import io.github.luizgrp.sectionedrecyclerviewadapter.tools.Scenario1;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*
 * Unit tests for SectionedRecyclerViewAdapter
 */
@SuppressWarnings({"PMD.MethodNamingConventions"})
public class SectionedRecyclerViewAdapterScenario1Test {

    private Scenario1 scenario;

    private SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter;

    @Before
    public void setUp() {
        scenario = new Scenario1();

        sectionedRecyclerViewAdapter = new SectionedRecyclerViewAdapter();

        scenario.getSections().forEach((tag, section) -> sectionedRecyclerViewAdapter.addSection(tag, section));
    }

    @Test
    public void givenScenario_whenGetPositionInSection_thenReturnsCorrectPosition() {
        // When
        final int result = sectionedRecyclerViewAdapter.getPositionInSection(scenario.getFirstSectionContentItemPositionInAdapter());
        final int result2 = sectionedRecyclerViewAdapter.getPositionInSection(scenario.getLastSectionContentItemPositionInAdapter());

        // Then
        assertThat(result, is(scenario.getFirstSectionItemPositionInSection()));
        assertThat(result2, is(scenario.getLastSectionItemPositionInSection()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenScenario_whenGetPositionInSectionForHeader_thenThrowsException() {
        // When
        sectionedRecyclerViewAdapter.getPositionInSection(scenario.getSectionHeaderPositionInAdapter());
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenScenario_whenGetPositionInSectionForFooter_thenThrowsException() {
        // When
        sectionedRecyclerViewAdapter.getPositionInSection(scenario.getSectionFooterPositionInAdapter());
    }

    @Test
    public void givenTagAndIndex_whenAddSectionWithTagAndIndex_thenSucceeds() {
        // Given
        final String tag = "tag";
        final int index = 1;
        final Section section = mock(Section.class);

        // When
        sectionedRecyclerViewAdapter.addSection(index, tag, section);

        // Then
        assertThat(sectionedRecyclerViewAdapter.getSectionIndex(section), is(index));
    }

    @Test
    public void givenInvalidTag_whenGetSectionWithTag_thenReturnsNull() {
        // Given
        final String tag = "tag";

        // When
        final Section result = sectionedRecyclerViewAdapter.getSection(tag);

        // Then
        assertNull(result);
    }

    @Test
    public void givenInvalidTag_whenRemoveSectionWithTag_thenDoesNotRemoveAnything() {
        // Given
        final String tag = "tag";
        final int size = sectionedRecyclerViewAdapter.getCopyOfSectionsMap().size();

        // When
        sectionedRecyclerViewAdapter.removeSection(tag);

        // Then
        assertThat(sectionedRecyclerViewAdapter.getCopyOfSectionsMap().size(), is(size));
        assertNull(sectionedRecyclerViewAdapter.getSection(tag));
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenExistingSection_whenAddSectionWithSection_thenThrowsException() {
        // Given
        final Section section = mock(Section.class);
        sectionedRecyclerViewAdapter.addSection(section);

        // When
        sectionedRecyclerViewAdapter.addSection(section);
    }

    @Test
    public void givenScenario_whenRemoveSectionWithTag_thenSucceeds() {
        // Given
        final int size = sectionedRecyclerViewAdapter.getCopyOfSectionsMap().size();

        // When
        sectionedRecyclerViewAdapter.removeSection(scenario.getSectionTag());

        // Then
        assertThat(sectionedRecyclerViewAdapter.getCopyOfSectionsMap().size(), is(size - 1));
        assertNull(sectionedRecyclerViewAdapter.getSection(scenario.getSectionTag()));
    }

    @Test
    public void givenInvalidSection_whenRemoveSectionWithSection_thenDoesNotRemoveAnything() {
        // Given
        final int size = sectionedRecyclerViewAdapter.getCopyOfSectionsMap().size();
        final Section section = mock(Section.class);

        // When
        sectionedRecyclerViewAdapter.removeSection(section);

        // Then
        assertThat(sectionedRecyclerViewAdapter.getCopyOfSectionsMap().size(), is(size));
    }

    @Test
    public void givenScenario_whenRemoveSectionWithSection_thenSucceeds() {
        // Given
        final int size = sectionedRecyclerViewAdapter.getCopyOfSectionsMap().size();

        // When
        sectionedRecyclerViewAdapter.removeSection(scenario.getSection());

        // Then
        assertThat(sectionedRecyclerViewAdapter.getCopyOfSectionsMap().size(), is(size - 1));
        assertNull(sectionedRecyclerViewAdapter.getSection(scenario.getSectionTag()));
    }

    @Test
    public void givenScenario_whenGetItemCount_thenReturnsCorrectQuantity() {
        // When
        final int result = sectionedRecyclerViewAdapter.getItemCount();

        // Then
        assertThat(result, is(24));
    }

    @Test
    public void givenScenario_whenGetCopyOfSectionsMap_thenReturnsCorrectSize() {
        // When
        final int result = sectionedRecyclerViewAdapter.getCopyOfSectionsMap().size();

        // Then
        assertThat(result, is(4));
    }

    @Test
    public void givenScenario_whenGetSectionCount_thenCorrectNumberIsReturned() {
        // When
        final int result = sectionedRecyclerViewAdapter.getSectionCount();

        // Then
        assertThat(result, is(4));
    }

    @Test
    public void givenIndex0_whenGetSectionWithIndex_thenCorrectSectionIsReturned() {
        // Given
        final int index = 0;
        final Section section = scenario.getSections().getValue(index);

        // When
        final Section result = sectionedRecyclerViewAdapter.getSection(index);

        // Then
        assertThat(result, is(section));
    }

    @Test
    public void givenIndex1_whenGetSectionWithIndex_thenCorrectSectionIsReturned() {
        // Given
        final int index = 1;
        final Section section = scenario.getSections().getValue(index);

        // When
        final Section result = sectionedRecyclerViewAdapter.getSection(index);

        // Then
        assertThat(result, is(section));
    }

    @Test
    public void givenIndex2_whenGetSectionWithIndex_thenCorrectSectionIsReturned() {
        // Given
        final int index = 2;
        final Section section = scenario.getSections().getValue(index);

        // When
        final Section result = sectionedRecyclerViewAdapter.getSection(index);

        // Then
        assertThat(result, is(section));
    }

    @Test
    public void givenIndex3_whenGetSectionWithIndex_thenCorrectSectionIsReturned() {
        // Given
        final int index = 3;
        final Section section = scenario.getSections().getValue(index);

        // When
        final Section result = sectionedRecyclerViewAdapter.getSection(index);

        // Then
        assertThat(result, is(section));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenInvalidIndex_whenGetSectionWithIndex_thenExceptionIsThrown() {
        // Given
        final int index = 4;

        // When
        sectionedRecyclerViewAdapter.getSection(index);
    }

    @Test
    public void givenScenario_whenGetSection_thenReturnsCorrectSection() {
        // When
        final Section result = sectionedRecyclerViewAdapter.getSection(scenario.getSectionTag());

        // Then
        assertThat(result, is(scenario.getSection()));
    }

    @Test
    public void givenFirstSection_whenGetSectionForPosition_thenReturnsCorrectSection() {
        // Given
        final Section section = sectionedRecyclerViewAdapter.getSections().getValue(0);

        // When
        Section sectionForFirstItemPositionInAdapter = sectionedRecyclerViewAdapter.getSectionForPosition(0);
        Section sectionForLastItemPositionInAdapter = sectionedRecyclerViewAdapter.getSectionForPosition(11);

        // Then
        assertThat(sectionForFirstItemPositionInAdapter, is(section));
        assertThat(sectionForLastItemPositionInAdapter, is(section));
    }

    @Test
    public void givenSection_whenGetSectionForPosition_thenReturnsCorrectSection() {
        // When
        Section sectionForFirstItemPositionInAdapter = sectionedRecyclerViewAdapter.getSectionForPosition(12);
        Section sectionForLastItemPositionInAdapter = sectionedRecyclerViewAdapter.getSectionForPosition(23);

        // Then
        assertThat(sectionForFirstItemPositionInAdapter, is(scenario.getSection()));
        assertThat(sectionForLastItemPositionInAdapter, is(scenario.getSection()));
    }

    @Test
    public void givenViewType0_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        final int viewTypeHeader = 0;


        // When
        final int result = SectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeHeader);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER));
    }

    @Test
    public void givenViewType1_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        final int viewTypeFooter = 1;

        // When
        final int result = SectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeFooter);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER));
    }

    @Test
    public void givenViewType2_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        final int viewTypeItemLoaded = 2;

        // When
        final int result = SectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeItemLoaded);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED));
    }

    @Test
    public void givenViewType3_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        final int viewTypeLoading = 3;

        // When
        final int result = SectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeLoading);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_LOADING));
    }

    @Test
    public void givenViewType4_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        final int viewTypeFailed = 4;

        // When
        final int result = SectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeFailed);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_FAILED));
    }

    @Test
    public void givenViewType5_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        final int viewTypeEmpty = 5;

        // When
        final int result = SectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeEmpty);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_EMPTY));
    }

    @Test
    public void givenAdapterViewTypeForHeader_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        final int viewTypeHeader = 12;

        // When
        final int result = SectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeHeader);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER));
    }

    @Test
    public void givenAdapterViewTypeForFooter_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        final int viewTypeFooter = 13;

        // When
        final int result = SectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeFooter);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER));
    }

    @Test
    public void givenAdapterViewTypeForLoaded_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        final int viewTypeItemLoaded = 14;

        // When
        final int result = SectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeItemLoaded);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED));
    }

    @Test
    public void givenAdapterViewTypeForLoading_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        final int viewTypeLoading = 15;

        // When
        final int result = SectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeLoading);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_LOADING));
    }

    @Test
    public void givenAdapterViewTypeForFailed_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        final int viewTypeFailed = 16;

        // When
        final int result = SectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeFailed);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_FAILED));
    }

    @Test
    public void givenAdapterViewTypeForEmpty_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        final int viewTypeEmpty = 17;

        // When
        final int result = SectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeEmpty);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_EMPTY));
    }

    @Test
    public void givenAdapterViewTypeConstantForHeader_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        final int viewTypeHeader = SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER;

        // When
        final int result = SectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeHeader);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER));
    }

    @Test
    public void givenAdapterViewTypeConstantForFooter_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        final int viewTypeFooter = SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER;

        // When
        final int result = SectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeFooter);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER));
    }

    @Test
    public void givenAdapterViewTypeConstantForItemLoaded_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        final int viewTypeItemLoaded = SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED;

        // When
        final int result = SectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeItemLoaded);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED));
    }

    @Test
    public void givenAdapterViewTypeConstantForLoading_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        final int viewTypeLoading = SectionedRecyclerViewAdapter.VIEW_TYPE_LOADING;

        // When
        final int result = SectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeLoading);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_LOADING));
    }

    @Test
    public void givenAdapterViewTypeConstantForFailed_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        final int viewTypeFailed = SectionedRecyclerViewAdapter.VIEW_TYPE_FAILED;

        // When
        final int result = SectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeFailed);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_FAILED));
    }

    @Test
    public void givenAdapterViewTypeConstantForEmpty_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        final int viewTypeEmpty = SectionedRecyclerViewAdapter.VIEW_TYPE_EMPTY;

        // When
        final int result = SectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeEmpty);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_EMPTY));
    }

    @Test
    public void givenAdapterPositionForHeader_whenGetSectionItemViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        final int headerPosition = 12;

        // When
        final int result = sectionedRecyclerViewAdapter.getSectionItemViewType(headerPosition);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER));
    }

    @Test
    public void givenAdapterPositionForFirstItem_whenGetSectionItemViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        final int firstItemPosition = 13;

        // When
        final int result = sectionedRecyclerViewAdapter.getSectionItemViewType(firstItemPosition);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED));
    }

    @Test
    public void givenAdapterPositionForLastItem_whenGetSectionItemViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        final int lastItemPosition = 22;

        // When
        final int result = sectionedRecyclerViewAdapter.getSectionItemViewType(lastItemPosition);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED));
    }

    @Test
    public void givenAdapterPositionForFooter_whenGetSectionItemViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        final int footerPosition = 23;

        // When
        final int result = sectionedRecyclerViewAdapter.getSectionItemViewType(footerPosition);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER));
    }

    @Test
    public void givenAdapterPositionForHeader_whenGetItemViewType_thenReturnsCorrectItemViewType() {
        // Given
        final int headerPosition = 12;

        // When
        final int result = sectionedRecyclerViewAdapter.getItemViewType(headerPosition);

        // Then
        assertThat(result, is(12));
    }

    @Test
    public void givenAdapterPositionForFirstItem_whenGetItemViewType_thenReturnsCorrectItemViewType() {
        // Given
        final int firstItemPosition = 13;

        // When
        final int result = sectionedRecyclerViewAdapter.getItemViewType(firstItemPosition);

        // Then
        assertThat(result, is(14));
    }

    @Test
    public void givenAdapterPositionForLastItem_whenGetItemViewType_thenReturnsCorrectItemViewType() {
        // Given
        final int lastItemPosition = 22;

        // When
        final int result = sectionedRecyclerViewAdapter.getItemViewType(lastItemPosition);

        // Then
        assertThat(result, is(14));
    }

    @Test
    public void givenAdapterPositionForFooter_whenGetItemViewType_thenReturnsCorrectItemViewType() {
        // Given
        final int footerPosition = 23;

        // When
        final int result = sectionedRecyclerViewAdapter.getItemViewType(footerPosition);

        // Then
        assertThat(result, is(13));
    }

    @Test(expected = NullPointerException.class)
    public void givenViewTypeForHeader_whenOnCreateViewHolder_thenThrowsException() {
        // Given
        final int viewTypeHeader = SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER;

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewTypeHeader);
    }

    @Test(expected = NullPointerException.class)
    public void givenViewTypeForFooter_whenOnCreateViewHolder_thenThrowsException() {
        // Given
        final int viewTypeFooter = SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER;

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewTypeFooter);
    }

    @Test(expected = NullPointerException.class)
    public void givenViewTypeForLoading_whenOnCreateViewHolder_thenThrowsException() {
        // Given
        final int viewTypeLoading = SectionedRecyclerViewAdapter.VIEW_TYPE_LOADING;

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewTypeLoading);
    }

    @Test(expected = NullPointerException.class)
    public void givenViewTypeForFailed_whenOnCreateViewHolder_thenThrowsException() {
        // Given
        final int viewTypeFailed = SectionedRecyclerViewAdapter.VIEW_TYPE_FAILED;

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewTypeFailed);
    }

    @Test(expected = NullPointerException.class)
    public void givenViewTypeForEmpty_whenOnCreateViewHolder_thenThrowsException() {
        // Given
        final int viewTypeEmpty = SectionedRecyclerViewAdapter.VIEW_TYPE_EMPTY;

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewTypeEmpty);
    }

    @Test
    public void givenScenario_whenRemoveAllSections_thenSucceeds() {
        // When
        sectionedRecyclerViewAdapter.removeAllSections();

        // Then
        assertThat(sectionedRecyclerViewAdapter.getItemCount(), is(0));
        assertTrue(sectionedRecyclerViewAdapter.getCopyOfSectionsMap().isEmpty());
    }

    @Test
    public void givenScenario_whenRemoveAllSectionsAndSectionIsAdded_thenFirstPositionHasItemViewTypeZero() {
        // Given
        final Section section = mock(Section.class);
        when(section.isVisible()).thenReturn(true);
        when(section.hasHeader()).thenReturn(true);
        when(section.hasFooter()).thenReturn(true);
        when(section.getSectionItemsTotal()).thenReturn(12);
        when(section.getState()).thenReturn(Section.State.LOADED);

        // When
        sectionedRecyclerViewAdapter.removeAllSections();
        sectionedRecyclerViewAdapter.addSection(section);

        // Then
        assertThat(sectionedRecyclerViewAdapter.getItemViewType(0), is(0));
    }

    @Test
    public void givenSectionAtIndex0_whenGetSectionIndex_thenReturnsCorrectIndex() {
        // Given
        final int index = 0;
        final Section section = scenario.getSections().getValue(index);

        // When
        final int result = sectionedRecyclerViewAdapter.getSectionIndex(section);

        // Then
        assertThat(result, is(index));
    }

    @Test
    public void givenSectionAtIndex1_whenGetSectionIndex_thenReturnsCorrectIndex() {
        // Given
        final int index = 1;
        final Section section = scenario.getSections().getValue(index);

        // When
        final int result = sectionedRecyclerViewAdapter.getSectionIndex(section);

        // Then
        assertThat(result, is(index));
    }

    @Test
    public void givenSectionAtIndex2_whenGetSectionIndex_thenReturnsCorrectIndex() {
        // Given
        final int index = 2;
        final Section section = scenario.getSections().getValue(index);

        // When
        final int result = sectionedRecyclerViewAdapter.getSectionIndex(section);

        // Then
        assertThat(result, is(index));
    }

    @Test
    public void givenSectionAtIndex3_whenGetSectionIndex_thenReturnsCorrectIndex() {
        // Given
        final int index = 3;
        final Section section = scenario.getSections().getValue(index);

        // When
        final int result = sectionedRecyclerViewAdapter.getSectionIndex(section);

        // Then
        assertThat(result, is(index));
    }

    @Test
    public void givenInvalidSection_whenGetSectionIndex_thenReturnsMinusOne() {
        // Given
        final Section invalidSection = mock(Section.class);

        // When
        final int result = sectionedRecyclerViewAdapter.getSectionIndex(invalidSection);

        // Then
        assertThat(result, is(-1));
    }

    @Test
    public void givenTagForFirstSection_whenGetSectionAdapter_thenReturnsCorrectSectionAdapter() {
        // Given
        final int index = 0;
        final Section section = scenario.getSections().getValue(index);
        String tag = getTagForSection(section);

        // When
        SectionAdapter sectionAdapter = sectionedRecyclerViewAdapter.getAdapterForSection(tag);

        // Then
        assertThat(sectionAdapter.getSectionPosition(), is(index));
    }

    @Test
    public void givenTagForSecondSection_whenGetSectionAdapter_thenReturnsCorrectSectionAdapter() {
        // Given
        final int index = 1;
        final Section section = scenario.getSections().getValue(index);
        String tag = getTagForSection(section);

        // When
        SectionAdapter sectionAdapter = sectionedRecyclerViewAdapter.getAdapterForSection(tag);

        // Then
        assertThat(sectionAdapter.getSection(), is(section));
    }

    @Test
    public void givenTagForThirdSection_whenGetSectionAdapter_thenReturnsCorrectSectionAdapter() {
        // Given
        final int index = 2;
        final Section section = scenario.getSections().getValue(index);
        String tag = getTagForSection(section);

        // When
        SectionAdapter sectionAdapter = sectionedRecyclerViewAdapter.getAdapterForSection(tag);

        // Then
        assertThat(sectionAdapter.getSection(), is(section));
    }

    @Test
    public void givenTagForFourthSection_whenGetSectionAdapter_thenReturnsCorrectSectionAdapter() {
        // Given
        final int index = 3;
        final Section section = scenario.getSections().getValue(index);
        String tag = getTagForSection(section);

        // When
        SectionAdapter sectionAdapter = sectionedRecyclerViewAdapter.getAdapterForSection(tag);

        // Then
        assertThat(sectionAdapter.getSection(), is(section));
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidTag_whenGetSectionAdapter_thenReturnsCorrectSectionAdapter() {
        // Given
        final String tag = "tag";

        // When
        sectionedRecyclerViewAdapter.getAdapterForSection(tag);
    }

    private String getTagForSection(Section section) {
        //noinspection OptionalGetWithoutIsPresent
        return sectionedRecyclerViewAdapter.getCopyOfSectionsMap()
                .entrySet()
                .stream()
                .filter(entry -> section.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst()
                .get();
    }
}
