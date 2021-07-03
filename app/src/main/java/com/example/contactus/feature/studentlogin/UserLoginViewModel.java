package com.example.contactus.feature.studentlogin;

import android.content.Context;

import com.example.contactus.feature.base.BaseViewModel;
import com.example.contactus.feature.data.dataSource.repo.AuthenticateDataSource;
import com.example.contactus.feature.data.dataSource.repo.AuthenticateRepo;
import com.example.contactus.feature.data.entities.LoginResponse;

import io.reactivex.Single;


public class UserLoginViewModel extends BaseViewModel {

    private final AuthenticateRepo authenticateRepo;
    private final Context context;


    public UserLoginViewModel(AuthenticateRepo authenticateRepo, Context context) {
        this.authenticateRepo = authenticateRepo;
        this.context = context;
    }

    public Single<LoginResponse> authenticate(String userName, String password) {
        shouldShowProgressBar.onNext(true);
        return authenticateRepo.authenticate(userName, password, AuthenticateDataSource.UserType.USER).doOnEvent((loginResponse, throwable) -> shouldShowProgressBar.onNext(false));
    }


}
