package com.example.textviewsandbuttons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = findViewById(R.id.mButton);
        mTextView = findViewById(R.id.mTextView);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTextView.getVisibility() == View.VISIBLE) {
                    mTextView.setVisibility(View.INVISIBLE);
                } else {
                    mTextView.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
