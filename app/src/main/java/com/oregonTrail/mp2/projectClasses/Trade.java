/** Serves as a representation of a single trade between the player and a random NPC. */

package com.oregonTrail.mp2.projectClasses;

import androidx.annotation.NonNull;

public class Trade {
    private Item output;
    private int moneyInput = 0;
    private Item itemInput = null;

    public Trade(Item output, int moneyInput, Item itemInput) {
        this.output = output;
        this.moneyInput = moneyInput;
        this.itemInput = itemInput;
    }

    public Item getOutput() { return this.output; }
    public int getMoneyInput() { return this.moneyInput; }
    public Item getItemInput() { return this.itemInput; }

    /**
     * Creates a message that can be used to explain the trade
     * @return string message of the trade
     */
    @NonNull
    @Override
    public String toString() {
        String message = "A stranger offers you " + getOutput() + " in exchange for ";
        if (getMoneyInput() > 0) { message += "$" + getMoneyInput(); }
        if (getMoneyInput() > 0 && getItemInput() != null) {message += " and ";}
        if (getItemInput() != null) { message += "[ " + getItemInput() + " ]"; }
        return message;
    }

    public boolean accept(Wagon wagon) { // int moneyRef, Item itemInRef, Item itemOutRef
        Item itemOutRef = wagon.getInvItem(getOutput().getName());
        boolean success = true;
        if (getMoneyInput() > 0) {
            if (getMoneyInput() <= wagon.getMoney()) { wagon.setMoney(wagon.getMoney() - getMoneyInput()); }
            else { success = false; }
        }
        if (getItemInput() != null) {
            Item itemInRef = wagon.getInvItem(getItemInput().getName());
            if (getItemInput().getQuantity() <= itemInRef.getQuantity()) {
                itemInRef.incrementQuantity(-getItemInput().getQuantity());
            }
            else { success = false; }
        }
        if (success) {
            itemOutRef.incrementQuantity(getOutput().getQuantity());
        }
        return success;
    }
}
