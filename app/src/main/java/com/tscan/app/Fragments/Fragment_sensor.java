package com.tscan.app.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

//import com.tscan.app.UI_listeners.ExceptionHandler;
import com.tscan.app.Utils.CheckBluetooth;
import com.tscan.app.R;
import com.tscan.app.Adapters.Adapter_Sensor;
import com.tscan.app.UI_listeners.UI_Listener;

import java.util.Arrays;

import uk.co.etiltd.thermalib.Device;
import uk.co.etiltd.thermalib.ThermaLib;

public class Fragment_sensor extends Fragment {

    private ProgressBar sensor_progressBar;
    private RelativeLayout sensor_toolbar, sensor_toolbar_refresh;
    private ListView sensor_list;
    private SwipeRefreshLayout sensor_pullToRefresh;
    private TextView sensor_noAvailable_tv, sensor_refresh_tv;
    private ThermaLib mThermaLib;
    private Object mThermaLibCallbackHandle;
    private Adapter_Sensor mAdapter;
    private int scanTimeout = 5;
    private final String TAG = "tododu_sensor";
    private CheckBluetooth checkBluetooth;

    private UI_Listener ui_listener = new UI_Listener();
    private Context context;


    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

/////////////////////////////////////////////////////////////////////////
//   ON CREATE                                                         //
/////////////////////////////////////////////////////////////////////////
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sensor, container, false);

        /////////////////////////////////////////////////////////////////////////
        //   INITIALIZATION                                                    //
        /////////////////////////////////////////////////////////////////////////
        sensor_progressBar = view.findViewById(R.id.sensor_progressBar);
        sensor_list = view.findViewById(R.id.sensor_list);
        sensor_list.setFooterDividersEnabled(false);
        sensor_toolbar = view.findViewById(R.id.sensor_toolbar);
        sensor_toolbar_refresh = view.findViewById(R.id.sensor_toolbar_refresh);
        sensor_pullToRefresh = view.findViewById(R.id.sensor_pullToRefresh);
        sensor_noAvailable_tv = view.findViewById(R.id.sensor_noAvailable_tv);
        sensor_refresh_tv = view.findViewById(R.id.sensor_refresh_tv);

        // ThermaLib and adapter for list
        mThermaLib = ThermaLib.instance(context);
        mAdapter = new Adapter_Sensor(context, mThermaLib.getDeviceList());
        sensor_list.setAdapter(mAdapter);
        mThermaLib.setSupportedTransports(Arrays.asList(new Integer[]{ThermaLib.Transport.BLUETOOTH_LE}));
        mThermaLibCallbackHandle = mThermaLib.registerCallbacks(mThermalibCallbacks, TAG);

        checkBluetooth = new CheckBluetooth();

        /////////////////////////////////////////////////////////////////////////
        //   ONCLICK LISTENERS                                                 //
        /////////////////////////////////////////////////////////////////////////
        sensor_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    getFragmentManager().popBackStack();

            }
        });

        sensor_toolbar_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scan_sensors();
            }
        });

        sensor_pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "swipe");
                sensor_pullToRefresh.setEnabled(false);
            }
        });

        ui_listener.setSensorConnected_DropPanel(new UI_Listener.OnSensorConnected_DropPanel() {
            @Override
            public void onSensorConnected_DropPanel(String drop_down_panel) {
                try {
                    getFragmentManager().popBackStack();
                } catch(Exception e) {
                    Log.i("Exception fragment sensor", String.valueOf(e));
                }
            }
        });

        return view;
    }



/////////////////////////////////////////////////////////////////////////
//   LIFECYCLE                                                         //
/////////////////////////////////////////////////////////////////////////
    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "Resume ");
    scan_sensors();
    }


