package com.totororamen.kiosk.data.entities;

/**
 * Membership information
 * Contains the id, full name, email address and phone number of the membership
 */
public class Membership {
    private int membershipID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private int autoPay;

    // How many virtual stamps the member have
    private int stampCount;

    /**
     * Constructor of {@code Membership}
     * @param membershipID The identification number of member, should be an integer
     * @param firstName The first name of the member
     * @param lastName The last name of the member
     * @param email The e-mail address of the member
     * @param phone The phone number of the member
     * @param stampCount The current number of virtual stamps the member have
     * @param autoPay Whether the user use auto pay
     * @param password The hashed user password
     */
    public Membership(int membershipID, String firstName, String lastName, String email, String phone, int stampCount, String password, int autoPay) {
        this.membershipID = membershipID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.stampCount = stampCount;
        this.password = password;
        this.autoPay = autoPay;
    }

    /**
     * Gets the membership number
     * @return The membership number
     */
    public int getMembershipID() {
        return membershipID;
    }

    /**
     * Gets the first name of the member
     * @return The first name of the member
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the member
     * @return The last name of the member
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the e-mail address of the member
     * @return The e-mail address of the member
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the phone number of the member
     * @return The phone number of the member
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets the number of the virtual stamps the member have
     * @return The number of the virtual stamps the member have
     */
    public int getStampCount() {
        return stampCount;
    }

    /**
     * Sets the number of the virtual stamps the member have
     * @param stampCount The number of the virtual stamps the member have
     */
    public void setStampCount(int stampCount) {
        this.stampCount = stampCount;
    }

    /**
     * Gets the (hashed) password of the user
     * @return The hashed password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the hashed password of the user, note that the password must be pre-hashed
     * @param password The hashed password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public int getAutoPay() {
        return autoPay;
    }

    public void setAuthPay(int autoPay) {
        this.autoPay = autoPay;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
