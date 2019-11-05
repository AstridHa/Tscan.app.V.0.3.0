package com.tscan.app.UI_listeners;

import java.util.ArrayList;
import java.util.List;

public class UI_Listener_countdown_alarmManager {
    private static boolean listener_countdown_trigger;
    private static List<CountdownListener> listeners = new ArrayList<CountdownListener>();

    public static boolean getListener_countdown_trigger() { return listener_countdown_trigger; }

    public static void setListener_countdown_trigger(boolean value) {
        listener_countdown_trigger = value;

        for (CountdownListener l : listeners) {
            l.OnListener_countdown_trigger();
        }
    }

    public static void addListener_countdown_trigger(CountdownListener l) {
        listeners.add(l);
    }

    public interface CountdownListener {
        void OnListener_countdown_trigger();

    }
}


