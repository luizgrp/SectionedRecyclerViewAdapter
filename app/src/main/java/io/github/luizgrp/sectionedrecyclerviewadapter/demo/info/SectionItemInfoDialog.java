package io.github.luizgrp.sectionedrecyclerviewadapter.demo.info;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import io.github.luizgrp.sectionedrecyclerviewadapter.demo.R;

public class SectionItemInfoDialog extends DialogFragment {

    private static final String SECTION_ITEM_INFO_KEY = "SectionItemInfoKey";
    private static final String SECTION_INFO_KEY = "SectionInfoKey";

    public SectionItemInfoDialog() {
        // this should be instantiated via factory method
    }

    private SectionItemInfo getSectionInfoItem() {
        final Bundle bundle = requireArguments();
        if (!bundle.containsKey(SECTION_ITEM_INFO_KEY)) {
            throw new IllegalStateException("Fragment " + this + " does not have a SectionItemInfo argument.");
        } else {
            return bundle.getParcelable(SECTION_ITEM_INFO_KEY);
        }
    }

    private SectionInfo getSectionInfo() {
        final Bundle bundle = requireArguments();
        if (!bundle.containsKey(SECTION_INFO_KEY)) {
            throw new IllegalStateException("Fragment " + this + " does not have a SectionInfo argument.");
        } else {
            return bundle.getParcelable(SECTION_INFO_KEY);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable final Bundle savedInstanceState) {
        final SectionItemInfo sectionItemInfo = getSectionInfoItem();
        final SectionInfo sectionInfo = getSectionInfo();

        final StringBuilder sb = new StringBuilder();
        final String lineBreaker = "\n";
        final String space = " ";
        sb.append(lineBreaker);
        sb.append(getString(R.string.item_info_title)).append(lineBreaker).append(lineBreaker);
        sb.append(getString(R.string.pos_in_section)).append(space).append(sectionItemInfo.getPositionInSection())
                .append(lineBreaker);
        sb.append(getString(R.string.pos_in_adapter)).append(space).append(sectionItemInfo.getAdapterPosition())
                .append(lineBreaker);
        sb.append(getString(R.string.view_type)).append(space).append(sectionItemInfo.getSectionItemViewType())
                .append(lineBreaker);
        sb.append(lineBreaker).append(lineBreaker);

        sb.append(getString(R.string.section_info_title)).append(lineBreaker).append(lineBreaker);
        sb.append(getString(R.string.pos_in_adapter)).append(space).append(sectionInfo.getSectionPosition())
                .append(lineBreaker);
        sb.append(getString(R.string.has_header)).append(space).append(sectionInfo.isHeaded()).append(lineBreaker);
        if (sectionInfo.isHeaded()) {
            sb.append(getString(R.string.header_pos)).append(space).append(sectionInfo.getSectionHeaderPosition())
                    .append(lineBreaker);
        }
        sb.append(getString(R.string.has_footer)).append(space).append(sectionInfo.isFooted()).append(lineBreaker);
        if (sectionInfo.isFooted()) {
            sb.append(getString(R.string.footer_pos)).append(space).append(sectionInfo.getSectionFooterPosition())
                    .append(lineBreaker);
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.info)
                .setMessage(sb.toString())
                .setPositiveButton(R.string.close, null);

        return builder.create();
    }

    public static SectionItemInfoDialog getInstance(@NonNull final SectionItemInfo sectionItemInfo,
                                                    final SectionInfo sectionInfo) {
        final SectionItemInfoDialog sectionItemInfoDialog = new SectionItemInfoDialog();
        final Bundle args = new Bundle();

        args.putParcelable(SECTION_ITEM_INFO_KEY, sectionItemInfo);
        args.putParcelable(SECTION_INFO_KEY, sectionInfo);

        sectionItemInfoDialog.setArguments(args);

        return sectionItemInfoDialog;
    }
}
