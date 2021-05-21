package com.example.contactus.feature.data.entities;

import com.google.gson.annotations.SerializedName;

public class TicketsItem {

    @SerializedName("TicketId")
    private int ticketId;

    @SerializedName("TicketOwnerId")
    private int ticketOwnerId;

    @SerializedName("TicketLastMessage")
    private Message ticketLastMessage;

    @SerializedName("TicketRelatedAdministrativeDepartemantId")
    private int ticketRelatedAdministrativeDepartemantId;

    @SerializedName("TicketTitle")
    private String ticketTitle;

    @SerializedName("TicketStatus")
    private int ticketStatus;

    @SerializedName("TicketSubmitDate")
    private String ticketSubmitDate;

    public int getTicketId() {
        return ticketId;
    }

    public int getTicketOwnerId() {
        return ticketOwnerId;
    }

    public Message getTicketLastMessage() {
        return ticketLastMessage;
    }

    public int getTicketRelatedAdministrativeDepartemantId() {
        return ticketRelatedAdministrativeDepartemantId;
    }

    public String getTicketTitle() {
        return ticketTitle;
    }

    public int getTicketStatus() {
        return ticketStatus;
    }

    public String getTicketSubmitDate() {
        return ticketSubmitDate;
    }
}