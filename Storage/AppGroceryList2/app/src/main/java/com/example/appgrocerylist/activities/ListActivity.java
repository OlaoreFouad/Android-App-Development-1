package com.example.appgrocerylist.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.appgrocerylist.R;
import com.example.appgrocerylist.data.DatabaseHandler;
import com.example.appgrocerylist.models.Grocery;
import com.example.appgrocerylist.ui.GroceryAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private RecyclerView groceryList;
    private GroceryAdapter groceryAdapter;
    private List<Grocery> groceries, listItems;
    private DatabaseHandler db;
    private FloatingActionButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        groceryList = findViewById(R.id.groceryListId);
        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        groceries = new ArrayList<>();
        listItems = new ArrayList<>();
        db = new DatabaseHandler(this);

        groceries = db.getGroceries();

        for(Grocery c: groceries) {
            Grocery grocery = new Grocery();
            grocery.setId(c.getId());
            grocery.setName(c.getName());
            grocery.setQty(c.getQty());
            grocery.setDateAdded(c.getDateAdded());

            listItems.add(grocery);
        }

        groceryAdapter = new GroceryAdapter(this, listItems);

        groceryList.setLayoutManager(new LinearLayoutManager(this));
        groceryList.setHasFixedSize(true);
        groceryList.setAdapter(groceryAdapter);

    }
}
