package com.example.umeed.Models.TablayoutModels;

import com.example.umeed.Models.AllListDataModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HospitalAvailabilityModel {

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("error")
    @Expose
    boolean error;

    @SerializedName("data")
    @Expose
    ArrayList<HospitalAvailabilityDataModel> data;

    public HospitalAvailabilityModel(String status, boolean error, ArrayList<HospitalAvailabilityDataModel> data) {
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

    public ArrayList<HospitalAvailabilityDataModel> getData() {
        return data;
    }

    public void setData(ArrayList<HospitalAvailabilityDataModel> data) {
        this.data = data;
    }
}
