package com.tscan.app.Utils;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

public class CheckBluetooth {
    public boolean isBluetoothON(FragmentActivity activity) {

        boolean bluetootk_connected = true;
        BluetoothManager b_Manager;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            b_Manager = (BluetoothManager) activity.getSystemService(Context.BLUETOOTH_SERVICE);

            if (b_Manager == null) {
                Toast.makeText(activity, "Bluetooth is not available", Toast.LENGTH_LONG).show();

                bluetootk_connected = false;
            } else {
                // initiate device discovery
                BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

                if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
                    bluetootk_connected = false;

                } else if (!activity.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
                    Toast.makeText(activity, "Bluetooth Low Energy is not available on this Android phone/tablet. Real Bluetooth devices will not be accessible.", Toast.LENGTH_LONG).show();
                    bluetootk_connected = false;
                }
            }
            if (bluetootk_connected) {
                if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED
                        &&
                        ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED
                ) {
                    // Code the sophisticated approach. As per https://developer.android.com/training/permissions/requesting.html
                    Toast.makeText(activity, "To access real Bluetooth devices, you must enabled Location Services for this app via Android Settings.", Toast.LENGTH_LONG).show();
                    bluetootk_connected = false;
                }
            }
        }
        else{
            Toast.makeText(activity, "Your device is to old to support Bluetooth Service", Toast.LENGTH_LONG).show();

        }

        return bluetootk_connected;
    }
}
