package com.example.passingdatatoandfro;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button goButton;
    private TextView firstText;

    private final int REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.firstActivityEditTextId);
        goButton = findViewById(R.id.goButtonId);
        firstText = findViewById(R.id.firstActivityTextViewId);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editText.getText())) {
                    Snackbar.make(goButton, "Enter data bro!", Snackbar.LENGTH_SHORT).show();
                } else {
                    String data = editText.getText().toString();
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                    intent.putExtra("data", data);
//                    startActivity(intent);
                    startActivityForResult(intent, REQUEST_CODE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String first = data.getStringExtra("result");
                firstText.setText(first);
            }
        }
    }
}
