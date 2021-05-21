package com.example.contactus.feature.data.dataSource.repo;

import com.example.contactus.feature.data.entities.SubmitNewTicketMessageResponse;

import io.reactivex.Single;

public interface TicketsMessagesDataSource {

    Single<SubmitNewTicketMessageResponse> submitNewMessageOfTicket(int studentId, int messageSendType, String messageText, int ticketId);
}
