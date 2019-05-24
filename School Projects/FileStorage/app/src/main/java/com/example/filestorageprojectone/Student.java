package com.example.filestorageprojectone;

import android.util.Log;

import com.example.filestorageprojectone.extensions.MyDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {

    private String name;
    private Date date;
    private int score;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Student(String name, Date date, int score) {
        this.name = name;
        this.date = date;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        String d = dateFormat.format(this.date);
        Log.i("Files", "getDate: " + d);

        return d;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return this.getName() + "\t" + this.getScore() + "\t" + this.getDate();
    }

    public static Student parseStudent(String dataString) {
        String[] data = dataString.split("\t");
        return new Student(data[0], MyDate.parseDate(data[2]), Integer.parseInt(data[1]));
    }
}
