package com.tscan.app.Fragments;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.tscan.app.Adapters.Adapter_Completed_Records;
import com.tscan.app.Adapters.Adapter_food_category;
import com.tscan.app.Adapters.Adapter_food_type;
import com.tscan.app.Data.Model_haccp_task_definition;
import com.tscan.app.Data.Model_haccp_task_result_core_cooking;
import com.tscan.app.Data.Singleton_Sensor;
import com.tscan.app.Data.Singleton_Settings;
import com.tscan.app.R;
import com.tscan.app.UI_listeners.UI_Listener_New_Core_Cooking;
import com.tscan.app.Utils.Utils;
import com.tscan.app.ViewModel.ViewModel_haccp_queries;

import java.text.NumberFormat;

import static com.tscan.app.Utils.Utils.action_open;

public class Fragment_task_core extends Fragment implements View.OnClickListener {

    private TextInputEditText UI_batch, UI_description;
    private TextInputLayout task_core_description_inputLayout, task_core_batch_inputLayout;
    private RelativeLayout UI_save_fab, UI_sensor_fab;
    private ImageView UI_sensor_fab_iv, UI_scan_circle, UI_scan_pair_to_sensor, UI_status_iv, task_core_foodType_iv, task_core_foodCategory_iv;
    private TextView progress_tv, UI_scan_tempereture_value, UI_status_title;
    private TextView task_core_target_date, task_core_instructions;
    private RelativeLayout UI_task_tempereture_frame, toolbar;
    private LinearLayout UI_content_container_uncompleted;
    private String new_comment = "";
    private Bundle intent;
    private Model_haccp_task_definition model_task_definition;
    private RecyclerView completed_rv, expired_rv;
    private Adapter_Completed_Records adapter;
    private CardView progress;
    private ProgressBar circle_progress_bar;

    private RelativeLayout task_core_foodCategory, task_core_foodType;
    private TextView task_core_foodType_tv, task_core_foodCategory_tv;
    private RecyclerView food_type_recyclerview, food_category_recyclerview;

    private Adapter_food_category adapter_food_category;
    private Adapter_food_type adapter_food_type;

    public static UI_Listener_New_Core_Cooking ui_listener_new_core_cooking = new UI_Listener_New_Core_Cooking();

    private int status;
    private int schedule_mode;
    private int quantity;
    private int result_count;
    private int selected_food_cat_list;
    private int selected_food_type_list;
    private String description;
    private int current_time;

    private int category_expanded = 0;
    private int type_expanded = 0;
    private int category_selected = 3;
    private int category_completed = 0;


