package com.tscan.app.Fragments;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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

import com.google.gson.Gson;
import com.tscan.app.Data.Model_haccp_task_result_core_cooking;
import com.tscan.app.Data.Singleton_Sensor;
import com.tscan.app.Data.Singleton_Settings;
import com.tscan.app.R;
//import com.tscan.app.UI_listeners.ExceptionHandler;
import com.tscan.app.Utils.Utils;
import com.tscan.app.ViewModel.ViewModel_haccp_queries;

import java.text.NumberFormat;

import static com.tscan.app.Utils.Utils.action_open;

public class Fragment_pending_record extends Fragment implements View.OnClickListener {

    private ViewModel_haccp_queries viewModel_haccp_queries;
    private BluetoothAdapter mBluetoothAdapter;

    private TextView pending_progress_tv, pending_action_bin_tv, pending_action_cook_tv, pending_action_other_tv, pending_temp, pending_food_type, pending_batch, pending_username, pending_date, scan_tempereture_value;
    private ProgressBar pending_progress_bar;
    private CardView pending_progress, pending_action_bin, pending_action_cook, pending_action_other;
    private LinearLayout pending_action_container, pending_action_list;
    private RelativeLayout pending_toolbar, tempereture_frame, pending_save_fab, pending_sensor_fab;
    private ImageView pending_action_bin_iv, pending_action_cook_iv, pending_action_other_iv, scan_circle, scan_pair_icon, pending_sensor_fab_iv;

    private Bundle intent;
    private Model_haccp_task_result_core_cooking record_data;
    private String record_username;
    private int record_batch;
    private Integer record_food_type;
    private Integer record_food_category;
    private String remedial_action_selected;
    private float record_temp;
    private int record_date;

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
        pending_temp = view.findViewById(R.id.pending_temp);
        pending_username = view.findViewById(R.id.pending_username);
        pending_batch = view.findViewById(R.id.pending_batch);
        pending_food_type = view.findViewById(R.id.pending_food_type);
        pending_date = view.findViewById(R.id.pending_date);
        pending_progress_bar = view.findViewById(R.id.pending_progress_bar);
        pending_progress_tv = view.findViewById(R.id.progress_tv);
        pending_progress = view.findViewById(R.id.pending_progress);

        pending_action_bin = view.findViewById(R.id.pending_action_bin);
        pending_action_cook = view.findViewById(R.id.pending_action_cook);
        pending_action_other = view.findViewById(R.id.pending_action_other);
        pending_action_bin_iv = view.findViewById(R.id.pending_action_bin_iv);
        pending_action_cook_iv = view.findViewById(R.id.pending_action_cook_iv);
        pending_action_other_iv = view.findViewById(R.id.pending_action_other_iv);
        pending_action_other_tv = view.findViewById(R.id.pending_action_other_tv);
        pending_action_cook_tv = view.findViewById(R.id.pending_action_cook_tv);
        pending_action_bin_tv = view.findViewById(R.id.pending_action_bin_tv);

        pending_action_list = view.findViewById(R.id.pending_action_list);
        pending_action_container = view.findViewById(R.id.pending_action_container);

        tempereture_frame = view.findViewById(R.id.task_tempereture_frame);
        scan_circle = view.findViewById(R.id.scan_circle);
        scan_tempereture_value = view.findViewById(R.id.scan_tempereture_value);
        scan_pair_icon = view.findViewById(R.id.scan_pair_to_sensor);
        pending_save_fab = view.findViewById(R.id.pending_save_fab);
        pending_sensor_fab = view.findViewById(R.id.pending_sensor_fab);
        pending_sensor_fab_iv = view.findViewById(R.id.pending_sensor_fab_iv);


        /** GET THE DATA FROM THE WINDOW THAT WAS CLICKED TO OPEN FRAGMENT_CORE_COOKING FRAGMENT */
        intent = getArguments();
        Log.i("DAT_Received", String.valueOf(intent.getString("data")));
        if (intent != null) {
            String bundle = intent.getString("data");
            Gson gson = new Gson();
            record_data = gson.fromJson(bundle, Model_haccp_task_result_core_cooking.class);
            record_temp = record_data.getRecords_latest_reading();
            record_username = record_data.getRecords_initiated_by_user();
            record_batch = record_data.getRecords_batch_number();
            record_food_category = record_data.getRecords_food_item_category_id();
            record_food_type = record_data.getRecords_food_item_type_id();
            record_date = record_data.getRecords_initiated_timestamp_unix();
        }

        /////////////////////////////////////////////////////////////////////////
        //   ONCLICK LISTENER                                                  //
        /////////////////////////////////////////////////////////////////////////
        pending_action_bin.setOnClickListener(Fragment_pending_record.this);
        pending_action_cook.setOnClickListener(Fragment_pending_record.this);
        pending_action_other.setOnClickListener(Fragment_pending_record.this);
        tempereture_frame.setOnClickListener(Fragment_pending_record.this);
        pending_save_fab.setOnClickListener(Fragment_pending_record.this);
        pending_sensor_fab.setOnClickListener(Fragment_pending_record.this);
        pending_toolbar.setOnClickListener(Fragment_pending_record.this);


