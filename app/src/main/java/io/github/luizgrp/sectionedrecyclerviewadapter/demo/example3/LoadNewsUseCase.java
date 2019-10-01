package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example3;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.ArrayRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

final class LoadNewsUseCase {

    final List<News> execute(@NonNull final Context context, @ArrayRes final int newsArrayRes,
                             @DrawableRes final int newsIcon) {

        final List<News> list = new ArrayList<>();

        for (String arrayItem : context.getResources().getStringArray(newsArrayRes)) {
            final String[] item = arrayItem.split("\\|");

            list.add(new News(item[0], item[1], newsIcon));
        }

        return list;
    }
}
