package com.google.ar.core.examples.java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.google.ar.core.examples.java.augmentedimage.R;


import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class InstagramActivity extends AppCompatActivity {


    int GALLERY_CODE = 101;
    int REQUEST_PERMISSION_CODE = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram);




        selectGallery();


}



    private void selectGallery() {

        int permissionCheck = ContextCompat.checkSelfPermission(InstagramActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (PackageManager.PERMISSION_GRANTED == permissionCheck) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(intent, GALLERY_CODE);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION_CODE);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            switch (requestCode) {

                case 101:
                    shareImage(data.getData());
                    break;


                default:
                    break;
            }

        }
    }

    private void shareImage(Uri imgUri) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, imgUri);
        intent.setPackage("com.instagram.android");
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {

            case 1001:


                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent, GALLERY_CODE);


                break;
        }
    }


}