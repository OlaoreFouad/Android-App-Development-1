package com.example.todolistapp.storage;

import android.content.Context;
import android.util.Log;

import com.example.todolistapp.models.Todo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class File {

    private OutputStreamWriter outputStreamWriter;
    private InputStreamReader inputStreamReader;

    private List<Todo> todos;
    private Context context;

    public File(Context context) {
        this.context = context;
    }

    public List<Todo> getTodos() {
        todos = new ArrayList<>();

        try {
            String[] todoData;
            inputStreamReader = new InputStreamReader(context.openFileInput("todolist"));

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                todoData = line.split("\t");
                Todo todo = new Todo(todoData[0], todoData[1]);

                todos.add(todo);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return todos;
    }

    public void addTodo(Todo todo) {
        try {
            FileOutputStream fos = context.openFileOutput("todolist", Context.MODE_APPEND);
            String todoString = todo.getTitle() + "\t" + todo.getDescription();
            fos.write(todoString.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
