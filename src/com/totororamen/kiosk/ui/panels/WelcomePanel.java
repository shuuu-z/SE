package com.totororamen.kiosk.ui.panels;

import com.totororamen.kiosk.ui.MainWindow;

import javax.swing.*;
import java.awt.*;

/**
 * The initial panel that shown in the application,
 * including an "ORDER NOW" button and a button to the management system
 */
public class WelcomePanel extends BasePanel {
    private JPanel imagePanel;

    private JLabel welcomeLabel;
    private JButton orderButton;
    private JButton managerButton;

    public WelcomePanel(MainWindow frame) {
        super(frame);
    }

    @Override
    protected void setupGUI() {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.white);
        imagePanel = new ImagePanel("assets/img/ramen.jpg");
        imagePanel.setBackground(Color.yellow);

        this.add(imagePanel, new GridBagConstraints(0,0,1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        welcomeLabel = new JLabel("Welcome to Totoro Ramen!",SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 20));
        this.add(welcomeLabel, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(20, 20, 20, 20), 5, 5));

        orderButton = new JButton("ORDER NOW >>");
        orderButton.setFont(new Font("Arial",  Font.BOLD,30));
        orderButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        orderButton.setBackground(Color.orange);
        orderButton.addActionListener(e -> frame.toAskMembership());
        this.add(orderButton, new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(60, 70, 50, 70), 40, 40));

        managerButton = new JButton("Management menu");
        managerButton.setBorder(BorderFactory.createEmptyBorder());
        managerButton.setBackground(this.getBackground());
        managerButton.setForeground(Color.BLUE);
        managerButton.addActionListener(e -> frame.toManager());
        this.add(managerButton, new GridBagConstraints(0, 3, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(30, 30, 30, 30), 10, 10));
    }
}
