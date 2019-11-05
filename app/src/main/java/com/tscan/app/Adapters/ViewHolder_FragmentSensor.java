package com.tscan.app.Adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tscan.app.Data.Singleton_Sensor;
import com.tscan.app.R;

import org.json.JSONObject;

import uk.co.etiltd.thermalib.Device;
import uk.co.etiltd.thermalib.ThermaLibException;

import static android.widget.Toast.LENGTH_LONG;

public class ViewHolder_FragmentSensor {

    private Context mContext;
    private JSONObject device_array;
    private TextView probe_name;
    private TextView probe_state;
    private ImageView probe_connectBtn;
    private RelativeLayout probe_connectBtn_rl;
    private final String TAG = "ViewHolder_fragment_sensor";
    private Device mDevice;

    public void setDevice(Device device) {
        mDevice = device;
    }

    public Device getDevice() {
        return mDevice;
    }


    public ViewHolder_FragmentSensor(View view) {
        mContext = view.getContext();
        //device setter is not set yet here
        probe_name = view.findViewById(R.id.probe_row_deviceName);
        probe_state = view.findViewById(R.id.probe_row_connectionState);
        probe_connectBtn = view.findViewById(R.id.probe_row_button_connect);
        probe_connectBtn_rl = view.findViewById(R.id.probe_row_button_connect_LL);
    }

    public void setSensorClass(ViewHolder_FragmentSensor vh) {
        setFields(vh);

        vh.probe_name.setText(mDevice.getDeviceName());
        vh.probe_connectBtn_rl.setOnClickListener(v -> {

            if(Singleton_Sensor.getSensor_instance().getSensor_id().equals("0")){

                if (!vh.mDevice.isConnected() && vh.mDevice.getConnectionState() != Device.ConnectionState.UNAVAILABLE) {

                    try {
                        vh.mDevice.requestConnection();
                        vh.probe_state.setText("Connecting...");

                        Singleton_Sensor.getSensor_instance().setSensor_id(vh.mDevice.getIdentifier());
                        Singleton_Sensor.getSensor_instance().setSensor_name(vh.mDevice.getDeviceName());
                        Singleton_Sensor.getSensor_instance().setSensor_manufacturer(vh.mDevice.getManufacturerName());
                        Singleton_Sensor.getSensor_instance().setSensor_model(vh.mDevice.getModelNumber());
                        Singleton_Sensor.getSensor_instance().setDevice_datas(vh.mDevice);

                    } catch (ThermaLibException e) {
                        e.printStackTrace();
                    }
                }
                else if (vh.mDevice.isConnected()){
                    vh.mDevice.requestDisconnection();
                    Singleton_Sensor.getSensor_instance().setSensor_id(String.valueOf(0));
                    Singleton_Sensor.getSensor_instance().setSensor_name(null);
                    Singleton_Sensor.getSensor_instance().setSensor_manufacturer(null);
                    Singleton_Sensor.getSensor_instance().setSensor_model(null);
                    Singleton_Sensor.getSensor_instance().setDevice_datas(null);
                }
            }
            else{
                Device connected_device = Singleton_Sensor.getSensor_instance().getDevice_datas();

                if( connected_device == vh.mDevice) {
                    connected_device.requestDisconnection();
                    Singleton_Sensor.getSensor_instance().setSensor_id(String.valueOf(0));
                    Singleton_Sensor.getSensor_instance().setSensor_name(null);
                    Singleton_Sensor.getSensor_instance().setSensor_manufacturer(null);
                    Singleton_Sensor.getSensor_instance().setSensor_model(null);
                    Singleton_Sensor.getSensor_instance().setDevice_datas(null);

                }
                else {
                    connected_device.requestDisconnection();
                    Singleton_Sensor.getSensor_instance().setSensor_id(String.valueOf(0));
                    Singleton_Sensor.getSensor_instance().setSensor_name(null);
                    Singleton_Sensor.getSensor_instance().setSensor_manufacturer(null);
                    Singleton_Sensor.getSensor_instance().setSensor_model(null);
                    Singleton_Sensor.getSensor_instance().setDevice_datas(null);


                    try {
                        vh.mDevice.requestConnection();
                        Singleton_Sensor.getSensor_instance().setSensor_id(vh.mDevice.getIdentifier());
                        Singleton_Sensor.getSensor_instance().setSensor_name(vh.mDevice.getDeviceName());
                        Singleton_Sensor.getSensor_instance().setSensor_manufacturer(vh.mDevice.getManufacturerName());
                        Singleton_Sensor.getSensor_instance().setSensor_model(vh.mDevice.getModelNumber());
                        Singleton_Sensor.getSensor_instance().setDevice_datas(vh.mDevice);

                    } catch (ThermaLibException e) {
                        e.printStackTrace();
                        Toast.makeText(mContext, "Couldn't connect. Turn it ON/OFF and try again.", LENGTH_LONG).show();
                    }
                }
            }
        });

        if(mDevice.getConnectionState() == Device.ConnectionState.CONNECTED && mDevice.isReady()){
            Singleton_Sensor.getSensor_instance().setVariable(true);
            Singleton_Sensor.getSensor_instance().setSensor_value(mDevice.getSensor(0).getReading());
            Singleton_Sensor.getSensor_instance().setUnit(mDevice.getSensor(0).getReadingUnit().getUnitString());
        }



    }

    void setFields(ViewHolder_FragmentSensor vh){
        if(vh.mDevice.getConnectionState() == Device.ConnectionState.CONNECTED && vh.mDevice.isReady()){
            vh.probe_state.setText("Connected");
            vh.probe_state.setTextColor(getColor(R.color.grey));
            vh.probe_name.setText(vh.mDevice.getIdentifier());
            vh.probe_name.setTextColor(getColor(R.color.green));
            vh.probe_connectBtn.setImageResource(R.drawable.ic_bluetooth_connected);
            vh.probe_connectBtn_rl.setBackgroundResource(R.drawable.style_button_bluetooth_connected);
        }

        else if (vh.mDevice.getConnectionState() == Device.ConnectionState.CONNECTING || vh.mDevice.getConnectionState() == Device.ConnectionState.DISCONNECTING ){
            vh.probe_state.setText("Connecting");
            vh.probe_state.setTextColor(getColor(R.color.grey));
            vh.probe_name.setText(vh.mDevice.getIdentifier());
            vh.probe_name.setTextColor(getColor(R.color.gold));
            vh.probe_connectBtn.setImageResource(R.drawable.ic_bluetooth_wait);
            vh.probe_connectBtn_rl.setBackgroundResource(R.drawable.style_button_bluetooth_wait);
        }
        else if (vh.mDevice.getConnectionState() == Device.ConnectionState.AVAILABLE || vh.mDevice.getConnectionState() == Device.ConnectionState.UNAVAILABLE
                || vh.mDevice.getConnectionState() == Device.ConnectionState.DISCONNECTED) {
            vh.probe_state.setText(String.valueOf(vh.mDevice.getConnectionState()));
            vh.probe_state.setTextColor(getColor(R.color.grey));
            vh.probe_name.setText(vh.mDevice.getIdentifier());
            vh.probe_name.setTextColor(getColor(R.color.grey));
            vh.probe_connectBtn.setImageResource(R.drawable.ic_bluetooth);
            vh.probe_connectBtn_rl.setBackgroundResource(R.drawable.style_button_bluetooth);
        }

    }

    private int getColor(int colorRes) {
        return mContext.getResources().getColor(colorRes);
    }

}




