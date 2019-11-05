package com.tscan.app.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tscan.app.API.Model_getData;
import com.tscan.app.API.Model_getData_reading;
import com.tscan.app.API.Model_getToken;
import com.tscan.app.API.UnsafeOkHttpClient;
import com.tscan.app.API.api;
import com.tscan.app.Adapters.Adapter_Login;
import com.tscan.app.Data.Model_current_user;
import com.tscan.app.Data.Model_haccp_corrective_action_type;
import com.tscan.app.Data.Model_haccp_food_item_categories;
import com.tscan.app.Data.Model_haccp_food_item_types;
import com.tscan.app.Data.Model_haccp_location;
import com.tscan.app.Data.Model_haccp_task_category;
import com.tscan.app.Data.Model_haccp_task_definition;
import com.tscan.app.Data.Model_haccp_task_result_status;
import com.tscan.app.Data.Model_haccp_task_result_type;
import com.tscan.app.Data.Model_haccp_task_window;
import com.tscan.app.Data.Singleton_Settings;
import com.tscan.app.Database.Database_HACCP;
import com.tscan.app.Fragments.Fragment_create_user;
import com.tscan.app.R;
import com.tscan.app.UI_listeners.UI_Listener_login;
import com.tscan.app.Utils.CheckWifiConnection;
import com.tscan.app.Utils.Decode_Token;
import com.tscan.app.Utils.Utils;
import com.tscan.app.ViewModel.ViewModel_haccp_queries;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class Activity_login extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private Adapter_Login user_adapter;
    private RecyclerView user_recyclerview;
    private CardView user_add, user_next;
    private TextView user_next_tv;

    public static CardView user_progress;
    private ProgressBar user_progress_spin;
    private ImageView user_progress_icon;
    private TextView user_progress_text;

    static public Model_current_user user_model = new Model_current_user();
    public static UI_Listener_login ui_listener_login = new UI_Listener_login();
    public final static int PAGES = 5;
    public final static int LOOPS = 1000;
    public final static int FIRST_PAGE = PAGES * LOOPS / 2;
    private static final int ASK_MULTIPLE_PERMISSION_REQUEST_CODE= 0;
    public static ViewModel_haccp_queries viewModel_haccp_queries;
    public static Database_HACCP database_HACCP;

    private CheckWifiConnection checkWifiConnection;

    private Handler handler = new Handler();
    private Runnable runnable;

/////////////////////////////////////////////////////////////////////////
//   ON CREATE                                                         //
/////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /////////////////////////////////////////////////////////////////////////
        //   INITIALIZATION                                                    //
        /////////////////////////////////////////////////////////////////////////
        user_recyclerview = findViewById(R.id.user_recyclerview);
        user_add = findViewById(R.id.user_add);
        user_next = findViewById(R.id.user_next);

        user_progress = findViewById(R.id.user_progress);
        user_progress_spin = findViewById(R.id.user_progress_spin);
        user_progress_icon = findViewById(R.id.user_progress_icon);
        user_progress_text = findViewById(R.id.user_progress_text);

        user_next_tv = findViewById(R.id.user_next_tv);
        user_adapter = new Adapter_Login(this);

        user_recyclerview.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        user_recyclerview.setLayoutManager(linearLayoutManager);
        user_recyclerview.setAdapter(user_adapter);

        viewModel_haccp_queries = ViewModelProviders.of(Activity_login.this).get(ViewModel_haccp_queries.class);
        database_HACCP = Database_HACCP.getDatabase_haccp(Activity_login.this);
        checkWifiConnection = new CheckWifiConnection();

        user_add.setBackground(getResources().getDrawable(R.drawable.bck_white_radius_100));
        user_next.setBackground(getResources().getDrawable(R.drawable.bck_white_radius_100));
        user_add.setCardElevation(4);

        /** Check for Permissions */
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.WAKE_LOCK}, ASK_MULTIPLE_PERMISSION_REQUEST_CODE);

        /////////////////////////////////////////////////////////////////////////
        //   ON CLICK LISTENERS                                                //
        /////////////////////////////////////////////////////////////////////////
        user_add.setOnClickListener(v -> {
            Utils.avoid_double_click(v); // this is to avoid the event to tiigger 2 times in a row (in this case it would open the fragment 2 times...)

            Fragment_create_user fragment = new Fragment_create_user();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_out_left);
            ft.replace(R.id.fragment_create_user, fragment);
            ft.addToBackStack(null);
            ft.commit();
        });

        user_next.setOnClickListener(v -> {
            Utils.avoid_double_click(v); // this is to avoid the event to tiigger 2 times in a row

            if (user_model.getUser_name() == null) {
                Toast.makeText(getApplicationContext(), "Create or pick a user from the list", Toast.LENGTH_LONG).show();
            } else {
                if(checkWifiConnection.isInternetConnected(Activity_login.this)){
                    disable_user_interaction();
                    user_progress.setVisibility(View.VISIBLE);
                    get_mobile_token();
                }
                else{
                    alertdialog_wifi();
                    }
            }
        });
    }


