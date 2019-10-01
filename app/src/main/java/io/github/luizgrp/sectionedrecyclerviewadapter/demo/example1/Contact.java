package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example1;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

final class Contact {
    final String name;
    @DrawableRes
    final int profileImage;

    Contact(@NonNull final String name, @DrawableRes final int profileImage) {
        this.name = name;
        this.profileImage = profileImage;
    }
}
