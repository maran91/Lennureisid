package com.project.flights.controller;

import com.project.flights.dto.SeatFilterResponseDTO;
import com.project.flights.model.FlightSeat;
import com.project.flights.service.FlightSeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/flight-seats")
public class FlightSeatController {

    private final FlightSeatService flightSeatService;

    public FlightSeatController(FlightSeatService flightSeatService) {
        this.flightSeatService = flightSeatService;
    }

    @GetMapping
    public List<FlightSeat> findAllByFlightId(@RequestParam Long flightId) {
        return flightSeatService.findAllByFlightId(flightId);
    }

    @PostMapping("/filter")
    public ResponseEntity<SeatFilterResponseDTO> filterSeats(@RequestBody Map<String, String> filters) {
        List<FlightSeat> seats = flightSeatService.findByFilters(filters);
        int requestedAmount = Integer.parseInt(filters.getOrDefault("amount", "0"));

        if (seats.size() < requestedAmount) {
            SeatFilterResponseDTO response = new SeatFilterResponseDTO(
                    false,
                    "Not enough seats available. Requested: " + requestedAmount + ", Found: " + seats.size(),
                    seats
            );
            return ResponseEntity.ok(response);
        }

        SeatFilterResponseDTO response = new SeatFilterResponseDTO(true, "Seats found", seats);
        return ResponseEntity.ok(response);
    }
}