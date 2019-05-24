package com.example.filestorageprojectone.extensions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate extends Date {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public MyDate() {
        this.simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    public static Date parseDate(String date) {
        Date d = null;
        try {
            d = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

}
