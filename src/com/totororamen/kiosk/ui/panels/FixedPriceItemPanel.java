package com.totororamen.kiosk.ui.panels;

import com.totororamen.kiosk.data.entities.ItemOption;

import javax.swing.*;
import java.awt.*;

/**
 * The panel used to display the options of the Ramen.
 * It should display inside the {@code PersonalizePanel}
 */
public class FixedPriceItemPanel extends JPanel {
    private PersonalizePanel parent;

    private String name;
    private String[] options;
    private boolean[] availability;

    private JLabel nameLabel;
    private JButton[] optionButtons;

    private int selectedOption = 1;

    /**
     * Constructor of {@code FixedPriceItemPanel}
     * @param option The option display in this panel
     * @param parent The parent panel
     */
    public FixedPriceItemPanel(ItemOption option, PersonalizePanel parent) {
        this.name = option.getName();
        this.options = option.getOptions();
        this.availability = option.getAvailability();
        this.parent = parent;
        setupGUI();
    }

    /**
     * The handler of the option items button
     * @param index The index of the clicked button
     */
    private void buttonClicked(int index) {
        for (int i = 0; i < optionButtons.length; i++) {
            if (index == i) {
                optionButtons[i].setBackground(Color.orange);
                optionButtons[i].setBorder(BorderFactory.createLineBorder(Color.ORANGE));
            }
            else {
                optionButtons[i].setBackground(Color.lightGray);
                optionButtons[i].setBorder(BorderFactory.createEmptyBorder());
            }
        }
        selectedOption = index;
        parent.setOption(name, index); // Sets the current option in the parent panel
    }

    /**
     * Initialize GUI component
     */
    private void setupGUI() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));

        nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 17));
        optionButtons = new JButton[options.length];
        boolean firstAvailable = false;
        for (int i = 0; i < options.length; i++) {
            optionButtons[i] = new JButton(options[i]);
            optionButtons[i].setFont(new Font("Arial", Font.BOLD, 17));
            optionButtons[i].setBackground(Color.lightGray);
            optionButtons[i].setBorder(BorderFactory.createEmptyBorder());
            int finalI = i;
            optionButtons[i].addActionListener(e -> buttonClicked(finalI));
            optionButtons[i].setEnabled(availability[i]);
            if (availability[i] && !firstAvailable) {
                firstAvailable = true;
                optionButtons[i].setBackground(Color.orange);
                optionButtons[i].setBorder(BorderFactory.createLineBorder(Color.ORANGE));
                parent.setOption(name, i);
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
