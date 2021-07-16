package com.example.contactus.feature.data.dataSource;

import com.example.contactus.feature.data.dataSource.repo.AuthenticateDataSource;
import com.example.contactus.feature.data.dataSource.repo.SharedPrefrencesDataSource;
import com.example.contactus.feature.data.entities.LoginResponse;
import com.example.contactus.feature.data.entities.LogoutResponse;
import com.example.contactus.feature.data.sharedPrefrences.SharedPrefrencesManager;

import io.reactivex.Single;

public class LocalDataSource implements AuthenticateDataSource, SharedPrefrencesDataSource {
    
    
    private final SharedPrefrencesManager sharedPrefrencesManager;
    
    public LocalDataSource(SharedPrefrencesManager sharedPrefrencesManager) {
        this.sharedPrefrencesManager = sharedPrefrencesManager;
    }
    
    
    @Override
    public Single<LoginResponse> authenticate(String userName, String passWord, UserType userType) {
        return null;
    }
    
    @Override
    public Single<LogoutResponse> logout(UserType userType) {
        return null;
    }
    
    
    @Override
    public void updateToken(String token) {
    
    }
    
    @Override
    public String getToken() {
        return sharedPrefrencesManager.getToken();
    }
    
    @Override
    public boolean isStudent() {
        return sharedPrefrencesManager.isStudent();
    }
    
    @Override
    public boolean isSupporter() {
        return sharedPrefrencesManager.isSupporter();
    }
    
    @Override
    public void setIsUser(boolean isUser) {
        sharedPrefrencesManager.setIsStudentInSharedPref(isUser);
    }
    
    @Override
    public void setIsLoggedIn(boolean isLoggedInStat) {
        sharedPrefrencesManager.setIsLoggedIn(isLoggedInStat);
    }
    
    @Override
    public boolean getISLoggedInStat() {
        return sharedPrefrencesManager.getLoginStatus();
    }
    
    @Override
    public void clear() {
        sharedPrefrencesManager.clear();
    }
}
