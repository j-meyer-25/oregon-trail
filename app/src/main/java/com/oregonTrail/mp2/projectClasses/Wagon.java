package com.oregonTrail.mp2.projectClasses;

import java.util.ArrayList;

public class Wagon {
    private double money;
    private ArrayList<Item> inventory = new ArrayList<Item>();
    private int pace; // 1-4
    //20

    // Constructors
    public Wagon(double starterCash) { this.money = starterCash; }

    // Getters
    public double getMoney() { return this.money; }
    public ArrayList<Item> getInventory() { return this.inventory; }
    public int getPace() { return this.pace; }

    // Setters
    public void setMoney(double money) { this.money = money; }
    public void setInventory(ArrayList<Item> inventory) { this.inventory = inventory; }
    public void setPace(int pace) { this.pace = pace; }

    public void addItem(Item toAdd) { inventory.add(toAdd); }

    public int driveForward() {
        int milesForward = 0;
        milesForward += Math.floor(2*(this.getPace() - 1) + 20); // Custom pace formula
        // TODO have more things like weather impact the milesForward
        return milesForward;
    }

    public void clearInventory() { this.inventory.clear(); }

    /** This is the temporary inventory for the MVP */
    public void setDefaultInventory() {
        clearInventory();
        addItem(new Item(0.17, "Food", "Pounds", 2000));
        addItem(new Item(20, "Shotgun", "", 1));
        addItem(new Item(10, "Rifle", "", 1));
        addItem(new Item(2, "Clothing", "Sets", 8));
        addItem(new Item(5, "Shots", "", 500));
        addItem(new Oxen(50, "Oxen", "", 6));
        addItem(new Item(8, "Spare Wagon Wheel", "", 3));
        addItem(new Item(3, "Spare Wagon Axel", "", 3));
        addItem(new Item(3, "Spare Wagon Tongue", "", 3));
        addItem(new Item(1.50, "Medical Supply Box", "", 1));
        addItem(new Item(0.50, "Sewing Kit", "", 1));
        addItem(new Item(0.25, "Fire Starting Kit", "", 1));
        addItem(new Item(8, "Spare Wagon Wheel", "", 3));
        addItem(new Item(0.05, "Kid's Toys", "", 3));
        addItem(new Item(0, "Keepsakes", "Trunk", 1));
        addItem(new Item(0.01, "Seed packages", "", 10));
        addItem(new Item(2.50, "Shovel", "", 5));
        addItem(new Item(2.75, "Spare Wagon Wheel", "Trunk", 1));
    }
}
