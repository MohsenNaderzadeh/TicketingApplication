package com.example.contactus.feature.supporterlogin;

import android.content.Context;

import com.example.contactus.feature.base.BaseViewModel;
import com.example.contactus.feature.data.TokenContainer;
import com.example.contactus.feature.data.dataSource.CloudDataSource;
import com.example.contactus.feature.data.dataSource.UserInfoManager;
import com.example.contactus.feature.data.dataSource.repo.AuthenticateDataSource;
import com.example.contactus.feature.data.entities.Token;

import io.reactivex.Completable;
import io.reactivex.Single;

public class SupporterLoginViewModel extends BaseViewModel {

    private final CloudDataSource cloudDataSource;
    private final Context context;

    public SupporterLoginViewModel(CloudDataSource cloudDataSource, Context context) {
        this.cloudDataSource = cloudDataSource;
        this.context = context;
    }

    public Completable authenticate(String userName, String password) {
        shouldShowProgressBar.onNext(true);
        Single<Token> tokenReq;
        tokenReq = cloudDataSource.authenticate(userName, password, AuthenticateDataSource.UserType.SUPPORTER);
        return tokenReq.doOnSuccess(token -> {
            UserInfoManager userInfoManager = new UserInfoManager(context);
            userInfoManager.setTokenInSharedPref(token.getToken());
            userInfoManager.setIsUserInSharedPref(false);
            userInfoManager.setIsUserInSharedPref(true);
            TokenContainer.setIsUser(false);
            TokenContainer.updateToken(token.getToken());
        }).doOnEvent((token, throwable) -> {
            shouldShowProgressBar.onNext(false);
        }).ignoreElement();
    }
}
