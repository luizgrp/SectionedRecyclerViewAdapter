package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example9;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

class WatchItem {
    final String code;
    String price;
    String delta;
    @ColorInt
    int deltaColor;

    WatchItem(@NonNull final String code, @NonNull final String price, @NonNull final String delta,
              final @ColorInt int deltaColor) {
        this.code = code;
        this.price = price;
        this.delta = delta;
        this.deltaColor = deltaColor;
    }
}
