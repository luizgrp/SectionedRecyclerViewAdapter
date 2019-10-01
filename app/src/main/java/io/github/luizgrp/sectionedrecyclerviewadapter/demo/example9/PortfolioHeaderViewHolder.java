package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example9;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.R;

class PortfolioHeaderViewHolder extends RecyclerView.ViewHolder {

    final TextView tvHoldingsPriceTotal;

    PortfolioHeaderViewHolder(@NonNull final View view) {
        super(view);

        tvHoldingsPriceTotal = view.findViewById(R.id.tvHoldingsPriceTotal);
    }
}
