package com.tscan.app.UI_listeners;

import com.tscan.app.API.Model_getData_reading;

public class UI_Listener_login {
    private String username;
    private Model_getData_reading tables_deleted;
    private String tables_refreshed;
    private String login_fail;
    private Model_getData_reading download_successful;
    private String download_fail;
    private String delete_fail;
    private String delete_successful;
    private String insert_Fail;
    private String insert_Successful;
    private String window_Fail;
    private String window_Successful;
    private String errormessage;
    private String userDuplicate;

    private OnUserChangeListener userChangeListener;
    private OnUserNullListener userNullListener;

    private OnTablesEmptyListener tablesListener;
    private OnDataRefreshListener dataRefreshListener;

    private OnLoginToken_Fail loginToken_fail;
    private OnDataDownloadFail dataDownloadFail;
//    private OnWindowsGeneratedFail windowsGeneratedFail;
//
//    private OnWindowsGeneratedSuccessful windowsGeneratedSuccessful;
    private OnCatchError catchError;

    private OnUserDuplicate userduplicate;





    // USER SELECTED LISTENER
    public void setUserVariable(String user_name){
        username = user_name;
        if(userChangeListener != null) userChangeListener.onUserChange(user_name);
    }

    public OnUserChangeListener getUserChangeListener() {
        return userChangeListener;
    }

    public void setUserChangeListener(OnUserChangeListener userChangeListener){
        this.userChangeListener = userChangeListener;
    }

    public interface OnUserChangeListener{
        void onUserChange(String username);
    }


    // USER UNSELECTED LISTENER
    public void setUserNull(String user_name){
        username = user_name;
        if(userNullListener != null) userNullListener.onUserNull();
    }

    public OnUserNullListener getUserNullListener() {
        return userNullListener;
    }

    public void setUserNullListener(OnUserNullListener userNullListener){
        this.userNullListener = userNullListener;
    }

    public interface OnUserNullListener{
        void onUserNull();
    }



    //DATA DELETED FROM ROOM
    public void setEmptyTablesListener(Model_getData_reading status){
        tables_deleted = status;
        if(tablesListener != null) tablesListener.onTablesDeleted(status);
    }

    public OnTablesEmptyListener getTablesEmptyListener(){
        return tablesListener;
    }

    public void setTablesEmptyListener(OnTablesEmptyListener tables_listener){
        this.tablesListener = tables_listener;
    }

    public interface OnTablesEmptyListener{
        void onTablesDeleted(Model_getData_reading status);
    }


    //DATA REFRESHED
    public void setRefreshDataListener(String refreshed){
        tables_refreshed = refreshed;
        if(dataRefreshListener != null) dataRefreshListener.onDataRefreshListener(refreshed);
    }

    public OnDataRefreshListener getDataRefreshedListener(){
        return dataRefreshListener;
    }

    public void setDataRefreshListener(OnDataRefreshListener tables_listener){
        this.dataRefreshListener = tables_listener;
    }

    public interface OnDataRefreshListener{
        void onDataRefreshListener(String status);
    }


    //ON LOGIN AND GET TOKEN FAIL
    public void callLoginFail_listener(String Fail){
        login_fail = Fail;

        if(loginToken_fail != null) loginToken_fail.onLoginToken_Fail(Fail);
    }

    public OnLoginToken_Fail getLoginFail(){
        return loginToken_fail;
    }

    public void setLoginToken_Fail(OnLoginToken_Fail login_Fail){
        this.loginToken_fail = login_Fail;
    }

    public interface OnLoginToken_Fail{
        void onLoginToken_Fail(String Fail);
    }


    //ON DATA DOWNLOAD FAIL
    public void callDownloadData_Fail(String download_Fail){
        download_fail = download_Fail;

        if(dataDownloadFail != null) dataDownloadFail.onDataDownloadFail(download_Fail);
    }

    public OnDataDownloadFail getDataDownloadFail(){
        return dataDownloadFail;
    }

    public void setDataDownloadFail(OnDataDownloadFail download_fail){
        this.dataDownloadFail = download_fail;
    }

    public interface OnDataDownloadFail{
        void onDataDownloadFail(String download_fail);
    }



//    //ON WINDOW GENERATED FAIL
//    public void callWindowsGeneratedFail (String window_fail){
//        window_Fail = window_fail;
//
//        if(windowsGeneratedFail != null) windowsGeneratedFail.onWindowsGeneratedFail(window_fail);
//    }
//
//    public OnWindowsGeneratedFail getWindowsGeneratedFail(){
//        return windowsGeneratedFail;
//    }
//
//    public void setWindowsGeneratedFail(OnWindowsGeneratedFail windowsGeneratedFail){
//        this.windowsGeneratedFail = windowsGeneratedFail;
//    }
//
//    public interface OnWindowsGeneratedFail{
//        void onWindowsGeneratedFail(String windowsGeneratedFail);}
//
//
    //ON WINDOW GENERATED SUCCESSFUL
//    public void callWindowsGeneratedSuccessful (String window_successful){
//        window_Successful = window_successful;
//        if(windowsGeneratedSuccessful != null) windowsGeneratedSuccessful.onDataInsertIntoRoomSuccessful(window_successful);
//    }
//
//    public OnWindowsGeneratedSuccessful getWindowsGeneratedSuccessful(){
//        return windowsGeneratedSuccessful;
//    }
//
//    public void setWindowsGeneratedSuccessful(OnWindowsGeneratedSuccessful window_successful){
//        this.windowsGeneratedSuccessful = window_successful;
//    }
//
//    public interface OnWindowsGeneratedSuccessful{
//        void onDataInsertIntoRoomSuccessful(String windowsGeneratedSuccessful);
//    }



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



    //ERROR USERNAME ALREADY EXIST
    public void callUserDuplicate (String user_duplicate){
        userDuplicate = user_duplicate;
        if(userduplicate != null) userduplicate.onUserDuplicate(user_duplicate);
    }

    public OnUserDuplicate getUserDuplicate(){
        return userduplicate;
    }

    public void setUserDuplicate(OnUserDuplicate user_duplicate){
        this.userduplicate = user_duplicate;
    }

    public interface OnUserDuplicate{
        void onUserDuplicate(String user_duplicate);
    }


}
