package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example9;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.R;

class PortfolioItemViewHolder extends RecyclerView.ViewHolder {

    final TextView tvCode;
    final TextView tvHoldingQty;
    final TextView tvHoldingPrice;
    final TextView tvPrice;
    final TextView tvDelta;

    PortfolioItemViewHolder(@NonNull View view) {
        super(view);

        tvCode = view.findViewById(R.id.tvCode);
        tvHoldingQty = view.findViewById(R.id.tvHoldingQty);
        tvHoldingPrice = view.findViewById(R.id.tvHoldingPrice);
        tvPrice = view.findViewById(R.id.tvPrice);
        tvDelta = view.findViewById(R.id.tvDelta);
    }
}
