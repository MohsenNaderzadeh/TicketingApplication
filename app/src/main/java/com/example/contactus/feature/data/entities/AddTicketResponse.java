package com.example.contactus.feature.data.entities;

import com.google.gson.annotations.SerializedName;

public class AddTicketResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("ticketInfo")
    private TicketInfo ticketInfo;

    public boolean isSuccess() {
        return success;
    }

    public TicketInfo getTicketInfo() {
        return ticketInfo;
    }
}