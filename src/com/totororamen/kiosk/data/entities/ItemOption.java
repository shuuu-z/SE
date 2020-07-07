package com.totororamen.kiosk.data.entities;

/**
 * An selectable option of the Ramen
 * e.g. The spiciness, which can be 1 to 6
 */
public class ItemOption {
    private String name;
    private String[] options;
    private boolean[] availability;

    /**
     * Constructor of {@code ItemOption}
     * @param name The name of the option
     * @param options The selectable items of this option
     * @param availability The availability of each of the {@code options}
     */
    public ItemOption(String name, String[] options, boolean[] availability) {
        this.name = name;
        this.options = options;
        this.availability = availability;
    }

    /**
     * Gets the name of this option
     * @return The name of this option
     */
    public String getName() {
        return name;
    }

    /**
     * Get the items of the option
     * @return The items of the option
     */
    public String[] getOptions() {
        return options;
    }

    /**
     * Gets the availability of the option items
     * @return The availability of the option items
     */
    public boolean[] getAvailability() {
        return availability;
    }

    /**
     * Sets the availability of the option items
     * @param availability The availability of the option items
     */
    public void setAvailability(boolean[] availability) {
        this.availability = availability;
    }
}
