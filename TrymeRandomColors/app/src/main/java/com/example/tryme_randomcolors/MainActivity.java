package com.example.tryme_randomcolors;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private View rootView;
    private Button color_btn;
    private int[] colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Instantiating the colors array!
        colors = new int[]{ Color.CYAN, Color.RED, Color.BLUE, Color.DKGRAY, Color.GREEN, Color.GRAY, Color.YELLOW, Color.MAGENTA };



        rootView = findViewById(R.id.rootView);
        color_btn = findViewById(R.id.color_btn);

        rootView.setBackgroundColor(Color.RED);
        color_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              The Random object
                Random random = new Random();

                int value = random.nextInt(7);

                rootView.setBackgroundColor(colors[value]);
            }
        });
    }
}
