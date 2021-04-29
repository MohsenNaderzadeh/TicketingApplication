package com.example.contactus.feature.loginuser;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.contactus.R;
import com.example.contactus.feature.ForgetPassword.ForgetPasswordActivity;
import com.example.contactus.feature.base.MyCompletableObserver;
import com.example.contactus.feature.base.MyTextWatcher;
import com.example.contactus.feature.base.ObserverActivity;
import com.example.contactus.feature.data.api.ApiServiceProvider;
import com.example.contactus.feature.data.dataSource.CloudDataSource;
import com.example.contactus.feature.data.dataSource.repo.AuthenticateRepo;
import com.example.contactus.feature.supporterlogin.SupporterLoginActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class UserLoginActivity extends ObserverActivity {

    private static final String TAG = "LoginActivity";
    UserLoginViewModel userLoginViewModel;
    EditText username_ed_login;
    EditText password_ed_login;
    Button btn_login;
    LoadingDialogFragment loadingDialogFragment;
    private View UserForgetPassword_tv, Supporterlogintv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void observe() {

        btn_login.setOnClickListener(view -> {
            userLoginViewModel.authenticate(username_ed_login.getText().toString(), password_ed_login.getText().toString())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new MyCompletableObserver(compositeDisposable) {
                        @Override
                        public void onComplete() {


                        }
                    });
        });


        compositeDisposable.add(
                userLoginViewModel.getShouldShowProgressBar()
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(aBoolean -> {
                            Log.i("progress", aBoolean.toString());
                            if (aBoolean)
                                loadingDialogFragment.show(getSupportFragmentManager(), null);
                            else loadingDialogFragment.dismiss();
                        }, throwable -> {
                            loadingDialogFragment.dismiss();
                        }));


    }

    @Override
    public void setUpViews() {
        userLoginViewModel = new UserLoginViewModel(new AuthenticateRepo(new CloudDataSource(ApiServiceProvider.getApiService())), this);
        username_ed_login = findViewById(R.id.username_ed_login);
        password_ed_login = findViewById(R.id.password_ed_login);
        btn_login = findViewById(R.id.btn_login);
        UserForgetPassword_tv = findViewById(R.id.UserForgetPassword_tv);
        Supporterlogintv = findViewById(R.id.Supporterlogintv);
        loadingDialogFragment = new LoadingDialogFragment();
        username_ed_login.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                btn_login.setEnabled(editable.length() > 0 && password_ed_login.getText().toString().length() > 0);
            }
        });
        password_ed_login.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                btn_login.setEnabled(editable.length() > 0 && username_ed_login.getText().toString().length() > 0);
            }
        });

        UserForgetPassword_tv.setOnClickListener(view -> {
            Intent forgetPasswordIntent = new Intent(UserLoginActivity.this, ForgetPasswordActivity.class);
            startActivity(forgetPasswordIntent);
        });

        Supporterlogintv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgetPasswordIntent = new Intent(UserLoginActivity.this, SupporterLoginActivity.class);
                startActivity(forgetPasswordIntent);
            }
        });
    }
}