package com.google.ar.core.examples.java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.google.ar.core.examples.java.augmentedimage.R;
import com.google.ar.core.examples.java.kotlingif.GifActivity;


public class Answer extends AppCompatActivity {


    private Button btn_to_gif;
    private Button btn_to_test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);


        //test01이미지를 인식하는 activity 등장
        btn_to_gif = (Button) findViewById(R.id.btn_to_gif);
        btn_to_gif.setOnClickListener(onClickListener);


        //test02이미지를 인식하는 activity 등장
        btn_to_test = (Button) findViewById(R.id.btn_to_test);
        btn_to_test.setOnClickListener(onClickListener);



    }

    //클릭 이벤트
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //test01이미지를 인식하는 activity 등장
                case R.id.btn_to_gif:

                    Intent intent1 = new Intent(Answer.this, GifActivity.class);
                    startActivity(intent1);

                    break;

                //test02이미지를 인식하는 activity 등장
                case R.id.btn_to_test:

                    Intent intent2 = new Intent(Answer.this, GifActivity.class);
                    startActivity(intent2);

                    break;

            }
        }
    };


}
