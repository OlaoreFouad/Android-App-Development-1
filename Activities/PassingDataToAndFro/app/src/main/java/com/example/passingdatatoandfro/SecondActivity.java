package com.example.passingdatatoandfro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class SecondActivity extends AppCompatActivity {

    private TextView secondText;
    private EditText editText;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        secondText = findViewById(R.id.secondActivityTextViewId);
        backButton = findViewById(R.id.backButtonId);

        Bundle extras = getIntent().getExtras();
        secondText.setText(extras.getString("data"));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "Coming back!";
                Intent intent = getIntent();
                intent.putExtra("result", result);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
