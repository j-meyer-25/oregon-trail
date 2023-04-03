package com.oregonTrail.mp2.projectClasses;

public class Oxen extends Item {
    private int health = 100;

    public Oxen(double thisPrice, String thisName, String units, int quantity) {
        super(thisPrice, thisName, units, quantity);
    }

    public int getHealth() { return this.health; }
    public void setHealth(int health) { this.health = health; }
}
