package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example6;

import android.view.View;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.R;

final class ExpandableMovieSection extends Section {

    private final String title;
    private final List<Movie> list;
    private final ClickListener clickListener;

    private boolean expanded = true;

    ExpandableMovieSection(@NonNull final String title, @NonNull final List<Movie> list,
                           @NonNull final ClickListener clickListener) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.section_ex6_item)
                .headerResourceId(R.layout.section_ex6_header)
                .build());

        this.title = title;
        this.list = list;
        this.clickListener = clickListener;
    }

    @Override
    public int getContentItemsTotal() {
        return expanded ? list.size() : 0;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(final View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ItemViewHolder itemHolder = (ItemViewHolder) holder;

        Movie movie = list.get(position);

        itemHolder.tvItem.setText(movie.name);
        itemHolder.tvSubItem.setText(movie.category);

        itemHolder.rootView.setOnClickListener(v ->
                clickListener.onItemRootViewClicked(this, itemHolder.getAdapterPosition())
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
        headerHolder.imgArrow.setImageResource(
                expanded ? R.drawable.ic_keyboard_arrow_up_black_18dp : R.drawable.ic_keyboard_arrow_down_black_18dp
        );

        headerHolder.rootView.setOnClickListener(v ->
                clickListener.onHeaderRootViewClicked(this)
        );
    }

    boolean isExpanded() {
        return expanded;
    }

    void setExpanded(final boolean expanded) {
        this.expanded = expanded;
    }

    interface ClickListener {

        void onHeaderRootViewClicked(@NonNull final ExpandableMovieSection section);

        void onItemRootViewClicked(@NonNull final ExpandableMovieSection section, final int itemAdapterPosition);
    }
}
