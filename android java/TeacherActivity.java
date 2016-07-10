package com.example.vinay.sqllogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//Intermediate screen for teachers

public class TeacherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        Button myButton = (Button) findViewById(R.id.button1);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherActivity.this, ProgressActivity.class));
                finish();
            }

        });

        Button myButton1 = (Button) findViewById(R.id.button2);
        myButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherActivity.this, StudentActivity.class));
                finish();
            }

        });
    }
}