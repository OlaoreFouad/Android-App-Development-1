package com.example.petbioapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView catImage, dogImage;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        catImage = findViewById(R.id.catId);
        dogImage = findViewById(R.id.dogId);

        catImage.setOnClickListener(this);
        dogImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.catId: {
                intent = new Intent(MainActivity.this, BioActivity.class);
                intent.putExtra("name", "Jarvis");
                intent.putExtra("bio", "Great cat. Loves people and meows a lot!");

                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
            break;
            case R.id.dogId: {
                intent = new Intent(MainActivity.this, BioActivity.class);
                intent.putExtra("name", "Dufus");
                intent.putExtra("bio", "Great god. Loves people, food and barks a lot!");

                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
            break;
            default:
                break;
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