/////////////////////////////////////////////////////////////////////////
//   LIFECYCLE                                                         //
/////////////////////////////////////////////////////////////////////////
    @Override
    public void onStart(){
        super.onStart();
        Log.i("lifecycle_Activity_login", "onStart");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i("lifecycle_Activity_login", "onResume");

        /////////////////////////////////////////////////////////////////////////
        //   ON CHANGE LISTENERS                                               //
        /////////////////////////////////////////////////////////////////////////
        viewModel_haccp_queries.getAllUserList().observe(this, user -> user_adapter.setData(user));

        /** Working with LOGIN_ADAPTER and SELECTED_USER_LISTENER. Whenerver a user_name item is selected the below functions update the UI accordingly*/
        ui_listener_login.setUserChangeListener(new UI_Listener_login.OnUserChangeListener() {
            @Override
            public void onUserChange(String username) {
                user_next.setBackground(getResources().getDrawable(R.drawable.bck_ripple_green_green_radius_100));
                user_next_tv.setTextColor(getResources().getColor(R.color.white));
            }
        });

        ui_listener_login.setUserNullListener(new UI_Listener_login.OnUserNullListener() {
            @Override
            public void onUserNull() {
                user_next.setBackground(getResources().getDrawable(R.drawable.bck_white_radius_100));
                user_next.setCardElevation(4);
                user_next_tv.setTextColor(getResources().getColor(R.color.grey));
            }
        });

        ui_listener_login.setLoginToken_Fail(new UI_Listener_login.OnLoginToken_Fail() {
            @Override
            public void onLoginToken_Fail(String Fail) {
                dismiss_dialog();
                Utils.warning_dialog(Activity_login.this, "Server Issue", "Check your internet connection and try again. if this is a persistent issue, please contact t-Scan.");
            }
        });

        ui_listener_login.setDataDownloadFail(new UI_Listener_login.OnDataDownloadFail() {
            @Override
            public void onDataDownloadFail(String download_fail) {
                show_dialog_fail("Failed refreshing data, Please check your WIFI connection. if this is a percistent issue, please contact t-Scan.");
//                able_user_interaction();
//                user_progress.setVisibility(View.GONE);
//                Utils.warning_dialog(Activity_login.this, "Server Issue", "Check your internet connection and try again.");
            }
        });

        /** When TOKEN is confirmed and Task data downloaded successfully, below listener insert data in ROOM */
        ui_listener_login.setTablesEmptyListener(new UI_Listener_login.OnTablesEmptyListener() {
            @Override
            public void onTablesDeleted(Model_getData_reading data_tasks) {
                Log.i("Listener_onTablesDeleted", String.valueOf(data_tasks));
                new Async_upload_data_to_room().execute(data_tasks);
            }
        });

        /** When data have been successfully inserted into ROOM, below listener generates the cached data */
        ui_listener_login.setDataRefreshListener(new UI_Listener_login.OnDataRefreshListener() {
            @Override
            public void onDataRefreshListener(String status) {
                Log.i("Listener", "tables refreshed");
//                new Async_generate_windows().execute();
                Intent intent_mainactivity = new Intent(Activity_login.this, Activity_main.class);
                startActivity(intent_mainactivity);
                finish();
            }
        });

        /** When data are now cached successfully, go to next page */
//        ui_listener_login.setWindowsGeneratedSuccessful(new UI_Listener_login.OnWindowsGeneratedSuccessful() {
//            @Override
//            public void onDataInsertIntoRoomSuccessful(String windowsGeneratedSuccessful) {
//                Intent intent_mainactivity = new Intent(Activity_login.this, Activity_main.class);
//                startActivity(intent_mainactivity);
//                finish();
//            }
//        });

        ui_listener_login.setCatchError(new UI_Listener_login.OnCatchError() {
            @Override
            public void onCatchError(String error_message) {
                Utils.warning_dialog(Activity_login.this,"Error caught","Something went wrong. Check your internet connection and if the issue is not resolved, please contact t-Scan." );
            }
        });
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.i("lifecycle_Activity_login", "onPause");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.i("lifecycle_Activity_login", "onStop");
        viewModel_haccp_queries.getAllUserList().removeObservers(Activity_login.this);

        // remove Handler callbacks
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("lifecycle_Activity_login", "onDestroy");
    }



