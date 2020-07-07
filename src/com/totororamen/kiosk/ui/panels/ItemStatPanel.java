package com.totororamen.kiosk.ui.panels;

import com.totororamen.kiosk.data.entities.Item;
import com.totororamen.kiosk.data.entities.Order;
import com.totororamen.kiosk.data.io.ItemIO;
import com.totororamen.kiosk.data.io.OrderIO;

import javax.swing.*;
import java.awt.*;

/**
 * The panel used to display the stats of one add-on item.
 * Used in the management system
 */
public class ItemStatPanel extends JPanel {
    private String name;
    private int sold;

    /**
     * Constructor of {@code ItemStatPanel}
     * @param name The name of the item
     */
    public ItemStatPanel(String name) {
        this.name = name;
        // Find the item with the name
        Item i = ItemIO.getInstance().getItemByName(name);
        if (i != null) {
            // If the item is the Ramen, its number will be the ramen number
            if (name.equals("Ramen")) {
                for (Order o : OrderIO.getInstance().getData()) {
                    sold += o.getRamenNumber();
                }
            }
            else {
                // Add-on items
                for (Order o : OrderIO.getInstance().getData()) {
                    for (int j = 0; j < o.getAddonNames().length; j++) {
                        if (o.getAddonNames()[j].equals(name)) {
                            sold += o.getAddonsNumbers()[j];
                        }
                    }
                }
            }
        }
        setupGUI();
    }

    /**
     * Initialize GUI components
     */
    private void setupGUI() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 17));
        JLabel soldLabel = new JLabel(sold + " orders");
        soldLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));

        add(nameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
                new Insets(10, 20, 10, 10), 10, 10));
        add(soldLabel,  new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
                new Insets(10, 20, 10, 10), 10, 10));
    }
}
