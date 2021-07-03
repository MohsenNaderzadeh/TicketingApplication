package com.example.contactus.feature.data.entities;

import com.google.gson.annotations.SerializedName;

public class SupporterTicketsItem {

    @SerializedName("AddedDate")
    private String addedDate;

    @SerializedName("TicketId")
    private int ticketId;

    @SerializedName("TicketOwnerId")
    private int ticketOwnerId;

    @SerializedName("TicketRelatedAdministrativeDepartemantId")
    private int ticketRelatedAdministrativeDepartemantId;

    @SerializedName("TicketTitle")
    private String ticketTitle;

    @SerializedName("TicketStatus")
    private int ticketStatus;

    @SerializedName("TicketInBoxId")
    private int ticketInBoxId;

    @SerializedName("TicketSubmitDate")
    private String ticketSubmitDate;

    @SerializedName("TicketLastMessage")
    private Message ticketLastMessage;

    public String getAddedDate() {
        return addedDate;
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getTicketOwnerId() {
        return ticketOwnerId;
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

    public int getTicketInBoxId() {
        return ticketInBoxId;
    }

    public String getTicketSubmitDate() {
        return ticketSubmitDate;
    }

    public Message getTicketLastMessage() {
        return ticketLastMessage;
    }

    public void setTicketLastMessage(Message ticketLastMessage) {
        this.ticketLastMessage = ticketLastMessage;
    }
}