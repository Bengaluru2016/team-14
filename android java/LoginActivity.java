package com.example.vinay.sqllogin;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    EditText u,p;
    Button lb;
    int a,i=0;
    ArrayList<String> result;
    AlertDialog alertDialog;
    public class Background1 extends AsyncTask<String,Void,String> {
        Context context;

        Background1 (Context ctx){
            context = ctx;
        }
        @Override
        protected String doInBackground(String... params) {
            String type= params[0];
            String login_url = "http://ec2-54-169-131-166.ap-southeast-1.compute.amazonaws.com/user_type.php";
            String reg_url = "http://10.0.2.2/register.php";
            String src_url = "http://10.0.2.2/search.php";
            if(type.equals("login")){
                try {
                    String username= params[1];
                    String password= params[2];
                    /*String[] params = new String[]{"username","password"};
                    String[] val = new String[]{username,password};*/

                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                    String postdata= URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                            +URLEncoder.encode("pwd","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");


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
            else if(type.equals("register")){
                try {

                    String name= params[1];
                    String lname= params[2];
                    String age= params[3];
                    String username= params[4];
                    String password= params[5];

                    URL url = new URL(reg_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String postdata= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                            +URLEncoder.encode("lname","UTF-8")+"="+URLEncoder.encode(lname,"UTF-8")+"&"
                            +URLEncoder.encode("age","UTF-8")+"="+URLEncoder.encode(age,"UTF-8")+"&"
                            +URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                            +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
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
        /*alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");*/

        }


        @Override
        protected void onPostExecute(String aVoid) {



            //int a=Integer.parseInt(aVoid);
            switch (Integer.parseInt(aVoid))
            {
                case -1:alertDialog=new AlertDialog.Builder(context).create();
                    alertDialog.setTitle("Login Status");
                    alertDialog.setMessage("Invalid Password");
                    alertDialog.show();
                    break;
                case  0:alertDialog=new AlertDialog.Builder(context).create();
                    alertDialog.setTitle("Login Status");
                    alertDialog.setMessage("Invalid User");
                    alertDialog.show();
                    break;
                case 1: Intent intent=new Intent(context.getApplicationContext(),InputActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
            //Intent intent=new Intent(context,InputActivity.class);
            //startActivity(intent);
            /*if(a==1)
            {Intent intent=new Intent(context,InputActivity.class);
            startActivity(intent);}
            else
                Toast.makeText(context,"Error in login",Toast.LENGTH_LONG).show();*/
            /*alertDialog.setMessage(aVoid);
            alertDialog.show();*/
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        u=(EditText)findViewById(R.id.uname);
        p=(EditText)findViewById(R.id.password);

    }
    public void Onlogin(View view) {
        String uname = u.getText().toString();
        String pass = p.getText().toString();
        uname="\""+uname+"\"";
        pass="\""+pass+"\"";
        String type = "login";


        Background1 bg1 = new Background1(this);
        bg1.execute(type,uname,pass);

    }

    public void Search(View view)
    {
        String str="123";
        String type="search";
        Background1 bg1 = new Background1(this);
        bg1.execute(type,str);


    }
    public void OpenReg(View view){
        startActivity(new Intent(this,RegisterActivity.class));
    }
}
