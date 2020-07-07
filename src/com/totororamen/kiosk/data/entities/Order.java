package com.totororamen.kiosk.data.entities;

import com.totororamen.kiosk.data.io.ItemIO;
import com.totororamen.kiosk.data.io.ItemOptionsIO;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Entity class of the orders
 */
public class Order {
    private String orderID;
    private int membershipID;
    private float price;
    private int ramenNumber;
    private String[] options;
    private int[] optionsSelect;
    private String[] addons;
    private float[] addonsPrice;
    private int[] addonsNumber;
    private boolean takeAway;
    private int paymentType;
    private String time;
    private String note;

    public Order(String orderID, int membershipID, float price, int ramenNumber, String[] options, int[] optionsSelect,
                 String[] addons, float[] addonsPrice, int[] addonsNumber, boolean takeAway, int paymentType, String time, String note) {
        this.orderID = orderID;
        this.membershipID = membershipID;
        this.ramenNumber = ramenNumber;
        this.price = price;
        this.options = options;
        this.optionsSelect = optionsSelect;
        this.addons = addons;
        this.addonsPrice = addonsPrice;
        this.addonsNumber = addonsNumber;
        this.takeAway = takeAway;
        this.paymentType = paymentType;
        this.time = time;
        this.note = note;
    }

    public Order(String orderID, int membershipID, float price, int ramenNumber, String options, String addons, boolean takeAway, int paymentType, String time, String note) {
        this.orderID = orderID;
        this.membershipID = membershipID;
        this.price = price;
        this.ramenNumber = ramenNumber;
        this.takeAway = takeAway;
        this.paymentType = paymentType;
        this.time = time;
        this.note = note;

        String[] optionsSpilt = options.split("\\|");
        this.options = new String[optionsSpilt.length];
        this.optionsSelect = new int[optionsSpilt.length];
        for (int i = 0; i < optionsSpilt.length; i++) {
            this.options[i] = optionsSpilt[i].split(":")[0];
            this.optionsSelect[i] = Integer.parseInt(optionsSpilt[i].split(":")[1]);
        }

        String[] addonsSpilt = addons.split("\\|");
        this.addons = new String[addonsSpilt.length];
        this.addonsPrice = new float[addonsSpilt.length];
        this.addonsNumber = new int[addonsSpilt.length];
        for (int i = 0; i < addonsSpilt.length; i++) {
            this.addons[i] = addonsSpilt[i].split(":")[0];
            this.addonsPrice[i] = Float.parseFloat(addonsSpilt[i].split(":")[1]);
            this.addonsNumber[i] = Integer.parseInt(addonsSpilt[i].split(":")[2]);
        }
    }

    public void printTicket() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("data/tickets/" + getOrderID() + ".txt"));
            bw.write("             Totoro Ramen               \n");
            bw.write("========================================\n");
            bw.write("Order ID: " + getOrderID() + "\n");
            bw.write("========================================\n");
            bw.write("Dining Option: " + (isTakeAway() ? "Take away" : "Eat in") + "\n");
            bw.write("Ramen                  x" + getRamenNumber() + "\n");

            for (int i = 0; i < getOptionNames().length; i++) {
                ItemOption o = ItemOptionsIO.getInstance().getOptionByName(getOptionNames()[i]);
                if (o != null) {
                    bw.write("  " + getOptionNames()[i] + ": " + o.getOptions()[getOptionsSelects()[i]] + "\n");
                }
            }
            bw.write("========================================\n");
            bw.write("Add-ons:\n");
            for (int i = 0; i < getAddonNames().length; i++) {
                bw.write("  " + getAddonNames()[i] + "  x" + getAddonsNumbers()[i] + "\n");

            }
            bw.write("========================================\n");
            bw.write("Note: \n");
            bw.write("   " + getNote());

            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getOrderID() {
        return orderID;
    }

    public int getMembershipID() {
        return membershipID;
    }

    public int getRamenNumber() {
        return ramenNumber;
    }

    public float getPrice() {
        return price;
    }

    public String[] getOptionNames() {
        return options;
    }

    public int[] getOptionsSelects() {
        return optionsSelect;
    }

    public String[] getAddonNames() {
        return addons;
    }

    public float[] getAddonsPrices() {
        return addonsPrice;
    }

    public int[] getAddonsNumbers() {
        return addonsNumber;
    }

    public boolean isTakeAway() {
        return takeAway;
    }

    public void setTakeAway(boolean takeAway) {
        this.takeAway = takeAway;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
