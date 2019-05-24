package com.example.appgrocerylist.models;

import java.io.Serializable;

public class Grocery implements Serializable {

    private String name, qty, dateAdded;
    private int id;

    public Grocery() {
    }

    public Grocery(String name, String qty, String dateAdded, int id) {
        this.name = name;
        this.qty = qty;
        this.dateAdded = dateAdded;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
