package com.totororamen.kiosk.ui.panels;

import com.totororamen.kiosk.data.entities.ItemOption;
import com.totororamen.kiosk.data.io.ItemOptionsIO;

import javax.swing.*;
import java.awt.*;

/**
 * The panel used to manage the availability of the fixed-price item options.
 * Used in the management system
 */
public class FixedPriceItemAvailabilityPanel extends JPanel {

    private String name;
    private String[] options;
    private boolean[] availability;
    private ItemOption itemOption;

    private JLabel nameLabel;
    private JButton[] optionButtons;

    /**
     * Constructor of {@code FixedPriceItemAvailabilityPanel}
     * @param option The option data shown in the panel
     */
    public FixedPriceItemAvailabilityPanel(ItemOption option) {
        this.name = option.getName();
        this.options = option.getOptions();
        this.availability = option.getAvailability();
        this.itemOption = option;
        setupGUI();
    }

    /**
     * Handler of the buttons, toggle the availability of one option item
     * @param index The index of the pressed button
     */
    private void buttonClicked(int index) {
        if (!availability[index]) {
            // Set the button orange indicates that this item is available
            optionButtons[index].setBackground(Color.orange);
            optionButtons[index].setBorder(BorderFactory.createLineBorder(Color.ORANGE));
            availability[index] = true;
        } else {
            // Set the button gray indicates that this item is unavailable
            optionButtons[index].setBackground(Color.lightGray);
            optionButtons[index].setBorder(BorderFactory.createEmptyBorder());
            availability[index] = false;
        }

    }

    /**
     * Save the change of the availabilities.
     * Should be called by the parent panel
     */
    public void saveChanges() {
        itemOption.setAvailability(availability);
        ItemOptionsIO.getInstance().saveChanges();
    }

    /**
     * Initialize GUI components
     */
    private void setupGUI() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));

        nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 17));
        optionButtons = new JButton[options.length];
        for (int i = 0; i < options.length; i++) {
            optionButtons[i] = new JButton(options[i]);
            optionButtons[i].setFont(new Font("Arial", Font.BOLD, 17));
            int finalI = i;  // Java requires variables used in lambda expressions to be final
            optionButtons[i].addActionListener(e -> buttonClicked(finalI));
            if (availability[i]) {
                optionButtons[i].setBackground(Color.orange);
                optionButtons[i].setBorder(BorderFactory.createLineBorder(Color.ORANGE));
            } else {
                optionButtons[i].setBackground(Color.lightGray);
                optionButtons[i].setBorder(BorderFactory.createEmptyBorder());
            }
        }


        if (options.length <= 2) {
            add(nameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
                    new Insets(10, 20, 10, 30), 20, 15));
            for(int i = 0; i < optionButtons.length; i ++) {
                add(optionButtons[i], new GridBagConstraints(i + 1, 0, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
                        new Insets(10, 0, 10, 10), 70, 15));
            }
        }
        else {
            add(nameLabel, new GridBagConstraints(0, 0, optionButtons.length, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                    new Insets(10, 20, 10, 20), 20, 15));

            for (int i = 0; i < optionButtons.length; i++) {
                add(optionButtons[i], new GridBagConstraints(i, 1, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                        new Insets(5, 10, 10, 10), 20, 15));
            }
        }
    }
}
