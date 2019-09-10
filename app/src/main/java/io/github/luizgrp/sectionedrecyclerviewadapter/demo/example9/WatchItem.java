package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example9;

import androidx.annotation.ColorInt;

class WatchItem {
    final String code;
    String price;
    String delta;
    @ColorInt
    int deltaColor;

    WatchItem(final String code, final String price, final String delta,
              final @ColorInt int deltaColor) {
        this.code = code;
        this.price = price;
        this.delta = delta;
        this.deltaColor = deltaColor;
    }
}
