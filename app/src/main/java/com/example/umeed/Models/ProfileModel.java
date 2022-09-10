package com.example.umeed.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileModel {

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("error")
    @Expose
    boolean error;

    @SerializedName("data")
    @Expose
    ProfileDataModel data;

    public ProfileModel(String status, boolean error, ProfileDataModel data) {
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

    public ProfileDataModel getData() {
        return data;
    }

    public void setData(ProfileDataModel data) {
        this.data = data;
    }
}
