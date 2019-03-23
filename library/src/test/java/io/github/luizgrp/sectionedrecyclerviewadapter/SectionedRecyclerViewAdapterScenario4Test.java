package io.github.luizgrp.sectionedrecyclerviewadapter;

import org.junit.Before;
import org.junit.Test;

import io.github.luizgrp.sectionedrecyclerviewadapter.tools.Scenario4;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/*
 * Unit tests for SectionedRecyclerViewAdapter
 */
@SuppressWarnings({"PMD.MethodNamingConventions"})
public class SectionedRecyclerViewAdapterScenario4Test {

    private SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter;

    @Before
    public void setUp() {
        Scenario4 scenario = new Scenario4();

        sectionedRecyclerViewAdapter = new SectionedRecyclerViewAdapter();

        scenario.getSections().forEach((tag, section) -> sectionedRecyclerViewAdapter.addSection(tag, section));
    }

    @Test
    public void givenLoadingViewPositionInAdapter_whenGetItemViewType_thenReturnsCorrectViewType() {
        // Given
        int position = 0;

        // When
        int viewTypeLoading = sectionedRecyclerViewAdapter.getItemViewType(position);

        // Then
        assertThat(viewTypeLoading, is(3));
    }

    @Test
    public void givenFailedViewPositionInAdapter_whenGetItemViewType_thenReturnsCorrectViewType() {
        // Given
        int position = 2;

        // When
        int viewTypeFailed = sectionedRecyclerViewAdapter.getItemViewType(position);

        // Then
        assertThat(viewTypeFailed, is(16));
    }

    @Test
    public void givenEmptyViewPositionInAdapter_whenGetItemViewType_thenReturnsCorrectViewType() {
        // Given
        int position = 5;

        // When
        int viewTypeEmpty = sectionedRecyclerViewAdapter.getItemViewType(position);

        // Then
        assertThat(viewTypeEmpty, is(23));
    }
}
