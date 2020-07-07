package com.totororamen.kiosk.ui;

import com.totororamen.kiosk.data.entities.Membership;
import com.totororamen.kiosk.data.entities.Order;
import com.totororamen.kiosk.ui.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Calendar;

/**
 * The main window of the application
 * Contains multiple frames
 */
public class MainWindow extends JFrame {
    // The thread used to update the displayed time
    private Thread timer;
    
    // GUI components
    // Panels
    private JPanel timePanel;  // The panel used to display the clock
    private JPanel bodyPanel;  // The panel below the clock panel
    private JPanel titlePanel; // The title bar of the body part, contain page title and "back" button
    private JPanel mainPanel;  // The main panel, used for displaying the sub-panels

    // Card layout used to switch between panels
    private CardLayout cards;

    // Sub-panels, display in the main section
    private WelcomePanel welcomePanel;
    private AskMembershipPanel askMembershipPanel;
    private MembershipLoginPanel membershipLoginPanel;
    private MembershipRegisterPanel membershipRegisterPanel;
    private MembershipDetailsPanel membershipDetailsPanel;
    private OrderPanel orderPanel;
    private PersonalizePanel personalizePanel;
    private DiningOptionPanel diningOptionPanel;
    private OrderSummaryPanel orderSummaryPanel;
    private OrderFinishPanel orderFinishPanel;
    private OrderNotePanel orderNotePanel;
    private MembershipResetPasswordPanel membershipResetPasswordPanel;
    private MembershipModifyPasswordPanel membershipModifyPasswordPanel;
    private MembershipModifyInfoPanel membershipModifyInfoPanel;

    // Sub-panels for management functions
    private ManagerPasswordPanel managerPasswordPanel;
    private ManagerMenuPanel managerMenuPanel;
    private ManagerModifyPasswordPanel managerModifyPasswordPanel;
    private ManagerStatsPanel managerStatsPanel;
    private ManagerEditMenuPanel managerEditMenuPanel;

    // Labels
    private JLabel timeLabel;
    private JLabel titleLabel;

    // Buttons
    private JButton backButton;


    private int orderNumber = 1000;
    private Membership currentMembership;

    /**
     * Constructor of {@code MainWindow}
     * @param title The custom window title
     */
    public MainWindow(String title) {
        super(title);
        setupGUI();

        // Create a thread for the clock and start it
        timer = new Thread(this::updateTime);
        timer.start();

        // Disable the "back" button
        disableBack();
    }

    /**
     * Initialize GUI components
     */
    private void setupGUI() {
        // The clock displayed on the top
        timePanel = new JPanel();
        timeLabel = new JLabel("00:00");
        timePanel.add(timeLabel);
        this.getContentPane().add(timePanel, BorderLayout.NORTH);

        // The body part below the clock
        bodyPanel = new JPanel();
        titlePanel = new JPanel(); // The panel in the top of the body
        mainPanel = new JPanel();  // This panel will used to display the sub-panels
        titleLabel = new JLabel("Totoro Ramen", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.ITALIC | Font.BOLD,18));
        backButton = new JButton("");
        backButton.setFont(new Font("Arial", Font.BOLD, 15));
        bodyPanel.setLayout(new BorderLayout());
        titlePanel.setLayout(new BorderLayout());
        mainPanel.setLayout(new BorderLayout());
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.add(backButton, BorderLayout.WEST);
        titlePanel.add(new JLabel("                       "), BorderLayout.EAST); // A placeholder
        titlePanel.setBackground(Color.LIGHT_GRAY);
        backButton.setBackground(Color.LIGHT_GRAY);
        backButton.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        bodyPanel.add(titlePanel, BorderLayout.NORTH);
        bodyPanel.add(mainPanel, BorderLayout.CENTER);
        this.getContentPane().add(bodyPanel, BorderLayout.CENTER);

        // Use card layout for the main panel
        cards = new CardLayout();
        mainPanel.setLayout(cards);

        // Creation of the sub-panels
        welcomePanel = new WelcomePanel(this);
        mainPanel.add(welcomePanel, "welcome");

        askMembershipPanel = new AskMembershipPanel(this);
        mainPanel.add(askMembershipPanel, "ask_membership");

        membershipLoginPanel = new MembershipLoginPanel(this);
        mainPanel.add(membershipLoginPanel, "membership_login");

        membershipRegisterPanel = new MembershipRegisterPanel(this);
        mainPanel.add(membershipRegisterPanel, "membership_register");

        orderPanel = new OrderPanel(this);
        mainPanel.add(orderPanel, "order");

        personalizePanel = new PersonalizePanel(this);
        mainPanel.add(personalizePanel, "personalize");

