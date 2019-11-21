package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example8;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.R;

class HeaderViewHolder extends RecyclerView.ViewHolder {

    final TextView tvTitle;
    final Button btnAdd;
    final Button btnClear;
    final Button btnShuffle;
    final Button btnRemove;
    final Button btnStateLoaded;
    final Button btnStateLoading;
    final Button btnStateFailed;
    final Button btnStateEmpty;

    HeaderViewHolder(@NonNull final View view) {
        super(view);

        tvTitle = view.findViewById(R.id.tvTitle);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnClear = view.findViewById(R.id.btnClear);
        btnShuffle = view.findViewById(R.id.btnShuffle);
        btnRemove = view.findViewById(R.id.btnRemove);
        btnStateLoaded = view.findViewById(R.id.btnStateLoaded);
        btnStateLoading = view.findViewById(R.id.btnStateLoading);
        btnStateFailed = view.findViewById(R.id.btnStateFailed);
        btnStateEmpty = view.findViewById(R.id.btnStateEmpty);
    }
}
