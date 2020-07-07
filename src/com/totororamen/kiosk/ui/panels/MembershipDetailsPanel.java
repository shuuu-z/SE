package com.totororamen.kiosk.ui.panels;

import com.totororamen.kiosk.data.entities.Membership;
import com.totororamen.kiosk.data.entities.Order;
import com.totororamen.kiosk.data.io.MembershipIO;
import com.totororamen.kiosk.data.io.OrderIO;
import com.totororamen.kiosk.ui.MainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

/**
 * The panel showing the personal info of the member.
 * Including the virtual stamp history
 */
public class MembershipDetailsPanel extends BasePanel {

    private JLabel membershipNumberLabel;
    private JButton personalInfoButton;
    private JButton virtualStampsButton;
    private JPanel mainPanel;
    private JPanel personalInfoPanel;
    private JPanel virtualStampPanel;
    private JScrollPane virtualStampsScrollPane;
    private JPanel bottomPanel;
    private JCheckBox autoPayCheckBox;

    private JLabel virtualStampsCountLabel;
    private JButton orderButton;

    private CardLayout cards;

    private Membership membership;
    private ArrayList<Order> orders;

    public MembershipDetailsPanel(MainWindow frame, Membership membership) {
        super(frame, membership);
        frame.setCurrentMembership(membership);
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
        frame.setCurrentMembership(membership);
        loadData(membership);
        setupGUI();
    }

    @Override
    protected void loadData(Object data) {
        Membership membership = (Membership) data;
        this.membership = membership;
        if (orders == null) {
            orders = new ArrayList<>();
        }

        orders.clear();
        for (Order o : OrderIO.getInstance().getData()) {
            if (o.getMembershipID() != 0 && o.getMembershipID() == membership.getMembershipID()) {
                orders.add(o);
            }
        }
    }

    @Override
    protected void setupGUI() {
        removeAll();
        setLayout(new GridBagLayout());

        membershipNumberLabel = new JLabel(String.format("Membership ID: %08d", membership.getMembershipID()));
        membershipNumberLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        add(membershipNumberLabel, new GridBagConstraints(0, 0, 2, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 10, 10));

        personalInfoButton = new JButton("Personal Info");
        personalInfoButton.setFont(new Font("Arial", Font.BOLD, 20));
        personalInfoButton.setBackground(Color.orange);
        personalInfoButton.addActionListener(e -> togglePersonalInfoPanel());
        personalInfoButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        add(personalInfoButton, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 10, 30));

        virtualStampsButton = new JButton("Virtual Stamps");
        virtualStampsButton.setFont(new Font("Arial", Font.BOLD, 20));
        virtualStampsButton.setBackground(Color.white);
        virtualStampsButton.addActionListener(e -> toggleVirtualStampsPanel());
        virtualStampsButton.setBorder(BorderFactory.createLineBorder(Color.white));
        add(virtualStampsButton, new GridBagConstraints(1, 1, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 10, 30));

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

