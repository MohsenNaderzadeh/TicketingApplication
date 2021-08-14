package com.example.contactus.feature.data.dataSource.repo;

import com.example.contactus.feature.data.dataSource.TicketsCloudDataSource;
import com.example.contactus.feature.data.entities.AddTicketResponse;
import com.example.contactus.feature.data.entities.AddToSupporterInboxResponse;
import com.example.contactus.feature.data.entities.CloseTicketResponse;
import com.example.contactus.feature.data.entities.TicketOwnerInfo;
import com.example.contactus.feature.data.entities.TicketsResponse;

import io.reactivex.Single;

public class TicketsRepository implements TicketsDataSource {
    private final TicketsCloudDataSource cloudDataSource;

    public TicketsRepository(TicketsCloudDataSource cloudDataSource) {
        this.cloudDataSource = cloudDataSource;
    }

    @Override
    public Single<TicketsResponse> getTicketList() {
        return cloudDataSource.getTicketList();
    }
    
    @Override
    public Single<AddTicketResponse> submitNewTicket(String ticketTitle, int relatedDepartemantId) {
        return cloudDataSource.submitNewTicket(ticketTitle, relatedDepartemantId);
    }
    
    @Override
    public Single<CloseTicketResponse> closeTicket(int ticketId, AuthenticateDataSource.UserType userType) {
        return cloudDataSource.closeTicket(ticketId, userType);
    }
    
    @Override
    public Single<AddToSupporterInboxResponse> addTicketToInbox(int ticketId) {
        return cloudDataSource.addTicketToInbox(ticketId);
    }
    
    @Override
    public Single<TicketsResponse> getClosedTicketsList() {
        return cloudDataSource.getClosedTicketsList();
    }
    
    @Override
    public Single<TicketOwnerInfo> getTicketOwnerInfo(int ownerInfo) {
        return cloudDataSource.getTicketOwnerInfo(ownerInfo);
    }
}
