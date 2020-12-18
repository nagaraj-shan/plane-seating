package com.squareshift.model;

import java.util.List;

/**
 * The interface Seating plan.
 */
public interface SeatingPlan {
    /**
     * Assign seat numbers list.
     *
     * @param plane the plane
     * @return the list
     */
    List<Seat> assignSeatNumbers(Plane plane);
}
