package com.example.contactus.feature.supporterlogin;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.contactus.R;
import com.example.contactus.feature.base.MyTextWatcher;
import com.example.contactus.feature.base.ObserverActivity;

public class SupporterLoginActivity extends ObserverActivity {

    private View Supporter_Login_back_iv;
    private EditText Supporter_Username_ed_login, Supporter_password_ed_login;
    private Button Supporter_login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supporter_login);
    }

    @Override
    public void observe() {

    }

    @Override
    public void setUpViews() {
        Supporter_Login_back_iv = findViewById(R.id.Supporter_Login_back_iv);
        Supporter_Username_ed_login = findViewById(R.id.Supporter_Username_ed_login);
        Supporter_password_ed_login = findViewById(R.id.Supporter_password_ed_login);
        Supporter_login_btn = findViewById(R.id.Supporter_login_btn);

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
}