/////////////////////////////////////////////////////////////////////////
//   FUNCTIONS                                                         //
/////////////////////////////////////////////////////////////////////////
    private void alertdialog_wifi() {

    AlertDialog.Builder alertDialog = new AlertDialog.Builder(Activity_login.this, R.style.AlertDialogCustom);
    alertDialog.setTitle("No Internet connection");
    alertDialog.setMessage("Your data won't be up to date");

    alertDialog.setNegativeButton("Log me in anyway", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            startActivity(new Intent(Activity_login.this, Activity_main.class));
        }
    });

    alertDialog.setPositiveButton("Turn Wifi ON", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            WifiManager wifiManager = (WifiManager) Activity_login.this.getSystemService(Context.WIFI_SERVICE);
            wifiManager.setWifiEnabled(true);

            user_progress.setVisibility(View.VISIBLE);
            runnable = () -> {
                if(checkWifiConnection.isInternetConnected(Activity_login.this)){
                    disable_user_interaction();
                    get_mobile_token();
                }
                else{
                    user_progress.setVisibility(View.GONE);
                }};
            handler.postDelayed(runnable, 5000);

        }
    });
    alertDialog.show();
}

    void disable_user_interaction(){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    void able_user_interaction(){
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private void show_dialog_fail(String text){
        user_progress.setVisibility(View.VISIBLE);
        user_progress_spin.setVisibility(View.GONE);
        user_progress_icon.setVisibility(View.VISIBLE);
        user_progress_text.setText(text);
        user_progress_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_close));

        runnable = this::dismiss_dialog;
        handler.postDelayed(runnable, 2000);
    }

    private void dismiss_dialog(){
        user_progress.setVisibility(View.GONE);
        able_user_interaction();
    }

    private void get_mobile_token() {
        OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
        Gson builder = new GsonBuilder().disableHtmlEscaping().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(builder))
                .build();
        api api = retrofit.create(com.tscan.app.API.api.class);

        JSONObject paramObject = new JSONObject();
        try {
            paramObject.put("company_id", 16);
            paramObject.put("device_api_key", "DEF");
            paramObject.put("device_serial_number", "ABC");

            Call<Model_getToken> call = api.login_getToken(paramObject.toString());
            call.enqueue(new Callback<Model_getToken>() {
                @Override
                public void onResponse(Call<Model_getToken> call, Response<Model_getToken> response) {
                    if(response.isSuccessful() ){
                        /** Decode token and save results into SETTINGS_MODEL (Singleton)*/
                        new Decode_Token().decode_token(response.body().getToken(), Activity_login.this);
                        new Async_download_data().execute(response.body().getToken());
                    }
                    else {
                        able_user_interaction();
                        user_progress.setVisibility(View.GONE);
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Log.i("TOKEN_fail1", String.valueOf(jObjError));
                            ui_listener_login.callLoginFail_listener("fail");
                        } catch (Exception e) {
                            Log.i("TOKEN_fail2", String.valueOf(e.getMessage()));
                            ui_listener_login.callLoginFail_listener("fail");
                        }
                    }
                }
                @Override
                public void onFailure(Call<Model_getToken> call, Throwable t) {
                    able_user_interaction();
                    user_progress.setVisibility(View.GONE);
                    Log.i("TOKEN_fail3" , String.valueOf(t));
                    ui_listener_login.callLoginFail_listener("fail");
                }
            });
        } catch (JSONException e) {
            ui_listener_login.callLoginFail_listener("fail");
            e.printStackTrace();
        }
    }



