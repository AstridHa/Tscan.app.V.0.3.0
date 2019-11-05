package com.tscan.app.Fragments;

import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.tscan.app.R;
import com.tscan.app.Data.Model_haccp_user;
//import com.tscan.app.UI_listeners.ExceptionHandler;
import com.tscan.app.UI_listeners.UI_Listener_login;
import com.tscan.app.Utils.CustomGestureListener;
import com.tscan.app.Utils.Utils;
import com.tscan.app.ViewModel.ViewModel_haccp_queries;

import java.io.ByteArrayOutputStream;

import static com.tscan.app.Activities.Activity_login.ui_listener_login;


public class Fragment_create_user extends Fragment {

    private Toolbar add_user_toolbar;
    private RelativeLayout save_button;
    private AppCompatAutoCompleteTextView user_name, user_initials;
    private TextView add_user_clear, add_user_signature;
    private ImageView user_fab_iv;
    private String imageEncoded = null;
    private ViewModel_haccp_queries viewModel_haccp_queries;
    private ProgressBar progressbar;
    private GestureOverlayView gestureOverlayView = null;
    Bitmap drawingCacheBitmap;


/////////////////////////////////////////////////////////////////////////
//   ON CREATE                                                         //
/////////////////////////////////////////////////////////////////////////
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_user, container, false);

        /////////////////////////////////////////////////////////////////////////
        //   INITIALIZATION                                                    //
        /////////////////////////////////////////////////////////////////////////
        gestureOverlayView = view.findViewById(R.id.add_user_canvas);
        gestureOverlayView.addOnGesturePerformedListener(new CustomGestureListener());

        add_user_toolbar = view.findViewById(R.id.add_user_toolbar);
        add_user_signature = view.findViewById(R.id.add_user_signature);
        user_name = view.findViewById(R.id.user_name);
        user_initials =  view.findViewById(R.id.user_initials);
        save_button = view.findViewById(R.id.add_user_save);
        add_user_clear = view.findViewById(R.id.add_user_clear);
        progressbar = view.findViewById(R.id.add_user_save_progress);
        user_fab_iv = view.findViewById(R.id.user_fab_iv);
        Toolbar add_user_toolbar = view.findViewById(R.id.add_user_toolbar);

        viewModel_haccp_queries = ViewModelProviders.of(this).get(ViewModel_haccp_queries.class);


        /////////////////////////////////////////////////////////////////////////
        //   TASK CATEGORY ONCLICK                                             //
        /////////////////////////////////////////////////////////////////////////
        add_user_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gestureOverlayView.clear(false);
            }
        });

        add_user_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        save_button.setBackground(getResources().getDrawable(R.drawable.bck_ripple_white_grey_radius_100));
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.avoid_double_click(v); // this is to avoid the event to tiigger 2 times in a row

                final String name = user_name.getText().toString().trim();
                final String initials = user_initials.getText().toString().trim();
                try {
                    // First destroy cached image.
                    gestureOverlayView.destroyDrawingCache();
                    // Enable drawing cache function.
                    gestureOverlayView.setDrawingCacheEnabled(true);
                    // Get drawing cache bitmap.
                    drawingCacheBitmap = gestureOverlayView.getDrawingCache();
                    // Create a new bitmap
                    Bitmap bitmap = Bitmap.createBitmap(drawingCacheBitmap);
                    // Compress bitmap to png image.
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 25, stream);
                    byte[] byteArray = stream.toByteArray();
                    imageEncoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                } catch (Exception e) {
                    Log.v("Signature Gestures", e.getMessage());
                    e.printStackTrace();
                }

                if(name.isEmpty()){
                    user_name.setError("Please complete");
                }
                else if (initials.isEmpty()){
                    user_initials.setError("Please complete");
                }
                else {
                    user_fab_iv.setVisibility(View.GONE);
                    progressbar.setVisibility(View.VISIBLE);

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        viewModel_haccp_queries.insert_user_haccp(new Model_haccp_user(name, initials, imageEncoded));
                        getFragmentManager().popBackStack();
                    }

                }, 2000);

                }
            }
        });

        return view;
    }


/////////////////////////////////////////////////////////////////////////
//   LIFECYCLE                                                         //
/////////////////////////////////////////////////////////////////////////
    @Override
    public void onStart(){
        super.onStart();
        Log.i("lifecycle_fragment_create", "onStart");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i("lifecycle_fragment_create", "onResume");

        ui_listener_login.setUserDuplicate(new UI_Listener_login.OnUserDuplicate() {
            @Override
            public void onUserDuplicate(String user_duplicate) {
                Utils.warning_dialog(getContext(), "Caution", "This username already exist.");
            }
        });
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.i("lifecycle_fragment_create", "onPause");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.i("lifecycle_fragment_create", "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("lifecycle_fragment_create", "onDestroy");
    }
}
