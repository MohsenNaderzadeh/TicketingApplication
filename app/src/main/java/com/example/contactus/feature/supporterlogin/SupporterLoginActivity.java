package com.example.contactus.feature.supporterlogin;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.contactus.R;
import com.example.contactus.feature.base.MyCompletableObserver;
import com.example.contactus.feature.base.MyTextWatcher;
import com.example.contactus.feature.base.ObserverActivity;
import com.example.contactus.feature.data.api.ApiServiceProvider;
import com.example.contactus.feature.data.dataSource.CloudDataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
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
                    .subscribe(new MyCompletableObserver(compositeDisposable) {
                        @Override
                        public void onComplete() {


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
        supporterLoginViewModel = new SupporterLoginViewModel(new CloudDataSource(ApiServiceProvider.getApiService()), this);
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