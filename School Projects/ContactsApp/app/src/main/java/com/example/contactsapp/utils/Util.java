package com.example.contactsapp.utils;

public class Util {

//    Database Information
    public static final String DATABASE_NAME = "contactsDB";
    public static final int DATABASE_VERSION = 1;

//    Table Information
    public static final String TABLE_NAME = "contacts";

//    Column names.
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE_NUMBER = "phone_number";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_ORG = "organization";
    public static final String KEY_GROUPS = "groups";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_NICKNAME = "nickname";

//    Log Tags.
    public static final String DATABASE_TAG = "CONTACTS";
    public static final String TAG = "CA";

//    Value Retrieval Methods!
    public static String getValue(String val) {
        return val == null ? "" : val;
    }

    public static String join(String delimeter, String[] items) {
        String data = "";
        for (int cnt = 0; cnt < items.length; cnt++) {
            if (cnt == items.length-1) {
                data += items[cnt];
                break;
            } else {
                data += items[cnt] + delimeter;
            }
        }
        return data;
    }
}