    private static ViewModel_haccp_queries viewModel_haccp_queries;
    private BluetoothAdapter mBluetoothAdapter;


/////////////////////////////////////////////////////////////////////////
//   ON CREATE                                                         //
/////////////////////////////////////////////////////////////////////////
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_tasks_core, container, false);

        viewModel_haccp_queries = ViewModelProviders.of(this).get(ViewModel_haccp_queries.class);

        action_open = 1;

        /////////////////////////////////////////////////////////////////////////
        //   INITIALIZATION                                                    //
        /////////////////////////////////////////////////////////////////////////
        task_core_foodCategory = view.findViewById(R.id.task_core_foodCategory);
        task_core_foodCategory_tv = view.findViewById(R.id.task_core_foodCategory_tv);
        task_core_foodType = view.findViewById(R.id.task_core_foodType);
        task_core_foodType_tv = view.findViewById(R.id.task_core_foodType_tv);

        task_core_foodType_iv = view.findViewById(R.id.task_core_foodType_iv);
        task_core_foodCategory_iv = view.findViewById(R.id.task_core_foodCategory_iv);
        UI_description = view.findViewById(R.id.task_core_description);
        UI_batch = view.findViewById(R.id.task_core_batch);
        task_core_batch_inputLayout = view.findViewById(R.id.task_core_batch_inputLayout);
        task_core_description_inputLayout = view.findViewById(R.id.task_core_description_inputLayout);
        food_type_recyclerview  = view.findViewById(R.id.food_type_recyclerview);
        food_category_recyclerview  = view.findViewById(R.id.food_category_recyclerview);

        toolbar = view.findViewById(R.id.task_core_toolbar);

        UI_save_fab = view.findViewById(R.id.task_core_save_fab);
        UI_task_tempereture_frame = view.findViewById(R.id.task_tempereture_frame);
        UI_sensor_fab = view.findViewById(R.id.task_core_sensor_fab);
        UI_sensor_fab_iv = view.findViewById(R.id.task_core_sensor_fab_iv);
        UI_scan_circle = view.findViewById(R.id.scan_circle);
        UI_scan_pair_to_sensor = view.findViewById(R.id.scan_pair_to_sensor);
        UI_scan_tempereture_value = view.findViewById(R.id.scan_tempereture_value);
        UI_status_iv = view.findViewById(R.id.task_core_status_iv);
        UI_status_title = view.findViewById(R.id.task_core_status_title);
        UI_content_container_uncompleted = view.findViewById(R.id.task_core_content_uncompleted);
        progress = view.findViewById(R.id.task_core_progress);
        progress_tv = view.findViewById(R.id.progress_tv);
        circle_progress_bar = view.findViewById(R.id.cardview_circle_progress_bar);
        task_core_instructions = view.findViewById(R.id.task_core_instructions);

        adapter_food_category = new Adapter_food_category(getContext());
        food_category_recyclerview.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager_1 = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        food_category_recyclerview.setLayoutManager(linearLayoutManager_1);
        food_category_recyclerview.setAdapter(adapter_food_category);
        viewModel_haccp_queries.getAllFoodItemCategories().observe(Fragment_task_core.this, category -> adapter_food_category.set_food_category_list(category));

        adapter_food_type = new Adapter_food_type(getContext());
        food_type_recyclerview.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager_2 = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        food_type_recyclerview.setLayoutManager(linearLayoutManager_2);
        food_type_recyclerview.setAdapter(adapter_food_type);
        viewModel_haccp_queries.getFoodItemTypes_by_category_id().observe(Fragment_task_core.this, type -> adapter_food_type.set_food_type_list(type));

        /** GET THE DATA FROM THE WINDOW THAT WAS CLICKED TO OPEN FRAGMENT_CORE_COOKING FRAGMENT */
        intent = getArguments();

        if (intent != null) {
            String bundle = intent.getString("data");
            Gson gson = new Gson();
            model_task_definition = gson.fromJson(bundle, Model_haccp_task_definition.class);
            quantity = model_task_definition.getTask_definition_quantity_required();
        }

        /////////////////////////////////////////////////////////////////////////
        //   ONCLICK LISTENER                                                  //
        /////////////////////////////////////////////////////////////////////////
        // FOOD TYPE DROPDOWN                                                  //
        /////////////////////////////////////////////////////////////////////////
        task_core_foodCategory.setFocusable(false);
        task_core_foodCategory.setClickable(true);
        task_core_foodCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(category_expanded == 0) {
                    Utils.avoid_double_click(v); // this is to avoid the event to tiigger 2 times in a row
                    category_expanded = 1;
                    food_category_recyclerview.setVisibility(View.VISIBLE);
                    rotate_icon_up(task_core_foodCategory_iv);

                }else{
                    category_expanded = 0;
                    food_category_recyclerview.setVisibility(View.GONE);
                    rotate_icon_down(task_core_foodCategory_iv);
                }
            }
        });

        task_core_foodType.setFocusable(false);
        task_core_foodType.setClickable(true);
        task_core_foodType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (category_completed == 1){
                    Log.i("Category_selected", String.valueOf(category_selected));
                    if (type_expanded == 0) {
                        Utils.avoid_double_click(v); // this is to avoid the event to tiigger 2 times in a row
                        type_expanded = 1;
                        food_type_recyclerview.setVisibility(View.VISIBLE);
                        rotate_icon_up(task_core_foodType_iv);
                    } else {
                        type_expanded = 0;
                        food_type_recyclerview.setVisibility(View.GONE);
                        rotate_icon_down(task_core_foodType_iv);
                    }
                } else{
                    Toast.makeText(getContext(), "Select a food category first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        task_core_batch_inputLayout.setHint("Batch number ");
        UI_description.setHint("Add a description ");

        toolbar.setOnClickListener(Fragment_task_core.this);
        UI_task_tempereture_frame.setOnClickListener(Fragment_task_core.this);
        UI_sensor_fab.setOnClickListener(Fragment_task_core.this);
        UI_save_fab.setOnClickListener(Fragment_task_core.this);

        /////////////////////////////////////////////////////////////////////////
        //   LISTENER SENSOR STATE                                             //
        /////////////////////////////////////////////////////////////////////////
        Singleton_Sensor.getSensor_instance().setValueChangeListener(new Singleton_Sensor.onConnectionChangeListener() {
            @Override
            public void onChange() {
                if (Singleton_Sensor.getSensor_instance().isConnected()) {

                    if (Singleton_Sensor.getSensor_instance().isConnected()) {
                        UI_sensor_fab_iv.setImageDrawable(view.getResources().getDrawable(R.drawable.ic_bluetooth_connected));
                        UI_scan_circle.setImageDrawable(view.getResources().getDrawable(R.drawable.scan_circle_green));
                        UI_scan_pair_to_sensor.setVisibility(View.GONE);
                        UI_scan_tempereture_value.setVisibility(View.VISIBLE);

                        setTemperature(view);

                    } else {
                        UI_sensor_fab_iv.setImageDrawable(view.getResources().getDrawable(R.drawable.ic_bluetooth_disconnected));
                        UI_scan_circle.setImageDrawable(view.getResources().getDrawable(R.drawable.scan_circle_grey));
                        UI_scan_pair_to_sensor.setVisibility(View.VISIBLE);
                        UI_scan_tempereture_value.setVisibility(View.GONE);
                    }
                }
            }
        });

        ui_listener_new_core_cooking.setfoodCategorySelected(new UI_Listener_New_Core_Cooking.OnFoodCategorySelected() {
            @Override
            public void onFoodCategorySelected(Integer selected_category, String category_name) {
                if(selected_category != null){
                    task_core_foodCategory_tv.setText(category_name);
                    category_selected = selected_category;
                    category_completed = 1;
                    viewModel_haccp_queries.getAllFoodItemTypes().observe(Fragment_task_core.this, type -> adapter_food_type.set_all_but_filter(type, category_selected));
                }
                else{
                    category_completed = 0;
                }
            }
        });

        ui_listener_new_core_cooking.setfoodTypeSelected(new UI_Listener_New_Core_Cooking.OnFoodTypeSelected() {
            @Override
            public void onFoodTypeSelected(String type_selected) {
                if(type_selected != null){
                    task_core_foodType_tv.setText(String.valueOf(type_selected));
                }
                else{
                    //do nothing
                }
            }
        });



        set_content_UI();

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.task_core_toolbar:
                Log.i("action_open_frag", String.valueOf(action_open));
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(toolbar.getWindowToken(), 0);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        close_fragment();
                    }
                }, 100);
                break;

            case R.id.task_tempereture_frame:
                Utils.avoid_double_click(v); // this is to avoid the event to tiigger 2 times in a row
                pair_to_sensor();
                break;

            case R.id.task_core_sensor_fab:
                Utils.avoid_double_click(v); // this is to avoid the event to tiigger 2 times in a row
                pair_to_sensor();
                break;

            case R.id.task_core_save_fab:
                Utils.avoid_double_click(v); // this is to avoid the event to tiigger 2 times in a row
                save_task_data();
                break;
        }
    }

