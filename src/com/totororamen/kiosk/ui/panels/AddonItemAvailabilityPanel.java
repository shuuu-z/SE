package com.totororamen.kiosk.ui.panels;

import com.totororamen.kiosk.data.entities.Item;
import com.totororamen.kiosk.data.io.ItemIO;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * The panel used to display and modify the availability of an item
 * Used in the management functions
 */
public class AddonItemAvailabilityPanel extends JPanel {
    private Item item;
    private boolean available;

    private JLabel nameLabel;
    private JButton availableButton;
    private JTextField priceField;
    private JLabel priceLabel;

    /**
     * Constructor of {@code AddonItemAvailabilityPanel}
     * @param item The item to be operated
     */
    public AddonItemAvailabilityPanel(Item item) {
        this.item = item;
        this.available = item.isAvailable();
        setupGUI();
    }

    /**
     * Handler of the availability toggle
     */
    private void toggleAvailable() {
        if (this.available) {
            // Sets the button gray with "unavailable" text
            availableButton.setBackground(Color.lightGray);
            availableButton.setBorder(BorderFactory.createLineBorder(Color.lightGray));
            availableButton.setText("Unavailable");
            this.available = false;
        }
        else {
            // Sets the button orange with "available" text
            availableButton.setBackground(Color.orange);
            availableButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
            availableButton.setText("  Available   "); // Add blank spaces to make sure the layout won't change
            this.available = true;
        }
    }

    /**
     * Saves the changes of the current item
     * @return Whether the operation is successful
     */
    public boolean saveChanges() {
        try {
            // Attempt to set price with the text in the text field
            item.setPrice(Float.parseFloat(priceField.getText()));
        }
        catch (Exception e) {
            // Some error with the input (probably wrong format), indicate it with red border
            priceField.setBorder(BorderFactory.createLineBorder(Color.red, 5));
            return false;
        }

        // If successful, set the availability and save the changes
        item.setAvailable(available);
        ItemIO.getInstance().saveChanges();
        return true;
    }

    /**
     * Sets up the GUI components
     */
    private void setupGUI() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));

        nameLabel = new JLabel(item.getName());
        availableButton = new JButton("  Available   ");
        priceLabel = new JLabel("£");
        priceField = new JTextField(Float.toString(item.getPrice()));

        nameLabel.setFont(new Font("Arial", Font.BOLD, 17));
        availableButton.setFont(new Font("Arial", Font.BOLD, 20));
        priceField.setFont(new Font("Arial", Font.BOLD, 17));
        priceField.setBorder(BorderFactory.createLineBorder(Color.orange, 5));
        priceLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        priceLabel.setForeground(Color.orange);
        availableButton.setBackground(Color.orange);
        availableButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        availableButton.addActionListener(e -> toggleAvailable());

        if (!item.isAvailable()) {
            availableButton.setBackground(Color.lightGray);
            availableButton.setBorder(BorderFactory.createLineBorder(Color.lightGray));
            availableButton.setText("Unavailable");
        }

        // Ramens should always be available, so the "available" button should be hidden
        if (item.getName().equals("Ramen")) {
            availableButton.setVisible(false);
        }

        add(nameLabel, new GridBagConstraints(0, 0, 3, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 20, 5, 10),  10, 10));
        add(priceLabel, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 30, 15, 1), 10, 20));
        add(priceField, new GridBagConstraints(1, 1, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 10, 25, 150), 25, 5));
        add(availableButton, new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 10, 25, 10), 25, 5));
    }
}
