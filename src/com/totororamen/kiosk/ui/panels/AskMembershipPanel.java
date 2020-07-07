package com.totororamen.kiosk.ui.panels;

import com.totororamen.kiosk.ui.MainWindow;

import javax.swing.*;
import java.awt.*;

/**
 * The panel asking the costumers whether he/she
 * have a membership
 */
public class AskMembershipPanel extends BasePanel {
    private JLabel topLabel;

    private JButton yesButton;
    private JButton noButton;
    private JButton noNeedButton;

    private JPanel spacerPanel;

    public AskMembershipPanel(MainWindow frame) {
        super(frame);
    }

    @Override
    protected void setupGUI() {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.white);

        topLabel = new JLabel("Have you got a membership number?");
        topLabel.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 18));
        this.add(topLabel, new GridBagConstraints(0,0,1, 1,0,0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(150, 50, 10, 50), 10, 10));

        yesButton = new JButton("Yes, I have");
        yesButton.setFont(new Font("Arial", Font.BOLD, 25));
        yesButton.setBackground(Color.orange);
        yesButton.addActionListener(e -> frame.toMembershipLogin());
        yesButton.setBorder(BorderFactory.createEmptyBorder());
        this.add(yesButton, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(30, 100, 15, 100), 20, 20));

        noButton = new JButton("No, join now");
        noButton.setFont(new Font("Arial", Font.BOLD, 25));
        noButton.setBackground(Color.orange);
        noButton.addActionListener(e -> frame.toMembershipRegister());
        noButton.setBorder(BorderFactory.createEmptyBorder());
        this.add(noButton, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(15, 100, 15, 100), 20, 20));

        noNeedButton = new JButton("No need");
        noNeedButton.setFont(new Font("Arial", Font.BOLD, 25));
        noNeedButton.setBackground(Color.orange);
        noNeedButton.addActionListener(e -> frame.toOrder());
        noNeedButton.setBorder(BorderFactory.createEmptyBorder());
        this.add(noNeedButton, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(15, 100, 15, 100), 20, 20));

        spacerPanel = new JPanel();
        spacerPanel.setBackground(Color.white);
        this.add(spacerPanel, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
    }
}
