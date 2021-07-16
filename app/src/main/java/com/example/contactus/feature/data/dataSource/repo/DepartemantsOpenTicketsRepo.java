package com.example.contactus.feature.data.dataSource.repo;

import com.example.contactus.feature.data.dataSource.DepartemantsOpenTicketCloudDataSource;
import com.example.contactus.feature.data.entities.DepartemantOpenTicketsResponse;

import io.reactivex.Single;

public class DepartemantsOpenTicketsRepo implements DepartemantsOpenTicketsDataSource {
    private final DepartemantsOpenTicketCloudDataSource departemantsOpenTicketCloudDataSource;
    
    public DepartemantsOpenTicketsRepo(DepartemantsOpenTicketCloudDataSource departemantsOpenTicketCloudDataSource) {
        this.departemantsOpenTicketCloudDataSource = departemantsOpenTicketCloudDataSource;
    }
    
    @Override
    public Single<DepartemantOpenTicketsResponse> getAll() {
        return departemantsOpenTicketCloudDataSource.getAll();
    }
}
