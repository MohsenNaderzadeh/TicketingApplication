package com.example.contactus.feature.ForgetPassword;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.contactus.R;
import com.example.contactus.feature.base.MyTextWatcher;
import com.example.contactus.feature.base.ObserverActivity;

public class ForgetPasswordActivity extends ObserverActivity {


    private EditText user_email_ed;
    private Button resetPasswordBtn;

    private View back_iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);


    }

    @Override
    public void observe() {

    }

    @Override
    public void setUpViews() {
        user_email_ed = findViewById(R.id.user_email_ed);
        resetPasswordBtn = findViewById(R.id.resetPasswordBtn);
        user_email_ed.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                resetPasswordBtn.setEnabled(editable.length() > 0);
            }
        });
        back_iv = findViewById(R.id.back_iv);
        back_iv.setOnClickListener(view -> onBackPressed());
    }
}