package com.example.umeed.Models;

import com.example.umeed.Models.TablayoutModels.HospitalTreatmentDataModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookingModel {

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("error")
    @Expose
    boolean error;

    @SerializedName("data")
    @Expose
    ArrayList<BookingDataModel> data;

    public BookingModel(String status, boolean error, ArrayList<BookingDataModel> data) {
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

    public ArrayList<BookingDataModel> getData() {
        return data;
    }

    public void setData(ArrayList<BookingDataModel> data) {
        this.data = data;
    }
}
