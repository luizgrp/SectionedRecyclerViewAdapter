package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example9;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.R;

class WatchItemViewHolder extends RecyclerView.ViewHolder {

    final View rootView;
    final TextView tvCode;
    final TextView tvPrice;
    final TextView tvDelta;

    WatchItemViewHolder(@NonNull View view) {
        super(view);

        rootView = view;
        tvCode = view.findViewById(R.id.tvCode);
        tvPrice = view.findViewById(R.id.tvPrice);
        tvDelta = view.findViewById(R.id.tvDelta);
    }
}
