package com.example.contactus.feature.data.entities;

import com.google.gson.annotations.SerializedName;

public class SubmitNewTicketMessageResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private Message message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}