package com.example.contactus.feature.data.dataSource;

import com.example.contactus.feature.data.dataSource.repo.AuthenticateDataSource;
import com.example.contactus.feature.data.entities.LoginResponse;

import io.reactivex.Single;

public class LocalDataSource implements AuthenticateDataSource {


    @Override
    public Single<LoginResponse> authenticate(String userName, String passWord, UserType userType) {
        return null;
    }
}
