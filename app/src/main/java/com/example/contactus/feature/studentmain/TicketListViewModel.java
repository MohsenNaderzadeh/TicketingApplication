package com.example.contactus.feature.studentmain;

import com.example.contactus.feature.base.BaseViewModel;
import com.example.contactus.feature.data.dataSource.LocalDataSource;
import com.example.contactus.feature.data.dataSource.repo.AuthenticateDataSource;
import com.example.contactus.feature.data.dataSource.repo.AuthenticateRepo;
import com.example.contactus.feature.data.dataSource.repo.TicketsRepository;
import com.example.contactus.feature.data.entities.LogoutResponse;
import com.example.contactus.feature.data.entities.TicketsResponse;

import io.reactivex.Single;

public class TicketListViewModel extends BaseViewModel {
    
    
    private final TicketsRepository ticketsRepository;
    private final AuthenticateRepo authenticateRepo;
    private final LocalDataSource localDataSource;
    
    public TicketListViewModel(TicketsRepository ticketsRepository, AuthenticateRepo authenticateRepo, LocalDataSource localDataSource) {
        this.ticketsRepository = ticketsRepository;
        this.authenticateRepo = authenticateRepo;
        this.localDataSource = localDataSource;
    }
    
    public Single<TicketsResponse> getTicketsList() {
        return ticketsRepository.getTicketList();
    }
    
    
    public Single<LogoutResponse> logoutButtonClicked() {
        return authenticateRepo.logout(AuthenticateDataSource.UserType.USER);
    }
    
    public void clearSharedPref() {
        localDataSource.clear();
    }
    
    public Single<TicketsResponse> getClosedTickets() {
        return ticketsRepository.getClosedTicketsList();
    }
}
