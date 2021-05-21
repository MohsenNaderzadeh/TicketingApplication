package com.example.contactus.feature.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TicketsResponse {

    @SerializedName("ticketsLenght")
    private int ticketsLenght;

    @SerializedName("tickets")
    private List<TicketInfo> tickets;

    @SerializedName("pageNumber")
    private int pageNumber;

    @SerializedName("success")
    private boolean success;

    public int getTicketsLenght() {
        return ticketsLenght;
    }

    public List<TicketInfo> getTickets() {
        return tickets;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public boolean isSuccess() {
        return success;
    }
}