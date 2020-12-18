package com.squareshift.model;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * The type Plane.
 */
public final class Plane {
    private int id;
    private String name;
    private final AtomicInteger filledSeats;
    private final int totalSeats;
    private final List<Seat> seats;
    /**
     * The Seat blocks.
     */
    SeatBlock[] seatBlocks;

    private Plane(SeatBlock[] groups, SeatingPlan seatingPlan) {
        this.seatBlocks = groups;
        this.seats = seatingPlan.assignSeatNumbers(this);
        this.totalSeats = getNumberOfSeats();
        this.filledSeats = new AtomicInteger(0);
    }

    /**
     * Gets number of seats.
     *
     * @return the number of seats
     */
    public int getNumberOfSeats() {
        return Arrays.stream(seatBlocks).mapToInt(SeatBlock::getNumberOfSeats).sum();
    }

    /**
     * Gets total aile seats.
     *
     * @return the total aile seats
     */
    public int getTotalAileSeats() {
        return Arrays.stream(seatBlocks).mapToInt(SeatBlock::getNumberOfAileSeats).sum();
    }

    /**
     * Gets total window seats.
     *
     * @return the total window seats
     */
    public int getTotalWindowSeats() {
        return Arrays.stream(seatBlocks).mapToInt(SeatBlock::getNumberOfWindowSeats).sum();
    }

    /**
     * Gets total center seats.
     *
     * @return the total center seats
     */
    public int getTotalCenterSeats() {
        return Arrays.stream(seatBlocks).mapToInt(SeatBlock::getNumberOfCenterSeats).sum();
    }

    /**
     * Gets available seats.
     *
     * @return the available seats
     */
    public int getAvailableSeats() {
        return totalSeats - filledSeats.get();
    }

    /**
     * Book seat.
     *
     * @param numberOfPassenger the number of passenger
     */
    public void bookSeat(int numberOfPassenger) {
        validate(numberOfPassenger);
        IntStream.range(1, numberOfPassenger + 1).forEach(value -> book(new Passenger(value)));
    }


    /**
     * Book seat seat.
     *
     * @param passenger the passenger
     * @return the seat
     */
    public Seat bookSeat(Passenger passenger) {
        validate(1);
        return book(passenger);
    }

    private void validate(int numberOfPassenger) {
        if (filledSeats.get() >= totalSeats) {
            throw new RuntimeException("All Seats Booked");
        }
        if (filledSeats.get() + numberOfPassenger > getTotalSeats()) {
            throw new RuntimeException("Requested Seat Not available");
        }
    }

    private Seat book(Passenger passenger) {
        Seat seat = seats.get(filledSeats.get());
        seat.setPassenger(passenger);
        filledSeats.incrementAndGet();
        return seat;
    }

    private Seat getNextAvailableSeat() {
        return null;
    }

    /**
     * Get seat blocks seat block [ ].
     *
     * @return the seat block [ ]
     */
    public SeatBlock[] getSeatBlocks() {
        return seatBlocks;
    }

    /**
     * Gets total seats.
     *
     * @return the total seats
     */
    public int getTotalSeats() {
        return totalSeats;
    }

    /**
     * New builder builder.
     *
     * @return the builder
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * The type Builder.
     */
    public static final class Builder {
        private int id;
        private String name;
        private List<SeatBlock> seatBlocks;
        private SeatingPlan seatingPlan = new AWCSeatPlan();

        private Builder() {
            seatBlocks = new ArrayList<>();
        }

        /**
         * Add seat group builder.
         *
         * @param rows    the rows
         * @param columns the columns
         * @return the builder
         */
        public Builder addSeatGroup(int rows, int columns) {
            this.seatBlocks.add(new SeatBlock(seatBlocks.size() + 1, rows, columns));
            return this;
        }

        /**
         * Add seat groups builder.
         *
         * @param seatsMap the seats map
         * @return the builder
         */
        public Builder addSeatGroups(int[][] seatsMap) {
            Arrays.stream(seatsMap).forEach(rc -> addSeatGroup(rc[1], rc[0]));
            return this;
        }

        /**
         * With seating plan builder.
         *
         * @param seatingPlan the seating plan
         * @return the builder
         */
        public Builder withSeatingPlan(SeatingPlan seatingPlan) {
            this.seatingPlan = seatingPlan;
            return this;
        }

        /**
         * With id builder.
         *
         * @param id the id
         * @return the builder
         */
        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        /**
         * With name builder.
         *
         * @param name the name
         * @return the builder
         */
        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Build plane.
         *
         * @return the plane
         */
        public Plane build() {
            if (CollectionUtils.isEmpty(seatBlocks)) {
                throw new RuntimeException("Plane without Seat Group is not possible");
            }
            this.seatBlocks.get(0).setPosition(Position.LEFT);
            this.seatBlocks.get(seatBlocks.size() - 1).setPosition(Position.RIGHT);
            this.seatBlocks.forEach(SeatBlock::initSeats);
            SeatBlock[] arr = seatBlocks.toArray(new SeatBlock[0]);
            Plane plane = new Plane(arr, seatingPlan);
            plane.name = this.name;
            plane.id = this.id;
            return plane;
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Plane.class.getSimpleName() + "[", "]")
                .add("filledSeats=" + filledSeats)
                .add("totalSeats=" + totalSeats)
                .add("seatBlocks=" + Arrays.toString(seatBlocks))
                .toString();
    }

    /**
     * Print seats.
     */
    public void printSeats() {
        String seatMap = seatBlocks[0].print();
        for (int i = 1; i < seatBlocks.length; i++) {
            seatMap = concatenateHorizontally(seatMap, seatBlocks[i].print(), " | | ");
        }
        System.out.println(seatMap);
    }


    private static String concatenateHorizontally(final String left, final String right, String concat) {
        {
            String br = "\n";
            String[] lefts = left.split(br);
            String[] rights = right.split(br);
            int lmaxLen = Arrays.stream(lefts).mapToInt(String::length).max().orElse(0);
            int rmaxLen = Arrays.stream(rights).mapToInt(String::length).max().orElse(0);
            int max = Math.max(lefts.length, rights.length);
            StringBuilder sB = new StringBuilder();
            for (int i = 0; i < max; i++) {

                if (i < lefts.length) {
                    sB.append(lefts[i]);
                    sB.append(repeat(" ", lmaxLen - lefts[i].length()));
                } else {
                    sB.append(repeat(" ", lmaxLen));

                }
                sB.append(concat);
                if (i < rights.length) {
                    sB.append(rights[i]);
                    sB.append(repeat(" ", rmaxLen - rights[i].length()));
                } else {
                    sB.append(repeat(" ", rights[0].length()));
                }

                sB.append(br);
            }

            return sB.toString();
        }
    }

    /**
     * Repeat string.
     *
     * @param str   the str
     * @param times the times
     * @return the string
     */
    public static String repeat(String str, int times) {
        return Stream.generate(() -> str).limit(times).collect(Collectors.joining());
    }
}


