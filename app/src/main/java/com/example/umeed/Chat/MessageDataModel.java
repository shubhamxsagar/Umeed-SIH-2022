package com.example.umeed.Chat;

import com.example.umeed.Models.AllListDataModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MessageDataModel {

    @SerializedName("sender")
    @Expose
    String sender;

    @SerializedName("receiver")
    @Expose
    String receiver;

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("timestamp")
    @Expose
    String timestamp;

    public MessageDataModel(String sender, String receiver, String message, String timestamp) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
