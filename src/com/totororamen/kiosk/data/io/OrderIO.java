package com.totororamen.kiosk.data.io;

import com.totororamen.kiosk.data.entities.Order;

import java.io.*;
import java.util.ArrayList;

/**
 * This class provide access to the stored collection of {@code Order}
 */
public class OrderIO {
    // The Item data stored in the file
    private ArrayList<Order> data;

    // The file containing the items data, in CSV format
    private final File FILE = new File("data/tables/orders.csv");

    // The first line of the file
    private final String FIRST_LINE = "ID,MembershipID,Price,Number of ramens,Options,Add-ons,Take-away\r";

    // Singleton instance
    private static OrderIO instance;

    /**
     * Private constructor.
     * We only need one instance of this class in this application
     */
    private OrderIO() {
        data = new ArrayList<>();
        if (FILE.exists())
            readData();
    }

    /**
     * Gets the singleton instance of the class
     * If the instance is not initialized, create it
     * @return The instance
     */
    public static OrderIO getInstance() {
        if (instance == null) {
            instance = new OrderIO();
        }
        return instance;
    }

    /**
     * Gets the list of items from the file
     * @return The list of items
     */
    public ArrayList<Order> getData() {
        return data;
    }

    /**
     * Save the change of the data to the file
     */
    public void saveChanges() {
        try {
            if (FILE.exists()){
                if (!FILE.delete() || !FILE.createNewFile()){
                    return;
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE));
            bw.write(FIRST_LINE);
            for (Order item : data) {
                StringBuilder strOptions = new StringBuilder();
                StringBuilder strAddons = new StringBuilder();

                for (int i = 0; i < item.getOptionNames().length; i++) {
                    strOptions.append(String.format("%s:%d", item.getOptionNames()[i], item.getOptionsSelects()[i]));
                    if(i < item.getOptionNames().length - 1) {
                        strOptions.append("|");
                    }
                }

                for (int i = 0; i < item.getAddonNames().length; i++) {
                    strAddons.append(String.format("%s:%.02f:%d", item.getAddonNames()[i], item.getAddonsPrices()[i],item.getAddonsNumbers()[i]));
                    if (i < item.getAddonNames().length - 1) {
                        strAddons.append("|");
                    }
                }

                bw.write(String.format("%s,%08d,%.02f,%d,%s,%s,%s,%d,%s,%s\r",
                        item.getOrderID(),
                        item.getMembershipID(),
                        item.getPrice(),
                        item.getRamenNumber(),
                        strOptions,
                        strAddons,
                        item.isTakeAway() ? "1" : "0",
                        item.getPaymentType(),
                        item.getTime(),
                        item.getNote().replace(",", "[//]")
                ));
            }
            bw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Read data from the file
     */
    private void readData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE));
            String line = "";
            br.readLine();  // Skip the first line
            while ((line = br.readLine()) != null) {
                String[] spilt = line.split(",");
                Order item = new Order(
                        spilt[0],
                        Integer.parseInt(spilt[1]),
                        Float.parseFloat(spilt[2]),
                        Integer.parseInt(spilt[3]),
                        spilt[4],
                        spilt[5],
                        spilt[6].equals("1"),
                        Integer.parseInt(spilt[7]),
                        spilt[8],
                        spilt[9].replace("[//]", ",")
                );
                data.add(item);
            }
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