/////////////////////////////////////////////////////////////////////////
//   FUNCTIONS                                                         //
/////////////////////////////////////////////////////////////////////////
    private void set_content_UI() {
        UI_status_title.setText(String.valueOf(model_task_definition.getTask_definition_name()));
//        UI_status_tv.setText(String.valueOf(model_task_definition.getJoined_result_count() + " out of " + model_task_definition.getJoined_definition_quantity_required() + " completed"));

                task_core_instructions.setText(model_task_definition.getTask_definition_instruction_text());
//                task_core_target_date.setVisibility(View.GONE);
//                task_core_temperature_required.setText("* must be above 75" + getResources().getString(R.string.degree) + " *");
                UI_status_iv.setColorFilter(Color.parseColor("#8B8B8B"), PorterDuff.Mode.SRC_IN);
                UI_status_title.setTextColor(getResources().getColor(R.color.grey));
//                UI_status_tv.setTextColor(getResources().getColor(R.color.grey));

//                circle_progress_bar.setProgress(Math.round(100.0f * model_task_definition.getJoined_result_count() / model_task_definition.getJoined_definition_quantity_required()));

                UI_content_container_uncompleted.setVisibility(View.VISIBLE);

                UI_save_fab.setVisibility(View.VISIBLE);
                UI_sensor_fab.setVisibility(View.VISIBLE);
    }

    private void setTemperature(View view) {

        float new_degrees = (Singleton_Sensor.getSensor_instance().getSensor_value());

//        if (new_degrees < 28) {
//            task_core_temperature_required.setTextColor(getResources().getColor(R.color.red));
//        } else {
//            task_core_temperature_required.setTextColor(getResources().getColor(R.color.grey));
//        }

        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        String new_output = formatter.format(new_degrees);

        String new_units = String.valueOf(Singleton_Sensor.getSensor_instance().getUnit());
        String new_unit = new_units.substring(0, 2);
        String new_temperature = new_output + new_unit;
        UI_scan_tempereture_value.setText(new_temperature);
        UI_scan_tempereture_value.setTextColor(view.getResources().getColor(R.color.green));
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
                    progress.setVisibility(View.VISIBLE);
                    progress_tv.setText("Enabling Bluetooth...");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progress.setVisibility(View.GONE);
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
                String food_type = task_core_foodType_tv.getText().toString().trim();
                String food_category = task_core_foodCategory_tv.getText().toString().trim();
                String batch_number = UI_batch.getText().toString().trim();
                float reading = Singleton_Sensor.getSensor_instance().getSensor_value();
                String new_sensor_id = String.valueOf(Singleton_Sensor.getSensor_instance().getSensor_id());
                String sensor_serial_number = String.valueOf(Singleton_Sensor.getSensor_instance().getSensor_model());

                Log.i("process_task_core_t", String.valueOf(reading));

                if (food_category.isEmpty()) {
                    task_core_foodType_tv.setError("Please complete");
                } else if (reading == 0.0) {
                    alertDialog_temperature_required();
                } else {
                    NumberFormat new_formatter = NumberFormat.getNumberInstance();
                    new_formatter.setMinimumFractionDigits(2);
                    new_formatter.setMaximumFractionDigits(2);
                    float new_temperature = Float.parseFloat(new_formatter.format(reading));

                    if (reading > 28) {
                        /** If schedule mode is equal to OnDemand ==> UPLOAD TO ROOM AND NATIONAL / IF NATIONAL SUCCESFUL, Put an icon to say so**/
//                        if (schedule_mode == 2) {
//                            save_on_demand_record_1(new_food_type, new_batch, new_temperature, new_sensor_id);
//                        }
//
//                        /** If schedule mode is equal to Shceduled ==> UPLOAD TO ROOM **/
//                        else {
                            save_scheduled_record_1(food_type, food_category, batch_number, new_temperature, sensor_serial_number);
//                        }
                    } else if (reading < 28) {
//                        if (new_comment.trim().isEmpty()) {
//                            alertDialog_corrective_action();
//                        } else {
//                            if (schedule_mode == 2) {
//                                save_on_demand_record_2(new_food_type, new_batch, new_temperature, new_sensor_id);
//                            } else {
                                save_scheduled_record_2(food_type, food_category, batch_number, new_temperature, sensor_serial_number);
//                            }
//                        }
                    }
                }
    }

    //SCHEDULED : SAVE RECORD WITH task_result_status PASSED
    private void save_scheduled_record_1(String food_type, String food_category, String batch, float temperature, String sensor_serial_number) {
        progress.setVisibility(View.VISIBLE);
        current_time = (int) (System.currentTimeMillis() / 1000L);

        Model_haccp_task_result_core_cooking record = new Model_haccp_task_result_core_cooking(
                model_task_definition.getTask_definition_id(),
                model_task_definition.getTask_definition_name(),
                model_task_definition.getTask_definition_task_result_type_id(),
                food_category,
                food_type,
                description,
                batch,
                dfghjkl,//temperature required to pass - get it from food_category
                1, // Passed
                Singleton_Settings.getsettings_instance().getCurrent_user(),
                current_time,
                sensor_serial_number,
                null, // corrective action_id - none here because status PASSED
                null, // corrective action_freetext - none here because status PASSED
                temperature,
                null,
                null,
                null,
                null,
                null
        );
        viewModel_haccp_queries.insert_record(record);

        Toast.makeText(getContext(), "Item saved", Toast.LENGTH_LONG).show();
        task_core_foodCategory_tv.setText("");
        task_core_foodType_tv.setText("");
        UI_batch.setText("");
        new_comment = "";

        close_fragment();
    }


    //SCHEDULED : SAVE RECORD WITH task_result_status FAILED
    private void save_scheduled_record_2(String food_type, String food_category, String batch, float temperature, String sensor_serial_number) {
        progress.setVisibility(View.VISIBLE);
        current_time = (int) (System.currentTimeMillis() / 1000L);

        Model_haccp_task_result_core_cooking record = new Model_haccp_task_result_core_cooking(
                model_task_definition.getTask_definition_id(),
                model_task_definition.getTask_definition_name(),
                model_task_definition.getTask_definition_task_result_type_id(),
                food_category,
                food_type,
                description,
                batch,
                dfghjkl,//temperature required to pass - get it from food_category
                1, // Passed
                Singleton_Settings.getsettings_instance().getCurrent_user(),
                current_time,
                sensor_serial_number,
                null, // corrective action_id - none here because status PASSED
                null, // corrective action_freetext - none here because status PASSED
                temperature,
                null,
                null,
                null,
                null,
                null
        );
        viewModel_haccp_queries.insert_record(record);

        alertDialog_corrective_action();
    }


    private void alertDialog_temperature_required() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext(), R.style.AlertDialogCustom);
        alertDialog.setTitle("Missing information");
        alertDialog.setMessage("A temperature scan is requested in order to complete and save this task. Pair to a sensor and go ahead.");

        alertDialog.setNegativeButton("Got it!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    private void alertDialog_corrective_action() {
        Utils.got_it_dialog(getContext(), "Item at risk", "This item will require a corrective action.", "Got it");
        close_fragment();
    }

    private void alertDialog_bluetooth() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext(), R.style.AlertDialogCustom);
        alertDialog.setTitle("Bluetooth is OFF");
        alertDialog.setMessage("Do you want to turn it ON ?");

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mBluetoothAdapter.enable();
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    private void close_fragment() {
        action_open = 0;
        getFragmentManager().popBackStack();
    }


/////////////////////////////////////////////////////////////////////////
//   ANIMATION                                                         //
/////////////////////////////////////////////////////////////////////////

    private void rotate_icon_up(View v) {
        v.animate().rotationBy(180);
    }

    private void rotate_icon_down(View v) {
        v.animate().rotationBy(-180);
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("lifecycle_fragment_task", "onDestroy");
    }

}
