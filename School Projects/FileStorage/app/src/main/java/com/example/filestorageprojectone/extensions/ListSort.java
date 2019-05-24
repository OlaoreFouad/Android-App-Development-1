package com.example.filestorageprojectone.extensions;

import com.example.filestorageprojectone.Student;

import java.util.Comparator;

public class ListSort implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        if (o2.getScore() == o1.getScore()) {
            return MyDate.parseDate(o2.getDate()).compareTo(MyDate.parseDate(o1.getDate()));
        } else {
            return o2.getScore() - o1.getScore();
        }
    }
}
