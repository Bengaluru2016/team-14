package com.example.vinay.sqllogin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class SurveyActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    EditText e1,e2,e3,e4,e5,e6,e7;
    ImageView iv;
    Button sub,p;
    protected Bitmap yourbitmap;
    AlertDialog alertDialog;
    RadioButton rdbMale, rdbFemale;
    RadioGroup rgGender;
    String gender;


    private static final int CAMERA_PIC_REQUEST = 1111;
    private static final int RESULT_LOAD_IMAGE = 1;
    public class Background1 extends AsyncTask<String,Void,String> {
        Context context;

        Background1 (Context ctx){
            context = ctx;
        }
        @Override
        protected String doInBackground(String... params) {
            String type= params[0];
            String login_url = "http://ec2-54-169-131-166.ap-southeast-1.compute.amazonaws.com/submit_survey.php";
            if(type.equals("survey")){
                try {
                    String fir= params[1];
                    String lang= params[2];
                    String add= params[3];
                    String occ= params[4];
                    String phone= params[5];
                    String age= params[6];
                    String sex= params[7];


                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                    String postdata= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(fir,"UTF-8")
                            +"&"+URLEncoder.encode("age","UTF-8")+"="+URLEncoder.encode(age,"UTF-8")+"&"
                            +URLEncoder.encode("language","UTF-8")+"="+URLEncoder.encode(lang,"UTF-8")+"&"
                            +URLEncoder.encode("occ","UTF-8")+"="+URLEncoder.encode(occ,"UTF-8")+"&"
                            +URLEncoder.encode("mobile","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8")+"&"
                            +URLEncoder.encode("gender","UTF-8")+"="+URLEncoder.encode(sex,"UTF-8")+"&"
                            +URLEncoder.encode("addr","UTF-8")+"="+URLEncoder.encode(add,"UTF-8");


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
            alertDialog.setTitle("Your SID is");

        }


        @Override
        protected void onPostExecute(String aVoid) {
            alertDialog.setMessage("\t"+aVoid);
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
        setContentView(R.layout.activity_survey);
        e1=(EditText)findViewById(R.id.first);
        e2=(EditText)findViewById(R.id.lang);
        e3=(EditText)findViewById(R.id.address);
        e4=(EditText)findViewById(R.id.occupation);
        e5=(EditText)findViewById(R.id.phone);
        e6=(EditText)findViewById(R.id.age);
        rgGender = (RadioGroup) findViewById(R.id.rgGender);
        rdbMale = (RadioButton) findViewById(R.id.rdbMale);
        rdbFemale = (RadioButton) findViewById(R.id.rdbMale);

        iv=(ImageView)findViewById(R.id.imageView);
        iv.setImageResource(R.drawable.no_user);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);//load only one image*/
                // selectImage();
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int position) {
        // TODO Auto-generated method stub
        switch (position) {
            case R.id.rdbMale:
                gender = "true";
                break;
            case R.id.rdbFemale:
                gender = "false";
                break;

            default:
                break;
        }
    }
    public void photo(View view) {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_PIC_REQUEST);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_LOAD_IMAGE && resultCode==RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            iv.setImageURI(selectedImage);

            iv.buildDrawingCache();
            yourbitmap = iv.getDrawingCache();

        }

    }
    public void submit(View view)
    {
        String first=e1.getText().toString();
        String lang=e2.getText().toString();
        String add=e3.getText().toString();
        String occ=e4.getText().toString();
        String phone=e5.getText().toString();
        String age=e6.getText().toString();
                first="\""+first+"\"";
        lang="\""+lang+"\"";
        add="\""+add+"\"";
        occ="\""+occ+"\"";
        phone="\""+phone+"\"";
        age="\""+age+"\"";
        gender="\""+gender+"\"";
        String type="survey";
        Background1 bg1 = new Background1(this);
        bg1.execute(type,first,lang,add,occ,phone,age,gender);

    }
}
