package com.example.contactus.feature.supportermain;

import com.example.contactus.feature.data.dataSource.LocalDataSource;
import com.example.contactus.feature.data.dataSource.repo.AuthenticateDataSource;
import com.example.contactus.feature.data.dataSource.repo.AuthenticateRepo;
import com.example.contactus.feature.data.dataSource.repo.SupporterTicketsInboxRepo;
import com.example.contactus.feature.data.entities.LogoutResponse;
import com.example.contactus.feature.data.entities.SupporterTicketInboxResponse;

import io.reactivex.Single;

public class SupporterMainViewModel {
    
    private final SupporterTicketsInboxRepo supporterTicketsInboxRepo;
    private final AuthenticateRepo authenticateRepo;
    private final LocalDataSource localDataSource;
    
    public SupporterMainViewModel(SupporterTicketsInboxRepo supporterTicketsInboxRepo, AuthenticateRepo authenticateRepo, LocalDataSource localDataSource) {
        this.supporterTicketsInboxRepo = supporterTicketsInboxRepo;
        this.authenticateRepo = authenticateRepo;
        this.localDataSource = localDataSource;
    }
    
    
    public Single<SupporterTicketInboxResponse> getAll() {
        return supporterTicketsInboxRepo.getAll();
    }
    
    public Single<LogoutResponse> logoutButtonClicked() {
        return authenticateRepo.logout(AuthenticateDataSource.UserType.SUPPORTER);
    }
    
    public void clearSharedPref() {
        localDataSource.clear();
    }
}
