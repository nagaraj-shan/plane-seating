package com.squareshift.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AWCSeatPlanTest {

    private AWCSeatPlan awcSeatPlanUnderTest;

    @BeforeEach
    void setUp() {
        awcSeatPlanUnderTest = new AWCSeatPlan();
    }

    @Test
    void testAssignSeatNumbers() {
        // Setup
        final Plane plane = Plane.newBuilder().addSeatGroups(new int[][]{{3, 2}, {4, 3}, {2, 3}, {3, 4}})
                                 .withSeatingPlan(awcSeatPlanUnderTest).build();

        // Run the test
        final List<Seat> result = awcSeatPlanUnderTest.assignSeatNumbers(plane);

        // Verify the results
        int c = 0;
        int s=1;
        verifyTheSeat(result.get(c++), 1,0, 2,  SeatType.AILE, s++);
        verifyTheSeat(result.get(c++), 2,0, 0,  SeatType.AILE, s++);
        verifyTheSeat(result.get(c++), 2,0, 3,  SeatType.AILE, s++);
        verifyTheSeat(result.get(c++), 3,0, 0,  SeatType.AILE, s++);
        verifyTheSeat(result.get(c++), 3,0, 1,  SeatType.AILE, s++);
        verifyTheSeat(result.get(c++), 4,0, 0,  SeatType.AILE, s++);
        verifyTheSeat(result.get(c++), 1,1, 2,  SeatType.AILE, s++);
        verifyTheSeat(result.get(c++), 2,1, 0,  SeatType.AILE, s++);
        verifyTheSeat(result.get(c++), 2,1, 3,  SeatType.AILE, s++);
        verifyTheSeat(result.get(c++), 3,1, 0,  SeatType.AILE, s++);
        verifyTheSeat(result.get(c++), 3,1, 1,  SeatType.AILE, s++);
        verifyTheSeat(result.get(c++), 4,1, 0,  SeatType.AILE, s++);
        verifyTheSeat(result.get(c++), 2,2, 0,  SeatType.AILE, s++);
        verifyTheSeat(result.get(c++), 2,2, 3,  SeatType.AILE, s++);
        verifyTheSeat(result.get(c++), 3,2, 0,  SeatType.AILE, s++);
        verifyTheSeat(result.get(c++), 3,2, 1,  SeatType.AILE, s++);
        verifyTheSeat(result.get(c++), 4,2, 0,  SeatType.AILE, s++);
        verifyTheSeat(result.get(c++), 4 ,3, 0,  SeatType.AILE, s++);
        verifyTheSeat(result.get(c++), 1,0, 0,  SeatType.WINDOW, s++);
        verifyTheSeat(result.get(c++), 4,0, 2,  SeatType.WINDOW, s++);
        verifyTheSeat(result.get(c++), 1,1, 0,  SeatType.WINDOW, s++);
        verifyTheSeat(result.get(c++), 4,1, 2,  SeatType.WINDOW, s++);
        verifyTheSeat(result.get(c++), 4,2, 2,  SeatType.WINDOW, s++);
        verifyTheSeat(result.get(c++), 4,3, 2,  SeatType.WINDOW, s++);
        verifyTheSeat(result.get(c++), 1,0, 1,  SeatType.CENTER, s++);
        verifyTheSeat(result.get(c++), 2,0, 1,  SeatType.CENTER, s++);
        verifyTheSeat(result.get(c++), 2,0, 2,  SeatType.CENTER, s++);
        verifyTheSeat(result.get(c++), 4,0, 1,  SeatType.CENTER, s++);
        verifyTheSeat(result.get(c++), 1,1, 1,  SeatType.CENTER, s++);
        verifyTheSeat(result.get(c++), 2,1, 1,  SeatType.CENTER, s++);
        verifyTheSeat(result.get(c++), 2,1, 2,  SeatType.CENTER, s++);
        verifyTheSeat(result.get(c++), 4,1, 1,  SeatType.CENTER, s++);
        verifyTheSeat(result.get(c++), 2,2, 1,  SeatType.CENTER, s++);
        verifyTheSeat(result.get(c++), 2,2, 2,  SeatType.CENTER, s++);
        verifyTheSeat(result.get(c++), 4,2, 1,  SeatType.CENTER, s++);
        verifyTheSeat(result.get(c), 4,3,1, SeatType.CENTER, s);
    }

    private void verifyTheSeat(Seat seat, int blockId,int row, int column, SeatType seatType, int seatNumber) {
        assertEquals(blockId, seat.getBlockId());
        assertEquals(row, seat.getRow());
        assertEquals(column, seat.getColumn());
        assertEquals(seatType, seat.getSeatType());
        assertEquals(seatNumber, seat.getSeatNumber());
    }
}
