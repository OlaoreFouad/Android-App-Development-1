package com.example.filestorageprojectone;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.filestorageprojectone.data.File;
import com.example.filestorageprojectone.extensions.MyDate;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    Input Fields
    private EditText nameEdit, scoreEdit, dateEdit;
    private Button addButton, viewButton;
    private DatePickerDialog datePickerDialog;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

//    Intent
    private Intent intent;

//    Storage objects
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEdit = findViewById(R.id.studentNameEditId);
        scoreEdit = findViewById(R.id.studentScoreEditId);
        dateEdit = findViewById(R.id.studentDateEditId);

        addButton = findViewById(R.id.addButtonId);
        addButton.setOnClickListener(this);

        dateEdit.setText(this.dateFormat.format(getCurrentDate()));
        dateEdit.setOnClickListener(this);

        viewButton = findViewById(R.id.viewButtonId);
        viewButton.setOnClickListener(this);

        file = new File(this, "storage");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addButtonId: {
                add();
            }
            break;
            case R.id.studentDateEditId: {
//                datePickerDialog.show();
            }
            break;
            case R.id.viewButtonId: {
                 intent = new Intent(MainActivity.this, DataActivity.class);
                 startActivity(intent);
            }
            break;
            default:
                break;
        }
    }

    private Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();

        Date date = calendar.getTime();

        return date;
    }

    private void add() {
        if (!(TextUtils.isEmpty(nameEdit.getText()) || TextUtils.isEmpty(scoreEdit.getText()))) {
            String name = nameEdit.getText().toString();
            int score = Integer.parseInt(scoreEdit.getText().toString());
            String date = dateEdit.getText().toString();

            Date d = MyDate.parseDate(date);

            file.addStudent(new Student(name, d, score));
            Log.i("Files", "add: done!");

        } else {
            Toast.makeText(this, "Add text in fields!", Toast.LENGTH_SHORT).show();
        }
    }
}