/////////////////////////////////////////////////////////////////////////
//   FUNCTIONS                                                         //
/////////////////////////////////////////////////////////////////////////
    private void scan_sensors() {
        sensor_progressBar.setVisibility(View.VISIBLE);
        sensor_refresh_tv.setVisibility(View.GONE);

        if(checkBluetooth.isBluetoothON(getActivity())){
            mThermaLib.startScanForDevices(ThermaLib.Transport.BLUETOOTH_LE, scanTimeout);
            mAdapter.notifyDataSetChanged();
        }
        else{
            sensor_list.setVisibility(View.GONE);
            mAdapter.clearList();
            sensor_progressBar.setVisibility(View.GONE);
            sensor_refresh_tv.setVisibility(View.VISIBLE);

            sensor_noAvailable_tv.setText("Bluetooth is OFF...");
            sensor_noAvailable_tv.setVisibility(View.VISIBLE);
        }
    }

    private ThermaLib.ClientCallbacks mThermalibCallbacks = new ThermaLib.ClientCallbacksBase(){
        @Override
        public void onScanComplete(int transport, ThermaLib.ScanResult scanResult, int numDevices, String msg) {
            if (scanResult != ThermaLib.ScanResult.SUCCESS) {
                Log.i(TAG, "onscanComplete " + "no success");
                sensor_list.setVisibility(View.GONE);
                mAdapter.clearList();
                sensor_noAvailable_tv.setText("No sensor detected...");
                sensor_noAvailable_tv.setVisibility(View.VISIBLE);

            } else {
                if(mAdapter.getCount() == 0){
                    Log.i(TAG, "onscanComplete " + "0 device");
                    sensor_list.setVisibility(View.GONE);
//                    mAdapter.clearList();
                    sensor_noAvailable_tv.setText("No sensor detected... \n Turn your sensor OFF and ON again.");
                    sensor_noAvailable_tv.setVisibility(View.VISIBLE);
                }
                else {
                    Log.i(TAG, "onscanComplete " + "1");
                    sensor_noAvailable_tv.setVisibility(View.GONE);
                    sensor_list.setVisibility(View.VISIBLE);
                    mAdapter.notifyDataSetChanged();
                }
            }
            sensor_progressBar.setVisibility(View.GONE);
            sensor_refresh_tv.setVisibility(View.VISIBLE);
        }


        @Override
        public void onNewDevice(Device device, long timestamp) {
            Log.i(TAG, "onNewDevice " + device.getDeviceName());

            sensor_pullToRefresh.setRefreshing(false);
            sensor_noAvailable_tv.setVisibility(View.GONE);
            sensor_list.setVisibility(View.VISIBLE);
            mAdapter.notifyDataSetChanged();
        }

        // device connection state change
        @Override
        public void onDeviceConnectionStateChanged(Device device, Device.ConnectionState newState, long timestamp) {
//            Log.i(TAG, "StateChanged " + (newState) + " " + (Singleton_Sensor.getSensor_instance().isSensor_isConnected()));
//            mAdapter.setFieldsForDevice(device); // reflect the change
            Log.i("Listener_sensor1", String.valueOf(device.isConnected()));

            if(device.isConnected()){
                Log.i("Listener_sensor2", String.valueOf(device.isConnected()));
                ui_listener.setDropPanel_listener("drop it");
            }

            mAdapter.notifyDataSetChanged();
        }

        // device object updated, which can be stimulated by a device event (e.g. new reading) or an SDK event (e.g.
        // a call to a settings method such as setHighAlarm.
        @Override
        public void onDeviceUpdated(Device device, long timestamp) {
            sensor_pullToRefresh.setRefreshing(false);
            mAdapter.notifyDataSetChanged();
            mAdapter.setFieldsForDevice(device); // reflect the change
        }

        // Not all devices report all events. Most events are currently reported only by Bluetooth LE events.
        @Override
        public void onDeviceNotificationReceived(Device device, int notificationType, byte[] payload, long timestamp) {
            super.onDeviceNotificationReceived(device, notificationType, payload, timestamp);
            //notification received when shutdown
            mAdapter.notifyDataSetChanged();
            Toast.makeText(context, Device.NotificationType.toString(notificationType), Toast.LENGTH_SHORT).show();
        }

        // This is called when ThermaLib.requestService has completed. Currently only relevant for Cloud devices.
        @Override
        public void onRequestServiceComplete(int transport, boolean succeeded, String errorMessage, String appKey) {
            Log.d(TAG, "WiFi Service Connection" + errorMessage);
        }

        // called when a request to revoke access to a device (==deregistration, ==unpairing) completes. Currently only
        // relevant for Cloud devices.
        @Override
        public void onDeviceRevokeRequestComplete(Device device, boolean succeeded, String errorMessage) {
            if (succeeded) {
                Toast.makeText(context, "Device Forgotten", Toast.LENGTH_SHORT).show();
                ThermaLib.instance(context).deleteDevice(device);
            } else {
                Toast.makeText(context, "Unable to forget device : " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onRemoteSettingsReceived(Device device) {
            Toast.makeText(context, "Settings received for " + device.getDeviceName(), Toast.LENGTH_SHORT).show();
        }

        // called when a disconnection has occurred that is not correlated with client app action, such as a disconnection request.;
        @Override
        public void onUnexpectedDeviceDisconnection(Device device, String exceptionMessage, DeviceDisconnectionReason reason, long timestamp) {
            Log.e(TAG, "Unexpected Disconnection : " + exceptionMessage);
            mAdapter.setFieldsForDevice(device);
        }
    };
}
