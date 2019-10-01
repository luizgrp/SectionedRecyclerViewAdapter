package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example7;

import android.text.TextUtils;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.R;

class ContactsSection extends Section implements FilterableSection {

    private final String title;
    private final List<Contact> list;
    private final ClickListener clickListener;
    private final List<Contact> filteredList;

    ContactsSection(final String title, final List<Contact> list, final ClickListener clickListener) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.section_ex7_item)
                .headerResourceId(R.layout.section_ex7_header)
                .build());

        this.title = title;
        this.list = list;
        this.clickListener = clickListener;
        this.filteredList = new ArrayList<>(list);
    }

    @Override
    public int getContentItemsTotal() {
        return filteredList.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(final View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ItemViewHolder itemHolder = (ItemViewHolder) holder;

        final Contact contact = filteredList.get(position);

        itemHolder.tvItem.setText(contact.name);
        itemHolder.imgItem.setImageResource(contact.profileImage);

        itemHolder.rootView.setOnClickListener(v ->
                clickListener.onItemRootViewClicked(title, itemHolder.getAdapterPosition())
        );
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(final View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(final RecyclerView.ViewHolder holder) {
        final HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

        headerHolder.tvTitle.setText(title);
    }

    @Override
    public void filter(@NonNull final String query) {
        if (TextUtils.isEmpty(query)) {
            filteredList.clear();
            filteredList.addAll(list);
            this.setVisible(true);
        } else {
            filteredList.clear();
            for (final Contact contact : list) {
                if (contact.name.toLowerCase(Locale.getDefault())
                        .contains(query.toLowerCase(Locale.getDefault()))) {
                    filteredList.add(contact);
                }
            }

            this.setVisible(!filteredList.isEmpty());
        }
    }

    interface ClickListener {

        void onItemRootViewClicked(@NonNull final String sectionTitle, final int itemAdapterPosition);
    }
}
