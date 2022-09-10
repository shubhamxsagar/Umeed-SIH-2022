package com.example.umeed.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingDataModel {

    @SerializedName("bookingid")
    @Expose
    String bookingid;

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("medicalusertype")
    @Expose
    String medicalusertype;

    @SerializedName("ownername")
    @Expose
    String ownername;

    @SerializedName("organizationname")
    @Expose
    String organizationname;

    @SerializedName("treatmentname")
    @Expose
    String treatmentname;

    public BookingDataModel(String bookingid, String status, String medicalusertype, String ownername, String organizationname, String treatmentname) {
        this.bookingid = bookingid;
        this.status = status;
        this.medicalusertype = medicalusertype;
        this.ownername = ownername;
        this.organizationname = organizationname;
        this.treatmentname = treatmentname;
    }

    public String getBookingid() {
        return bookingid;
    }

    public void setBookingid(String bookingid) {
        this.bookingid = bookingid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMedicalusertype() {
        return medicalusertype;
    }

    public void setMedicalusertype(String medicalusertype) {
        this.medicalusertype = medicalusertype;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getOrganizationname() {
        return organizationname;
    }

    public void setOrganizationname(String organizationname) {
        this.organizationname = organizationname;
    }

    public String getTreatmentname() {
        return treatmentname;
    }

    public void setTreatmentname(String treatmentname) {
        this.treatmentname = treatmentname;
    }
}
