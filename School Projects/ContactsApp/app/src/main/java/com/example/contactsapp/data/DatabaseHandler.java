package com.example.contactsapp.data;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.contactsapp.models.Contact;
import com.example.contactsapp.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String CREATE_CONTACTS_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
            + Util.KEY_ID + " INTEGER PRIMARY KEY, "
            + Util.KEY_NAME + " TEXT, "
            + Util.KEY_PHONE_NUMBER + " TEXT, "
            + Util.KEY_EMAIL + " TEXT, "
            + Util.KEY_ADDRESS + " TEXT, "
            + Util.KEY_NICKNAME + " TEXT, "
            + Util.KEY_ORG + " TEXT, "
            + Util.KEY_GROUPS + " TEXT" + ")";

    private static final String SELECT_ALL_CONTACTS = "SELECT * FROM " + Util.TABLE_NAME;

    private SQLiteDatabase db;

    public DatabaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        /*dropping the table!*/
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);

        /*Create a new one!*/
        onCreate(db);

    }

    @TargetApi(Build.VERSION_CODES.O)
    public void addContact(Contact contact) {

        /*Set the database to be writable!*/
        setWritable();

        /*Using Content Values to set data!*/
        ContentValues values = new ContentValues();

//        Saving the available data in variables!
        String name = contact.getName();
        String phoneNumber = contact.getPhoneNumber();
        String email = Util.getValue(contact.getEmail());
        String address = Util.getValue(contact.getAddress());
        String nickname = Util.getValue(contact.getNickname());
        String groups = contact.getGroups() == null ? "" : String.join(", ", contact.getGroups());
        String org = Util.getValue(contact.getOrganization());

//        Inserting the values in the content values object!
        values.put(Util.KEY_NAME, name);
        values.put(Util.KEY_ADDRESS, address);
        values.put(Util.KEY_EMAIL, email);
        values.put(Util.KEY_NICKNAME, nickname);
        values.put(Util.KEY_ORG, org);
        values.put(Util.KEY_PHONE_NUMBER, phoneNumber);
        values.put(Util.KEY_GROUPS, groups);

//        Inserting in database!
        long rowIndex = this.db.insert(Util.TABLE_NAME, null, values);
        this.db.close();
    }

    public List<Contact> getAllContacts() {

//        Instantiate arrayList.
        List<Contact> contacts = new ArrayList<>();

//        Retrieving a readable database!
        setReadable();

        Cursor cursor = this.db.rawQuery(SELECT_ALL_CONTACTS, null);

        Log.i(Util.DATABASE_TAG, "getAllContacts: " + cursor.getCount());

        if (!(cursor.getCount() <= 0)) {
            cursor.moveToFirst();
            do {
                Contact contact = new Contact();

                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));

                contact.setEmail(cursor.getString(3));
                contact.setAddress(cursor.getString(4));
                contact.setNickname(cursor.getString(5));
                contact.setOrganization(cursor.getString(6));
//                contact.setEmail(cursor.getString(7));

                if (cursor.getString(3) != null) {
                    contact.setEmail(cursor.getString(3));
                }

                if (cursor.getString(4) != null) {
                    contact.setAddress(cursor.getString(4));
                }

                if (cursor.getString(5) != null) {
                    contact.setNickname(cursor.getString(5));
                }

                if (cursor.getString(6) != null) {
                    contact.setOrganization(cursor.getString(6));
                }

                if (cursor.getString(7) != null) {
                    contact.setGroups(cursor.getString(7).split(", "));
                }

                contacts.add(contact);

            } while (cursor.moveToNext());
        }

        cursor.close();
        close();

        return contacts;

    }

    private void setReadable() {
        this.db = this.getReadableDatabase();
    }

    private void setWritable() {
        this.db = this.getWritableDatabase();
    }

    public void close() {
        this.db.close();
    }
}
