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
            @RequestParam() String departure,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
            @RequestParam() Duration durationFrom,
            @RequestParam() Duration durationTo,
            @RequestParam() int priceMin,
            @RequestParam() int priceMax
    ) {
        List<Flight> filteredFlights = flightService.findByFilters(
                departure,
                departureDate,
                priceMin,
                priceMax,
                durationFrom,
                durationTo
        );
        return ResponseEntity.ok(filteredFlights);
    }
}
