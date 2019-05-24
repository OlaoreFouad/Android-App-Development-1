package com.example.todolistapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todolistapp.models.Todo;
import com.example.todolistapp.storage.File;

public class AddTodoActivity extends AppCompatActivity {

    private EditText titleEditText, descriptionEditText;
    private Button addTodoButton;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        titleEditText = findViewById(R.id.todoT);
        descriptionEditText = findViewById(R.id.todoD);
        addTodoButton = findViewById(R.id.addTodo);
        file = new File(AddTodoActivity.this);

        addTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(TextUtils.isEmpty(titleEditText.getText()) || TextUtils.isEmpty(descriptionEditText.getText()))) {
                    String title = titleEditText.getText().toString();
                    String description = descriptionEditText.getText().toString();

                    file.addTodo(new Todo(title, description));

                    setResult(RESULT_OK, new Intent());
                    finish();

                } else {
                    Toast.makeText(AddTodoActivity.this, "Fill in the inputs.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
