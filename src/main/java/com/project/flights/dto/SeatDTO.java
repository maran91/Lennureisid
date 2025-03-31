package com.project.flights.dto;

import lombok.Data;

@Data
public class SeatDTO {
    private String rowName;
    private int seatNumber;
    private boolean windowSeat;
    private boolean extraLegRoom;
    private boolean nearExit;
}