package com.example.contactus.feature;

import android.app.Application;

import com.example.contactus.feature.data.TokenContainer;
import com.example.contactus.feature.data.dataSource.UserInfoManager;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UserInfoManager userInfoManager = new UserInfoManager(this);
        TokenContainer.updateToken(userInfoManager.getToken());
        TokenContainer.setIsUser(userInfoManager.isUser());
    }
}
