package com.example.contactus.feature.data.dataSource;

import com.example.contactus.feature.data.api.ApiService;
import com.example.contactus.feature.data.dataSource.repo.DepartemantsOpenTicketsDataSource;
import com.example.contactus.feature.data.entities.DepartemantOpenTicketsResponse;

import io.reactivex.Single;

public class DepartemantsOpenTicketCloudDataSource implements DepartemantsOpenTicketsDataSource {
    
    private final ApiService apiService;
    
    public DepartemantsOpenTicketCloudDataSource(ApiService apiService) {
        this.apiService = apiService;
    }
    
    @Override
    public Single<DepartemantOpenTicketsResponse> getAll() {
        return apiService.getAll();
    }
}
