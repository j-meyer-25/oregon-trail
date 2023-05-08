/**
 * Wagon.java
 * April 4, 2023
 * Truman Godsey and Josh Meyer
 * This class functions as an inventory for all of the supplies brought on the trail. It also
 * includes the amount of money remaining, ox remaining, and methods for determining travel
 * for the day.
 */

package com.oregonTrail.mp2.projectClasses;

import java.util.ArrayList;

/** This class holds the inventory of the party, money remaining, and oxen remaining */
public class Wagon {
    /** Instance Variables */
    private double money;
    private ArrayList<Item> inventory = new ArrayList<>();
    private Oxen oxen = new Oxen(50, "Oxen", "", 6);
    private int pace = 1; // 1-4
    private double paceMultiplier = 1;

    /** Constructor */
    public Wagon(double starterCash) { this.money = starterCash; }

    /** Getters */
    public double getMoney() { return this.money; }
    public ArrayList<Item> getInventory() { return this.inventory; }
    public Oxen getOxen() { return this.oxen; }
    public int getPace() { return this.pace; }

    public void setPaceMultiplier(double paceMultiplier) {
        this.paceMultiplier = paceMultiplier;
    }

    /** Setters */
    public void setMoney(double money) { this.money = money; }
    public void setInventory(ArrayList<Item> inventory) { this.inventory = inventory; }
    public void setPace(int pace) { this.pace = pace; }

    /** Adds an Item object to the wagon's inventory arraylist
     * @param toAdd the item to add to the inventory
     */
    public void addItem(Item toAdd) { inventory.add(toAdd); }

    /** Based on various factors, especially Pace, this determines how far
     * the wagon should move in a day.
     * @return how many miles driven in the current day
     */
    public int driveForward() {
        int milesForward = 0;
        milesForward += Math.floor( (2.3*(this.getPace() - 1) + 22) * this.paceMultiplier ); // Custom pace formula
        setPaceMultiplier(1.0);
        return milesForward;
    }

    /**
     * Gets an inventory item by its name
     * @param name the string name of the item
     * @return Item if it exists, null otherwise
     */
    public Item getInvItem(String name) {
        for (Item item : inventory) {
            if (item.getName().equals(name)) { return item; }
         }
        return null;
    }

    /** Removes all items in the wagon's inventory. */
    public void clearInventory() { this.inventory.clear(); }

    /** This is the temporary inventory for the MVP assuming no shops */
    public void setDefaultInventory() {
        clearInventory();
        addItem(new Item(0.17, "Food", "Pounds", 2000));
        addItem(new Item(20, "Shotgun", "", 1));
        addItem(new Item(10, "Rifle", "", 1));
        addItem(new Item(2, "Clothing", "Sets", 8));
        addItem(new Item(5, "Shots", "", 500));
        addItem(new Item(8, "Spare Wagon Wheel", "", 3));
        addItem(new Item(3, "Spare Wagon Axel", "", 3));
        addItem(new Item(3, "Spare Wagon Tongue", "", 3));
        addItem(new Item(1.50, "Medical Supply Box", "", 1));
    }
}
