package com.tscan.app.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tscan.app.Data.Model_haccp_corrective_action_type;
import com.tscan.app.R;

import java.util.List;

import static com.tscan.app.Fragments.Fragment_task_core.ui_listener_new_core_cooking;

public class Adapter_food_corrective_action extends RecyclerView.Adapter<Adapter_food_corrective_action.ViewHolder>{

    private List<Model_haccp_corrective_action_type> model_corrective_action;

    private LayoutInflater mInflater;
    private Context mContext;
    private int selected_position = -1;

/////////////////////////////////////////////////////////////////////////
//   ADAPTER CONSTRUCTOR                                               //
/////////////////////////////////////////////////////////////////////////
    public Adapter_food_corrective_action(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

/////////////////////////////////////////////////////////////////////////
//   ON CREATE VIEW ITEMS                                              //
/////////////////////////////////////////////////////////////////////////
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.row_food_list, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
        int position = viewHolder.getAdapterPosition();

        /////////////////////////////////////////////////////////////////////////
        //   DISPLAY ITEMS                                                     //
        /////////////////////////////////////////////////////////////////////////
        viewHolder.category_name.setText(model_corrective_action.get(position).getCorrective_action_name());

        if (selected_position == position) {
            viewHolder.category_name.setTextColor(Color.parseColor("#1E9405"));
        } else {
            viewHolder.category_name.setTextColor(Color.parseColor( "#C5C5C5"));
        }

        /////////////////////////////////////////////////////////////////////////
        //   ITEMS ONCLICK LISTENER                                            //
        /////////////////////////////////////////////////////////////////////////
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected_position != position) {
                    selected_position = position;
                    ui_listener_new_core_cooking.callRemedialActionSelected(model_corrective_action.get(position).getCorrective_action_name(), model_corrective_action.get(position).getCorrective_action_id(), model_corrective_action.get(position).getCorrective_action_new_haccp_task_result_status_id());
                    viewHolder.category_name.setTextColor(Color.parseColor("#1E9405"));
                    notifyDataSetChanged();
                }
            }
        });
    }


/////////////////////////////////////////////////////////////////////////
//   FUNCTIONS                                                         //
/////////////////////////////////////////////////////////////////////////
    @Override
    public int getItemCount() {
        if(model_corrective_action != null){
            return model_corrective_action.size(); }
        else{ return 0; }
    }

    public void set_corrective_action_list(List<Model_haccp_corrective_action_type> corrective_action) {
        this.model_corrective_action = corrective_action;
        notifyDataSetChanged();
    }



/////////////////////////////////////////////////////////////////////////
//  VIEWHOLDER INITIALIZATION                                          //
/////////////////////////////////////////////////////////////////////////
    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView category_name;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            category_name = itemView.findViewById(R.id.row_username);
        }
    }
}
