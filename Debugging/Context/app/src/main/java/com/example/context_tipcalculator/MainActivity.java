package com.example.context_tipcalculator;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mBillAmount;
    private SeekBar mSeekBar;
    private TextView mResult;
    private TextView mSeekbarProgress;

    private Button calculateButton;
    private int progress;
    
    //TODO: fix this code
    //TODO: build an amazing application with this framework.
    // TODO: 3/16/2019 I love myself.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBillAmount = findViewById(R.id.billAmountEditTextId);
        mSeekBar = findViewById(R.id.tipSeekbarId);
        mResult = findViewById(R.id.resultTextviewId);

        mSeekbarProgress = findViewById(R.id.seekBarProgressId);

        calculateButton = findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mBillAmount.getText())) {
                    Toast.makeText(MainActivity.this, "Enter a bill amount", Toast.LENGTH_SHORT).show();
                } else {
                    int total = Integer.parseInt(mBillAmount.getText().toString()) + mSeekBar.getProgress();
                    mResult.setText(getString(R.string.total_is) + total);
                }
            }
        });

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    mSeekbarProgress.setText("0/20");
                } else {
                    mSeekbarProgress.setText(progress + "/20");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                progress = seekBar.getProgress();
            }
        });

    }
}
