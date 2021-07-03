package com.example.contactus.feature.data.entities;

import com.google.gson.annotations.SerializedName;

public class CloseTicketResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("updatedTicket")
    private TicketInfo updatedTicket;

    public boolean isSuccess() {
        return success;
    }

    public TicketInfo getUpdatedTicket() {
        return updatedTicket;
    }
}