package com.example.vinay.sqllogin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SurveyActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5;
    ImageView iv;
    Button sub,p;
    protected Bitmap yourbitmap;
    private static final int CAMERA_PIC_REQUEST = 1111;
    private static final int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        e1=(EditText)findViewById(R.id.first);
        e2=(EditText)findViewById(R.id.lang);
        e3=(EditText)findViewById(R.id.address);
        e4=(EditText)findViewById(R.id.occupation);
        e5=(EditText)findViewById(R.id.phone);
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
}
