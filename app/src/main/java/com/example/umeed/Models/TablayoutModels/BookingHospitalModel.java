package com.example.umeed.Models.TablayoutModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingHospitalModel {

    @SerializedName("bookingid")
    @Expose
    String bookingid;

    @SerializedName("userid")
    @Expose
    String userid;

    @SerializedName("medicalid")
    @Expose
    String medicalid;

    @SerializedName("medicalusertype")
    @Expose
    String medicalusertype;

    @SerializedName("servicetype")
    @Expose
    String servicetype;

    @SerializedName("serviceid")
    @Expose
    String serviceid;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("sharemedicalhistory")
    @Expose
    String sharemedicalhistory;

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("through")
    @Expose
    String through;

    @SerializedName("timestamp")
    @Expose
    String timestamp;

    @SerializedName("allotedbedid")
    @Expose
    String allotedbedid;

    @SerializedName("needambulance")
    @Expose
    String needambulance;

    public BookingHospitalModel(String bookingid, String userid, String medicalid, String medicalusertype, String servicetype, String serviceid, String description, String sharemedicalhistory, String status, String through, String timestamp, String allotedbedid, String needambulance) {
        this.bookingid = bookingid;
        this.userid = userid;
        this.medicalid = medicalid;
        this.medicalusertype = medicalusertype;
        this.servicetype = servicetype;
        this.serviceid = serviceid;
        this.description = description;
        this.sharemedicalhistory = sharemedicalhistory;
        this.status = status;
        this.through = through;
        this.timestamp = timestamp;
        this.allotedbedid = allotedbedid;
        this.needambulance = needambulance;
    }

    public String getBookingid() {
        return bookingid;
    }

    public void setBookingid(String bookingid) {
        this.bookingid = bookingid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMedicalid() {
        return medicalid;
    }

    public void setMedicalid(String medicalid) {
        this.medicalid = medicalid;
    }

    public String getMedicalusertype() {
        return medicalusertype;
    }

    public void setMedicalusertype(String medicalusertype) {
        this.medicalusertype = medicalusertype;
    }

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    public String getServiceid() {
        return serviceid;
    }

    public void setServiceid(String serviceid) {
        this.serviceid = serviceid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSharemedicalhistory() {
        return sharemedicalhistory;
    }

    public void setSharemedicalhistory(String sharemedicalhistory) {
        this.sharemedicalhistory = sharemedicalhistory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getThrough() {
        return through;
    }

    public void setThrough(String through) {
        this.through = through;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAllotedbedid() {
        return allotedbedid;
    }

    public void setAllotedbedid(String allotedbedid) {
        this.allotedbedid = allotedbedid;
    }

    public String getNeedambulance() {
        return needambulance;
    }

    public void setNeedambulance(String needambulance) {
        this.needambulance = needambulance;
    }

}
