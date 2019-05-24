package com.example.filestorageprojectone;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.filestorageprojectone.data.File;
import com.example.filestorageprojectone.extensions.ListSort;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private StudentAdapter adapter;
    private List<Student> students;

    private List<Student> students1;
    private ListSort listSort;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        mRecyclerView = findViewById(R.id.studentList);
        listSort = new ListSort();
        populateList();
        file = new File(this, "storage");

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new StudentAdapter(this, students);
        mRecyclerView.setAdapter(adapter);

    }

    private void populateList() {
        students = new ArrayList<>();
        file = new File(this, "storage");
        students = file.getStudents();

        if (!students.isEmpty()) {
            Collections.sort(students, listSort);
        }

//        students = sort(students);
    }

    private List<Student> sort(List<Student> sList) {
        List<Student> s = new ArrayList<>();
        students1 = new ArrayList<>(sList);
        for (int cnt = 0; cnt < sList.size(); cnt++) {
            s.add(getMax(sList));
        }

        return s;
    }

    private Student getMax(List<Student> s) {

        Student student = students1.get(0);
        for (Student stud: students1) {
            if (student.getScore() < stud.getScore()) {
                student = stud;
            }
        }

        students1.remove(student);
        return student;
    }

}
