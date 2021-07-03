package com.example.contactus.feature.supportermain;

import com.example.contactus.feature.data.dataSource.repo.SupporterTicketsInboxRepo;
import com.example.contactus.feature.data.entities.SupporterTicketInboxResponse;

import io.reactivex.Single;

public class SupporterMainViewModel {

    private final SupporterTicketsInboxRepo supporterTicketsInboxRepo;

    public SupporterMainViewModel(SupporterTicketsInboxRepo supporterTicketsInboxRepo) {
        this.supporterTicketsInboxRepo = supporterTicketsInboxRepo;
    }


    public Single<SupporterTicketInboxResponse> getAll() {
        return supporterTicketsInboxRepo.getAll();
    }
}
