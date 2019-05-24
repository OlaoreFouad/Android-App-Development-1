package com.example.mediaservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mediaservice.services.MusicService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button playButton, pauseButton, stopButton;
    private Intent musicIntentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButtonId);
        pauseButton = findViewById(R.id.pauseButtonId);
        stopButton = findViewById(R.id.stopButtonId);

        playButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        musicIntentService = new Intent(MainActivity.this, MusicService.class);
        switch (v.getId()) {
            case R.id.playButtonId: {
                startService(musicIntentService);
            }
            break;
            case R.id.stopButtonId: {
                stopService(musicIntentService);
            }
            break;
        }
    }
}
