package com.example.contactus.feature.data.sharedPrefrences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefrencesManager {
    private static final String EXTRA_KEY_TOKEN = "userToken";
    private static final String EXTRA_KEY_IS_STUDENT = "is_Student";
    private static final String EXTRA_KEY_IS_SUPPORTER = "is_Supporter";
    private static final String EXTRA_KEY_IS_LOGGED_IN = "is_loggedIn";
    private final SharedPreferences sharedPreferences;
    
    public SharedPrefrencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
    }
    
    
    public void setTokenInSharedPref(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EXTRA_KEY_TOKEN, token);
        editor.apply();
    }
    
    public void setIsStudentInSharedPref(boolean isUser) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(EXTRA_KEY_IS_STUDENT, isUser);
        editor.apply();
    }
    
    public void setIsLoggedIn(boolean isLoggedInStat) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(EXTRA_KEY_IS_LOGGED_IN, isLoggedInStat);
        editor.apply();
    }
    
    public void setIsSupporter(boolean isStudent) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(EXTRA_KEY_IS_SUPPORTER, isStudent);
        editor.apply();
    }
    
    public boolean getLoginStatus() {
        return sharedPreferences.getBoolean(EXTRA_KEY_IS_LOGGED_IN, false);
    }
    
    public void clear() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EXTRA_KEY_TOKEN, null);
        editor.putBoolean(EXTRA_KEY_IS_LOGGED_IN, false);
        editor.putBoolean(EXTRA_KEY_IS_STUDENT, false);
        editor.putBoolean(EXTRA_KEY_IS_SUPPORTER, false);
        editor.apply();
    }
    
    public String getToken() {
        return sharedPreferences.getString(EXTRA_KEY_TOKEN, null);
    }
    
    public boolean isStudent() {
        return sharedPreferences.getBoolean(EXTRA_KEY_IS_STUDENT, false);
    }
    
    public boolean isSupporter() {
        return sharedPreferences.getBoolean(EXTRA_KEY_IS_SUPPORTER, false);
    }
    
    
}
