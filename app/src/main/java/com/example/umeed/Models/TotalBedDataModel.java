package com.example.umeed.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TotalBedDataModel {

    @SerializedName("totalbedcount")
    @Expose
    String totalbedcount;

    public TotalBedDataModel(String totalbedcount) {
        this.totalbedcount = totalbedcount;
    }

    public String getTotalbedcount() {
        return totalbedcount;
    }

    public void setTotalbedcount(String totalbedcount) {
        this.totalbedcount = totalbedcount;
    }
}
