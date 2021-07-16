package com.example.contactus.feature.data.dataSource.repo;

public interface SharedPrefrencesDataSource {
    
    void updateToken(String token);
    
    String getToken();
    
    boolean isStudent();
    
    boolean isSupporter();
    
    void setIsUser(boolean isUser);
    
    void setIsLoggedIn(boolean isLoggedInStat);
    
    boolean getISLoggedInStat();
    
    void clear();
    
}
