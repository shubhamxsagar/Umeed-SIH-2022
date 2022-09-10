package com.example.umeed.Models.TablayoutModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HospitalAvailabilityDataModel {

    @SerializedName("costdifferencefromgovernment")
    @Expose
    String costdifferencefromgovernment;

    @SerializedName("medicalid")
    @Expose
    String medicalid;

    @SerializedName("bedname")
    @Expose
    String bedname;

    @SerializedName("facility")
    @Expose
    String facility;

    @SerializedName("oxygenprovided")
    @Expose
    String oxygenprovided;

    @SerializedName("cost")
    @Expose
    String cost;

    @SerializedName("bedid")
    @Expose
    String bedid;

    @SerializedName("type")
    @Expose
    String type;

    @SerializedName("totalnumberavailable")
    @Expose
    String totalnumberavailable;

    @SerializedName("currentavailablity")
    @Expose
    String currentavailablity;

    @SerializedName("bedonhold")
    @Expose
    String bedonhold;

    public HospitalAvailabilityDataModel(String costdifferencefromgovernment, String medicalid, String bedname, String facility, String oxygenprovided, String cost, String bedid, String type, String totalnumberavailable, String currentavailablity, String bedonhold) {
        this.costdifferencefromgovernment = costdifferencefromgovernment;
        this.medicalid = medicalid;
        this.bedname = bedname;
        this.facility = facility;
        this.oxygenprovided = oxygenprovided;
        this.cost = cost;
        this.bedid = bedid;
        this.type = type;
        this.totalnumberavailable = totalnumberavailable;
        this.currentavailablity = currentavailablity;
        this.bedonhold = bedonhold;
    }

    public String getCostdifferencefromgovernment() {
        return costdifferencefromgovernment;
    }

    public void setCostdifferencefromgovernment(String costdifferencefromgovernment) {
        this.costdifferencefromgovernment = costdifferencefromgovernment;
    }

    public String getMedicalid() {
        return medicalid;
    }

    public void setMedicalid(String medicalid) {
        this.medicalid = medicalid;
    }

    public String getBedname() {
        return bedname;
    }

    public void setBedname(String bedname) {
        this.bedname = bedname;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getOxygenprovided() {
        return oxygenprovided;
    }

    public void setOxygenprovided(String oxygenprovided) {
        this.oxygenprovided = oxygenprovided;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getBedid() {
        return bedid;
    }

    public void setBedid(String bedid) {
        this.bedid = bedid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTotalnumberavailable() {
        return totalnumberavailable;
    }

    public void setTotalnumberavailable(String totalnumberavailable) {
        this.totalnumberavailable = totalnumberavailable;
    }

    public String getCurrentavailablity() {
        return currentavailablity;
    }

    public void setCurrentavailablity(String currentavailablity) {
        this.currentavailablity = currentavailablity;
    }

    public String getBedonhold() {
        return bedonhold;
    }

    public void setBedonhold(String bedonhold) {
        this.bedonhold = bedonhold;
    }
}
