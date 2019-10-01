package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example3;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.R;

final class FailedViewHolder extends RecyclerView.ViewHolder {

    final View rootView;

    FailedViewHolder(@NonNull final View itemView) {
        super(itemView);

        rootView = itemView.findViewById(R.id.rootView);
    }
}
