package com.example.umeed.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatusErrorModel {


    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("error")
    @Expose
    boolean error;

//    @SerializedName("data")
//    @Expose
//    ArrayList<RegisterDoner> data;

    public StatusErrorModel(String status, boolean error) {
        this.status = status;
        this.error = error;
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

}
