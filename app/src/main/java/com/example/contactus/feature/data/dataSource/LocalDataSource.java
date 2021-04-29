package com.example.contactus.feature.data.dataSource;

import com.example.contactus.feature.data.dataSource.repo.AuthenticateDataSource;
import com.example.contactus.feature.data.entities.Token;

import io.reactivex.Single;

public class LocalDataSource implements AuthenticateDataSource {


    @Override
    public Single<Token> authenticate(String userName, String passWord, UserType userType) {
        return null;
    }
}
