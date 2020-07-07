package com.totororamen.kiosk.ui.panels;

import com.totororamen.kiosk.data.utils.ManagementPassword;
import com.totororamen.kiosk.ui.MainWindow;

import javax.swing.*;
import java.awt.*;

/**
 * The panel asking the manager to input the password
 */
public class ManagerPasswordPanel extends BasePanel {
    private JButton confirmButton;
    private JLabel infoLabel;
    private JPasswordField passwordField;

    public ManagerPasswordPanel(MainWindow frame) {
        super(frame);
    }

    @Override
    protected void setupGUI() {
        this.setLayout(new GridBagLayout());
        infoLabel = new JLabel("Management Password: ");
        infoLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        this.add(infoLabel, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(30, 30, 30, 30), 30, 30));

        passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        passwordField.setFont(new Font("Arial", Font.BOLD, 30));
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        this.add(passwordField, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(30, 30, 30, 30), 10, 10));

        this.add(new JPanel(), new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 30, 30));

        confirmButton = new JButton("Confirm");
        confirmButton.setBackground(Color.orange);
        confirmButton.addActionListener(e -> verify());
        confirmButton.setFont(new Font("Arial", Font.BOLD, 30));
        confirmButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        this.add(confirmButton, new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(30, 70, 30, 70), 15, 15));
    }

    /**
     * The handler of the "OK" button
     */
    private void verify() {
        // Verify the password
        if (ManagementPassword.verifyPassword(passwordField.getText())) {
            // If passed, to the manager menu
            frame.toManagerMenu();
        }
        else {
            JOptionPane.showMessageDialog(null, "Incorrect password");
        }
        // Clear the password field
        passwordField.setText("");
    }
}
