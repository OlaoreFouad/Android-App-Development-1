package com.example.bottomnavigationprojectone.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomnavigationprojectone.MainActivity;
import com.example.bottomnavigationprojectone.R;
import com.example.bottomnavigationprojectone.util.Utils;

public class SignupActivity extends AppCompatActivity {

    private EditText firstnameEditText, lastnameEditText, passwordEditText;
    private Button signupButton;
    private TextView loginText;

    private SharedPreferences sharedPreferences;
    private int counter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        lastnameEditText = findViewById(R.id.lastnameEditTextId);
        firstnameEditText = findViewById(R.id.firstnameEditTextId);
        passwordEditText = findViewById(R.id.passwordEditTextId);
        signupButton = findViewById(R.id.signupButtonId);
        loginText = findViewById(R.id.login_txt);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });
    }

    private void signup() {
        if (!(TextUtils.isEmpty(firstnameEditText.getText()) && TextUtils.isEmpty(passwordEditText.getText())
                && TextUtils.isEmpty(passwordEditText.getText()))) {
            sharedPreferences = getSharedPreferences(Utils.TAG, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putBoolean("authenticated", true);

            editor.commit();
            Intent intent = new Intent(SignupActivity.this, MainActivity.class);

            startActivity(intent);
        } else {
            ConstraintLayout constraintLayout = findViewById(R.id.constraintLayoutId);
            Snackbar.make(constraintLayout, "Please provide inputs", Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onBackPressed() {
        counter++;

        if (counter == 0) {
            Toast.makeText(this, "Press again to exit!", Toast.LENGTH_SHORT).show();
            counter++;
        } else if (counter == 1) {
            super.onBackPressed();
            finish();
            moveTaskToBack(true);
        }
    }
}
