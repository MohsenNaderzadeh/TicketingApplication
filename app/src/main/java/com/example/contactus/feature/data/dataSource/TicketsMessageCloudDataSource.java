package com.example.contactus.feature.data.dataSource;

import com.example.contactus.feature.data.api.ApiService;
import com.example.contactus.feature.data.dataSource.repo.AuthenticateDataSource;
import com.example.contactus.feature.data.dataSource.repo.TicketsMessagesDataSource;
import com.example.contactus.feature.data.entities.MessageListResponse;
import com.example.contactus.feature.data.entities.SubmitNewTicketMessageResponse;

import io.reactivex.Single;

public class TicketsMessageCloudDataSource implements TicketsMessagesDataSource {
    private final ApiService apiService;

    public TicketsMessageCloudDataSource(ApiService apiService) {
        this.apiService = apiService;
    }
    
    @Override
    public Single<SubmitNewTicketMessageResponse> submitNewMessageOfTicket(int studentId, int messageSendType, String messageText, int ticketId, AuthenticateDataSource.UserType userType, int CoworkerId) {
        if (userType == AuthenticateDataSource.UserType.USER) {
            return apiService.submitNewMessageOfTicket(studentId, messageSendType, messageText, ticketId);
            
        } else {
            return apiService.submitNewMessageOfTicketForSupporter(CoworkerId, messageSendType, messageText, ticketId);
        }
    }
    
    @Override
    public Single<MessageListResponse> getTicketsMessages(int ticketsId, AuthenticateDataSource.UserType userType) {
        if (userType == AuthenticateDataSource.UserType.USER) {
            return apiService.getAllTicketsMessage(ticketsId);
            
        } else {
            return apiService.getAllMessageForSupporter(ticketsId);
        }
    }
}
