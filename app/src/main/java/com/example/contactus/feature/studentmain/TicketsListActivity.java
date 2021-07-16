package com.example.contactus.feature.studentmain;

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
import com.example.contactus.feature.data.dataSource.TicketsCloudDataSource;
import com.example.contactus.feature.data.dataSource.repo.AuthenticateRepo;
import com.example.contactus.feature.data.dataSource.repo.TicketsRepository;
import com.example.contactus.feature.data.entities.LogoutResponse;
import com.example.contactus.feature.data.entities.MenuItem;
import com.example.contactus.feature.data.entities.RelatedDepartemants;
import com.example.contactus.feature.data.entities.TicketInfo;
import com.example.contactus.feature.data.entities.TicketsResponse;
import com.example.contactus.feature.data.sharedPrefrences.SharedPrefManagerSingletone;
import com.example.contactus.feature.eventbusevents.ConnectedInternet;
import com.example.contactus.feature.eventbusevents.DisConnectedInternet;
import com.example.contactus.feature.studentmain.adapter.NavigationMenuListAdapter;
import com.example.contactus.feature.studentmain.adapter.TicketListAdapter;
import com.example.contactus.feature.view.LoadingDialogFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TicketsListActivity extends ObserverActivity implements OnRvItemsClickListener<TicketInfo>, AddNewTicketDialog.OnTicketsCreationFirstStepCompeleted {
    
    private static final String TAG = "TicketsListActivity";
    private RecyclerView main_tickets_rv;
    private View main_add_New_Ticket_floatingBtn;
    private TicketListAdapter ticketListAdapter;
    private ImageView searchIcon;
    private EditText search_query_ed;
    private TextView toolbar_title;
    private RecyclerView main_navigation_menu_recyclerview;
    private NavigationMenuListAdapter navigationMenuListAdapter;
    private boolean isInSearchMode = false;
    private View main_navigation_ic;
    private DrawerLayout main_drawer_layout;
    public static final String EXTRA_KEY_SELECTED_DEPARTEMANT = "selected_departemant";
    public static final String EXTRA_KEY_NEW_TICKET_TITLE = "new_Ticket_Title";
    private TicketListViewModel ticketListViewModel;
    private RecyclerViewSkeletonScreen ticketsViewSkeleton;
    public static final String EXTRA_KEY_TICKET_TO_CHAT = "ticket_to_chat";
    private TextView emptyState_tv;
    private TextView noSearchResult_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void setUpViews() {
        ticketListAdapter = new TicketListAdapter(this);
        noSearchResult_tv = findViewById(R.id.noSearchResult_tv);
        main_tickets_rv = findViewById(R.id.main_tickets_rv);
        emptyState_tv = findViewById(R.id.emptyState_tv);
        ticketListViewModel = new TicketListViewModel(new TicketsRepository(new TicketsCloudDataSource(ApiServiceProvider.getApiService())), new AuthenticateRepo(new AuthenticationCloudDataSource(ApiServiceProvider.getApiService())), new LocalDataSource(SharedPrefManagerSingletone.getSharedPrefrencesManager(this)));
        main_add_New_Ticket_floatingBtn = findViewById(R.id.main_add_New_Ticket_floatingBtn);
        searchIcon = findViewById(R.id.searchIcon);
        search_query_ed = findViewById(R.id.search_query_ed);
        toolbar_title = findViewById(R.id.toolbar_title);
        main_navigation_menu_recyclerview = findViewById(R.id.main_navigation_menu_recyclerview);
        main_navigation_ic = findViewById(R.id.main_hambergur_ic);
        main_drawer_layout = findViewById(R.id.main_drawer_layout);
        ticketListAdapter.setOnRvItemsClickListener(this);
        main_tickets_rv.setLayoutManager(new LinearLayoutManager(TicketsListActivity.this, RecyclerView.VERTICAL, false));
        main_tickets_rv.setNestedScrollingEnabled(false);
        main_tickets_rv.setAdapter(ticketListAdapter);
        ticketsViewSkeleton = Skeleton.bind(main_tickets_rv)
                .adapter(ticketListAdapter)
                .load(R.layout.tickets_item_sketlon)
                .count(10)
                .shimmer(true)
                .show();
        setupDrawerItems();
    
        searchIcon.setOnClickListener(view -> {
            if (!isInSearchMode) {
                isInSearchMode = true;
                final Animation anim_out = AnimationUtils.loadAnimation(TicketsListActivity.this, android.R.anim.fade_out);
                final Animation anim_in = AnimationUtils.loadAnimation(TicketsListActivity.this, android.R.anim.fade_in);
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
                //Perform Search
                search_query_ed.addTextChangedListener(new MyTextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        ticketListAdapter.getFilter().filter(charSequence.toString());
                    }
                });

            } else {
                isInSearchMode = false;
                search_query_ed.setText("");
                final Animation anim_out = AnimationUtils.loadAnimation(TicketsListActivity.this, android.R.anim.fade_out);
                final Animation anim_in = AnimationUtils.loadAnimation(TicketsListActivity.this, android.R.anim.fade_in);
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
            addNewTicketDialog.setOnTicketsCreationFirstStepCompeleted(TicketsListActivity.this);
        });


        main_navigation_ic.setOnClickListener(view -> {
            if (!main_drawer_layout.isDrawerOpen(Gravity.RIGHT)) {
                main_drawer_layout.openDrawer(Gravity.RIGHT);
            }
        });

        ticketListAdapter.setOnSearchCallBack(new TicketListAdapter.OnSearchCallBack() {
            @Override
            public void searchHasNoResult() {
                main_tickets_rv.setVisibility(View.GONE);
                noSearchResult_tv.setVisibility(View.VISIBLE);
            }

            @Override
            public void searchHasResult() {
                noSearchResult_tv.setVisibility(View.GONE);
            }

            @Override
            public void searchBoxNoValue() {
                main_tickets_rv.setVisibility(View.VISIBLE);
                noSearchResult_tv.setVisibility(View.GONE);
                ticketListAdapter.setItems(ticketListAdapter.getMainList());
            }
        });
    }

    @Override
    public void observe() {

        ticketListViewModel.getTicketsList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MSingleObserver<TicketsResponse>(compositeDisposable) {
                    @Override
                    public void onSuccess(TicketsResponse ticketsResponse) {
                        if (ticketsResponse.isSuccess()) {
                            if (ticketsResponse.getTicketsLenght() > 0) {
                                ticketListAdapter.setItems(ticketsResponse.getTickets());
                            } else {
                                emptyState_tv.setVisibility(View.VISIBLE);
                                main_tickets_rv.setVisibility(View.GONE);
                            }
                            ticketsViewSkeleton.hide();
                        }
                    }
                });


    }


    @Override
    public void onBackPressed() {
        if (isInSearchMode) {
            search_query_ed.setText("");
            final Animation anim_out = AnimationUtils.loadAnimation(TicketsListActivity.this, android.R.anim.fade_out);
            final Animation anim_in = AnimationUtils.loadAnimation(TicketsListActivity.this, android.R.anim.fade_in);
            anim_in.setAnimationListener(new MyAnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    searchIcon.setImageResource(R.drawable.ic_baseline_search_24);
                    search_query_ed.setVisibility(View.INVISIBLE);
                    searchIcon.startAnimation(anim_out);
                    search_query_ed.startAnimation(anim_out);
                    isInSearchMode = false;
                }
            });
            searchIcon.startAnimation(anim_in);
            search_query_ed.startAnimation(anim_in);

        }


        if (main_drawer_layout.isDrawerOpen(Gravity.RIGHT)) {
            main_drawer_layout.closeDrawer(Gravity.RIGHT);
        }

        if (!isInSearchMode && !main_drawer_layout.isDrawerOpen(Gravity.RIGHT)) {
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
        toolbar_title.setText(title);
    }


    private void setupDrawerItems() {
        navigationMenuListAdapter = new NavigationMenuListAdapter();
        main_navigation_menu_recyclerview.setLayoutManager(new LinearLayoutManager(TicketsListActivity.this, RecyclerView.VERTICAL, false));
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
    
    
        navigationMenuListAdapter.setItems(menuItemList);
    
        main_navigation_menu_recyclerview.setAdapter(navigationMenuListAdapter);
    
        navigationMenuListAdapter.setOnRvItemsClickListener((item, position) -> {
        
            if (position == 4) {
                LoadingDialogFragment loadingDialogFragment = new LoadingDialogFragment();
                loadingDialogFragment.show(getSupportFragmentManager(), null);
                ticketListViewModel.logoutButtonClicked()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new MSingleObserver<LogoutResponse>(compositeDisposable) {
                            @Override
                            public void onSuccess(LogoutResponse logoutResponse) {
                                if (logoutResponse.isSuccess()) {
                                    loadingDialogFragment.dismiss();
                                    ticketListViewModel.clearSharedPref();
                                    TokenContainer.setIsStudent(false);
                                    TokenContainer.setIsSupporter(false);
                                    TokenContainer.updateToken(null);
                                    finish();
                                }
                            }
                        });
            }
        
        });
    
    }

    @Override
    public void OnItemClicked(TicketInfo item, int position) {
        Intent intent = new Intent(TicketsListActivity.this, ChatActivity.class);
        intent.putExtra(EXTRA_KEY_TICKET_TO_CHAT, item);
        startActivity(intent);
    }

    @Override
    public void onTicketRelatedDepartemantAndTicketTitleRecieved(RelatedDepartemants selectedDepartemant, String ticketTitle) {
        Intent intent = new Intent(TicketsListActivity.this, ChatActivity.class);
        intent.putExtra(EXTRA_KEY_SELECTED_DEPARTEMANT, selectedDepartemant);
        intent.putExtra(EXTRA_KEY_NEW_TICKET_TITLE, ticketTitle);
        startActivity(intent);
    }
}