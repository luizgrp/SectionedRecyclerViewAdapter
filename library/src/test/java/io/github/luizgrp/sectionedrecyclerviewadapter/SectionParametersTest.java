package io.github.luizgrp.sectionedrecyclerviewadapter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/*
 * Unit tests for SectionParameters
 */
@SuppressWarnings({"PMD.MethodNamingConventions"})
public class SectionParametersTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void constructor_withValidParameters_throwsNoExceptions() {
        // Given
        final int itemId = 1;
        final int footerId = 3;
        final int loadingId = 5;
        final int emptyId = 6;

        // When
        SectionParameters sectionParameters = SectionParameters.builder()
                .itemResourceId(itemId)
                .headerViewWillBeProvided()
                .footerResourceId(footerId)
                .failedViewWillBeProvided()
                .loadingResourceId(loadingId)
                .emptyResourceId(emptyId)
                .build();

        // Then
        assertThat(sectionParameters.itemResourceId, is(itemId));
        assertThat(sectionParameters.itemViewWillBeProvided, is(false));
        assertThat(sectionParameters.headerResourceId, is(nullValue()));
        assertThat(sectionParameters.headerViewWillBeProvided, is(true));
        assertThat(sectionParameters.footerResourceId, is(footerId));
        assertThat(sectionParameters.footerViewWillBeProvided, is(false));
        assertThat(sectionParameters.failedResourceId, is(nullValue()));
        assertThat(sectionParameters.failedViewWillBeProvided, is(true));
        assertThat(sectionParameters.loadingResourceId, is(loadingId));
        assertThat(sectionParameters.loadingViewWillBeProvided, is(false));
        assertThat(sectionParameters.emptyResourceId, is(emptyId));
        assertThat(sectionParameters.emptyViewWillBeProvided, is(false));
    }

    @Test
    public void constructor_withConflictingItemParameters_throwsException() {
        // Given
        final int itemId = 1;
        final int footerId = 3;
        final int loadingId = 5;
        final int emptyId = 6;

        // Expect exception
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("itemResourceId and itemViewWillBeProvided cannot both be set");

        // When
        SectionParameters.builder()
                .itemResourceId(itemId)
                .itemViewWillBeProvided()
                .headerViewWillBeProvided()
                .footerResourceId(footerId)
                .failedViewWillBeProvided()
                .loadingResourceId(loadingId)
                .emptyResourceId(emptyId)
                .build();
    }

    @Test
    public void constructor_withoutMandatoryItemParameter_throwsException() {
        // Given
        final int footerId = 3;
        final int loadingId = 5;
        final int emptyId = 6;

        // Expect exception
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Either itemResourceId or itemViewWillBeProvided must be set");

        // When
        SectionParameters.builder()
                .headerViewWillBeProvided()
                .footerResourceId(footerId)
                .failedViewWillBeProvided()
                .loadingResourceId(loadingId)
                .emptyResourceId(emptyId)
                .build();
    }

    @Test
    public void constructor_withConflictingFooterParameters_throwsException() {
        // Given
        final int itemId = 1;
        final int footerId = 3;
        final int loadingId = 5;
        final int emptyId = 6;

        // Expect exception
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("footerResourceId and footerViewWillBeProvided cannot both be set");

        // When
        SectionParameters.builder()
                .itemResourceId(itemId)
                .headerViewWillBeProvided()
                .footerResourceId(footerId)
                .footerViewWillBeProvided()
                .failedViewWillBeProvided()
                .loadingResourceId(loadingId)
                .emptyResourceId(emptyId)
                .build();
    }

}
