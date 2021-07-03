package com.example.contactus.feature.studentmain;

import com.example.contactus.feature.base.BaseViewModel;
import com.example.contactus.feature.data.dataSource.repo.TicketsRepository;
import com.example.contactus.feature.data.entities.TicketsResponse;

import io.reactivex.Single;

public class TicketListViewModel extends BaseViewModel {


    private final TicketsRepository ticketsRepository;

    public TicketListViewModel(TicketsRepository ticketsRepository) {
        this.ticketsRepository = ticketsRepository;
    }

    public Single<TicketsResponse> getTicketsList() {
        return ticketsRepository.getTicketList();
    }
}
