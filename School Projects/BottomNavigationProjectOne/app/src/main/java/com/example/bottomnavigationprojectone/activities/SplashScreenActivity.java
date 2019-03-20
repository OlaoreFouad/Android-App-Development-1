package com.example.bottomnavigationprojectone.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.bottomnavigationprojectone.MainActivity;
import com.example.bottomnavigationprojectone.R;
import com.example.bottomnavigationprojectone.util.Utils;

public class SplashScreenActivity extends AppCompatActivity {

    private Intent intent;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPreferences = getSharedPreferences(Utils.TAG, MODE_PRIVATE);
        boolean authenticated = sharedPreferences.getBoolean("authenticated", false);

        Log.i(Utils.TAG, "Authenticated: " + Boolean.toString(authenticated));

        if (authenticated) {
            setIntent(MainActivity.class);
        } else {
            setIntent(LoginActivity.class);
        }
    }

    private void setIntent(final Class aClass) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                intent = new Intent(SplashScreenActivity.this, aClass);
                startActivity(intent);
            }
        }, 3000);
    }
}
