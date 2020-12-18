package com.squareshift.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type Awc seat plan.
 */
public class AWCSeatPlan implements SeatingPlan {
    @Override
    public List<Seat> assignSeatNumbers(Plane plane) {
        List<Seat> seats = Arrays.stream(plane.getSeatBlocks())
                                 .flatMap((Function<SeatBlock, Stream<Seat>>) seatBlock -> Arrays
                                         .stream(seatBlock.getSeats())
                                         .flatMap((Function<Seat[], Stream<Seat>>) Arrays::stream))
                                 .collect(Collectors.toList());
        Comparator<Seat> seatComparator = Comparator.comparing(Seat::getRow);
        List<Seat> aileSeats = seats.stream().filter(seat -> seat.getSeatType() == SeatType.AILE).sorted(seatComparator)
                                    .collect(Collectors.toList());

        List<Seat> windowSeats = seats.stream().filter(seat -> seat.getSeatType() == SeatType.WINDOW)
                                      .sorted(seatComparator)
                                      .collect(Collectors.toList());

        List<Seat> centerSeats = seats.stream().filter(seat -> seat.getSeatType() == SeatType.CENTER)
                                      .sorted(seatComparator)
                                      .collect(Collectors.toList());
        List<Seat> seatList = Stream.of(aileSeats, windowSeats, centerSeats).flatMap(Collection::stream)
                                    .collect(Collectors.toList());
        for (int i = 0; i < seatList.size(); i++) {
            seatList.get(i).setSeatNumber(i+1);
        }
        return seatList;
    }
}
