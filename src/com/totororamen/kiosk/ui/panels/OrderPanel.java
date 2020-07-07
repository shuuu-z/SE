package com.totororamen.kiosk.ui.panels;

import com.totororamen.kiosk.data.entities.Item;
import com.totororamen.kiosk.data.io.ItemIO;
import com.totororamen.kiosk.ui.MainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * The order panel, will display the price of the ramen.
 */
public class OrderPanel extends BasePanel {
    private ImagePanel imagePanel;
    private JLabel nameLabel;
    private JLabel priceLabel;
    private JLabel descriptionLabel;
    private JPanel descriptionPanel;
    private JButton personalizeButton;

    private Item ramen;

    public OrderPanel(MainWindow frame) {
        super(frame, null);
    }

    @Override
    protected void loadData(Object data) {
        for (Item item : ItemIO.getInstance().getData()) {
            if (item.getName().equals("Ramen")) {
                ramen = item;
            }
        }
    }

    @Override
    public void reset() {
        loadData(null);
        super.reset();
    }

    @Override
    protected void setupGUI() {
        removeAll();
        setLayout(new GridBagLayout());

        imagePanel = new ImagePanel("assets/img/ramen.jpg");
        add(imagePanel, new GridBagConstraints(0,0,2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        nameLabel = new JLabel("Ramen");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(nameLabel, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
                new Insets(20, 20, 20, 20),10 ,10));

        priceLabel = new JLabel(String.format("£%.02f", ramen != null ? ramen.getPrice() : 0f));
        priceLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        priceLabel.setForeground(Color.orange);
        add(priceLabel, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
                new Insets(20, 20, 20, 20), 10, 10));

        descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new GridBagLayout());
        descriptionLabel = new JLabel(String.format("<html><h2>Product description</h2><div>We serve a simple dish " +
                "(ramen) at a fixed price of £%.02f, but you can personalize it by changing options</div></html>", ramen != null ? ramen.getPrice() : 0f));
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        descriptionPanel.add(descriptionLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(20, 20, 30, 20), 20, 20));
        descriptionPanel.setBackground(Color.white);
        add(descriptionPanel, new GridBagConstraints(0, 2, 2, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

        personalizeButton = new JButton("Personalize >");
        personalizeButton.setBackground(Color.orange);
        personalizeButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        personalizeButton.addActionListener(e -> frame.toPersonalize());
        personalizeButton.setFont(new Font("Arial", Font.BOLD, 25));
        add(personalizeButton, new GridBagConstraints(0, 3, 2, 1, 1, 0, GridBagConstraints.SOUTH, GridBagConstraints.BOTH,
                new Insets(20, 80, 20, 20), 20, 20));
    }
}
