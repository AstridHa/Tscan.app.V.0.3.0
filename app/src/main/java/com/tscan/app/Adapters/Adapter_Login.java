package com.tscan.app.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.tscan.app.Activities.Activity_login;
import com.tscan.app.Data.Model_haccp_user;
import com.tscan.app.Data.Singleton_Sensor;
import com.tscan.app.Data.Singleton_Settings;
import com.tscan.app.R;
import com.tscan.app.ViewModel.ViewModel_haccp_queries;

import java.util.ArrayList;
import java.util.List;

import static com.tscan.app.Activities.Activity_login.ui_listener_login;

public class Adapter_Login extends RecyclerView.Adapter<Adapter_Login.ViewHolder>{
    private List<Model_haccp_user> userList = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;
    private ViewModel_haccp_queries viewModel_user;
    private int selected_position = -1;
    private Bitmap decodedSignature = null;


/////////////////////////////////////////////////////////////////////////
//   ADAPTER CONSTRUCTOR                                               //
/////////////////////////////////////////////////////////////////////////
    public Adapter_Login(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

/////////////////////////////////////////////////////////////////////////
//   ON CREATE VIEW ITEMS                                              //
/////////////////////////////////////////////////////////////////////////
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.row_user_list, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
        int  position = pos % userList.size();

        viewModel_user = ViewModelProviders.of((FragmentActivity) mContext).get(ViewModel_haccp_queries.class);
        Model_haccp_user current = userList.get(position);

        /////////////////////////////////////////////////////////////////////////
        //   DISPLAY ITEMS                                                     //
        /////////////////////////////////////////////////////////////////////////
        viewHolder.user_name.setText(current.getUser_name());

        if (selected_position == position) {
            viewHolder.user_name.setTextColor(Color.parseColor("#1E9405"));
            viewHolder.row_linear.setBackground(mContext.getResources().getDrawable(R.drawable.bck_task_type_round_green));
        } else {
            viewHolder.user_name.setTextColor(Color.parseColor( "#C5C5C5"));
            viewHolder.row_linear.setBackground(mContext.getResources().getDrawable(R.drawable.bck_task_type_round_grey));
        }

        /////////////////////////////////////////////////////////////////////////
        //   ITEMS ONCLICK LISTENER                                            //
        /////////////////////////////////////////////////////////////////////////
        int finalPosition = position;
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("CLICKED" , current.getUser_initials());

                if (selected_position == finalPosition) {
                    selected_position = -1;
                    Activity_login.user_model.setUser_name(null);
                    Activity_login.user_model.setUser_initials(null);
                    Singleton_Settings.getsettings_instance().setCurrent_user(null);

                    notifyDataSetChanged();
                    ui_listener_login.setUserNull("user null");

                } else {
                    selected_position = finalPosition;
                    Activity_login.user_model.setUser_name(current.getUser_name());
                    Activity_login.user_model.setUser_initials(current.getUser_initials());
                    Singleton_Settings.getsettings_instance().setCurrent_user(current.getUser_name());

                    notifyDataSetChanged();
                    ui_listener_login.setUserVariable(current.getUser_name());
                }
            }
        });

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                selected_position = finalPosition;
                ui_listener_login.setUserVariable(current.getUser_name());
                notifyDataSetChanged();
                alertdialog(userList.get(pos));
                return false;
            }
        });
    }


/////////////////////////////////////////////////////////////////////////
//   FUNCTIONS                                                         //
/////////////////////////////////////////////////////////////////////////
    @Override
    public int getItemCount() {
        if(userList != null){
            return userList.size(); }
        else{ return 0; }
    }

    public void setData(List<Model_haccp_user> users) {
        this.userList = users;
        notifyDataSetChanged();
    }

    private void alertdialog(Model_haccp_user user_table) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext, R.style.AlertDialogCustom);
        alertDialog.setTitle("Delete user");
        alertDialog.setMessage("Do you really want to delete this user?");
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                ui_listener_login.setUserNull("null");
                dialog.cancel();
            }
        });
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                viewModel_user.delete_user_haccp(user_table);
            }
        });


        alertDialog.show();
    }


/////////////////////////////////////////////////////////////////////////
//  VIEWHOLDER INITIALIZATION                                          //
/////////////////////////////////////////////////////////////////////////
    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView user_name;
        private RelativeLayout row_linear;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            user_name = itemView.findViewById(R.id.row_username);
            row_linear = itemView.findViewById(R.id.row_linear);
        }
    }
}
