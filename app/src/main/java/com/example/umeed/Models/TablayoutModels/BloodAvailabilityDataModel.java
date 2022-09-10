package com.example.umeed.Models.TablayoutModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BloodAvailabilityDataModel {

    @SerializedName("bloodgroup")
    @Expose
    String bloodgroup;

    @SerializedName("available")
    @Expose
    String available;

    public BloodAvailabilityDataModel(String bloodgroup, String available) {
        this.bloodgroup = bloodgroup;
        this.available = available;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
}
