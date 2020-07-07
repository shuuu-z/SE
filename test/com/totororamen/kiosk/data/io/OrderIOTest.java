package com.totororamen.kiosk.data.io;

import com.totororamen.kiosk.data.entities.Membership;
import com.totororamen.kiosk.data.entities.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests of {@code OrderIO}
 * WARNING: This test will generate a 'test' order in orders.csv, you should remove it manually
 */
class OrderIOTest {
    private OrderIO orderIO;
    @BeforeEach
    void setUp() {
        orderIO = OrderIO.getInstance();
        System.out.println("WARNING: This test will generate a 'test' order in orders.csv, you should remove it manually");
    }

    @Test
    void test() {
        // Create a item and add it into the file
        Order order  = new Order("test", 0, 10.f, 1,
                new String[] {"Soup"},
                new int[] {1},
                new String[] { "Extra Test"},
                new float[] {1f},
                new int[] {1},
                false,
                1,
                "Sometimes",
                "some notes");
        orderIO.getData().add(order);
        orderIO.saveChanges();
        assertTrue(orderIO.getData().contains(order)); // Check whether the new added item exists
    }

}