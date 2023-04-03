package com.oregonTrail.mp2.projectClasses;

public class Oxen extends Item {
    private boolean injured = false;

    public Oxen(double thisPrice, String thisName, String units, int quantity) {
        super(thisPrice, thisName, units, quantity);
    }

    public boolean isInjured() { return this.injured; }

    public void injureOxen () {
        if (!this.injured) { injured = true; }
        else {
            incrementQuantity(-1);
            this.injured = false;
        }
    }

}
