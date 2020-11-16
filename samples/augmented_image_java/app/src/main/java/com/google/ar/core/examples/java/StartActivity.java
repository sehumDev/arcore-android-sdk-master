package com.google.ar.core.examples.java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.google.ar.core.examples.java.augmentedimage.AugmentedImageActivity;
import com.google.ar.core.examples.java.augmentedimage.R;

public class StartActivity extends AppCompatActivity {

    private Button btn_ar1;
    private Button btn_ar2;
    private Button btn_ar3;

    private Button shareButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //test01이미지를 인식하는 activity 등장
        btn_ar1 = (Button) findViewById(R.id.btn_ar1);
        btn_ar1.setOnClickListener(onClickListener);

        //test02이미지를 인식하는 activity 등장
        btn_ar2 = (Button) findViewById(R.id.btn_ar2);
        btn_ar2.setOnClickListener(onClickListener);

        //test03이미지를 인식하는 activity 등장
        btn_ar3 = (Button) findViewById(R.id.btn_ar3);
        btn_ar3.setOnClickListener(onClickListener);

        //test03이미지를 인식하는 activity 등장
        // shareButton = (Button)findViewById(R.id.fb_share_button);




    }
    //클릭 이벤트
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //test01이미지를 인식하는 activity 등장
                case R.id.btn_ar1:
                    Intent intent1 = new Intent(StartActivity.this, AugmentedImageActivity.class);
                    intent1.putExtra("AR", 1);
                    startActivity(intent1);
                    break;
                //test02이미지를 인식하는 activity 등장
                case R.id.btn_ar2:
                    Intent intent2 = new Intent(StartActivity.this, AugmentedImageActivity.class);
                    intent2.putExtra("AR", 2);
                    startActivity(intent2);
                    break;
                //test03이미지를 인식하는 activity 등장
                case R.id.btn_ar3:
                    Intent intent3 = new Intent(StartActivity.this, ChromaKeyVideoActivity.class);
                    intent3.putExtra("AR", 3);
                    startActivity(intent3);

                    break;

            }
        }
    };
}
