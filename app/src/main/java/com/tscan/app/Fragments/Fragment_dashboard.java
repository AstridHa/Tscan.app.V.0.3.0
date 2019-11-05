package com.tscan.app.Fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tscan.app.Adapters.Adapter_Completed_Records;
import com.tscan.app.Adapters.Adapter_Pending_Records;
import com.tscan.app.R;
import com.tscan.app.Utils.OnAlarmReceiver_UpdateCountdown;
import com.tscan.app.Utils.Utils;
import com.tscan.app.ViewModel.ViewModel_haccp_queries;
import java.util.concurrent.ScheduledExecutorService;

public class Fragment_dashboard extends Fragment {

    private CardView category_todolist, category_completed, category_pending;
    private TextView main_task_title, category_todolist_title, category_completed_title, category_pending_title;
    private TextView category_todolist_nb, category_completed_nb, category_pending_nb;
    private ImageView category_todolist_iv, category_completed_iv, category_pending_iv;

    private ViewModel_haccp_queries viewModel_haccp_queries;

    private ScheduledExecutorService scheduleTaskExecutor;

    private RecyclerView rv_vertical;
//    private Adapter_Task_Definition adapter_vertical;
    private Adapter_Pending_Records adapter_pending_records;
    private Adapter_Completed_Records adapter_completed_tasks;

    private AlarmManager alarmManager_update_countdown;
    private PendingIntent alarmIntent;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("lifecycle_fragment_dash", "onAttach");
    }

/////////////////////////////////////////////////////////////////////////
//   ON CREATE                                                         //
/////////////////////////////////////////////////////////////////////////
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        /////////////////////////////////////////////////////////////////////////
        //   INITIALIZATION                                                    //
        /////////////////////////////////////////////////////////////////////////
        rv_vertical = view.findViewById(R.id.main_recyclerview_vertical);

        category_completed = view.findViewById(R.id.main_cardview_completed);
        category_completed_iv = view.findViewById(R.id.main_cardview_completed_iv);
        category_completed_nb = view.findViewById(R.id.main_cardview_completed_nb);
        category_completed_title = view.findViewById(R.id.main_cardview_completed_title);

        category_pending = view.findViewById(R.id.main_cardview_pending);
        category_pending_iv = view.findViewById(R.id.main_cardview_pending_iv);
        category_pending_nb = view.findViewById(R.id.main_cardview_pending_nb);
        category_pending_title = view.findViewById(R.id.main_cardview_pending_title);

        viewModel_haccp_queries = ViewModelProviders.of(this).get(ViewModel_haccp_queries.class);

        // Recyclerviews
        LinearLayoutManager layoutManager_v = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        adapter_completed_tasks = new Adapter_Completed_Records(Fragment_dashboard.this.getContext());
        adapter_pending_records = new Adapter_Pending_Records(getContext());
            // Recyclerview today's list
            rv_vertical.setHasFixedSize(true);
            rv_vertical.setItemViewCacheSize(10);
            rv_vertical.setLayoutManager(layoutManager_v);
            rv_vertical.setAdapter(adapter_pending_records);

        /////////////////////////////////////////////////////////////////////////
        //   TASK CATEGORY ONCLICK                                             //
        /////////////////////////////////////////////////////////////////////////
        category_completed.setOnClickListener(v -> {
            Utils.avoid_double_click(v); // this is to avoid the event to tiigger 2 times in a row

            category_completed_iv.setColorFilter(Color.parseColor("#1E9405"), PorterDuff.Mode.SRC_IN);
            category_completed_nb.setTextColor(Fragment_dashboard.this.getResources().getColor(R.color.green));
            category_completed_title.setTextColor(Fragment_dashboard.this.getResources().getColor(R.color.green));

            category_pending_iv.setColorFilter(Color.parseColor("#8B8B8B"), PorterDuff.Mode.SRC_IN);
            category_pending_nb.setTextColor(Fragment_dashboard.this.getResources().getColor(R.color.grey));
            category_pending_title.setTextColor(Fragment_dashboard.this.getResources().getColor(R.color.grey));

            category_completed_title.setTypeface(null, Typeface.BOLD);
            category_pending_title.setTypeface(null, Typeface.NORMAL);

            rv_vertical.setAdapter(adapter_completed_tasks);

            //remove the observers that have potentially already been created
            viewModel_haccp_queries.query_pending_records().removeObservers(Fragment_dashboard.this);
            viewModel_haccp_queries.query_completed_records().removeObservers(Fragment_dashboard.this);

            viewModel_haccp_queries.query_completed_records().observe(Fragment_dashboard.this, task -> adapter_completed_tasks.get_records_Data(task));
        });

        category_pending.setOnClickListener(v -> {
            Utils.avoid_double_click(v); // this is to avoid the event to tiigger 2 times in a row

            category_completed_iv.setColorFilter(Color.parseColor("#8B8B8B"), PorterDuff.Mode.SRC_IN);
            category_completed_nb.setTextColor(getResources().getColor(R.color.grey));
            category_completed_title.setTextColor(getResources().getColor(R.color.grey));

            category_pending_iv.setColorFilter(Color.parseColor("#E01717"), PorterDuff.Mode.SRC_IN);
            category_pending_nb.setTextColor(getResources().getColor(R.color.red));
            category_pending_title.setTextColor(getResources().getColor(R.color.red));

            category_completed_title.setTypeface(null, Typeface.NORMAL);
            category_pending_title.setTypeface(null, Typeface.BOLD);

            rv_vertical.setAdapter(adapter_pending_records);

            //remove the observers that have potentially already been created
            viewModel_haccp_queries.query_completed_records().removeObservers(Fragment_dashboard.this);
            viewModel_haccp_queries.query_pending_records().removeObservers(Fragment_dashboard.this);

            viewModel_haccp_queries.query_pending_records().observe(Fragment_dashboard.this, task -> adapter_pending_records.getDataPendingRecords(task));

        });

        return view;
    }


