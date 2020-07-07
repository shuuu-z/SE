package com.totororamen.kiosk.data.io;

import com.totororamen.kiosk.data.entities.Item;
import com.totororamen.kiosk.data.entities.ItemOption;

import java.io.*;
import java.util.ArrayList;

/**
 * This class provides access to the ramen options table.
 */
public class ItemOptionsIO {
    // The options data stored in the file
    private ArrayList<ItemOption> data;

    // The file containing the options data, in CSV format
    private final File FILE = new File("data/tables/options.csv");

    // The first line of the file
    private final String FIRST_LINE = "Name,Options,Availability\r";

    /// Singleton instance
    private static ItemOptionsIO instance;

    /**
     * Private constructor.
     * We only need one instance of this class in this application
     */
    private ItemOptionsIO() {
        data = new ArrayList<>();
        if (FILE.exists())
            readData();
    }

    /**
     * Get the singleton instance
     * @return instance
     */
    public static ItemOptionsIO getInstance() {
        if (instance == null) {
            instance = new ItemOptionsIO();
        }
        return instance;
    }

    /**
     * Gets the list of options from the file
     * @return The list of options
     */
    public ArrayList<ItemOption> getData() {
        return data;
    }

    /**
     * Gets one option by its name
     * @param name The name of the option
     * @return The option found, if none, it will be {@code null}
     */
    public ItemOption getOptionByName(String name) {
        for (ItemOption option : data) {
            if (option.getName().equals(name)){
                return option;
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
            for (ItemOption item : data) {
                StringBuilder strOptions = new StringBuilder();
                StringBuilder strAvailability = new StringBuilder();
                for (int i = 0; i < item.getOptions().length; i++) {
                    strOptions.append(item.getOptions()[i]);
                    strAvailability.append(item.getAvailability()[i] ? "1" : "0");
                    if (i < item.getOptions().length - 1) {
                        strAvailability.append("|");
                        strOptions.append("|");
                    }

                }
                bw.write(String.format("%s,%s,%s\r", item.getName(), strOptions.toString(), strAvailability.toString()));
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
                String name = spilt[0];
                String[] options = spilt[1].split("\\|");
                String[] strAvailability = spilt[2].split("\\|");
                boolean[] availability = new boolean[strAvailability.length];

                for (int i = 0; i < strAvailability.length; i++) {
                    availability[i] = strAvailability[i].equals("1");
                }

                ItemOption itemOption = new ItemOption(name, options, availability);

                data.add(itemOption);
            }
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
