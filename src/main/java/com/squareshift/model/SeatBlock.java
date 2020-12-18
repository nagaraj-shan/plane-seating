package com.squareshift.model;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * The type Seat block.
 */
public class SeatBlock {
    private final Seat[][] seats;
    private final int rows;
    private final int columns;
    private final int blockId;
    private Position position = Position.CENTER;

    /**
     * Instantiates a new Seat block.
     *
     * @param blockId the block id
     * @param rows    the rows
     * @param columns the columns
     */
    public SeatBlock(int blockId, int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.blockId = blockId;
        this.seats = new Seat[rows][columns];
    }

    /**
     * Init seats.
     */
    void initSeats() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                SeatType type = SeatType.CENTER;
                if ((position == Position.LEFT && j == 0) || (position == Position.RIGHT && j == columns - 1)) {
                    type = SeatType.WINDOW;
                } else if ((position == Position.CENTER && (j == 0 || j == columns - 1)) || (rows > 1 && ((position == Position.LEFT && j == columns - 1) || (position == Position.RIGHT && j == 0)))) {
                    type = SeatType.AILE;
                }
                this.seats[i][j] = new Seat(blockId, i, j, type);
            }
        }
    }

    /**
     * Gets number of seats.
     *
     * @return the number of seats
     */
    public int getNumberOfSeats() {
        return rows * columns;
    }

    /**
     * Gets number of aile seats.
     *
     * @return the number of aile seats
     */
    public int getNumberOfAileSeats() {
        if (position == Position.CENTER) {
            return rows > 2 ? this.columns * 2 : this.columns * rows;
        } else {
            return rows > 1 ? this.columns : 0;
        }
    }

    /**
     * Gets number of window seats.
     *
     * @return the number of window seats
     */
    public int getNumberOfWindowSeats() {
        return position != Position.CENTER ? columns : 0;
    }

    /**
     * Gets number of center seats.
     *
     * @return the number of center seats
     */
    public int getNumberOfCenterSeats() {
        return getNumberOfSeats() - (getNumberOfAileSeats() + getNumberOfWindowSeats());
    }

    /**
     * Sets position.
     *
     * @param position the position
     */
    void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Get seats seat [ ] [ ].
     *
     * @return the seat [ ] [ ]
     */
    public Seat[][] getSeats() {
        return seats;
    }

    /**
     * Gets rows.
     *
     * @return the rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets columns.
     *
     * @return the columns
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SeatBlock.class.getSimpleName() + "[", "]")
                .add("seats=" + Arrays.toString(seats))
                .add("rows=" + rows)
                .add("columns=" + columns)
                .add("position=" + position)
                .toString();
    }

    /**
     * Print string.
     *
     * @return the string
     */
    public String print() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                builder.append(seats[i][j].getSeatType().getValue());
                builder.append(seats[i][j].getSeatNumber());
                builder.append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
