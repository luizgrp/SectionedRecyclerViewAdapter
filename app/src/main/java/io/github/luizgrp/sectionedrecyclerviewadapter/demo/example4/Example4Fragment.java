package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.R;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.info.SectionInfoFactory;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.info.SectionItemInfoDialog;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.info.SectionItemInfoFactory;

public class Example4Fragment extends Fragment implements ExpandableContactsSection.ClickListener {

    private static final String DIALOG_TAG = "SectionItemInfoDialogTag";

    private SectionedRecyclerViewAdapter sectionedAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_ex4, container, false);

        sectionedAdapter = new SectionedRecyclerViewAdapter();

        final Map<String, List<Contact>> contactsMap = new LoadContactsUseCase().execute(requireContext());
        for (final Map.Entry<String, List<Contact>> entry : contactsMap.entrySet()) {
            if (entry.getValue().size() > 0) {
                sectionedAdapter.addSection(new ExpandableContactsSection(entry.getKey(), entry.getValue(), this));
            }
        }

        final RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(sectionedAdapter);

        return view;
    }

    @Override
    public void onHeaderRootViewClicked(@NonNull final ExpandableContactsSection section) {
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
    public void onItemRootViewClicked(@NonNull final ExpandableContactsSection section, final int itemAdapterPosition) {
        final SectionItemInfoDialog dialog = SectionItemInfoDialog.getInstance(
                SectionItemInfoFactory.create(itemAdapterPosition, sectionedAdapter),
                SectionInfoFactory.create(section, sectionedAdapter.getAdapterForSection(section))
        );
        dialog.show(getParentFragmentManager(), DIALOG_TAG);
    }
}
