package com.totororamen.kiosk.data.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The tests of {@code ManagementPassword}
 * WARNING: Password will be changed to "admin" for the test
 */
class ManagementPasswordTest {

    @BeforeAll
    static void setup() {
        // Setup the password for the test
        ManagementPassword.changePassword("admin");
        System.out.println("WARNING: The management password will be changed to \"admin\" for the test");
    }

    /**
     * Test verifying the password
     */
    @Test
    void verifyPassword() {
        assertTrue(ManagementPassword.verifyPassword("admin"));  // Should be true since it is the password
        assertFalse(ManagementPassword.verifyPassword("Admin")); // Should be false
        assertFalse(ManagementPassword.verifyPassword("ADMIN"));
        assertFalse(ManagementPassword.verifyPassword("admin123"));
    }

    /**
     * Test changing the password
     */
    @Test
    void changePassword() {
        ManagementPassword.changePassword("admin2");  // Change the password
        assertFalse(ManagementPassword.verifyPassword("admin")); // The previous password should be no longer valid
        assertTrue(ManagementPassword.verifyPassword("admin2"));
        ManagementPassword.changePassword("admin");
        assertFalse(ManagementPassword.verifyPassword("admin2"));
        assertTrue(ManagementPassword.verifyPassword("admin"));
    }

    @Test
    void hashUserPassword() {
        // System.out.println(ManagementPassword.hashUserPassword("123456789"));
        assertEquals(ManagementPassword.hashUserPassword("User"), ManagementPassword.hashUserPassword("User"));
        assertNotEquals(ManagementPassword.hashUserPassword("user"), ManagementPassword.hashUserPassword("User"));
        assertNotEquals(ManagementPassword.hashUserPassword("USER"), ManagementPassword.hashUserPassword("User"));
        assertNotEquals(ManagementPassword.hashUserPassword("User"), ManagementPassword.hashUserPassword("User "));
        assertNotEquals(ManagementPassword.hashUserPassword("User"), ManagementPassword.hashUserPassword("User123"));
    }
}