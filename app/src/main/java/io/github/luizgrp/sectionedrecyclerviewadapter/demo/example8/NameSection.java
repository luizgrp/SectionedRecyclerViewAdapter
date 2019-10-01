package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example8;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.R;

class NameSection extends Section {

    private final String title;
    private final ClickListener clickListener;
    private final List<Person> list;

    NameSection(@NonNull final String title, @NonNull final ClickListener clickListener) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.section_ex8_item)
                .headerResourceId(R.layout.section_ex8_header)
                .build());

        this.title = title;
        this.clickListener = clickListener;
        this.list = new ArrayList<>();
    }

    @Override
    public int getContentItemsTotal() {
        return list.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(final View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        final ItemViewHolder itemHolder = (ItemViewHolder) holder;

        final Person person = list.get(position);

        itemHolder.tvItem.setText(person.name);
        itemHolder.tvSubItem.setText(person.id);
        itemHolder.imgItem.setImageResource(person.profileImage);

        itemHolder.rootView.setOnClickListener(v ->
                clickListener.onItemRootViewClicked(this, itemHolder.getAdapterPosition()));
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        final HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

        headerHolder.tvTitle.setText(title);

        headerHolder.rootView.setOnClickListener(view ->
                clickListener.onHeaderRootViewClicked(this, headerHolder.getAdapterPosition()));

        headerHolder.btnAdd.setOnClickListener(v -> clickListener.onHeaderAddButtonClicked(this));

        headerHolder.btnClear.setOnClickListener(v -> clickListener.onHeaderClearButtonClicked(this));
    }

    void add(final int position, @NonNull final Person person) {
        list.add(position, person);
    }

    void remove(final int position) {
        list.remove(position);
    }

    void clear() {
        list.clear();
    }

    interface ClickListener {

        void onItemRootViewClicked(@NonNull final NameSection section, final int itemAdapterPosition);

        void onHeaderRootViewClicked(@NonNull final NameSection section, final int itemAdapterPosition);

        void onHeaderAddButtonClicked(@NonNull final NameSection section);

        void onHeaderClearButtonClicked(@NonNull final NameSection section);
    }
}
