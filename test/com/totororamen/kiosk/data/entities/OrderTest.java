package com.totororamen.kiosk.data.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests of {@code Order}
 * This test aims to test the {@code printTicket()} method of {@code Order}
 */
class OrderTest {
    private Order order;

    @BeforeEach
    void setUp() {
        this.order = new Order("test", 0, 10.f, 1,
                new String[] {"Soup"},
                new int[] {1},
                new String[] { "Extra Test"},
                new float[] {1f},
                new int[] {1},
                false,
                1,
                "Sometimes",
                "some notes");
    }

    /**
     * Tests the {@code printTicket()} method.
     * The result will be printed to the console.
     */
    @Test
    void printTicket() {
        try {
            File file = new File("data/tickets/test.txt");
            if (file.exists()) {
                assertTrue(file.delete());
            }
            this.order.printTicket();
            System.out.println("The print ticket result:");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        }
        catch (Exception e) {
            fail(e.getMessage());  // Fail the test if there are errors
        }
    }
}