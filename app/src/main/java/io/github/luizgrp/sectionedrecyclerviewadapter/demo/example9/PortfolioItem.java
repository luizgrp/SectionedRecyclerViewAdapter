package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example9;

import androidx.annotation.ColorInt;

class PortfolioItem {
    final String code;
    String price;
    String delta;
    @ColorInt
    int deltaColor;
    final String holdingQty;
    String holdingPrice;

    PortfolioItem(final String code, final String price, final String delta,
                  final @ColorInt int deltaColor, final String holdingQty,
                  final String holdingPrice) {
        this.code = code;
        this.price = price;
        this.delta = delta;
        this.deltaColor = deltaColor;
        this.holdingQty = holdingQty;
        this.holdingPrice = holdingPrice;
    }
}
