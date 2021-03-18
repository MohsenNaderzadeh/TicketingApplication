package com.example.contactus.feature.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.contactus.R;

public class AddNewTicketDialog extends DialogFragment {


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View view= LayoutInflater.from(getContext()).inflate(R.layout.add_new_ticket_dialog,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(new ContextThemeWrapper(getContext(),R.style.Theme2)).setView(view);
        AlertDialog alertDialog=builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        return alertDialog;
    }
}
