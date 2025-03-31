package com.project.flights.service;

import com.project.flights.model.FlightSeat;
import com.project.flights.repository.FlightSeatRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

@Service
public class FlightSeatService {

    private final FlightSeatRepository flightSeatRepository;

    public FlightSeatService(FlightSeatRepository flightSeatRepository) {
        this.flightSeatRepository = flightSeatRepository;
    }
    public List<FlightSeat> findAllByFlightId(Long flightId) {
        return flightSeatRepository.findByFlightId(flightId);
    }

    public List<FlightSeat> findByFilters(Map<String, String> filters) {
        Specification<FlightSeat> spec = Specification.where(null);

        if (filters.containsKey("flightId")) {
            Long flightId = Long.parseLong(filters.get("flightId"));
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("flight").get("id"), flightId));
        }

        if (filters.containsKey("windowSeat")) {
            boolean value = Boolean.parseBoolean(filters.get("windowSeat"));
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("seat").get("windowSeat"), value));
        }

        if (filters.containsKey("extraLegRoom")) {
            boolean value = Boolean.parseBoolean(filters.get("extraLegRoom"));
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("seat").get("extraLegRoom"), value));
        }

        if (filters.containsKey("nearExit")) {
            boolean value = Boolean.parseBoolean(filters.get("nearExit"));
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("seat").get("nearExit"), value));
        }

        spec = spec.and((root, query, cb) ->
                cb.isFalse(root.get("isBooked")));

        int amount = filters.containsKey("amount") ? Integer.parseInt(filters.get("amount")) : Integer.MAX_VALUE;
        Pageable pageable = PageRequest.of(0, amount, Sort.by("seat.seatNumber").ascending());

        return flightSeatRepository.findAll(spec, pageable).getContent();
    }
}