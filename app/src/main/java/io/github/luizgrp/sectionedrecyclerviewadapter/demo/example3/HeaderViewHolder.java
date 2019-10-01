package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example3;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.R;

final class HeaderViewHolder extends RecyclerView.ViewHolder {

    final TextView tvTitle;

    HeaderViewHolder(@NonNull final View itemView) {
        super(itemView);

        tvTitle = itemView.findViewById(R.id.tvTitle);
    }
}