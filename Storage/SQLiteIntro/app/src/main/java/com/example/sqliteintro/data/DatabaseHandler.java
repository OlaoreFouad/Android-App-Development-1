package com.example.sqliteintro.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.sqliteintro.models.Contact;
import com.example.sqliteintro.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private Contact contact;

    private final String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
            + Util.KEY_ID + " INTEGER PRIMARY KEY,"
            + Util.KEY_NAME + " TEXT,"
            + Util.KEY_PHONE_NUMBER + " TEXT"
            + ")";

    private final String SELECT_ALL_CONTACTS = "SELECT * FROM " + Util.TABLE_NAME;

    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_CONTACT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        When upgrading dbs, we would most likely delete existing tables and create new ones.
//        In this case, we would be deleting the contact table if it already exists.

//        Dropping the table!
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);

        onCreate(db);
    }

    /* Adding a Contact */
    public void addContact(Contact contact) {

//        Retrieve a writable database.
        setWritable();

//        Create a content values object and add contact info into it!
        ContentValues values = new ContentValues(); // Creates it!

//        Add contact info!
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

      db.insert(Util.TABLE_NAME, null, values);
      close();

    }

    /* Getting a Contact! */
    public Contact getContact(int id) {
//        Retrieving a readable database!
        setReadable();

        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{
                Util.KEY_ID, Util.KEY_NAME, Util.KEY_PHONE_NUMBER
        }, Util.KEY_ID + "=?", new String[]{
                String.valueOf(id)
        }, null, null, null, null);


        if (cursor != null) {
            cursor.moveToFirst();
            this.contact = new Contact(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2)
            );
        }
        
        cursor.close();
        close();

        return contact;
    }

    /* Getting all contacts */
    public List<Contact> getAllContacts() {

//        Retrieving a readable database!
            setReadable();    
        
        /* List to store all contacts. */
        List<Contact> contacts = new ArrayList<>();

        Cursor cursor = db.rawQuery(SELECT_ALL_CONTACTS, null);

        if (cursor != null) {
            cursor.moveToFirst();
            do {
                Contact contact = new Contact(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2)
                );

                contacts.add(contact);
            } while (cursor.moveToNext());
        }
        
        cursor.close();
        close();

        return contacts;
    }

    /*Updating a particular contact*/
    public void updateContact(Contact contact) {
        setWritable();
        ContentValues values = new ContentValues();

        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        db.update(Util.TABLE_NAME, values, Util.KEY_ID+"=?", new String[] {
                String.valueOf(contact.getId())
        });

        close();
    }

    /*Deleting a particular contact!*/
    public void deleteContact(Contact contact) {
        setWritable();
        db.delete(Util.TABLE_NAME, Util.KEY_ID+"=?", new String[] {
                String.valueOf(contact.getId())
        });
    }

    public void close() {
        this.db.close();
    }
    
    private void setReadable(){
        this.db = this.getReadableDatabase();
    }


    private void setWritable(){
        this.db = this.getWritableDatabase();
    }
}
