package com.example.contactus.feature.data.dataSource;

import com.example.contactus.feature.data.api.ApiService;
import com.example.contactus.feature.data.dataSource.repo.SupporterTicketsInboxDataSource;
import com.example.contactus.feature.data.entities.SupporterTicketInboxResponse;

import io.reactivex.Single;

public class SupporterTicketsInboxCloud implements SupporterTicketsInboxDataSource {
    
    private final ApiService apiService;
    
    public SupporterTicketsInboxCloud(ApiService apiService) {
        this.apiService = apiService;
    }
    
    @Override
    public Single<SupporterTicketInboxResponse> getAll() {
        return apiService.getAllSupporterTicketInbox();
    }
    
    @Override
    public Single<SupporterTicketInboxResponse> getAllClosedTickets() {
        return apiService.getAllClosedTicketsSupporter();
    }
}
