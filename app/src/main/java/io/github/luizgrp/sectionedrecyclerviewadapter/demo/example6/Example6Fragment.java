package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example6;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.R;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.info.SectionInfoFactory;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.info.SectionItemInfoDialog;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.info.SectionItemInfoFactory;

public class Example6Fragment extends Fragment implements ExpandableMovieSection.ClickListener {

    private static final String DIALOG_TAG = "SectionItemInfoDialogTag";

    private SectionedRecyclerViewAdapter sectionedAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_ex6, container, false);

        sectionedAdapter = new SectionedRecyclerViewAdapter();

        final LoadMoviesUseCase loadMoviesUseCase = new LoadMoviesUseCase();
        sectionedAdapter.addSection(new ExpandableMovieSection(getString(R.string.top_rated_movies_topic),
                loadMoviesUseCase.execute(requireContext(), R.array.top_rated_movies), this));
        sectionedAdapter.addSection(new ExpandableMovieSection(getString(R.string.most_popular_movies_topic),
                loadMoviesUseCase.execute(requireContext(), R.array.most_popular_movies), this));

        final RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

        final GridLayoutManager glm = new GridLayoutManager(getContext(), 2);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(final int position) {
                if (sectionedAdapter.getSectionItemViewType(position) == SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER) {
                    return 2;
                }
                return 1;
            }
        });
        recyclerView.setLayoutManager(glm);
        recyclerView.setAdapter(sectionedAdapter);

        return view;
    }

    @Override
    public void onHeaderRootViewClicked(@NonNull final ExpandableMovieSection section) {
        final SectionAdapter sectionAdapter = sectionedAdapter.getAdapterForSection(section);

        // store info of current section state before changing its state
        final boolean wasExpanded = section.isExpanded();
        final int previousItemsTotal = section.getContentItemsTotal();

        section.setExpanded(!wasExpanded);
        sectionAdapter.notifyHeaderChanged();

        if (wasExpanded) {
            sectionAdapter.notifyItemRangeRemoved(0, previousItemsTotal);
        } else {
            sectionAdapter.notifyAllItemsInserted();
        }
    }

    @Override
    public void onItemRootViewClicked(@NonNull final ExpandableMovieSection section, final int itemAdapterPosition) {
        final SectionItemInfoDialog dialog = SectionItemInfoDialog.getInstance(
                SectionItemInfoFactory.create(itemAdapterPosition, sectionedAdapter),
                SectionInfoFactory.create(section, sectionedAdapter.getAdapterForSection(section))
        );
        dialog.show(getParentFragmentManager(), DIALOG_TAG);
    }
}
