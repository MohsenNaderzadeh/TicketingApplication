package com.example.contactus.feature.data.entities;

import com.google.gson.annotations.SerializedName;

public class AddToSupporterInboxResponse {
    @SerializedName("ticketCount")
    private int ticketCount;
    
    @SerializedName("success")
    private boolean success;
    
    @SerializedName("supporterId")
    private int supporterId;
    
    @SerializedName("supporterTickets")
    private SupporterTicketsItem supporterTicket;
    
    public int getTicketCount() {
        return ticketCount;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public int getSupporterId() {
        return supporterId;
    }
    
    public SupporterTicketsItem getSupporterTickets() {
        return supporterTicket;
    }
    
}
