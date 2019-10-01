package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example6;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;

class LoadMoviesUseCase {

    List<Movie> execute(@NonNull final Context context, @ArrayRes final int stringArray) {
        final List<String> arrayList = new ArrayList<>(Arrays.asList(
                context.getResources().getStringArray(stringArray)));

        final List<Movie> movieList = new ArrayList<>();

        for (String str : arrayList) {
            String[] array = str.split("\\|");
            movieList.add(new Movie(array[0], array[1]));
        }

        return movieList;
    }
}
