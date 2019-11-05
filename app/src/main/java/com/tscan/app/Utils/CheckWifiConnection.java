package com.tscan.app.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckWifiConnection {

    public boolean isInternetConnected(Context activity) {
        ConnectivityManager conMgr = (ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean connected = true;
        if (conMgr != null) {
            NetworkInfo networkinfo = conMgr.getActiveNetworkInfo();

            if (networkinfo != null) {
                if (!networkinfo.isConnected()) {
                    connected = false;
                }

                if (!networkinfo.isAvailable()) {
                    connected = false;
                }
            }

            if (networkinfo == null)
                connected = false;
        } else
            connected = false;
        return connected;
    }
}
