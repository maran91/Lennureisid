package com.project.flights.controller;

import com.project.flights.model.Flight;
import com.project.flights.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/flight")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<Flight>> findFlights(@RequestParam Map<String, String> filters) {
        List<Flight> results = flightService.findByDynamicFilters(filters);
        return ResponseEntity.ok(results);
    }

}
