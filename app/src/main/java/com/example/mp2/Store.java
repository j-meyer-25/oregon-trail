package com.example.mp2;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Store {
    private ArrayList<Item> stock = new ArrayList<Item>();
    //private String location;

    // Constructor
    public Store(Item... items) { this.stock.addAll(Arrays.asList(items)); }

    /**
     * Fetches the store stock
     * @return array list of Items the store sells
     */
    public ArrayList<Item> getStock() { return stock; }

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
            for (int i = 1; i <= amount; i++) {
                Item newItem = (Item) itemToBuy.clone();
                wagon.addItem(newItem);
                wagon.setMoney(wagon.getMoney() - totalCost);
            }
        } else {
            return false;
        }
        return true;
    }
}