        managerPasswordPanel = new ManagerPasswordPanel(this);
        mainPanel.add(managerPasswordPanel, "manager_password");

        managerMenuPanel = new ManagerMenuPanel(this);
        mainPanel.add(managerMenuPanel, "manager_menu");

        managerModifyPasswordPanel = new ManagerModifyPasswordPanel(this);
        mainPanel.add(managerModifyPasswordPanel, "manager_change_password");

        managerStatsPanel = new ManagerStatsPanel(this);
        mainPanel.add(managerStatsPanel, "manager_stats");

        managerEditMenuPanel = new ManagerEditMenuPanel(this);
        mainPanel.add(managerEditMenuPanel, "manager_edit_menu");

        membershipResetPasswordPanel = new MembershipResetPasswordPanel(this);
        mainPanel.add(membershipResetPasswordPanel, "reset_password");
    }

    /**
     * Jumps to the welcome page
     */
    public void toWelcome() {
        // Clear current membership
        currentMembership = null;

        // Switch card, will change the panel displayed in the main panel
        cards.show(mainPanel, "welcome");
        titleLabel.setText("Toroto Ramen");  // Sets the title bar
        disableBack();
    }

    /**
     * Jump to the membership asking page
     */
    public void toAskMembership() {
        cards.show(mainPanel, "ask_membership");
        titleLabel.setText("Membership");
        enableBack(e -> toWelcome()); // Enables "back" button and set the action of it
    }

    /**
     * Jump to the membership login page
     */
    public void toMembershipLogin() {
        membershipLoginPanel.reset();  // Reset the panel to clear the text fields for the panel
        cards.show(mainPanel, "membership_login");
        titleLabel.setText("Membership Login");
        enableBack(e -> toAskMembership());
    }

    /**
     * Jump to the membership register page
     */
    public void toMembershipRegister() {
        membershipRegisterPanel.reset();
        cards.show(mainPanel, "membership_register");
        titleLabel.setText("Membership Register");
        enableBack(e -> toAskMembership());
    }

    /**
     * Jump to the membership details page
     * @param membership The membership info
     */
    public void toMembershipDetails(Membership membership) {
        // If the panel is not initialized yet
        if (membershipDetailsPanel == null) {
            membershipDetailsPanel = new MembershipDetailsPanel(this, membership);
            mainPanel.add(membershipDetailsPanel, "membership_details");
        }
        else {
            membershipDetailsPanel.setMembership(membership);
        }
        cards.show(mainPanel, "membership_details");
        titleLabel.setText("Membership Details");
        enableBack(e -> toMembershipLogin());
        this.currentMembership = membership; // Sets the current membership
    }

    /**
     * Jump to the order beginning page
     */
    public void toOrder() {
        orderPanel.reset();
        cards.show(mainPanel, "order");
        titleLabel.setText("Ordering");
        enableBack(e -> toWelcome());
    }

    /**
     * Jump to the order personalization page
     */
    public void toPersonalize() {
        personalizePanel.reset();
        cards.show(mainPanel, "personalize");
        titleLabel.setText("Ordering");
        enableBack(e -> toOrder());
    }

    /**
     * Jump to the dining option page
     * @param order The current order
     */
    public void toDiningOption(Order order) {
        if (diningOptionPanel == null) {
            diningOptionPanel = new DiningOptionPanel(this, order);
            mainPanel.add(diningOptionPanel, "dining_option");
        }
        else {
            diningOptionPanel.setOrder(order);
        }
        cards.show(mainPanel, "dining_option");
        titleLabel.setText("Dining Option");
        enableBack(e -> toPersonalize());
    }

    /**
     * Jump to the order details page
     * @param order The current order
     */
    public void toDetails(Order order) {
        if (orderSummaryPanel == null) {
            orderSummaryPanel = new OrderSummaryPanel(this, order);
            mainPanel.add(orderSummaryPanel, "details");
        }
        else {
            orderSummaryPanel.setOrder(order);
        }

        cards.show(mainPanel, "details");
        titleLabel.setText("Payment");
        enableBack(e -> toDiningOption(order));
    }

    /**
     * Jump to the order finish page
     * @param orderID The ID of current order
     */
    public void toOrderFinish(String orderID) {
        // Increase the current order number
        // This number will reset when the application restarts
        orderNumber++;
        if (orderFinishPanel == null) {
            orderFinishPanel = new OrderFinishPanel(this, Integer.toString(orderNumber), orderID);
            mainPanel.add(orderFinishPanel, "finish");
        }
        else {
            orderFinishPanel.setOrderId(Integer.toString(orderNumber), orderID);
        }

        cards.show(mainPanel, "finish");
        titleLabel.setText("Thanks");
        disableBack();
    }

    /**
     * Jumps to the order note page
     * @param order The order
     */
    public void toOrderNote(Order order) {
        if (orderNotePanel == null) {
            orderNotePanel = new OrderNotePanel(this, order);
            mainPanel.add(orderNotePanel, "order_note");
        }
        else {
            orderNotePanel.setOrder(order);
        }

        cards.show(mainPanel, "order_note");
        titleLabel.setText("Order Notes");
        enableBack(a -> toDiningOption(order));
    }

    public void toMembershipResetPassword() {
        cards.show(mainPanel, "reset_password");
        enableBack(e -> toMembershipLogin());
        titleLabel.setText("Reset Password");
    }

    public void toMembershipModifyInfo(Membership m) {
        if (membershipModifyInfoPanel == null) {
            membershipModifyInfoPanel = new MembershipModifyInfoPanel(this, m);
            mainPanel.add(membershipModifyInfoPanel, "member_modify_info");
        }
        else {
            membershipModifyInfoPanel.setMembership(m);
        }

        cards.show(mainPanel, "member_modify_info");
        titleLabel.setText("Modify info");
        enableBack(e -> toMembershipDetails(m));
    }

    public void toMembershipModifyPassword(Membership m, boolean isReset) {
        if (membershipModifyPasswordPanel == null) {
            membershipModifyPasswordPanel = new MembershipModifyPasswordPanel(this, m);
            mainPanel.add(membershipModifyPasswordPanel, "member_modify_password");
        }
        else {
            membershipModifyPasswordPanel.setMembership(m);
        }

        cards.show(mainPanel, "member_modify_password");
        titleLabel.setText("Modify Password");
        if (isReset)
            enableBack(e -> toMembershipLogin());
        else
            enableBack(e -> toMembershipDetails(m));
    }

    /**
     * Jump to the management password page
     */
    public void toManager() {
        cards.show(mainPanel, "manager_password");
        enableBack(e -> toWelcome());
        titleLabel.setText("Management");
    }

    /**
     * Jump to the management menu page
     */
    public void toManagerMenu() {
        cards.show(mainPanel, "manager_menu");
        enableBack(e -> toWelcome());
        titleLabel.setText("Management");
    }

    /**
     * Jump to the management password changing page
     */
    public void toChangeManagerPassword() {
        managerModifyPasswordPanel.reset();
        cards.show(mainPanel, "manager_change_password");
        enableBack(e -> toWelcome());
        titleLabel.setText("Management");
    }

    /**
     * Jump to the management stat viewer page
     */
    public void toManagerStats() {
        managerStatsPanel.reset();
        cards.show(mainPanel, "manager_stats");
        enableBack(e -> toManagerMenu());
        titleLabel.setText("Statistics");
    }

    /**
     * Jump to the menu editor page
     */
    public void toManagerEditMenu() {
        managerEditMenuPanel.reset();
        cards.show(mainPanel, "manager_edit_menu");
        enableBack(e -> toManagerMenu());
        titleLabel.setText("Edit Menu");
    }

    /**
     * Disables the "back" button
     * This will make the button looks invisible, but not really hidden from the layout
     */
    private void disableBack() {
        backButton.setText("            "); // Set the text to blank spaces to make sure the layout looks the same
        backButton.setEnabled(false);       // Disable the button
    }

    /**
     * Enables the "back" button and assign an action for it
     * @param target The action of the button
     */
    private void enableBack(ActionListener target) {
        backButton.setText("< Back"); // Sets back the button text
        backButton.setEnabled(true);  // Enables the button
        // Removes the previous assigned action listeners
        if (backButton.getActionListeners().length != 0) {
            backButton.removeActionListener(backButton.getActionListeners()[0]);
        }
        // Assign new listener
        backButton.addActionListener(target);
    }

    /**
     * Gets the current membership
     * @return The current membership
     */
    public Membership getCurrentMembership() {
        return currentMembership;
    }

    /**
     * Sets the current membership
     * @param currentMembership The current membership
     */
    public void setCurrentMembership(Membership currentMembership) {
        this.currentMembership = currentMembership;
    }

    /**
     * Update the time displayed on the clock at the top
     */
    private void updateTime() {
        while (true) {
            try{
                Calendar c = Calendar.getInstance();
                String currentTime = String.format("%02d:%02d",c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
                timeLabel.setText(currentTime);
                Thread.sleep(1000); // Update period is one sec.
            }
            catch (Exception e){
                break;
            }
        }
    }
}
