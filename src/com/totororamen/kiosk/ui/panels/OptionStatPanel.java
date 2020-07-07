package com.totororamen.kiosk.ui.panels;

import com.totororamen.kiosk.data.entities.ItemOption;
import com.totororamen.kiosk.data.entities.Order;
import com.totororamen.kiosk.data.io.ItemOptionsIO;
import com.totororamen.kiosk.data.io.OrderIO;

import javax.swing.*;
import java.awt.*;

/**
 * The panel used to display the popularity of each of the option items
 * of one option of the ramen.
 * This will be used in the management system
 */
public class OptionStatPanel extends JPanel {
    private String name;
    private String[] optionNames;
    private int[] optionOrders;
    private int totalOrders;

    /**
     * Constructor of {@code OptionStatPanel}
     * @param name The name of the option
     */
    public OptionStatPanel(String name) {
        this.name = name;
        ItemOption o = ItemOptionsIO.getInstance().getOptionByName(name);
        if (o != null) {
            optionNames = o.getOptions();
            optionOrders = new int[o.getOptions().length];

            for (Order order : OrderIO.getInstance().getData()) {
                for (int i = 0; i < order.getOptionNames().length; i++) {
                    if (name.equals(order.getOptionNames()[i])) {
                        optionOrders[order.getOptionsSelects()[i]]++;
                    }
                }
            }
        }

        totalOrders = OrderIO.getInstance().getData().size();
        setupGUI();
    }

    /**
     * initialize GUI components
     */
    private void setupGUI() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 17));

        add(nameLabel, new GridBagConstraints(0, 0, 3, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
                new Insets(10, 20, 10, 30), 20, 15));

        for (int i = 0; i < optionNames.length; i++) {
            JLabel optionLabel = new JLabel(optionNames[i]);
            JProgressBar percent = new JProgressBar();
            JLabel ordersLabel = new JLabel(optionOrders[i] + " orders");

            ordersLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
            optionLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
            percent.setMinimum(0);
            percent.setMaximum(totalOrders);
            percent.setValue(optionOrders[i]);
            percent.setBorderPainted(false);
            percent.setBorder(BorderFactory.createEmptyBorder());
            percent.setBackground(Color.lightGray);
            percent.setForeground(Color.orange);

            add(optionLabel, new GridBagConstraints(0, i + 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
                    new Insets(10, 60, 10, 10), 10, 10));
            add(percent, new GridBagConstraints(1, i + 1, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
                    new Insets(10, 10, 10, 10), 10, 10));
            add(ordersLabel, new GridBagConstraints(2, i + 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
                    new Insets(10, 10, 10, 10), 10, 10));
        }
    }
}
