package com.example.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mBillAmount;
    private SeekBar mSeekBar;
    private TextView mSeekBarTracker;
    private Button mButton;
    private TextView resultText;
    private TextView totalBill;

    private int seekBarPercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBillAmount = findViewById(R.id.billAmountId);
        mSeekBar = findViewById(R.id.seekBar);
        mSeekBarTracker = findViewById(R.id.seekBarTracker);
        mButton = findViewById(R.id.calculate_tip_btn);
        resultText = findViewById(R.id.resultTextId);
        totalBill = findViewById(R.id.totalBillTextId);

        mButton.setOnClickListener(this);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    mSeekBarTracker.setText(Integer.toString(progress) + "%");
                } else {
                    String tracker = Integer.toString(progress) + "%";
                    mSeekBarTracker.setText(tracker);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
//Do nothing!
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBarPercentage = seekBar.getProgress();
                calculate();
            }
        });
    }

    public void onClick(View v) {
        calculate();
    }

    public void calculate() {
        float result;

        if (!TextUtils.isEmpty(mBillAmount.getText().toString())) {
            int billAmount = Integer.parseInt(mBillAmount.getText().toString());
            result = billAmount * seekBarPercentage/100;

            resultText.setText("Tip amount: $" + String.valueOf(result));
            totalBill.setText("Total Bill: $" + String.valueOf(result + billAmount));
        } else {
            Toast.makeText(this, "Please enter a bill amount.", Toast.LENGTH_SHORT).show();
        }
    }
}
