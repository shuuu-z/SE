package com.totororamen.kiosk.data.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

/**
 * The management password helper
 * Provides encrypted storage and verification of the password
 */
public class ManagementPassword {
    // Default password, will be used if the password file don't exist
    private static final String DEFAULT_PASSWORD = "admin";

    // The hashed password read from file
    private static byte[] hashedPassword;

    // The file stores the hashed password
    private static File passwordFile = new File("data/password").getAbsoluteFile();

    static {
        if (!passwordFile.exists()) {
            // If the password file doesn't exists, create one
            hashedPassword = MD5digest(DEFAULT_PASSWORD);
            updatePassword();
        }
        else {
            readPassword();
        }
    }

    /**
     * Verify management password
     * @param password The password to be verified
     * @return True if the password matches the stored password
     */
    public static boolean verifyPassword(String password) {
        byte[] hashed = MD5digest(password);
        return Arrays.equals(hashed, hashedPassword);
    }

    /**
     * Modify the stored password
     * @param password The new password
     */
    public static void changePassword(String password) {
        hashedPassword = MD5digest(password);
        updatePassword();
    }

    /**
     * Hash the user password with MD5 algorithm as a base64-encoded string
     * @param password The clear user password
     * @return The MD5 digested password (base64 encoded)
     */
    public static String hashUserPassword(String password) {
        return Base64.getEncoder().encodeToString(MD5digest(password));
    }

    /**
     * Read the hashed password from the file
     */
    private static void readPassword() {
        try {
            FileInputStream fis = new FileInputStream(passwordFile);
            hashedPassword = new byte[16];
            fis.read(hashedPassword, 0, 16);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Update the hashed password to the file
     */
    private static void updatePassword() {
        try {
            if (hashedPassword != null) {
                FileOutputStream fos = new FileOutputStream(passwordFile, false);
                fos.write(hashedPassword, 0, 16);
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Use the MD5 algorithm to hash the password
     * @param password The plain password
     * @return The hashed password in bytes
     */
    private static byte[] MD5digest(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            return md.digest();
        }
        catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

}
