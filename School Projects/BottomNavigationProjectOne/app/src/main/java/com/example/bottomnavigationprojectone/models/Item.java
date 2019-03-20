package com.example.bottomnavigationprojectone.models;

public class Item {

    private String name, description;
    private int color;

    public Item(String name, String description, int color) {
        this.name = name;
        this.description = description;
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
