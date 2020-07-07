package com.totororamen.kiosk.ui.panels;

import com.totororamen.kiosk.Main;
import com.totororamen.kiosk.data.entities.Order;
import com.totororamen.kiosk.ui.MainWindow;

import javax.swing.*;
import java.awt.*;

/**
 * The panel displaying the take-meal number
 * and the order ID after the order finishes.
 */
public class OrderFinishPanel extends BasePanel {
    private String number;
    private String orderId;

    /**
     * Constructor
     * @param frame The parent frame
     * @param number The take-meal number
     * @param orderId The order ID
     */
    public OrderFinishPanel(MainWindow frame, String number, String orderId) {
        super(frame, new String[]{number, orderId});
    }

    @Override
    protected void loadData(Object data) {
        String[] ss = (String[]) data;
        this.number = ss[0];
        this.orderId = ss[1];
    }

    /**
     * Sets the take-meal number and the order ID
     * @param number The take-meal number
     * @param orderId The order ID
     */
    public void setOrderId(String number, String orderId) {
        this.number = number;
        this.orderId = orderId;
        setupGUI();
    }

    @Override
    protected void setupGUI() {
        removeAll();
        setLayout(new GridBagLayout());

        JLabel topLabel = new JLabel("Use this code to take your meal:");
        topLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        add(topLabel, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(40, 30, 20, 10), 20, 20));

        JLabel numLabel = new JLabel(number);
        numLabel.setForeground(Color.orange);
        numLabel.setFont(new Font("Arial", Font.BOLD, 40));
        add(numLabel, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 60, 10, 30), 20, 20));

        JLabel orderIDLabel = new JLabel("Order ID:" + orderId);
        orderIDLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        add(orderIDLabel, new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(20, 30, 20, 10), 20, 20));

        JLabel tipsLabel = new JLabel("<html><p>If you logged in with membership, you will get a stamp. When you collected 10 stamps, you can have a free meal</p></html>");
        tipsLabel.setForeground(Color.LIGHT_GRAY);
        add(tipsLabel, new GridBagConstraints(0, 3, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(20, 30, 20, 10), 20, 20));

        add(new JPanel(), new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(20, 30, 20, 10), 20, 20));

        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(Color.orange);
        exitButton.setBorder(BorderFactory.createEmptyBorder());
        exitButton.addActionListener(e -> frame.toWelcome());
        exitButton.setFont(new Font("Arial", Font.BOLD, 30));
        add(exitButton, new GridBagConstraints(0, 5, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(30, 30, 30, 30), 10, 10));
    }
}
