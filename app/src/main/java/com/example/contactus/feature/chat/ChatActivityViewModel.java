package com.example.contactus.feature.chat;

import com.example.contactus.feature.base.BaseViewModel;
import com.example.contactus.feature.data.dataSource.TicketsCloudDataSource;
import com.example.contactus.feature.data.dataSource.TicketsMessageCloudDataSource;
import com.example.contactus.feature.data.entities.AddTicketResponse;
import com.example.contactus.feature.data.entities.CloseTicketResponse;
import com.example.contactus.feature.data.entities.Message;
import com.example.contactus.feature.data.entities.MessageListResponse;
import com.example.contactus.feature.data.entities.SubmitNewTicketMessageResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class ChatActivityViewModel extends BaseViewModel {
    private final TicketsCloudDataSource cloudDataSource;
    private final TicketsMessageCloudDataSource ticketsMessageCloudDataSource;

    public ChatActivityViewModel(TicketsCloudDataSource cloudDataSource, TicketsMessageCloudDataSource ticketsMessageCloudDataSource) {
        this.cloudDataSource = cloudDataSource;
        this.ticketsMessageCloudDataSource = ticketsMessageCloudDataSource;
    }

    public Single<AddTicketResponse> submitNewTicket(String ticketTitle, int relatedDepartemantId) {
        return cloudDataSource.submitNewTicket(ticketTitle, relatedDepartemantId);
    }

    public Single<SubmitNewTicketMessageResponse> submitNewTicketMessage(int studentId, int messageSendType, String messageText, int ticketId) {
        return ticketsMessageCloudDataSource.submitNewMessageOfTicket(studentId, messageSendType, messageText, ticketId);
    }


    public Single<MessageListResponse> getAll(int ticketId) {
        return ticketsMessageCloudDataSource.getTicketsMessages(ticketId).map((res) -> {
            List<Message> filteredList = new ArrayList<>();
            for (Message message : res.getMessages()
            ) {
                if (message.getMessageSendType() == 1) {
                    message.setMessageSendStatus(Message.sendStatus.SEND);
                    filteredList.add(message);
                } else {
                    filteredList.add(message);
                }
            }
            return new MessageListResponse(res.getPageNumber(), res.isSuccess(), filteredList, res.getMessagesCount(), res.getTicketId());
        });
    }


    public Single<CloseTicketResponse> closeTicket(int ticketId) {
        return cloudDataSource.closeTicket(ticketId);
    }


}
