package com.example.umeed.Models.TablayoutModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BloodAvailabilityModel {

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("error")
    @Expose
    boolean error;

    @SerializedName("data")
    @Expose
    ArrayList<BloodAvailabilityDataModel> data;

    public BloodAvailabilityModel(String status, boolean error, ArrayList<BloodAvailabilityDataModel> data) {
        this.status = status;
        this.error = error;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ArrayList<BloodAvailabilityDataModel> getData() {
        return data;
    }

    public void setData(ArrayList<BloodAvailabilityDataModel> data) {
        this.data = data;
    }
}
