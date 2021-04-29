package com.example.contactus.feature.data.dataSource;

import android.content.Context;
import android.content.SharedPreferences;

public class UserInfoManager {
    private static final String EXTRA_KEY_TOKEN = "userToken";
    private static final String EXTRA_KEY_IS_USER = "is_user";
    private final SharedPreferences sharedPreferences;

    public UserInfoManager(Context context) {
        sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
    }


    public void setTokenInSharedPref(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EXTRA_KEY_TOKEN, token);
        editor.apply();
    }

    public void setIsUserInSharedPref(boolean isUser) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(EXTRA_KEY_IS_USER, isUser);
        editor.apply();
    }

    public String getToken() {
        return sharedPreferences.getString(EXTRA_KEY_TOKEN, "");
    }

    public boolean isUser() {
        return sharedPreferences.getBoolean(EXTRA_KEY_IS_USER, true);
    }


}
