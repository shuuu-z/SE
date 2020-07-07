package com.totororamen.kiosk.ui.panels;

import com.totororamen.kiosk.data.entities.Item;
import com.totororamen.kiosk.data.entities.ItemOption;
import com.totororamen.kiosk.data.io.ItemIO;
import com.totororamen.kiosk.data.io.ItemOptionsIO;
import com.totororamen.kiosk.ui.MainWindow;

import javax.swing.*;
import java.awt.*;

/**
 * The panel showing the stats to the manager.
 * Including the popularity of the options and the sold number of the add-ons
 */
public class ManagerStatsPanel extends JPanel {
    private MainWindow frame;

    private JButton fixedPriceButton;
    private JButton addonsButton;
    private JPanel mainPanel;
    private JPanel fixedPricePanel;
    private JPanel addonsPanel;
    private JScrollPane fixedPriceScrollPane; // Scroll panes make panels scrollable
    private JScrollPane addonsScrollPane;

    // Use card layout to switch between panels
    private CardLayout cards;

    public ManagerStatsPanel(MainWindow frame) {
        this.frame = frame;
        setupGUI();
    }

    public void reset() {
        setupGUI();
    }

    private void setupGUI() {
        removeAll();
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


        fixedPricePanel.add(new ItemStatPanel("Ramen"), new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));


        int gridy = 1;
        for (ItemOption option : ItemOptionsIO.getInstance().getData()) {
            fixedPricePanel.add(new OptionStatPanel(option.getName()), new GridBagConstraints(0, gridy++, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
        }

        gridy = 0;
        for (Item item : ItemIO.getInstance().getData()) {
            if (!item.getName().equals("Ramen"))
                addonsPanel.add(new ItemStatPanel(item.getName()), new GridBagConstraints(0, gridy++, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
        }
        addonsPanel.add(new JPanel(), new GridBagConstraints(0, gridy, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
    }

    /**
     * Toggles to display the add-ons stats
     */
    private void toggleAddons() {
        cards.show(mainPanel, "addons");
        addonsButton.setBackground(Color.orange);
        addonsButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        fixedPriceButton.setBackground(Color.white);
        fixedPriceButton.setBorder(BorderFactory.createEmptyBorder());
    }

    /**
     * Toggle to display the options stats.
     */
    private void toggleFixedPrice() {
        cards.show(mainPanel, "fixed_price");
        addonsButton.setBackground(Color.white);
        addonsButton.setBorder(BorderFactory.createEmptyBorder());
        fixedPriceButton.setBackground(Color.orange);
        fixedPriceButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
    }
}
