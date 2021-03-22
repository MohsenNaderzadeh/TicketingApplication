package com.example.contactus.feature.base;

import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.example.contactus.feature.eventbusevents.ConnectedInternet;
import com.example.contactus.feature.eventbusevents.DisConnectedInternet;
import com.example.contactus.feature.receviers.InternetConnectionBroadCastReciever;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public abstract class ObserverActivity extends BaseActivity {

    private InternetConnectionBroadCastReciever internetConnectionBroadCastReciever;
    public  abstract void observe();



    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        internetConnectionBroadCastReciever=new InternetConnectionBroadCastReciever();
        registerReceiver(internetConnectionBroadCastReciever,
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        observe();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        unregisterReceiver(internetConnectionBroadCastReciever);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setTextofToolbar(ConnectedInternet connectedInternet) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setTextofToolbar(DisConnectedInternet disConnectedInternet) {
    }
}
