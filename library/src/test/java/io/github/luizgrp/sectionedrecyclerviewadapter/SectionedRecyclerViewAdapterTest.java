package io.github.luizgrp.sectionedrecyclerviewadapter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.FootedSectionStub;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.FootedStatelessSectionStub;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.HeadedFootedSectionStub;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.HeadedFootedStatelessSectionStub;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.HeadedSectionStub;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.HeadedStatelessSectionStub;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.SectionStub;
import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.StatelessSectionStub;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Gustavo Pagani
 */
public class SectionedRecyclerViewAdapterTest {

    final int ITEMS_QTY = 10;
    final String SECTION_TAG = "tag";

    @Test
    public void getItemCount_emptyAdapter_isZero() {
        assertThat(new SectionedRecyclerViewAdapter().getItemCount(), is(0));
    }

    @Test
    public void getSectionsMap_emptyAdapter_isEmpty() {
        assertTrue(new SectionedRecyclerViewAdapter().getSectionsMap().isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getSectionPosition_emptyAdapter_throwsException() {
        new SectionedRecyclerViewAdapter().getSectionPosition(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getSectionForPosition_emptyAdapter_throwsException() {
        new SectionedRecyclerViewAdapter().getSectionForPosition(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getItemViewType_emptyAdapter_throwsException() {
        new SectionedRecyclerViewAdapter().getItemViewType(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getSectionItemViewType_emptyAdapter_throwsException() {
        new SectionedRecyclerViewAdapter().getSectionItemViewType(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void onBindViewHolder_emptyAdapter_throwsException() {
        new SectionedRecyclerViewAdapter().onBindViewHolder(null, 0);
    }

    @Test
    public void addSection_emptyAdapter_succeeds() {
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();
        StatelessSectionStub sectionStub = addStatelessSectionStubToAdapter(sectionAdapter);

        final String SECTION_TAG = sectionAdapter.addSection(sectionStub);

        assertSame(sectionStub, sectionAdapter.getSection(SECTION_TAG));
        assertSame(sectionStub, sectionAdapter.getSectionsMap().get(SECTION_TAG));
    }

    @Test
    public void addSectionWithTag_emptyAdapter_succeeds() {
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();
        StatelessSectionStub sectionStub = addStatelessSectionStubToAdapter(sectionAdapter);

        sectionAdapter.addSection(SECTION_TAG, sectionStub);

        assertSame(sectionStub, sectionAdapter.getSection(SECTION_TAG));
        assertSame(sectionStub, sectionAdapter.getSectionsMap().get(SECTION_TAG));
    }

    @Test
    public void getSectionWithTag_removedSection_returnsNull() {
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();
        StatelessSectionStub sectionStub = addStatelessSectionStubToAdapter(sectionAdapter);

        final String SECTION_TAG = sectionAdapter.addSection(sectionStub);
        sectionAdapter.removeSection(SECTION_TAG);

        assertNull(sectionAdapter.getSection(SECTION_TAG));
    }

    @Test
    public void getSectionWithTag_emptyAdapter_returnsNull() {
        assertNull(new SectionedRecyclerViewAdapter().getSection(SECTION_TAG));
    }

    @Test
    public void getItemCount_with8VisiblePlus2InvisibleSections_is44() {
        SectionedRecyclerViewAdapter sectionAdapter = getAdapterWith4StatelessSectionsAnd4Sections();

        // invisible sections don't increase the total item count
        StatelessSectionStub invisibleSectionStub = addStatelessSectionStubToAdapter(sectionAdapter);
        invisibleSectionStub.setVisible(false);

        HeadedStatelessSectionStub invisibleHeadedSectionSub = addHeadedStatelessSectionStubToAdapter(sectionAdapter);
        invisibleHeadedSectionSub.setVisible(false);

        // 10 + 11 + 11 + 12 + 10 + 11 + 11 + 12
        assertThat(sectionAdapter.getItemCount(), is(88));
    }

    @Test
    public void getSectionsMap_with8VisiblePlus2InvisibleSections_hasSize6() {
        SectionedRecyclerViewAdapter sectionAdapter = getAdapterWith4StatelessSectionsAnd4Sections();

        FootedStatelessSectionStub invisibleFootedSectionSub = addFootedStatelessSectionStubToAdapter(sectionAdapter);
        invisibleFootedSectionSub.setVisible(false);

        HeadedFootedStatelessSectionStub invisibleHeadedFootedSectionSub = addHeadedFootedStatelessSectionStubToAdapter(sectionAdapter);
        invisibleHeadedFootedSectionSub.setVisible(false);

        assertThat(sectionAdapter.getSectionsMap().size(), is(10));
    }

    @Test
    public void getSection_emptyAdapter_isNull() {
        assertNull(new SectionedRecyclerViewAdapter().getSection(SECTION_TAG));
    }

    @Test
    public void getSection_with5Sections_returnsCorrectSection() {
        SectionedRecyclerViewAdapter sectionAdapter = getAdapterWith4StatelessSectionsAnd4Sections();
        StatelessSectionStub sectionStub = addStatelessSectionStubToAdapter(sectionAdapter);

        final String SECTION_TAG = sectionAdapter.addSection(sectionStub);

        assertSame(sectionStub, sectionAdapter.getSection(SECTION_TAG));
    }

    @Test
    public void getSectionForPosition_withManySections_returnsCorrectValuesForStatelessSection() {
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();

        StatelessSectionStub sectionStub = addStatelessSectionStubToAdapter(sectionAdapter);
        addHeadedStatelessSectionStubToAdapter(sectionAdapter);
        addFootedStatelessSectionStubToAdapter(sectionAdapter);
        addHeadedFootedStatelessSectionStubToAdapter(sectionAdapter);

        // StatelessSection - Items from 0 to 9
        assertSame(sectionAdapter.getSectionForPosition(0), sectionStub);
        assertSame(sectionAdapter.getSectionForPosition(9), sectionStub);
    }

    @Test
    public void getSectionForPosition_withManySections_returnsCorrectValuesForHeadedStatelessSection() {
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();

        addStatelessSectionStubToAdapter(sectionAdapter);
        HeadedStatelessSectionStub sectionStub = addHeadedStatelessSectionStubToAdapter(sectionAdapter);
        addFootedStatelessSectionStubToAdapter(sectionAdapter);
        addHeadedFootedStatelessSectionStubToAdapter(sectionAdapter);

        // HeadedStatelessSection - Header at 10
        assertSame(sectionAdapter.getSectionForPosition(10), sectionStub);
        // HeadedStatelessSection - Items from 11 to 20
        assertSame(sectionAdapter.getSectionForPosition(11), sectionStub);
        assertSame(sectionAdapter.getSectionForPosition(20), sectionStub);
    }

    @Test
    public void getSectionForPosition_withManySections_returnsCorrectValuesForFootedStatelessSection() {
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();

        addStatelessSectionStubToAdapter(sectionAdapter);
        addHeadedStatelessSectionStubToAdapter(sectionAdapter);
        FootedStatelessSectionStub sectionStub = addFootedStatelessSectionStubToAdapter(sectionAdapter);
        addHeadedFootedStatelessSectionStubToAdapter(sectionAdapter);

        // FootedStatelessSection - Items from 21 to 30
        assertSame(sectionAdapter.getSectionForPosition(21), sectionStub);
        assertSame(sectionAdapter.getSectionForPosition(30), sectionStub);
        // FootedStatelessSection - Footer at 31
        assertSame(sectionAdapter.getSectionForPosition(31), sectionStub);
    }

    @Test
    public void getSectionForPosition_withManySections_returnsCorrectValuesForHeadedFootedStatelessSection() {
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();

        addStatelessSectionStubToAdapter(sectionAdapter);
        addHeadedStatelessSectionStubToAdapter(sectionAdapter);
        addFootedStatelessSectionStubToAdapter(sectionAdapter);
        HeadedFootedStatelessSectionStub sectionStub = addHeadedFootedStatelessSectionStubToAdapter(sectionAdapter);

        // HeadedFootedStatelessSection - Header at 32
        assertSame(sectionAdapter.getSectionForPosition(32), sectionStub);
        // HeadedFootedStatelessSection - Items from 33 to 42
        assertSame(sectionAdapter.getSectionForPosition(33), sectionStub);
        assertSame(sectionAdapter.getSectionForPosition(42), sectionStub);
        // HeadedFootedStatelessSection - Footer at 43
        assertSame(sectionAdapter.getSectionForPosition(43), sectionStub);
    }

    @Test
    public void getSectionItemViewType_withManySections_returnsCorrectValuesForStatelessSection() {
        SectionedRecyclerViewAdapter sectionAdapter = getAdapterWith4StatelessSectionsAnd4Sections();

        // StatelessSection - Items from 0 to 9
        assertThat(sectionAdapter.getSectionItemViewType(0), is(SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED));
        assertThat(sectionAdapter.getSectionItemViewType(9), is(SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED));
    }

    @Test
    public void getSectionItemViewType_withManySections_returnsCorrectValuesForHeadedStatelessSection() {
        SectionedRecyclerViewAdapter sectionAdapter = getAdapterWith4StatelessSectionsAnd4Sections();

        // HeadedStatelessSection - Header at 10
        assertThat(sectionAdapter.getSectionItemViewType(10), is(SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER));
        // HeadedStatelessSection - Items from 11 to 20
        assertThat(sectionAdapter.getSectionItemViewType(11), is(SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED));
        assertThat(sectionAdapter.getSectionItemViewType(20), is(SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED));
    }

    @Test
    public void getSectionItemViewType_withManySections_returnsCorrectValuesForFootedStatelessSection() {
        SectionedRecyclerViewAdapter sectionAdapter = getAdapterWith4StatelessSectionsAnd4Sections();

        // FootedStatelessSection - Items from 21 to 30
        assertThat(sectionAdapter.getSectionItemViewType(21), is(SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED));
        assertThat(sectionAdapter.getSectionItemViewType(30), is(SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED));
        // FootedStatelessSection - Footer at 31
        assertThat(sectionAdapter.getSectionItemViewType(31), is(SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER));
    }

    @Test
    public void getSectionItemViewType_withManySections_returnsCorrectValuesForHeadedFootedStatelessSection() {
        SectionedRecyclerViewAdapter sectionAdapter = getAdapterWith4StatelessSectionsAnd4Sections();

        // HeadedFootedStatelessSection - Header at 32
        assertThat(sectionAdapter.getSectionItemViewType(32), is(SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER));
        // HeadedFootedStatelessSection - Items from 33 to 42
        assertThat(sectionAdapter.getSectionItemViewType(33), is(SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED));
        assertThat(sectionAdapter.getSectionItemViewType(42), is(SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED));
        // HeadedFootedStatelessSection - Footer at 43
        assertThat(sectionAdapter.getSectionItemViewType(43), is(SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER));
    }

    @Test
    public void getItemViewType_withManySections_returnsCorrectValuesForStatelessSection() {
        SectionedRecyclerViewAdapter sectionAdapter = getAdapterWith4StatelessSectionsAnd4Sections();

        // StatelessSection [0-4] - Items are type 2
        final int ITEM_TYPE = 2;

        assertThat(sectionAdapter.getItemViewType(0), is(ITEM_TYPE));
        assertThat(sectionAdapter.getItemViewType(9), is(ITEM_TYPE));
    }

    @Test
    public void getItemViewType_withManySections_returnsCorrectValuesForHeadedStatelessSection() {
        SectionedRecyclerViewAdapter sectionAdapter = getAdapterWith4StatelessSectionsAnd4Sections();

        // HeadedStatelessSection [5-9] - Header is type 5
        final int HEADER_TYPE = 5;
        // HeadedStatelessSection [5-9] - Items are type 7
        final int ITEM_TYPE = 7;


        assertThat(sectionAdapter.getItemViewType(10), is(HEADER_TYPE));

        assertThat(sectionAdapter.getItemViewType(11), is(ITEM_TYPE));
        assertThat(sectionAdapter.getItemViewType(20), is(ITEM_TYPE));
    }

    @Test
    public void getItemViewType_withManySections_returnsCorrectValuesForFootedStatelessSection() {
        SectionedRecyclerViewAdapter sectionAdapter = getAdapterWith4StatelessSectionsAnd4Sections();

        // FootedStatelessSection [10-14] - Items are type 12
        final int ITEM_TYPE = 12;
        // FootedStatelessSection [10-14] - Footer is type 11
        final int FOOTER_TYPE = 11;

        assertThat(sectionAdapter.getItemViewType(21), is(ITEM_TYPE));
        assertThat(sectionAdapter.getItemViewType(30), is(ITEM_TYPE));
        assertThat(sectionAdapter.getItemViewType(31), is(FOOTER_TYPE));
    }

    @Test
    public void getItemViewType_withManySections_returnsCorrectValuesForHeadedFootedStatelessSection() {
        SectionedRecyclerViewAdapter sectionAdapter = getAdapterWith4StatelessSectionsAnd4Sections();

        // HeadedFootedStatelessSection [15-19] - Header is type 15
        final int HEADER_TYPE = 15;
        // HeadedFootedStatelessSection [15-19] - Items are type 17
        final int ITEM_TYPE = 17;
        // HeadedFootedStatelessSection [15-19] - Footer is type 16
        final int FOOTER_TYPE = 16;

        assertThat(sectionAdapter.getItemViewType(32), is(HEADER_TYPE));
        assertThat(sectionAdapter.getItemViewType(33), is(ITEM_TYPE));
        assertThat(sectionAdapter.getItemViewType(42), is(ITEM_TYPE));
        assertThat(sectionAdapter.getItemViewType(43), is(FOOTER_TYPE));
    }

    @Test
    public void getItemViewType_withManySections_returnsCorrectValuesForSection() {
        SectionedRecyclerViewAdapter sectionAdapter = getAdapterWith4StatelessSectionsAnd4Sections();

        // Section [20-24] - Items are type 22
        final int ITEM_TYPE = 22;

        assertThat(sectionAdapter.getItemViewType(44), is(ITEM_TYPE));
        assertThat(sectionAdapter.getItemViewType(53), is(ITEM_TYPE));
    }

    @Test
    public void getItemViewType_withManySections_returnsCorrectValuesForSectionWithLoadingState() {
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();

        addStatelessSectionStubToAdapter(sectionAdapter);
        addHeadedStatelessSectionStubToAdapter(sectionAdapter);
        addFootedStatelessSectionStubToAdapter(sectionAdapter);
        addHeadedFootedStatelessSectionStubToAdapter(sectionAdapter);

        Section section = addSectionStubToAdapter(sectionAdapter);
        addHeadedSectionStubToAdapter(sectionAdapter);
        addFootedSectionStubToAdapter(sectionAdapter);
        addHeadedFootedSectionStubToAdapter(sectionAdapter);

        section.setState(Section.State.LOADING);

        // Section [20-24] - Loading state is type 23
        final int LOADING_TYPE = 23;

        assertThat(sectionAdapter.getItemViewType(44), is(LOADING_TYPE));
    }

    @Test
    public void getItemViewType_withManySections_returnsCorrectValuesForSectionWithFailedState() {
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();

        addStatelessSectionStubToAdapter(sectionAdapter);
        addHeadedStatelessSectionStubToAdapter(sectionAdapter);
        addFootedStatelessSectionStubToAdapter(sectionAdapter);
        addHeadedFootedStatelessSectionStubToAdapter(sectionAdapter);

        Section section = addSectionStubToAdapter(sectionAdapter);
        addHeadedSectionStubToAdapter(sectionAdapter);
        addFootedSectionStubToAdapter(sectionAdapter);
        addHeadedFootedSectionStubToAdapter(sectionAdapter);

        section.setState(Section.State.FAILED);

        // Section [20-24] - Failed state is type 24
        final int LOADING_TYPE = 24;

        assertThat(sectionAdapter.getItemViewType(44), is(LOADING_TYPE));
    }

    @Test
    public void getItemViewType_withManySections_returnsCorrectValuesForHeadedSection() {
        SectionedRecyclerViewAdapter sectionAdapter = getAdapterWith4StatelessSectionsAnd4Sections();

        // HeadedSection [25-29] - Header is type 25
        final int HEADER_TYPE = 25;
        // HeadedSection [25-29] - Items are type 27
        final int ITEM_TYPE = 27;


        assertThat(sectionAdapter.getItemViewType(54), is(HEADER_TYPE));

        assertThat(sectionAdapter.getItemViewType(55), is(ITEM_TYPE));
        assertThat(sectionAdapter.getItemViewType(64), is(ITEM_TYPE));
    }

    @Test
    public void getItemViewType_withManySections_returnsCorrectValuesForFootedSection() {
        SectionedRecyclerViewAdapter sectionAdapter = getAdapterWith4StatelessSectionsAnd4Sections();

        // FootedSection [30-34] - Items are type 32
        final int ITEM_TYPE = 32;
        // FootedSection [30-34] - Footer is type 31
        final int FOOTER_TYPE = 31;

        assertThat(sectionAdapter.getItemViewType(65), is(ITEM_TYPE));
        assertThat(sectionAdapter.getItemViewType(74), is(ITEM_TYPE));
        assertThat(sectionAdapter.getItemViewType(75), is(FOOTER_TYPE));
    }

    @Test
    public void getItemViewType_withManySections_returnsCorrectValuesForHeadedFootedSection() {
        SectionedRecyclerViewAdapter sectionAdapter = getAdapterWith4StatelessSectionsAnd4Sections();

        // HeadedFootedSection [35-39] - Header is type 35
        final int HEADER_TYPE = 35;
        // HeadedFootedSection [35-39] - Items are type 37
        final int ITEM_TYPE = 37;
        // HeadedFootedSection [35-39] - Footer is type 36
        final int FOOTER_TYPE = 36;

        assertThat(sectionAdapter.getItemViewType(76), is(HEADER_TYPE));
        assertThat(sectionAdapter.getItemViewType(77), is(ITEM_TYPE));
        assertThat(sectionAdapter.getItemViewType(86), is(ITEM_TYPE));
        assertThat(sectionAdapter.getItemViewType(87), is(FOOTER_TYPE));
    }

    @Test
    public void onCreateViewHolder_emptyAdapter_returnsNull() {
        assertNull(new SectionedRecyclerViewAdapter().onCreateViewHolder(null, 0));
    }

    @Test(expected = NullPointerException.class)
    public void onCreateViewHolder_withStatelessSection_throwsExceptionForHeader() {
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();
        addStatelessSectionStubToAdapter(sectionAdapter);

        sectionAdapter.onCreateViewHolder(null, SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER);
    }

    @Test(expected = NullPointerException.class)
    public void onCreateViewHolder_withStatelessSection_throwsExceptionForFooter() {
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();
        addStatelessSectionStubToAdapter(sectionAdapter);

        sectionAdapter.onCreateViewHolder(null, SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER);
    }

    @Test(expected = NullPointerException.class)
    public void onCreateViewHolder_withStatelessSection_throwsExceptionForLoading() {
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();
        addStatelessSectionStubToAdapter(sectionAdapter);

        sectionAdapter.onCreateViewHolder(null, SectionedRecyclerViewAdapter.VIEW_TYPE_LOADING);
    }

    @Test(expected = NullPointerException.class)
    public void onCreateViewHolder_withStatelessSection_throwsExceptionForFailed() {
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();
        addStatelessSectionStubToAdapter(sectionAdapter);

        sectionAdapter.onCreateViewHolder(null, SectionedRecyclerViewAdapter.VIEW_TYPE_FAILED);
    }

    @Test
    public void removeAllSections_withManySections_succeeds() {
        SectionedRecyclerViewAdapter sectionAdapter = getAdapterWith4StatelessSectionsAnd4Sections();

        sectionAdapter.removeAllSections();

        assertThat(sectionAdapter.getItemCount(), is(0));
        assertTrue(sectionAdapter.getSectionsMap().isEmpty());
    }

    @Test
    public void testSortOrder(){
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();

        StatelessSectionStub sectionStub1 = new StatelessSectionStub(ITEMS_QTY);
        sectionStub1.setSortOrder(1);

        StatelessSectionStub sectionStub2 = new StatelessSectionStub(ITEMS_QTY);
        sectionStub2.setSortOrder(0);

        sectionAdapter.addSection(sectionStub1);
        sectionAdapter.addSection(sectionStub2);


        orderByValue(sectionAdapter.getSectionsMap(), new Comparator<Section>() {
            @Override
            public int compare(Section lhs, Section rhs) {
                return lhs.getSortOrder() - rhs.getSortOrder();
            }
        });

        assertThat(sectionAdapter.getSectionForPosition(0).getSortOrder(), is(0));
        assertThat(sectionAdapter.getSectionForPosition(ITEMS_QTY + 1).getSortOrder(), is(1));



    }




    private SectionedRecyclerViewAdapter getAdapterWith4StatelessSectionsAnd4Sections() {
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();

        addStatelessSectionStubToAdapter(sectionAdapter);
        addHeadedStatelessSectionStubToAdapter(sectionAdapter);
        addFootedStatelessSectionStubToAdapter(sectionAdapter);
        addHeadedFootedStatelessSectionStubToAdapter(sectionAdapter);

        addSectionStubToAdapter(sectionAdapter);
        addHeadedSectionStubToAdapter(sectionAdapter);
        addFootedSectionStubToAdapter(sectionAdapter);
        addHeadedFootedSectionStubToAdapter(sectionAdapter);

        return sectionAdapter;
    }

    private StatelessSectionStub addStatelessSectionStubToAdapter(SectionedRecyclerViewAdapter sectionAdapter) {
        StatelessSectionStub sectionStub = new StatelessSectionStub(ITEMS_QTY);
        sectionAdapter.addSection(sectionStub);
        return sectionStub;
    }

    private SectionStub addSectionStubToAdapter(SectionedRecyclerViewAdapter sectionAdapter) {
        SectionStub sectionStub = new SectionStub(ITEMS_QTY);
        sectionAdapter.addSection(sectionStub);
        return sectionStub;
    }

    private HeadedStatelessSectionStub addHeadedStatelessSectionStubToAdapter(SectionedRecyclerViewAdapter sectionAdapter) {
        HeadedStatelessSectionStub headedSectionSub = new HeadedStatelessSectionStub(ITEMS_QTY);
        sectionAdapter.addSection(headedSectionSub);
        return headedSectionSub;
    }

    private HeadedSectionStub addHeadedSectionStubToAdapter(SectionedRecyclerViewAdapter sectionAdapter) {
        HeadedSectionStub headedSectionSub = new HeadedSectionStub(ITEMS_QTY);
        sectionAdapter.addSection(headedSectionSub);
        return headedSectionSub;
    }

    private FootedStatelessSectionStub addFootedStatelessSectionStubToAdapter(SectionedRecyclerViewAdapter sectionAdapter) {
        FootedStatelessSectionStub footedSectionSub = new FootedStatelessSectionStub(ITEMS_QTY);
        sectionAdapter.addSection(footedSectionSub);
        return footedSectionSub;
    }

    private FootedSectionStub addFootedSectionStubToAdapter(SectionedRecyclerViewAdapter sectionAdapter) {
        FootedSectionStub footedSectionSub = new FootedSectionStub(ITEMS_QTY);
        sectionAdapter.addSection(footedSectionSub);
        return footedSectionSub;
    }

    private HeadedFootedStatelessSectionStub addHeadedFootedStatelessSectionStubToAdapter(SectionedRecyclerViewAdapter sectionAdapter) {
        HeadedFootedStatelessSectionStub headedFootedSectionSub = new HeadedFootedStatelessSectionStub(ITEMS_QTY);
        sectionAdapter.addSection(headedFootedSectionSub);
        return headedFootedSectionSub;
    }

    private HeadedFootedSectionStub addHeadedFootedSectionStubToAdapter(SectionedRecyclerViewAdapter sectionAdapter) {
        HeadedFootedSectionStub headedFootedSectionSub = new HeadedFootedSectionStub(ITEMS_QTY);
        sectionAdapter.addSection(headedFootedSectionSub);
        return headedFootedSectionSub;
    }

    public static <K, V> void orderByValue(
            LinkedHashMap<K, V> m, final Comparator<? super V> c) {
        List<Map.Entry<K, V>> entries = new ArrayList<>(m.entrySet());

        Collections.sort(entries, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> lhs, Map.Entry<K, V> rhs) {
                return c.compare(lhs.getValue(), rhs.getValue());
            }
        });

        m.clear();
        for(Map.Entry<K, V> e : entries) {
            m.put(e.getKey(), e.getValue());
        }
    }
}
