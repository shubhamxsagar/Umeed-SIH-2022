package com.example.umeed.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComplaintsDataModel {

    @SerializedName("complainid")
    @Expose
    String complainid;

    @SerializedName("userid")
    @Expose
    String userid;

    @SerializedName("type")
    @Expose
    String type;

    @SerializedName("bookingid")
    @Expose
    String bookingid;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("timestamp")
    @Expose
    String timestamp;

    public ComplaintsDataModel(String complainid, String userid, String type, String bookingid, String description, String status, String timestamp) {
        this.complainid = complainid;
        this.userid = userid;
        this.type = type;
        this.bookingid = bookingid;
        this.description = description;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getComplainid() {
        return complainid;
    }

    public void setComplainid(String complainid) {
        this.complainid = complainid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBookingid() {
        return bookingid;
    }

    public void setBookingid(String bookingid) {
        this.bookingid = bookingid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
