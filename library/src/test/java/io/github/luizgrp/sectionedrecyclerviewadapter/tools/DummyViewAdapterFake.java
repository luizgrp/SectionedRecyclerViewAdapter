package io.github.luizgrp.sectionedrecyclerviewadapter.tools;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Fake implementation of the adapter that returns a dummy view when inflate method is called.
 */
@SuppressWarnings("PMD.AtLeastOneConstructor")
public class DummyViewAdapterFake extends SectionedRecyclerViewAdapter {

    @Override
    public View inflate(@LayoutRes final int layoutResourceId, final ViewGroup parent) {
        final View view = mock(View.class);
        when(view.getTag()).thenReturn(layoutResourceId);
        return view;
    }
}
