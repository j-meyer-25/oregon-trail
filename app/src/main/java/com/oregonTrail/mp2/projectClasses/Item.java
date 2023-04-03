package com.oregonTrail.mp2.projectClasses;

import androidx.annotation.NonNull;

public class Item implements Cloneable {
    private double price;
    private String name;
    private String unit;
    private int quantity;

    // Constructor
    public Item(double thisPrice, String thisName, String units, int quantity) {
        setPrice(thisPrice);
        setName(thisName);
        setUnits(units);
        setQuantity(quantity);
    }

    public double getPrice() { return this.price; }
    public String toString() { return this.name + this.unit; }
    public int getQuantity() { return this.quantity; }

    public void setPrice(double newPrice) { this.price = newPrice; }
    public void setName(String newName) { this.name = newName; }
    public void setUnits(String unitString) { this.unit = unitString; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void incrementQuantity(int quantity) {this.quantity += quantity;}

    // Clone interface allows multiple items to be cloned from the shop
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}