package com.tscan.app.Fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tscan.app.API.Model_getData;
import com.tscan.app.API.Model_getData_reading;
import com.tscan.app.API.Model_getToken;
import com.tscan.app.API.Model_submitResult;
import com.tscan.app.API.UnsafeOkHttpClient;
import com.tscan.app.API.api;
import com.tscan.app.Activities.Activity_login;
import com.tscan.app.Dao.Dao_join;
import com.tscan.app.Data.Model_haccp_corrective_action_type;
import com.tscan.app.Data.Model_haccp_food_item_categories;
import com.tscan.app.Data.Model_haccp_food_item_types;
import com.tscan.app.Data.Model_haccp_location;
import com.tscan.app.Data.Model_haccp_task_category;
import com.tscan.app.Data.Model_haccp_task_definition;
import com.tscan.app.Data.Model_haccp_task_result_core_cooking;
import com.tscan.app.Data.Model_haccp_task_result_status;
import com.tscan.app.Data.Model_haccp_task_result_type;
import com.tscan.app.Data.Model_haccp_task_window;
import com.tscan.app.Data.Model_join;
import com.tscan.app.Data.Model_mobile_device;
import com.tscan.app.Data.Singleton_Settings;
import com.tscan.app.Database.Database_HACCP;
import com.tscan.app.R;
import com.tscan.app.UI_listeners.UI_Listener_settings;
import com.tscan.app.Utils.CheckWifiConnection;
import com.tscan.app.Utils.Utils;
import com.tscan.app.ViewModel.ViewModel_haccp_queries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.tscan.app.Activities.Activity_login.database_HACCP;

//import com.tscan.app.UI_listeners.ExceptionHandler;

public class Fragment_settings extends Fragment {

    private CardView btn_logout;
    private CardView btn_token;
    private CardView btn_delete_local_data;
    private CardView btn_refresh_local_data;
    private CardView btn_upload_records_to_national;

    private TextView settings_upload_data_tv, mobile_device_name, mobile_device_serial_number, company_id,
            company_name, department_id, department_name, settings_upload_records_to_national_tv;

    private CardView settings_progressbar;
    private ProgressBar settings_progressbar_spin;
    private ImageView settings_progressbar_icon;
    private TextView settings_progressbar_text;

    private static ViewModel_haccp_queries viewModel_haccp_queries;
    private static UI_Listener_settings ui_listener_settings = new UI_Listener_settings();

    private Database_HACCP database_haccp;
    private Dao_join dao_join;

    private CheckWifiConnection checkWifiConnection;
    private Handler handler = new Handler();
    private Runnable runnable;


/////////////////////////////////////////////////////////////////////////
//   ON CREATE                                                         //
/////////////////////////////////////////////////////////////////////////
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        /////////////////////////////////////////////////////////////////////////
        //   INITIALIZATION                                                    //
        /////////////////////////////////////////////////////////////////////////
        btn_logout = view.findViewById(R.id.settings_logout);
        btn_token = view.findViewById(R.id.settings_test_token);
        btn_refresh_local_data = view.findViewById(R.id.settings_upload_data);
        btn_delete_local_data = view.findViewById(R.id.settings_refresh);
        btn_upload_records_to_national = view.findViewById(R.id.settings_upload_records_to_national);
        mobile_device_name = view.findViewById(R.id.mobile_device_name);
        mobile_device_serial_number = view.findViewById(R.id.mobile_device_serial_number);
        company_id = view.findViewById(R.id.company_id);
        company_name = view.findViewById(R.id.company_name);
        department_id  = view.findViewById(R.id.department_id);
        department_name = view.findViewById(R.id.department_name);
        settings_upload_data_tv = view.findViewById(R.id.settings_upload_data_tv);
        settings_upload_records_to_national_tv = view.findViewById(R.id.settings_upload_records_to_national_tv);

        settings_progressbar = view.findViewById(R.id.settings_progressbar);
        settings_progressbar_spin = view.findViewById(R.id.settings_progressbar_spin);
        settings_progressbar_icon = view.findViewById(R.id.settings_progressbar_icon);
        settings_progressbar_text = view.findViewById(R.id.settings_progressbar_text);

