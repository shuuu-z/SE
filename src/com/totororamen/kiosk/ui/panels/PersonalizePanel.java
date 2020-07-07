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
import java.util.Dictionary;
import java.util.HashMap;
import java.util.UUID;

/**
 * The panel allowing the customer to personalize their ramen.
 * Including choosing options and add-ons
 */
public class PersonalizePanel extends BasePanel {
    private JLabel topLabel;
    private JButton fixedPriceButton;
    private JButton addonsButton;
    private JPanel mainPanel;
    private JPanel fixedPricePanel;
    private JPanel addonsPanel;
    private JScrollPane fixedPriceScrollPane;
    private JScrollPane addonsScrollPane;
    private JPanel bottomPanel;

    private JLabel totalPrizeLabel;
    private JButton selectedButton;

    private CardLayout cards;

    private Item ramen;
    private ArrayList<Item> addons;
    private ArrayList<ItemOption> options;

    private HashMap<String, Integer> selectedOptions;
    private HashMap<String, Integer> itemNumbers;
    private float price;

    public PersonalizePanel(MainWindow frame) {
        super(frame, null);  // We need data for this frame but we don't need data object
    }

    @Override
    protected void loadData(Object data) {
        selectedOptions = new HashMap<>();
        itemNumbers = new HashMap<>();

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

    /**
     * Gets the current price of the order
     * @return The current price of the order
     */
    public float getPrice() {
        return price;
    }

    /**
     * Adds to the current price, used when changing the number of ramen
     * or the add-ons
     * @param add The amount to be added, can be a positive/negative number
     */
    public void addPrice(float add) {
        price += add;
        totalPrizeLabel.setText(String.format("£%.02f", price));
    }

    /**
     * Sets the current number of an item (ramen or add-on item)
     * @param name The name of the item whose number will be changed
     * @param value The number of the item
     */
    public void setNumber(String name, int value) {
        itemNumbers.put(name, value);
    }

    /**
     * Sets the current selected option item of an option
     * @param name The name of the option that is going to be changed
     * @param value The index of the selected option item
     */
    public void setOption(String name, int value) {
        selectedOptions.put(name, value);
    }

    /**
     * Reset the content of the panel
     */
    public void reset() {
        price = 0;
        itemNumbers.clear();
        selectedOptions.clear();
        setupGUI();
    }

    @Override
    protected void setupGUI() {
        removeAll();
        setLayout(new GridBagLayout());

        topLabel = new JLabel("Personalize your ramen!");
        topLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
        add(topLabel, new GridBagConstraints(0, 0, 2, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(15, 20, 15, 20), 10, 10));

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

        totalPrizeLabel = new JLabel("£");
        totalPrizeLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
        totalPrizeLabel.setForeground(Color.orange);
        bottomPanel.add(totalPrizeLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 20, 10,10), 10, 10));

        selectedButton = new JButton("Selected >");
        selectedButton.setBorder(BorderFactory.createEmptyBorder());
        selectedButton.setFont(new Font("Arial", Font.BOLD, 20));
        selectedButton.setBackground(Color.orange);
        selectedButton.addActionListener(e -> doConfirm());
        bottomPanel.add(selectedButton, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
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

        if (ramen != null) {
            fixedPricePanel.add(new AddonItemPanel(ramen, this), new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
        }

        int gridy = 1;
        for (ItemOption option : options) {
            fixedPricePanel.add(new FixedPriceItemPanel(option, this), new GridBagConstraints(0, gridy++, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
        }

        gridy = 0;
        for (Item item : addons) {
            addonsPanel.add(new AddonItemPanel(item, this), new GridBagConstraints(0, gridy++, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
        }
        addonsPanel.add(new JPanel(), new GridBagConstraints(0, gridy, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
    }

    private void doConfirm() {
        String[] optionNames = new String[selectedOptions.size()];
        int[] optionSelected = new int[selectedOptions.size()];
        String[] addonNames = new String[itemNumbers.size() - 1];
        int[] addonNumbers = new int[itemNumbers.size() - 1];
        float[] addonPrices = new float[itemNumbers.size() - 1];
        int ramens = 0;

        int i = 0;
        for (String k : selectedOptions.keySet()) {
            optionNames[i] = k;
            optionSelected[i] = selectedOptions.get(k);
            i++;
        }

        i = 0;
        for (String k : itemNumbers.keySet()) {
            if (k.equals("Ramen")) {
                ramens = itemNumbers.get(k);
            }
            else {
                addonNames[i] = k;
                addonNumbers[i] = itemNumbers.get(k);
                for (Item item : ItemIO.getInstance().getData()) {
                    if (item.getName().equals(k)) {
                        addonPrices[i] = item.getPrice();
                        break;
                    }
                }
                i++;
            }
        }

        Order order = new Order(UUID.randomUUID().toString(), frame.getCurrentMembership() == null ? 0 : frame.getCurrentMembership().getMembershipID(), price,
                ramens, optionNames, optionSelected, addonNames, addonPrices, addonNumbers, false, 0, "", "");
        frame.toDiningOption(order);
    }

    private void toggleAddons() {
        cards.show(mainPanel, "addons");
        addonsButton.setBackground(Color.orange);
        addonsButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        fixedPriceButton.setBackground(Color.white);
        fixedPriceButton.setBorder(BorderFactory.createEmptyBorder());
    }

    private void toggleFixedPrice() {
        cards.show(mainPanel, "fixed_price");
        addonsButton.setBackground(Color.white);
        addonsButton.setBorder(BorderFactory.createEmptyBorder());
        fixedPriceButton.setBackground(Color.orange);
        fixedPriceButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
    }
}
