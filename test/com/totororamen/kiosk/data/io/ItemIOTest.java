package com.totororamen.kiosk.data.io;

import com.totororamen.kiosk.data.entities.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test of the {@code ItemIO} class
 */
class ItemIOTest {
    private ItemIO itemIO;

    @BeforeEach
    void setUp() {
        itemIO = ItemIO.getInstance();
        System.out.println("WARNING: This test will generate a 'Test item' item in items.csv, you should remove it manually");
    }

    @Test
    void test() {
        // Create a item and add it into the file
        Item item = new Item("Test item", 10.f, 1, 10, true);
        itemIO.getData().add(item);
        itemIO.saveChanges();
        assertNotNull(itemIO.getItemByName("Test item")); // Check whether the new added item exists
        assertNotNull(itemIO.getItemByName("Ramen"));     // Check whether the "Ramen" exists
    }
}