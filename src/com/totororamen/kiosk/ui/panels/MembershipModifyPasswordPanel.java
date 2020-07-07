package com.totororamen.kiosk.ui.panels;

import com.totororamen.kiosk.data.entities.Membership;
import com.totororamen.kiosk.data.io.MembershipIO;
import com.totororamen.kiosk.data.utils.ManagementPassword;
import com.totororamen.kiosk.ui.MainWindow;

import javax.swing.*;
import java.awt.*;

/**
 * This panel allows membership to change the password
 */
public class MembershipModifyPasswordPanel extends BasePanel {
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel repeatLabel;
    private JPasswordField repeatField;
    private JButton confirmButton;

    private Membership membership;

    public MembershipModifyPasswordPanel(MainWindow frame, Membership membership) {
        super(frame, membership);
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
        reset();
    }

    @Override
    protected void loadData(Object data) {
        this.membership = (Membership) data;
    }

    @Override
    protected void setupGUI() {
        setLayout(new GridBagLayout());

        passwordLabel = new JLabel("New Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        add(passwordLabel, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(15, 30, 5, 30), 5, 5));

        passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        passwordField.setFont(new Font("Arial", Font.BOLD, 30));
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        add(passwordField,  new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 30, 5, 30), 10, 10));

        repeatLabel = new JLabel("Confirm Password:");
        repeatLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        add(repeatLabel, new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 30, 5, 30), 5, 5));

        repeatField = new JPasswordField();
        repeatField.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        repeatField.setFont(new Font("Arial", Font.BOLD, 30));
        repeatField.setHorizontalAlignment(JTextField.CENTER);
        add(repeatField,  new GridBagConstraints(0, 3, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 30, 5, 30), 10, 10));


        add(new JPanel(),new GridBagConstraints(0, 8, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 30, 30));

        confirmButton = new JButton("Confirm");
        confirmButton.setBackground(Color.orange);
        confirmButton.addActionListener(e -> doChange());
        confirmButton.setFont(new Font("Arial", Font.BOLD, 20));
        confirmButton.setBorder(BorderFactory.createEmptyBorder());
        this.add(confirmButton, new GridBagConstraints(0, 9, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 70, 30, 70), 20, 20));
    }

    /**
     * The handler of the "Confirm" button
     */
    private void doChange() {
        // Make sure that the password fields are not empty and the password in the two fields equal
        if (passwordField.getText().isEmpty() || !passwordField.getText().equals(repeatField.getText())) {
            JOptionPane.showMessageDialog(null, "The password is empty or the two passwords does not match");
            return;
        }

        // Change the password
        membership.setPassword(ManagementPassword.hashUserPassword(passwordField.getText()));
        MembershipIO.getInstance().saveChanges();

        JOptionPane.showMessageDialog(null, "Your password have successfully reset");
        frame.toMembershipDetails(membership);
    }
}
