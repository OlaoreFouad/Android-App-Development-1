package com.example.petbioapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Objects;

public class BioActivity extends AppCompatActivity {

    private ImageView petImage;
    private android.widget.TextView petName, petBio;

//    Extras
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio);

        petImage = findViewById(R.id.petImage);
        petName = findViewById(R.id.petName);
        petBio = findViewById(R.id.petBio);

        extras = getIntent().getExtras();
        Objects.requireNonNull(extras);

        setUp(extras.getString("name"), extras.getString("bio"));
    }

    private void setUp(String name,  String bio) {
        switch (name.toLowerCase()) {
            case "dufus": {
//                Show Dog Stuff.
                petImage.setImageDrawable(getResources().getDrawable(R.drawable.icon_lg_dog));
                petName.setText(name);
                petBio.setText(bio);
            }
            break;
            case "jarvis": {
//                Show Cat Stuff.
                petImage.setImageDrawable(getResources().getDrawable(R.drawable.icon_lg_cat));
                petName.setText(name);
                petBio.setText(bio);
            }
            break;
            default:
                break;
        }
    }
}
