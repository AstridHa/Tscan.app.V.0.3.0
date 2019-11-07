package com.tscan.app.Fragments;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.tscan.app.Adapters.Adapter_food_corrective_action;
import com.tscan.app.Data.Model_haccp_task_result_core_cooking;
import com.tscan.app.Data.Singleton_Sensor;
import com.tscan.app.Data.Singleton_Settings;
import com.tscan.app.R;
//import com.tscan.app.UI_listeners.ExceptionHandler;
import com.tscan.app.UI_listeners.UI_Listener_Recording;
import com.tscan.app.Utils.Utils;
import com.tscan.app.ViewModel.ViewModel_haccp_queries;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.tscan.app.Fragments.Fragment_task_core.ui_listener_new_core_cooking;
import static com.tscan.app.Utils.Utils.action_open;

public class Fragment_pending_record extends Fragment implements View.OnClickListener {

    private ViewModel_haccp_queries viewModel_haccp_queries;
    private BluetoothAdapter mBluetoothAdapter;

    private TextView pending_status_title, pending_progress_tv, pending_temp, pending_food_type, pending_batch, pending_username, pending_date, scan_tempereture_value;
    private ProgressBar pending_progress_bar;
    private CardView pending_progress, pending_action_bin, pending_action_cook, pending_action_other;
    private RecyclerView pending_remedial_action_rv;
    private Adapter_food_corrective_action adapter_food_corrective_action;
    private LinearLayout task_corrective_action_text;
    private RelativeLayout task_temperature_frame, pending_toolbar, temperature_frame, pending_save_fab, pending_sensor_fab;
    private EditText task_corrective_action_text_et;
    private ImageView scan_circle, scan_pair_icon, pending_sensor_fab_iv;

    private Bundle intent;
    private Model_haccp_task_result_core_cooking record_data;

    private String record_username;
    private int record_batch;
    private String record_food_type;
    private String record_food_category;
    private int record_pass_temperature;
    private float record_temp;
    private long record_date;

    private int remedial_action_selected;
    private int remedial_new_status;

