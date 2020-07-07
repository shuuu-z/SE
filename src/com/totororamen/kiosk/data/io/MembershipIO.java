package com.totororamen.kiosk.data.io;

import com.totororamen.kiosk.data.entities.Membership;

import java.io.*;
import java.util.ArrayList;

/**
 * This class provide read/write operations related to the {@code Membership} objects.
 */
public class MembershipIO {
    // The Item data stored in the file
    private ArrayList<Membership> data;

    // The file containing the membership data, in CSV format
    private final File FILE = new File("data/tables/membership.csv");

    // The first line of the file
    private final String FIRST_LINE = "ID,First Name,Last Name,E-mail,Phone,Stamps,Password,AutoPay\r";

    // Singleton instance
    private static MembershipIO instance;

    /**
     * Private constructor.
     * We only need one instance of this class in this application
     */
    private MembershipIO() {
        data = new ArrayList<>();
        if (FILE.exists())
            readData();
    }

    /**
     * Gets the singleton instance of the class
     * If the instance is not initialized, create it
     * @return The instance
     */
    public static MembershipIO getInstance() {
        if (instance == null) {
            instance = new MembershipIO();
        }
        return instance;
    }

    /**
     * Gets the list of items from the file
     * @return The list of items
     */
    public ArrayList<Membership> getData() {
        return data;
    }

    /**
     * Gets one membership info by its id
     * @param id The name of the item
     * @return The item found, if none, it will be {@code null}
     */
    public Membership getMembershipById(int id) {
        for (Membership m : data) {
            if (m.getMembershipID() == id) {
                return m;
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
                if (!FILE.delete() || !FILE.createNewFile()){
                    return;
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE));
            bw.write(FIRST_LINE);
            for (Membership item : data) {
                bw.write(String.format("%08d,%s,%s,%s,%s,%d,%s,%d\r",
                        item.getMembershipID(),
                        item.getFirstName(),
                        item.getLastName(),
                        item.getEmail(),
                        item.getPhone(),
                        item.getStampCount(),
                        item.getPassword(),
                        item.getAutoPay()));
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
                Membership item = new Membership(
                        Integer.parseInt(spilt[0]),
                        spilt[1],
                        spilt[2],
                        spilt[3],
                        spilt[4],
                        Integer.parseInt(spilt[5]),
                        spilt[6],
                        Integer.parseInt(spilt[7])
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
