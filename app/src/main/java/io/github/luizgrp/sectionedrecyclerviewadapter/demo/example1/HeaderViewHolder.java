package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example1;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.R;

final class HeaderViewHolder extends RecyclerView.ViewHolder {

    final TextView tvTitle;

    HeaderViewHolder(@NonNull View view) {
        super(view);

        tvTitle = view.findViewById(R.id.tvTitle);
    }
}
