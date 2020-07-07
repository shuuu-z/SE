package com.totororamen.kiosk.ui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * A JPenel that has a background image.
 */
public class ImagePanel extends JPanel {
    private String imagePath;

    /**
     * Constructor
     * @param imagePath The background image of this panel
     */
    public ImagePanel(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Function used to paint the background image
     * DO NOT call directly, this will be called automatically
     * @param g graphic parameter
     */
    public void paintComponent(Graphics g) {
        Image img = Toolkit.getDefaultToolkit().getImage(imagePath);
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    /**
     * Return the path of the background image
     * @return The path of the background image
     */
    public String getImagePath() {
        return this.imagePath;
    }

    /**
     * Sets the path of the background image
     * @param imagePath The new path of the background image
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
