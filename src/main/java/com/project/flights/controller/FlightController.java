package com.project.flights.controller;

import com.project.flights.dto.FlightDTO;
import com.project.flights.model.Flight;
import com.project.flights.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/flight")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> findById(@PathVariable("id") Long id) {
        Flight flight = flightService.findById(id);
        return ResponseEntity.ok(flight);
    }

    @GetMapping
    public ResponseEntity<List<FlightDTO>> findFlights(@RequestParam Map<String, String> filters) {
        List<Flight> results = flightService.findByDynamicFilters(filters);
        List<FlightDTO> dtoList = flightService.mapToDTOList(results);
        return ResponseEntity.ok(dtoList);
    }
}