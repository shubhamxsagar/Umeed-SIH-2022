package com.example.umeed.Chat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ViewChatContactModel {

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("error")
    @Expose
    boolean error;

    @SerializedName("data")
    @Expose
    ArrayList<ViewChatContactDataModel> data;

    public ViewChatContactModel(String status, boolean error, ArrayList<ViewChatContactDataModel> data) {
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

    public ArrayList<ViewChatContactDataModel> getData() {
        return data;
    }

    public void setData(ArrayList<ViewChatContactDataModel> data) {
        this.data = data;
    }
}
