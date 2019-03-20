package com.example.passingdata;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText messageEditText;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendButton = findViewById(R.id.sendButtonId);
        messageEditText = findViewById(R.id.messageEditTextId);

        sendButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!TextUtils.isEmpty(messageEditText.getText())) {
            String message = messageEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("message", message);

            startActivity(intent);
        } else {
            Snackbar.make(sendButton, "Provide text in the input field!",
                    Snackbar.LENGTH_SHORT).show();
        }
    }
}
