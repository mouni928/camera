package com.example.camera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button bt;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt=(Button) findViewById(R.id.bt);
        img=(ImageView) findViewById(R.id.img);
        if(ContextCompat.checkSelfPermission(
                MainActivity.this,
                Manifest.permission.CAMERA)!= PackageManager.PERMISSION_DENIED)
        {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CAMERA},101);
        }
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,101);
            }
        });
    }

    @Override
    protected void onActivityResult(int Reqcode, int rescode, @Nullable Intent data) {
        super.onActivityResult(Reqcode,rescode,data);
        if(Reqcode==101){
            Bitmap photo=(Bitmap) data.getExtras().get("data");
            img.setImageBitmap(photo);
        }
    }
}