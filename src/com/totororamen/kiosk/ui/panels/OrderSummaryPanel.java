package com.totororamen.kiosk.ui.panels;

import com.totororamen.kiosk.Main;
import com.totororamen.kiosk.data.entities.Item;
import com.totororamen.kiosk.data.entities.ItemOption;
import com.totororamen.kiosk.data.entities.Membership;
import com.totororamen.kiosk.data.entities.Order;
import com.totororamen.kiosk.data.io.ItemIO;
import com.totororamen.kiosk.data.io.ItemOptionsIO;
import com.totororamen.kiosk.data.io.MembershipIO;
import com.totororamen.kiosk.data.io.OrderIO;
import com.totororamen.kiosk.ui.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The panel displaying the summary of the order
 * and asking the customer to pay
 */
public class OrderSummaryPanel extends BasePanel {
    private Order order;
    private Membership membership;
    private int paymentType;

    private JPanel orderDetailPanel;
    private JScrollPane orderDetailScrollPane;
    private JButton paymentCashButton;
    private JButton paymentCardButton;
    private JButton paymentStampButton;
    private JLabel choosePaymentLabel;
    private JLabel pendingPaymentLabel;
    private JLabel gifLabel;
    private JButton confirmPaymentButton;

    public OrderSummaryPanel(MainWindow frame, Order order) {
        super(frame, order);
    }

    @Override
    protected void loadData(Object data) {
        Order order = (Order) data;
        this.order = order;

        // If the membership is 0, this means that the customer didn't logged in with membership
        if (order.getMembershipID() != 0) {
            Membership m = MembershipIO.getInstance().getMembershipById(order.getMembershipID());
            if (m != null) {
                this.membership = m;
            }
        }
    }

    /**
     * Sets the current order
     * @param order The current order
     */
    public void setOrder(Order order) {
        this.order = order;
        if (order.getMembershipID() != 0) {
            for (Membership m : MembershipIO.getInstance().getData()) {
                if (m.getMembershipID() == order.getMembershipID()) {
                    this.membership = m;
                }
            }
        }
        setupGUI();
    }

    @Override
    protected void setupGUI() {
        removeAll();
        setLayout(new GridBagLayout());

        orderDetailPanel = new JPanel();
        orderDetailPanel.setLayout(new GridBagLayout());
        orderDetailScrollPane = new JScrollPane(orderDetailPanel);
        add(orderDetailScrollPane, new GridBagConstraints(0, 0, 3, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

        JLabel ramenLabel = new JLabel("Ramen" + (order.isTakeAway() ? "(take away)" : "(eat in)"));
        JLabel ramenCountLabel = new JLabel("x" + order.getRamenNumber());
        JLabel priceLabel = new JLabel(String.format("£%.02f", order.getPrice()));
        ramenLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
        ramenCountLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
        priceLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
        priceLabel.setForeground(Color.orange);

        orderDetailPanel.add(ramenLabel, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(20, 20, 5, 10), 5, 5));
        orderDetailPanel.add(ramenCountLabel, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(20, 20, 5, 10), 5, 5));
        orderDetailPanel.add(priceLabel, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(20, 20, 5, 10), 5, 5));