    /////////////////////////////////////////////////////////////////////////
//   ON CREATE                                                         //
/////////////////////////////////////////////////////////////////////////
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_pending_record, container, false);

        viewModel_haccp_queries = ViewModelProviders.of(this).get(ViewModel_haccp_queries.class);
        action_open = 1;

        /////////////////////////////////////////////////////////////////////////
        //   INITIALIZATION                                                    //
        /////////////////////////////////////////////////////////////////////////
        pending_toolbar = view.findViewById(R.id.pending_toolbar);
        pending_status_title = view.findViewById(R.id.pending_status_title);
        pending_temp = view.findViewById(R.id.pending_temp);
        pending_username = view.findViewById(R.id.pending_username);
        pending_batch = view.findViewById(R.id.pending_batch);
        pending_food_type = view.findViewById(R.id.pending_food_type);
        pending_date = view.findViewById(R.id.pending_date);
        pending_progress_bar = view.findViewById(R.id.pending_progress_bar);
        pending_progress_tv = view.findViewById(R.id.progress_tv);
        pending_progress = view.findViewById(R.id.pending_progress);

        pending_remedial_action_rv = view.findViewById(R.id.pending_remedial_action_rv);
        task_temperature_frame = view.findViewById(R.id.task_temperature_frame);
        task_corrective_action_text = view.findViewById(R.id.task_corrective_action_text);
        task_corrective_action_text_et = view.findViewById(R.id.task_corrective_action_text_et);

        temperature_frame = view.findViewById(R.id.task_temperature_frame);
        scan_circle = view.findViewById(R.id.scan_circle);
        scan_tempereture_value = view.findViewById(R.id.scan_tempereture_value);
        scan_pair_icon = view.findViewById(R.id.scan_pair_to_sensor);
        pending_save_fab = view.findViewById(R.id.pending_save_fab);
        pending_sensor_fab = view.findViewById(R.id.pending_sensor_fab);
        pending_sensor_fab_iv = view.findViewById(R.id.pending_sensor_fab_iv);

        adapter_food_corrective_action = new Adapter_food_corrective_action(getContext());
        pending_remedial_action_rv.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        pending_remedial_action_rv.setLayoutManager(linearLayoutManager);
        pending_remedial_action_rv.setAdapter(adapter_food_corrective_action);

        /** GET THE DATA FROM THE WINDOW THAT WAS CLICKED TO OPEN FRAGMENT_CORE_COOKING FRAGMENT */
        intent = getArguments();
        Log.i("DAT_Received", String.valueOf(intent.getString("data")));
        if (intent != null) {
            String bundle = intent.getString("data");
            Gson gson = new Gson();
            record_data = gson.fromJson(bundle, Model_haccp_task_result_core_cooking.class);
            pending_status_title.setText(String.valueOf(record_data.getRecords_task_definition_name()));
            record_temp = record_data.getRecords_latest_reading();
            record_username = record_data.getRecords_initiated_by_user();
            record_batch = record_data.getRecords_batch_number();
            record_food_category = record_data.getRecords_food_item_category_name();
            record_food_type = record_data.getRecords_food_item_type_name();
            record_date = record_data.getRecords_initiated_timestamp_unix();
            record_pass_temperature = record_data.getRecords_pass_temperature();
        }

        /////////////////////////////////////////////////////////////////////////
        //   ONCLICK LISTENER                                                  //
        /////////////////////////////////////////////////////////////////////////
        temperature_frame.setOnClickListener(Fragment_pending_record.this);
        pending_save_fab.setOnClickListener(Fragment_pending_record.this);
        pending_sensor_fab.setOnClickListener(Fragment_pending_record.this);
        pending_toolbar.setOnClickListener(Fragment_pending_record.this);

        /////////////////////////////////////////////////////////////////////////
        //   LISTENER SENSOR STATE                                             //
        /////////////////////////////////////////////////////////////////////////
        ui_listener_new_core_cooking.setRemedialActionSelected(new UI_Listener_Recording.OnRemedialActionSelected() {
            @Override
            public void onRemedialActionSelected(String remedialAction_name, int remedialAction_id, int remedialAction_newStatus) {
                if(remedialAction_name != null){
                   show_remedial_action_ui(remedialAction_id);
                    remedial_action_selected = remedialAction_id;
                    remedial_new_status = remedialAction_newStatus;
                }
            }
        });

        Singleton_Sensor.getSensor_instance().setValueChangeListener(new Singleton_Sensor.onConnectionChangeListener() {
            @Override
            public void onChange() {
                if (Singleton_Sensor.getSensor_instance().isConnected()) {

                    if (Singleton_Sensor.getSensor_instance().isConnected()) {
                        pending_sensor_fab_iv.setImageDrawable(view.getResources().getDrawable(R.drawable.ic_bluetooth_connected));
                        scan_circle.setImageDrawable(view.getResources().getDrawable(R.drawable.scan_circle_green));
                        scan_pair_icon.setVisibility(GONE);
                        scan_tempereture_value.setVisibility(VISIBLE);

                        setTemperature(view);

                    } else {
                        pending_sensor_fab_iv.setImageDrawable(view.getResources().getDrawable(R.drawable.ic_bluetooth_disconnected));
                        scan_circle.setImageDrawable(view.getResources().getDrawable(R.drawable.scan_circle_grey));
                        scan_pair_icon.setVisibility(VISIBLE);
                        scan_tempereture_value.setVisibility(GONE);
                    }
                }
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.pending_toolbar:
                action_open = 0;
                Log.i("action_open_frag", String.valueOf(action_open));
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(pending_toolbar.getWindowToken(), 0);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getFragmentManager().popBackStack();
                    }
                }, 100);
                break;

            case R.id.task_temperature_frame:
                Utils.avoid_double_click(v); // this is to avoid the event to tiigger 2 times in a row
                pair_to_sensor();
                break;

            case R.id.pending_save_fab:
                Utils.avoid_double_click(v); // this is to avoid the event to tiigger 2 times in a row
                save_task_data();
                break;

            case R.id.pending_sensor_fab:
                Utils.avoid_double_click(v); // this is to avoid the event to tiigger 2 times in a row
                pair_to_sensor();
                break;
        }
    }

