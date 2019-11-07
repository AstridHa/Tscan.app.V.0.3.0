package com.tscan.app.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.tscan.app.Dao.Dao_join;
import com.tscan.app.Data.Model_haccp_location;
import com.tscan.app.Data.Model_haccp_task_definition;
import com.tscan.app.Data.Model_haccp_task_result_core_cooking;
import com.tscan.app.Data.Model_haccp_task_window;
import com.tscan.app.Fragments.Fragment_task_core;
import com.tscan.app.R;
import com.tscan.app.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import static com.tscan.app.Activities.Activity_login.viewModel_haccp_queries;
import static com.tscan.app.Utils.Utils.action_open;

public class Adapter_Task_Definition extends RecyclerView.Adapter<Adapter_Task_Definition.ViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private Activity mActivity;
    private List<Model_haccp_task_definition> model_task_definition;
//    private Dao_join dao_join;
//    private Database_HACCP db_definition;

/////////////////////////////////////////////////////////////////////////
//   ADAPTER CONSTRUCTOR                                               //
/////////////////////////////////////////////////////////////////////////
    public Adapter_Task_Definition(Context context, Activity activity, Dao_join dao_join) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mActivity = activity;
//        this.dao_join = dao_join;
    }

/////////////////////////////////////////////////////////////////////////
//   ON CREATE VIEW ITEMS                                              //
/////////////////////////////////////////////////////////////////////////
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = mInflater.inflate(R.layout.row_task_definition_bis, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int pos) {
        int position = viewHolder.getAdapterPosition();
        final long current_time = (System.currentTimeMillis() / 1000L);

        String task_definition_name = String.valueOf(model_task_definition.get(position).getTask_definition_name());
        int task_definition_location_id = model_task_definition.get(position).getTask_definition_location_id();

        Log.i("action_open_act", String.valueOf(action_open));

        /////////////////////////////////////////////////////////////////////////
        //   DISPLAY ITEMS                                                     //
        /////////////////////////////////////////////////////////////////////////
        setTaskImage(model_task_definition.get(position).task_definition_task_result_type_id, viewHolder);

        viewHolder.task_title.setText(task_definition_name);
        viewHolder.task_title.setTextColor(mContext.getResources().getColor(R.color.grey));
        viewHolder.task_location.setTextColor(mContext.getResources().getColor(R.color.grey));

        /////////////////////////////////////////////////////////////////////////
        //   ITEMS ONCLICK LISTENER                                            //
        /////////////////////////////////////////////////////////////////////////
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(action_open == 0) {
                    Utils.avoid_double_click(v); // this is to avoid the event to tiigger 2 times in a row
                    action_open = 1;

                    int viewHolder_position = viewHolder.getAdapterPosition();
                    Gson datajson = new Gson();
                    String json = datajson.toJson(model_task_definition.get(viewHolder_position));
                    Bundle bundle = new Bundle();
                    bundle.putString("data", json);
                    Fragment_task_core fragment = new Fragment_task_core();
                    fragment.setArguments(bundle);
                    FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
                    ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_out_left);
                    ft.replace(R.id.fragment_place, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        if (model_task_definition != null) {
            return model_task_definition.size();
        } else { return 0; }
    }

    public void getData(List<Model_haccp_task_definition> model_task_definition) {
        this.model_task_definition = model_task_definition;

        JSONArray task_type_array = null;
        try {
            task_type_array = new JSONArray(new Gson().toJson(model_task_definition));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i("Adapter_task_defintion", String.valueOf(task_type_array));
        notifyDataSetChanged();
    }




/////////////////////////////////////////////////////////////////////////
//   FUNCTIONS                                                         //
/////////////////////////////////////////////////////////////////////////
    private void setTaskImage(int task_core_type, ViewHolder viewHolder) {
        switch (task_core_type) {
            case 1:
                viewHolder.task_icon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_core));
                break;

            case 2:
                viewHolder.task_icon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_delivery));
                break;

            case 3:
                viewHolder.task_icon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_hot_held));
                break;

            case 4:
                viewHolder.task_icon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_blast));
                break;

            default:
                break;
        }
    }

//    private void convertTaskCountdown(long window_deadline_time, long countdown_seconds, ViewHolder viewHolder) {
//        long countdown_minutes = countdown_seconds/60;
//
//        Date date = new java.util.Date(window_deadline_time*1000L);
//        SimpleDateFormat sdf = new java.text.SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
//        String formattedDate = sdf.format(date);
//
//        if(countdown_seconds >= 3600){
//            viewHolder.task_deadline.setText(String.valueOf(countdown_minutes/60 + " hour"));
//        }
//        if(countdown_seconds >= 60 && countdown_seconds < 3600){
//            viewHolder.task_deadline.setText(String.valueOf(countdown_minutes + " minutes"));
//        }
//        if (countdown_seconds >= 1 && countdown_seconds < 60 ) {
//            viewHolder.task_deadline.setText("Few seconds remaining...");
//        }
//        else if (countdown_seconds < 1 ) {
//            viewHolder.task_deadline.setText("Expired");
//        }
//    }

//    private void setExpiryBar(long task_countdown_sec, ViewHolder viewHolder) {
//
//        if (task_countdown_sec > 900 ) {
//            viewHolder.task_bar.setBackgroundColor(Color.parseColor("#C5C5C5"));
//        }
//        if (task_countdown_sec > 600 && task_countdown_sec <= 900 ) {
//            viewHolder.task_bar.setBackgroundColor(Color.parseColor("#C5C5C5"));  //grey
//        }
//        if (task_countdown_sec > 360 && task_countdown_sec <= 600 ) { //6 to 10 mins
//            viewHolder.task_bar.setBackgroundColor(Color.parseColor("#FFB700"));  //yellow
//        }
//        if (task_countdown_sec > 180 && task_countdown_sec <= 360 ) { // 3 to 6 mins
//            viewHolder.task_bar.setBackgroundColor(Color.parseColor("#F86D00"));  //orange
//        }
//        if (task_countdown_sec <= 180) { //3 minutes
//            viewHolder.task_bar.setBackgroundColor(Color.parseColor("#E01717"));  //red
//        }
//    }

    private void setCompletionPercentage(int window_result_count, int window_quantity_required, ViewHolder viewHolder) {
        viewHolder.task_circle_progress.setProgress(Math.round(100.0f * window_result_count / window_quantity_required));
    }


/////////////////////////////////////////////////////////////////////////
//  VIEWHOLDER INITIALIZATION                                          //
/////////////////////////////////////////////////////////////////////////
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView task_title, task_location;
        RelativeLayout task_alarm, task_add, task_pending;
        ImageView task_icon, task_alarm_iv, task_add_iv, task_pending_iv;
        ProgressBar task_circle_progress;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            task_icon = itemView.findViewById(R.id.task_definition_icon);
            task_title = itemView.findViewById(R.id.task_definition_title);
            task_location = itemView.findViewById(R.id.task_definition_location);

            task_circle_progress = itemView.findViewById(R.id.task_definition_circle_progress_bar);
            task_pending = itemView.findViewById(R.id.task_definition_pending);
            task_add = itemView.findViewById(R.id.task_definition_add);
            task_alarm = itemView.findViewById(R.id.task_definition_alarm);
            task_alarm_iv = itemView.findViewById(R.id.task_definition_alarm_iv);
            task_add_iv = itemView.findViewById(R.id.task_definition_add_iv);
            task_pending_iv = itemView.findViewById(R.id.task_definition_pending_iv);


//            task_bar = itemView.findViewById(R.id.task_definition_bar);
//            task_deadline = itemView.findViewById(R.id.task_deadline);
        }
    }
}
