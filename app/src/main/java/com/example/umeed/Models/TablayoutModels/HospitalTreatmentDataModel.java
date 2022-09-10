package com.example.umeed.Models.TablayoutModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HospitalTreatmentDataModel {

    @SerializedName("bedname")
    @Expose
    String bedname;

    @SerializedName("medicalid")
    @Expose
    String medicalid;

    @SerializedName("usertype")
    @Expose
    String usertype;

    @SerializedName("treatment")
    @Expose
    String treatment;

    @SerializedName("cost")
    @Expose
    String cost;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("treatmentid")
    @Expose
    String treatmentid;

    public HospitalTreatmentDataModel(String bedname, String medicalid, String usertype, String treatment, String cost, String description, String treatmentid) {
        this.bedname = bedname;
        this.medicalid = medicalid;
        this.usertype = usertype;
        this.treatment = treatment;
        this.cost = cost;
        this.description = description;
        this.treatmentid = treatmentid;
    }

    public String getBedname() {
        return bedname;
    }

    public void setBedname(String bedname) {
        this.bedname = bedname;
    }

    public String getMedicalid() {
        return medicalid;
    }

    public void setMedicalid(String medicalid) {
        this.medicalid = medicalid;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTreatmentid() {
        return treatmentid;
    }

    public void setTreatmentid(String treatmentid) {
        this.treatmentid = treatmentid;
    }
}
