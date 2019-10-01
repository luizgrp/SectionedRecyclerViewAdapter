package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.R;

final class ItemViewHolder extends RecyclerView.ViewHolder {

    final View rootView;
    final ImageView imgItem;
    final TextView tvHeader;
    final TextView tvDate;

    ItemViewHolder(@NonNull final View view) {
        super(view);

        rootView = view;
        imgItem = view.findViewById(R.id.imgItem);
        tvHeader = view.findViewById(R.id.tvHeader);
        tvDate = view.findViewById(R.id.tvDate);
    }
}