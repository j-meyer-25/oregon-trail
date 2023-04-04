package com.oregonTrail.mp2.projectClasses;

public class Oxen extends Item {
    private boolean injured = false;

    public Oxen(double thisPrice, String thisName, String units, int quantity) {
        super(thisPrice, thisName, units, quantity);
    }

    /** Tells whether an oxen in the group is injured or not. */
    public boolean isInjured() { return this.injured; }

    /** Injure an oxen in the group, or kill an already injured oxen. */
    public void injureOxen () {
        if (!this.injured) { injured = true; }
        else {
            incrementQuantity(-1);
            this.injured = false;
        }
    }

}
