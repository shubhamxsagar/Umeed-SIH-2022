package com.example.umeed.Chat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewChatContactDataModel {

    @SerializedName("contact")
    @Expose
    String contact;

    public ViewChatContactDataModel(String contact) {
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
