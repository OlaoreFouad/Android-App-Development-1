package com.example.moviedirectory.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private Context context;
    private SharedPreferences sharedPreferences;

    public Prefs(Context context) {
        this.context = context;
        sharedPreferences = this.context.getSharedPreferences("MainActivity", Context.MODE_PRIVATE);
    }

    public String getSearch() {
        return this.sharedPreferences.getString("search", "batman");
    }

    public void setSearch(String searchTerm) {
        this.sharedPreferences.edit().putString("search", searchTerm).apply();
    }

}
