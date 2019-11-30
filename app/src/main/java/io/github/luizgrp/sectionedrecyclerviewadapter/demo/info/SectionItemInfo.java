package io.github.luizgrp.sectionedrecyclerviewadapter.demo.info;

import android.os.Parcel;
import android.os.Parcelable;

public class SectionItemInfo implements Parcelable {

    private final int adapterPosition;
    private final String positionInSection;
    private final String sectionItemViewType;

    SectionItemInfo(final int adapterPosition, final String positionInSection, final String sectionItemViewType) {
        this.adapterPosition = adapterPosition;
        this.positionInSection = positionInSection;
        this.sectionItemViewType = sectionItemViewType;
    }

    public int getAdapterPosition() {
        return adapterPosition;
    }

    String getPositionInSection() {
        return positionInSection;
    }

    String getSectionItemViewType() {
        return sectionItemViewType;
    }

    private SectionItemInfo(Parcel in) {
        adapterPosition = in.readInt();
        positionInSection = in.readString();
        sectionItemViewType = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(adapterPosition);
        dest.writeString(positionInSection);
        dest.writeString(sectionItemViewType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SectionItemInfo> CREATOR = new Creator<SectionItemInfo>() {
        @Override
        public SectionItemInfo createFromParcel(Parcel in) {
            return new SectionItemInfo(in);
        }

        @Override
        public SectionItemInfo[] newArray(int size) {
            return new SectionItemInfo[size];
        }
    };
}
