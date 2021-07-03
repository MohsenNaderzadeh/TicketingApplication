package com.example.contactus.feature.data.dataSource;

import com.example.contactus.feature.data.api.ApiService;
import com.example.contactus.feature.data.dataSource.repo.TicketsDataSource;
import com.example.contactus.feature.data.entities.AddTicketResponse;
import com.example.contactus.feature.data.entities.CloseTicketResponse;
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
    public Single<CloseTicketResponse> closeTicket(int ticketId) {
        return apiService.closeTicket(ticketId);
    }


}
