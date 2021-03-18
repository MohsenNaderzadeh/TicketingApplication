package com.example.contactus.feature.main;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contactus.R;
import com.example.contactus.feature.base.MyAnimationListener;
import com.example.contactus.feature.base.MyTextWatcher;
import com.example.contactus.feature.base.ObserverActivity;
import com.example.contactus.feature.data.DataFakeGenerator;
import com.example.contactus.feature.data.Ticket;
import com.example.contactus.feature.eventbusevents.ConnectedInternet;
import com.example.contactus.feature.eventbusevents.DisConnectedInternet;
import com.example.contactus.feature.main.adapter.TicketListAdapter;
import com.example.contactus.feature.base.OnRvItemsClickListener;
import com.example.contactus.feature.receviers.InternetConnectionBroadCastReciever;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends ObserverActivity implements OnRvItemsClickListener<Ticket> {

    private RecyclerView main_tickets_rv;
    private View main_add_New_Ticket_floatingBtn;
    private TicketListAdapter ticketListAdapter;
    private ImageView searchIcon;
    private EditText search_query_ed;
    private TextView toolbar_title;

    private boolean isInSearchMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void setUpViews() {
        main_tickets_rv = findViewById(R.id.main_tickets_rv);
        main_add_New_Ticket_floatingBtn = findViewById(R.id.main_add_New_Ticket_floatingBtn);
        main_add_New_Ticket_floatingBtn.bringToFront();
        searchIcon = findViewById(R.id.searchIcon);
        search_query_ed = findViewById(R.id.search_query_ed);
        toolbar_title=findViewById(R.id.toolbar_title);

    }

    @Override
    public void observe() {
        ticketListAdapter = new TicketListAdapter(DataFakeGenerator.getTicketsList());
        ticketListAdapter.setOnRvItemsClickListener(this);
        main_tickets_rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        main_tickets_rv.setAdapter(ticketListAdapter);
        searchIcon.setOnClickListener(view -> {
            if (!isInSearchMode) {
                isInSearchMode = true;
                final Animation anim_out = AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_out);
                final Animation anim_in = AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_in);
                anim_out.setAnimationListener(new MyAnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        searchIcon.setImageResource(R.drawable.ic_baseline_arrow_back_left_24);
                        search_query_ed.setVisibility(View.VISIBLE);
                        searchIcon.startAnimation(anim_in);
                        search_query_ed.startAnimation(anim_in);
                    }
                });
                searchIcon.startAnimation(anim_out);
                search_query_ed.startAnimation(anim_out);
                search_query_ed.addTextChangedListener(new MyTextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        ticketListAdapter.getFilter().filter(charSequence.toString());
                    }
                });

            } else {
                isInSearchMode = false;
                search_query_ed.setText("");
                final Animation anim_out = AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_out);
                final Animation anim_in = AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_in);
                anim_in.setAnimationListener(new MyAnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        searchIcon.setImageResource(R.drawable.ic_baseline_search_24);
                        search_query_ed.setVisibility(View.INVISIBLE);
                        searchIcon.startAnimation(anim_out);
                        search_query_ed.startAnimation(anim_out);
                    }
                });

                searchIcon.startAnimation(anim_in);
                search_query_ed.startAnimation(anim_in);
            }


        });
        main_add_New_Ticket_floatingBtn.setOnClickListener(view -> {
            AddNewTicketDialog addNewTicketDialog = new AddNewTicketDialog();
            addNewTicketDialog.show(getSupportFragmentManager(), null);
        });
    }

    @Override
    public void OnItemClicked(Ticket item, int position) {
        Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        if (isInSearchMode) {
            isInSearchMode = false;
            search_query_ed.setText("");
            final Animation anim_out = AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_out);
            final Animation anim_in = AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_in);
            anim_in.setAnimationListener(new MyAnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    searchIcon.setImageResource(R.drawable.ic_baseline_search_24);
                    search_query_ed.setVisibility(View.INVISIBLE);
                    searchIcon.startAnimation(anim_out);
                    search_query_ed.startAnimation(anim_out);
                }
            });
            searchIcon.startAnimation(anim_in);
            search_query_ed.startAnimation(anim_in);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setTextofToolbar(ConnectedInternet connectedInternet) {
        updateToolbarText("دانشجویار");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setTextofToolbar(DisConnectedInternet disConnectedInternet) {
        updateToolbarText("در حال اتصال ...");

    }


    private void updateToolbarText(String title){
        toolbar_title.setText(title);
    }


}