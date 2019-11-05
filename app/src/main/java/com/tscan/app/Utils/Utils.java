package com.tscan.app.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.view.View;

import com.tscan.app.Activities.Activity_login;
import com.tscan.app.Activities.Activity_main;
import com.tscan.app.R;

public class Utils {

    private static int MIN_CLICK_INTERVAL = 500;
    private static boolean result = false;

    public static int action_open = 0; // global variable used to avoid opening several task_fragment if multiple items are clicked inside Recyclerview



    public static void avoid_double_click(View v) {
        v.setEnabled(false);

        new Handler().postDelayed(() -> v.setEnabled(true), MIN_CLICK_INTERVAL);
    }

    public static void warning_dialog(Context context, String title, String message){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, R.style.AlertDialogCustom);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    public static void got_it_dialog(Context context, String title, String message, String button){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, R.style.AlertDialogCustom);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);

        final AlertDialog alert = alertDialog.create();
        alert.show();

        alertDialog.setPositiveButton(button, (dialog, which) -> alert.dismiss());
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    public static void dialog_successful(Context context, String title, String message){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, R.style.AlertDialogCustom);
        alertDialog.setTitle(title);
        alertDialog.setIcon(R.drawable.ic_completed_green);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(true);

        final AlertDialog alert = alertDialog.create();
        alert.show();

        final Handler handler  = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (alert.isShowing()) {
                    alert.dismiss();
                }
            }
        };

        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });
        handler.postDelayed(runnable, 2000);

    }

    public static boolean alertdialog_login_no_wifi(Context context, String title, String message, String button_true, String button_flase){
        CheckWifiConnection checkWifiConnection = new CheckWifiConnection();

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, R.style.AlertDialogCustom);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);

        alertDialog.setNegativeButton( button_flase, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.startActivity(new Intent(context, Activity_main.class));
            }
        });

        alertDialog.setPositiveButton(button_true, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                wifiManager.setWifiEnabled(true);

                Activity_login.user_progress.setVisibility(View.VISIBLE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(checkWifiConnection.isInternetConnected(context)){
                            Activity_login.user_progress.setVisibility(View.GONE);
                            context.startActivity(new Intent(context, Activity_main.class));
                        }
                        else{
                            Activity_login.user_progress.setVisibility(View.GONE);
                        }
                    }
                }, 5000);
            }
        });
        return result;
    }
}

