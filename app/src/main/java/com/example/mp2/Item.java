package com.example.mp2;

import androidx.annotation.NonNull;

public class Item implements Cloneable {
    private double price;
    private String name;
    private String unit;

    // Constructor
    public Item(double thisPrice, String thisName) {
        setPrice(thisPrice);
        setName(thisName);
    }

    public double getPrice() { return this.price; }
    public String toString() { return this.name + this.unit; }

    public void setPrice(double newPrice) { this.price = newPrice; }
    public void setName(String newName) { this.name = newName; }
    public void setUnits(String unitString) { this.unit = unitString; }

    // Clone interface allows multiple items to be cloned from the shop
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}