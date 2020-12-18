package com.squareshift.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SeatBlockTest {

    private SeatBlock seatBlockUnderTest;

    @BeforeEach
    void setUp() {
        seatBlockUnderTest = new SeatBlock(1,5, 3);
    }

    @Test
    void testGetNumberOfSeats() {
        // Setup

        // Run the test
        final int result = seatBlockUnderTest.getNumberOfSeats();

        // Verify the results
        assertEquals(15, result);
    }

    @Test
    void testGetNumberOfAileSeats() {
        // Setup
        seatBlockUnderTest.setPosition(Position.LEFT);
        seatBlockUnderTest.initSeats();
        // Run the test
        final int result1 = seatBlockUnderTest.getNumberOfAileSeats();

        seatBlockUnderTest.setPosition(Position.RIGHT);
        seatBlockUnderTest.initSeats();
        final int result2 = seatBlockUnderTest.getNumberOfAileSeats();

        seatBlockUnderTest.setPosition(Position.CENTER);
        seatBlockUnderTest.initSeats();
        final int result3 = seatBlockUnderTest.getNumberOfAileSeats();

        // Verify the results
        assertEquals(3, result1);
        assertEquals(3, result2);
        assertEquals(6, result3);
    }

    @Test
    void testGetNumberOfWindowSeats() {
        // Setup
        seatBlockUnderTest.setPosition(Position.LEFT);
        seatBlockUnderTest.initSeats();
        // Run the test
        final int result1 = seatBlockUnderTest.getNumberOfWindowSeats();

        seatBlockUnderTest.setPosition(Position.RIGHT);
        seatBlockUnderTest.initSeats();
        final int result2 = seatBlockUnderTest.getNumberOfWindowSeats();

        seatBlockUnderTest.setPosition(Position.CENTER);
        seatBlockUnderTest.initSeats();
        final int result3 = seatBlockUnderTest.getNumberOfWindowSeats();

        // Verify the results
        assertEquals(3, result1);
        assertEquals(3, result2);
        assertEquals(0, result3);
    }

    @Test
    void testGetNumberOfCenterSeats() {
        // Setup
        seatBlockUnderTest.setPosition(Position.LEFT);
        seatBlockUnderTest.initSeats();
        // Run the test
        final int result1 = seatBlockUnderTest.getNumberOfCenterSeats();

        seatBlockUnderTest.setPosition(Position.RIGHT);
        seatBlockUnderTest.initSeats();
        final int result2 = seatBlockUnderTest.getNumberOfCenterSeats();

        seatBlockUnderTest.setPosition(Position.CENTER);
        seatBlockUnderTest.initSeats();
        final int result3 = seatBlockUnderTest.getNumberOfCenterSeats();

        // Verify the results
        assertEquals(9, result1);
        assertEquals(9, result2);
        assertEquals(9, result3);
    }

}
