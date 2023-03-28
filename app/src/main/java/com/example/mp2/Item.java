package com.example.mp2;

import androidx.annotation.NonNull;

public class Item implements Cloneable {
    private double price;
    private String name;

    // Constructor
    public Item(double thisPrice, String thisName) {
        setPrice(thisPrice);
        setName(thisName);
    }

    public double getPrice() { return this.price; }
    public String toString() { return this.name; }

    public void setPrice(double newPrice) { this.price = newPrice; }
    public void setName(String newName) { this.name = newName; }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}