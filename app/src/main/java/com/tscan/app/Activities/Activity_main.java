package com.tscan.app.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.tscan.app.Adapters.MainActivity_pageAdapter;
import com.tscan.app.Data.Singleton_Settings;
import com.tscan.app.R;
//import com.tscan.app.UI_listeners.ExceptionHandler;

public class Activity_main extends TscanActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MainActivity_pageAdapter pageAdapter;

    int icons_grey[] = {
            R.drawable.ic_performance_grey,
            R.drawable.ic_today_grey,
            R.drawable.ic_profile_grey};

//    private RelativeLayout fab, fab_delivery, fab_blast_chilling, fab_cleaning, fab_core_cooking, fab_hot_held, fab_manager_duty, fab_thawing, fab_probe_callibration, fab_vacuum_packing;
//    private View view_close;
//    private ImageView fab_add_iv;

    boolean isFABOpen=false;

/////////////////////////////////////////////////////////////////////////
//   ON CREATE                                                         //
/////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /////////////////////////////////////////////////////////////////////////
        //   INITIALIZATION                                                    //
        /////////////////////////////////////////////////////////////////////////
//        view_close = findViewById(R.id.view_close);
//        fab_add_iv = findViewById(R.id.fab_add_iv);
//        fab = findViewById(R.id.fab);
//        fab_delivery = findViewById(R.id.fab_delivery);
//        fab_core_cooking = findViewById(R.id.fab_core_cooking);
//        fab_probe_callibration = findViewById(R.id.fab_probe_callibration);

        //PageAdapter and Tablayout
        viewPager = findViewById(R.id.main_viewPager);
        tabLayout = findViewById(R.id.main_tablayout);

        pageAdapter = new MainActivity_pageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);
        viewPager.setCurrentItem(1);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(icons_grey[0]);
        tabLayout.getTabAt(1).setIcon(icons_grey[1]);
        tabLayout.getTabAt(2).setIcon(icons_grey[2]);


        /////////////////////////////////////////////////////////////////////////
        //   ON CLICK LISTENERS                                                //
        /////////////////////////////////////////////////////////////////////////
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (!isFABOpen) {
//                    showFABMenu();
//                } else {
//                    closeFABMenu();
//                }
//            }
//        });
//
//        fab_delivery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                closeFABMenu();
//            }
//        });
//
//        fab_core_cooking.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                closeFABMenu();
////                openFragment_onDemand();
//            }
//        });
//
//        fab_probe_callibration.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                closeFABMenu();
//            }
//        });
//
//        view_close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                closeFABMenu();
//            }
//        });
    }


/////////////////////////////////////////////////////////////////////////
//   FUNCTIONS                                                         //
/////////////////////////////////////////////////////////////////////////
//    private void openFragment_onDemand() {
//        Bundle bundle = new Bundle();
//        Gson datajson = new Gson();
//
//            /** FOR THE MOMENT THIS IS STANDARD TO CORE_COOKING_MEASUREMENT.
//             * BUT IT WILL REQUIRE TO BE DYNAMIC AND
//             * SET AT CLICK TIME IN THE FUTURE*/
//            Model_window_joined_data model = new Model_window_joined_data();
//            model.setJoined_window_id(0);
//            model.setJoined_definition_id(3);
//            model.setJoined_definition_name("Core temp (chickens)");
//            model.setJoined_definition_company_id(Singleton_Settings.getsettings_instance().getCompany_id());
//            model.setJoined_definition_department_id(Singleton_Settings.getsettings_instance().getDepartment_id());
//            model.setJoined_definition_location_id(1);
//            model.setJoined_definition_joined_category_id(1);
//            model.setJoined_definition_joined_result_type_id(1);
//            model.setJoined_definition_instruction_text(" ");
//            model.setJoined_definition_quantity_required(1);
//            model.setJoined_definition_windows_generated_to_unix(null);
//            model.setJoined_category_name("default category");
//            model.setJoined_category_sort_order(1);
//            model.setJoined_result_type_name("Core Cooking Measurement");
//            model.setJoined_window_end_time_unix(null);
//            model.setJoined_window_start_time_unix(null);
//            model.setJoined_result_count(0);
//            model.setJoined_window_status(1);
//
//            String json = datajson.toJson(model);
//
//        bundle.putString("data", json);
//
//        Fragment_task_core fragment = new Fragment_task_core();
//        fragment.setArguments(bundle);
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_out_left);
//        ft.replace(R.id.fragment_place, fragment);
//        ft.addToBackStack(null);
//        ft.commit();
//    }
//
//    private void showFABMenu(){
//        isFABOpen=true;
//        view_close.setVisibility(View.VISIBLE);
//        fab_delivery.setVisibility(View.VISIBLE);
//        fab_core_cooking.setVisibility(View.VISIBLE);
//        fab_probe_callibration.setVisibility(View.VISIBLE);
//
//        view_close.setAlpha(1);
//        fab_delivery.setAlpha(1);
//        fab_core_cooking.setAlpha(1);
//        fab_probe_callibration.setAlpha(1);
//
//
//        fab_add_iv.animate().rotationBy(180).setListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animator) {
//
//                fab_delivery.animate().translationY(-fab.getHeight()+-getResources().getDimension(R.dimen.standard_12));
//                fab_probe_callibration.animate().translationY((-fab.getHeight()*2)+(-getResources().getDimension(R.dimen.standard_12)*2));
//                fab_core_cooking.animate().translationY((-fab.getHeight()*3)+(-getResources().getDimension(R.dimen.standard_12)*3));
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animator) {
//
//                if (fab_add_iv.getRotation() != 180) {
//                    fab_add_iv.setRotation(180);
//                }
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animator) {}
//
//            @Override
//            public void onAnimationRepeat(Animator animator) {}
//        });
//    }
//
//    private void closeFABMenu(){
//        isFABOpen=false;
//        view_close.setVisibility(View.GONE);
//        fab_add_iv.animate().rotationBy(-180).setListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animator) {
//
//                fab_delivery.animate().translationY(0);
//                fab_core_cooking.animate().translationY(0);
//                fab_probe_callibration.animate().translationY(0);
//
//                fab_delivery.animate().alpha(0);
//                fab_core_cooking.animate().alpha(0);
//                fab_probe_callibration.animate().alpha(0);
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animator) {
//                if(!isFABOpen){
//                    fab_delivery.setVisibility(View.GONE);
//                    fab_core_cooking.setVisibility(View.GONE);
//                    fab_probe_callibration.setVisibility(View.GONE);
//                }
//                if (fab_add_iv.getRotation() != -180) {
//                    fab_add_iv.setRotation(-180);
//                }
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animator) {
//
//            }
//        });
//    }


/////////////////////////////////////////////////////////////////////////
//   LIFECYCLE                                                         //
/////////////////////////////////////////////////////////////////////////
    @Override
    public void onResume() {
        super.onResume();

        if(Singleton_Settings.getsettings_instance().getCurrent_user() == null){
            startActivity(new Intent(Activity_main.this, Activity_login.class));
            finish();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed(){
    }
}
