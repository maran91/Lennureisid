package com.project.flights.controller;

import com.project.flights.model.Flight;
import com.project.flights.service.FlightService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<Flight>> findFlights(
            @RequestParam(required = false) String departure,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate departureDate,
            @RequestParam(required = false) Integer price,
            @RequestParam(required = false) Duration duration) {

        List<Flight> filteredFlights = flightService.findByFilters(departure, departureDate, price != null ? price : -1, duration);
        return ResponseEntity.ok(filteredFlights);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Flight flight) {
        System.out.println(flight);
        Flight savedFlight = flightService.saveFlight(flight);
        return ResponseEntity.ok(savedFlight);
    }
}
