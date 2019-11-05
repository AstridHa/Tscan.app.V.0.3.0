package com.tscan.app.UI_listeners;


public class UI_Listener {

    private String drop_down_panel;
    private OnSensorConnected_DropPanel sensorConnected_dropPanel;


    //WHEN SENSOR CONNECTED POPBACKSTACK DROPDOWNPANEL
    public void setDropPanel_listener(String drop_downPanel){
        drop_down_panel = drop_downPanel;
        if(sensorConnected_dropPanel != null) sensorConnected_dropPanel.onSensorConnected_DropPanel(drop_downPanel);
    }

    public OnSensorConnected_DropPanel getDropPanel_listener(){
        return sensorConnected_dropPanel;
    }

    public void setSensorConnected_DropPanel(OnSensorConnected_DropPanel dropPanel){
        this.sensorConnected_dropPanel = dropPanel;
    }

    public interface OnSensorConnected_DropPanel{
        void onSensorConnected_DropPanel(String drop_down_panel);
    }



}
