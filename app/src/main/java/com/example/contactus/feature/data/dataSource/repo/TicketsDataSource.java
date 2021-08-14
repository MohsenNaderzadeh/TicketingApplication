package com.example.contactus.feature.data.dataSource.repo;

import com.example.contactus.feature.data.entities.AddTicketResponse;
import com.example.contactus.feature.data.entities.AddToSupporterInboxResponse;
import com.example.contactus.feature.data.entities.CloseTicketResponse;
import com.example.contactus.feature.data.entities.TicketOwnerInfo;
import com.example.contactus.feature.data.entities.TicketsResponse;

import io.reactivex.Single;

public interface TicketsDataSource {
    
    Single<TicketsResponse> getTicketList();
    
    Single<AddTicketResponse> submitNewTicket(String ticketTitle, int relatedDepartemantId);
    
    Single<CloseTicketResponse> closeTicket(int ticketId, AuthenticateDataSource.UserType userType);
    
    Single<AddToSupporterInboxResponse> addTicketToInbox(int ticketId);
    
    Single<TicketsResponse> getClosedTicketsList();
    
    Single<TicketOwnerInfo> getTicketOwnerInfo(int ownerInfo);
}
