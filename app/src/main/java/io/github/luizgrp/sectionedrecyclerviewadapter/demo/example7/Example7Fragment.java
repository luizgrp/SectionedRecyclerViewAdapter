package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example7;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.R;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.info.SectionInfoFactory;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.info.SectionItemInfoDialog;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.info.SectionItemInfoFactory;

public class Example7Fragment extends Fragment implements SearchView.OnQueryTextListener,
        ContactsSection.ClickListener {

    private static final String DIALOG_TAG = "SectionItemInfoDialogTag";

    private SectionedRecyclerViewAdapter sectionedAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_ex7, container, false);

        sectionedAdapter = new SectionedRecyclerViewAdapter();

        final Map<String, List<Contact>> contactsMap = new LoadContactsUseCase().execute(requireContext());
        for (final Map.Entry<String, List<Contact>> entry : contactsMap.entrySet()) {
            if (entry.getValue().size() > 0) {
                sectionedAdapter.addSection(new ContactsSection(entry.getKey(), entry.getValue(), this));
            }
        }

        final RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(sectionedAdapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull final Menu menu, @NonNull final MenuInflater inflater) {
        inflater.inflate(R.menu.menu_ex7, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextChange(final String query) {

        for (Section section : sectionedAdapter.getCopyOfSectionsMap().values()) {
            if (section instanceof FilterableSection) {
                ((FilterableSection) section).filter(query);
            }
        }
        sectionedAdapter.notifyDataSetChanged();

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(final String query) {
        return false;
    }

    @Override
    public void onItemRootViewClicked(@NonNull final ContactsSection section, final int itemAdapterPosition) {
        final SectionItemInfoDialog dialog = SectionItemInfoDialog.getInstance(
                SectionItemInfoFactory.create(itemAdapterPosition, sectionedAdapter),
                SectionInfoFactory.create(section, sectionedAdapter.getAdapterForSection(section))
        );
        dialog.show(getParentFragmentManager(), DIALOG_TAG);
    }
}
