package com.example.todolistapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.todolistapp.adapters.TodoAdpater;
import com.example.todolistapp.models.Todo;
import com.example.todolistapp.storage.File;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView todoRecyclerView;
    private File file;
    private TodoAdpater adapter;
    private List<Todo> todoList;
    private FloatingActionButton addTodoButton;

    private android.widget.TextView noTodosTextView;
    private static final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoRecyclerView = findViewById(R.id.todoListId);
        noTodosTextView = findViewById(R.id.noTodosTextViewId);
        addTodoButton = findViewById(R.id.addTodoButtonId);

        file = new File(MainActivity.this);
        todoList = file.getTodos();

        if (todoList.isEmpty()) {
            noTodosTextView.setVisibility(View.VISIBLE);
        } else {
            noTodosTextView.setVisibility(View.GONE);
        }

        addTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, AddTodoActivity.class), REQUEST_CODE);
            }
        });

        initialize();
    }

    private void initialize(){
        todoList = file.getTodos();
        todoRecyclerView.setHasFixedSize(true);
        todoRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new TodoAdpater(this, todoList);
        Log.i("MainActivity", "initialize: " + todoList.toString());
        todoRecyclerView.setAdapter(adapter);

        if (todoList.isEmpty()) {
            noTodosTextView.setVisibility(View.VISIBLE);
        } else {
            noTodosTextView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                initialize();
            }
        }
    }
}
