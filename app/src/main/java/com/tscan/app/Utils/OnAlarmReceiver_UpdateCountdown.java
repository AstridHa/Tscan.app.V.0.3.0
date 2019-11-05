package com.tscan.app.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tscan.app.UI_listeners.UI_Listener_countdown_alarmManager;


public class OnAlarmReceiver_UpdateCountdown extends BroadcastReceiver {

    public OnAlarmReceiver_UpdateCountdown() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        UI_Listener_countdown_alarmManager.setListener_countdown_trigger(true);
    }
}
