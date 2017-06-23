package io.github.luizgrp.sectionedrecyclerviewadapter;

import org.junit.Test;

import io.github.luizgrp.sectionedrecyclerviewadapter.testdoubles.stub.ParametersedSectionStub;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SectionParametersTest {
    @Test
    public void build_constructsCorrectSection() {
        // Given
        SectionParameters sectionParameters = new SectionParameters.Builder(1)
                .failedResourceId(2)
                .footerResourceId(3)
                .headerResourceId(4)
                .loadingResourceId(5)
                .build();

        // When
        Section section = new ParametersedSectionStub(sectionParameters);
        int result = section.getItemResourceId();
        int result2 = section.getFailedResourceId();
        int result3 = section.getFooterResourceId();
        int result4 = section.getHeaderResourceId();
        int result5 = section.getLoadingResourceId();

        // Then
        assertThat(result, is(1));
        assertThat(result2, is(2));
        assertThat(result3, is(3));
        assertThat(result4, is(4));
        assertThat(result5, is(5));
    }
}