        JLabel noteLabel = new JLabel("Note: " + order.getNote());
        noteLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));

        orderDetailPanel.add(noteLabel, new GridBagConstraints(0, 1, 3, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(20, 20, 5, 10), 5, 5));

        int j = 2;
        for (int i = 0; i < order.getOptionNames().length; i++) {
            ItemOption o = ItemOptionsIO.getInstance().getOptionByName(order.getOptionNames()[i]);
            if (o != null) {
                JLabel optionNameLabel = new JLabel(order.getOptionNames()[i]);
                JLabel optionSelectLabel = new JLabel(o.getOptions()[order.getOptionsSelects()[i]]);

                optionSelectLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
                optionNameLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));

                orderDetailPanel.add(optionNameLabel, new GridBagConstraints(0, j, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                        new Insets(20, 60, 5, 10), 5, 5));
                orderDetailPanel.add(optionSelectLabel, new GridBagConstraints(1, j, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                        new Insets(20, 60, 5, 10), 5, 5));
                j++;
            }

        }

        for (int i = 0; i < order.getAddonNames().length; i++) {
            Item o = ItemIO.getInstance().getItemByName(order.getAddonNames()[i]);
            if (o != null && order.getAddonsNumbers()[i] != 0) {
                JLabel addonNameLabel = new JLabel(order.getAddonNames()[i]);
                JLabel addonNumberLabel = new JLabel("x" + order.getAddonsNumbers()[i]);
                JLabel addonPriceLabel = new JLabel(String.format("£%.02f", order.getAddonsPrices()[i] * order.getAddonsNumbers()[i]));

                addonNumberLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
                addonNameLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
                addonPriceLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
                addonPriceLabel.setForeground(Color.orange);

                orderDetailPanel.add(addonNameLabel, new GridBagConstraints(0, j, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                        new Insets(20, 60, 5, 10), 5, 5));
                orderDetailPanel.add(addonNumberLabel, new GridBagConstraints(1, j, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                        new Insets(20, 60, 5, 10), 5, 5));
                orderDetailPanel.add(addonPriceLabel, new GridBagConstraints(2, j, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                        new Insets(20, 60, 5, 10), 5, 5));
                j++;
            }

        }


        if (membership == null || membership.getStampCount() <= 10 || membership.getAutoPay() == 0) {

            choosePaymentLabel = new JLabel("Choose a payment method");
            choosePaymentLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 17));
            add(choosePaymentLabel, new GridBagConstraints(0, 1, 3, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                    new Insets(10, 20, 10, 20), 10, 5));

            paymentCashButton = new JButton("Cash");
            paymentCashButton.setBorder(BorderFactory.createEmptyBorder());
            paymentCashButton.setBackground(Color.lightGray);
            paymentCashButton.addActionListener(e -> toggleCash());
            paymentCashButton.setFont(new Font("Arial", Font.BOLD, 20));
            add(paymentCashButton, new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                    new Insets(10, 10, 10, 10), 10, 10));

            paymentCardButton = new JButton("Card");
            paymentCardButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
            paymentCardButton.setBackground(Color.orange);
            paymentCardButton.addActionListener(e -> toggleCard());
            paymentCardButton.setFont(new Font("Arial", Font.BOLD, 20));
            add(paymentCardButton, new GridBagConstraints(1, 2, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                    new Insets(10, 10, 10, 10), 10, 10));

            paymentStampButton = new JButton("Stamps");
            paymentStampButton.setBorder(BorderFactory.createEmptyBorder());
            paymentStampButton.setBackground(Color.lightGray);
            paymentStampButton.addActionListener(e -> toggleStamps());
            // Disable the stamps button if the user have not logged in or have insufficient stamps.
            paymentStampButton.setEnabled(membership != null && membership.getStampCount() >= 10);
            paymentStampButton.setFont(new Font("Arial", Font.BOLD, 20));
            add(paymentStampButton, new GridBagConstraints(2, 2, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                    new Insets(10, 10, 10, 10), 10, 10));

            gifLabel = new JLabel();
            ImageIcon icon = new ImageIcon(new ImageIcon("assets/img/InsertCard.gif").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
            gifLabel.setIcon(icon);
            add(gifLabel, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                    new Insets(10, 10, 10, 10), 10, 10));

            pendingPaymentLabel = new JLabel("Please insert your card");
            pendingPaymentLabel.setFont(new Font("Arial", Font.BOLD, 17));
            add(pendingPaymentLabel, new GridBagConstraints(1, 3, 2, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                    new Insets(10, 10, 10, 10), 10, 30));

            confirmPaymentButton = new JButton("Confirm Payment");
            confirmPaymentButton.setBackground(Color.orange);
            confirmPaymentButton.setFont(new Font("Arial", Font.BOLD, 20));
            confirmPaymentButton.setBorder(BorderFactory.createEmptyBorder());
            confirmPaymentButton.addActionListener(e -> confirmPayment());
            add(confirmPaymentButton, new GridBagConstraints(0, 4, 3, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                    new Insets(10, 60, 10, 60), 10, 20));
        }
        else {
            paymentType = 2;
            pendingPaymentLabel = new JLabel("<html>You have enabled auto pay with stamps<br/> Clicking 'Confirm' will have this meal for free with 10 stamps</html>");
            pendingPaymentLabel.setFont(new Font("Arial", Font.BOLD, 17));
            add(pendingPaymentLabel, new GridBagConstraints(0, 1, 2, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                    new Insets(10, 10, 10, 10), 10, 30));

            confirmPaymentButton = new JButton("Confirm Payment");
            confirmPaymentButton.setBackground(Color.orange);
            confirmPaymentButton.setFont(new Font("Arial", Font.BOLD, 20));
            confirmPaymentButton.setBorder(BorderFactory.createEmptyBorder());
            confirmPaymentButton.addActionListener(e -> confirmPayment());
            add(confirmPaymentButton, new GridBagConstraints(0, 2, 3, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                    new Insets(10, 60, 10, 60), 10, 20));
        }
    }

    /**
     * Toggle current payment method to cash
     */
    private void toggleCash() {
        paymentCashButton.setBackground(Color.orange);
        paymentCashButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        paymentStampButton.setBackground(Color.lightGray);
        paymentStampButton.setBorder(BorderFactory.createEmptyBorder());
        paymentCardButton.setBackground(Color.lightGray);
        paymentCardButton.setBorder(BorderFactory.createEmptyBorder());
        pendingPaymentLabel.setText("Please insert notes");
        gifLabel.setIcon(new ImageIcon(new ImageIcon("assets/img/InsertMoney.gif").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
        paymentType = 0;
    }

    /**
     * toggle current payment method to card
     */
    private void toggleCard() {
        paymentCashButton.setBackground(Color.lightGray);
        paymentCashButton.setBorder(BorderFactory.createEmptyBorder());
        paymentStampButton.setBackground(Color.lightGray);
        paymentStampButton.setBorder(BorderFactory.createEmptyBorder());
        paymentCardButton.setBackground(Color.orange);
        paymentCardButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        pendingPaymentLabel.setText("Please insert your card");
        gifLabel.setIcon(new ImageIcon(new ImageIcon("assets/img/InsertCard.gif").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
        paymentType = 1;
    }

    /**
     * Toggle current payment method to stamp.
     * Will be unavailable if the user don't have enough stamps
     */
    private void toggleStamps() {
        paymentCashButton.setBackground(Color.lightGray);
        paymentCashButton.setBorder(BorderFactory.createEmptyBorder());
        paymentStampButton.setBackground(Color.orange);
        paymentStampButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        paymentCardButton.setBackground(Color.lightGray);
        paymentCardButton.setBorder(BorderFactory.createEmptyBorder());
        pendingPaymentLabel.setText("Have this meal for free with 10 stamps!");
        gifLabel.setIcon(null);
        paymentType = 2;
    }

    /**
     * Confirm the order.
     * This will store the order to the file and a ticket will be printed
     */
    private void confirmPayment() {
        // If the user logged in as membership, change the stamp count
        if (membership != null) {
            if (paymentType < 2) {
                // The user use cash/card, his/her stamps will increase by 1.
                membership.setStampCount(membership.getStampCount() + 1);
            } else {
                // The user pay with the stamps
                membership.setStampCount(membership.getStampCount() - 10);
            }
        }

        order.setPaymentType(paymentType);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        order.setTime(formatter.format(new Date())); // Sets the current time to the order
        OrderIO.getInstance().getData().add(order);
        OrderIO.getInstance().saveChanges();  // Save the changes of the membership's stamps
        MembershipIO.getInstance().saveChanges(); // Save the new order to the file
        frame.toOrderFinish(order.getOrderID());
        order.printTicket();  // The tickets will be saved as a file
    }
}
