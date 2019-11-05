package com.tscan.app.Data;

import uk.co.etiltd.thermalib.Device;

public class Singleton_Sensor {

    private static Singleton_Sensor sensor_instance;
    public Singleton_Sensor() {}
    public synchronized static Singleton_Sensor getSensor_instance() { if (sensor_instance == null)
        {sensor_instance = new Singleton_Sensor(); }
        return sensor_instance; }

    public static void setSensor_instance(Singleton_Sensor sensor_instance) {
        Singleton_Sensor.sensor_instance = sensor_instance;
    }

    /** DEVICE DATA **/
    private Device device_datas;
    private String sensor_id = "0";
    private String sensor_name;
    private String sensor_manufacturer;
    private String sensor_model;
    private String unit;
    private float sensor_value;

    public Device getDevice_datas() {
        return device_datas;
    }

    public void setDevice_datas(Device device_datas) {
        this.device_datas = device_datas;
    }

    public String getSensor_id() {
        return sensor_id;
    }

    public void setSensor_id(String sensor_id) {
        this.sensor_id = sensor_id;
    }

    public String getSensor_name() {
        return sensor_name;
    }

    public void setSensor_name(String sensor_name) {
        this.sensor_name = sensor_name;
    }

    public String getSensor_manufacturer() {
        return sensor_manufacturer;
    }

    public void setSensor_manufacturer(String sensor_manufacturer) {
        this.sensor_manufacturer = sensor_manufacturer;
    }

    public String getSensor_model() {
        return sensor_model;
    }

    public void setSensor_model(String sensor_model) {
        this.sensor_model = sensor_model;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getSensor_value() {
        return sensor_value;
    }

    public void setSensor_value(float sensor_value) {
        this.sensor_value = sensor_value;
    }



    /** LISTENERS **/
    private boolean sensor_connected = false;
    private String ConnectionState;
    private onConnectionChangeListener connectionChangeListener;

    public boolean isConnected() {
        return sensor_connected;
    }

    public void setVariable(boolean value) {
        sensor_connected = value;
        if (connectionChangeListener != null) connectionChangeListener.onChange();
    }

    public String getConnectionState() {
        return ConnectionState;
    }

    public void setConnectionState(String connectionState) {
        ConnectionState = connectionState;
    }




    public onConnectionChangeListener getConnectionChangeListener() {
        return connectionChangeListener;
    }

    public void setValueChangeListener(onConnectionChangeListener connectionChangeListener) {
        this.connectionChangeListener = connectionChangeListener;
    }

    public interface onConnectionChangeListener {
        void onChange();
    }
}
