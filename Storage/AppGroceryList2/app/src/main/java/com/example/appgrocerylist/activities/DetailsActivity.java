package com.example.appgrocerylist.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.appgrocerylist.R;
import com.example.appgrocerylist.models.Grocery;

public class DetailsActivity extends AppCompatActivity {

    private Grocery mGrocery;
    private TextView name, date, qty;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        name = findViewById(R.id.groceryNameIdDet);
        date = findViewById(R.id.groceryDateIdDet);
        qty = findViewById(R.id.groceryQtyIdDet);

        extras = getIntent().getExtras();

        if (extras != null && extras.get("grocery") != null) {
            populate(extras.get("grocery"));
        }
    }

    private void populate(Object extra) {
        mGrocery = (Grocery) extra;
        name.setText(mGrocery.getName());
        date.setText(mGrocery.getDateAdded());
        qty.setText(mGrocery.getQty());
    }
}
