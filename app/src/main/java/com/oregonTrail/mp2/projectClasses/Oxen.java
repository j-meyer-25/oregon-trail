/**
 * Oxen.java
 * April 4, 2023
 * Truman Godsey and Josh Meyer
 * This class inherits from the Item class and is used to store information regarding Oxen you have
 * on the trail. If an Ox gets hurt once they get injured, if they get hurt again they die. Necessary
 * information regarding this process is held in this class.
 */

package com.oregonTrail.mp2.projectClasses;

/** Specialized class for holding if an ox is injured and running the method to hurt ox if random event hits */
public class Oxen extends Item {

    /** Instance Variables */
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
