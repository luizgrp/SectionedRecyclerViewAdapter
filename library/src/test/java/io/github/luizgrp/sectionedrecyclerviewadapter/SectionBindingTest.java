package io.github.luizgrp.sectionedrecyclerviewadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static io.github.luizgrp.sectionedrecyclerviewadapter.Section.State;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/*
 * Unit tests for SectionedRecyclerViewAdapter, testing specifically whether the right get*View(), get*ViewHolder() and
 * onBind*ViewHolder() functions are called.
 */
@SuppressWarnings({"PMD.MethodNamingConventions"})
public class SectionBindingTest {

    private static final int ITEMS_QTY = 10;

    private SectionedRecyclerViewAdapter sectionAdapter;

    @Spy
    SectionImpl dummySection = new SectionImpl(); // This one is first, so that the section of interest is second.

    @Spy
    SectionImpl section = new SectionImpl();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        sectionAdapter = new SectionedRecyclerViewAdapter();
        sectionAdapter.addSection(dummySection);
        sectionAdapter.addSection(section);
    }

    @Test
    public void onBindViewHolder_withSection_isCalled() {
        // When
        // Section - Items [10-19]
        sectionAdapter.onBindViewHolder(null, 10);

        // Then
        verify(section, times(1)).onBindItemViewHolder(null, 0);
    }

    @Test
    public void onBindLoadingViewHolder_withSection_isCalled() {
        // Given
        section.setState(State.LOADING);

        // When
        // Section - Loading [10]
        sectionAdapter.onBindViewHolder(null, 10);

        // Then
        verify(section, times(1)).onBindLoadingViewHolder(null);
    }

    @Test
    public void onBindFailedViewHolder_withSection_isCalled() {
        // Given
        section.setState(State.FAILED);

        // When
        // Section - Failed [10]
        sectionAdapter.onBindViewHolder(null, 10);

        // Then
        verify(section, times(1)).onBindFailedViewHolder(null);
    }

    @Test
    public void onBindEmptyViewHolder_withSection_isCalled() {
        // Given
        section.setState(State.EMPTY);

        // When
        // Section - Empty [10]
        sectionAdapter.onBindViewHolder(null, 10);

        // Then
        verify(section, times(1)).onBindEmptyViewHolder(null);
    }

    @Test
    public void onBindViewHolder_withHeadedSection_isCalled() {
        // Given
        Section headedSection = spy(new SectionImpl(new SectionParameters.Builder(-1).headerResourceId(-1)));
        sectionAdapter.addSection(headedSection);

        // When
        // HeadedSection - Header [20]
        sectionAdapter.onBindViewHolder(null, 20);
        // HeadedSection - Items [21-30]
        sectionAdapter.onBindViewHolder(null, 22);

        // Then
        verify(headedSection, times(1)).onBindHeaderViewHolder(null);
        verify(headedSection, times(1)).onBindItemViewHolder(null, 22 - 21);
    }

    @Test
    public void onBindViewHolder_withFootedSection_isCalled() {
        // Given
        Section footedSection = spy(new SectionImpl(new SectionParameters.Builder(-1).footerViewWillBeProvided()));
        sectionAdapter.addSection(footedSection);

        // When
        // FootedSection - Items [20-29]
        sectionAdapter.onBindViewHolder(null, 25);
        // FootedSection - Footer [30]
        sectionAdapter.onBindViewHolder(null, 30);

        // Then
        verify(footedSection, times(1)).onBindItemViewHolder(null, 25 - 20);
        verify(footedSection, times(1)).onBindFooterViewHolder(null);
    }

    @Test
    public void onBindViewHolder_withHeadedFootedSection_isCalled() {
        // Given
        Section headedFootedSection = spy(new SectionImpl(new SectionParameters.Builder()
                .itemViewWillBeProvided()
                .headerViewWillBeProvided()
                .footerResourceId(-1)));
        sectionAdapter.addSection(headedFootedSection);

        // When
        // HeadedFootedSection - Header [20]
        sectionAdapter.onBindViewHolder(null, 20);
        // HeadedFootedSection - Items [21-30]
        sectionAdapter.onBindViewHolder(null, 21);
        sectionAdapter.onBindViewHolder(null, 28);
        // HeadedFootedSection - Footer [31]
        sectionAdapter.onBindViewHolder(null, 31);

        // Then
        verify(headedFootedSection, times(1)).onBindHeaderViewHolder(null);
        verify(headedFootedSection, times(1)).onBindItemViewHolder(null, 0);
        verify(headedFootedSection, times(1)).onBindItemViewHolder(null, 28 - 21);
        verify(headedFootedSection, times(1)).onBindFooterViewHolder(null);
    }

    private static class SectionImpl extends Section {

        public SectionImpl(SectionParameters.Builder builder) {
            super(builder.build());
        }

        public SectionImpl() {
            super(new SectionParameters.Builder()
                    .itemResourceId(-1)
                    .emptyResourceId(-1)
                    .failedViewWillBeProvided()
                    .loadingViewWillBeProvided()
                    .build());
        }

        @Override
        public int getContentItemsTotal() {
            return ITEMS_QTY;
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return null;
        }

        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        }
    }
}
