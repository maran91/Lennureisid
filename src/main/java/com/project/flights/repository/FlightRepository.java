package com.project.flights.repository;

import com.project.flights.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureTimeBetween(LocalDateTime departureTime, LocalDateTime arrivalTime);

    List<Flight> findByPrice(int price);

    List<Flight> findByFlightDuration(Duration flightDuration);

    List<Flight> findByDeparture(String departure);

    List<Flight> findByDepartureAndPriceAndFlightDurationAndDepartureTimeBetween(String departure, int price, Duration flightDuration, LocalDateTime departureTimeAfter, LocalDateTime departureTimeBefore);

}
