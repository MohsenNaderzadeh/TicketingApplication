package com.example.contactus.feature.data.dataSource.repo;

import com.example.contactus.feature.data.entities.MessageListResponse;
import com.example.contactus.feature.data.entities.SubmitNewTicketMessageResponse;

import io.reactivex.Single;

public interface TicketsMessagesDataSource {

    Single<SubmitNewTicketMessageResponse> submitNewMessageOfTicket(int studentId, int messageSendType, String messageText, int ticketId, AuthenticateDataSource.UserType userType, int CoworkerId);
    
    Single<MessageListResponse> getTicketsMessages(int ticketsId, AuthenticateDataSource.UserType userType);
}
