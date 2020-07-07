package com.totororamen.kiosk.ui.panels;

import com.totororamen.kiosk.ui.MainWindow;

import javax.swing.*;
import java.awt.*;

/**
 * The base class of all the sub-panels that will shown
 * as the body of the main frame.
 */
public abstract class BasePanel extends JPanel {
    protected MainWindow frame;  // The parent frame

    /**
     * Constructor (Without data)
     * @param frame The main frame
     */
    public BasePanel(MainWindow frame) {
        this.frame = frame;
        setupGUI();
    }

    /**
     * Constructor (With data)
     * @param frame The main frame
     * @param data The data object
     */
    public BasePanel(MainWindow frame, Object data) {
        this.frame = frame;
        loadData(data);
        setupGUI();
    }

    /**
     * This method resets the data on the panel,
     * it will reload GUI components.
     * Sub-classes can override this method in order to do more on panel reset
     */
    public void reset() {
        super.removeAll(); // Removes all the GUI components
        setupGUI();
    }

    /**
     * This method will load the data required for the GUI display.
     * The data object can be any type (need conversion)
     * This method is not abstract so you can choose whether to override it or not
     * @param data The data object
     */
    protected void loadData(Object data) {
    }

    /**
     * This method sets up the GUI components,
     * it will be called by the constructor and the
     * {@code reset()} method.
     * It must be overridden by sub-classes.
     */
    protected abstract void setupGUI();
}
