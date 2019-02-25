package com.example.radiobuttons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button send_btn;
    private RadioGroup radioGroup;

    private RadioButton male, female, others;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.activity_main);

//        Widget Ids
        send_btn = findViewById(R.id.sendButton);
        radioGroup = findViewById(R.id.radioGroupId);
        male = findViewById(R.id.maleId);
        female = findViewById(R.id.femaleId);
        others = findViewById(R.id.othersId);

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (male.isChecked()) {
                    showToast("Male is selected!");
                } else if(female.isChecked()) {
                    showToast("Female is selected!");
                } else if (others.isChecked()) {
                    showToast("Others is selected!");
                }
            }
        });
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
