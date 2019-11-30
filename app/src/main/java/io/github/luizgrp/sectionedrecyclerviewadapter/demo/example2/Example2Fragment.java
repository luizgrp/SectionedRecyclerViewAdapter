package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.R;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.info.SectionInfoFactory;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.info.SectionItemInfoDialog;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.info.SectionItemInfoFactory;

public class Example2Fragment extends Fragment implements NewsSection.ClickListener {

    private static final String DIALOG_TAG = "SectionItemInfoDialogTag";

    private SectionedRecyclerViewAdapter sectionedAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_ex2, container, false);

        sectionedAdapter = new SectionedRecyclerViewAdapter();

        final LoadNewsUseCase loadNewsUseCase = new LoadNewsUseCase();

        sectionedAdapter.addSection(new NewsSection(
                requireContext().getString(R.string.world_topic),
                loadNewsUseCase.execute(requireContext(), R.array.news_world,
                        R.drawable.ic_public_black_48dp),
                this
        ));
        sectionedAdapter.addSection(new NewsSection(
                requireContext().getString(R.string.biz_topic),
                loadNewsUseCase.execute(requireContext(), R.array.news_biz,
                        R.drawable.ic_business_black_48dp),
                this
        ));
        sectionedAdapter.addSection(new NewsSection(
                requireContext().getString(R.string.tech_topic),
                loadNewsUseCase.execute(requireContext(), R.array.news_tech,
                        R.drawable.ic_devices_other_black_48dp),
                this
        ));
        sectionedAdapter.addSection(new NewsSection(
                requireContext().getString(R.string.sports_topic),
                loadNewsUseCase.execute(requireContext(), R.array.news_sports,
                        R.drawable.ic_directions_run_black_48dp),
                this
        ));

        final RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(sectionedAdapter);

        return view;
    }

    @Override
    public void onItemRootViewClicked(@NonNull final NewsSection section, final int itemAdapterPosition) {
        final SectionItemInfoDialog dialog = SectionItemInfoDialog.getInstance(
                SectionItemInfoFactory.create(itemAdapterPosition, sectionedAdapter),
                SectionInfoFactory.create(section, sectionedAdapter.getAdapterForSection(section))
        );
        dialog.show(getParentFragmentManager(), DIALOG_TAG);
    }

    @Override
    public void onFooterRootViewClicked(@NonNull final NewsSection section, final int itemAdapterPosition) {
        final SectionItemInfoDialog dialog = SectionItemInfoDialog.getInstance(
                SectionItemInfoFactory.create(itemAdapterPosition, sectionedAdapter),
                SectionInfoFactory.create(section, sectionedAdapter.getAdapterForSection(section))
        );
        dialog.show(getParentFragmentManager(), DIALOG_TAG);
    }
}
