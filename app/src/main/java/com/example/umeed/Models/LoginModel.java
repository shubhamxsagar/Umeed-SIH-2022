package com.example.umeed.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("usertype")
    @Expose
    String usertype;

    @SerializedName("usersubtype")
    @Expose
    String usersubtype;

    public LoginModel(String usertype, String usersubtype) {
        this.usertype = usertype;
        this.usersubtype = usersubtype;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getUsersubtype() {
        return usersubtype;
    }

    public void setUsersubtype(String usersubtype) {
        this.usersubtype = usersubtype;
    }
}
