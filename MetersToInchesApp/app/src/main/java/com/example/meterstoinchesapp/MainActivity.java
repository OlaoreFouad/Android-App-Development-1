package com.example.meterstoinchesapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /*
    * 1m = 39.3701in
    * */

    private EditText mMeters;
    private Button convert_btn;
    private TextView mInches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMeters = findViewById(R.id.meters);
        convert_btn = findViewById(R.id.convert_btn);
        mInches = findViewById(R.id.inches_txt);

        convert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double multiplier = 39.37;
                double result;

                if (TextUtils.isEmpty(mMeters.getText())) {
                    mInches.setText(R.string.error_message);
                    mInches.setTextColor(Color.RED);
                } else {

                    double meter_value = Double.parseDouble(mMeters.getText().toString());
                    result = meter_value * multiplier;

                    mInches.setText(String.format("%.2f", result) + " inches");
                    mInches.setTextColor(Color.DKGRAY);
                }
            }
        });
    }
}
