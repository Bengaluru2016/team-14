package com.example.vinay.sqllogin;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class StudentActivity extends AppCompatActivity {

    EditText e1,e2,e3,e4;
    AlertDialog alertDialog;
    public class Background1 extends AsyncTask<String,Void,String> {
        Context context;

        Background1 (Context ctx){
            context = ctx;
        }
        @Override
        protected String doInBackground(String... params) {
            String type= params[0];
            String login_url = "http://ec2-54-169-131-166.ap-southeast-1.compute.amazonaws.com/reg_student.php";
            if(type.equals("stureg")){
                try {
                    String id= params[1];
                    String type1= params[2];
                    String city= params[3];
                    String school= params[4];

                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                    String postdata= URLEncoder.encode("sid","UTF-8")+"="+URLEncoder.encode(id,"UTF-8")+"&"
                            +URLEncoder.encode("schoolname","UTF-8")+"="+URLEncoder.encode(type1,"UTF-8")
                            +"&"+URLEncoder.encode("city","UTF-8")+"="+URLEncoder.encode(city,"UTF-8")+"&"
                            +URLEncoder.encode("schooltype","UTF-8")+"="+URLEncoder.encode(school,"UTF-8")+"&"
                            ;


                    bufferedWriter.write(postdata);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line="";
                    while ((line = bufferedReader.readLine())!=null)
                    {
                        result +=line;
                    }
//                    a=Integer.parseInt(result);

                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            alertDialog=new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Registration Status");

        }


        @Override
        protected void onPostExecute(String aVoid) {
            alertDialog.setMessage(aVoid);
            alertDialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stureg);
        e1=(EditText)findViewById(R.id.sid);
        e4=(EditText)findViewById(R.id.sname);
        e2=(EditText)findViewById(R.id.stype);
        e3=(EditText)findViewById(R.id.city);

    }
    public void save(View view)
    {
        String id=e1.getText().toString();
        String type=e2.getText().toString();
        String city=e3.getText().toString();
        String school=e4.getText().toString();
        id="\""+id+"\"";
        type="\""+type+"\"";
        city="\""+city+"\"";
        school="\""+school+"\"";
        String type1="stureg";
        Background1 bg1 = new Background1(this);
        bg1.execute(type1,id,type,city,school);

    }
}
