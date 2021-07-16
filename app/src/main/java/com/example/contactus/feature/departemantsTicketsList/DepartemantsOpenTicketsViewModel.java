package com.example.contactus.feature.departemantsTicketsList;

import com.example.contactus.feature.base.BaseViewModel;
import com.example.contactus.feature.data.dataSource.DepartemantsOpenTicketCloudDataSource;
import com.example.contactus.feature.data.entities.DepartemantOpenTicketsResponse;

import io.reactivex.Single;

public class DepartemantsOpenTicketsViewModel extends BaseViewModel {
    
    private final DepartemantsOpenTicketCloudDataSource departemantsOpenTicketCloudDataSource;
    
    public DepartemantsOpenTicketsViewModel(DepartemantsOpenTicketCloudDataSource departemantsOpenTicketCloudDataSource) {
        this.departemantsOpenTicketCloudDataSource = departemantsOpenTicketCloudDataSource;
    }
    
    
    public Single<DepartemantOpenTicketsResponse> getll() {
        return departemantsOpenTicketCloudDataSource.getAll();
    }
}
