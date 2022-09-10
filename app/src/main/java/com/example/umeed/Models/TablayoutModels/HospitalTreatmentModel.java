package com.example.umeed.Models.TablayoutModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HospitalTreatmentModel {
    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("error")
    @Expose
    boolean error;

    @SerializedName("data")
    @Expose
    ArrayList<HospitalTreatmentDataModel> data;

    public HospitalTreatmentModel(String status, boolean error, ArrayList<HospitalTreatmentDataModel> data) {
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

    public ArrayList<HospitalTreatmentDataModel> getData() {
        return data;
    }

    public void setData(ArrayList<HospitalTreatmentDataModel> data) {
        this.data = data;
    }
}
