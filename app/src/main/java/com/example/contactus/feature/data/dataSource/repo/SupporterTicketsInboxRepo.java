package com.example.contactus.feature.data.dataSource.repo;

import com.example.contactus.feature.data.dataSource.SupporterTicketsInboxCloud;
import com.example.contactus.feature.data.entities.SupporterTicketInboxResponse;

import io.reactivex.Single;

public class SupporterTicketsInboxRepo implements SupporterTicketsInboxDataSource {
    
    private final SupporterTicketsInboxCloud supporterTicketsInboxCloud;
    
    public SupporterTicketsInboxRepo(SupporterTicketsInboxCloud supporterTicketsInboxCloud) {
        this.supporterTicketsInboxCloud = supporterTicketsInboxCloud;
    }
    
    @Override
    public Single<SupporterTicketInboxResponse> getAll() {
        return supporterTicketsInboxCloud.getAll();
    }
    
    @Override
    public Single<SupporterTicketInboxResponse> getAllClosedTickets() {
        return supporterTicketsInboxCloud.getAllClosedTickets();
    }
}
