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

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;
    private TextView signupText;

    private SharedPreferences sharedPreferences;
    private int counter = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditTextId);
        passwordEditText = findViewById(R.id.passwordEditTextId);
        loginButton = findViewById(R.id.loginButtonId);
        signupText = findViewById(R.id.sign_up_txt);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
    }

    private void login() {
        if (!(TextUtils.isEmpty(usernameEditText.getText()) && TextUtils.isEmpty(passwordEditText.getText()))) {
            sharedPreferences = getSharedPreferences(Utils.TAG, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putBoolean("authenticated", true);

            editor.commit();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);

            startActivity(intent);
        } else {
            ConstraintLayout constraintLayout = findViewById(R.id.constraintLayoutId);
            Snackbar.make(constraintLayout, "Please provide inputs", Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (counter == 0) {
            Toast.makeText(this, "Press again to exit!", Toast.LENGTH_SHORT).show();
            counter++;
        } else if (counter == 1) {
            finish();
            moveTaskToBack(true);
        }
    }
}
