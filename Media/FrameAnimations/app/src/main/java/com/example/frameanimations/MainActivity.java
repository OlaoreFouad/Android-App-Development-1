package com.example.frameanimations;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private AnimationDrawable animationDrawable;
    private ImageView batImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        batImage = findViewById(R.id.batImageId);

        batImage.setBackgroundResource(R.drawable.bat_anim);

        animationDrawable = (AnimationDrawable) batImage.getBackground();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        animationDrawable.start();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animationDrawable.stop();
            }
        }, 5000);

        return super.onTouchEvent(event);
    }
}
