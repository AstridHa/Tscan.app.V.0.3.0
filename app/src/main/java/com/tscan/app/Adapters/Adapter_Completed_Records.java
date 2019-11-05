package com.tscan.app.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tscan.app.Data.Model_haccp_food_item_types;
import com.tscan.app.Data.Model_haccp_task_result_core_cooking;
import com.tscan.app.R;
import com.tscan.app.UI_listeners.UI_Listener_Adapter_Completed_uploaded_icon;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Adapter_Completed_Records extends RecyclerView.Adapter<Adapter_Completed_Records.ViewHolder>{
    private LayoutInflater mInflater;
    private Context mContext;
    private List<Model_haccp_task_result_core_cooking> model_records;
    private static Integer uploaded_record_count;

    private List<Model_haccp_food_item_types> model_food_types;


    public static UI_Listener_Adapter_Completed_uploaded_icon ui_listener_uploaded_count = new UI_Listener_Adapter_Completed_uploaded_icon();


/////////////////////////////////////////////////////////////////////////
//   ADAPTER CONSTRUCTOR                                               //
/////////////////////////////////////////////////////////////////////////
    public Adapter_Completed_Records(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

/////////////////////////////////////////////////////////////////////////
//   ON CREATE VIEW ITEMS                                              //
/////////////////////////////////////////////////////////////////////////
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = mInflater.inflate(R.layout.row_record_completed, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int pos) {
        int position = viewHolder.getAdapterPosition();

        /////////////////////////////////////////////////////////////////////////
        //   DISPLAY ITEMS                                                     //
        /////////////////////////////////////////////////////////////////////////

        viewHolder.recycler_completed_temp.setText(String.valueOf(model_records.get(position).getRecords_latest_reading()));
        viewHolder.recycler_completed_food_type.setText(String.valueOf(model_food_types.get(model_records.get(position).getRecords_food_item_type_id())));
        viewHolder.recycler_completed_batch.setText("#" + model_records.get(position).getRecords_batch_number());
        viewHolder.recycler_completed_username.setText("by " + model_records.get(position).getRecords_initiated_by_user());
        convertCompletionUnix(model_records.get(position).getRecords_initiated_timestamp_unix(), viewHolder);
        setTaskImage(model_records.get(position).getRecords_task_result_type_id(), viewHolder);

        viewHolder.recycler_completed_action_ll.setVisibility(View.GONE);

        if(model_records.get(position).getRecords_timestamp_uploaded_unix() != null){
            viewHolder.cardview_upload_status.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_uploaded));
        }

        if(!String.valueOf(model_records.get(position).getRecords_latest_corrective_action_type_id()).isEmpty() ){
            viewHolder.recycler_completed_action_ll.setVisibility(View.VISIBLE);
            viewHolder.recycler_completed_action.setText(String.valueOf(model_records.get(position).getRecords_latest_corrective_action_type_id()));
        }
    }


    @Override
    public int getItemCount() {
        if(model_records != null){
            return model_records.size();
        }
        else{
            return 0;
        }
    }

    public void get_records_Data(List<Model_haccp_task_result_core_cooking> task) {
        this.model_records = task;
        Log.i("Adapter_completed", "refresh data");
        notifyDataSetChanged();
    }




/////////////////////////////////////////////////////////////////////////
//   FUNCTIONS                                                         //
/////////////////////////////////////////////////////////////////////////

    private void setTaskImage(int task_type, ViewHolder viewHolder) {
        switch (task_type) {
            case 1:
                viewHolder.completed_food_icon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_core));
                break;

            case 2:
                viewHolder.completed_food_icon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_delivery));
                break;

            case 3:
                viewHolder.completed_food_icon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_hot_held));
                break;

            case 4:
                viewHolder.completed_food_icon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_blast));
                break;

            default:
                break;
        }
    }

//    private void setCompletionPercentage(int window_result_count, int window_quantity_required, ViewHolder viewHolder) {
//        viewHolder.completed_progressBar.setProgress(Math.round(100.0f * window_result_count / window_quantity_required));
//    }

    private void convertCompletionUnix(int window_completed_unix, ViewHolder viewHolder) {
        Date date = new java.util.Date(window_completed_unix*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        String formattedDate = sdf.format(date);
        viewHolder.recycler_completed_date.setText(formattedDate);
    }

/////////////////////////////////////////////////////////////////////////
//  VIEWHOLDER INITIALIZATION                                          //
/////////////////////////////////////////////////////////////////////////
    class ViewHolder extends RecyclerView.ViewHolder{
    TextView recycler_completed_temp, recycler_completed_food_type, recycler_completed_batch, recycler_completed_username, recycler_completed_date, recycler_completed_action;
    LinearLayout recycler_completed_action_ll;
    RelativeLayout item_container;
    ImageView cardview_upload_status, completed_food_icon;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            recycler_completed_temp = itemView.findViewById(R.id.recycler_completed_temp);
            recycler_completed_username = itemView.findViewById(R.id.recycler_completed_username);
            recycler_completed_batch = itemView.findViewById(R.id.recycler_completed_batch);
            recycler_completed_food_type = itemView.findViewById(R.id.recycler_completed_food_type);
            recycler_completed_action = itemView.findViewById(R.id.recycler_completed_action);
            recycler_completed_date = itemView.findViewById(R.id.recycler_completed_date);
            cardview_upload_status = itemView.findViewById(R.id.cardview_upload_status);
            completed_food_icon = itemView.findViewById(R.id.completed_food_icon);
            item_container = itemView.findViewById(R.id.item_container);
            recycler_completed_action_ll = itemView.findViewById(R.id.recycler_completed_action_ll);

        }
    }
}
