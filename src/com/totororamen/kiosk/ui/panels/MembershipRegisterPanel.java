package com.totororamen.kiosk.ui.panels;

import com.totororamen.kiosk.Main;
import com.totororamen.kiosk.data.entities.Membership;
import com.totororamen.kiosk.data.io.MembershipIO;
import com.totororamen.kiosk.data.utils.ManagementPassword;
import com.totororamen.kiosk.ui.MainWindow;

import javax.swing.*;
import java.awt.*;

/**
 * The panel requiring the new member to enter the personal info
 * to apply for a membership
 */
public class MembershipRegisterPanel extends BasePanel {
    private JLabel firstNameLabel;
    private JTextField firstNameField;
    private JLabel lastNameLabel;
    private JTextField lastNameField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel repeatLabel;
    private JPasswordField repeatField;
    private JButton confirmButton;

    public MembershipRegisterPanel(MainWindow frame) {
        super(frame);
    }

    @Override
    protected void setupGUI() {
        setLayout(new GridBagLayout());

        firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        add(firstNameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(15, 30, 5, 30), 5, 5));

        firstNameField = new JTextField();
        firstNameField.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        firstNameField.setFont(new Font("Arial", Font.BOLD, 30));
        firstNameField.setHorizontalAlignment(JTextField.CENTER);
        add(firstNameField, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 30, 5, 30), 10, 10));

        lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        add(lastNameLabel, new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 30, 5, 30), 5, 5));

        lastNameField = new JTextField();
        lastNameField.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        lastNameField.setFont(new Font("Arial", Font.BOLD, 30));
        lastNameField.setHorizontalAlignment(JTextField.CENTER);
        add(lastNameField, new GridBagConstraints(0, 3, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 30, 5, 30), 10, 10));

        emailLabel = new JLabel("E-mail:");
        emailLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        add(emailLabel, new GridBagConstraints(0, 4, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 30, 5, 30), 5, 5));

        emailField = new JTextField();
        emailField.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        emailField.setFont(new Font("Arial", Font.BOLD, 30));
        emailField.setHorizontalAlignment(JTextField.CENTER);
        add(emailField, new GridBagConstraints(0, 5, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 30, 5, 30), 10, 10));

        phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        add(phoneLabel, new GridBagConstraints(0, 6, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 30, 5, 30), 5, 5));

        phoneTextField = new JTextField();
        phoneTextField.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        phoneTextField.setFont(new Font("Arial", Font.BOLD, 30));
        phoneTextField.setHorizontalAlignment(JTextField.CENTER);
        add(phoneTextField, new GridBagConstraints(0, 7, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 30, 5, 30), 10, 10));

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        add(passwordLabel, new GridBagConstraints(0, 8, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 30, 5, 30), 5, 5));

        passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        passwordField.setFont(new Font("Arial", Font.BOLD, 30));
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        add(passwordField, new GridBagConstraints(0, 9, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 30, 5, 30), 10, 10));

        repeatLabel = new JLabel("Password: ");
        repeatLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        add(repeatLabel, new GridBagConstraints(0, 10, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 30, 5, 30), 5, 5));

        repeatField = new JPasswordField();
        repeatField.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        repeatField.setFont(new Font("Arial", Font.BOLD, 30));
        repeatField.setHorizontalAlignment(JTextField.CENTER);
        add(repeatField, new GridBagConstraints(0, 11, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 30, 5, 30), 10, 10));

        add(new JPanel(), new GridBagConstraints(0, 12, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 30, 30));

        confirmButton = new JButton("Confirm and enter");
        confirmButton.setBackground(Color.orange);
        confirmButton.addActionListener(e -> doRegister());
        confirmButton.setFont(new Font("Arial", Font.BOLD, 20));
        confirmButton.setBorder(BorderFactory.createEmptyBorder());
        this.add(confirmButton, new GridBagConstraints(0, 13, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 70, 30, 70), 20, 20));
    }

    /**
     * The handler of the "Confirm" button
     */
    private void doRegister() {
        if (!verify()) {
            return;
        }

        MembershipIO membershipIO = MembershipIO.getInstance();
        int maxNumber = 0;
        for (Membership m : membershipIO.getData()) {
            if (m.getMembershipID() > maxNumber) {
                maxNumber = m.getMembershipID(); // Gets the maximum number of the existing memberships
            }
        }
        // Create the membership info with the input information, the number is the maximum number pluses one
        Membership membership = new Membership(maxNumber + 1,
                firstNameField.getText(),
                lastNameField.getText(),
                emailField.getText(),
                phoneTextField.getText(),
                0,
                ManagementPassword.hashUserPassword(passwordField.getText()),
                0);
        membershipIO.getData().add(membership);
        membershipIO.saveChanges();

        // Go to the membership details
        frame.toMembershipDetails(membership);
        reset();
    }

    /**
     * Verifies whether the input data are valid. Popups will be shown if there is
     * Invalid fields
     *
     * @return True if the fields are valid
     */
    private boolean verify() {
        String emailTest = "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        String phoneTest = "^[1-9][0-9]{10}$";
        String passwordTest = "^[0-9a-zA-Z]{8,12}$";
        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() ||
                (emailField.getText().isEmpty() && phoneTextField.getText().isEmpty()) || passwordField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Information cannot be empty!");
            return false;
        } else if (!emailField.getText().matches(emailTest) && !emailField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter the effective email!");
            return false;
        } else if (!phoneTextField.getText().matches(phoneTest) && !phoneTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter the effective phone number!");
            return false;
        } else if (!passwordField.getText().matches(passwordTest)) {
            JOptionPane.showMessageDialog(null, "Please enter the effective password!");
            return false;
        }
        else if (!passwordField.getText().equals(repeatField.getText())) {
            JOptionPane.showMessageDialog(null, "The two passwords does not match!");
            return false;
        }
        return true;
    }

    /**
     * Override the {@code reset} function.
     * This will only clear the fields instead of reloading the whole layout
     */
    @Override
    public void reset() {
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        phoneTextField.setText("");
    }
}

