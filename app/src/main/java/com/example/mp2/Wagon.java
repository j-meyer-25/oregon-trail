package com.example.mp2;

import java.util.ArrayList;

public class Wagon {
    private double money;
    private ArrayList<Item> inventory = new ArrayList<Item>();
    private int pace; // 1-4
    //20

    // Constructors
    public Wagon(double starterCash) { this.money = starterCash; }
    public Wagon() { this.money = 0; }

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
        // TODO figure out how this should be calculated
        return milesForward;
    }
}
