package com.example.contactus.feature.data.dataSource.repo;

import com.example.contactus.feature.data.entities.LoginResponse;

import io.reactivex.Single;

public interface AuthenticateDataSource {

    Single<LoginResponse> authenticate(String userName, String passWord, UserType userType);

    enum UserType {
        USER, SUPPORTER
    }

}
