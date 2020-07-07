package com.totororamen.kiosk.ui.panels;

import com.totororamen.kiosk.data.entities.Item;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * This is the panel showing one item in the "personalize" page.
 * The item can be Ramen or any add-on items
 */
public class AddonItemPanel extends JPanel {
    private PersonalizePanel parent;

    private String name;
    private float price;
    private int numberMax;
    private int numberMin;
    private int num;
    private boolean available;

    private JLabel nameLabel;
    private JButton addButton;
    private JButton subButton;
    private JLabel numLabel;
    private JLabel priceLabel;

    /**
     * Constructor of {@code AddonItemPanel}
     * @param item The item shown in the panel
     * @param parent The parent panel, should be {@code PersonalizePanel}
     */
    public AddonItemPanel(Item item, PersonalizePanel parent) {
        this.name = item.getName();
        this.price = item.getPrice();
        this.numberMax = item.getMaxNumber();
        this.numberMin = item.getMinNumber();
        this.num = numberMin; // Initialize to the minimum number
        this.parent = parent;
        this.available = item.isAvailable();
        parent.addPrice(num * price);
        parent.setNumber(name, num);
        setupGUI();
    }

    /**
     * The handler of the "add" button
     */
    private void addNum() {
        // Make sure the number don't exceed the boundary
        if (num < numberMax){
            num++;
            numLabel.setText(Integer.toString(num));
            parent.addPrice(price);
            parent.setNumber(name, num);
        }
    }

    /**
     * The handler of the "sub" button
     */
    private void subNum() {
        // Make sure the number don't exceed the boundary
        if (num > numberMin) {
            num--;
            numLabel.setText(Integer.toString(num));
            parent.addPrice(-price);
            parent.setNumber(name, num);
        }
    }

    /**
     * Initialize GUI components
     */
    private void setupGUI() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));

        nameLabel = new JLabel(name);
        addButton = new JButton("+");
        subButton = new JButton("- ");
        numLabel = new JLabel(Integer.toString(numberMin));
        priceLabel = new JLabel("£" + new DecimalFormat("#0.00").format(price));

        nameLabel.setFont(new Font("Arial", Font.BOLD, 17));
        addButton.setFont(new Font("Arial", Font.BOLD, 20));
        subButton.setFont(new Font("Arial", Font.BOLD, 20));
        numLabel.setFont(new Font("Arial", Font.BOLD, 17));
        priceLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 17));
        priceLabel.setForeground(Color.orange);
        addButton.setBackground(Color.orange);
        addButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        addButton.addActionListener(e -> addNum());
        subButton.setBackground(Color.orange);
        subButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        subButton.addActionListener(e -> subNum());

        // If this item is not available, disable the buttons and make the name gray
        if (!available) {
            nameLabel.setForeground(Color.gray);
            addButton.setEnabled(false);
            subButton.setEnabled(false);
        }

        add(nameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 20, 5, 10),  10, 5));
        add(priceLabel, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 20, 10, 10), 10, 5));
        add(subButton, new GridBagConstraints(1, 0, 1, 2, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(25, 10, 25, 10), 25, 5));
        add(numLabel, new GridBagConstraints(2, 0, 1, 2, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 10, 5, 10), 10, 10));
        add(addButton, new GridBagConstraints(3, 0, 1, 2, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(25, 10, 25, 10), 25, 6));
    }
}
