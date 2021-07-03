package com.example.contactus.feature.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MessageListResponse {

    @SerializedName("pageNumber")
    private int pageNumber;

    @SerializedName("success")
    private boolean success;

    @SerializedName("messages")
    private List<Message> messages;

    @SerializedName("messagesCount")
    private int messagesCount;

    @SerializedName("ticketId")
    private int ticketId;

    public MessageListResponse(int pageNumber, boolean success, List<Message> messages, int messagesCount, int ticketId) {
        this.pageNumber = pageNumber;
        this.success = success;
        this.messages = messages;
        this.messagesCount = messagesCount;
        this.ticketId = ticketId;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public int getMessagesCount() {
        return messagesCount;
    }

    public void setMessagesCount(int messagesCount) {
        this.messagesCount = messagesCount;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
}