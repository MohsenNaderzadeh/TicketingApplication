package com.example.contactus.feature.data.dataSource;

import com.example.contactus.feature.data.api.ApiService;
import com.example.contactus.feature.data.dataSource.repo.AuthenticateDataSource;
import com.example.contactus.feature.data.dataSource.repo.TicketsDataSource;
import com.example.contactus.feature.data.dataSource.repo.TicketsMessagesDataSource;
import com.example.contactus.feature.data.dataSource.repo.TicketsSubjectDataSource;
import com.example.contactus.feature.data.entities.AddTicketResponse;
import com.example.contactus.feature.data.entities.LoginResponse;
import com.example.contactus.feature.data.entities.RelatedDepartemants;
import com.example.contactus.feature.data.entities.SubmitNewTicketMessageResponse;
import com.example.contactus.feature.data.entities.TicketsResponse;

import java.util.List;

import io.reactivex.Single;


public class CloudDataSource implements AuthenticateDataSource, TicketsDataSource, TicketsSubjectDataSource, TicketsMessagesDataSource {
    private final ApiService apiService;

    public CloudDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<LoginResponse> authenticate(String userName, String passWord, UserType userType) {
        if (userType == UserType.USER) {
            return apiService.userAuthenticate(userName, passWord);
        } else {
            return apiService.supporterAuthenticate(userName, passWord);
        }
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
    public Single<List<RelatedDepartemants>> getAllSubjects() {
        return apiService.getAllTicketsSubject();
    }

    @Override
    public Single<SubmitNewTicketMessageResponse> submitNewMessageOfTicket(int studentId, int messageSendType, String messageText, int ticketId) {
        return apiService.submitNewMessageOfTicket(studentId, messageSendType, messageText, ticketId);
    }
}
