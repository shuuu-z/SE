package com.totororamen.kiosk.data.io;

import com.totororamen.kiosk.data.entities.Item;
import com.totororamen.kiosk.data.entities.ItemOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests of {@code ItemOption}
 * WARNING: This test will generate a 'Test option' item in options.csv, you should remove it manually
 */
class ItemOptionsIOTest {
    private ItemOptionsIO itemOptionsIO;

    @BeforeEach
    void setUp() {
        itemOptionsIO = ItemOptionsIO.getInstance();
        System.out.println("WARNING: This test will generate a 'Test option' item in options.csv, you should remove it manually");
    }

    @Test
    void test() {
        // Create a item and add it into the file
        ItemOption item = new ItemOption("Test Option", new String[] {"When", "will", "we", "back", "to", "school"},
                new boolean[] {true, true, true, false, false, false});
        itemOptionsIO.getData().add(item);
        itemOptionsIO.saveChanges();
        assertNotNull(itemOptionsIO.getOptionByName("Test Option")); // Check whether the new added item exists
    }
}