        /////////////////////////////////////////////////////////////////////////
        //   LISTENER SENSOR STATE                                             //
        /////////////////////////////////////////////////////////////////////////
        Singleton_Sensor.getSensor_instance().setValueChangeListener(new Singleton_Sensor.onConnectionChangeListener() {
            @Override
            public void onChange() {
                if (Singleton_Sensor.getSensor_instance().isConnected()) {

                    if (Singleton_Sensor.getSensor_instance().isConnected()) {
                        pending_sensor_fab_iv.setImageDrawable(view.getResources().getDrawable(R.drawable.ic_bluetooth_connected));
                        scan_circle.setImageDrawable(view.getResources().getDrawable(R.drawable.scan_circle_green));
                        scan_pair_icon.setVisibility(View.GONE);
                        scan_tempereture_value.setVisibility(View.VISIBLE);

                        setTemperature(view);

                    } else {
                        pending_sensor_fab_iv.setImageDrawable(view.getResources().getDrawable(R.drawable.ic_bluetooth_disconnected));
                        scan_circle.setImageDrawable(view.getResources().getDrawable(R.drawable.scan_circle_grey));
                        scan_pair_icon.setVisibility(View.VISIBLE);
                        scan_tempereture_value.setVisibility(View.GONE);
                    }
                }
            }
        });

        set_content_UI();

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

            case R.id.pending_action_bin:
                pending_action_bin_iv.setColorFilter(Color.parseColor("#E01717"), PorterDuff.Mode.SRC_IN);
                pending_action_bin_tv.setTextColor(getResources().getColor(R.color.red));

                pending_action_other_iv.setColorFilter(Color.parseColor("#8B8B8B"), PorterDuff.Mode.SRC_IN);
                pending_action_other_tv.setTextColor(getResources().getColor(R.color.grey));

                pending_action_cook_iv.setColorFilter(Color.parseColor("#8B8B8B"), PorterDuff.Mode.SRC_IN);
                pending_action_cook_tv.setTextColor(getResources().getColor(R.color.grey));

                remedial_action_selected = "Item binned";

                pending_action_container.setVisibility(View.GONE);

                break;

            case R.id.pending_action_cook:
                pending_action_bin_iv.setColorFilter(Color.parseColor("#8B8B8B"), PorterDuff.Mode.SRC_IN);
                pending_action_bin_tv.setTextColor(getResources().getColor(R.color.grey));

                pending_action_cook_iv.setColorFilter(Color.parseColor("#E01717"), PorterDuff.Mode.SRC_IN);
                pending_action_cook_tv.setTextColor(getResources().getColor(R.color.red));

                pending_action_other_iv.setColorFilter(Color.parseColor("#8B8B8B"), PorterDuff.Mode.SRC_IN);
                pending_action_other_tv.setTextColor(getResources().getColor(R.color.grey));

                remedial_action_selected = "Item re-cooked";

                pending_action_container.setVisibility(View.VISIBLE);

                break;

            case R.id.pending_action_other:
                pending_action_bin_iv.setColorFilter(Color.parseColor("#8B8B8B"), PorterDuff.Mode.SRC_IN);
                pending_action_bin_tv.setTextColor(getResources().getColor(R.color.grey));

                pending_action_cook_iv.setColorFilter(Color.parseColor("#8B8B8B"), PorterDuff.Mode.SRC_IN);
                pending_action_cook_tv.setTextColor(getResources().getColor(R.color.grey));

                pending_action_other_iv.setColorFilter(Color.parseColor("#E01717"), PorterDuff.Mode.SRC_IN);
                pending_action_other_tv.setTextColor(getResources().getColor(R.color.red));

                remedial_action_selected = "Other action has been taken";

                pending_action_container.setVisibility(View.GONE);

                break;

            case R.id.task_tempereture_frame:
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
        pending_temp.setText(String.valueOf(record_temp));
        pending_food_type.setText(String.valueOf(record_food_type));
        pending_batch.setText(String.valueOf(record_batch));
        pending_username.setText(String.valueOf("by" + record_username));
        pending_date.setText(String.valueOf(record_date));

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
                    pending_progress.setVisibility(View.VISIBLE);
                    pending_progress_tv.setText("Enabling Bluetooth...");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pending_progress.setVisibility(View.GONE);
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
                String username = Singleton_Settings.getsettings_instance().getCurrent_user();
                String current_time = String.valueOf((int) (System.currentTimeMillis() / 1000L));


                    NumberFormat new_formatter = NumberFormat.getNumberInstance();
                    new_formatter.setMinimumFractionDigits(2);
                    new_formatter.setMaximumFractionDigits(2);
                    float new_temperature = Float.parseFloat(new_formatter.format(new_degrees));

                    if (new_degrees > 28) {
                        Log.i("SAVE_CALLED", String.valueOf(record_date));
                        Log.i("SAVE_CALLED", String.valueOf(new_temperature));
                        Log.i("SAVE_CALLED", remedial_action_selected);
                        Log.i("SAVE_CALLED", current_time);
                        Log.i("SAVE_CALLED", new_sensor_id);
                        Log.i("SAVE_CALLED", username);

                        viewModel_haccp_queries.update_record(
                                record_date,// time of the first record - this is used as an ID to look for the right record
                                (int) new_temperature
//                                remedial_action_selected,
//                                "1", // result_status_id PASSED
//                                current_time,// new time recorded
//                                new_sensor_id,
//                                username
                        );
                        Toast.makeText(getContext(), "Item saved", Toast.LENGTH_LONG).show();

                    }
                    else if (new_degrees < 28) {

                    }
    }




}

