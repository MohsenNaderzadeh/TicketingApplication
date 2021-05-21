package com.example.contactus.feature.data.dataSource.repo;

import com.example.contactus.feature.data.entities.AddTicketResponse;
import com.example.contactus.feature.data.entities.TicketsResponse;

import io.reactivex.Single;

public interface TicketsDataSource {

    Single<TicketsResponse> getTicketList();

    Single<AddTicketResponse> submitNewTicket(String ticketTitle, int relatedDepartemantId);
}
