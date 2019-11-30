package io.github.luizgrp.sectionedrecyclerviewadapter.demo.info;

import java.security.InvalidParameterException;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class SectionItemInfoFactory {

    public static SectionItemInfo create(
            final int itemAdapterPosition,
            final SectionedRecyclerViewAdapter sectionedAdapter
    ) {
        final int viewType = sectionedAdapter.getSectionItemViewType(itemAdapterPosition);
        final String positionInSection;
        if (viewType == 2) {
            positionInSection = String.valueOf(sectionedAdapter.getPositionInSection(itemAdapterPosition));
        } else {
            positionInSection = "N/A";
        }
        return new SectionItemInfo(
                itemAdapterPosition,
                positionInSection,
                String.format("%s (%s)", viewType, getViewTypeName(viewType))
        );
    }

    private static String getViewTypeName(int viewType) {
        switch (viewType) {
            case 0:
                return "header";
            case 1:
                return "footer";
            case 2:
                return "item";
            case 3:
                return "loading";
            case 4:
                return "failed";
            case 5:
                return "empty";
            default:
                throw new InvalidParameterException();
        }
    }
}
