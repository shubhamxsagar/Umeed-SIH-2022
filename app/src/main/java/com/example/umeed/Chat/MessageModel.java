package com.example.umeed.Chat;

import com.example.umeed.Models.AllListDataModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MessageModel {

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("error")
    @Expose
    boolean error;

    @SerializedName("data")
    @Expose
    ArrayList<MessageDataModel> data;

    public MessageModel(String status, boolean error, ArrayList<MessageDataModel> data) {
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

    public ArrayList<MessageDataModel> getData() {
        return data;
    }

    public void setData(ArrayList<MessageDataModel> data) {
        this.data = data;
    }
}
