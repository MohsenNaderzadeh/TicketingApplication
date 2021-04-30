package com.example.contactus.feature.base;

import androidx.appcompat.app.AppCompatActivity;

import com.example.contactus.feature.loginuser.LoadingDialogFragment;

public abstract class BaseActivity extends AppCompatActivity {

    public abstract  void setUpViews();

    protected LoadingDialogFragment loadingDialogFragment = new LoadingDialogFragment();

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
