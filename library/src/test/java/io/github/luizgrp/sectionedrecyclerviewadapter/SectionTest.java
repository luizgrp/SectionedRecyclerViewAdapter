package io.github.luizgrp.sectionedrecyclerviewadapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static io.github.luizgrp.sectionedrecyclerviewadapter.Section.State;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/*
 * Unit tests for Section
 */
@SuppressWarnings({"PMD.MethodNamingConventions"})
public class SectionTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

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
        SectionParameters sectionParameters = SectionParameters.builder()
                .itemResourceId(itemId)
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
        SectionParameters sectionParameters = SectionParameters.builder()
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
        SectionParameters sectionParameters = SectionParameters.builder()
                .itemResourceId(itemId)
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
        SectionParameters sectionParameters = SectionParameters.builder()
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
        SectionParameters sectionParameters = SectionParameters.builder()
                .itemResourceId(itemId)
                .build();
        Section section = getSection(sectionParameters);

        // Expect exception
        expectedException.expect(IllegalStateException.class);

        // When
        section.setState(State.LOADING);
    }

    @Test
    public void setState_withMissingFailedResId_throwsException() {
        // Given
        final int itemId = 1;

        @SuppressWarnings("ResourceType")
        SectionParameters sectionParameters = SectionParameters.builder()
                .itemResourceId(itemId)
                .build();
        Section section = getSection(sectionParameters);

        // Expect exception
        expectedException.expect(IllegalStateException.class);

        // When
        section.setState(State.FAILED);
    }

    @Test
    public void setState_withEmptyFailedResId_throwsException() {
        // Given
        final int itemId = 1;

        @SuppressWarnings("ResourceType")
        SectionParameters sectionParameters = SectionParameters.builder()
                .itemResourceId(itemId)
                .build();
        Section section = getSection(sectionParameters);

        // Expect exception
        expectedException.expect(IllegalStateException.class);

        // When
        section.setState(State.EMPTY);
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
