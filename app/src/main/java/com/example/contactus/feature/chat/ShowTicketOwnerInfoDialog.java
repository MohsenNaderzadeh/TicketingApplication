package com.example.contactus.feature.chat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.contactus.R;

public class ShowTicketOwnerInfoDialog extends DialogFragment {
    
    public static final String EXTRA_STD_NAME_KEY = "stdName";
    public static final String EXTRA_STD_NATIONAL_CODE_KEY = "stdNationalCode";
    public static final String EXTRA_STD_MAJOR_NAME_KEY = "stdMajorName";
    public static final String EXTRA_STD_MAJOR_GRADE_KEY = "stdMajorGrade";
    public static final String EXTRA_STD_UNIVERSITY_CODE_KEY = "stdUniversityCode";
    
    private String stdName;
    private String stdNationalCode;
    private String stdUniversityCode;
    private String stdMajorName;
    private String stdMajorGrade;
    
    public static ShowTicketOwnerInfoDialog newInstance(String stdName, String stdNationalCode, String stdUniversityCode, String stdMajorName, String stdMajorGrade) {
        
        Bundle args = new Bundle();
        args.putString(EXTRA_STD_NAME_KEY, stdName);
        args.putString(EXTRA_STD_NATIONAL_CODE_KEY, stdNationalCode);
        args.putString(EXTRA_STD_UNIVERSITY_CODE_KEY, stdUniversityCode);
        args.putString(EXTRA_STD_MAJOR_NAME_KEY, stdMajorName);
        args.putString(EXTRA_STD_MAJOR_GRADE_KEY, stdMajorGrade);
        ShowTicketOwnerInfoDialog fragment = new ShowTicketOwnerInfoDialog();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            stdName = getArguments().getString(EXTRA_STD_NAME_KEY);
            stdNationalCode = getArguments().getString(EXTRA_STD_NATIONAL_CODE_KEY);
            stdMajorName = getArguments().getString(EXTRA_STD_MAJOR_NAME_KEY);
            stdMajorGrade = getArguments().getString(EXTRA_STD_MAJOR_GRADE_KEY);
            stdUniversityCode = getArguments().getString(EXTRA_STD_UNIVERSITY_CODE_KEY);
        } else {
            dismiss();
        }
    }
    
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_user_info, null, false);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext())
                .setView(rootView);
        TextView dialog_user_info_userName_tv = rootView.findViewById(R.id.dialog_user_info_userName_tv);
        TextView dialog_user_info_nationalCode_tv = rootView.findViewById(R.id.dialog_user_info_nationalCode_tv);
        TextView dialog_user_info_universityCode_tv = rootView.findViewById(R.id.dialog_user_info_universityCode_tv);
        TextView dialog_user_info_majorName_tv = rootView.findViewById(R.id.dialog_user_info_majorName_tv);
        TextView dialog_user_info_majorgrade_tv = rootView.findViewById(R.id.dialog_user_info_majorgrade_tv);
        
        dialog_user_info_userName_tv.setText(stdName);
        dialog_user_info_nationalCode_tv.setText(stdNationalCode);
        dialog_user_info_majorName_tv.setText(stdMajorName);
        dialog_user_info_majorgrade_tv.setText(stdMajorGrade);
        dialog_user_info_universityCode_tv.setText(stdUniversityCode);
        
        return alertBuilder.create();
    }
}
