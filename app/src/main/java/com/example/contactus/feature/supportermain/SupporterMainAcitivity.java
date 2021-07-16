package com.example.contactus.feature.supportermain;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ethanhua.skeleton.RecyclerViewSkeletonScreen;
import com.ethanhua.skeleton.Skeleton;
import com.example.contactus.R;
import com.example.contactus.feature.base.MSingleObserver;
import com.example.contactus.feature.base.MyAnimationListener;
import com.example.contactus.feature.base.MyTextWatcher;
import com.example.contactus.feature.base.ObserverActivity;
import com.example.contactus.feature.base.OnRvItemsClickListener;
import com.example.contactus.feature.chat.ChatActivity;
import com.example.contactus.feature.data.TokenContainer;
import com.example.contactus.feature.data.api.ApiServiceProvider;
import com.example.contactus.feature.data.dataSource.AuthenticationCloudDataSource;
import com.example.contactus.feature.data.dataSource.LocalDataSource;
import com.example.contactus.feature.data.dataSource.SupporterTicketsInboxCloud;
import com.example.contactus.feature.data.dataSource.repo.AuthenticateRepo;
import com.example.contactus.feature.data.dataSource.repo.SupporterTicketsInboxRepo;
import com.example.contactus.feature.data.entities.LogoutResponse;
import com.example.contactus.feature.data.entities.MenuItem;
import com.example.contactus.feature.data.entities.SupporterTicketInboxResponse;
import com.example.contactus.feature.data.entities.SupporterTicketsItem;
import com.example.contactus.feature.data.entities.TicketInfo;
import com.example.contactus.feature.data.sharedPrefrences.SharedPrefManagerSingletone;
import com.example.contactus.feature.departemantsTicketsList.DepartemantsOpenTicketsActivity;
import com.example.contactus.feature.eventbusevents.ConnectedInternet;
import com.example.contactus.feature.eventbusevents.DisConnectedInternet;
import com.example.contactus.feature.studentmain.TicketsListActivity;
import com.example.contactus.feature.studentmain.adapter.NavigationMenuListAdapter;
import com.example.contactus.feature.supportermain.adapter.SupporterInboxTicketsListAdapter;
import com.example.contactus.feature.view.LoadingDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SupporterMainAcitivity extends ObserverActivity implements OnRvItemsClickListener<SupporterTicketsItem> {
    
    private View main_supporter_panel_navigation_btn;
    private TextView main_supporter_panel_toolbar_title;
    private ImageView main_supporter_panel_search_Iv;
    private EditText supporter_search_query_ed;
    private RecyclerView supporter_main_tickets_rv;
    private TextView supporter_EmptyState_tv;
    private TextView supporterNoSearchResult_tv;
    private FloatingActionButton supporter_Main_Add_New_Ticket_ToInbox_FloatingBtn;
    private DrawerLayout supporter_drawer_layout;
    private SupporterInboxTicketsListAdapter inboxTicketsListAdapter;
    private RecyclerViewSkeletonScreen supporterticketsViewSkeleton;
    private NavigationMenuListAdapter navigationMenuListAdapter;
    private RecyclerView main_supporter_panel_navigation;
    private boolean isInSearchMode = false;
    private SupporterMainViewModel supporterMainViewModel;
    private int supporterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supporter_main_acitivity);
    }

    @Override
    public void observe() {
        supporterMainViewModel.getAll()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MSingleObserver<SupporterTicketInboxResponse>(compositeDisposable) {
                    @Override
                    public void onSuccess(SupporterTicketInboxResponse supporterTicketInboxResponse) {
                        if (supporterTicketInboxResponse.isSuccess()) {
                            supporterId = supporterTicketInboxResponse.getSupporterId();
                            if (supporterTicketInboxResponse.getTicketCount() > 0) {
                                inboxTicketsListAdapter.setItems(supporterTicketInboxResponse.getSupporterTickets());
                                supporter_EmptyState_tv.setVisibility(View.GONE);
                            } else {
                                supporter_EmptyState_tv.setVisibility(View.VISIBLE);

                            }
                            supporterticketsViewSkeleton.hide();
                        }
                    }
                });
    }

    @Override
    public void setUpViews() {
        main_supporter_panel_navigation_btn = findViewById(R.id.main_supporter_panel_navigation_btn);
        main_supporter_panel_toolbar_title = findViewById(R.id.main_supporter_panel_toolbar_title);
        main_supporter_panel_search_Iv = findViewById(R.id.main_supporter_panel_search_Iv);
        supporter_search_query_ed = findViewById(R.id.supporter_search_query_ed);
        supporter_main_tickets_rv = findViewById(R.id.supporter_main_tickets_rv);
        supporter_EmptyState_tv = findViewById(R.id.supporter_EmptyState_tv);
        supporterNoSearchResult_tv = findViewById(R.id.supporterNoSearchResult_tv);
        inboxTicketsListAdapter = new SupporterInboxTicketsListAdapter(this);
        supporter_Main_Add_New_Ticket_ToInbox_FloatingBtn = findViewById(R.id.supporter_Main_Add_New_Ticket_ToInbox_FloatingBtn);
        supporter_drawer_layout = findViewById(R.id.supporter_drawer_layout);
        main_supporter_panel_navigation = findViewById(R.id.main_supporter_panel_navigation);
        supporterMainViewModel = new SupporterMainViewModel(new SupporterTicketsInboxRepo(new SupporterTicketsInboxCloud(ApiServiceProvider.getApiService())), new AuthenticateRepo(new AuthenticationCloudDataSource(ApiServiceProvider.getApiService())), new LocalDataSource(SharedPrefManagerSingletone.getSharedPrefrencesManager(this)));
        //TODO:Set Onclick Listneer on Items List
        supporter_main_tickets_rv.setLayoutManager(new LinearLayoutManager(SupporterMainAcitivity.this, RecyclerView.VERTICAL, false));
        supporter_main_tickets_rv.setNestedScrollingEnabled(false);
    
        supporter_main_tickets_rv.setAdapter(inboxTicketsListAdapter);
        inboxTicketsListAdapter.setOnRvItemsClickListener(this);
        supporterticketsViewSkeleton = Skeleton.bind(supporter_main_tickets_rv)
                .adapter(inboxTicketsListAdapter)
                .load(R.layout.tickets_item_sketlon)
                .count(10)
                .shimmer(true)
                .show();

        setupDrawerItems();

        main_supporter_panel_search_Iv.setOnClickListener(view -> {
            if (!isInSearchMode) {
                isInSearchMode = true;
                final Animation anim_out = AnimationUtils.loadAnimation(SupporterMainAcitivity.this, android.R.anim.fade_out);
                final Animation anim_in = AnimationUtils.loadAnimation(SupporterMainAcitivity.this, android.R.anim.fade_in);
                anim_out.setAnimationListener(new MyAnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        main_supporter_panel_search_Iv.setImageResource(R.drawable.ic_baseline_arrow_back_left_24);
                        supporter_search_query_ed.setVisibility(View.VISIBLE);
                        main_supporter_panel_search_Iv.startAnimation(anim_in);
                        supporter_search_query_ed.startAnimation(anim_in);
                    }
                });
                main_supporter_panel_search_Iv.startAnimation(anim_out);
                supporter_search_query_ed.startAnimation(anim_out);
                //Perform Search
                supporter_search_query_ed.addTextChangedListener(new MyTextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        inboxTicketsListAdapter.getFilter().filter(charSequence.toString());
                    }
                });

            } else {
                isInSearchMode = false;
                supporter_search_query_ed.setText("");
                final Animation anim_out = AnimationUtils.loadAnimation(SupporterMainAcitivity.this, android.R.anim.fade_out);
                final Animation anim_in = AnimationUtils.loadAnimation(SupporterMainAcitivity.this, android.R.anim.fade_in);
                anim_in.setAnimationListener(new MyAnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        main_supporter_panel_search_Iv.setImageResource(R.drawable.ic_baseline_search_24);
                        supporter_search_query_ed.setVisibility(View.INVISIBLE);
                        main_supporter_panel_search_Iv.startAnimation(anim_out);
                        supporter_search_query_ed.startAnimation(anim_out);
                    }
                });

                main_supporter_panel_search_Iv.startAnimation(anim_in);
                supporter_search_query_ed.startAnimation(anim_in);
            }


        });
        supporter_Main_Add_New_Ticket_ToInbox_FloatingBtn.setOnClickListener(view -> {
            Intent goToDepartemantsOpenTicketsList = new Intent(SupporterMainAcitivity.this, DepartemantsOpenTicketsActivity.class);
            startActivity(goToDepartemantsOpenTicketsList);
        });


        main_supporter_panel_navigation_btn.setOnClickListener(view -> {
            if (!supporter_drawer_layout.isDrawerOpen(Gravity.RIGHT)) {
                supporter_drawer_layout.openDrawer(Gravity.RIGHT);
            }
        });

        inboxTicketsListAdapter.setOnSearchCallBack(new SupporterInboxTicketsListAdapter.OnSearchCallBack() {
            @Override
            public void searchHasNoResult() {
                supporter_main_tickets_rv.setVisibility(View.GONE);
                supporterNoSearchResult_tv.setVisibility(View.VISIBLE);
            }

            @Override
            public void searchHasResult() {
                supporterNoSearchResult_tv.setVisibility(View.GONE);
            }

            @Override
            public void searchBoxNoValue() {
                supporter_main_tickets_rv.setVisibility(View.VISIBLE);
                supporterNoSearchResult_tv.setVisibility(View.GONE);
                inboxTicketsListAdapter.setItems(inboxTicketsListAdapter.getMainList());
            }
        });

    }

    private void setupDrawerItems() {
        navigationMenuListAdapter = new NavigationMenuListAdapter();
        main_supporter_panel_navigation.setLayoutManager(new LinearLayoutManager(SupporterMainAcitivity.this, RecyclerView.VERTICAL, false));
        List<MenuItem> menuItemList = new ArrayList<>();
        MenuItem MainPage = new MenuItem();

        MainPage.setId(1);
        MainPage.setItemIcon(R.drawable.ic_baseline_home_24);
        MainPage.setItemText("صفحه اصلی");
        menuItemList.add(MainPage);


        MenuItem SettingItem = new MenuItem();
        SettingItem.setId(2);
        SettingItem.setItemIcon(R.drawable.ic_baseline_settings_24);
        SettingItem.setItemText("تنظیمات");
        menuItemList.add(SettingItem);

        MenuItem AboutUs = new MenuItem();
        AboutUs.setId(3);
        AboutUs.setItemIcon(R.drawable.ic_baseline_group_24);
        AboutUs.setItemText("درباره ما");
        menuItemList.add(AboutUs);

        MenuItem contactUs = new MenuItem();
        contactUs.setId(4);
        contactUs.setItemIcon(R.drawable.ic_baseline_local_phone_24);
        contactUs.setItemText("تماس با ما");
        menuItemList.add(contactUs);
    
    
        MenuItem logOut = new MenuItem();
        logOut.setId(5);
        logOut.setItemIcon(R.drawable.ic_baseline_exit_to_app_24);
        logOut.setItemText("خروج از حساب کاربری");
        menuItemList.add(logOut);
    
        //TODO:Add Onclick Listner for items of navigation menu
        navigationMenuListAdapter.setItems(menuItemList);
        navigationMenuListAdapter.setOnRvItemsClickListener((item, position) -> {
        
            if (position == 4) {
                LoadingDialogFragment loadingDialogFragment = new LoadingDialogFragment();
                loadingDialogFragment.show(getSupportFragmentManager(), null);
                supporterMainViewModel.logoutButtonClicked()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new MSingleObserver<LogoutResponse>(compositeDisposable) {
                            @Override
                            public void onSuccess(LogoutResponse logoutResponse) {
                                if (logoutResponse.isSuccess()) {
                                    loadingDialogFragment.dismiss();
                                    supporterMainViewModel.clearSharedPref();
                                    TokenContainer.setIsStudent(false);
                                    TokenContainer.setIsSupporter(false);
                                    TokenContainer.updateToken(null);
                                    finish();
                                }
                            }
                        });
            }
        
        });
        main_supporter_panel_navigation.setAdapter(navigationMenuListAdapter);
    
    }


    @Override
    public void onBackPressed() {
        if (isInSearchMode) {
            supporter_search_query_ed.setText("");
            final Animation anim_out = AnimationUtils.loadAnimation(SupporterMainAcitivity.this, android.R.anim.fade_out);
            final Animation anim_in = AnimationUtils.loadAnimation(SupporterMainAcitivity.this, android.R.anim.fade_in);
            anim_in.setAnimationListener(new MyAnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    main_supporter_panel_search_Iv.setImageResource(R.drawable.ic_baseline_search_24);
                    supporter_search_query_ed.setVisibility(View.INVISIBLE);
                    main_supporter_panel_search_Iv.startAnimation(anim_out);
                    supporter_search_query_ed.startAnimation(anim_out);
                    isInSearchMode = false;
                }
            });
            main_supporter_panel_search_Iv.startAnimation(anim_in);
            supporter_search_query_ed.startAnimation(anim_in);

        }


        if (supporter_drawer_layout.isDrawerOpen(Gravity.RIGHT)) {
            supporter_drawer_layout.closeDrawer(Gravity.RIGHT);
        }

        if (!isInSearchMode && !supporter_drawer_layout.isDrawerOpen(Gravity.RIGHT)) {
            super.onBackPressed();
        }
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
        main_supporter_panel_toolbar_title.setText(title);
    }
    
    @Override
    public void OnItemClicked(SupporterTicketsItem item, int position) {
        Intent goToChatActivity = new Intent(SupporterMainAcitivity.this, ChatActivity.class);
        TicketInfo ticketInfo = new TicketInfo(item.getTicketId(), item.getTicketOwnerId(), item.getTicketRelatedAdministrativeDepartemantId(), item.getTicketTitle(), item.getTicketStatus(), item.getTicketSubmitDate(), item.getTicketLastMessage());
        goToChatActivity.putExtra(TicketsListActivity.EXTRA_KEY_TICKET_TO_CHAT, ticketInfo);
        goToChatActivity.putExtra("SupporterId", supporterId);
        goToChatActivity.putExtra("OriginActivity", "SupporterMain");
        startActivity(goToChatActivity);
    }
}