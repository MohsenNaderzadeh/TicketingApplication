package com.example.contactus.feature.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SupporterTicketInboxResponse {

    @SerializedName("ticketCount")
    private int ticketCount;

    @SerializedName("success")
    private boolean success;

    @SerializedName("supporterId")
    private int supporterId;

    @SerializedName("supporterTickets")
    private List<SupporterTicketsItem> supporterTickets;

    public int getTicketCount() {
        return ticketCount;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getSupporterId() {
        return supporterId;
    }

    public List<SupporterTicketsItem> getSupporterTickets() {
        return supporterTickets;
    }
}