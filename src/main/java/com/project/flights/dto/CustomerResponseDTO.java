package com.project.flights.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private FlightDTO flight;
    private List<SeatDTO> bookedSeats;
}