/////////////////////////////////////////////////////////////////////////
//   ASYNC TASKS                                                       //
/////////////////////////////////////////////////////////////////////////

    private static class Async_deleteLocalData extends AsyncTask<Model_getData_reading, Model_getData_reading, Model_getData_reading> {
        @Override
        protected Model_getData_reading doInBackground(Model_getData_reading... model_getData_readings) {

            Model_getData_reading new_task_data = model_getData_readings[0];

            /** Delete the content of all ROOM Tables*/
            try {
            viewModel_haccp_queries.delete_haccp_location();
            viewModel_haccp_queries.delete_haccp_food_item_categories();
            viewModel_haccp_queries.delete_haccp_food_item_types();
            viewModel_haccp_queries.delete_haccp_corrective_actions();
            viewModel_haccp_queries.delete_haccp_task_category();
            viewModel_haccp_queries.delete_haccp_task_definition();
            viewModel_haccp_queries.delete_haccp_task_result_status();
            viewModel_haccp_queries.delete_haccp_task_result_type();
            viewModel_haccp_queries.delete_haccp_task_window();
            viewModel_haccp_queries.delete_model_joins();
//            viewModel_haccp_queries.delete_model_window_joined_data();

            return new_task_data;

            } catch(Exception e) {
                ui_listener_login.callCatchError(e.getMessage());
                return null;
            }
        }

        protected void onPostExecute(Model_getData_reading result) {
            if(result != null) {
                ui_listener_login.setEmptyTablesListener(result);
            }
        }
    }

    private static class Async_download_data extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            String tok = strings[0];

            OkHttpClient okHttpClient = UnsafeOkHttpClient.getTokenHeader(tok);
            Gson builder = new GsonBuilder().disableHtmlEscaping().setLenient().create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(api.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(builder))
                    .build();

            JSONObject paramObject = new JSONObject();
            try {
                paramObject.put("token", tok);
                api api = retrofit.create(com.tscan.app.API.api.class);
                Call<Model_getData> call = api.login_getData(paramObject.toString());

                call.enqueue(new Callback<Model_getData>() {
                    @Override
                    public void onResponse(Call<Model_getData> call, Response<Model_getData> response) {
                        if(response.isSuccessful() ){
                            Log.i("Login_get_data" + " Successful: ", String.valueOf(response.body().getTask_data()));

                            Model_getData_reading data_array = (response.body().getTask_data());
                            new Async_deleteLocalData().execute(data_array);
                        }
                        else {
                            Log.i("Login_get_data", "Failed");
                            ui_listener_login.callDownloadData_Fail("fail");
                            try {
                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                ui_listener_login.callCatchError("fail");
                                Log.i("Login_get_data", "fail " + (jObjError)) ;
                            } catch (Exception e) {
                                ui_listener_login.callCatchError("fail");
                                Log.i("Login_get_data", "fail " + (e)) ;
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<Model_getData> call, Throwable t) {
                        ui_listener_login.callDownloadData_Fail("fail");
                        Log.i("Alarm_upload" ,"Failure" + (t));
                    }
                });
            } catch (JSONException e) {
                ui_listener_login.callDownloadData_Fail("fail");
                e.printStackTrace();
            }
            return null;
        }
    }

    private static class Async_upload_data_to_room extends AsyncTask<Model_getData_reading, Model_getData_reading, List<Model_getData_reading.Model_getlocations>> {
        @Override
        protected List<Model_getData_reading.Model_getlocations> doInBackground(Model_getData_reading... model_getData_readings) {

            Log.i("Listener_room", "put data in room");

//            try {

            List<Model_getData_reading.Model_gethaccp_task_categories> haccp_task_categories = model_getData_readings[0].getHaccp_task_categories();

            for(int h = 0; h < haccp_task_categories.size(); h++) {
                int task_categories_id = (haccp_task_categories.get(h).getId());
                String task_categories_name = haccp_task_categories.get(h).getName();
                int task_categories_company_id = (haccp_task_categories.get(h).getCompany_id());
                int task_categories_department_id = (haccp_task_categories.get(h).getDepartment_id());
                int task_categories_sort_order = Integer.parseInt(haccp_task_categories.get(h).getSort_order());

                /**  CREATE haccp_task_category*/
                Model_haccp_task_category haccp_task_category = new Model_haccp_task_category();
                haccp_task_category.setId(task_categories_id);
                haccp_task_category.setName(task_categories_name);
                haccp_task_category.setCompany_id(task_categories_company_id);
                haccp_task_category.setDepartment_id(task_categories_department_id);
                haccp_task_category.setSort_order(task_categories_sort_order);

                viewModel_haccp_queries.insert_haccp_task_category(haccp_task_category);
            }

            List<Model_getData_reading.Model_gethaccp_task_result_statuses> haccp_task_result_statuses = model_getData_readings[0].getHaccp_task_result_statuses();

            for(int g = 0; g < haccp_task_result_statuses.size(); g++) {
                int task_result_statuses_types_id = haccp_task_result_statuses.get(g).getId();
                String task_result_statuses_types_name = haccp_task_result_statuses.get(g).getName();
                int task_result_statuses_task_result_type_id = haccp_task_result_statuses.get(g).getHaccp_task_result_type_id();
                String task_result_statuses_corrective_action_required = haccp_task_result_statuses.get(g).getCorrective_action_required();

                /**  CREATE haccp_task_result_status**/
                Model_haccp_task_result_status haccp_task_result_status = new Model_haccp_task_result_status();
                haccp_task_result_status.setId(task_result_statuses_types_id);
                haccp_task_result_status.setName(task_result_statuses_types_name);
                haccp_task_result_status.setCorrective_action_required(task_result_statuses_corrective_action_required);
                haccp_task_result_status.setHaccp_task_result_type_id(task_result_statuses_task_result_type_id);

                viewModel_haccp_queries.insert_haccp_task_result_status(haccp_task_result_status);
            }

            List<Model_getData_reading.Model_gethaccp_task_result_types> haccp_task_result_types = model_getData_readings[0].getHaccp_task_result_types();

            for(int f = 0; f < haccp_task_result_types.size(); f++) {
                int task_result_types_id = haccp_task_result_types.get(f).getId();
                String task_result_types_name = haccp_task_result_types.get(f).getName();

                /**  CREATE haccp_task_result_type*/
                Model_haccp_task_result_type haccp_task_result_type = new Model_haccp_task_result_type();
                haccp_task_result_type.setId(task_result_types_id);
                haccp_task_result_type.setName(task_result_types_name);

                viewModel_haccp_queries.insert_haccp_task_result_type(haccp_task_result_type);
            }


            List<Model_getData_reading.Model_gethaccp_food_item_categories> haccp_food_item_categories = model_getData_readings[0].getHaccp_food_item_categories();
                for(int ee = 0; ee < haccp_food_item_categories.size(); ee++) {
                    Model_haccp_food_item_categories food_item_categories = new Model_haccp_food_item_categories();
                    food_item_categories.setFood_category_id(haccp_food_item_categories.get(ee).getId_food_item_categories());
                    food_item_categories.setFood_category_company_id(haccp_food_item_categories.get(ee).getCompany_id_food_item_categories());
                    food_item_categories.setFood_category_name(haccp_food_item_categories.get(ee).getName_food_item_categories());
                    food_item_categories.setFood_category_temperature(haccp_food_item_categories.get(ee).getFood_item_categories_core_cooking_pass_temperature());

                    viewModel_haccp_queries.insert_haccp_food_category(food_item_categories);
                }


            List<Model_getData_reading.Model_getHaccp_food_item_types> haccp_food_item_types = model_getData_readings[0].getHaccp_food_item_types();
                for(int aa = 0; aa < haccp_food_item_types.size(); aa++) {

                Model_haccp_food_item_types food_item_types = new Model_haccp_food_item_types();
                food_item_types.setFood_type_id(haccp_food_item_types.get(aa).getId_food_item_types());
                food_item_types.setFood_type_name(haccp_food_item_types.get(aa).getName_food_item_types());
                food_item_types.setFood_type_company_id(haccp_food_item_types.get(aa).getHaccp_food_item_company_id());
                food_item_types.setFood_category_id(haccp_food_item_types.get(aa).getHaccp_food_item_category_id());
                food_item_types.setFood_type_temperature(haccp_food_item_types.get(aa).getCore_cooking_pass_temperature());
                    Log.i("Login_upload11", String.valueOf(haccp_food_item_types.get(aa).getHaccp_food_item_category_id()));
                    Log.i("Login_upload12", String.valueOf(haccp_food_item_types.get(aa).getHaccp_food_item_company_id()));
                    Log.i("Login_upload13", String.valueOf(haccp_food_item_types.get(aa).getId_food_item_types()));
                    Log.i("Login_upload14", String.valueOf(haccp_food_item_types.get(aa).getName_food_item_types()));

                    viewModel_haccp_queries.insert_haccp_food_type(food_item_types);
                }


              List<Model_getData_reading.Model_getHaccp_corrective_actions> haccp_corrective_actions = model_getData_readings[0].getHaccp_corrective_actions();
                for(int ff = 0; ff < haccp_corrective_actions.size(); ff++) {
                    Model_haccp_corrective_action_type corrective_action_type = new Model_haccp_corrective_action_type();
                    corrective_action_type.setCorrective_action_id(haccp_corrective_actions.get(ff).getCorrective_action_id());
                    corrective_action_type.setCorrective_action_company_id(haccp_corrective_actions.get(ff).getCorrective_action_company_id());
                    corrective_action_type.setCorrective_action_haccp_task_result_type_id(haccp_corrective_actions.get(ff).getCorrective_action_haccp_task_result_type_id());
                    corrective_action_type.setCorrective_action_name(haccp_corrective_actions.get(ff).getCorrective_action_name());
                    corrective_action_type.setCorrective_action_new_haccp_task_result_status_id(haccp_corrective_actions.get(ff).getCorrective_action_new_haccp_task_result_status_id());

                    viewModel_haccp_queries.insert_haccp_corrective_actions(corrective_action_type);
                }

            List<Model_getData_reading.Model_getlocations> locations = model_getData_readings[0].getLocations();
            for(int e = 0; e < locations.size(); e++) {
                int location_id = locations.get(e).getId();
                int location_company_id = locations.get(e).getCompany_id();
                int location_department_id = locations.get(e).getDepartment_id();
                String location_name = locations.get(e).getName();

                /**  CREATE location*/
                Model_haccp_location haccp_location = new Model_haccp_location();
                haccp_location.setId(location_id);
                haccp_location.setName(location_name);
                haccp_location.setCompany_id(location_company_id);
                haccp_location.setDepartment_id(location_department_id);

                viewModel_haccp_queries.insert_haccp_location(haccp_location);

                /** For each location, get all the Task_definition:
                 * There will be several Locations in the future and each location will have
                 * several task_definition which will have several task_windows... better to break this into
                 * multiple backthreads to process it all*/

                List<Model_getData_reading.Model_getTask_definitions> task_def = locations.get(e).getHaccp_task_definitions();

                for(int a = 0; a < task_def.size(); a++) {
                    int task_definition_id = task_def.get(a).getTask_definition_id();
                    String task_definition_name = task_def.get(a).getTask_definition_name();
                    int task_definition_company_id = task_def.get(a).getTask_definition_company_id();
                    int task_definition_department_id = task_def.get(a).getTask_definition_department_id();
                    int task_definition_location_id = task_def.get(a).getTask_definition_location_id();
                    int task_definition_category_id = task_def.get(a).getTask_definition_task_category_id();
                    int task_definition_result_type_id = task_def.get(a).getTask_definition_task_result_type_id();
                    String task_definition_instruction_text = task_def.get(a).getTask_definition_instruction_text();
                    int task_definition_quantity_required = task_def.get(a).getTask_definition_quantity_required();
                    int task_definition_valid_from_unix = task_def.get(a).getTask_definition_valid_from_unix();
                    Integer task_definition_valid_to_unix = task_def.get(a).getTask_definition_valid_to_unix();
                    Integer task_definition_windows_generated_to_unix = task_def.get(a).getTask_definition_windows_generated_to_unix();

                    /**  CREATE haccp_task_definition*/
                    Model_haccp_task_definition haccp_task_definition = new Model_haccp_task_definition();
                    haccp_task_definition.setTask_definition_id(task_definition_id);
                    haccp_task_definition.setTask_definition_company_id(task_definition_company_id);
                    haccp_task_definition.setTask_definition_department_id(task_definition_department_id);
                    haccp_task_definition.setTask_definition_instruction_text(task_definition_instruction_text);
                    haccp_task_definition.setTask_definition_location_id(task_definition_location_id);
                    haccp_task_definition.setTask_definition_name(task_definition_name);
                    haccp_task_definition.setTask_definition_quantity_required(task_definition_quantity_required);
                    haccp_task_definition.setTask_definition_task_category_id(task_definition_category_id);
                    haccp_task_definition.setTask_definition_task_result_type_id(task_definition_result_type_id);
                    haccp_task_definition.setTask_definition_valid_from_unix(task_definition_valid_from_unix);
                    haccp_task_definition.setTask_definition_valid_to_unix(task_definition_valid_to_unix);
                    haccp_task_definition.setTask_definition_windows_generated_to_unix(task_definition_windows_generated_to_unix);

                    viewModel_haccp_queries.insert_haccp_task_definition(haccp_task_definition);

                    /** For each task_definition, call AsyncTask to generate all the task_windows*/
                    List<Model_getData_reading.Model_gethaccp_task_windows> task_windows = task_def.get(a).getTask_definition_task_windows();

                    for (int m = 0; m < task_windows.size(); m++) {
                        int task_window_id = task_windows.get(m).getId();
                        int task_window_task_definition_id = task_windows.get(m).getHaccp_task_definition_id();
                        int task_window_end_time_unix = task_windows.get(m).getEnd_time_unix();
                        int task_window_start_time_unix = task_windows.get(m).getStart_time_unix();

                        Model_haccp_task_window haccp_task_window = new Model_haccp_task_window();
                        haccp_task_window.setId(task_window_id);
                        haccp_task_window.setHaccp_task_definition_id(task_window_task_definition_id);
                        haccp_task_window.setStart_time_unix(task_window_start_time_unix);
                        haccp_task_window.setEnd_time_unix(task_window_end_time_unix);

                        viewModel_haccp_queries.insert_haccp_task_window(haccp_task_window);
                    }
                }
            }
                return locations;

//            } catch (Exception e) {
//                ui_listener_login.callCatchError(e.getMessage());
//                return null;
//            }
        }

        protected void onPostExecute(List<Model_getData_reading.Model_getlocations> result) {
            Log.i("Listener", "Almost done");
            if(result != null) {
                ui_listener_login.setRefreshDataListener("completed");
            }
        }
    }

