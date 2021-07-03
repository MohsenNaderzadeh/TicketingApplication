package com.example.contactus.feature.data.dataSource;

import com.example.contactus.feature.data.api.ApiService;
import com.example.contactus.feature.data.dataSource.repo.TicketsMessagesDataSource;
import com.example.contactus.feature.data.entities.MessageListResponse;
import com.example.contactus.feature.data.entities.SubmitNewTicketMessageResponse;

import io.reactivex.Single;

public class TicketsMessagesCloudDataSource implements TicketsMessagesDataSource {
    private final ApiService apiService;

    public TicketsMessagesCloudDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<SubmitNewTicketMessageResponse> submitNewMessageOfTicket(int studentId, int messageSendType, String messageText, int ticketId) {
        return apiService.submitNewMessageOfTicket(studentId, messageSendType, messageText, ticketId);
    }

    @Override
    public Single<MessageListResponse> getTicketsMessages(int ticketsId) {
        return apiService.getAllTicketsMessage(ticketsId);
    }
}
