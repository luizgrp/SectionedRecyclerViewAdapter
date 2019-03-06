package io.github.luizgrp.sectionedrecyclerviewadapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentMatcher;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static io.github.luizgrp.sectionedrecyclerviewadapter.Section.State;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*
 * Unit tests for SectionedRecyclerViewAdapter, testing specifically whether the right get*View(), get*ViewHolder() and
 * onBind*ViewHolder() functions are called.
 */
@SuppressWarnings({"PMD.MethodNamingConventions"})
public class SectionBindingTest {

    private static final int ITEMS_QTY = 10;

    private SectionedRecyclerViewAdapter sectionAdapter;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Spy
    private SectionImpl dummySection = new SectionImpl(); // This one is first, so that the section of interest is second.

    @Spy
    private SectionImpl section = new SectionImpl();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        sectionAdapter = new AdapterImpl();
        sectionAdapter.addSection(dummySection); // View types 0-5, items 0-9
        sectionAdapter.addSection(section); // View types 6-11, items 10-19
    }

    @Test
    public void onCreateViewHolder_withItemResourceId_calls_getItemViewHolder() {
        // When
        //noinspection ConstantConditions
        sectionAdapter.onCreateViewHolder(null, 6 + SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED);

        // Then
        verify(dummySection, never()).getItemView(any(ViewGroup.class));
        verify(dummySection, never()).getItemViewHolder(any(View.class));
        verify(section, never()).getItemView(any(ViewGroup.class));
        verify(section, times(1)).getItemViewHolder(argThat(hasTag(-1)));
    }

    @Test
    public void onCreateViewHolder_withItemViewProvided_calls_getItemView() {
        // Given
        Section providingSection = spy(new SectionImpl(SectionParameters.builder().itemViewWillBeProvided()));
        sectionAdapter.addSection(providingSection); // Third section, view types 12-17
        doReturn(dummyViewWithTag(42)).when(providingSection).getItemView(null);

        // When
        //noinspection ConstantConditions
        sectionAdapter.onCreateViewHolder(null, 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED);

        // Then
        verify(section, never()).getItemView(any(ViewGroup.class));
        verify(section, never()).getItemViewHolder(any(View.class));
        verify(providingSection, times(1)).getItemView(null);
        verify(providingSection, times(1)).getItemViewHolder(argThat(hasTag(42)));
    }

    @Test
    public void onCreateViewHolder_withItemViewProvided_throws_when_getItemView_returns_null() {
        // Given
        Section providingSection = spy(new SectionImpl(SectionParameters.builder().itemViewWillBeProvided()));
        sectionAdapter.addSection(providingSection); // Third section, view types 12-17
        doReturn(null).when(providingSection).getItemView(null);

        // Expect exception
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Section.getItemView() returned null");

        // When
        //noinspection ConstantConditions
        sectionAdapter.onCreateViewHolder(null, 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED);
    }

    @Test
    public void onCreateViewHolder_withHeaderResourceId_calls_getHeaderViewHolder() {
        // Given
        Section headedSection = spy(new SectionImpl(SectionParameters.builder().itemResourceId(-1).headerResourceId(-2)));
        sectionAdapter.addSection(headedSection);

        // When
        //noinspection ConstantConditions
        sectionAdapter.onCreateViewHolder(null, 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER);

        // Then
        verify(section, never()).getHeaderView(any(ViewGroup.class));
        verify(section, never()).getHeaderViewHolder(any(View.class));
        verify(headedSection, never()).getHeaderView(any(ViewGroup.class));
        verify(headedSection, times(1)).getHeaderViewHolder(argThat(hasTag(-2)));
    }

    @Test
    public void onCreateViewHolder_withHeaderViewProvided_calls_getHeaderView() {
        // Given
        Section providingSection = spy(new SectionImpl(SectionParameters.builder().itemResourceId(-1).headerViewWillBeProvided()));
        sectionAdapter.addSection(providingSection); // Third section, view types 12-17
        doReturn(dummyViewWithTag(42)).when(providingSection).getHeaderView(null);

        // When
        //noinspection ConstantConditions
        sectionAdapter.onCreateViewHolder(null, 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER);

        // Then
        verify(providingSection, times(1)).getHeaderView(null);
        verify(providingSection, times(1)).getHeaderViewHolder(argThat(hasTag(42)));
    }

    @Test
    public void onCreateViewHolder_withHeaderViewProvided_throws_when_getHeaderView_returns_null() {
        // Given
        Section providingSection = spy(new SectionImpl(SectionParameters.builder().itemResourceId(-1).headerViewWillBeProvided()));
        sectionAdapter.addSection(providingSection); // Third section, view types 12-17
        doReturn(null).when(providingSection).getHeaderView(null);

        // Expect exception
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Section.getHeaderView() returned null");

        // When
        //noinspection ConstantConditions
        sectionAdapter.onCreateViewHolder(null, 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER);
    }

    @Test
    public void onCreateViewHolder_withFooterResourceId_calls_getFooterViewHolder() {
        // Given
        Section footedSection = spy(new SectionImpl(SectionParameters.builder().itemResourceId(-1).footerResourceId(-3)));
        sectionAdapter.addSection(footedSection);

        // When
        //noinspection ConstantConditions
        sectionAdapter.onCreateViewHolder(null, 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER);

        // Then
        verify(section, never()).getFooterView(any(ViewGroup.class));
        verify(section, never()).getFooterViewHolder(any(View.class));
        verify(footedSection, never()).getFooterView(any(ViewGroup.class));
        verify(footedSection, times(1)).getFooterViewHolder(argThat(hasTag(-3)));
    }

    @Test
    public void onCreateViewHolder_withFooterViewProvided_calls_getFooterView() {
        // Given
        Section providingSection = spy(new SectionImpl(SectionParameters.builder().itemResourceId(-1).footerViewWillBeProvided()));
        sectionAdapter.addSection(providingSection); // Third section, view types 12-17
        doReturn(dummyViewWithTag(42)).when(providingSection).getFooterView(null);

        // When
        //noinspection ConstantConditions
        sectionAdapter.onCreateViewHolder(null, 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER);

        // Then
        verify(providingSection, times(1)).getFooterView(null);
        verify(providingSection, times(1)).getFooterViewHolder(argThat(hasTag(42)));
    }

    @Test
    public void onCreateViewHolder_withFooterViewProvided_throws_when_getFooterView_returns_null() {
        // Given
        Section providingSection = spy(new SectionImpl(SectionParameters.builder().itemResourceId(-1).footerViewWillBeProvided()));
        sectionAdapter.addSection(providingSection); // Third section, view types 12-17
        doReturn(null).when(providingSection).getFooterView(null);

        // Expect exception
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Section.getFooterView() returned null");

        // When
        //noinspection ConstantConditions
        sectionAdapter.onCreateViewHolder(null, 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER);
    }

    @Test
    public void onCreateViewHolder_withLoadingResourceId_calls_getLoadingViewHolder() {
        // Given
        Section resourceSection = spy(new SectionImpl(SectionParameters.builder().itemResourceId(-1).loadingResourceId(-5)));
        sectionAdapter.addSection(resourceSection); // Third section, view types 12-17

        // When
        //noinspection ConstantConditions
        sectionAdapter.onCreateViewHolder(null, 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_LOADING);

        // Then
        verify(resourceSection, never()).getLoadingView(any(ViewGroup.class));
        verify(resourceSection, times(1)).getLoadingViewHolder(argThat(hasTag(-5)));
    }

    @Test
    public void onCreateViewHolder_withLoadingViewProvided_calls_getLoadingView() {
        // Given
        Section providingSection = spy(new SectionImpl(SectionParameters.builder().itemResourceId(-1).loadingViewWillBeProvided()));
        sectionAdapter.addSection(providingSection); // Third section, view types 12-17
        doReturn(dummyViewWithTag(42)).when(providingSection).getLoadingView(null);

        // When
        //noinspection ConstantConditions
        sectionAdapter.onCreateViewHolder(null, 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_LOADING);

        // Then
        verify(providingSection, times(1)).getLoadingView(null);
        verify(providingSection, times(1)).getLoadingViewHolder(argThat(hasTag(42)));
    }

    @Test
    public void onCreateViewHolder_withLoadingViewProvided_throws_when_getLoadingView_returns_null() {
        // Given
        Section providingSection = spy(new SectionImpl(SectionParameters.builder().itemResourceId(-1).loadingViewWillBeProvided()));
        sectionAdapter.addSection(providingSection); // Third section, view types 12-17
        doReturn(null).when(providingSection).getLoadingView(null);

        // Expect exception
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Section.getLoadingView() returned null");

        // When
        //noinspection ConstantConditions
        sectionAdapter.onCreateViewHolder(null, 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_LOADING);
    }

    @Test
    public void onCreateViewHolder_withFailedResourceId_calls_getFailedViewHolder() {
        // Given
        Section resourceSection = spy(new SectionImpl(SectionParameters.builder().itemResourceId(-1)
                .loadingResourceId(-5)
                .failedResourceId(-6)));
        sectionAdapter.addSection(resourceSection); // Third section, view types 12-17

        // When
        //noinspection ConstantConditions
        sectionAdapter.onCreateViewHolder(null, 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_FAILED);

        // Then
        verify(resourceSection, never()).getFailedView(any(ViewGroup.class));
        verify(resourceSection, times(1)).getFailedViewHolder(argThat(hasTag(-6)));
    }

    @Test
    public void onCreateViewHolder_withFailedViewProvided_calls_getFailedView() {
        // Given
        Section providingSection = spy(new SectionImpl(SectionParameters.builder().itemResourceId(-1).failedViewWillBeProvided()));
        sectionAdapter.addSection(providingSection); // Third section, view types 12-17
        doReturn(dummyViewWithTag(42)).when(providingSection).getFailedView(null);

        // When
        //noinspection ConstantConditions
        sectionAdapter.onCreateViewHolder(null, 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_FAILED);

        // Then
        verify(providingSection, times(1)).getFailedView(null);
        verify(providingSection, times(1)).getFailedViewHolder(argThat(hasTag(42)));
    }

    @Test
    public void onCreateViewHolder_withFailedViewProvided_throws_when_getFailedView_returns_null() {
        // Given
        Section providingSection = spy(new SectionImpl(SectionParameters.builder().itemResourceId(-1).failedViewWillBeProvided()));
        sectionAdapter.addSection(providingSection); // Third section, view types 12-17
        doReturn(null).when(providingSection).getFailedView(null);

        // Expect exception
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Section.getFailedView() returned null");

        // When
        //noinspection ConstantConditions
        sectionAdapter.onCreateViewHolder(null, 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_FAILED);
    }

    @Test
    public void onCreateViewHolder_withEmptyResourceId_calls_getEmptyViewHolder() {
        // When
        //noinspection ConstantConditions
        sectionAdapter.onCreateViewHolder(null, 6 + SectionedRecyclerViewAdapter.VIEW_TYPE_EMPTY);

        // Then
        verify(section, never()).getEmptyView(any(ViewGroup.class));
        verify(section, times(1)).getEmptyViewHolder(argThat(hasTag(-1)));
    }

    @Test
    public void onCreateViewHolder_withEmptyViewProvided_calls_getEmptyView() {
        // Given
        Section providingSection = spy(new SectionImpl(SectionParameters.builder().itemResourceId(-1).emptyViewWillBeProvided()));
        sectionAdapter.addSection(providingSection); // Third section, view types 12-17
        doReturn(dummyViewWithTag(42)).when(providingSection).getEmptyView(null);

        // When
        //noinspection ConstantConditions
        sectionAdapter.onCreateViewHolder(null, 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_EMPTY);

        // Then
        verify(providingSection, times(1)).getEmptyView(null);
        verify(providingSection, times(1)).getEmptyViewHolder(argThat(hasTag(42)));
    }

    @Test
    public void onCreateViewHolder_withEmptyViewProvided_throws_when_getEmptyView_returns_null() {
        // Given
        Section providingSection = spy(new SectionImpl(SectionParameters.builder().itemResourceId(-1).emptyViewWillBeProvided()));
        sectionAdapter.addSection(providingSection); // Third section, view types 12-17
        doReturn(null).when(providingSection).getEmptyView(null);

        // Expect exception
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Section.getEmptyView() returned null");

        // When
        //noinspection ConstantConditions
        sectionAdapter.onCreateViewHolder(null, 12 + SectionedRecyclerViewAdapter.VIEW_TYPE_EMPTY);
    }

    @Test
    public void onBindViewHolder_withSection_isCalled() {
        // When
        // Section - Items [10-19]
        //noinspection ConstantConditions
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
        //noinspection ConstantConditions
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
        //noinspection ConstantConditions
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
        //noinspection ConstantConditions
        sectionAdapter.onBindViewHolder(null, 10);

        // Then
        verify(section, times(1)).onBindEmptyViewHolder(null);
    }

    @Test
    public void onBindViewHolder_withHeadedSection_isCalled() {
        // Given
        Section headedSection = spy(new SectionImpl(SectionParameters.builder().itemResourceId(-1).headerResourceId(-1)));
        sectionAdapter.addSection(headedSection); // Third section, items 10-19

        // When
        // HeadedSection - Header [20]
        //noinspection ConstantConditions
        sectionAdapter.onBindViewHolder(null, 20);
        // HeadedSection - Items [21-30]
        //noinspection ConstantConditions
        sectionAdapter.onBindViewHolder(null, 22);

        // Then
        verify(headedSection, times(1)).onBindHeaderViewHolder(null);
        verify(headedSection, times(1)).onBindItemViewHolder(null, 22 - 21);
    }

    @Test
    public void onBindViewHolder_withFootedSection_isCalled() {
        // Given
        Section footedSection = spy(new SectionImpl(SectionParameters.builder().itemResourceId(-1).footerViewWillBeProvided()));
        sectionAdapter.addSection(footedSection); // Third section, items 10-19

        // When
        // FootedSection - Items [20-29]
        //noinspection ConstantConditions
        sectionAdapter.onBindViewHolder(null, 25);
        // FootedSection - Footer [30]
        //noinspection ConstantConditions
        sectionAdapter.onBindViewHolder(null, 30);

        // Then
        verify(footedSection, times(1)).onBindItemViewHolder(null, 25 - 20);
        verify(footedSection, times(1)).onBindFooterViewHolder(null);
    }

    @Test
    public void onBindViewHolder_withHeadedFootedSection_isCalled() {
        // Given
        Section headedFootedSection = spy(new SectionImpl(SectionParameters.builder()
                .itemViewWillBeProvided()
                .headerViewWillBeProvided()
                .footerResourceId(-1)));
        sectionAdapter.addSection(headedFootedSection); // Third section, items 10-19

        // When
        // HeadedFootedSection - Header [20]
        //noinspection ConstantConditions
        sectionAdapter.onBindViewHolder(null, 20);
        // HeadedFootedSection - Items [21-30]
        //noinspection ConstantConditions
        sectionAdapter.onBindViewHolder(null, 21);
        //noinspection ConstantConditions
        sectionAdapter.onBindViewHolder(null, 28);
        // HeadedFootedSection - Footer [31]
        //noinspection ConstantConditions
        sectionAdapter.onBindViewHolder(null, 31);

        // Then
        verify(headedFootedSection, times(1)).onBindHeaderViewHolder(null);
        verify(headedFootedSection, times(1)).onBindItemViewHolder(null, 0);
        verify(headedFootedSection, times(1)).onBindItemViewHolder(null, 28 - 21);
        verify(headedFootedSection, times(1)).onBindFooterViewHolder(null);
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
    }

    /**
     * Override the inflate method in order to avoid mocking the LayoutInflater.
     */
    private static class AdapterImpl extends SectionedRecyclerViewAdapter {
        @Override
        View inflate(int layoutResourceId, ViewGroup parent) {
            return dummyViewWithTag(layoutResourceId);
        }
    }

    /**
     * @param tag Any integer value.
     * @return A View that will have its tag set to the given value, to recognize it later on.
     */
    private static View dummyViewWithTag(int tag) {
        View view = mock(View.class);
        when(view.getTag()).thenReturn(tag);
        return view;
    }

    /**
     * @param tag Any integer value.
     * @return A Mockito argument matcher to check whether the argument was a View with its tag set to the given value.
     */
    private static ArgumentMatcher<View> hasTag(final int tag) {
        return new ArgumentMatcher<View>() {
            @Override
            public boolean matches(View argument) {
                return argument != null && argument.getTag() != null && argument.getTag().equals(tag);
            }
        };
    }
}
