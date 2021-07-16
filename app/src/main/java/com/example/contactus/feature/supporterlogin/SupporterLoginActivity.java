package com.example.contactus.feature.supporterlogin;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.contactus.R;
import com.example.contactus.feature.base.MSingleObserver;
import com.example.contactus.feature.base.MyTextWatcher;
import com.example.contactus.feature.base.ObserverActivity;
import com.example.contactus.feature.data.TokenContainer;
import com.example.contactus.feature.data.api.ApiServiceProvider;
import com.example.contactus.feature.data.dataSource.AuthenticationCloudDataSource;
import com.example.contactus.feature.data.entities.LoginResponse;
import com.example.contactus.feature.data.sharedPrefrences.SharedPrefrencesManager;
import com.example.contactus.feature.supportermain.SupporterMainAcitivity;
import com.example.contactus.feature.view.ErrorDialogFragment;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

public class SupporterLoginActivity extends ObserverActivity {

    private View Supporter_Login_back_iv;
    private EditText Supporter_Username_ed_login, Supporter_password_ed_login;
    private Button Supporter_login_btn;
    private SupporterLoginViewModel supporterLoginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supporter_login);
    }

    @Override
    public void observe() {

        Supporter_login_btn.setOnClickListener(view -> {
            supporterLoginViewModel.authenticate(Supporter_Username_ed_login.getText().toString(), Supporter_password_ed_login.getText().toString())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new MSingleObserver<LoginResponse>(compositeDisposable) {
                        @Override
                        public void onSuccess(LoginResponse loginResponse) {
                            if (loginResponse.isSuccess()) {
                                SharedPrefrencesManager sharedPrefrencesManager = new SharedPrefrencesManager(SupporterLoginActivity.this);
                                sharedPrefrencesManager.setTokenInSharedPref(loginResponse.getToken());
                                sharedPrefrencesManager.setIsStudentInSharedPref(false);
                                sharedPrefrencesManager.setIsSupporter(true);
                                sharedPrefrencesManager.setIsLoggedIn(true);
                                TokenContainer.setIsSupporter(true);
                                TokenContainer.setIsStudent(false);
                                TokenContainer.updateToken(loginResponse.getToken());
    
                                Intent ticketsListActivity = new Intent(SupporterLoginActivity.this, SupporterMainAcitivity.class);
                                finish();
                                startActivity(ticketsListActivity);
    
                                loadingDialogFragment.dismiss();
    
    
                            } else {
                                loadingDialogFragment.dismiss();
                                ErrorDialogFragment errorDialogFragment = ErrorDialogFragment.newInstance(loginResponse.getErrorMessage());
                                errorDialogFragment.show(getSupportFragmentManager(), null);
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            super.onError(e);
                            loadingDialogFragment.dismiss();
                            ErrorDialogFragment errorDialogFragment = ErrorDialogFragment.newInstance("خطای ناشناخته");
                            errorDialogFragment.show(getSupportFragmentManager(), null);
                        }
                    });
        });


        compositeDisposable.add(
                supporterLoginViewModel.getShouldShowProgressBar()
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(aBoolean -> {
                            if (aBoolean)
                                loadingDialogFragment.show(getSupportFragmentManager(), null);
                            else loadingDialogFragment.dismiss();
                        }, throwable -> {
                            loadingDialogFragment.dismiss();
                        }));
    }

    @Override
    public void setUpViews() {
        Supporter_Login_back_iv = findViewById(R.id.Supporter_Login_back_iv);
        Supporter_Username_ed_login = findViewById(R.id.Supporter_Username_ed_login);
        Supporter_password_ed_login = findViewById(R.id.Supporter_password_ed_login);
        Supporter_login_btn = findViewById(R.id.Supporter_login_btn);
        supporterLoginViewModel = new SupporterLoginViewModel(new AuthenticationCloudDataSource(ApiServiceProvider.getApiService()), this);
        Supporter_Username_ed_login.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                Supporter_login_btn.setEnabled(editable.length() > 0 && Supporter_password_ed_login.getText().toString().length() > 0);
            }
        });
        Supporter_password_ed_login.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                Supporter_login_btn.setEnabled(editable.length() > 0 && Supporter_password_ed_login.getText().toString().length() > 0);
            }
        });

        Supporter_login_btn.setOnClickListener(view -> {

        });

        Supporter_Login_back_iv.setOnClickListener(view -> onBackPressed());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}