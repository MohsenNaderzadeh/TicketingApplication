package com.example.contactus.feature.data.dataSource.repo;

import com.example.contactus.feature.data.dataSource.CloudDataSource;
import com.example.contactus.feature.data.entities.SubmitNewTicketMessageResponse;

import io.reactivex.Single;

public class TicketsMessagesRepo implements TicketsMessagesDataSource {
    private final CloudDataSource cloudDataSource;

    public TicketsMessagesRepo(CloudDataSource cloudDataSource) {
        this.cloudDataSource = cloudDataSource;
    }

    @Override
    public Single<SubmitNewTicketMessageResponse> submitNewMessageOfTicket(int studentId, int messageSendType, String messageText, int ticketId) {
        return cloudDataSource.submitNewMessageOfTicket(studentId, messageSendType, messageText, ticketId);
    }
}
