package com.example.contactus.feature.studentmain;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.contactus.R;
import com.example.contactus.feature.base.MSingleObserver;
import com.example.contactus.feature.base.MyTextWatcher;
import com.example.contactus.feature.data.api.ApiServiceProvider;
import com.example.contactus.feature.data.dataSource.RelatedAdministrativeDepartemantsCloudDataSource;
import com.example.contactus.feature.data.entities.RelatedDepartemants;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class AddNewTicketDialog extends DialogFragment implements AdapterView.OnItemSelectedListener {


    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    List<RelatedDepartemants> relatedDepartemantsList = new ArrayList<>();
    ArrayAdapter relativeDepartemants;
    RelatedDepartemants selectedDepartemants;
    String ticketTitle = "";
    Button btn_continue_ticket_creation;
    private OnTicketsCreationFirstStepCompeleted onTicketsCreationFirstStepCompeleted;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.add_new_ticket_dialog, null);
        Spinner relatedDepartemantSpinner = view.findViewById(R.id.main_add_new_ticket_dialog_ticket__related_department_sp);
        EditText main_add_new_ticket_dialog_ticket_subject_ed = view.findViewById(R.id.main_add_new_ticket_dialog_ticket_subject_ed);
        btn_continue_ticket_creation = view.findViewById(R.id.btn_continue_ticket_creation);
        relatedDepartemantSpinner.setOnItemSelectedListener(this);
        relatedDepartemantsList.add(new RelatedDepartemants("لطفا اداره مربوطه را انتخاب نمایید", -1));
        relatedDepartemantSpinner.setSelection(0);
        relatedDepartemantSpinner.setEnabled(false);
        RelatedAdministrativeDepartemantsCloudDataSource cloudDataSource = new RelatedAdministrativeDepartemantsCloudDataSource(ApiServiceProvider.getApiService());
        cloudDataSource.getAllSubjects()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MSingleObserver<List<RelatedDepartemants>>(compositeDisposable) {
                    @Override
                    public void onSuccess(List<RelatedDepartemants> ticketSubjects) {
                        relatedDepartemantsList.addAll(ticketSubjects);
                        relativeDepartemants = new ArrayAdapter(getContext(), R.layout.myspinnerlayout, relatedDepartemantsList);
                        relativeDepartemants.setDropDownViewResource(R.layout.myspinner_dropdown_item);
                        relatedDepartemantSpinner.setAdapter(relativeDepartemants);
                        relatedDepartemantSpinner.setEnabled(true);
                    }
                });

        main_add_new_ticket_dialog_ticket_subject_ed.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() > 0) {

                    ticketTitle = charSequence.toString();
                    if (selectedDepartemants != null)
                        btn_continue_ticket_creation.setEnabled(true);
                } else {
                    btn_continue_ticket_creation.setEnabled(false);
                }
            }
        });

        btn_continue_ticket_creation.setOnClickListener(view1 -> {

            if (onTicketsCreationFirstStepCompeleted != null) {
                onTicketsCreationFirstStepCompeleted.onTicketRelatedDepartemantAndTicketTitleRecieved(selectedDepartemants, ticketTitle);
                dismiss();
            }
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext()).setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        return alertDialog;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        compositeDisposable.clear();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if (relatedDepartemantsList.get(position).getDepartemantId() != -1) {
            selectedDepartemants = relatedDepartemantsList.get(position);
            if (ticketTitle.length() > 0)
                btn_continue_ticket_creation.setEnabled(true);
        } else {
            btn_continue_ticket_creation.setEnabled(false);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void setOnTicketsCreationFirstStepCompeleted(OnTicketsCreationFirstStepCompeleted onTicketsCreationFirstStepCompeleted) {
        this.onTicketsCreationFirstStepCompeleted = onTicketsCreationFirstStepCompeleted;
    }


    public interface OnTicketsCreationFirstStepCompeleted {
        void onTicketRelatedDepartemantAndTicketTitleRecieved(RelatedDepartemants selectedDepartemant, String TicketTitle);
    }
}
