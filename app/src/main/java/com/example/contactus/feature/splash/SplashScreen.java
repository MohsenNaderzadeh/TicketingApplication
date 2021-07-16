package com.example.contactus.feature.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.contactus.R;
import com.example.contactus.feature.data.TokenContainer;
import com.example.contactus.feature.data.dataSource.LocalDataSource;
import com.example.contactus.feature.data.sharedPrefrences.SharedPrefManagerSingletone;
import com.example.contactus.feature.studentlogin.UserLoginActivity;
import com.example.contactus.feature.studentmain.TicketsListActivity;
import com.example.contactus.feature.supportermain.SupporterMainAcitivity;

public class SplashScreen extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        LocalDataSource localDataSource = new LocalDataSource(SharedPrefManagerSingletone.getSharedPrefrencesManager(this));
        new Handler().postDelayed(new Runnable() {
            
            // Using handler with postDelayed called runnable run method
            
            @Override
            
            public void run() {
                Log.i("s", "run: " + localDataSource.getISLoggedInStat());
                Log.i("s", "run: " + localDataSource.isStudent());
                Log.i("s", "run: " + localDataSource.isSupporter());
                Log.i("s", "run: " + localDataSource.getToken());
                if (localDataSource.getISLoggedInStat() && !localDataSource.isStudent() && localDataSource.isSupporter() && localDataSource.getToken() != null) {
                    Intent intent = new Intent(SplashScreen.this, SupporterMainAcitivity.class);
                    TokenContainer.updateToken(localDataSource.getToken());
                    TokenContainer.setIsStudent(false);
                    TokenContainer.setIsSupporter(true);
                    finish();
                    startActivity(intent);
                    
                } else if (localDataSource.getISLoggedInStat() && !localDataSource.isSupporter() && localDataSource.isStudent() && localDataSource.getToken() != null) {
                    Intent intent = new Intent(SplashScreen.this, TicketsListActivity.class);
                    Log.i("token", "run: " + localDataSource.getToken());
                    TokenContainer.updateToken(localDataSource.getToken());
                    TokenContainer.setIsStudent(true);
                    TokenContainer.setIsSupporter(false);
                    finish();
                    startActivity(intent);
                    
                } else {
                    finish();
                    Intent intent = new Intent(SplashScreen.this, UserLoginActivity.class);
                    startActivity(intent);
                }
                
            }
            
        }, 5000); // wait for 5 seconds
    }
}