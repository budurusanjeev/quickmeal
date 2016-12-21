package com.yitsol.quickmeal.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class CustomPackageCategoryDO implements Parcelable {
    public static final Creator<CustomPackageCategoryDO> CREATOR = new Creator<CustomPackageCategoryDO>() {
        @Override
        public CustomPackageCategoryDO createFromParcel(Parcel in) {
            return new CustomPackageCategoryDO(in);
        }

        @Override
        public CustomPackageCategoryDO[] newArray(int size) {
            return new CustomPackageCategoryDO[size];
        }
    };
    public String status;
    public String ItemName = "";
    public double ItemPrice;
    public String sbItemCategoryId = "";
    public String Message = "";

    public CustomPackageCategoryDO(String ItemName, double ItemPrice, String sbItemCategoryId) {
        this.ItemName = ItemName;
        this.ItemPrice = ItemPrice;
        this.sbItemCategoryId = sbItemCategoryId;
    }

    protected CustomPackageCategoryDO(Parcel in) {
        status = in.readString();
        ItemName = in.readString();
        ItemPrice = in.readDouble();
        sbItemCategoryId = in.readString();
        Message = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(ItemName);
        dest.writeDouble(ItemPrice);
        dest.writeString(sbItemCategoryId);
        dest.writeString(Message);
    }
}

