package com.example.contactus.feature.data.dataSource.repo;

import com.example.contactus.feature.data.dataSource.CloudDataSource;
import com.example.contactus.feature.data.entities.Token;

import io.reactivex.Single;

public class AuthenticateRepo implements AuthenticateDataSource {

    private final CloudDataSource cloudDataSource;

    public AuthenticateRepo(CloudDataSource CloudDataSource) {

        cloudDataSource = CloudDataSource;
    }

    @Override
    public Single<Token> authenticate(String userName, String passWord) {
        return cloudDataSource.authenticate(userName, passWord);
    }
}
