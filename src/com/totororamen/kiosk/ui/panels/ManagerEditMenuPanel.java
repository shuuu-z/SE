package com.totororamen.kiosk.ui.panels;

import com.totororamen.kiosk.data.entities.Item;
import com.totororamen.kiosk.data.entities.ItemOption;
import com.totororamen.kiosk.data.entities.Order;
import com.totororamen.kiosk.data.io.ItemIO;
import com.totororamen.kiosk.data.io.ItemOptionsIO;
import com.totororamen.kiosk.ui.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * The panel allowing the manager to edit the menu.
 * Including changing prices and toggling availability
 */
public class ManagerEditMenuPanel extends BasePanel {

    private JButton fixedPriceButton;
    private JButton addonsButton;
    private JPanel mainPanel;
    private JPanel fixedPricePanel;
    private JPanel addonsPanel;
    private JScrollPane fixedPriceScrollPane;
    private JScrollPane addonsScrollPane;
    private JPanel bottomPanel;

    private JButton saveButton;

    private CardLayout cards;

    private Item ramen;
    private ArrayList<Item> addons;
    private ArrayList<ItemOption> options;

    private ArrayList<AddonItemAvailabilityPanel> addonItemAvailabilityPanels;
    private ArrayList<FixedPriceItemAvailabilityPanel> fixedPriceItemAvailabilityPanels;

    public ManagerEditMenuPanel(MainWindow frame) {
        super(frame, null);
    }

    @Override
    protected void loadData(Object data) {
        addonItemAvailabilityPanels = new ArrayList<>();
        fixedPriceItemAvailabilityPanels = new ArrayList<>();

        ItemIO itemIO = ItemIO.getInstance();
        ArrayList<Item> items = itemIO.getData();
        addons = new ArrayList<>();
        options = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().equals("Ramen")) {
                ramen = item;
            }
            else {
                addons.add(item);
            }
        }

        options = ItemOptionsIO.getInstance().getData();
    }

    @Override
    protected void setupGUI() {
        setLayout(new GridBagLayout());

        fixedPriceButton = new JButton("Fixed Price");
        fixedPriceButton.setFont(new Font("Arial", Font.BOLD, 20));
        fixedPriceButton.setBackground(Color.orange);
        fixedPriceButton.addActionListener(e -> toggleFixedPrice());
        fixedPriceButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        add(fixedPriceButton, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 30));

        addonsButton = new JButton("  Add-ons  ");
        addonsButton.setFont(new Font("Arial", Font.BOLD, 20));
        addonsButton.setBackground(Color.white);
        addonsButton.addActionListener(e -> toggleAddons());
        addonsButton.setBorder(BorderFactory.createEmptyBorder());
        add(addonsButton, new GridBagConstraints(1, 1, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 30));

        mainPanel = new JPanel();
        mainPanel.setLayout(cards = new CardLayout());
        mainPanel.setBackground(Color.white);
        mainPanel.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, Color.orange));
        add(mainPanel, new GridBagConstraints(0, 2, 2, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        add(bottomPanel, new GridBagConstraints(0, 3, 2, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

        saveButton = new JButton("Save");
        saveButton.setBorder(BorderFactory.createEmptyBorder());
        saveButton.setFont(new Font("Arial", Font.BOLD, 20));
        saveButton.setBackground(Color.orange);
        saveButton.addActionListener(e -> doConfirm());
        bottomPanel.add(saveButton, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 30, 20));

        fixedPricePanel = new JPanel();
        fixedPriceScrollPane = new JScrollPane(fixedPricePanel);
        fixedPricePanel.setBackground(Color.white);
        fixedPricePanel.setLayout(new GridBagLayout());
        mainPanel.add(fixedPriceScrollPane, "fixed_price");

        addonsPanel = new JPanel();
        addonsScrollPane = new JScrollPane(addonsPanel);
        addonsPanel.setLayout(new GridBagLayout());
        addonsPanel.setBackground(Color.white);
        mainPanel.add(addonsScrollPane, "addons");

        cards.show(mainPanel, "fixed_price");

        addonItemAvailabilityPanels.clear();
        fixedPriceItemAvailabilityPanels.clear();

        if (ramen != null) {
            AddonItemAvailabilityPanel addonItemAvailabilityPanel = new AddonItemAvailabilityPanel(ramen);
            addonItemAvailabilityPanels.add(addonItemAvailabilityPanel);
            fixedPricePanel.add(addonItemAvailabilityPanel, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
        }

        int gridy = 1;
        for (ItemOption option : options) {
            FixedPriceItemAvailabilityPanel fixedPriceItemAvailabilityPanel = new FixedPriceItemAvailabilityPanel(option);
            fixedPriceItemAvailabilityPanels.add(fixedPriceItemAvailabilityPanel);
            fixedPricePanel.add(fixedPriceItemAvailabilityPanel, new GridBagConstraints(0, gridy++, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
        }

        gridy = 0;
        for (Item item : addons) {
            AddonItemAvailabilityPanel addonItemAvailabilityPanel = new AddonItemAvailabilityPanel(item);
            addonItemAvailabilityPanels.add(addonItemAvailabilityPanel);
            addonsPanel.add(addonItemAvailabilityPanel, new GridBagConstraints(0, gridy++, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
        }
        addonsPanel.add(new JPanel(), new GridBagConstraints(0, gridy, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
    }

    /**
     * The handler of the "Save" button
     */
    private void doConfirm() {
        boolean ok = true;
        // Save changes of the add-ons and the options
        for (AddonItemAvailabilityPanel p : addonItemAvailabilityPanels) {
            ok &= p.saveChanges();
        }

        for (FixedPriceItemAvailabilityPanel p : fixedPriceItemAvailabilityPanels) {
            p.saveChanges();
        }

        // If there is no error, go back to manager menu
        if (ok)
            frame.toManagerMenu();
    }

    /**
     * toggles to the add-on tab
     */
    private void toggleAddons() {
        cards.show(mainPanel, "addons");
        addonsButton.setBackground(Color.orange);
        addonsButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        fixedPriceButton.setBackground(Color.white);
        fixedPriceButton.setBorder(BorderFactory.createEmptyBorder());
    }

    /**
     * Toggles to the options tab
     */
    private void toggleFixedPrice() {
        cards.show(mainPanel, "fixed_price");
        addonsButton.setBackground(Color.white);
        addonsButton.setBorder(BorderFactory.createEmptyBorder());
        fixedPriceButton.setBackground(Color.orange);
        fixedPriceButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
    }
}
