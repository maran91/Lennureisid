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

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public List<Flight> findByFilters(String departure, LocalDate departureDate, int price, Duration flightDuration) {
        LocalDateTime startOfDay = null;
        LocalDateTime endOfDay = null;
        if (departureDate != null) {
            startOfDay = departureDate.atStartOfDay();
            endOfDay = startOfDay.plusDays(1);
        }
        if (departure != null & departureDate != null & price > 0 & flightDuration != null) {
            return flightRepository.findByDepartureAndPriceAndFlightDurationAndDepartureTimeBetween(departure, price, flightDuration, startOfDay, endOfDay);
        } else if (departure != null) {
            return flightRepository.findByDeparture(departure);
        } else if (departureDate != null) {
            return flightRepository.findByDepartureTimeBetween(startOfDay, endOfDay);
        } else if (price > 0) {
            return flightRepository.findByPrice(price);
        } else if (flightDuration != null) {
            return flightRepository.findByFlightDuration(flightDuration);
        } else {
            return flightRepository.findAll();
        }
    }

}
