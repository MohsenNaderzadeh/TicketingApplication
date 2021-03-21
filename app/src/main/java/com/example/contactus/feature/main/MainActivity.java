package com.example.contactus.feature.main;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactus.R;
import com.example.contactus.feature.base.MyAnimationListener;
import com.example.contactus.feature.base.MyTextWatcher;
import com.example.contactus.feature.base.ObserverActivity;
import com.example.contactus.feature.base.OnRvItemsClickListener;
import com.example.contactus.feature.data.DataFakeGenerator;
import com.example.contactus.feature.data.MenuItem;
import com.example.contactus.feature.data.Ticket;
import com.example.contactus.feature.eventbusevents.ConnectedInternet;
import com.example.contactus.feature.eventbusevents.DisConnectedInternet;
import com.example.contactus.feature.main.adapter.NavigationMenuListAdapter;
import com.example.contactus.feature.main.adapter.TicketListAdapter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ObserverActivity implements OnRvItemsClickListener<Ticket> {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void setUpViews() {
        main_tickets_rv = findViewById(R.id.main_tickets_rv);
        main_add_New_Ticket_floatingBtn = findViewById(R.id.main_add_New_Ticket_floatingBtn);
        searchIcon = findViewById(R.id.searchIcon);
        search_query_ed = findViewById(R.id.search_query_ed);
        toolbar_title = findViewById(R.id.toolbar_title);
        main_navigation_menu_recyclerview = findViewById(R.id.main_navigation_menu_recyclerview);
        main_navigation_ic = findViewById(R.id.main_hambergur_ic);
        main_drawer_layout = findViewById(R.id.main_drawer_layout);
    }

    @Override
    public void observe() {
        ticketListAdapter = new TicketListAdapter(DataFakeGenerator.getTicketsList());
        ticketListAdapter.setOnRvItemsClickListener(this);
        main_tickets_rv.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
        main_tickets_rv.setAdapter(ticketListAdapter);
        setupDrawerItems();
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


        main_navigation_ic.setOnClickListener(view -> {
            if (!main_drawer_layout.isDrawerOpen(Gravity.RIGHT)) {
                main_drawer_layout.openDrawer(Gravity.RIGHT);
            }
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


        if (main_drawer_layout.isDrawerOpen(Gravity.RIGHT)) {
            main_drawer_layout.closeDrawer(Gravity.RIGHT);
        }

        if (!isInSearchMode && !main_drawer_layout.isDrawerOpen(Gravity.RIGHT)) {
            super.onBackPressed();
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


    private void updateToolbarText(String title) {
        toolbar_title.setText(title);
    }


    private void setupDrawerItems() {
        navigationMenuListAdapter = new NavigationMenuListAdapter();
        main_navigation_menu_recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
        List<MenuItem> menuItemList = new ArrayList<>();
        MenuItem MainPage = new MenuItem();

        MainPage.setId(1);
        MainPage.setItemIcon(R.drawable.ic_baseline_home_24);
        MainPage.setItemText("صفحه اصلی");
        menuItemList.add(MainPage);

        MenuItem VR = new MenuItem();
        VR.setId(6);
        VR.setItemIcon(R.drawable.ic_baseline_preview_24);
        VR.setItemText("تور مجازی");
        menuItemList.add(VR);

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
        navigationMenuListAdapter.setOnRvItemsClickListener(new OnRvItemsClickListener<MenuItem>() {
            @Override
            public void OnItemClicked(MenuItem item, int position) {

            }
        });
        main_navigation_menu_recyclerview.setAdapter(navigationMenuListAdapter);

    }

}