        virtualStampsCountLabel = new JLabel(String.format("Total: %s stamps", membership.getStampCount()));
        virtualStampsCountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        bottomPanel.add(virtualStampsCountLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 20, 10, 10), 10, 10));

        orderButton = new JButton("To Order >");
        orderButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        orderButton.setFont(new Font("Arial", Font.BOLD, 20));
        orderButton.setBackground(Color.orange);
        orderButton.addActionListener(e -> frame.toOrder());
        bottomPanel.add(orderButton, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 30, 20));

        personalInfoPanel = new JPanel();
        personalInfoPanel.setBackground(Color.white);
        personalInfoPanel.setLayout(new GridBagLayout());
        mainPanel.add(personalInfoPanel, "personal");

        JLabel nameLabel = new JLabel(String.format("Name: %s %s", membership.getFirstName(), membership.getLastName()));
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        personalInfoPanel.add(nameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 30, 10, 10), 20, 20));

        JLabel emailLabel = new JLabel(String.format("E-mail: %s", membership.getEmail()));
        emailLabel.setFont(new Font("Arial", Font.BOLD, 20));
        personalInfoPanel.add(emailLabel, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 30, 10, 10), 20, 20));

        JLabel phoneLabel = new JLabel(String.format("Phone: %s", membership.getPhone()));
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 20));
        personalInfoPanel.add(phoneLabel, new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 30, 10, 10), 20, 20));

        autoPayCheckBox = new JCheckBox("    Automatically use stamps to pay");
        autoPayCheckBox.setFont(new Font("Arial", Font.BOLD, 15));
        autoPayCheckBox.setBackground(Color.white);
        personalInfoPanel.add(autoPayCheckBox, new GridBagConstraints(0, 3, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 30, 10, 10), 20, 20));
        autoPayCheckBox.setSelected(membership.getAutoPay() != 0);
        autoPayCheckBox.addActionListener(e -> {
            membership.setAuthPay(autoPayCheckBox.isSelected() ? 1 : 0);
            MembershipIO.getInstance().saveChanges();
        });

        JButton modifyButton = new JButton("Modify Personal Info.");
        modifyButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        modifyButton.setFont(new Font("Arial", Font.BOLD, 20));
        modifyButton.setBackground(Color.orange);
        modifyButton.addActionListener(e -> frame.toMembershipModifyInfo(membership));
        personalInfoPanel.add(modifyButton, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 30, 20));

        JButton modifyPasswordButton = new JButton("Modify password");
        modifyPasswordButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        modifyPasswordButton.setFont(new Font("Arial", Font.BOLD, 20));
        modifyPasswordButton.setBackground(Color.orange);
        modifyPasswordButton.addActionListener(e -> frame.toMembershipModifyPassword(membership, false));
        personalInfoPanel.add(modifyPasswordButton, new GridBagConstraints(0, 5, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 30, 20));

        virtualStampPanel = new JPanel();
        virtualStampsScrollPane = new JScrollPane(virtualStampPanel);
        virtualStampPanel.setLayout(new GridBagLayout());
        virtualStampPanel.setBackground(Color.white);
        mainPanel.add(virtualStampsScrollPane, "stamps");

        JLabel vsLabel = new JLabel("Obtaining Records:");
        vsLabel.setFont(new Font("Arial", Font.BOLD, 15));
        virtualStampPanel.add(vsLabel, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 20, 20));

        int i = 0;
        for (Order o : orders) {
            JButton vsButton1 = new JButton(String.format("<html>%s<br/>%s</html>", o.getTime(),
                    o.getPaymentType() == 2 ? "You spent 10 stamps" : "You get one stamp"));
            vsButton1.setFont(new Font("Arial", Font.PLAIN, 15));
            vsButton1.setBorder(BorderFactory.createEmptyBorder());
            vsButton1.setBackground(Color.lightGray);
            virtualStampPanel.add(vsButton1, new GridBagConstraints(0, ++i, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                    new Insets(10, 50, 10, 30), 20, 20));
        }

        JPanel spacer = new JPanel();
        spacer.setBackground(Color.white);
        virtualStampPanel.add(spacer, new GridBagConstraints(0, ++i, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 50, 10, 30), 20, 20));
        cards.show(mainPanel, "personal");
    }

    /**
     * Toggle to the virtual stamps tab
     */
    private void toggleVirtualStampsPanel() {
        cards.show(mainPanel, "stamps");
        virtualStampsButton.setBackground(Color.orange);
        virtualStampsButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        personalInfoButton.setBackground(Color.white);
        personalInfoButton.setBorder(BorderFactory.createEmptyBorder());
    }

    /**
     * Toggles to the personal info tab
     */
    private void togglePersonalInfoPanel() {
        cards.show(mainPanel, "personal");
        virtualStampsButton.setBackground(Color.white);
        virtualStampsButton.setBorder(BorderFactory.createEmptyBorder());
        personalInfoButton.setBackground(Color.orange);
        personalInfoButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
    }
}
