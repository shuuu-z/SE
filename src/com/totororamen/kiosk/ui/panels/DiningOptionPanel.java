package com.totororamen.kiosk.ui.panels;

import com.totororamen.kiosk.data.entities.Order;
import com.totororamen.kiosk.ui.MainWindow;

import javax.swing.*;
import java.awt.*;

/**
 * The panel asking the customers whether he/she wants
 * to eat the meal in the restaurant or take away.
 */
public class DiningOptionPanel extends BasePanel {
    private Order order;

    private JLabel topLabel;

    private JButton eatInButton;
    private JButton takeAwayButton;
    private JButton noNeedButton;

    private JPanel spacerPanel;

    /**
     * Constructor
     * @param frame The parent frame, should be {@code MainWindow}
     * @param order The current order
     */
    public DiningOptionPanel(MainWindow frame, Order order) {
        super(frame);
        this.order = order;
    }

    /**
     * Sets the current order
     * @param order The order
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    protected void setupGUI() {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.white);

        topLabel = new JLabel("Choose your dining option");
        topLabel.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 26));
        this.add(topLabel, new GridBagConstraints(0,0,1, 1,0,0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(150, 50, 10, 50), 10, 10));

        eatInButton = new JButton("Eat In");
        eatInButton.setFont(new Font("Arial", Font.BOLD, 25));
        eatInButton.setBackground(Color.orange);
        eatInButton.addActionListener(e -> confirm(false));
        eatInButton.setBorder(BorderFactory.createLineBorder(Color.orange));
        this.add(eatInButton, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(30, 100, 15, 100), 20, 20));

        takeAwayButton = new JButton("Take Away");
        takeAwayButton.setFont(new Font("Arial", Font.BOLD, 25));
        takeAwayButton.setBackground(Color.orange);
        takeAwayButton.addActionListener(e -> confirm(true));
        takeAwayButton.setBorder(BorderFactory.createLineBorder(Color.orange));
        this.add(takeAwayButton, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(15, 100, 15, 100), 20, 20));

        spacerPanel = new JPanel();
        spacerPanel.setBackground(Color.white);
        this.add(spacerPanel, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
    }

    /**
     * Handler of the buttons
     * @param takeaway Whether to take away
     */
    private void confirm(boolean takeaway){
        order.setTakeAway(takeaway);
        frame.toOrderNote(order);
    }
}
