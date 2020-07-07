package com.totororamen.kiosk.ui.panels;

import com.totororamen.kiosk.data.entities.Membership;
import com.totororamen.kiosk.data.io.MembershipIO;
import com.totororamen.kiosk.ui.MainWindow;

import javax.swing.*;
import java.awt.*;

public class MembershipResetPasswordPanel extends BasePanel {
    private JLabel membershipNumberLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JLabel tipLabel;
    private JTextField membershipNumberField;
    private JTextField emailField;
    private JTextField phoneField;
    private JButton resetButton;

    public MembershipResetPasswordPanel(MainWindow frame) {
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

        emailLabel = new JLabel("e-mail:");
        emailLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        add(emailLabel, new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(15, 30, 5, 30), 5, 5));

        emailField = new JTextField();
        emailField.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        emailField.setFont(new Font("Arial", Font.BOLD, 30));
        emailField.setHorizontalAlignment(JTextField.CENTER);
        add(emailField, new GridBagConstraints(0, 3, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 30, 5, 30), 10, 10));

        phoneLabel = new JLabel("phone:");
        phoneLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        add(phoneLabel, new GridBagConstraints(0, 4, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(15, 30, 5, 30), 5, 5));

        phoneField = new JTextField();
        phoneField.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        phoneField.setFont(new Font("Arial", Font.BOLD, 30));
        phoneField.setHorizontalAlignment(JTextField.CENTER);
        add(phoneField, new GridBagConstraints(0, 5, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 30, 5, 30), 10, 10));

        this.add(new JPanel(), new GridBagConstraints(0, 6, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 30, 30));

        resetButton = new JButton("Reset password");
        resetButton.setBackground(Color.orange);
        resetButton.setFont(new Font("Arial", Font.BOLD, 30));
        resetButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        resetButton.addActionListener(e -> doReset());
        this.add(resetButton, new GridBagConstraints(0, 7, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(30, 70, 30, 70), 15, 15));

        tipLabel = new JLabel();
        tipLabel.setText("<html>Tips: You can reset your password with your email address <br/> and your phone number.</html>");

        this.add(tipLabel, new GridBagConstraints(0, 8, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(30, 30, 30, 30), 30, 30));
    }

    /**
     * Handler of button "Reset password"
     */
    void doReset() {
        if (!membershipNumberField.getText().matches("^[0-9]{8}$")) {
            JOptionPane.showMessageDialog(null, "The membership ID should be a 8-digit number!");
        }

        try {
            Membership m = MembershipIO.getInstance().getMembershipById(Integer.parseInt(membershipNumberField.getText()));
            if (m != null) {
                if (!m.getEmail().equals(emailField.getText())) {
                    JOptionPane.showMessageDialog(null, "The e-mail address is not correct");
                    return;
                }

                if (!m.getPhone().equals(phoneField.getText())) {
                    JOptionPane.showMessageDialog(null, "The phone number is not correct");
                    return;
                }

                frame.toMembershipModifyPassword(m, true);
            }
            else {
                JOptionPane.showMessageDialog(null, "The Membership ID does not exist");
                return;
            }
        }
        catch (Exception e) {
        }

        reset();
    }
}
