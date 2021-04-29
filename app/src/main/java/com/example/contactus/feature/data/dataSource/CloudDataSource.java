package com.example.contactus.feature.data.dataSource;

import com.example.contactus.feature.data.api.ApiService;
import com.example.contactus.feature.data.dataSource.repo.AuthenticateDataSource;
import com.example.contactus.feature.data.entities.Token;

import io.reactivex.Single;


public class CloudDataSource implements AuthenticateDataSource {
    private final ApiService apiService;

    public CloudDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<Token> authenticate(String userName, String passWord, UserType userType) {
        if (userType == UserType.USER) {
            return apiService.userAuthenticate(userName, passWord);
        } else {
            return apiService.supporterAuthenticate(userName, passWord);
        }
    }
}
