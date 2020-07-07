package com.totororamen.kiosk.ui.panels;

import com.totororamen.kiosk.data.entities.Order;
import com.totororamen.kiosk.ui.MainWindow;

import javax.swing.*;
import java.awt.*;

/**
 * The panel allowing user to add notes to the order
 */
public class OrderNotePanel extends BasePanel {
    //private int number;
    private Order order;

    private JLabel topLabel;
    private JTextArea noteField;
    private JButton submitButton;

    public OrderNotePanel(MainWindow frame, Order order) {
        super(frame, order);
    }

    public void setOrder(Order order) {
        this.order = order;
        reset();
    }

    @Override
    protected void loadData(Object data) {
        this.order = (Order) data;
    }

    @Override
    protected void setupGUI() {
        this.setLayout(new GridBagLayout());
        topLabel = new JLabel("You can write note: ");
        topLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        this.add(topLabel, new GridBagConstraints(0,0,1,1,1,0,GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(30,30,30,30), 30, 30));

        noteField = new JTextArea(4,15);
        noteField.setEnabled(true);//can be write
        noteField.setFont(new Font("Arial", Font.BOLD, 30));
        this.add(noteField, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.NORTH,GridBagConstraints.BOTH, new Insets(30,30,30,30),0,5));
        noteField.setLineWrap(true);//zi dong huan hang

        this.add(new JPanel(),new GridBagConstraints(0, 3, 1, 1, 1, 1 ,GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10,10,10,10),30,30));

        submitButton = new JButton("Submit");
        submitButton.setBackground(Color.orange);
        submitButton.setFont(new Font("Arial", Font.BOLD, 30));
        submitButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        this.add(submitButton, new GridBagConstraints(0,2,1,1,1,0,GridBagConstraints.NORTH,GridBagConstraints.BOTH,new Insets(30,70,30,70),15,15));
        submitButton.addActionListener(e -> doSubmit());

    }

    /**
     * Handle of the "submit" button
     */
    private void doSubmit(){
        if(isMoreWords()){
            return;
        }
        order.setNote(noteField.getText());
        frame.toDetails(order);
    }

    /**
     * Check if there word count does not exceed 50
     * @return True if the count does not exceed 50
     */
    private boolean isMoreWords(){
        String count = noteField.getText();
        if(count.length() >= 50){
            JOptionPane.showMessageDialog(null, "You can only enter 50 words!", "Too much words Waring", 1);
            return true;
        }
        return false;
    }
}
