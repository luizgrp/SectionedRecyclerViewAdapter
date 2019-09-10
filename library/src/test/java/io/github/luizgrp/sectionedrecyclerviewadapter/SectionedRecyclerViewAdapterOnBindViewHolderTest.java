package io.github.luizgrp.sectionedrecyclerviewadapter;

import android.view.View;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.tools.DummyViewAdapterFake;

import static io.github.luizgrp.sectionedrecyclerviewadapter.Section.State;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/*
 * Unit tests for SectionedRecyclerViewAdapter, testing specifically whether onBind*ViewHolder()
 * functions are called.
 */
@SuppressWarnings({"PMD.MethodNamingConventions"})
public class SectionedRecyclerViewAdapterOnBindViewHolderTest {

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
    public void givenItemPosition_whenOnBindViewHolder_thenCallsOnBindItemViewHolder() {
        // Given
        // Section - Items [10-19]
        final int itemPosition = 10;

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onBindViewHolder(null, itemPosition);

        // Then
        verify(section).onBindItemViewHolder(null, 0);
    }

    @Test
    public void givenItemPositionAndPayload_whenOnBindViewHolder_thenCallsOnBindItemViewHolder() {
        // Given
        // Section - Items [10-19]
        final int itemPosition = 10;

        final Object payload = new Object();
        final List<Object> payloads = new ArrayList<>();
        payloads.add(payload);

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onBindViewHolder(null, itemPosition, payloads);

        // Then
        verify(section).onBindItemViewHolder(null, 0, payloads);
    }

    @Test
    public void givenLoadingPosition_whenOnBindViewHolder_thenCallsOnBindLoadingViewHolder() {
        // Given
        // Section - Loading [10]
        final int loadingPosition = 10;
        section.setState(State.LOADING);

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onBindViewHolder(null, loadingPosition);

        // Then
        verify(section).onBindLoadingViewHolder(null);
    }

    @Test
    public void givenLoadingPositionAndPayload_whenOnBindViewHolder_thenCallsOnBindLoadingViewHolderWithPayload() {
        // Given
        // Section - Loading [10]
        final int loadingPosition = 10;
        section.setState(State.LOADING);

        final Object payload = new Object();
        final List<Object> payloads = new ArrayList<>();
        payloads.add(payload);

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onBindViewHolder(null, loadingPosition, payloads);

        // Then
        verify(section).onBindLoadingViewHolder(null, payloads);
    }

    @Test
    public void givenFailedPosition_whenOnBindViewHolder_thenCallsOnBindFailedViewHolder() {
        // Given
        // Section - Failed [10]
        final int failedPosition = 10;
        section.setState(State.FAILED);

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onBindViewHolder(null, failedPosition);

        // Then
        verify(section).onBindFailedViewHolder(null);
    }

    @Test
    public void givenFailedPositionAndPayload_whenOnBindViewHolder_thenCallsOnBindFailedViewHolderWithPayload() {
        // Given
        // Section - Failed [10]
        final int failedPosition = 10;
        section.setState(State.FAILED);

        final Object payload = new Object();
        final List<Object> payloads = new ArrayList<>();
        payloads.add(payload);

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onBindViewHolder(null, failedPosition, payloads);

        // Then
        verify(section).onBindFailedViewHolder(null, payloads);
    }

    @Test
    public void givenEmptyPosition_whenOnBindViewHolder_thenCallsOnBindEmptyViewHolder() {
        // Given
        // Section - Empty [10]
        final int emptyPosition = 10;
        section.setState(State.EMPTY);

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onBindViewHolder(null, emptyPosition);

        // Then
        verify(section).onBindEmptyViewHolder(null);
    }

    @Test
    public void givenEmptyPositionAndPayload_whenOnBindViewHolder_thenCallsOnBindEmptyViewHolderWithPayloads() {
        // Given
        // Section - Empty [10]
        final int emptyPosition = 10;
        section.setState(State.EMPTY);

        final Object payload = new Object();
        final List<Object> payloads = new ArrayList<>();
        payloads.add(payload);

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onBindViewHolder(null, emptyPosition, payloads);

        // Then
        verify(section).onBindEmptyViewHolder(null, payloads);
    }

    @Test
    public void givenHeaderPosition_whenOnBindViewHolder_thenCallsOnBindHeaderViewHolder() {
        // Given
        final Section headedSection = spy(new SectionImpl(
                SectionParameters.builder()
                        .itemResourceId(-1)
                        .headerResourceId(-1)
        ));
        sectionedRecyclerViewAdapter.addSection(headedSection); // Third section, items 10-19
        // HeadedSection - Header [20]
        final int headerPosition = 20;

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onBindViewHolder(null, headerPosition);

        // Then
        verify(headedSection).onBindHeaderViewHolder(null);
    }

    @Test
    public void givenHeaderPositionAndPayload_whenOnBindViewHolder_thenCallsOnBindHeaderViewHolderWithPayload() {
        // Given
        final Section headedSection = spy(new SectionImpl(
                SectionParameters.builder()
                        .itemResourceId(-1)
                        .headerResourceId(-1)
        ));
        sectionedRecyclerViewAdapter.addSection(headedSection); // Third section, items 10-19
        // HeadedSection - Header [20]
        final int headerPosition = 20;

        final Object payload = new Object();
        final List<Object> payloads = new ArrayList<>();
        payloads.add(payload);

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onBindViewHolder(null, headerPosition, payloads);

        // Then
        verify(headedSection).onBindHeaderViewHolder(null, payloads);
    }

    @Test
    public void givenFooterPosition_whenOnBindViewHolder_thenCallsOnBindFooterViewHolder() {
        // Given
        final Section footedSection = spy(new SectionImpl(
                SectionParameters.builder()
                        .itemResourceId(-1)
                        .footerViewWillBeProvided()
        ));
        sectionedRecyclerViewAdapter.addSection(footedSection); // Third section, items 10-19
        // FootedSection - Footer [30]
        final int footerPosition = 30;

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onBindViewHolder(null, footerPosition);

        // Then
        verify(footedSection).onBindFooterViewHolder(null);
    }

    @Test
    public void givenFooterPositionAndPayload_whenOnBindViewHolder_thenCallsOnBindFooterViewHolderWithPayloads() {
        // Given
        final Section footedSection = spy(new SectionImpl(
                SectionParameters.builder()
                        .itemResourceId(-1)
                        .footerViewWillBeProvided()
        ));
        sectionedRecyclerViewAdapter.addSection(footedSection); // Third section, items 10-19
        // FootedSection - Footer [30]
        final int footerPosition = 30;

        final Object payload = new Object();
        final List<Object> payloads = new ArrayList<>();
        payloads.add(payload);

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onBindViewHolder(null, footerPosition, payloads);

        // Then
        verify(footedSection).onBindFooterViewHolder(null, payloads);
    }

    @Test
    public void givenAnyPositionAndEmptyPayload_whenOnBindViewHolder_thenCallsOnBindItemViewHolderWithNOPayload() {
        // Given
        // Section - Items [10-19]
        final int itemPosition = 10;

        final List<Object> payloads = new ArrayList<>();

        // When
        //noinspection ConstantConditions
        sectionedRecyclerViewAdapter.onBindViewHolder(null, itemPosition, payloads);

        // Then
        verify(section).onBindItemViewHolder(null, 0);
    }

    private static class SectionImpl extends Section {

        SectionImpl(final SectionParameters.Builder builder) {
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
        public RecyclerView.ViewHolder getItemViewHolder(final View view) {
            return null;
        }

        @Override
        public void onBindItemViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        }
    }
}
