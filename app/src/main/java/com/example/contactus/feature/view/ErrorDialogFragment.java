package com.example.contactus.feature.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.contactus.R;

public class ErrorDialogFragment extends DialogFragment {

    private static final String EXTRA_KEY_ERROR_MESSAGE = "errorMessage";
    private String errorMessage;

    private ErrorDialogFragment() {

    }

    public static ErrorDialogFragment newInstance(String errorMessage) {

        Bundle args = new Bundle();
        args.putString(EXTRA_KEY_ERROR_MESSAGE, errorMessage);
        ErrorDialogFragment fragment = new ErrorDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getArguments() != null) {
            errorMessage = getArguments().getString(EXTRA_KEY_ERROR_MESSAGE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.error_dialog_layout, null);
        TextView errorMessageTv = rootView.findViewById(R.id.errorMessageTv);
        errorMessageTv.setText(errorMessage);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        return rootView;
    }
}
