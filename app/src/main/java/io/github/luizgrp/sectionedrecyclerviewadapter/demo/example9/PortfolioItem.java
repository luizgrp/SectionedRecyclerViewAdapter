package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example9;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

class PortfolioItem {
    final String code;
    String price;
    String delta;
    @ColorInt
    int deltaColor;
    final String holdingQty;
    String holdingPrice;

    PortfolioItem(@NonNull final String code, @NonNull final String price, @NonNull final String delta,
                  final @ColorInt int deltaColor, @NonNull final String holdingQty,
                  @NonNull final String holdingPrice) {
        this.code = code;
        this.price = price;
        this.delta = delta;
        this.deltaColor = deltaColor;
        this.holdingQty = holdingQty;
        this.holdingPrice = holdingPrice;
    }
}
