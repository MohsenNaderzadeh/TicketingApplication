package com.example.contactus.feature.data.dataSource;

import com.example.contactus.feature.data.api.ApiService;
import com.example.contactus.feature.data.dataSource.repo.AuthenticateDataSource;
import com.example.contactus.feature.data.entities.LoginResponse;
import com.example.contactus.feature.data.entities.LogoutResponse;

import io.reactivex.Single;

public class AuthenticationCloudDataSource implements AuthenticateDataSource {
    private final ApiService apiService;

    public AuthenticationCloudDataSource(ApiService apiService) {
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
    public Single<LogoutResponse> logout(UserType userType) {
        if (userType == UserType.USER) {
            return apiService.logoutStudent();
        } else {
            return apiService.logoutSupporter();
        }
    }
}
