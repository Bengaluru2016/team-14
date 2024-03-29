package com.example.vinay.sqllogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//Module to handle the start of a survey -- This is the intermediate screen with two buttons
//The user chooses an action here

public class InputActivity extends AppCompatActivity {

    private Button startSurvey;
    private Button updateRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

    }
    public void NewSurvey(View view)
    {
        startActivity(new Intent(this,SurveyActivity.class));
    }
    public void Update(View view)
    {
        startActivity(new Intent(this,TeacherActivity.class));
    }
    public void Missing(View view) {startActivity(new Intent(this,MissingActivity.class));}
}
