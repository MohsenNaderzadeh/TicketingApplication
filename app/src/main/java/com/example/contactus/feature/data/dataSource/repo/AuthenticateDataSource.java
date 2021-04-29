package com.example.contactus.feature.data.dataSource.repo;

import com.example.contactus.feature.data.entities.Token;

import io.reactivex.Single;

public interface AuthenticateDataSource {

    Single<Token> authenticate(String userName, String passWord);

}