/////////////////////////////////////////////////////////////////////////
//   FUNCTIONS                                                         //
/////////////////////////////////////////////////////////////////////////
    private void set_content_UI() {
        viewModel_haccp_queries.getCorrectiveActionByCompanyId(Singleton_Settings.getsettings_instance().getCompany_id()).observe(Fragment_pending_record.this, corrective_action -> adapter_food_corrective_action.set_corrective_action_list(corrective_action));

        pending_temp.setText(String.valueOf(record_temp));
        pending_batch.setText(String.valueOf(record_batch));
        pending_username.setText("by" + record_username);

        if(record_food_type != null){
            pending_food_type.setText(record_food_type);
        } else{
            pending_food_type.setText(String.valueOf(record_food_category));
        }

        Date date = new java.util.Date(record_data.getRecords_initiated_timestamp_unix()*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        pending_date.setText(String.valueOf(sdf.format(date)));
    }

    private void show_remedial_action_ui(int remedialAction_id) {
        if(remedialAction_id == 1){ //Re-check the temperature
            task_temperature_frame.setVisibility(VISIBLE);
            task_corrective_action_text.setVisibility(GONE);
        }
        else if(remedialAction_id == 2){
            task_temperature_frame.setVisibility(GONE);
            task_corrective_action_text.setVisibility(VISIBLE);
        }
        else if(remedialAction_id == 3){
            task_temperature_frame.setVisibility(GONE);
            task_corrective_action_text.setVisibility(VISIBLE);
        }


    }

    private void setTemperature(View view) {

        float new_degrees = (Singleton_Sensor.getSensor_instance().getSensor_value());

        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        String new_output = formatter.format(new_degrees);

        String new_units = String.valueOf(Singleton_Sensor.getSensor_instance().getUnit());
        String new_unit = new_units.substring(0, 2);
        String new_temperature = new_output + new_unit;
        scan_tempereture_value.setText(new_temperature);
        scan_tempereture_value.setTextColor(view.getResources().getColor(R.color.green));
    }

    private void pair_to_sensor() {
                mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (mBluetoothAdapter.isEnabled()) {
                    Fragment_sensor fragment = new Fragment_sensor();
                    FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                    ft.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_in_down, R.anim.slide_out_up, R.anim.slide_out_down);
                    ft.add(R.id.fragment_sensor, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                } else {
                    mBluetoothAdapter.enable();
                    pending_progress.setVisibility(VISIBLE);
                    pending_progress_tv.setText("Enabling Bluetooth...");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pending_progress.setVisibility(GONE);
                            Fragment_sensor fragment = new Fragment_sensor();
                            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                            ft.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_in_down, R.anim.slide_out_up, R.anim.slide_out_down);
                            ft.add(R.id.fragment_sensor, fragment);
                            ft.addToBackStack(null);
                            ft.commit();
                        }
                    }, 1500);
                }
    }

    private void save_task_data() {

                float new_degrees = Singleton_Sensor.getSensor_instance().getSensor_value();
                String new_sensor_id = String.valueOf(Singleton_Sensor.getSensor_instance().getSensor_id());
                String sensor_name = Singleton_Sensor.getSensor_instance().getSensor_name();
                String new_sensor_serial_number = null;
                    if(sensor_name != null) {
                        String[] splited = sensor_name.split("\\s+");
                        new_sensor_serial_number = splited[0].trim();
                    }
                String username = Singleton_Settings.getsettings_instance().getCurrent_user();
                String remedial_action_freetext = task_corrective_action_text_et.getText().toString().trim();
                long current_time = System.currentTimeMillis() / 1000L;


                    NumberFormat new_formatter = NumberFormat.getNumberInstance();
                    new_formatter.setMinimumFractionDigits(2);
                    new_formatter.setMaximumFractionDigits(2);
                    float new_temperature = Float.parseFloat(new_formatter.format(new_degrees));

                    if (remedial_action_selected == 1 && new_degrees > record_pass_temperature) {
                        viewModel_haccp_queries.update_record(
                                (int) record_date,// time of the first record - this is used as an ID to look for the right record
                                (int) new_temperature,
                                remedial_action_selected,
                                1,
                                remedial_action_freetext,
                                current_time,// new time recorded
                                new_sensor_id,
                                new_sensor_serial_number,
                                username
                        );
                        Toast.makeText(getContext(), "Item saved", Toast.LENGTH_LONG).show();
                        close_fragment();
                    }
                    else if (remedial_action_selected == 1 && new_degrees < record_pass_temperature) {
                        Utils.warning_dialog(getContext(), "Can't approve", "The temperature is still too low");
                    }
                    else if(remedial_action_selected != 1){ // dont update temperature, action just approves the record with unmatching temperature
                        viewModel_haccp_queries.update_record(
                                (int) record_date,// time of the first record - this is used as an ID to look for the right record
                                (int) record_temp,
                                remedial_action_selected,
                                1,
                                remedial_action_freetext,
                                current_time,// new time recorded
                                new_sensor_id,
                                new_sensor_serial_number,
                                username
                        );
                        close_fragment();

                    }
    }

    private void close_fragment() {
        action_open = 0;
        getFragmentManager().popBackStack();
    }


/////////////////////////////////////////////////////////////////////////
//   LIFECYCLE                                                         //
/////////////////////////////////////////////////////////////////////////
    @Override
    public void onStart() {
        super.onStart();
        Log.i("lifecycle_fragment_task", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        set_content_UI();
        Log.i("lifecycle_fragment_task", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("lifecycle_fragment_task", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("lifecycle_fragment_task", "onStop");
        viewModel_haccp_queries.getCorrectiveActionByCompanyId(Singleton_Settings.getsettings_instance().getCompany_id()).removeObservers(Fragment_pending_record.this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("lifecycle_fragment_task", "onDestroy");
    }


}

