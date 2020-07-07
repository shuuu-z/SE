package com.totororamen.kiosk.ui.panels;

import com.totororamen.kiosk.ui.MainWindow;

import javax.swing.*;
import java.awt.*;

/**
 * The panel showing the management menu.
 * Including three options
 */
public class ManagerMenuPanel extends BasePanel {

    private JLabel topLabel;

    private JButton modifyMenuButton;
    private JButton viewStatsButton;
    private JButton modifyPasswordButton;

    private JPanel spacerPanel;

    public ManagerMenuPanel(MainWindow frame) {
        super(frame);
    }

    @Override
    protected void setupGUI() {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.white);

        topLabel = new JLabel("Management Options");
        topLabel.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 25));
        this.add(topLabel, new GridBagConstraints(0,0,1, 1,0,0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(150, 50, 10, 50), 10, 10));

        modifyMenuButton = new JButton("Modify Menu");
        modifyMenuButton.setFont(new Font("Arial", Font.BOLD, 25));
        modifyMenuButton.setBackground(Color.orange);
        modifyMenuButton.addActionListener(e -> frame.toManagerEditMenu());
        modifyMenuButton.setBorder(BorderFactory.createEmptyBorder());
        this.add(modifyMenuButton, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(30, 100, 15, 100), 20, 20));

        viewStatsButton = new JButton("View Stats");
        viewStatsButton.setFont(new Font("Arial", Font.BOLD, 25));
        viewStatsButton.setBackground(Color.orange);
        viewStatsButton.addActionListener(e -> frame.toManagerStats());
        viewStatsButton.setBorder(BorderFactory.createEmptyBorder());
        this.add(viewStatsButton, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(15, 100, 15, 100), 20, 20));

        modifyPasswordButton = new JButton("Change Password");
        modifyPasswordButton.setFont(new Font("Arial", Font.BOLD, 25));
        modifyPasswordButton.setBackground(Color.orange);
        modifyPasswordButton.addActionListener(e -> frame.toChangeManagerPassword());
        modifyPasswordButton.setBorder(BorderFactory.createEmptyBorder());
        this.add(modifyPasswordButton, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(15, 100, 15, 100), 20, 20));

        spacerPanel = new JPanel();
        spacerPanel.setBackground(Color.white);
        this.add(spacerPanel, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
    }
}
