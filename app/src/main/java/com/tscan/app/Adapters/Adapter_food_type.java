package com.tscan.app.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tscan.app.Data.Model_haccp_food_item_types;
import com.tscan.app.R;
import com.tscan.app.ViewModel.ViewModel_haccp_queries;

import java.util.List;

import static com.tscan.app.Fragments.Fragment_task_core.ui_listener_new_core_cooking;

public class Adapter_food_type extends RecyclerView.Adapter<Adapter_food_type.ViewHolder>{

    private List<Model_haccp_food_item_types> model_food_types;

    private LayoutInflater mInflater;
    private Context mContext;
    private int selected_position = -1;

/////////////////////////////////////////////////////////////////////////
//   ADAPTER CONSTRUCTOR                                               //
/////////////////////////////////////////////////////////////////////////
    public Adapter_food_type(Context context) {
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
        viewHolder.category_name.setText(model_food_types.get(position).getFood_type_name());

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

                if (selected_position == position) {
                    selected_position = -1;
                    ui_listener_new_core_cooking.callFoodTypeSelected(null);
                    viewHolder.category_name.setTextColor(Color.parseColor( "#C5C5C5"));

                    notifyDataSetChanged();

                } else {
                    selected_position = position;
                    ui_listener_new_core_cooking.callFoodTypeSelected(model_food_types.get(position).getFood_type_name());
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
        if(model_food_types != null){
            return model_food_types.size(); }
        else{ return 0; }
    }




    public void set_food_type_list(List<Model_haccp_food_item_types> food_item_types) {
        this.model_food_types = food_item_types;
        notifyDataSetChanged();
    }

    public void set_all_but_filter(List<Model_haccp_food_item_types> food_item_types, int category_selected) {
        model_food_types.clear();

        for(int h = 0; h < food_item_types.size(); h++) {
            if(food_item_types.get(h).getFood_category_id() == category_selected) {
                model_food_types.add(food_item_types.get(h));
            }
        }

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
