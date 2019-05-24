package com.example.filestorageprojectone.data;

import android.content.Context;
import android.util.Log;

import com.example.filestorageprojectone.Student;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class File {

    private Context context;
    private String filename;
    private List<Student> students;

    public File(Context context, String filename) {
        this.context = context;
        this.filename = filename;
        students = new ArrayList<>();
    }

    public void addStudent(Student s) {

        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_APPEND);

            fos.write(s.toString().getBytes());
            fos.close();
            Log.i("Files", "addStudent: done!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Student> getStudents() {
        try {
            FileInputStream fis = context.openFileInput(filename);

            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                Student student = Student.parseStudent(line);
                students.add(student);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return students;
    }
}
