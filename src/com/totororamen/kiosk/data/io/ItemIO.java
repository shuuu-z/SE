package com.totororamen.kiosk.data.io;

import com.totororamen.kiosk.data.entities.Item;

import java.io.*;
import java.util.ArrayList;

/**
 * This class provide read/write operations related to the {@code Item} objects.
 */
public class ItemIO {
    // The Item data stored in the file
    private ArrayList<Item> data;

    // The file containing the items data, in CSV format
    private final File FILE = new File("data/tables/items.csv");

    // The first line of the file
    private final String FIRST_LINE = "Name,Price,Maximum Number,Minimum Number,Available\r";

    // Singleton instance
    private static ItemIO instance;

    /**
     * Private constructor.
     * We only need one instance of this class in this application
     */
    private ItemIO() {
        data = new ArrayList<>();
        if (FILE.exists())
            readData();
    }

    /**
     * Gets the singleton instance of the class
     * If the instance is not initialized, create it
     * @return The instance
     */
    public static ItemIO getInstance() {
        if (instance == null) {
            instance = new ItemIO();
        }
        return instance;
    }

    /**
     * Gets one item by its name
     * @param name The name of the item
     * @return The item found, if none, it will be {@code null}
     */
    public Item getItemByName(String name) {
        for (Item item : data) {
            if (item.getName().equals(name)){
                return item;
            }
        }

        return null;
    }

    /**
     * Save the change of the data to the file
     */
    public void saveChanges() {
        try {
            if (FILE.exists()){
                // If the file exists, delete it and create a new one
                if (!FILE.delete() || !FILE.createNewFile()){
                    return;
                }
            }

            // Write the data to the file
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE));
            bw.write(FIRST_LINE);
            for (Item item : data) {
                bw.write(String.format("%s,%.02f,%d,%d,%s\r",
                        item.getName(),
                        item.getPrice(),
                        item.getMaxNumber(),
                        item.getMinNumber(),
                        item.isAvailable() ? "1" : "0"));
            }
            bw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the list of items from the file
     * @return The list of items
     */
    public ArrayList<Item> getData() {
        return data;
    }

    /**
     * Read data from the file
     */
    private void readData() {
        data.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE));
            String line = "";
            br.readLine();  // Skip the first line
            while ((line = br.readLine()) != null) {
                String[] spilt = line.split(",");
                Item item = new Item(spilt[0],
                        Float.parseFloat(spilt[1]),
                        Integer.parseInt(spilt[2]),
                        Integer.parseInt(spilt[3]),
                        spilt[4].contains("1"));
                data.add(item);
            }
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
