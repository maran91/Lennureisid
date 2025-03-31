package com.project.flights.dto;

import lombok.Data;

@Data
public class FlightDTO {
    private Long id;
    private String departure;
    private String arrival;
    private String departureTime;
    private String arrivalTime;
    private int price;
    private String flightDuration;
}