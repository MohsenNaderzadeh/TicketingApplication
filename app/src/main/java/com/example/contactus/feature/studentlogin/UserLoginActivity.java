package com.example.contactus.feature.studentlogin;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.contactus.R;
import com.example.contactus.feature.ForgetPassword.ForgetPasswordActivity;
import com.example.contactus.feature.base.MSingleObserver;
import com.example.contactus.feature.base.MyTextWatcher;
import com.example.contactus.feature.base.ObserverActivity;
import com.example.contactus.feature.data.TokenContainer;
import com.example.contactus.feature.data.api.ApiServiceProvider;
import com.example.contactus.feature.data.dataSource.AuthenticationCloudDataSource;
import com.example.contactus.feature.data.dataSource.UserInfoManager;
import com.example.contactus.feature.data.dataSource.repo.AuthenticateRepo;
import com.example.contactus.feature.data.entities.LoginResponse;
import com.example.contactus.feature.eventbusevents.ConnectedInternet;
import com.example.contactus.feature.eventbusevents.DisConnectedInternet;
import com.example.contactus.feature.studentmain.TicketsListActivity;
import com.example.contactus.feature.supporterlogin.SupporterLoginActivity;
import com.example.contactus.feature.view.ErrorDialogFragment;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;


public class UserLoginActivity extends ObserverActivity {

    private static final String TAG = "LoginActivity";
    UserLoginViewModel userLoginViewModel;
    EditText username_ed_login;
    EditText password_ed_login;
    Button btn_login;
    private View UserForgetPassword_tv, Supporterlogintv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void observe() {
        btn_login.setOnClickListener(view -> {
            loadingDialogFragment.show(getSupportFragmentManager(), null);
            userLoginViewModel.authenticate(username_ed_login.getText().toString(), password_ed_login.getText().toString())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new MSingleObserver<LoginResponse>(compositeDisposable) {
                        @Override
                        public void onSuccess(@NonNull LoginResponse loginResponse) {

                            if (loginResponse.isSuccess()) {
                                UserInfoManager userInfoManager = new UserInfoManager(UserLoginActivity.this);
                                userInfoManager.setTokenInSharedPref(loginResponse.getToken());
                                userInfoManager.setIsUserInSharedPref(true);
                                TokenContainer.setIsUser(true);
                                TokenContainer.updateToken(loginResponse.getToken());

                                Intent ticketsListActivity = new Intent(UserLoginActivity.this, TicketsListActivity.class);
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


    }

    @Override
    public void setUpViews() {
        userLoginViewModel = new UserLoginViewModel(new AuthenticateRepo(new AuthenticationCloudDataSource(ApiServiceProvider.getApiService())), this);
        username_ed_login = findViewById(R.id.username_ed_login);
        password_ed_login = findViewById(R.id.password_ed_login);
        btn_login = findViewById(R.id.btn_login);
        UserForgetPassword_tv = findViewById(R.id.UserForgetPassword_tv);
        Supporterlogintv = findViewById(R.id.Supporterlogintv);
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

    @Override
    public void setTextofToolbar(ConnectedInternet connectedInternet) {
        super.setTextofToolbar(connectedInternet);

    }

    @Override
    public void setTextofToolbar(DisConnectedInternet disConnectedInternet) {
        super.setTextofToolbar(disConnectedInternet);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}