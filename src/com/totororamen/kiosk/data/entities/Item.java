package com.totororamen.kiosk.data.entities;

/**
 * An item, can be the Ramen or any add-ons for the ramen
 * Note that an item named "Ramen" is always required
 */
public class Item {
    private String name;
    private float price;
    private int maxNumber;
    private int minNumber;
    private boolean available;

    /**
     * Constructor of {@code Item}
     * @param name The name of the item, can be "Ramen" or any other add-ons
     * @param price The price of the item in pounds
     * @param maxNumber The maximum number of such item that a customer can purchase
     * @param minNumber The minimum number of such item that a customer can purchase
     * @param available Set whether this item is available
     */
    public Item(String name, float price, int maxNumber, int minNumber, boolean available) {
        this.name = name;
        this.price = price;
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
        this.available = available;
    }

    /**
     * Availability of the item
     * @return Is the item available
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets the availability of the item
     * @param available Is the item available
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Gets the name of the item
     * @return The name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the price of the item (in pounds)
     * @return The price of the item
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets the price of the item (in pounds)
     * @param price The price of the item
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Gets the maximum number of such item that a customer can purchase
     * @return The maximum number of such item that a customer can purchase
     */
    public int getMaxNumber() {
        return maxNumber;
    }

    /**
     * Gets the minimum number of such item that a customer can purchase
     * @return The minimum number of such item that a customer can purchase
     */
    public int getMinNumber() {
        return minNumber;
    }
}
