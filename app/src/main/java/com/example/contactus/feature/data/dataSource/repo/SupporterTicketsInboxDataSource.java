package com.example.contactus.feature.data.dataSource.repo;

import com.example.contactus.feature.data.entities.SupporterTicketInboxResponse;

import io.reactivex.Single;

public interface SupporterTicketsInboxDataSource {
    
    
    Single<SupporterTicketInboxResponse> getAll();
    
    Single<SupporterTicketInboxResponse> getAllClosedTickets();
    
    
}
