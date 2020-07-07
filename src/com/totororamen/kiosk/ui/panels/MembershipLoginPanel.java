package com.totororamen.kiosk.ui.panels;

import com.totororamen.kiosk.data.entities.Membership;
import com.totororamen.kiosk.data.io.MembershipIO;
import com.totororamen.kiosk.data.utils.ManagementPassword;
import com.totororamen.kiosk.ui.MainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * The panel asking the membership to enter his/her number
 */
public class MembershipLoginPanel extends BasePanel {
    private JLabel membershipNumberLabel;
    private JLabel passwordLabel;
    private JLabel tipLabel;
    private JTextField membershipNumberField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel resetPasswordLabel;

    public MembershipLoginPanel(MainWindow frame) {
        super(frame);
    }

    @Override
    protected void setupGUI() {
        this.setLayout(new GridBagLayout());
        membershipNumberLabel = new JLabel("Membership ID:");
        membershipNumberLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        add(membershipNumberLabel, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(15, 30, 5, 30), 5, 5));

        membershipNumberField = new JTextField();
        membershipNumberField.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        membershipNumberField.setFont(new Font("Arial", Font.BOLD, 30));
        membershipNumberField.setHorizontalAlignment(JTextField.CENTER);
        add(membershipNumberField, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 30, 5, 30), 10, 10));

        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        add(passwordLabel, new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(15, 30, 5, 30), 5, 5));

        passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        passwordField.setFont(new Font("Arial", Font.BOLD, 30));
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        add(passwordField, new GridBagConstraints(0, 3, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 30, 5, 30), 10, 10));

        resetPasswordLabel = new JLabel("Forget password?");
        resetPasswordLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        resetPasswordLabel.setForeground(Color.BLUE);
        resetPasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.toMembershipResetPassword();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                resetPasswordLabel.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                resetPasswordLabel.setForeground(Color.BLUE);
            }
        });
        add(resetPasswordLabel, new GridBagConstraints(0, 4, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(15, 30, 5, 30), 5, 5));

        this.add(new JPanel(), new GridBagConstraints(0, 5, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 30, 30));

        loginButton = new JButton("LOGIN");
        loginButton.setBackground(Color.orange);
        loginButton.setFont(new Font("Arial", Font.BOLD, 30));
        loginButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        loginButton.addActionListener(e -> doLogin());
        this.add(loginButton, new GridBagConstraints(0, 6, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(30, 70, 30, 70), 15, 15));

        tipLabel = new JLabel();
        tipLabel.setText("<html>Tips: if you just register, you will receive an SMS with 8-digit<br/> membership number</html>");

        this.add(tipLabel, new GridBagConstraints(0, 7, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(30, 30, 30, 30), 30, 30));
    }

    /**
     * Handler of the "Login" button
     */
    private void doLogin() {
        try {
            // Check whether the membership ID is a 8-digit number
            if (!membershipNumberField.getText().matches("^[0-9]{8}$")) {
                JOptionPane.showMessageDialog(null, "Please input your 8-digit membership ID");
                return;
            }

            // Attempt to get the membership info with the given ID
            Membership m = MembershipIO.getInstance().getMembershipById(Integer.parseInt(membershipNumberField.getText()));
            if (m != null && m.getPassword().equals(ManagementPassword.hashUserPassword(passwordField.getText()))) {
                // If found and the password matches, go to the membership details panel
                frame.toMembershipDetails(m);
            }
            else {
                JOptionPane.showMessageDialog(null, "The Membership ID or the password is incorrect");
            }
        } catch (Exception e) {
        }

        // Clear the field
        membershipNumberField.setText("");
        passwordField.setText("");
    }

    /**
     * Override the {@code reset()} method.
     * We only need to clear the field instead of reloading the whole layout
     */
    @Override
    public void reset() {
        membershipNumberField.setText("");
    }
}
