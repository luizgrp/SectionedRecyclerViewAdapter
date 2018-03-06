package io.github.luizgrp.sectionedrecyclerviewadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy.BindingFootedSectionSpy;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy.BindingHeadedFootedSectionSpy;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy.BindingHeadedSectionSpy;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy.BindingSectionSpy;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.SectionStub;

import static io.github.luizgrp.sectionedrecyclerviewadapter.Section.State;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/*
 * Unit tests for Section
 */
@SuppressWarnings({"PMD.MethodNamingConventions"})
public class SectionTest {

    private static final int ITEMS_QTY = 10;

    private SectionedRecyclerViewAdapter sectionAdapter;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        sectionAdapter = new SectionedRecyclerViewAdapter();
    }

    @Test
    public void constructor_withSectionParameters_constructsCorrectSection() {
        // Given
        final int itemId = 1;
        final int headerId = 2;
        final int footerId = 3;
        final int failedId = 4;
        final int loadingId = 5;
        final int emptyId = 6;

        @SuppressWarnings("ResourceType")
        SectionParameters sectionParameters = new SectionParameters.Builder(itemId)
            .headerResourceId(headerId)
            .footerResourceId(footerId)
            .failedResourceId(failedId)
            .loadingResourceId(loadingId)
            .emptyResourceId(emptyId)
            .build();

        // When
        Section section = getSection(sectionParameters);

        // Then
        assertThat(section.getItemResourceId(), is(itemId));
        assertThat(section.isItemViewWillBeProvided(), is(false));
        assertThat(section.getHeaderResourceId(), is(headerId));
        assertThat(section.isHeaderViewWillBeProvided(), is(false));
        assertThat(section.getFooterResourceId(), is(footerId));
        assertThat(section.isFooterViewWillBeProvided(), is(false));
        assertThat(section.getFailedResourceId(), is(failedId));
        assertThat(section.isFailedViewWillBeProvided(), is(false));
        assertThat(section.getLoadingResourceId(), is(loadingId));
        assertThat(section.isLoadingViewWillBeProvided(), is(false));
        assertThat(section.getEmptyResourceId(), is(emptyId));
        assertThat(section.isEmptyViewWillBeProvided(), is(false));
        assertThat(section.hasHeader(), is(true));
        assertThat(section.hasFooter(), is(true));
    }

    @Test
    public void constructor_withProvidedSectionParameters_constructsCorrectSection() {
        // Given
        final int emptyId = 6;

        @SuppressWarnings("ResourceType")
        SectionParameters sectionParameters = new SectionParameters.Builder()
                .itemViewWillBeProvided()
                .headerViewWillBeProvided()
                .loadingViewWillBeProvided()
                .emptyResourceId(emptyId)
                .build();

        // When
        Section section = getSection(sectionParameters);

        // Then
        assertThat(section.getItemResourceId(), is(nullValue()));
        assertThat(section.isItemViewWillBeProvided(), is(true));
        assertThat(section.getHeaderResourceId(), is(nullValue()));
        assertThat(section.isHeaderViewWillBeProvided(), is(true));
        assertThat(section.getFooterResourceId(), is(nullValue()));
        assertThat(section.isFooterViewWillBeProvided(), is(false));
        assertThat(section.getFailedResourceId(), is(nullValue()));
        assertThat(section.isFailedViewWillBeProvided(), is(false));
        assertThat(section.getLoadingResourceId(), is(nullValue()));
        assertThat(section.isLoadingViewWillBeProvided(), is(true));
        assertThat(section.getEmptyResourceId(), is(emptyId));
        assertThat(section.isEmptyViewWillBeProvided(), is(false));
        assertThat(section.hasHeader(), is(true));
        assertThat(section.hasFooter(), is(false));
    }

    @Test
    public void setState_withValidLoadingResId_succeeds() {
        // Given
        final int itemId = 1;
        final int loadingId = 2;

        @SuppressWarnings("ResourceType")
        SectionParameters sectionParameters = new SectionParameters.Builder(itemId)
            .loadingResourceId(loadingId)
            .build();
        Section section = getSection(sectionParameters);

        // When
        section.setState(State.LOADING);

        // Then
        assertThat(section.getState(), is(State.LOADING));
    }

    @Test
    public void setState_withLoadingViewProvided_succeeds() {
        // Given
        final int itemId = 1;

        @SuppressWarnings("ResourceType")
        SectionParameters sectionParameters = new SectionParameters.Builder()
                .itemResourceId(itemId)
                .loadingViewWillBeProvided()
                .build();
        Section section = getSection(sectionParameters);

        // When
        section.setState(State.LOADING);

        // Then
        assertThat(section.getState(), is(State.LOADING));
    }

    @Test
    public void setState_withMissingLoadingParameter_throwsException() {
        // Given
        final int itemId = 1;

        @SuppressWarnings("ResourceType")
        SectionParameters sectionParameters = new SectionParameters.Builder(itemId)
            .build();
        Section section = getSection(sectionParameters);

        // Expect exception
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Missing 'loading state' resource id or loadingViewWillBeProvided");

        // When
        section.setState(State.LOADING);
    }

    @Test
    public void setState_withMissingFailedResId_throwsException() {
        // Given
        final int itemId = 1;

        @SuppressWarnings("ResourceType")
        SectionParameters sectionParameters = new SectionParameters.Builder(itemId)
            .build();
        Section section = getSection(sectionParameters);

        // Expect exception
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Missing 'failed state' resource id or failedViewWillBeProvided");

        // When
        section.setState(State.FAILED);
    }

    @Test
    public void setState_withEmptyFailedResId_throwsException() {
        // Given
        final int itemId = 1;

        @SuppressWarnings("ResourceType")
        SectionParameters sectionParameters = new SectionParameters.Builder(itemId)
            .build();
        Section section = getSection(sectionParameters);

        // Expect exception
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Missing 'empty state' resource id or emptyViewWillBeProvided");

        // When
        section.setState(State.EMPTY);
    }

    @Test
    public void onBindViewHolder_withSection_isCalled() {
        // Given
        sectionAdapter.addSection(new SectionStub(ITEMS_QTY));

        BindingSectionSpy sectionSpy = new BindingSectionSpy(ITEMS_QTY);
        sectionAdapter.addSection(sectionSpy);

        // When
        // Section - Items [10-19]
        sectionAdapter.onBindViewHolder(null, 10);

        // Then
        assertTrue(sectionSpy.onBindItemViewHolderWasCalled);
    }

    @Test
    public void onBindLoadingViewHolder_withSection_isCalled() {
        // Given
        sectionAdapter.addSection(new SectionStub(ITEMS_QTY));

        BindingSectionSpy sectionSpy = new BindingSectionSpy(ITEMS_QTY);
        sectionAdapter.addSection(sectionSpy);

        sectionSpy.setState(State.LOADING);

        // When
        // Section - Loading [10]
        sectionAdapter.onBindViewHolder(null, 10);

        // Then
        assertTrue(sectionSpy.onBindLoadingViewHolderWasCalled);
    }

    @Test
    public void onBindFailedViewHolder_withSection_isCalled() {
        // Given
        sectionAdapter.addSection(new SectionStub(ITEMS_QTY));

        BindingSectionSpy sectionSpy = new BindingSectionSpy(ITEMS_QTY);
        sectionAdapter.addSection(sectionSpy);

        sectionSpy.setState(State.FAILED);

        // When
        // Section - Failed [10]
        sectionAdapter.onBindViewHolder(null, 10);

        // Then
        assertTrue(sectionSpy.onBindFailedViewHolderWasCalled);
    }

    @Test
    public void onBindEmptyViewHolder_withSection_isCalled() {
        // Given
        sectionAdapter.addSection(new SectionStub(ITEMS_QTY));

        BindingSectionSpy sectionSpy = new BindingSectionSpy(ITEMS_QTY);
        sectionAdapter.addSection(sectionSpy);

        sectionSpy.setState(State.EMPTY);

        // When
        // Section - Empty [10]
        sectionAdapter.onBindViewHolder(null, 10);

        // Then
        assertTrue(sectionSpy.onBindEmptyViewHolderWasCalled);
    }

    @Test
    public void onBindViewHolder_withHeadedSection_isCalled() {
        // Given
        sectionAdapter.addSection(new SectionStub(ITEMS_QTY));

        BindingHeadedSectionSpy sectionSpy = new BindingHeadedSectionSpy(ITEMS_QTY);
        sectionAdapter.addSection(sectionSpy);

        // When
        // HeadedSection - Header [10]
        sectionAdapter.onBindViewHolder(null, 10);
        // HeadedSection - Items [11-20]
        sectionAdapter.onBindViewHolder(null, 11);

        // Then
        assertTrue(sectionSpy.onBindHeaderViewHolderWasCalled);
        assertTrue(sectionSpy.onBindItemViewHolderWasCalled);
    }

    @Test
    public void onBindViewHolder_withFootedSection_isCalled() {
        // Given
        sectionAdapter.addSection(new SectionStub(ITEMS_QTY));

        BindingFootedSectionSpy sectionSpy = new BindingFootedSectionSpy(ITEMS_QTY);
        sectionAdapter.addSection(sectionSpy);

        // When
        // FootedSection - Items [10-19]
        sectionAdapter.onBindViewHolder(null, 10);
        // FootedSection - Footer [20]
        sectionAdapter.onBindViewHolder(null, 20);

        // Then
        assertTrue(sectionSpy.onBindItemViewHolderWasCalled);
        assertTrue(sectionSpy.onBindFooterViewHolderWasCalled);
    }

    @Test
    public void onBindViewHolder_withHeadedFootedSection_isCalled() {
        // Given
        sectionAdapter.addSection(new SectionStub(ITEMS_QTY));

        BindingHeadedFootedSectionSpy sectionSpy = new BindingHeadedFootedSectionSpy(ITEMS_QTY);
        sectionAdapter.addSection(sectionSpy);

        // When
        // HeadedFootedSection - Header [10]
        sectionAdapter.onBindViewHolder(null, 10);
        // HeadedFootedSection - Item [11]
        sectionAdapter.onBindViewHolder(null, 11);
        // HeadedFootedSection - Footer [21]
        sectionAdapter.onBindViewHolder(null, 21);

        // Then
        assertTrue(sectionSpy.onBindHeaderViewHolderWasCalled);
        assertTrue(sectionSpy.onBindItemViewHolderWasCalled);
        assertTrue(sectionSpy.onBindFooterViewHolderWasCalled);
    }

    private Section getSection(SectionParameters sectionParameters) {
        return new Section(sectionParameters) {
            @Override
            public int getContentItemsTotal() {
                return 0;
            }

            @Override
            public RecyclerView.ViewHolder getItemViewHolder(View view) {
                return null;
            }

            @Override
            public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

            }
        };
    }
}
