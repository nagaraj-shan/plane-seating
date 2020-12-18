package com.squareshift;

import com.squareshift.model.AWCSeatPlan;
import com.squareshift.model.Plane;

public class Main {

    public static void main(String[] args) {
        Plane plane = Plane.newBuilder().withSeatingPlan(new AWCSeatPlan())
                           .addSeatGroups(new int[][]{{3, 2}, {4, 3}, {2, 3}, {3, 4}}).build();
        System.out.println(plane);
        plane.printSeats();
    }
}