        viewModel_haccp_queries = ViewModelProviders.of(this).get(ViewModel_haccp_queries.class);

        database_haccp = Database_HACCP.getDatabase_haccp(getActivity());
        dao_join = database_haccp.dao_join();

        checkWifiConnection = new CheckWifiConnection();

        /////////////////////////////////////////////////////////////////////////
        //   ONCLICK LISTENERS                                                 //
        /////////////////////////////////////////////////////////////////////////
        btn_refresh_local_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkWifiConnection.isInternetConnected(getContext())){
                    show_dialog_spinner("Refreshing local data...", v);
                    new Async_retrofit_get_data().execute();
                }
                else{
                    Utils.warning_dialog(getContext(), "No internet connection", "Please connect to Wifi to proceed.");
                }
            }
        });

        btn_delete_local_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_dialog_spinner("Deleting data...", v);
                new Async_clear_data().execute();

                handler.postDelayed(() -> dismiss_dialog(), 2000);
            }
        });

        btn_upload_records_to_national.setOnClickListener(v -> {
            if(checkWifiConnection.isInternetConnected(getContext())){
                show_dialog_spinner("Uploading data...", v); new Async_upload_records_to_national().execute(dao_join);
            }
            else{
                Utils.warning_dialog(getContext(), "No internet connection", "Please connect to Wifi to proceed.");
            }
        });

        btn_token.setOnClickListener(v -> {
            if(checkWifiConnection.isInternetConnected(getContext())){
                show_dialog_spinner("Refreshing token...", v);
                new Async_test_api().execute();
            }
            else{
                Utils.warning_dialog(getContext(), "No internet connection", "Please connect to Wifi to proceed.");
            }
        });

        btn_logout.setOnClickListener(v -> {
            Utils.avoid_double_click(v); // this is to avoid the event to tiigger 2 times in a row
            Activity_login.user_model.setUser_name(null);
            Activity_login.user_model.setUser_initials(null);
            Singleton_Settings.getsettings_instance().setCurrent_user(null);

            Intent intent_mainactivity = new Intent(getActivity(), Activity_login.class);
            startActivity(intent_mainactivity);
        });

        /////////////////////////////////////////////////////////////////////////
        //   LISTENERS                                                         //
        /////////////////////////////////////////////////////////////////////////
        ui_listener_settings.setDataDeleteSuccessful(data_array -> {
            Log.i("Listener_onTablesDeleted", String.valueOf(data_array));
            new Async_download_data_to_room().execute(data_array);
        });

//        ui_listener_settings.setDataInsertIntoRoomSuccessful(insert_successful -> new Async_generate_windows().execute());

        ui_listener_settings.setWindowsGeneratedSuccessful(windowsGeneratedSuccessful -> show_dialog_successful("Tasks have been generated successfully"));

        ui_listener_settings.setDataUploadSuccessful(upload_successful -> {
            show_dialog_successful("Your records have been uploaded successfully");
        });

        ui_listener_settings.settokenSuccessful_settings(token_successful -> {
            show_dialog_successful("Token has refreshed successfully");
        });

        ui_listener_settings.settokenfail_settings(token_fail -> {
            show_dialog_fail("Failed refreshing token");
        });

        ui_listener_settings.setCatchError(error_message -> {
            dismiss_dialog();
            Utils.warning_dialog(getContext(),"Error caught","Something went wrong. Check your internet connection and if the issue is not resolved, please contact t-Scan." );
        });

        return view;
    }


/////////////////////////////////////////////////////////////////////////
//   LIFECYCLE                                                         //
/////////////////////////////////////////////////////////////////////////
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("lifecycle_fragment_stng", "onActivityCreated");
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.i("lifecycle_fragment_stng", "onStart");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i("lifecycle_fragment_stng", "onResume");
        set_UI();
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.i("lifecycle_fragment_stng", "onPause");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.i("lifecycle_fragment_stng", "onStop");
        // remove Handler callbacks
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("lifecycle_fragment_dash", "onDestroy");
    }


