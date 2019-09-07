package io.github.luizgrp.sectionedrecyclerviewadapter.demo;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class Example7Fragment extends Fragment implements SearchView.OnQueryTextListener {

    private SectionedRecyclerViewAdapter sectionedAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ex7, container, false);

        sectionedAdapter = new SectionedRecyclerViewAdapter();

        for (char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
            List<String> contacts = getContactsWithLetter(alphabet);

            if (contacts.size() > 0) {
                ContactsSection contactsSection = new ContactsSection(String.valueOf(alphabet), contacts);
                sectionedAdapter.addSection(contactsSection);
            }
        }

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(sectionedAdapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_ex7, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextChange(String query) {

        for (Section section : sectionedAdapter.getCopyOfSectionsMap().values()) {
            if (section instanceof FilterableSection) {
                ((FilterableSection) section).filter(query);
            }
        }
        sectionedAdapter.notifyDataSetChanged();

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private List<String> getContactsWithLetter(char letter) {
        List<String> contacts = new ArrayList<>();

        for (String contact : getResources().getStringArray(R.array.names)) {
            if (contact.charAt(0) == letter) {
                contacts.add(contact);
            }
        }

        return contacts;
    }

    private class ContactsSection extends Section implements FilterableSection {

        final String title;
        final List<String> list;
        List<String> filteredList;

        ContactsSection(String title, List<String> list) {
            super(SectionParameters.builder()
                    .itemResourceId(R.layout.section_ex7_item)
                    .headerResourceId(R.layout.section_ex7_header)
                    .build());

            this.title = title;
            this.list = list;
            this.filteredList = new ArrayList<>(list);
        }

        @Override
        public int getContentItemsTotal() {
            return filteredList.size();
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
            final ItemViewHolder itemHolder = (ItemViewHolder) holder;

            String name = filteredList.get(position);

            itemHolder.tvItem.setText(name);
            itemHolder.imgItem.setImageResource(name.hashCode() % 2 == 0 ? R.drawable.ic_face_black_48dp : R.drawable.ic_tag_faces_black_48dp);

            itemHolder.rootView.setOnClickListener(v ->
                    Toast.makeText(
                            getContext(),
                            String.format(
                                    "Clicked on position #%s of Section %s",
                                    sectionedAdapter.getPositionInSection(itemHolder.getAdapterPosition()),
                                    title
                            ),
                            Toast.LENGTH_SHORT
                    ).show()
            );
        }

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

            headerHolder.tvTitle.setText(title);
        }

        @Override
        public void filter(String query) {
            if (TextUtils.isEmpty(query)) {
                filteredList = new ArrayList<>(list);
                this.setVisible(true);
            } else {
                filteredList.clear();
                for (String value : list) {
                    if (value.toLowerCase(Locale.getDefault())
                            .contains(query.toLowerCase(Locale.getDefault()))) {
                        filteredList.add(value);
                    }
                }

                this.setVisible(!filteredList.isEmpty());
            }
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;

        HeaderViewHolder(View view) {
            super(view);

            tvTitle = view.findViewById(R.id.tvTitle);
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final ImageView imgItem;
        private final TextView tvItem;

        ItemViewHolder(View view) {
            super(view);

            rootView = view;
            imgItem = view.findViewById(R.id.imgItem);
            tvItem = view.findViewById(R.id.tvItem);
        }
    }

    interface FilterableSection {
        void filter(String query);
    }
}
