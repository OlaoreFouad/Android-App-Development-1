package com.example.todolistapp.models;

public class Todo {

    private String title, description;

    public Todo(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
}
