package io.github.luizgrp.sectionedrecyclerviewadapter;

import android.view.View;
import android.view.ViewGroup;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentMatcher;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import androidx.recyclerview.widget.RecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.tools.DummyViewAdapterFake;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*
 * Unit tests for SectionedRecyclerViewAdapter, testing specifically whether the right get*View(),
 * and get*ViewHolder() functions are called when onCreateViewHolder method is called.
 */
@SuppressWarnings({"PMD.MethodNamingConventions"})
public class SectionedRecyclerViewAdapterOnCreateViewHolderTest {

    private static final int ITEMS_QTY = 10;

    private SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Spy
    private SectionImpl dummySection = new SectionImpl(); // This one is first, so that the section of interest is second.

    @Spy
    private SectionImpl section = new SectionImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        sectionedRecyclerViewAdapter = new DummyViewAdapterFake();
        sectionedRecyclerViewAdapter.addSection(dummySection); // View types 0-5, items 0-9
        sectionedRecyclerViewAdapter.addSection(section); // View types 6-11, items 10-19
    }

    @Test
    public void givenSectionWithItemResourceId_whenOnCreateViewHolder_thenCallsGetItemViewHolder() {
        // Given
        final int itemViewType = 6 + SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED;

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, itemViewType);

        // Then
        verify(dummySection, never()).getItemView(any(ViewGroup.class));
        verify(dummySection, never()).getItemViewHolder(any(View.class));
        verify(section, never()).getItemView(any(ViewGroup.class));
        verify(section).getItemViewHolder(argThat(hasTag(-1)));
    }

    @Test
    public void givenSectionWithItemViewProvided_whenOnCreateViewHolder_thenCallsGetItemView() {
        // Given
        final int tag = 42;
        final int viewType = 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED;
        final Section providingSection = spy(new SectionImpl(
                SectionParameters.builder()
                        .itemViewWillBeProvided()
        ));
        doReturn(dummyViewWithTag(tag)).when(providingSection).getItemView(null);
        sectionedRecyclerViewAdapter.addSection(providingSection); // Third section, view types 12-17

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewType);

        // Then
        verify(section, never()).getItemView(any(ViewGroup.class));
        verify(section, never()).getItemViewHolder(any(View.class));
        verify(providingSection).getItemView(null);
        verify(providingSection).getItemViewHolder(argThat(hasTag(tag)));
    }

    @Test
    public void givenSectionWithItemViewProvidedAndGetItemViewReturnsNull_whenOnCreateViewHolder_thenThrowsException() {
        // Given
        final int viewType = 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED;
        final Section providingSection = spy(new SectionImpl(
                SectionParameters.builder()
                        .itemViewWillBeProvided()
        ));
        doReturn(null).when(providingSection).getItemView(null);
        sectionedRecyclerViewAdapter.addSection(providingSection); // Third section, view types 12-17

        // Expect exception
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Section.getItemView() returned null");

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewType);
    }

    @Test
    public void givenSectionWithHeaderResourceId_whenOnCreateViewHolder_thenCallsGetHeaderViewHolder() {
        // Given
        final int viewType = 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER;
        final Section headedSection = spy(new SectionImpl(
                SectionParameters.builder()
                        .itemResourceId(-1)
                        .headerResourceId(-2)
        ));
        sectionedRecyclerViewAdapter.addSection(headedSection);

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewType);

        // Then
        verify(section, never()).getHeaderView(any(ViewGroup.class));
        verify(section, never()).getHeaderViewHolder(any(View.class));
        verify(headedSection, never()).getHeaderView(any(ViewGroup.class));
        verify(headedSection).getHeaderViewHolder(argThat(hasTag(-2)));
    }

    @Test
    public void givenSectionWithHeaderViewProvided_whenOnCreateViewHolder_thenCallsGetHeaderView() {
        // Given
        final int tag = 44;
        final int viewType = 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER;
        final Section providingSection = spy(new SectionImpl(
                SectionParameters.builder()
                        .itemResourceId(-1)
                        .headerViewWillBeProvided()
        ));
        sectionedRecyclerViewAdapter.addSection(providingSection); // Third section, view types 12-17
        doReturn(dummyViewWithTag(tag)).when(providingSection).getHeaderView(null);

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewType);

        // Then
        verify(providingSection).getHeaderView(null);
        verify(providingSection).getHeaderViewHolder(argThat(hasTag(tag)));
    }

    @Test
    public void givenSectionWithHeaderViewProvidedAndGetHeaderViewReturnsNull_whenOnCreateViewHolder_thenThrowsException() {
        // Given
        final int viewType = 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER;
        final Section providingSection = spy(new SectionImpl(
                SectionParameters.builder()
                        .itemResourceId(-1)
                        .headerViewWillBeProvided()
        ));
        sectionedRecyclerViewAdapter.addSection(providingSection); // Third section, view types 12-17
        doReturn(null).when(providingSection).getHeaderView(null);

        // Expect exception
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Section.getHeaderView() returned null");

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewType);
    }

    @Test
    public void givenSectionWithFooterResourceId_whenOnCreateViewHolder_thenCallsGetFooterViewHolder() {
        // Given
        final int viewType = 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER;
        final Section footedSection = spy(new SectionImpl(
                SectionParameters.builder()
                        .itemResourceId(-1)
                        .footerResourceId(-3)
        ));
        sectionedRecyclerViewAdapter.addSection(footedSection);

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewType);

        // Then
        verify(section, never()).getFooterView(any(ViewGroup.class));
        verify(section, never()).getFooterViewHolder(any(View.class));
        verify(footedSection, never()).getFooterView(any(ViewGroup.class));
        verify(footedSection).getFooterViewHolder(argThat(hasTag(-3)));
    }

    @Test
    public void givenSectionWithFooterResourceId_whenOnCreateViewHolder_thenCallsGetFooterView() {
        // Given
        final int tag = 42;
        final int viewType = 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER;
        final Section providingSection = spy(new SectionImpl(
                SectionParameters.builder()
                        .itemResourceId(-1)
                        .footerViewWillBeProvided()
        ));
        sectionedRecyclerViewAdapter.addSection(providingSection); // Third section, view types 12-17
        doReturn(dummyViewWithTag(tag)).when(providingSection).getFooterView(null);

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewType);

        // Then
        verify(providingSection).getFooterView(null);
        verify(providingSection).getFooterViewHolder(argThat(hasTag(tag)));
    }

    @Test
    public void givenSectionWithFooterViewProvidedAndGetFooterViewReturnsNull_whenOnCreateViewHolder_thenThrowsException() {
        // Given
        final int viewType = 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER;
        final Section providingSection = spy(new SectionImpl(
                SectionParameters.builder()
                        .itemResourceId(-1)
                        .footerViewWillBeProvided()
        ));
        sectionedRecyclerViewAdapter.addSection(providingSection); // Third section, view types 12-17
        doReturn(null).when(providingSection).getFooterView(null);

        // Expect exception
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Section.getFooterView() returned null");

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewType);
    }

    @Test
    public void givenSectionWithLoadingResourceId_whenOnCreateViewHolder_thenCallsGetLoadingViewHolder() {
        // Given
        final int viewType = 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_LOADING;
        final Section resourceSection = spy(new SectionImpl(
                SectionParameters.builder()
                        .itemResourceId(-1)
                        .loadingResourceId(-5)
        ));
        sectionedRecyclerViewAdapter.addSection(resourceSection); // Third section, view types 12-17

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewType);

        // Then
        verify(resourceSection, never()).getLoadingView(any(ViewGroup.class));
        verify(resourceSection).getLoadingViewHolder(argThat(hasTag(-5)));
    }

    @Test
    public void givenSectionWithLoadingViewProvided_whenOnCreateViewHolder_thenCallsGetLoadingView() {
        // Given
        final int tag = 46;
        final int viewType = 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_LOADING;
        final Section providingSection = spy(new SectionImpl(
                SectionParameters.builder()
                        .itemResourceId(-1)
                        .loadingViewWillBeProvided()
        ));
        sectionedRecyclerViewAdapter.addSection(providingSection); // Third section, view types 12-17
        doReturn(dummyViewWithTag(tag)).when(providingSection).getLoadingView(null);

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewType);

        // Then
        verify(providingSection).getLoadingView(null);
        verify(providingSection).getLoadingViewHolder(argThat(hasTag(tag)));
    }

    @Test
    public void givenSectionWithLoadingViewProvidedAndGetLoadingViewReturnsNull_whenOnCreateViewHolder_thenThrowsException() {
        // Given
        final int viewType = 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_LOADING;
        final Section providingSection = spy(new SectionImpl(
                SectionParameters.builder()
                        .itemResourceId(-1)
                        .loadingViewWillBeProvided()
        ));
        sectionedRecyclerViewAdapter.addSection(providingSection); // Third section, view types 12-17
        doReturn(null).when(providingSection).getLoadingView(null);

        // Expect exception
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Section.getLoadingView() returned null");

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewType);
    }

    @Test
    public void givenSectionWithFailedResourceId_whenOnCreateViewHolder_thenCallsGetFailedViewHolder() {
        // Given
        final int viewType = 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_FAILED;
        final Section resourceSection = spy(new SectionImpl(
                SectionParameters.builder()
                        .itemResourceId(-1)
                        .loadingResourceId(-5)
                        .failedResourceId(-6)
        ));
        sectionedRecyclerViewAdapter.addSection(resourceSection); // Third section, view types 12-17

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewType);

        // Then
        verify(resourceSection, never()).getFailedView(any(ViewGroup.class));
        verify(resourceSection).getFailedViewHolder(argThat(hasTag(-6)));
    }

    @Test
    public void givenSectionWithFailedViewProvided_whenOnCreateViewHolder_thenCallsGetFailedView() {
        // Given
        final int tag = 48;
        final int viewType = 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_FAILED;
        final Section providingSection = spy(new SectionImpl(
                SectionParameters.builder()
                        .itemResourceId(-1)
                        .failedViewWillBeProvided()
        ));
        sectionedRecyclerViewAdapter.addSection(providingSection); // Third section, view types 12-17
        doReturn(dummyViewWithTag(tag)).when(providingSection).getFailedView(null);

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewType);

        // Then
        verify(providingSection).getFailedView(null);
        verify(providingSection).getFailedViewHolder(argThat(hasTag(tag)));
    }

    @Test
    public void givenSectionWithFailedViewProvidedAndGetFailedViewReturnsNull_whenOnCreateViewHolder_thenThrowsException() {
        // Given
        final int viewType = 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_FAILED;
        final Section providingSection = spy(new SectionImpl(
                SectionParameters.builder()
                        .itemResourceId(-1)
                        .failedViewWillBeProvided()
        ));
        sectionedRecyclerViewAdapter.addSection(providingSection); // Third section, view types 12-17
        doReturn(null).when(providingSection).getFailedView(null);

        // Expect exception
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Section.getFailedView() returned null");

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewType);
    }

    @Test
    public void givenSectionWithEmptyResourceId_whenOnCreateViewHolder_thenCallsGetEmptyViewHolder() {
        // Given
        final int viewType = 6 + SectionedRecyclerViewAdapter.VIEW_TYPE_EMPTY;

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewType);

        // Then
        verify(section, never()).getEmptyView(any(ViewGroup.class));
        verify(section).getEmptyViewHolder(argThat(hasTag(-1)));
    }

    @Test
    public void givenSectionWithEmptyViewProvided_whenOnCreateViewHolder_thenCallsGetEmptyView() {
        // Given
        final int tag = 50;
        final int viewType = 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_EMPTY;
        final Section providingSection = spy(new SectionImpl(
                SectionParameters.builder()
                        .itemResourceId(-1)
                        .emptyViewWillBeProvided()
        ));
        sectionedRecyclerViewAdapter.addSection(providingSection); // Third section, view types 12-17
        doReturn(dummyViewWithTag(tag)).when(providingSection).getEmptyView(null);

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewType);

        // Then
        verify(providingSection).getEmptyView(null);
        verify(providingSection).getEmptyViewHolder(argThat(hasTag(tag)));
    }

    @Test
    public void givenSectionWithEmptyViewProvidedAndGetEmptyViewReturnsNull_whenOnCreateViewHolder_thenThrowsException() {
        // Given
        final int viewType = 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_EMPTY;
        final Section providingSection = spy(new SectionImpl(
                SectionParameters.builder()
                        .itemResourceId(-1)
                        .emptyViewWillBeProvided()
        ));
        sectionedRecyclerViewAdapter.addSection(providingSection); // Third section, view types 12-17
        doReturn(null).when(providingSection).getEmptyView(null);

        // Expect exception
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Section.getEmptyView() returned null");

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onCreateViewHolder(null, viewType);
    }

    private static class SectionImpl extends Section {

        SectionImpl(SectionParameters.Builder builder) {
            super(builder.build());
        }

        SectionImpl() {
            super(SectionParameters.builder()
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

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return mock(RecyclerView.ViewHolder.class);
        }

        @Override
        public RecyclerView.ViewHolder getFooterViewHolder(View view) {
            return mock(RecyclerView.ViewHolder.class);
        }

        @Override
        public RecyclerView.ViewHolder getLoadingViewHolder(View view) {
            return mock(RecyclerView.ViewHolder.class);
        }

        @Override
        public RecyclerView.ViewHolder getFailedViewHolder(View view) {
            return mock(RecyclerView.ViewHolder.class);
        }

        @Override
        public RecyclerView.ViewHolder getEmptyViewHolder(View view) {
            return mock(RecyclerView.ViewHolder.class);
        }
    }

    /**
     * @param tag Any integer value.
     * @return A View that will have its tag set to the given value, to recognize it later on.
     */
    private static View dummyViewWithTag(final int tag) {
        final View view = mock(View.class);
        when(view.getTag()).thenReturn(tag);
        return view;
    }

    /**
     * @param tag Any integer value.
     * @return A Mockito argument matcher to check whether the argument was a View with its tag set to the given value.
     */
    private static ArgumentMatcher<View> hasTag(final int tag) {
        return argument -> argument != null
                && argument.getTag() != null
                && argument.getTag().equals(tag);
    }
}
