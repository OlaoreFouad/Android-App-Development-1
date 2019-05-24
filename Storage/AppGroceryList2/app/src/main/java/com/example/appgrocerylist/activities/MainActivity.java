package com.example.appgrocerylist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appgrocerylist.R;
import com.example.appgrocerylist.data.DatabaseHandler;
import com.example.appgrocerylist.models.Grocery;
import com.example.appgrocerylist.utils.Util;

public class MainActivity extends AppCompatActivity {

    private AlertDialog dialog;
    private AlertDialog.Builder builder;
    private EditText groceryItem;
    private EditText groceryQty;
    private Button saveButton;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHandler(this);
        bypassActivity();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showDialog();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showDialog() {
        builder = new AlertDialog.Builder(this);

//        Inflates the layout file into the view and instantiate the widgets present in the view!
        View view = getLayoutInflater().inflate(R.layout.add_item_dialog, null);
        groceryItem = view.findViewById(R.id.itemNameId);
        groceryQty = view.findViewById(R.id.itemQtyId);
        saveButton = view.findViewById(R.id.saveButtonId);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(TextUtils.isEmpty(groceryItem.getText()) && TextUtils.isEmpty(groceryQty.getText()))) {
                    Grocery grocery = new Grocery();
                    grocery.setName(groceryItem.getText().toString());
                    grocery.setQty(groceryQty.getText().toString());
                    saveGroceryToDB(grocery, v);
                } else {
                    Toast.makeText(MainActivity.this, "Specify the needed data!. Would you?", Toast.LENGTH_SHORT).show();
                }


            }
        });

//        Sets the custom view in the dialog to be the inflated view.
        builder.setView(view);

//        Creates the dialog by invoking the create() method. This returns an alert dialog instance.
        dialog = builder.create();

//        Displays the alert dialog!
        dialog.show();


    }

    private void saveGroceryToDB(Grocery grocery, View v) {
        this.db.addGrocery(grocery);
        Snackbar.make(v, "Item Saved!", Snackbar.LENGTH_SHORT);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        }, 1000);
    }

    private void bypassActivity() {
        if (db.getGroceryCount() > 0) {
            startActivity(new Intent(this, ListActivity.class));
            finish();
        }
    }
}
