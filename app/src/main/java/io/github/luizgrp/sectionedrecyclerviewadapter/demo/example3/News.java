package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example3;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

final class News {

    final String header;
    final String date;
    @DrawableRes
    final int icon;

    News(@NonNull final String header, @NonNull final String date, @DrawableRes final int icon) {
        this.header = header;
        this.date = date;
        this.icon = icon;
    }
}
