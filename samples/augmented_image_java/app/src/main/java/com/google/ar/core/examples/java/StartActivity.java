package com.google.ar.core.examples.java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.google.ar.core.examples.java.augmentedimage.AugmentedImageActivity;
import com.google.ar.core.examples.java.augmentedimage.R;

public class StartActivity extends AppCompatActivity {

    private Button btn_ar1;
    private Button btn_ar2;
    private Button btn_ar3;

    private Button shareButton_insta;

    private ShareButton shareButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // 없어도 에러안남 혹시 몰라서 일단 써노음
        //FacebookSdk.sdkInitialize(this.getApplicationContext());

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
        shareButton_insta = (Button) findViewById(R.id.shareButton_insta);
        shareButton_insta.setOnClickListener(onClickListener);


        // 공유할 facebook 내용들
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setQuote("안녕하세요 푸른길~ 푸른길 홍보하기")
                .setContentUrl(Uri.parse("https://thinkcontest.com/ufiles/contest/bf28435d740ebdd4a9ff495ec48cbf8baf6ebc9f.jpg"))
                //.setContentUrl(Uri.parse("https://developers.facebook.com"))
                .build();

        // 페이스북 공유버튼
        shareButton = (ShareButton) findViewById(R.id.fb_share_button);
        shareButton.setShareContent(content);


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

                case R.id.shareButton_insta:
                    Intent intent4 = new Intent(StartActivity.this, InstagramActivity.class);
                    startActivity(intent4);

                    break;



            }
        }
    };
}
