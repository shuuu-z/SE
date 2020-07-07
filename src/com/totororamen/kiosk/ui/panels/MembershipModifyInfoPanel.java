package com.totororamen.kiosk.ui.panels;

import com.totororamen.kiosk.data.entities.Membership;
import com.totororamen.kiosk.data.io.MembershipIO;
import com.totororamen.kiosk.data.utils.ManagementPassword;
import com.totororamen.kiosk.ui.MainWindow;

import javax.swing.*;
import java.awt.*;

public class MembershipModifyInfoPanel extends BasePanel {
    private JLabel firstNameLabel;
    private JTextField firstNameField;
    private JLabel lastNameLabel;
    private JTextField lastNameField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JButton confirmButton;

    private Membership membership;

    public MembershipModifyInfoPanel(MainWindow frame, Membership membership) {
        super(frame, membership);
    }

    @Override
    protected void loadData(Object data) {
        this.membership = (Membership) data;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
        reset();
    }

    @Override
    protected void setupGUI() {
        setLayout(new GridBagLayout());

        firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        add(firstNameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(15, 30, 5, 30), 5, 5));

        firstNameField = new JTextField(membership.getFirstName());
        firstNameField.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        firstNameField.setFont(new Font("Arial", Font.BOLD, 30));
        firstNameField.setHorizontalAlignment(JTextField.CENTER);
        add(firstNameField, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 30, 5, 30), 10, 10));

        lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        add(lastNameLabel, new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 30, 5, 30), 5, 5));

        lastNameField = new JTextField(membership.getLastName());
        lastNameField.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        lastNameField.setFont(new Font("Arial", Font.BOLD, 30));
        lastNameField.setHorizontalAlignment(JTextField.CENTER);
        add(lastNameField, new GridBagConstraints(0, 3, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 30, 5, 30), 10, 10));

        emailLabel = new JLabel("E-mail:");
        emailLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        add(emailLabel, new GridBagConstraints(0, 4, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 30, 5, 30), 5, 5));

        emailField = new JTextField(membership.getEmail());
        emailField.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        emailField.setFont(new Font("Arial", Font.BOLD, 30));
        emailField.setHorizontalAlignment(JTextField.CENTER);
        add(emailField, new GridBagConstraints(0, 5, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 30, 5, 30), 10, 10));

        phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        add(phoneLabel, new GridBagConstraints(0, 6, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 30, 5, 30), 5, 5));

        phoneTextField = new JTextField(membership.getPhone());
        phoneTextField.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        phoneTextField.setFont(new Font("Arial", Font.BOLD, 30));
        phoneTextField.setHorizontalAlignment(JTextField.CENTER);
        add(phoneTextField, new GridBagConstraints(0, 7, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
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

        membership.setFirstName(firstNameField.getText());
        membership.setLastName(lastNameField.getText());
        membership.setEmail(emailField.getText());
        membership.setPhone(phoneTextField.getText());
        membershipIO.saveChanges();

        // Go to the membership details
        frame.toMembershipDetails(membership);
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
        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() ||
                (emailField.getText().isEmpty() && phoneTextField.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Information cannot be empty!");
            return false;
        } else if (!emailField.getText().matches(emailTest) && !emailField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter the effective email!");
            return false;
        } else if (!phoneTextField.getText().matches(phoneTest) && !phoneTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter the effective phone number!");
            return false;
        }

        return true;
    }
}
