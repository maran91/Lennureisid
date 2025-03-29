package com.project.flights.service;

import com.project.flights.model.Flight;
import com.project.flights.repository.FlightRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> findByDynamicFilters(Map<String, String> filters) {
        Specification<Flight> spec = Specification.where(null);

        if (filters.containsKey("departureDate")) {
            LocalDate date = LocalDate.parse(filters.get("departureDate"));
            LocalDateTime startOfDay = date.atStartOfDay();
            LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

            if (filters.containsKey("departureDate")) {
                spec = spec.and((root, query, cb) ->
                        cb.between(root.get("departureTime"), startOfDay, endOfDay));
            }
        }

        if (filters.containsKey("departure")) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(cb.lower(root.get("departure")), filters.get("departure").toLowerCase()));
        }

        if (filters.containsKey("priceMin")) {
            spec = spec.and((root, query, cb) ->
                    cb.greaterThanOrEqualTo(root.get("price"), Integer.parseInt(filters.get("priceMin"))));
        }

        if (filters.containsKey("priceMax")) {
            spec = spec.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("price"), Integer.parseInt(filters.get("priceMax"))));
        }
        if (filters.containsKey("durationFrom")) {
            spec = spec.and((root, query, cb) ->
                    cb.greaterThanOrEqualTo(root.get("flightDuration"), Duration.parse(filters.get("durationFrom"))));
        }
        if (filters.containsKey("durationTo")) {
            spec = spec.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("flightDuration"), Duration.parse(filters.get("durationTo"))));
        }

        return flightRepository.findAll(spec);
    }
}
