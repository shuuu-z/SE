package com.totororamen.kiosk;

import com.totororamen.kiosk.ui.MainWindow;

import javax.swing.*;
import java.awt.*;

/**
 * The startup class of the kiosk application
 */
public class Main
{
    /**
     * The entry of the application
     * @param args Execution arguments, not used in the application
     */
    public static void main(String[] args) {
        MainWindow mw = new MainWindow("Totoro Ramen Ordering Kiosk");
        mw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mw.setSize(550, 900);
        mw.setLocationRelativeTo(null);
        mw.setVisible(true);
    }
}
