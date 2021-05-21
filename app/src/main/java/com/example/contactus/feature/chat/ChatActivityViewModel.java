package com.example.contactus.feature.chat;

import com.example.contactus.feature.base.BaseViewModel;
import com.example.contactus.feature.data.dataSource.CloudDataSource;
import com.example.contactus.feature.data.entities.AddTicketResponse;
import com.example.contactus.feature.data.entities.SubmitNewTicketMessageResponse;

import io.reactivex.Single;

public class ChatActivityViewModel extends BaseViewModel {
    private final CloudDataSource cloudDataSource;

    public ChatActivityViewModel(CloudDataSource cloudDataSource) {
        this.cloudDataSource = cloudDataSource;
    }

    public Single<AddTicketResponse> submitNewTicket(String ticketTitle, int relatedDepartemantId) {
        return cloudDataSource.submitNewTicket(ticketTitle, relatedDepartemantId);
    }

    public Single<SubmitNewTicketMessageResponse> submitNewTicketMessage(int studentId, int messageSendType, String messageText, int ticketId) {
        return cloudDataSource.submitNewMessageOfTicket(studentId, messageSendType, messageText, ticketId);
    }

}
