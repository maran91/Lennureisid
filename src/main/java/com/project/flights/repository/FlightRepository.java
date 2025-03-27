package com.project.flights.repository;

import com.project.flights.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByDepartureAndDepartureTimeBetweenAndPriceBetweenAndFlightDurationBetween(
            String departure,
            LocalDateTime departureTimeAfter,
            LocalDateTime departureTimeBefore,
            int priceMin,
            int priceMax,
            Duration durationMin,
            Duration durationMax);
}
