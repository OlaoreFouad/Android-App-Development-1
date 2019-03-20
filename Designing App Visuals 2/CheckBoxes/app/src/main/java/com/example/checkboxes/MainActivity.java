package com.example.checkboxes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CheckBox momCheckbox, dadCheckbox, grandparentsCheckbox;
    private Button showButton;
    private TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        momCheckbox = findViewById(R.id.momId);
        dadCheckbox = findViewById(R.id.dadId);
        grandparentsCheckbox = findViewById(R.id.grandparentsId);

        showButton = findViewById(R.id.show_btn);
        result = findViewById(R.id.resultTextView);

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder sb = new StringBuilder();
                sb.append(momCheckbox.getText().toString() + " status is: " + momCheckbox.isChecked() + "\n");
                sb.append(dadCheckbox.getText().toString() + " status is: " + dadCheckbox.isChecked() + "\n");
                sb.append(grandparentsCheckbox.getText().toString() + " status is: " + grandparentsCheckbox.isChecked() + "\n");

                result.setText(sb);
            }
        });
    }
}
