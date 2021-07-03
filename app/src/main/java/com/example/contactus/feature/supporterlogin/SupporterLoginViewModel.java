package com.example.contactus.feature.supporterlogin;

import android.content.Context;

import com.example.contactus.feature.base.BaseViewModel;
import com.example.contactus.feature.data.dataSource.AuthenticationCloudDataSource;
import com.example.contactus.feature.data.dataSource.repo.AuthenticateDataSource;
import com.example.contactus.feature.data.entities.LoginResponse;

import io.reactivex.Single;

public class SupporterLoginViewModel extends BaseViewModel {

    private final AuthenticationCloudDataSource cloudDataSource;
    private final Context context;

    public SupporterLoginViewModel(AuthenticationCloudDataSource cloudDataSource, Context context) {
        this.cloudDataSource = cloudDataSource;
        this.context = context;
    }

    public Single<LoginResponse> authenticate(String userName, String password) {
        shouldShowProgressBar.onNext(true);
        return cloudDataSource.authenticate(userName, password, AuthenticateDataSource.UserType.SUPPORTER);
    }
}
