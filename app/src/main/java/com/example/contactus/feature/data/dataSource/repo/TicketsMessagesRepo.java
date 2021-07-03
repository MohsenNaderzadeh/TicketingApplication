package com.example.contactus.feature.data.dataSource.repo;

import com.example.contactus.feature.data.dataSource.TicketsMessageCloudDataSource;
import com.example.contactus.feature.data.entities.MessageListResponse;
import com.example.contactus.feature.data.entities.SubmitNewTicketMessageResponse;

import io.reactivex.Single;

public class TicketsMessagesRepo implements TicketsMessagesDataSource {
    private final TicketsMessageCloudDataSource cloudDataSource;

    public TicketsMessagesRepo(TicketsMessageCloudDataSource cloudDataSource) {
        this.cloudDataSource = cloudDataSource;
    }

    @Override
    public Single<SubmitNewTicketMessageResponse> submitNewMessageOfTicket(int studentId, int messageSendType, String messageText, int ticketId) {
        return cloudDataSource.submitNewMessageOfTicket(studentId, messageSendType, messageText, ticketId);
    }

    @Override
    public Single<MessageListResponse> getTicketsMessages(int ticketsId) {
        return cloudDataSource.getTicketsMessages(ticketsId);
    }
}
