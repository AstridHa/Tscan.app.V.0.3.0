package com.tscan.app.UI_listeners;

public class UI_Listener_Adapter_Completed_Uploaded_Icon {

    private boolean listener_uploaded;
    private boolean listener_not_uploaded;

    private OnListener_uploaded uploaded;
    private OnListener_not_uploaded not_uploaded;



    public void callListener_uploaded(boolean listenerUploaded){
        listener_uploaded = listenerUploaded;

        if(uploaded != null) uploaded.onListener_uploaded(uploaded);
    }

    public OnListener_uploaded getlistener_uploaded(){
        return uploaded;
    }

    public void setListener_uploaded(OnListener_uploaded listener_uploaded){
        this.uploaded = listener_uploaded;
    }

    public interface OnListener_uploaded{
        void onListener_uploaded(OnListener_uploaded listener_uploaded);
    }




    public void callListener_not_uploaded(boolean listener_not_Uploaded){
        listener_not_uploaded = listener_not_Uploaded;

        if(not_uploaded != null) not_uploaded.onListener_not_uploaded(not_uploaded);
    }

    public OnListener_not_uploaded getlistener_not_uploaded(){
        return not_uploaded;
    }

    public void setListener_not_uploaded(OnListener_not_uploaded listener_uploaded){
        this.not_uploaded = listener_uploaded;
    }

    public interface OnListener_not_uploaded{
        void onListener_not_uploaded(OnListener_not_uploaded listener_not_uploaded);
    }
}


