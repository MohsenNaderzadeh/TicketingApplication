package com.example.contactus.feature.data.sharedPrefrences;

import android.content.Context;

public class SharedPrefManagerSingletone {
    private static SharedPrefrencesManager sharedPrefrencesManager;
    
    public static SharedPrefrencesManager getSharedPrefrencesManager(Context contex) {
        if (sharedPrefrencesManager == null) {
            return sharedPrefrencesManager = new SharedPrefrencesManager(contex);
        }
        return sharedPrefrencesManager;
    }
}
