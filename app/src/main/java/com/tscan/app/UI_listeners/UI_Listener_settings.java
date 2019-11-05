package com.tscan.app.UI_listeners;

import com.tscan.app.API.Model_getData_reading;

public class UI_Listener_settings {
    private String uploadSuccessful;
    private Model_getData_reading delete_successful;
    private String insert_Successful;
    private String window_Successful;
    private String token;
    private String tokenfail;
    private String errormessage;


    private OnDataUploadSuccessful_settings dataUploadSuccessful;
    private OnDataDeleteSuccessful_settings dataDeleteSuccessful;
    private OnDataInsertIntoRoomSuccessful_settings dataInsertIntoRoomSuccessful;
    private OnWindowsGeneratedSuccessful_settings windowsGeneratedSuccessful;
    private OnTokenSuccessful_settings tokenSuccessful_settings;
    private OnTokenFail_settings tokenfail_settings;
    private OnCatchError catchError;


    //ON TOKEN FAIL
    public void callTokenfail_settings(String token_fail){
        tokenfail = token_fail;

        if(tokenfail_settings != null) tokenfail_settings.onTokenFail_settings(token_fail);
    }

    public OnTokenFail_settings gettokenfail_settings(){
        return tokenfail_settings;
    }

    public void settokenfail_settings(OnTokenFail_settings tokenfail_settings){
        this.tokenfail_settings = tokenfail_settings;
    }

    public interface OnTokenFail_settings{
        void onTokenFail_settings(String token_fail);
    }




    //ON TOKEN SUCCESSFUL
    public void callTokenSuccessful_settings(String token_successful){
        token = token_successful;

        if(tokenSuccessful_settings != null) tokenSuccessful_settings.onTokenSuccessful_settings(token_successful);
    }

    public OnTokenSuccessful_settings gettokenSuccessful_settings(){
        return tokenSuccessful_settings;
    }

    public void settokenSuccessful_settings(OnTokenSuccessful_settings tokenSuccessful_settings){
        this.tokenSuccessful_settings = tokenSuccessful_settings;
    }

    public interface OnTokenSuccessful_settings{
        void onTokenSuccessful_settings(String token_successful);
    }



    //ON DATA DOWNLOAD SUCCESSFUL
    public void callDataUploadSuccessful(String upload_successful){
        uploadSuccessful = upload_successful;

        if(dataUploadSuccessful != null) dataUploadSuccessful.onDataUploadSuccessful_settings(upload_successful);
    }

    public OnDataUploadSuccessful_settings getDataUploadSuccessful(){
        return dataUploadSuccessful;
    }

    public void setDataUploadSuccessful(OnDataUploadSuccessful_settings upload_successful){
        this.dataUploadSuccessful = upload_successful;
    }

    public interface OnDataUploadSuccessful_settings{
        void onDataUploadSuccessful_settings(String upload_successful);
    }



    //ON DELETE DATA SUCCESSFUL
    public void callDeleteDataSuccessful (Model_getData_reading Successful){
        delete_successful = Successful;
        if(dataDeleteSuccessful != null) dataDeleteSuccessful.onDataDeleteSuccessful_settings(Successful);
    }

    public OnDataDeleteSuccessful_settings getDataDeleteSuccessful(){
        return dataDeleteSuccessful;
    }

    public void setDataDeleteSuccessful(OnDataDeleteSuccessful_settings delete_Successful){
        this.dataDeleteSuccessful = delete_Successful;
    }

    public interface OnDataDeleteSuccessful_settings{
        void onDataDeleteSuccessful_settings(Model_getData_reading data_array);
    }



    //ON INSERT DATA SUCCESSFUL
    public void callDataInsertIntoRoomSuccessful (String insert_successful){
        insert_Successful = insert_successful;
        if(dataInsertIntoRoomSuccessful != null) dataInsertIntoRoomSuccessful.onDataInsertIntoRoomSuccessful_settings(insert_successful);
    }

    public OnDataInsertIntoRoomSuccessful_settings getDataInsertIntoRoomSuccessful(){
        return dataInsertIntoRoomSuccessful;
    }

    public void setDataInsertIntoRoomSuccessful(OnDataInsertIntoRoomSuccessful_settings insert_successful){
        this.dataInsertIntoRoomSuccessful = insert_successful;
    }

    public interface OnDataInsertIntoRoomSuccessful_settings{
        void onDataInsertIntoRoomSuccessful_settings(String insert_successful);
    }




    //ON WINDOW GENERATED SUCCESSFUL
    public void callWindowsGeneratedSuccessful (String window_successful){
        window_Successful = window_successful;
        if(windowsGeneratedSuccessful != null) windowsGeneratedSuccessful.onDataInsertIntoRoomSuccessful_settings(window_successful);
    }

    public OnWindowsGeneratedSuccessful_settings getWindowsGeneratedSuccessful(){
        return windowsGeneratedSuccessful;
    }

    public void setWindowsGeneratedSuccessful(OnWindowsGeneratedSuccessful_settings window_successful){
        this.windowsGeneratedSuccessful = window_successful;
    }

    public interface OnWindowsGeneratedSuccessful_settings{
        void onDataInsertIntoRoomSuccessful_settings(String windowsGeneratedSuccessful);
    }



    //TRY AND CATCH ERROR - DISPLAY TOAST
    public void callCatchError (String error_message){
        errormessage = error_message;
        if(catchError != null) catchError.onCatchError(error_message);
    }

    public OnCatchError getCatchError(){
        return catchError;
    }

    public void setCatchError(OnCatchError error_message){
        this.catchError = error_message;
    }

    public interface OnCatchError{
        void onCatchError(String error_message);
    }
}
