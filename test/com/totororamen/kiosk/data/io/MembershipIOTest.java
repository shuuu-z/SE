package com.totororamen.kiosk.data.io;

import com.totororamen.kiosk.data.entities.ItemOption;
import com.totororamen.kiosk.data.entities.Membership;
import com.totororamen.kiosk.data.utils.ManagementPassword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests of {@code MembershipIO}
 * WARNING: This test will generate a 'Test membership' item in membership.csv with Id 999, you should remove it manually
 */
class MembershipIOTest {
    private MembershipIO membershipIO;

    @BeforeEach
    void setUp() {
        membershipIO = MembershipIO.getInstance();
        System.out.println("WARNING: This test will generate a 'Test membership' item in membership.csv with Id 999, you should remove it manually");
    }

    @Test
    void test() {
        // Create a item and add it into the file
        Membership membership = new Membership(999, "Test", "Membership", "test@example.com",
                "11111", 999, ManagementPassword.hashUserPassword("123456"), 0);
        membershipIO.getData().add(membership);
        membershipIO.saveChanges();
        assertNotNull(membershipIO.getMembershipById(999)); // Check whether the new added item exists
    }
}