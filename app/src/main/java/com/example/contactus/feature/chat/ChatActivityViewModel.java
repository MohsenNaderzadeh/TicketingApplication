package com.example.contactus.feature.chat;

import com.example.contactus.feature.base.BaseViewModel;
import com.example.contactus.feature.data.TokenContainer;
import com.example.contactus.feature.data.dataSource.repo.AuthenticateDataSource;
import com.example.contactus.feature.data.dataSource.repo.TicketsMessagesRepo;
import com.example.contactus.feature.data.dataSource.repo.TicketsRepository;
import com.example.contactus.feature.data.entities.AddTicketResponse;
import com.example.contactus.feature.data.entities.AddToSupporterInboxResponse;
import com.example.contactus.feature.data.entities.CloseTicketResponse;
import com.example.contactus.feature.data.entities.Message;
import com.example.contactus.feature.data.entities.MessageListResponse;
import com.example.contactus.feature.data.entities.SubmitNewTicketMessageResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class ChatActivityViewModel extends BaseViewModel {
    private final TicketsRepository cloudDataSource;
    private final TicketsMessagesRepo ticketsMessageRepo;
    
    public ChatActivityViewModel(TicketsRepository cloudDataSource, TicketsMessagesRepo ticketsMessageRepo) {
        this.cloudDataSource = cloudDataSource;
        this.ticketsMessageRepo = ticketsMessageRepo;
    }
    
    public Single<AddTicketResponse> submitNewTicket(String ticketTitle, int relatedDepartemantId) {
        return cloudDataSource.submitNewTicket(ticketTitle, relatedDepartemantId);
    }
    
    public Single<SubmitNewTicketMessageResponse> submitNewTicketMessage(int studentId, int messageSendType, String messageText, int ticketId, AuthenticateDataSource.UserType userType, int CoworkderId) {
        return ticketsMessageRepo.submitNewMessageOfTicket(studentId, messageSendType, messageText, ticketId, userType, CoworkderId);
    }
    
    
    public Single<MessageListResponse> getAll(int ticketId, AuthenticateDataSource.UserType userType) {
        return ticketsMessageRepo.getTicketsMessages(ticketId, userType).map((res) -> {
            List<Message> filteredList = new ArrayList<>();
            for (Message message : res.getMessages()
            ) {
                if (message.getMessageSendType() == 1 && TokenContainer.isStudent()) {
                    message.setMessageSendStatus(Message.sendStatus.SEND);
                    filteredList.add(message);
                } else if (message.getMessageSendType() == 0 && TokenContainer.isIsSupporter()) {
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
    
    
    public Single<AddToSupporterInboxResponse> addTicketToInbox(int ticketId) {
        return cloudDataSource.addTicketToInbox(ticketId);
    }
    
    
}
