package com.example.edittext_showmynameapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.editText);
        mTextView = findViewById(R.id.textView);
    }

    public void show(View v) {
        String enteredText = mEditText.getText().toString();

        mTextView.setText(enteredText);
        mTextView.setVisibility(View.VISIBLE);
    }
}
