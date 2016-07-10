package com.webtechproject.webtech1.cfg1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

//Activity for teachers -- A teacher may either register a student or upload student performance for tracking progress

public class Teacher1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher1);
        Button myButton = (Button)findViewById(R.id.button1);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Teacher1.this, uploadprogress1.class));
            }

        });

        Button myButton1 = (Button)findViewById(R.id.button2);
        myButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Teacher1.this, studentregistration.class));
            }

        });

        /*
        public void onCreate(Bundle savedInstanceState) {
    super.onCreate(Bundle savedInstanceState);
    setContentView(R.layout.activity_main);

    Button myButton = findViewById(R.id.new_button);
    myButton.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, InsurancePage.class));
        }

    }
         */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_teacher1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
