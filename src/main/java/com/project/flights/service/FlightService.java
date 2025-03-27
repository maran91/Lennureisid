package com.project.flights.service;

import com.project.flights.model.Flight;
import com.project.flights.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> findByFilters(
            String departure,
            LocalDate departureDate,
            int priceMin,
            int priceMax,
            Duration durationMin,
            Duration durationMax
    ) {
        LocalDateTime startOfDay = departureDate.atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);

        return flightRepository.findByDepartureAndDepartureTimeBetweenAndPriceBetweenAndFlightDurationBetween(
                departure,
                startOfDay,
                endOfDay,
                priceMin,
                priceMax,
                durationMin,
                durationMax
        );
    }
}
