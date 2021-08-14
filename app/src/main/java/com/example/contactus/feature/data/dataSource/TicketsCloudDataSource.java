package com.example.contactus.feature.data.dataSource;

import com.example.contactus.feature.data.api.ApiService;
import com.example.contactus.feature.data.dataSource.repo.AuthenticateDataSource;
import com.example.contactus.feature.data.dataSource.repo.TicketsDataSource;
import com.example.contactus.feature.data.entities.AddTicketResponse;
import com.example.contactus.feature.data.entities.AddToSupporterInboxResponse;
import com.example.contactus.feature.data.entities.CloseTicketResponse;
import com.example.contactus.feature.data.entities.TicketOwnerInfo;
import com.example.contactus.feature.data.entities.TicketsResponse;

import io.reactivex.Single;

public class TicketsCloudDataSource implements TicketsDataSource {

    private final ApiService apiService;

    public TicketsCloudDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<TicketsResponse> getTicketList() {
        return apiService.getAllTicketsList();
    }
    
    @Override
    public Single<AddTicketResponse> submitNewTicket(String ticketTitle, int relatedDepartemantId) {
        return apiService.submitNewTicket(ticketTitle, relatedDepartemantId);
    }
    
    @Override
    public Single<CloseTicketResponse> closeTicket(int ticketId, AuthenticateDataSource.UserType userType) {
        if (userType == AuthenticateDataSource.UserType.USER) {
            return apiService.closeTicket(ticketId);
            
        } else if (userType == AuthenticateDataSource.UserType.SUPPORTER) {
            return apiService.closeTicketBySupporter(ticketId);
        }
        return null;
    }
    
    @Override
    public Single<AddToSupporterInboxResponse> addTicketToInbox(int ticketId) {
        return apiService.addTicketToInbox(ticketId);
    }
    
    @Override
    public Single<TicketsResponse> getClosedTicketsList() {
        return apiService.getAllClosedTicketsList();
    }
    
    @Override
    public Single<TicketOwnerInfo> getTicketOwnerInfo(int ownerInfo) {
        return apiService.getTicketOwnerInfo(ownerInfo);
    }
    
    
}
