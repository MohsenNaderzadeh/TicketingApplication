package com.example.contactus.feature;

import android.app.Application;

import com.example.contactus.feature.data.TokenContainer;
import com.example.contactus.feature.data.dataSource.LocalDataSource;
import com.example.contactus.feature.data.sharedPrefrences.SharedPrefManagerSingletone;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LocalDataSource localDataSource = new LocalDataSource(SharedPrefManagerSingletone.getSharedPrefrencesManager(this));
        TokenContainer.updateToken(localDataSource.getToken());
        TokenContainer.setIsStudent(localDataSource.isStudent());
        TokenContainer.setIsSupporter(localDataSource.isSupporter());
    }
}