/////////////////////////////////////////////////////////////////////////
//   LIFECYCLE                                                         //
/////////////////////////////////////////////////////////////////////////
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("lifecycle_fragment_dash", "onActivityCreated");
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.i("lifecycle_fragment_dash", "onStart");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i("lifecycle_fragment_dash", "onResume");

        // AlarmManager - triggered every minute in order to refresh the countdown
        alarmManager_update_countdown = (AlarmManager)getContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext(), OnAlarmReceiver_UpdateCountdown.class);
        alarmIntent = PendingIntent.getBroadcast(getContext(), 0, intent, 0);
        alarmManager_update_countdown.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 60000, alarmIntent);

        /////////////////////////////////////////////////////////////////////////
        //   LISTENERS TO UPDATE Number of tasks listed for each filter        //
        /////////////////////////////////////////////////////////////////////////
//        viewModel_haccp_queries.get_count_uncompleted().observe(Fragment_dashboard.this, this::update_filter_number_uncompleted);
        viewModel_haccp_queries.get_count_completed().observe(Fragment_dashboard.this, this::update_filter_number_completed);
        viewModel_haccp_queries.get_count_pending().observe(Fragment_dashboard.this, this::update_filter_number_pending);

        //////////////////////////////////////////////////////////////////////////////////////////
        //   LISTENERS TO UPDATE TODAY'S LIST - this is the list the user lands on first        //
        //////////////////////////////////////////////////////////////////////////////////////////
//        viewModel_haccp_queries.query_joined_uncompleted_task_windows().observe(Fragment_dashboard.this, tasks -> {
//            Log.i("Fragment_dashboard_update", "onChanged1 ");
//            adapter_vertical.getData(tasks);
//        });

        category_pending_iv.setColorFilter(Color.parseColor("#E01717"), PorterDuff.Mode.SRC_IN);
        category_pending_nb.setTextColor(getResources().getColor(R.color.red));
        category_pending_title.setTextColor(getResources().getColor(R.color.red));

        viewModel_haccp_queries.query_pending_records().observe(Fragment_dashboard.this, tasks -> {
            Log.i("Fragment_dashboard_update", "onChanged1 ");
            adapter_pending_records.getDataPendingRecords(tasks);
        });




    }

    @Override
    public void onPause(){
        super.onPause();
        Log.i("lifecycle_fragment_dash", "onPause");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.i("lifecycle_fragment_dash", "onStop");
//        viewModel_haccp_queries.query_joined_uncompleted_task_windows().removeObservers(Fragment_dashboard.this);
//        viewModel_haccp_queries.query_joined_completed_task_windows().removeObservers(Fragment_dashboard.this);
        viewModel_haccp_queries.query_pending_records().removeObservers(Fragment_dashboard.this);
        viewModel_haccp_queries.query_completed_records().removeObservers(Fragment_dashboard.this);

//        viewModel_haccp_queries.get_count_uncompleted().removeObservers(Fragment_dashboard.this);
        viewModel_haccp_queries.get_count_completed().removeObservers(Fragment_dashboard.this);
        viewModel_haccp_queries.get_count_pending().removeObservers(Fragment_dashboard.this);

        alarmManager_update_countdown.cancel(alarmIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("lifecycle_fragment_dash", "onDestroy");
    }



/////////////////////////////////////////////////////////////////////////
//   FUNCTIONS                                                         //
/////////////////////////////////////////////////////////////////////////
//    private void update_filter_number_uncompleted(Integer integer) {
//        Log.i("Fragment_dashboard_observer", "update_filter_number_uncompleted");
//        category_todolist_nb.setText(String.valueOf(integer));
//    }
    private void update_filter_number_completed(Integer integer) {
        Log.i("Fragment_dashboard_observer", "update_filter_number_completed");
        category_completed_nb.setText(String.valueOf(integer));
    }
    private void update_filter_number_pending(Integer integer) {
        Log.i("Fragment_dashboard_observer", "update_filter_number_pending");
        category_pending_nb.setText(String.valueOf(integer));
    }
}
