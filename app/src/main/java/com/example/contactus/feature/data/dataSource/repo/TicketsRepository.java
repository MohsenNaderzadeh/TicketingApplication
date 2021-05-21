package com.example.contactus.feature.data.dataSource.repo;

import com.example.contactus.feature.data.dataSource.CloudDataSource;
import com.example.contactus.feature.data.entities.AddTicketResponse;
import com.example.contactus.feature.data.entities.TicketsResponse;

import io.reactivex.Single;

public class TicketsRepository implements TicketsDataSource {
    private final CloudDataSource cloudDataSource;

    public TicketsRepository(CloudDataSource cloudDataSource) {
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
}