//    private static class Async_generate_windows extends AsyncTask<String, String, String> {
//
//        /** WINDOW STATUS:
//         *          -  1 = Uncompleted
//         *          -  2 = Completed
//         *          -  3 = Failed (ex: temperature is below 75 degrees and the task the is flagged FAILED which means it is still to be completed
//         *          -  4 = Expired (only if the task has not been completed in time) */
//
//        @Override
//        protected String doInBackground(String... strings) {
//            List<Model_join> model_joins =  database_HACCP.dao_join().query_test();
//
//            String result = null;
//
//            if(model_joins != null) {
//                int current_time = (int) (System.currentTimeMillis()/1000L);
//                Log.i("Listener", "generating windows");
//
//                for(int i = 0; i < model_joins.size(); i++) {
//                    Log.i("Listener", String.valueOf(i));
//
//                    int joined_window_end_time_unix = model_joins.get(i).getWindow_end_time_unix();
//                    int joined_window_status;
//                    if(current_time > joined_window_end_time_unix ){
//                        joined_window_status = 4;
//                    }
//                    else{
//                        joined_window_status = 1;
//                    }
//
//                    try {
//                    Model_window_joined_data window_joined_data = new Model_window_joined_data();
//                    window_joined_data.setJoined_definition_id(model_joins.get(i).getTask_definition_id());
//                    window_joined_data.setJoined_definition_name(model_joins.get(i).getTask_definition_name());
//                    window_joined_data.setJoined_definition_company_id(model_joins.get(i).getTask_definition_company_id());
//                    window_joined_data.setJoined_definition_department_id(model_joins.get(i).getTask_definition_department_id());
//                    window_joined_data.setJoined_definition_location_id(model_joins.get(i).getTask_definition_location_id());
//                    window_joined_data.setJoined_definition_joined_category_id(model_joins.get(i).getTask_definition_task_category_id());
//                    window_joined_data.setJoined_definition_joined_result_type_id(model_joins.get(i).getTask_definition_task_result_type_id());
//                    window_joined_data.setJoined_definition_instruction_text(model_joins.get(i).getTask_definition_instruction_text());
//                    window_joined_data.setJoined_definition_quantity_required(model_joins.get(i).getTask_definition_quantity_required());
//                    window_joined_data.setJoined_definition_windows_generated_to_unix(model_joins.get(i).getTask_definition_windows_generated_to_unix());
//                    window_joined_data.setJoined_category_name(model_joins.get(i).getCategory_name());
//                    window_joined_data.setJoined_category_sort_order(model_joins.get(i).getCategory_sort_order());
//                    window_joined_data.setJoined_result_type_name(model_joins.get(i).getResult_type_name());
//                    window_joined_data.setJoined_window_id(model_joins.get(i).getWindow_id());
//                    window_joined_data.setJoined_window_start_time_unix(model_joins.get(i).getWindow_start_time_unix());
//                    window_joined_data.setJoined_window_end_time_unix(model_joins.get(i).getWindow_end_time_unix());
//                    window_joined_data.setJoined_result_count(model_joins.get(i).getResult_count());
//                    window_joined_data.setJoined_window_status(joined_window_status);
//
//                    viewModel_haccp_queries.insert_windows_joined_data(window_joined_data);
//
//                    result = "completed";
//
//                    } catch (Exception e) {
//                        ui_listener_login.callCatchError(e.getMessage());
//                        result = null;
//                    }
//                }
//                Log.i("Listener", "windows generated");
//            }
//
//            return result;
//        }
//
//        protected void onPostExecute(String result) {
//            Log.i("Listener", "DONE");
//            if(result != null) {
//                ui_listener_login.callWindowsGeneratedSuccessful("completed");
//            }
//        }
//    }
}


