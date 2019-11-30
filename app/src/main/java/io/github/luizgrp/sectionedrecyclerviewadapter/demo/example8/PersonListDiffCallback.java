package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example8;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

class PersonListDiffCallback extends DiffUtil.Callback {

    private final List<Person> oldList;
    private final List<Person> newList;

    PersonListDiffCallback(final List<Person> oldList, final List<Person> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList != null ? oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newList != null ? newList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(final int oldItemPosition, final int newItemPosition) {
        return newList.get(newItemPosition).id.equals(oldList.get(oldItemPosition).id);
    }

    @Override
    public boolean areContentsTheSame(final int oldItemPosition, final int newItemPosition) {
        return newList.get(newItemPosition).equals(oldList.get(oldItemPosition));
    }
}
