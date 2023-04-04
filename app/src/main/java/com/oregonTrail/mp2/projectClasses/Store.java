/**
 * Store.java
 * April 4, 2023
 * Truman Godsey and Josh Meyer
 * This class holds the store name for each store in the game and holds the stock of the store.
 * This was it can be easily recovered for display to the user.
 */

package com.oregonTrail.mp2.projectClasses;

import java.util.ArrayList;
import java.util.Arrays;

/** This class holds the stock that stores sell and the name of each store */
public class Store {
    /** Instance Variables */
    private String storeName;
    private ArrayList<Item> stock = new ArrayList<Item>();
    //private String location;

    /** Constructor */
    public Store(String storeName, Item... items) {
        setStoreName(storeName);
        this.stock.addAll(Arrays.asList(items));
    }

    /**
     * Fetches the store stock
     * @return array list of Items the store sells
     */
    public ArrayList<Item> getStock() { return stock; }
    public String getStoreName() { return this.storeName; }
    public void setStoreName(String storeName) { this.storeName = storeName; }

    /**
     * Purchases an item of whatever quantity from a store, provided the right index
     * @param itemIndex the index of the items in the store that will be displayed, starting from 1
     * @param amount integer number of items to try and buy
     * @param wagon current wagon for the game
     * @return true if purchase was successful, false if not enough money
     */
    public boolean purchaseItems(int itemIndex, int amount, Wagon wagon) throws CloneNotSupportedException {
        Item itemToBuy = stock.get(itemIndex - 1);
        double totalCost = amount * itemToBuy.getPrice();
        if (totalCost <= wagon.getMoney()) {
            Item newItem = (Item) itemToBuy.clone();
            newItem.setQuantity(amount);
            wagon.addItem(newItem);
            wagon.setMoney(wagon.getMoney() - totalCost);
        } else {
            return false;
        }
        return true;
    }
}
