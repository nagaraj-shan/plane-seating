package com.squareshift.model;

/**
 * The enum Seat type.
 */
public enum SeatType {
    /**
     * Window seat type.
     */
    WINDOW("W"),
    /**
     * Aile seat type.
     */
    AILE("A"),
    /**
     * Center seat type.
     */
    CENTER("C");

    private final String value;

    SeatType(String c) {
        this.value = c;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }
}
