package com.tscan.app.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.tscan.app.Data.Model_haccp_food_item_types;
import com.tscan.app.Data.Model_haccp_task_result_core_cooking;
import com.tscan.app.Fragments.Fragment_pending_record;
import com.tscan.app.R;
import com.tscan.app.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.tscan.app.Utils.Utils.action_open;

public class Adapter_Pending_Records extends RecyclerView.Adapter<Adapter_Pending_Records.ViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<Model_haccp_task_result_core_cooking> model_records;

    private List<Model_haccp_food_item_types> model_food_types;

    /////////////////////////////////////////////////////////////////////////
//   ADAPTER CONSTRUCTOR                                               //
/////////////////////////////////////////////////////////////////////////
    public Adapter_Pending_Records(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }


    /////////////////////////////////////////////////////////////////////////
//   ON CREATE VIEW ITEMS                                              //
/////////////////////////////////////////////////////////////////////////
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = mInflater.inflate(R.layout.row_record_pending, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        /////////////////////////////////////////////////////////////////////////
        //   DISPLAY ITEMS                                                     //
        /////////////////////////////////////////////////////////////////////////
        viewHolder.pending_temp.setText(String.valueOf(model_records.get(position).getRecords_latest_reading()));
        viewHolder.pending_food_type.setText(String.valueOf(model_food_types.get(model_records.get(position).getRecords_food_item_type_id())));
        viewHolder.pending_batch.setText("#" + model_records.get(position).getRecords_batch_number());
        viewHolder.pending_username.setText("by " + model_records.get(position).getRecords_initiated_by_user());

        convertDateUnix(model_records.get(position).getRecords_initiated_timestamp_unix(), viewHolder);

        setTaskImage(model_records.get(position).getRecords_task_result_type_id(), viewHolder);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (action_open == 0) {
                    Utils.avoid_double_click(v); // this is to avoid the event to tiigger 2 times in a row
                    action_open = 1;

                    int viewHolder_position = viewHolder.getAdapterPosition();
                    Gson datajson = new Gson();
                    String json = datajson.toJson(model_records.get(viewHolder_position));

                    Bundle bundle = new Bundle();
                    bundle.putString("data", json);
                    Fragment_pending_record fragment = new Fragment_pending_record();
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


/////////////////////////////////////////////////////////////////////////
//   FUNCTIONS                                                         //
/////////////////////////////////////////////////////////////////////////
    @Override
    public int getItemCount() {
        if (model_records != null) {
            return model_records.size();
        } else {
            return 0;
        }
    }

    public void getDataPendingRecords(List<Model_haccp_task_result_core_cooking> model_records) {
        this.model_records = model_records;
        JSONArray task_type_array = null;
        try {
            task_type_array = new JSONArray(new Gson().toJson(model_records));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("Adapter_pending_record", String.valueOf(task_type_array));
        notifyDataSetChanged();
    }

    private void convertDateUnix(long task_deadline_time, ViewHolder viewHolder) {
        Date date = new java.util.Date(task_deadline_time * 1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        String formattedDate = sdf.format(date);
        viewHolder.pending_date.setText(formattedDate);
    }



    private void setTaskImage(int task_type, ViewHolder viewHolder) {
        switch (task_type) {
            case 1:
                viewHolder.pending_food_icon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_core));
                break;

            case 2:
                viewHolder.pending_food_icon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_delivery));
                break;

            case 3:
                viewHolder.pending_food_icon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_hot_held));
                break;

            case 4:
                viewHolder.pending_food_icon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_blast));
                break;

            default:
                break;
        }
    }

/////////////////////////////////////////////////////////////////////////
//  VIEWHOLDER INITIALIZATION                                          //
/////////////////////////////////////////////////////////////////////////
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView pending_temp, pending_food_type, pending_batch, pending_username, pending_date;
        ImageView pending_food_icon;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            pending_temp = itemView.findViewById(R.id.pending_temp);
            pending_username = itemView.findViewById(R.id.pending_username);
            pending_batch = itemView.findViewById(R.id.pending_batch);
            pending_food_type = itemView.findViewById(R.id.pending_food_type);
            pending_date = itemView.findViewById(R.id.pending_date);
            pending_food_icon = itemView.findViewById(R.id.pending_food_icon);
        }
    }
}


