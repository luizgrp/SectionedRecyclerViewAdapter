package io.github.luizgrp.sectionedrecyclerviewadapter.demo.info;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

public class SectionInfo implements Parcelable {

    private final int sectionPosition;
    private final boolean headed;
    @Nullable
    private final Integer sectionHeaderPosition;
    private final boolean footed;
    @Nullable
    private final Integer sectionFooterPosition;

    SectionInfo(
            final int sectionPosition,
            final boolean headed,
            @Nullable final Integer sectionHeaderPosition,
            final boolean footed,
            @Nullable final Integer sectionFooterPosition
    ) {
        this.sectionPosition = sectionPosition;
        this.headed = headed;
        this.sectionHeaderPosition = sectionHeaderPosition;
        this.footed = footed;
        this.sectionFooterPosition = sectionFooterPosition;
    }

    private SectionInfo(Parcel in) {
        sectionPosition = in.readInt();
        headed = in.readByte() != 0;
        if (in.readByte() == 0) {
            sectionHeaderPosition = null;
        } else {
            sectionHeaderPosition = in.readInt();
        }
        footed = in.readByte() != 0;
        if (in.readByte() == 0) {
            sectionFooterPosition = null;
        } else {
            sectionFooterPosition = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(sectionPosition);
        dest.writeByte((byte) (headed ? 1 : 0));
        if (sectionHeaderPosition == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(sectionHeaderPosition);
        }
        dest.writeByte((byte) (footed ? 1 : 0));
        if (sectionFooterPosition == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(sectionFooterPosition);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SectionInfo> CREATOR = new Creator<SectionInfo>() {
        @Override
        public SectionInfo createFromParcel(Parcel in) {
            return new SectionInfo(in);
        }

        @Override
        public SectionInfo[] newArray(int size) {
            return new SectionInfo[size];
        }
    };

    int getSectionPosition() {
        return sectionPosition;
    }

    boolean isHeaded() {
        return headed;
    }

    @Nullable
    Integer getSectionHeaderPosition() {
        return sectionHeaderPosition;
    }

    boolean isFooted() {
        return footed;
    }

    @Nullable
    Integer getSectionFooterPosition() {
        return sectionFooterPosition;
    }

}
