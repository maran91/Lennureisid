package com.project.flights.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerBookingRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Long flightId;
    private List<Long> seatIds;
}