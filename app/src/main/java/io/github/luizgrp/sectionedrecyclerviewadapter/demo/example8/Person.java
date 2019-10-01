package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example8;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

class Person {
    final String name;
    final String id;
    @DrawableRes
    final int profileImage;

    Person(@NonNull final String name, @NonNull final String id, @DrawableRes final int profileImage) {
        this.name = name;
        this.id = id;
        this.profileImage = profileImage;
    }
}
