package com.squareshift.dto;

public class PlaneDto {

    private int id;
    private String name;
    private String seatBlocks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeatBlocks() {
        return seatBlocks;
    }

    public void setSeatBlocks(String seatBlocks) {
        this.seatBlocks = seatBlocks;
    }
}
