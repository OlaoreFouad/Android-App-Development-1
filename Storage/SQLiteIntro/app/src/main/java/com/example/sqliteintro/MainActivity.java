package com.example.sqliteintro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.sqliteintro.data.DatabaseHandler;
import com.example.sqliteintro.models.Contact;
import com.example.sqliteintro.utils.Util;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(getBaseContext());

        /*Adding contacts!*/
        db.addContact(new Contact("Fouad", "02335923939"));
        db.addContact(new Contact("Mommy", "02049494949"));
        db.addContact(new Contact("Daddy", "05032355244"));
        db.addContact(new Contact("Fateh", "09034541134"));
        db.addContact(new Contact("Esther", "08056345522"));

        /*Reading Contacts back!*/
        Contact contact = db.getContact(1);
        Log.i(Util.DATABASE_TAG, contact.toString());

        List<Contact> contacts = db.getAllContacts();

        for(Contact c: contacts) {
            Log.i(Util.DATABASE_TAG, c.toString());
        }
    }
}
