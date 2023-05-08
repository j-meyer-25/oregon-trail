/**
 * Item.java
 * April 4, 2023
 * Truman Godsey and Josh Meyer
 * This class holds the information regarding items in the game. It handles holding the price of
 * each item, the name, units, and the quantity that the player has.
 */

package com.oregonTrail.mp2.projectClasses;

/** Class which holds the information regarding items in the game */
public class Item implements Cloneable {

    /** Instance Variables */
    private double price;
    private String name;
    private String unit;
    private int quantity;

    /** Constructor */
    public Item(double thisPrice, String thisName, String units, int quantity) {
        setPrice(thisPrice);
        setName(thisName);
        setUnits(units);
        setQuantity(quantity);
    }

    /** Getters and Setters */
    public double getPrice() { return this.price; }
    public String toString() { return this.name + ": " + this.quantity + " " + this.unit; }
    public int getQuantity() { return this.quantity; }

    public void setPrice(double newPrice) { this.price = newPrice; }
    public void setName(String newName) { this.name = newName; }
    public void setUnits(String unitString) { this.unit = unitString; }
    public void setQuantity(int quantity) { this.quantity = Math.max(quantity, 0); }
    public void incrementQuantity(int quantity) {
        this.quantity = Math.max(this.quantity + quantity, 0);
    }

    // Clone interface allows multiple items to be cloned from the shop
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}