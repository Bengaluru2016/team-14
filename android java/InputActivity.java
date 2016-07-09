package com.example.vinay.sqllogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
}
