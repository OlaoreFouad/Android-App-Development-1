package com.example.externalfilesystem;

import android.content.DialogInterface;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private File file;
    private String path;
    private File currentDir;
    private String filename;

    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filename = "";
        showToast("External storage current state: " + Environment.getExternalStorageState());
        currentDir = Environment.getExternalStorageDirectory();

    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void newFile(View view) {
        getFileName();
//        File newfile = new File(this.currentDir, )
    }

    private String getFileName() {
        builder = new AlertDialog.Builder(this);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        android.widget.TextView heading = new TextView(this);
        heading.setText(R.string.file_name_text);

        final EditText filenameEditText = new EditText(this);
        filenameEditText.setHint(R.string.enter_file_name_text);

        layout.addView(heading);
        layout.addView(filenameEditText);

        builder.setView(layout)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!TextUtils.isEmpty(filenameEditText.getText().toString())) {
                            filename = filenameEditText.getText().toString();
                        } else {
                            showToast("No file name specified!");
                            return;
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showToast("No shit man!");
                    }
                });

        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (!TextUtils.isEmpty(filenameEditText.getText().toString())) {
                    filename = filenameEditText.getText().toString();
                } else {
                    showToast("No file name specified!");
                }
            }
        });

        builder.create().show();
        return filename;
    }
}
