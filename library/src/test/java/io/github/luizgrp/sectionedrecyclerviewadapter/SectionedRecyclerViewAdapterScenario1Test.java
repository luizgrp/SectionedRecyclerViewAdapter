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
        int result = sectionedRecyclerViewAdapter.getPositionInSection(scenario.getFirstSectionContentItemPositionInAdapter());
        int result2 = sectionedRecyclerViewAdapter.getPositionInSection(scenario.getLastSectionContentItemPositionInAdapter());

        // Then
        assertThat(result, is(scenario.getFirstSectionItemPositionInSection()));
        assertThat(result2, is(scenario.getLastSectionItemPositionInSection()));
    }

    @Test
    public void givenTagAndIndex_whenAddSectionWithTagAndIndex_thenSucceeds() {
        // Given
        String tag = "tag";
        int index = 1;
        Section section = mock(Section.class);

        // When
        sectionedRecyclerViewAdapter.addSection(index, tag, section);

        // Then
        assertThat(sectionedRecyclerViewAdapter.getSectionIndex(section), is(index));
    }

    @Test
    public void givenInvalidTag_whenGetSectionWithTag_thenReturnsNull() {
        // Given
        String tag = "tag";

        // When
        Section result = sectionedRecyclerViewAdapter.getSection(tag);

        // Then
        assertNull(result);
    }

    @Test
    public void givenInvalidTag_whenRemoveSectionWithTag_thenDoesNotRemoveAnything() {
        // Given
        String tag = "tag";
        int size = sectionedRecyclerViewAdapter.getCopyOfSectionsMap().size();

        // When
        sectionedRecyclerViewAdapter.removeSection(tag);

        // Then
        assertThat(sectionedRecyclerViewAdapter.getCopyOfSectionsMap().size(), is(size));
        assertNull(sectionedRecyclerViewAdapter.getSection(tag));
    }

    @Test
    public void givenScenario_whenRemoveSectionWithTag_thenSucceeds() {
        // Given
        int size = sectionedRecyclerViewAdapter.getCopyOfSectionsMap().size();

        // When
        sectionedRecyclerViewAdapter.removeSection(scenario.getSectionTag());

        // Then
        assertThat(sectionedRecyclerViewAdapter.getCopyOfSectionsMap().size(), is(size - 1));
        assertNull(sectionedRecyclerViewAdapter.getSection(scenario.getSectionTag()));
    }

    @Test
    public void givenInvalidSection_whenRemoveSectionWithSection_thenDoesNotRemoveAnything() {
        // Given
        int size = sectionedRecyclerViewAdapter.getCopyOfSectionsMap().size();
        Section section = mock(Section.class);

        // When
        sectionedRecyclerViewAdapter.removeSection(section);

        // Then
        assertThat(sectionedRecyclerViewAdapter.getCopyOfSectionsMap().size(), is(size));
    }

    @Test
    public void givenScenario_whenRemoveSectionWithSection_thenSucceeds() {
        // Given
        int size = sectionedRecyclerViewAdapter.getCopyOfSectionsMap().size();

        // When
        sectionedRecyclerViewAdapter.removeSection(scenario.getSection());

        // Then
        assertThat(sectionedRecyclerViewAdapter.getCopyOfSectionsMap().size(), is(size - 1));
        assertNull(sectionedRecyclerViewAdapter.getSection(scenario.getSectionTag()));
    }

    @Test
    public void givenScenario_whenGetItemCount_thenReturnsCorrectQuantity() {
        // When
        int result = sectionedRecyclerViewAdapter.getItemCount();

        // Then
        assertThat(result, is(24));
    }

    @Test
    public void givenScenario_whenGetCopyOfSectionsMap_thenReturnsCorrectSize() {
        // When
        int result = sectionedRecyclerViewAdapter.getCopyOfSectionsMap().size();

        // Then
        assertThat(result, is(4));
    }

    @Test
    public void givenScenario_whenGetSectionCount_thenCorrectNumberIsReturned() {
        // When
        int result = sectionedRecyclerViewAdapter.getSectionCount();

        // Then
        assertThat(result, is(4));
    }

    @Test
    public void givenIndex0_whenGetSectionWithIndex_thenCorrectSectionIsReturned() {
        // Given
        int index = 0;
        Section section = scenario.getSections().getValue(index);

        // When
        Section result = sectionedRecyclerViewAdapter.getSection(index);

        // Then
        assertThat(result, is(section));
    }

    @Test
    public void givenIndex1_whenGetSectionWithIndex_thenCorrectSectionIsReturned() {
        // Given
        int index = 1;
        Section section = scenario.getSections().getValue(index);

        // When
        Section result = sectionedRecyclerViewAdapter.getSection(index);

        // Then
        assertThat(result, is(section));
    }

    @Test
    public void givenIndex2_whenGetSectionWithIndex_thenCorrectSectionIsReturned() {
        // Given
        int index = 2;
        Section section = scenario.getSections().getValue(index);

        // When
        Section result = sectionedRecyclerViewAdapter.getSection(index);

        // Then
        assertThat(result, is(section));
    }

    @Test
    public void givenIndex3_whenGetSectionWithIndex_thenCorrectSectionIsReturned() {
        // Given
        int index = 3;
        Section section = scenario.getSections().getValue(index);

        // When
        Section result = sectionedRecyclerViewAdapter.getSection(index);

        // Then
        assertThat(result, is(section));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenInvalidIndex_whenGetSectionWithIndex_thenExceptionIsThrown() {
        // Given
        int index = 4;

        // When
        sectionedRecyclerViewAdapter.getSection(index);
    }

    @Test
    public void givenScenario_whenGetSection_thenReturnsCorrectSection() {
        // When
        Section result = sectionedRecyclerViewAdapter.getSection(scenario.getSectionTag());

        // Then
        assertThat(result, is(scenario.getSection()));
    }

    @Test
    public void givenFirstSection_whenGetSectionForPosition_thenReturnsCorrectSection() {
        // Given
        Section section = sectionedRecyclerViewAdapter.getSections().getValue(0);

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
        int viewTypeHeader = 0;


        // When
        int result = sectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeHeader);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER));
    }

    @Test
    public void givenViewType1_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        int viewTypeFooter = 1;

        // When
        int result = sectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeFooter);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER));
    }

    @Test
    public void givenViewType2_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        int viewTypeItemLoaded = 2;

        // When
        int result = sectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeItemLoaded);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED));
    }

    @Test
    public void givenViewType3_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        int viewTypeLoading = 3;

        // When
        int result = sectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeLoading);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_LOADING));
    }

    @Test
    public void givenViewType4_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        int viewTypeFailed = 4;

        // When
        int result = sectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeFailed);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_FAILED));
    }

    @Test
    public void givenViewType5_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        int viewTypeEmpty = 5;

        // When
        int result = sectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeEmpty);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_EMPTY));
    }

    @Test
    public void givenAdapterViewTypeForHeader_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        int viewTypeHeader = 12;

        // When
        int result = sectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeHeader);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER));
    }

    @Test
    public void givenAdapterViewTypeForFooter_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        int viewTypeFooter = 13;

        // When
        int result = sectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeFooter);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER));
    }

    @Test
    public void givenAdapterViewTypeForLoaded_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        int viewTypeItemLoaded = 14;

        // When
        int result = sectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeItemLoaded);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED));
    }

    @Test
    public void givenAdapterViewTypeForLoading_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        int viewTypeLoading = 15;

        // When
        int result = sectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeLoading);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_LOADING));
    }

    @Test
    public void givenAdapterViewTypeForFailed_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        int viewTypeFailed = 16;

        // When
        int result = sectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeFailed);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_FAILED));
    }

    @Test
    public void givenAdapterViewTypeForEmpty_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        int viewTypeEmpty = 17;

        // When
        int result = sectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeEmpty);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_EMPTY));
    }

    @Test
    public void givenAdapterViewTypeConstantForHeader_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        int viewTypeHeader = SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER;

        // When
        int result = sectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeHeader);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER));
    }

    @Test
    public void givenAdapterViewTypeConstantForFooter_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        int viewTypeFooter = SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER;

        // When
        int result = sectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeFooter);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER));
    }

    @Test
    public void givenAdapterViewTypeConstantForItemLoaded_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        int viewTypeItemLoaded = SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED;

        // When
        int result = sectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeItemLoaded);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED));
    }

    @Test
    public void givenAdapterViewTypeConstantForLoading_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        int viewTypeLoading = SectionedRecyclerViewAdapter.VIEW_TYPE_LOADING;

        // When
        int result = sectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeLoading);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_LOADING));
    }

    @Test
    public void givenAdapterViewTypeConstantForFailed_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        int viewTypeFailed = SectionedRecyclerViewAdapter.VIEW_TYPE_FAILED;

        // When
        int result = sectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeFailed);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_FAILED));
    }

    @Test
    public void givenAdapterViewTypeConstantForEmpty_whenGetSectionItemViewTypeForAdapterViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        int viewTypeEmpty = SectionedRecyclerViewAdapter.VIEW_TYPE_EMPTY;

        // When
        int result = sectionedRecyclerViewAdapter.getSectionItemViewTypeForAdapterViewType(viewTypeEmpty);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_EMPTY));
    }

    @Test
    public void givenAdapterPositionForHeader_whenGetSectionItemViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        int headerPosition = 12;

        // When
        int result = sectionedRecyclerViewAdapter.getSectionItemViewType(headerPosition);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER));
    }

    @Test
    public void givenAdapterPositionForFirstItem_whenGetSectionItemViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        int firstItemPosition = 13;

        // When
        int result = sectionedRecyclerViewAdapter.getSectionItemViewType(firstItemPosition);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED));
    }

    @Test
    public void givenAdapterPositionForLastItem_whenGetSectionItemViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        int lastItemPosition = 22;

        // When
        int result = sectionedRecyclerViewAdapter.getSectionItemViewType(lastItemPosition);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED));
    }

    @Test
    public void givenAdapterPositionForFooter_whenGetSectionItemViewType_thenReturnsCorrectSectionItemViewType() {
        // Given
        int footerPosition = 23;

        // When
        int result = sectionedRecyclerViewAdapter.getSectionItemViewType(footerPosition);

        // Then
        assertThat(result, is(SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER));
    }

    @Test
    public void givenAdapterPositionForHeader_whenGetItemViewType_thenReturnsCorrectItemViewType() {
        // Given
        int headerPosition = 12;

        // When
        int result = sectionedRecyclerViewAdapter.getItemViewType(headerPosition);

        // Then
        assertThat(result, is(12));
    }

    @Test
    public void givenAdapterPositionForFirstItem_whenGetItemViewType_thenReturnsCorrectItemViewType() {
        // Given
        int firstItemPosition = 13;

        // When
        int result = sectionedRecyclerViewAdapter.getItemViewType(firstItemPosition);

        // Then
        assertThat(result, is(14));
    }

    @Test
    public void givenAdapterPositionForLastItem_whenGetItemViewType_thenReturnsCorrectItemViewType() {
        // Given
        int lastItemPosition = 22;

        // When
        int result = sectionedRecyclerViewAdapter.getItemViewType(lastItemPosition);

        // Then
        assertThat(result, is(14));
    }

    @Test
    public void givenAdapterPositionForFooter_whenGetItemViewType_thenReturnsCorrectItemViewType() {
        // Given
        int footerPosition = 23;

        // When
        int result = sectionedRecyclerViewAdapter.getItemViewType(footerPosition);

        // Then
        assertThat(result, is(13));
    }

    @Test(expected = NullPointerException.class)
    public void givenViewTypeForHeader_whenOnCreateViewHolder_thenThrowsException() {
        // Given
        int viewTypeHeader = SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER;

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewTypeHeader);
    }

    @Test(expected = NullPointerException.class)
    public void givenViewTypeForFooter_whenOnCreateViewHolder_thenThrowsException() {
        // Given
        int viewTypeFooter = SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER;

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewTypeFooter);
    }

    @Test(expected = NullPointerException.class)
    public void givenViewTypeForLoading_whenOnCreateViewHolder_thenThrowsException() {
        // Given
        int viewTypeLoading = SectionedRecyclerViewAdapter.VIEW_TYPE_LOADING;

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewTypeLoading);
    }

    @Test(expected = NullPointerException.class)
    public void givenViewTypeForFailed_whenOnCreateViewHolder_thenThrowsException() {
        // Given
        int viewTypeFailed = SectionedRecyclerViewAdapter.VIEW_TYPE_FAILED;

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewTypeFailed);
    }

    @Test(expected = NullPointerException.class)
    public void givenViewTypeForEmpty_whenOnCreateViewHolder_thenThrowsException() {
        // Given
        int viewTypeEmpty = SectionedRecyclerViewAdapter.VIEW_TYPE_EMPTY;

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
    public void givenSectionAtIndex0_whenGetSectionIndex_thenReturnsCorrectIndex() {
        // Given
        int index = 0;
        Section section = scenario.getSections().getValue(index);

        // When
        int result = sectionedRecyclerViewAdapter.getSectionIndex(section);

        // Then
        assertThat(result, is(index));
    }

    @Test
    public void givenSectionAtIndex1_whenGetSectionIndex_thenReturnsCorrectIndex() {
        // Given
        int index = 1;
        Section section = scenario.getSections().getValue(index);

        // When
        int result = sectionedRecyclerViewAdapter.getSectionIndex(section);

        // Then
        assertThat(result, is(index));
    }

    @Test
    public void givenSectionAtIndex2_whenGetSectionIndex_thenReturnsCorrectIndex() {
        // Given
        int index = 2;
        Section section = scenario.getSections().getValue(index);

        // When
        int result = sectionedRecyclerViewAdapter.getSectionIndex(section);

        // Then
        assertThat(result, is(index));
    }

    @Test
    public void givenSectionAtIndex3_whenGetSectionIndex_thenReturnsCorrectIndex() {
        // Given
        int index = 3;
        Section section = scenario.getSections().getValue(index);

        // When
        int result = sectionedRecyclerViewAdapter.getSectionIndex(section);

        // Then
        assertThat(result, is(index));
    }

    @Test
    public void givenTagForFirstSection_whenGetSectionAdapter_thenReturnsCorrectSectionAdapter() {
        // Given
        int index = 0;
        Section section = scenario.getSections().getValue(index);
        String tag = getTagForSection(section);

        // When
        SectionAdapter sectionAdapter = sectionedRecyclerViewAdapter.getSectionAdapter(tag);

        // Then
        assertThat(sectionAdapter.getSectionPosition(), is(index));
    }

    @Test
    public void givenTagForSecondSection_whenGetSectionAdapter_thenReturnsCorrectSectionAdapter() {
        // Given
        int index = 1;
        Section section = scenario.getSections().getValue(index);
        String tag = getTagForSection(section);

        // When
        SectionAdapter sectionAdapter = sectionedRecyclerViewAdapter.getSectionAdapter(tag);

        // Then
        assertThat(sectionAdapter.getSection(), is(section));
    }

    @Test
    public void givenTagForThirdSection_whenGetSectionAdapter_thenReturnsCorrectSectionAdapter() {
        // Given
        int index = 2;
        Section section = scenario.getSections().getValue(index);
        String tag = getTagForSection(section);

        // When
        SectionAdapter sectionAdapter = sectionedRecyclerViewAdapter.getSectionAdapter(tag);

        // Then
        assertThat(sectionAdapter.getSection(), is(section));
    }

    @Test
    public void givenTagForFourthSection_whenGetSectionAdapter_thenReturnsCorrectSectionAdapter() {
        // Given
        int index = 3;
        Section section = scenario.getSections().getValue(index);
        String tag = getTagForSection(section);

        // When
        SectionAdapter sectionAdapter = sectionedRecyclerViewAdapter.getSectionAdapter(tag);

        // Then
        assertThat(sectionAdapter.getSection(), is(section));
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidTag_whenGetSectionAdapter_thenReturnsCorrectSectionAdapter() {
        // Given
        String tag = "tag";

        // When
        sectionedRecyclerViewAdapter.getSectionAdapter(tag);
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
