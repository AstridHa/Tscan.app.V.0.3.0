//package com.tscan.app.Activities;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.tscan.app.R;
//
//public class Activity_exception_handler  extends AppCompatActivity {
//
//    private TextView text;
//    private LinearLayout exception_button;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_exception_handler);
//
//        text = findViewById(R.id.exception_title);
//        exception_button = findViewById(R.id.exception_button);
//
//        String intent = getIntent().getStringExtra("error");
//        text.setText(intent);
//
//        exception_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Activity_login.class);
//                startActivity(intent);
//            }
//        });
//
//    }
//
//    }
