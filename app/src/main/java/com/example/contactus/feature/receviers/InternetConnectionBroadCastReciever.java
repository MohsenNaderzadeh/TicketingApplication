package com.example.contactus.feature.receviers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.example.contactus.feature.eventbusevents.ConnectedInternet;
import com.example.contactus.feature.eventbusevents.DisConnectedInternet;

import org.greenrobot.eventbus.EventBus;

public class InternetConnectionBroadCastReciever extends BroadcastReceiver {
    private static final String TAG = "InternetConnectionBroad";
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
        String reason = intent.getStringExtra(ConnectivityManager.EXTRA_REASON);
        boolean isFailover = intent.getBooleanExtra(ConnectivityManager.EXTRA_IS_FAILOVER, false);

        NetworkInfo currentNetworkInfo = (NetworkInfo) intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
        NetworkInfo otherNetworkInfo = (NetworkInfo) intent.getParcelableExtra(ConnectivityManager.EXTRA_OTHER_NETWORK_INFO);

        if(currentNetworkInfo.isConnected()){
            EventBus.getDefault().post(new ConnectedInternet());
        }else{
            EventBus.getDefault().post(new DisConnectedInternet());

        }
    }
}
