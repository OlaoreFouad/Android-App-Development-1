package com.example.radiobuttons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//    Radio Buttons
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    private String TAG = "RD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroupId);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                radioButton = findViewById(checkedId);

                switch (radioButton.getId()) {
                    case R.id.maleId: {
                        Log.d(TAG, "onCheckedChanged: Male is selected!");
                    }
                    break;
                    case R.id.femaleId: {
                        Log.d(TAG, "onCheckedChanged: Female is selected!");
                    }
                    break;
                    case R.id.othersId: {
                        Log.d(TAG, "onCheckedChanged: Others is selected!");
                    }
                    break;
                }
            }
        });
    }
}
