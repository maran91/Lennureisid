package com.project.flights.dto;

import com.project.flights.model.FlightSeat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SeatFilterResponseDTO {
    private boolean success;
    private String message;
    private List<FlightSeat> seats;
}
