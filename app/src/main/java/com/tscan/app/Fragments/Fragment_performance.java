package com.tscan.app.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tscan.app.Adapters.Adapter_Task_Definition;
import com.tscan.app.Dao.Dao_join;
import com.tscan.app.Database.Database_HACCP;
import com.tscan.app.R;
import com.tscan.app.ViewModel.ViewModel_haccp_queries;

public class Fragment_performance extends Fragment {

    private RecyclerView rv_vertical;
    private Adapter_Task_Definition adapter_task_definition;

    private ViewModel_haccp_queries viewModel_haccp_queries;
    private Dao_join dao_join;
    private Database_HACCP db_definition;

/////////////////////////////////////////////////////////////////////////
//   ON CREATE                                                         //
/////////////////////////////////////////////////////////////////////////
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_performance, container, false);

        /////////////////////////////////////////////////////////////////////////
        //   INITIALIZATION                                                    //
        /////////////////////////////////////////////////////////////////////////

        viewModel_haccp_queries = ViewModelProviders.of(this).get(ViewModel_haccp_queries.class);
        db_definition = Database_HACCP.getDatabase_haccp(getActivity());
        dao_join = db_definition.dao_join();

        // Recyclerviews
        rv_vertical = view.findViewById(R.id.performance_recyclerview);
        LinearLayoutManager layoutManager_v = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        adapter_task_definition = new Adapter_Task_Definition(getContext(), getActivity(), dao_join);
        rv_vertical.setHasFixedSize(true);
        rv_vertical.setLayoutManager(layoutManager_v);
        rv_vertical.setAdapter(adapter_task_definition);

        viewModel_haccp_queries.query_task_definition().observe(Fragment_performance.this, task -> adapter_task_definition.getData(task));

        return view;
    }


/////////////////////////////////////////////////////////////////////////
//   LIFECYCLE                                                         //
/////////////////////////////////////////////////////////////////////////
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("lifecycle_fragment_perf", "onActivityCreated");
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.i("lifecycle_fragment_perf", "onStart");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i("lifecycle_fragment_perf", "onResume");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.i("lifecycle_fragment_perf", "onPause");


        ////////////////////////////////////////////////////////////////////////////////////////////////
        //   LISTENERS TO UPDATE countdown - linked to above AlarmManager / triggered every minutes   //
        ////////////////////////////////////////////////////////////////////////////////////////////////
//        UI_Listener_countdown_alarmManager.addListener_countdown_trigger(new UI_Listener_countdown_alarmManager.CountdownListener() {
//            @Override
//            public void OnListener_countdown_trigger() {
//                Log.i("Fragment_dashboard", ("Listener triggered every minute"));
//                viewModel_haccp_queries.query_joined_uncompleted_task_windows().observe(Fragment_performance.this, this::update_countdown);
//            }
//
//            private void update_countdown(List<Model_window_joined_data> model_joins) {
//                long current_time = (System.currentTimeMillis())/1000L;
//
//                Log.i("Fragment_dashboard", "update countdown");
//
//                /** If Window's end_time is < now_time => update window_status to pending (4)
//                 * WINDOW STATUS:
//                 *          -  1 = Uncompleted
//                 *          -  2 = Completed
//                 *          -  3 = Failed (ex: temperature is below 75 degrees and the task the is flagged FAILED which means it is still to be completed
//                 *          -  4 = pending (only if the task has not been completed in time) */
//
//                for(int i = 0; i < model_joins.size(); i++) {
//                    Integer window_end_time_unix = model_joins.get(i).getJoined_window_end_time_unix();
//                    long window_countdown_sec = (window_end_time_unix - current_time);
//
//                    if (window_countdown_sec < 5 ) {
//                        viewModel_haccp_queries.update_upload_status(model_joins.get(i).getJoined_window_id(), 4);
//                    }
//                }
//                adapter_vertical.notifyDataSetChanged();
//            }
//        });
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.i("lifecycle_fragment_perf", "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("lifecycle_fragment_perf", "onDestroy");
    }

}
