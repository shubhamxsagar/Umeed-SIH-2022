package com.example.umeed.Map;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MapDataModel {

    @SerializedName("data")
    ArrayList<MapModel> mData;

    public MapDataModel(ArrayList<MapModel> mData) {
        this.mData = mData;
    }

    public ArrayList<MapModel> getmData() {
        return mData;
    }

    public void setmData(ArrayList<MapModel> mData) {
        this.mData = mData;
    }
}
