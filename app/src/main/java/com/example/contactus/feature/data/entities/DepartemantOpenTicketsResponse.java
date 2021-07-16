package com.example.contactus.feature.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DepartemantOpenTicketsResponse {
    
    @SerializedName("ticketsList")
    private List<TicketInfo> ticketsList;
    
    @SerializedName("success")
    private boolean success;
    
    public List<TicketInfo> getTicketsList() {
        return ticketsList;
    }
    
    public boolean isSuccess() {
        return success;
    }
}