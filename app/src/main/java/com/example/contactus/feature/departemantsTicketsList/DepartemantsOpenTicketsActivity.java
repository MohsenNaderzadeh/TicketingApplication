package com.example.contactus.feature.departemantsTicketsList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ethanhua.skeleton.RecyclerViewSkeletonScreen;
import com.ethanhua.skeleton.Skeleton;
import com.example.contactus.R;
import com.example.contactus.feature.base.MSingleObserver;
import com.example.contactus.feature.base.ObserverActivity;
import com.example.contactus.feature.base.OnRvItemsClickListener;
import com.example.contactus.feature.chat.ChatActivity;
import com.example.contactus.feature.data.api.ApiServiceProvider;
import com.example.contactus.feature.data.dataSource.DepartemantsOpenTicketCloudDataSource;
import com.example.contactus.feature.data.entities.DepartemantOpenTicketsResponse;
import com.example.contactus.feature.data.entities.TicketInfo;
import com.example.contactus.feature.departemantsTicketsList.adapter.DepartemantsOpenTicketsListAdapter;
import com.example.contactus.feature.eventbusevents.ConnectedInternet;
import com.example.contactus.feature.eventbusevents.DisConnectedInternet;
import com.example.contactus.feature.studentmain.TicketsListActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DepartemantsOpenTicketsActivity extends ObserverActivity implements OnRvItemsClickListener<TicketInfo> {
    
    private TextView main_supporter_departemants_openTickets_toolbar_title;
    private RecyclerView departemants_openticketsList_rv;
    private DepartemantsOpenTicketsListAdapter departemantsOpenTicketsListAdapter;
    private RecyclerViewSkeletonScreen departemantsTicketsViewSkeleton;
    private DepartemantsOpenTicketsViewModel departemantsOpenTicketsViewModel;
    private TextView supporter_departemantsTickets_EmptyState_tv;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departemants_open_tickets);
    }
    
    @Override
    public void observe() {
        departemantsOpenTicketsViewModel.getll()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MSingleObserver<DepartemantOpenTicketsResponse>(compositeDisposable) {
                    @Override
                    public void onSuccess(DepartemantOpenTicketsResponse departemantOpenTicketsResponse) {
                        if (departemantOpenTicketsResponse.isSuccess()) {
                            if (departemantOpenTicketsResponse.getTicketsList().size() > 0) {
                                departemantsOpenTicketsListAdapter.setItems(departemantOpenTicketsResponse.getTicketsList());
                            } else {
                                supporter_departemantsTickets_EmptyState_tv.setVisibility(View.VISIBLE);
                            }
                        }
                        departemantsTicketsViewSkeleton.hide();
                        
                    }
                });
        
    }
    
    @Override
    public void setUpViews() {
        main_supporter_departemants_openTickets_toolbar_title = findViewById(R.id.main_supporter_departemants_openTickets_toolbar_title);
        departemants_openticketsList_rv = findViewById(R.id.departemants_openticketsList_rv);
        departemantsOpenTicketsListAdapter = new DepartemantsOpenTicketsListAdapter(this);
        departemants_openticketsList_rv.setLayoutManager(new LinearLayoutManager(DepartemantsOpenTicketsActivity.this, RecyclerView.VERTICAL, false));
        departemantsOpenTicketsViewModel = new DepartemantsOpenTicketsViewModel(new DepartemantsOpenTicketCloudDataSource(ApiServiceProvider.getApiService()));
        departemants_openticketsList_rv.setNestedScrollingEnabled(false);
        departemants_openticketsList_rv.setAdapter(departemantsOpenTicketsListAdapter);
        supporter_departemantsTickets_EmptyState_tv = findViewById(R.id.supporter_departemantsTickets_EmptyState_tv);
        departemantsOpenTicketsListAdapter.setOnRvItemsClickListener(this);
        departemantsTicketsViewSkeleton = Skeleton.bind(departemants_openticketsList_rv)
                .adapter(departemantsOpenTicketsListAdapter)
                .load(R.layout.tickets_item_sketlon)
                .count(10)
                .shimmer(true)
                .show();
        
    }
    
    @Override
    public void OnItemClicked(TicketInfo item, int position) {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(TicketsListActivity.EXTRA_KEY_TICKET_TO_CHAT, item);
        startActivity(intent);
    }
    
    @Override
    public void setTextofToolbar(ConnectedInternet connectedInternet) {
        super.setTextofToolbar(connectedInternet);
        updateToolbarText("دانشجویار");
    }
    
    @Override
    public void setTextofToolbar(DisConnectedInternet disConnectedInternet) {
        super.setTextofToolbar(disConnectedInternet);
        updateToolbarText("در حال اتصال ...");
    }
    
    private void updateToolbarText(String title) {
        main_supporter_departemants_openTickets_toolbar_title.setText(title);
    }
}