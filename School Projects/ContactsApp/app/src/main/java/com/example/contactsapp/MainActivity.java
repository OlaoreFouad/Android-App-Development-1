package com.example.contactsapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.contactsapp.adapters.ContactAdapter;
import com.example.contactsapp.data.DatabaseHandler;
import com.example.contactsapp.models.Contact;
import com.example.contactsapp.utils.Util;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView contactsRecyclerView;
    private ContactAdapter adapter;
    private List<Contact> contacts;
    private DatabaseHandler db;

    private FloatingActionButton addContactButton;

    private android.widget.TextView noContactsText;
    private static final int REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.db = new DatabaseHandler(this);
        noContactsText = findViewById(R.id.noContactsTextId);
        addContactButton = findViewById(R.id.addContactButtonId);

        contactsRecyclerView = findViewById(R.id.contactsRecyclerViewId);
        noContactsText.setVisibility(View.GONE);
        initialize();

        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, AddContactActivity.class), REQUEST_CODE);
            }
        });

    }

    private void initialize() {
        contacts = this.db.getAllContacts();

        if (contacts.isEmpty()) {
            noContactsText.setVisibility(View.VISIBLE);
        } else {
            noContactsText.setVisibility(View.GONE);
            contactsRecyclerView.setHasFixedSize(true);
            contactsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            adapter = new ContactAdapter(this, contacts);
            contactsRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                if (data != null  && data.getSerializableExtra("contact") != null) {
                    Contact contact = (Contact) data.getSerializableExtra("contact");
                    this.db.addContact(contact);

                    initialize();
                }
            }
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Empty fields!", Toast.LENGTH_SHORT).show();
        }
    }
}
