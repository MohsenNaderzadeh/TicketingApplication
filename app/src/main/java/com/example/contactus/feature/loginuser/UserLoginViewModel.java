package com.example.contactus.feature.loginuser;

import android.content.Context;

import com.example.contactus.feature.base.BaseViewModel;
import com.example.contactus.feature.data.TokenContainer;
import com.example.contactus.feature.data.dataSource.UserInfoManager;
import com.example.contactus.feature.data.dataSource.repo.AuthenticateDataSource;
import com.example.contactus.feature.data.dataSource.repo.AuthenticateRepo;
import com.example.contactus.feature.data.entities.Token;

import io.reactivex.Completable;
import io.reactivex.Single;


public class UserLoginViewModel extends BaseViewModel {

    private final AuthenticateRepo authenticateRepo;
    private final Context context;


    public UserLoginViewModel(AuthenticateRepo authenticateRepo, Context context) {
        this.authenticateRepo = authenticateRepo;
        this.context = context;
    }

    public Completable authenticate(String userName, String password) {
        shouldShowProgressBar.onNext(true);
        Single<Token> tokenReq;
        tokenReq = authenticateRepo.authenticate(userName, password, AuthenticateDataSource.UserType.USER);
        return tokenReq.doOnSuccess(token -> {
            UserInfoManager userInfoManager = new UserInfoManager(context);
            userInfoManager.setTokenInSharedPref(token.getToken());
            userInfoManager.setIsUserInSharedPref(true);
            TokenContainer.updateToken(token.getToken());
            TokenContainer.setIsUser(true);
            userInfoManager.setIsUserInSharedPref(true);
        }).doOnEvent((token, throwable) -> {
            shouldShowProgressBar.onNext(false);
        }).ignoreElement();
    }


}
