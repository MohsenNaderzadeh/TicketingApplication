package com.example.contactus.feature.loginuser;

import android.content.Context;

import com.example.contactus.feature.data.TokenContainer;
import com.example.contactus.feature.data.dataSource.UserInfoManager;
import com.example.contactus.feature.data.dataSource.repo.AuthenticateDataSource;
import com.example.contactus.feature.data.dataSource.repo.AuthenticateRepo;
import com.example.contactus.feature.data.entities.Token;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.subjects.BehaviorSubject;


public class UserLoginViewModel {

    private final AuthenticateRepo authenticateRepo;
    private final Context context;


    private final BehaviorSubject<Boolean> shouldShowProgressBar = BehaviorSubject.create();

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
        }).doOnEvent((token, throwable) -> {
            shouldShowProgressBar.onNext(false);
        }).ignoreElement();
    }

    public BehaviorSubject<Boolean> getShouldShowProgressBar() {
        return shouldShowProgressBar;
    }

}
