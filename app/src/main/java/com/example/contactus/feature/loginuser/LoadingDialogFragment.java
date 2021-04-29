package com.example.contactus.feature.loginuser;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.contactus.R;

public class LoadingDialogFragment extends DialogFragment {

    private AlertDialog loadingDialog;

    public AlertDialog getLoadingDialog() {
        return loadingDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.loading_dialog_layout, container);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        return rootView;
    }


}
