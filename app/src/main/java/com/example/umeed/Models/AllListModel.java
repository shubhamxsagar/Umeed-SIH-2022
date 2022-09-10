package com.example.umeed.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllListModel {

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("error")
    @Expose
    boolean error;

    @SerializedName("data")
    @Expose
    ArrayList<AllListDataModel> data;

    public AllListModel(String status, boolean error, ArrayList<AllListDataModel> data) {
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

    public ArrayList<AllListDataModel> getData() {
        return data;
    }

    public void setData(ArrayList<AllListDataModel> data) {
        this.data = data;
    }
}
