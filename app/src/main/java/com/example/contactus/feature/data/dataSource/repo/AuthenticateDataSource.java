package com.example.contactus.feature.data.dataSource.repo;

import com.example.contactus.feature.data.entities.LoginResponse;
import com.example.contactus.feature.data.entities.LogoutResponse;

import io.reactivex.Single;

public interface AuthenticateDataSource {
    enum UserType {
        USER, SUPPORTER
    }
    
    Single<LoginResponse> authenticate(String userName, String passWord, UserType userType);
    
    Single<LogoutResponse> logout(UserType userType);
    
    
}
