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

//Activity for teachers to upload student progress and attendance

public class ProgressActivity extends AppCompatActivity {

    AlertDialog alertDialog;
    EditText e1,e2,e3,e4;
    public class Background1 extends AsyncTask<String,Void,String> {
        Context context;

        Background1 (Context ctx){
            context = ctx;
        }
        @Override
        protected String doInBackground(String... params) {
            String type= params[0];
            String login_url = "http://ec2-54-169-131-166.ap-southeast-1.compute.amazonaws.com/update_progress.php";
            if(type.equals("progress")){
                try {
                    String id2 = params[1];
                    String clas= params[2];
                    String atte= params[3];
                    String per= params[4];

                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                    String postdata= URLEncoder.encode("sid","UTF-8")+"="+URLEncoder.encode(id2,"UTF-8")+"&"
                            +URLEncoder.encode("class","UTF-8")+"="+URLEncoder.encode(clas,"UTF-8")
                            +"&"+URLEncoder.encode("attendance","UTF-8")+"="+URLEncoder.encode(atte,"UTF-8")+"&"
                            +URLEncoder.encode("performance","UTF-8")+"="+URLEncoder.encode(per,"UTF-8");


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
        alertDialog.setTitle("Update Status");

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
        setContentView(R.layout.activity_stuprog);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        e3=(EditText)findViewById(R.id.editText3);
        e4=(EditText)findViewById(R.id.editText4);

    }
    public void savepro(View view)
    {
        String id1=e1.getText().toString();
        String cla=e2.getText().toString();
        String att=e3.getText().toString();
        String perf=e4.getText().toString();
        id1="\""+id1+"\"";
        cla="\""+cla+"\"";
        att="\""+att+"\"";
        perf="\""+perf+"\"";
        String type="progress";
        Background1 bg1 = new Background1(this);
        bg1.execute(type,id1,cla,att,perf);
    }

}
