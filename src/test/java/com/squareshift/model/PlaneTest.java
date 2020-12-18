package com.squareshift.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlaneTest {

    private Plane planeUnderTest;


    @Test
    void testGetNumberOfSeats() {
        // Setup
        planeUnderTest = Plane.newBuilder().addSeatGroups(new int[][]{{1,1}}).build();
        // Run the test
        final int result = planeUnderTest.getNumberOfSeats();

        // Verify the results
        assertEquals(1, result);
    }

    @Test
    void testGetTotalAileSeats() {
        // Setup
        planeUnderTest = Plane.newBuilder().addSeatGroups(new int[][]{{5,3},{4,4}}).build();
        // Run the test
        final int result = planeUnderTest.getTotalAileSeats();

        // Verify the results
        assertEquals(9, result);
    }

    @Test
    void testGetTotalWindowSeats() {
        // Setup
        planeUnderTest = Plane.newBuilder().addSeatGroups(new int[][]{{5,3},{4,4}}).build();
        // Run the test
        final int result = planeUnderTest.getTotalWindowSeats();

        // Verify the results
        assertEquals(9, result);
    }

    @Test
    void testGetTotalCenterSeats() {
        // Setup
        planeUnderTest = Plane.newBuilder().addSeatGroups(new int[][]{{5,3},{4,4},{4,2}}).build();
        // Run the test
        final int result = planeUnderTest.getTotalCenterSeats();

        // Verify the results
        assertEquals(13, result);
    }

    @Test
    void testBookSeat() {
        // Setup
        planeUnderTest = Plane.newBuilder().addSeatGroups(new int[][]{{5,3},{4,4},{4,2}}).build();
        // Run the test
        planeUnderTest.bookSeat(10);

        // Verify the results
        assertEquals(39, planeUnderTest.getTotalSeats());
        assertEquals(29, planeUnderTest.getAvailableSeats());
    }

    @Test
    void testBookSeat1() {
        // Setup
        final Passenger passenger = new Passenger(0);
        planeUnderTest = Plane.newBuilder().addSeatGroups(new int[][]{{5,3},{4,4},{4,2}}).build();

        // Run the test
        final Seat result = planeUnderTest.bookSeat(passenger);

        // Verify the results
        assertEquals(SeatType.AILE, result.getSeatType());

    }
}
