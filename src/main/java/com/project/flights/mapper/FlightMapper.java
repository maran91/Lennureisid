package com.project.flights.mapper;

import com.project.flights.dto.FlightDTO;
import com.project.flights.model.Flight;

public class FlightMapper {
    public static FlightDTO toDTO(Flight flight) {
        FlightDTO dto = new FlightDTO();
        dto.setId(flight.getId());
        dto.setDeparture(flight.getDeparture());
        dto.setArrival(flight.getArrival());
        dto.setDepartureTime(flight.getDepartureTime().toString());
        dto.setArrivalTime(flight.getArrivalTime().toString());
        dto.setPrice(flight.getPrice());
        dto.setFlightDuration(String.valueOf(flight.getFlightDuration()));
        return dto;
    }
}
