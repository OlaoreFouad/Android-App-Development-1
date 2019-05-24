package com.example.appgrocerylist.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.appgrocerylist.models.Grocery;
import com.example.appgrocerylist.utils.Util;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String GROCERY_ADDED = "addGrocery: grocery added!";
    private static String CREATE_STATEMENT = "CREATE TABLE " + Util.TABLE_NAME + "("
            + Util.KEY_ID + " INTEGER PRIMARY KEY, "
            + Util.KEY_NAME + " TEXT, "
            + Util.KEY_QTY + " INTEGER, "
            + Util.KEY_DATE_ADDED + " LONG"
            + ")";

    private SQLiteDatabase db;
    private static String SELECT_ALL_GROCERIES = "SELECT * FROM " + Util.TABLE_NAME;

    public DatabaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);

        onCreate(db);
    }

    /**
     * CRUD OPERATIONS: Create, Read, Update and Delete.
     */

    /* Add Grocery */
    public void addGrocery(Grocery grocery) {
        setWritable();

//        Save the data into a content values object
        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, grocery.getName());
        values.put(Util.KEY_QTY, grocery.getQty());
        values.put(Util.KEY_DATE_ADDED, System.currentTimeMillis());

        this.db.insert(Util.TABLE_NAME, null, values);
        Log.i(Util.D_TAG, GROCERY_ADDED);
        close();
    }

    /* Get a grocery! */
    public Grocery getGrocery(int id) {
        setReadable();

        Grocery grocery = new Grocery();
        grocery.setId(id);

        Cursor cursor = this.db.query(Util.TABLE_NAME, new String[] {
                Util.KEY_NAME,
                Util.KEY_QTY,
                Util.KEY_DATE_ADDED
        }, Util.KEY_ID + "=?",
                new String[] {
                String.valueOf(id)
        }, null, null, null, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();

                grocery.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Util.KEY_ID))));
                grocery.setName(cursor.getString(cursor.getColumnIndex(Util.KEY_NAME)));
                grocery.setQty(cursor.getString(cursor.getColumnIndex(Util.KEY_QTY)));

//                Converting the date into a readable format!
                DateFormat dateFormat = DateFormat.getDateInstance();
                grocery.setDateAdded(dateFormat.format(
                        new Date(cursor
                                .getLong(
                                        cursor.getColumnIndex(Util.KEY_DATE_ADDED))
                        ).getTime()
                ));
            }
        }
        cursor.close();
        close();

        Log.i(Util.D_TAG, "getGrocery: grocery gotten!");
        return grocery;

    }

    /* Get all groceries */
    public List<Grocery> getGroceries() {
        setReadable();
        List<Grocery> groceries = null;

        Cursor cursor = this.db.query(
                Util.TABLE_NAME,
                new String[] {
                        Util.KEY_ID, Util.KEY_NAME, Util.KEY_QTY, Util.KEY_DATE_ADDED
                },
                null, null, null, null,
                Util.KEY_DATE_ADDED + " DESC"
        );

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                groceries = new ArrayList<>();

                cursor.moveToFirst();
                do {

                    Grocery grocery = new Grocery();

                    grocery.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Util.KEY_ID))));
                    grocery.setName(cursor.getString(cursor.getColumnIndex(Util.KEY_NAME)));
                    grocery.setQty(cursor.getString(cursor.getColumnIndex(Util.KEY_QTY)));

//                Converting the date into a readable format!
                    DateFormat dateFormat = DateFormat.getDateInstance();
                    grocery.setDateAdded(dateFormat.format(
                            new Date(cursor
                                    .getLong(
                                            cursor.getColumnIndex(Util.KEY_DATE_ADDED))
                            ).getTime()
                    ));

                    groceries.add(grocery);

                } while (cursor.moveToNext());
            }
        }

        if (groceries == null) {
            groceries = new ArrayList<>();
        }

        Log.i(Util.D_TAG, "getGroceries: all groceries gotten!");

        return groceries;

    }

    /* Update a grocery */
    public void updateGrocery(Grocery grocery) {
        setWritable();

        ContentValues values = new ContentValues();

        values.put(Util.KEY_NAME, grocery.getName());
        values.put(Util.KEY_QTY, grocery.getQty());
        values.put(Util.KEY_DATE_ADDED, System.currentTimeMillis()); // get system time!

        int status = db.update(Util.TABLE_NAME, values, Util.KEY_ID + "=?", new String[] {
           String.valueOf(grocery.getId())
        });

        if (status == -1) {
            Log.i(Util.D_TAG, "updateGrocery: error occured while adding grocery for " + values.get(Util.KEY_NAME));
        }
        Log.i(Util.D_TAG, "updateGrocery: grocery updated!");
        close();
    }

    /* Delete a grocery */
    public void deleteGrocery(int id) {
        setWritable();

        this.db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?", new String[] {
                String.valueOf(id)
        });
        Log.i(Util.D_TAG, "deleteGrocery: grocery deleted!");
        close();
    }

    /* Get grocery count */
    public int getGroceryCount() {
        setReadable();
        Cursor cursor = this.db.rawQuery(SELECT_ALL_GROCERIES, null);
//        close();

        Log.i(Util.D_TAG, "getGroceryCount: grocery count retrieved!");
        return cursor.getCount() <= 0 ? 0 : cursor.getCount();
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