/////////////////////////////////////////////////////////////////////////
//   FUNCTIONS                                                         //
/////////////////////////////////////////////////////////////////////////
    private void set_UI() {
        mobile_device_name.setText(Singleton_Settings.getsettings_instance().getMobile_device_name());
        mobile_device_serial_number.setText(Singleton_Settings.getsettings_instance().getMobile_device_serial_number());
        company_id.setText(String.valueOf(Singleton_Settings.getsettings_instance().getCompany_id()));
        company_name.setText(Singleton_Settings.getsettings_instance().getCompany_name());
        department_id.setText(String.valueOf(Singleton_Settings.getsettings_instance().getDepartment_id()));
        department_name.setText(Singleton_Settings.getsettings_instance().getDepartment_name());
    }

    private void disable_user_interaction(){
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private void able_user_interaction(){
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private void show_dialog_spinner(String text, View v){
        settings_progressbar.setVisibility(View.VISIBLE);
        settings_progressbar_spin.setVisibility(View.VISIBLE);
        settings_progressbar_icon.setVisibility(View.GONE);
        settings_progressbar_text.setText(text);
        Utils.avoid_double_click(v); // this is to avoid the event to tiigger 2 times in a row
        disable_user_interaction(); // disable any other potential clicks will Asynctask operating
    }

    private void show_dialog_successful(String text){
        settings_progressbar.setVisibility(View.VISIBLE);
        settings_progressbar_spin.setVisibility(View.GONE);
        settings_progressbar_icon.setVisibility(View.VISIBLE);
        settings_progressbar_text.setText(text);
        settings_progressbar_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_completed_green));

        runnable = this::dismiss_dialog;
        handler.postDelayed(runnable, 2000);
    }

    private void show_dialog_fail(String text){
        settings_progressbar.setVisibility(View.VISIBLE);
        settings_progressbar_spin.setVisibility(View.GONE);
        settings_progressbar_icon.setVisibility(View.VISIBLE);
        settings_progressbar_text.setText(text);
        settings_progressbar_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_close));

        runnable = this::dismiss_dialog;
        handler.postDelayed(runnable, 2000);
    }

    private void dismiss_dialog(){
        settings_progressbar.setVisibility(View.GONE);
        able_user_interaction();
    }


    private static void decode_token(String token) {

        try {
            String[] split = token.split("\\.");
            try {
                JSONObject obj = new JSONObject(getJSON(split[1]));
                Log.i("tscan_decodeToken", String.valueOf(obj.getJSONObject("tscan")));

                Singleton_Settings.getsettings_instance().setToken(token);
                Singleton_Settings.getsettings_instance().setCompany_id(obj.getJSONObject("tscan").getInt("company_id"));
                Singleton_Settings.getsettings_instance().setCompany_name(String.valueOf(obj.getJSONObject("tscan").getString("company_name")));
                Singleton_Settings.getsettings_instance().setDepartment_id(obj.getJSONObject("tscan").getInt("department_id"));
                Singleton_Settings.getsettings_instance().setDepartment_name(String.valueOf(obj.getJSONObject("tscan").getString("department_name")));
                Singleton_Settings.getsettings_instance().setExp(String.valueOf((obj.getString("iss"))));
                Singleton_Settings.getsettings_instance().setIat(String.valueOf((obj.getString("iat"))));
                Singleton_Settings.getsettings_instance().setIss(String.valueOf((obj.getString("iss"))));
                Singleton_Settings.getsettings_instance().setMobile_device_name(String.valueOf(obj.getJSONObject("tscan").getString("mobile_device_name")));
                Singleton_Settings.getsettings_instance().setMobile_device_serial_number(String.valueOf(obj.getJSONObject("tscan").getString("mobile_device_serial_number")));

                Model_mobile_device device_table = new Model_mobile_device();
                device_table.setApi_key(String.valueOf(000));
                device_table.setCompany_id(Integer.parseInt(obj.getJSONObject("tscan").getString("company_id")));
                device_table.setDepartment_id(Integer.parseInt(obj.getJSONObject("tscan").getString("department_id")));
                device_table.setDevice_serial_nuber(000);
                device_table.setEnabled(0);
                device_table.setName("Why name");
                viewModel_haccp_queries.insert_moble_device(device_table);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static String getJSON(String strEncoded) throws UnsupportedEncodingException {
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        return new String(decodedBytes, "UTF-8");
    }


/////////////////////////////////////////////////////////////////////////
//   ASYNCTASK                                                         //
/////////////////////////////////////////////////////////////////////////
    private static class Async_clear_data extends AsyncTask<Model_getData_reading, Model_getData_reading, Model_getData_reading> {
    @Override
    protected Model_getData_reading doInBackground(Model_getData_reading... model_getData_readings) {

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

            return null;

        } catch(Exception e) {
            ui_listener_settings.callCatchError(e.getMessage());
            return null;
        }
    }
}

    private static class Async_delete_local_data extends AsyncTask<Model_getData_reading, Model_getData_reading, Model_getData_reading> {
        @Override
        protected Model_getData_reading doInBackground(Model_getData_reading... model_getData_readings) {

            Model_getData_reading passed_new_task_data = model_getData_readings[0];

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

            return passed_new_task_data;

            } catch(Exception e) {
                ui_listener_settings.callCatchError(e.getMessage());
                return null;
            }
        }
        protected void onPostExecute(Model_getData_reading result) {
            /** Re-upload all the tables by calling server*/
            /** THIS HSOULD BE AN ASYNCTASKLOADER INSTEAD...*/
            Log.i("Listener_settings", "onPost delete completed");
            if(result != null) {
                ui_listener_settings.callDeleteDataSuccessful(result);
            }
        }
    }

    private static class Async_retrofit_get_data extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            Log.i("Listener_settings", "Get Data start...");

            String token = Singleton_Settings.getsettings_instance().getToken();

            OkHttpClient okHttpClient = UnsafeOkHttpClient.getTokenHeader(token);
            Gson builder = new GsonBuilder().disableHtmlEscaping().setLenient().create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(api.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(builder))
                    .build();

            JSONObject paramObject = new JSONObject();
            try {
                paramObject.put("token", token);
                api api = retrofit.create(com.tscan.app.API.api.class);
                Call<Model_getData> call = api.login_getData(paramObject.toString());

                call.enqueue(new Callback<Model_getData>() {
                    @Override
                    public void onResponse(Call<Model_getData> call, Response<Model_getData> response) {
                        if(response.isSuccessful() ){
                            Log.i("Listener_settings" + " Upload onResponse", String.valueOf(response.body().getTask_data()));

                            Model_getData_reading data_array = (response.body().getTask_data());
                            new Async_delete_local_data().execute(data_array);
//                            new Async_download_data_to_room().execute(data_array);
                        }
                        else {
                            Log.i("Listener_settings", " Upload Failed");
                            ui_listener_settings.callCatchError("fail");

                            try {
                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                ui_listener_settings.callCatchError("fail");
                                Log.i("Listener_settings", " Upload fail " + (response.errorBody().string())) ;


                            } catch (Exception e) {
                                ui_listener_settings.callCatchError("fail");
                                Log.i("Listener_settings", " Upload fail " + (e)) ;

                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<Model_getData> call, Throwable t) {
                        Log.i("S_RETROFIT" ,"Failure" + (t));
                        ui_listener_settings.callCatchError("fail");
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
                ui_listener_settings.callCatchError("fail");
            }
            return null;
        }
    }

    private static class Async_download_data_to_room extends AsyncTask<Model_getData_reading, Model_getData_reading, List<Model_getData_reading.Model_getlocations>> {
        @Override
        protected List<Model_getData_reading.Model_getlocations> doInBackground(Model_getData_reading... model_getData_readings) {

            Log.i("Listener_settings" ,"Upload start...");

            List<Model_getData_reading.Model_gethaccp_task_categories> haccp_task_categories = model_getData_readings[0].getHaccp_task_categories();

            try {
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
//                    food_item_categories.setFood_category_name(haccp_food_item_categories.get(ee).getName_food_item_categories());
                    food_item_categories.setFood_category_temperature(haccp_food_item_categories.get(ee).getFood_item_categories_core_cooking_pass_temperature());

                    viewModel_haccp_queries.insert_haccp_food_category(food_item_categories);
                }


                List<Model_getData_reading.Model_getHaccp_food_item_types> haccp_food_item_types = model_getData_readings[0].getHaccp_food_item_types();
                for(int aa = 0; aa < haccp_food_item_types.size(); aa++) {

                    Model_haccp_food_item_types food_item_types = new Model_haccp_food_item_types();
                    food_item_types.setFood_type_id(haccp_food_item_types.get(aa).getId_food_item_types());
//                    food_item_types.setFood_type_name(haccp_food_item_types.get(aa).getName_food_item_types());
                    food_item_types.setFood_type_company_id(haccp_food_item_types.get(aa).getHaccp_food_item_company_id());
                    food_item_types.setFood_category_id(haccp_food_item_types.get(aa).getHaccp_food_item_category_id());
                    food_item_types.setFood_type_temperature(haccp_food_item_types.get(aa).getCore_cooking_pass_temperature());

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
                        Log.i("Listener_settings_window", String.valueOf(task_windows.get(m).getId()));
                        Log.i("Listener_settings_window", String.valueOf(task_windows.get(m).getEnd_time_unix()));

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

            } catch (Exception e) {
                ui_listener_settings.callCatchError(e.getMessage());
                return null;
            }
        }

        protected void onPostExecute(List<Model_getData_reading.Model_getlocations> result) {
            Log.i("Listener_settings_upload", "Almost done");
            if (result != null) {
                ui_listener_settings.callDataInsertIntoRoomSuccessful("successful");
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
//            List<Model_join> model_joins = database_HACCP.dao_join().query_test();
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
//                    int joined_upload_status;
//                    if(current_time > joined_window_end_time_unix ){
//                        joined_upload_status = 4;
//                    }
//                    else{
//                        joined_upload_status = 1;
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
//                    window_joined_data.setJoined_window_end_time_unix(joined_window_end_time_unix);
//                    window_joined_data.setJoined_result_count(model_joins.get(i).getResult_count());
//                    window_joined_data.setJoined_window_status(joined_upload_status);
//
//                    viewModel_haccp_queries.insert_windows_joined_data(window_joined_data);
//                        result = "completed";
//
//                    } catch (Exception e) {
//                        ui_listener_settings.callCatchError(e.getMessage());
//                        result = null;
//                    }
//                }
//                Log.i("Listener", "windows generated");
//            }
//
//            return result;
//        }
//        protected void onPostExecute(String result) {
//            Log.i("Listener_settings_upload", "Almost done");
//            if(result != null) {
//                ui_listener_settings.callWindowsGeneratedSuccessful("successful");
//            }
//        }
//    }

    private static class Async_upload_records_to_national extends AsyncTask<Dao_join, Model_haccp_task_result_core_cooking, String> {
        @Override
        protected String doInBackground(Dao_join... passed_data) {

            Dao_join dao = passed_data[0];
            List<Model_haccp_task_result_core_cooking> model_records_data = dao.get_records_to_upload();

            Log.i("Fragment_settings", String.valueOf(model_records_data.size()));

            for (int q = 0; q < model_records_data.size(); q++) {

                int item_position = q;

                if (model_records_data.get(q).getRecords_timestamp_uploaded_unix() == null){
                    Log.i("Fragment_settings", String.valueOf(model_records_data.get(q).getRecords_timestamp_uploaded_unix()));
                Log.i("Listener_settings", "Upload records start...");
                OkHttpClient okHttpClient = UnsafeOkHttpClient.getTokenHeader(Singleton_Settings.getsettings_instance().getToken());
                Gson builder = new GsonBuilder().disableHtmlEscaping().setLenient().create();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(api.BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(builder))
                        .build();

                JSONObject paramObject1 = new JSONObject();
                JSONArray paramArray1 = new JSONArray();
                JSONObject paramObject2 = new JSONObject();
                JSONObject paramObject3 = new JSONObject();

                try {
                    paramObject3.put("haccp_task_definition_id ", model_records_data.get(q).getRecords_task_definition_id());
                    paramObject3.put("haccp_food_item_category_id ", model_records_data.get(q).getRecords_food_item_category_id());
                    paramObject3.put("haccp_food_item_type_id ", model_records_data.get(q).getRecords_food_item_type_id());
                    paramObject3.put("food_item_freetext ", model_records_data.get(q).getRecords_food_item_freetext());
                    paramObject3.put("food_item_batch_number ", model_records_data.get(q).getRecords_batch_number());
                    paramObject3.put("pass_temperature ", model_records_data.get(q).getRecords_pass_temperature());
                    paramObject3.put("haccp_task_result_status_id ", model_records_data.get(q).getRecords_task_result_status_id());
                    paramObject3.put("initiated_by_user ", model_records_data.get(q).getRecords_initiated_by_user());
                    paramObject3.put("initiated_timestamp_unix ", model_records_data.get(q).getRecords_initiated_timestamp_unix());
                    paramObject3.put("initiated_device_serial_number ", model_records_data.get(q).getRecords_initiated_device_serial_number());
                    paramObject3.put("latest_corrective_action_type_id ", model_records_data.get(q).getRecords_latest_corrective_action_type_id());
                    paramObject3.put("latest_corrective_action_freetext ", model_records_data.get(q).getRecords_latest_corrective_action_freetext());
                    paramObject3.put("latest_reading ", model_records_data.get(q).getRecords_latest_reading());
                    paramObject3.put("latest_reading_timestamp_unix ", model_records_data.get(q).getRecords_latest_reading_timestamp_unix());
                    paramObject3.put("latest_reading_mobile_sensor_id ", model_records_data.get(q).getRecords_latest_reading_mobile_sensor_id());
                    paramObject3.put("latest_reading_device_serial_number ", model_records_data.get(q).getRecords_latest_reading_device_serial_number());
                    paramObject3.put("latest_reading_user ", model_records_data.get(q).getRecords_latest_reading_user());
                    paramObject3.put("records_timestamp_uploaded_unix ", model_records_data.get(q).getRecords_timestamp_uploaded_unix());

                    paramArray1.put(paramObject3);
                    paramObject2.put("haccp_task_result_core_cooking", paramArray1);
                    paramObject1.put("task_results", paramObject2);
                    Log.i("DATA_UPLOADED_TO_ND3", String.valueOf(paramObject1));

                    api api = retrofit.create(com.tscan.app.API.api.class);
                    Call<Model_submitResult> call = api.save_result(paramObject1.toString());
                    call.enqueue(new Callback<Model_submitResult>() {
                        @Override
                        public void onResponse(Call<Model_submitResult> call, Response<Model_submitResult> response) {
                            if (response.isSuccessful()) {
                                Log.i("DATA_UPLOADED_DONE", String.valueOf(response));

                                    int current_time = (int) (System.currentTimeMillis()/1000L);
                                    viewModel_haccp_queries.update_timestamp_uploaded_record(
                                            model_records_data.get(item_position).getRecords_task_definition_id(),
                                            model_records_data.get(item_position).getRecords_initiated_timestamp_unix(),
                                            current_time);



                            } else {
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                                    Log.i("DATA_SAVE_BOF", String.valueOf(jObjError));
                                } catch (Exception e) {
                                    Log.i("DATA_SAVE_ERF", String.valueOf(e));
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Model_submitResult> call, Throwable t) {
                            Log.i("DATA_SAVE_FAIL", String.valueOf(t));
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
            return null;
        }

        protected void onPostExecute(String result) {
            ui_listener_settings.callDataUploadSuccessful("successful");
        }
    }

    private static class Async_test_api extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
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
                        if (response.isSuccessful()) {
                            ui_listener_settings.callTokenSuccessful_settings("successful");
                            /** Call Decode_Token to decode the token and save results into SETTINGS_MODEL (Singleton)*/
                            decode_token(response.body().getToken());
                        } else {
                            try {
                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                Log.i("TOKEN_fail1", String.valueOf(jObjError));
                                ui_listener_settings.callCatchError("fail");
                            } catch (Exception e) {
                                Log.i("TOKEN_fail2", String.valueOf(e.getMessage()));
                                ui_listener_settings.callCatchError("fail");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Model_getToken> call, Throwable t) {
                        Log.i("TOKEN_fail3" , String.valueOf(t));
                    }
                });
            } catch (JSONException e) {
                ui_listener_settings.callCatchError("fail");
                e.printStackTrace();
            }

            return null;
        }
    }




    }

