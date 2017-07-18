package io.github.luizgrp.sectionedrecyclerviewadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.junit.Before;
import org.junit.Test;

import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy.BindingFootedStatelessSectionSpy;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy.BindingHeadedFootedStatelessSectionSpy;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy.BindingHeadedStatelessSectionSpy;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.spy.BindingStatelessSectionSpy;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.StatelessSectionStub;

import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@link StatelessSection}
 */
public class StatelessSectionTest {

    private final int ITEMS_QTY = 10;

    private SectionedRecyclerViewAdapter sectionAdapter;

    @Before
    public void setup() {
        sectionAdapter = new SectionedRecyclerViewAdapter();
    }

    @Test
    public void onBindViewHolder_withStatelessSection_isCalled() {
        // Given
        sectionAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));

        BindingStatelessSectionSpy sectionSpy = new BindingStatelessSectionSpy(ITEMS_QTY);
        sectionAdapter.addSection(sectionSpy);

        // When
        // StatelessSection - Items [10-19]
        sectionAdapter.onBindViewHolder(null, 10);

        // Then
        assertTrue(sectionSpy.onBindItemViewHolderWasCalled);
    }

    @Test
    public void onBindViewHolder_withHeadedStatelessSection_isCalled() {
        // Given
        sectionAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));

        BindingHeadedStatelessSectionSpy sectionSpy = new BindingHeadedStatelessSectionSpy(ITEMS_QTY);
        sectionAdapter.addSection(sectionSpy);

        // When
        // HeadedStatelessSection - Header [10]
        sectionAdapter.onBindViewHolder(null, 10);
        // HeadedStatelessSection - Items [11-20]
        sectionAdapter.onBindViewHolder(null, 11);

        // Then
        assertTrue(sectionSpy.onBindHeaderViewHolderWasCalled);
        assertTrue(sectionSpy.onBindItemViewHolderWasCalled);
    }

    @Test
    public void onBindViewHolder_withFootedStatelessSection_isCalled() {
        // Given
        sectionAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));

        BindingFootedStatelessSectionSpy sectionSpy = new BindingFootedStatelessSectionSpy(ITEMS_QTY);
        sectionAdapter.addSection(sectionSpy);

        // When
        // FootedStatelessSection - Items [10-19]
        sectionAdapter.onBindViewHolder(null, 10);
        // FootedStatelessSection - Footer [20]
        sectionAdapter.onBindViewHolder(null, 20);

        // Then
        assertTrue(sectionSpy.onBindItemViewHolderWasCalled);
        assertTrue(sectionSpy.onBindFooterViewHolderWasCalled);
    }

    @Test
    public void onBindViewHolder_withHeadedFootedStatelessSection_isCalled() {
        // Given
        sectionAdapter.addSection(new StatelessSectionStub(ITEMS_QTY));

        BindingHeadedFootedStatelessSectionSpy sectionSpy = new BindingHeadedFootedStatelessSectionSpy(ITEMS_QTY);
        sectionAdapter.addSection(sectionSpy);

        // When
        // HeadedFootedStatelessSection - Header [10]
        sectionAdapter.onBindViewHolder(null, 10);
        // HeadedFootedStatelessSection - Items [11-20]
        sectionAdapter.onBindViewHolder(null, 11);
        // HeadedFootedStatelessSection - Footer [21]
        sectionAdapter.onBindViewHolder(null, 21);

        // Then
        assertTrue(sectionSpy.onBindHeaderViewHolderWasCalled);
        assertTrue(sectionSpy.onBindItemViewHolderWasCalled);
        assertTrue(sectionSpy.onBindFooterViewHolderWasCalled);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_withLoadingResource_throwsException() {
        // Given
        final int ITEM_ID = 1;
        final int LOADING_ID = 2;

        @SuppressWarnings("ResourceType")
        SectionParameters sectionParameters = new SectionParameters.Builder(ITEM_ID)
                .loadingResourceId(LOADING_ID)
                .build();

        // When
        getStatelessSection(sectionParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_withFailedResource_throwsException() {
        // Given
        final int ITEM_ID = 1;
        final int FAILED_ID = 2;

        @SuppressWarnings("ResourceType")
        SectionParameters sectionParameters = new SectionParameters.Builder(ITEM_ID)
                .failedResourceId(FAILED_ID)
                .build();

        // When
        getStatelessSection(sectionParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_withEmptyResource_throwsException() {
        // Given
        final int ITEM_ID = 1;
        final int EMPTY_ID = 2;

        @SuppressWarnings("ResourceType")
        SectionParameters sectionParameters = new SectionParameters.Builder(ITEM_ID)
                .emptyResourceId(EMPTY_ID)
                .build();

        // When
        getStatelessSection(sectionParameters);
    }

    private StatelessSection getStatelessSection(SectionParameters sectionParameters) {
        return new StatelessSection(sectionParameters) {
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
