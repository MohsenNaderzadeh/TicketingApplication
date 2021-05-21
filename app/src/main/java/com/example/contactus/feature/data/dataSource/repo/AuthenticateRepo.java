package com.example.contactus.feature.data.dataSource.repo;

import com.example.contactus.feature.data.dataSource.CloudDataSource;
import com.example.contactus.feature.data.entities.LoginResponse;

import io.reactivex.Single;

public class AuthenticateRepo implements AuthenticateDataSource {

    private final CloudDataSource cloudDataSource;

    public AuthenticateRepo(CloudDataSource CloudDataSource) {

        cloudDataSource = CloudDataSource;
    }

    @Override
    public Single<LoginResponse> authenticate(String userName, String passWord, UserType userType) {
        return cloudDataSource.authenticate(userName, passWord, userType);
    }
}
