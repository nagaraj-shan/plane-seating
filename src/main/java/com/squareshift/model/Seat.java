package com.squareshift.model;

import java.util.StringJoiner;

/**
 * The type Seat.
 */
public class Seat {
    private final int blockId;
    private int seatNumber;
    private int row;
    private int column;
    private SeatType seatType;
    private Passenger passenger;

    /**
     * Instantiates a new Seat.
     *
     * @param blockId  the block id
     * @param row      the row
     * @param column   the column
     * @param seatType the seat type
     */
    public Seat(int blockId, int row, int column, SeatType seatType) {
        this.blockId = blockId;
        this.row = row;
        this.column = column;
        this.seatType = seatType;
    }

    /**
     * Gets seat number.
     *
     * @return the seat number
     */
    public int getSeatNumber() {
        return seatNumber;
    }

    /**
     * Gets row.
     *
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets column.
     *
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Gets seat type.
     *
     * @return the seat type
     */
    public SeatType getSeatType() {
        return seatType;
    }

    /**
     * Sets seat number.
     *
     * @param seatNumber the seat number
     */
    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    /**
     * Gets block id.
     *
     * @return the block id
     */
    public int getBlockId() {
        return blockId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Seat.class.getSimpleName() + "[", "]")
                .add("seatNumber=" + seatNumber)
                .add("blockId=" + blockId)
                .add("row=" + row)
                .add("column=" + column)
                .add("seatType=" + seatType)
                .toString();
    }

    /**
     * Sets passenger.
     *
     * @param passenger the passenger
     */
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    /**
     * Gets passenger.
     *
     * @return the passenger
     */
    public Passenger getPassenger() {
        return passenger;
    }
}
