package com.example.contactus.feature.base;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseActivity extends AppCompatActivity {

    public abstract  void setUpViews();


    @Override
    protected void onStart() {
        super.onStart();
        setUpViews();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }
}
