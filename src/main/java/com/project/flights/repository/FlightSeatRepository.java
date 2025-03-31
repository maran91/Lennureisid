package com.project.flights.repository;

import com.project.flights.model.FlightSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface FlightSeatRepository extends JpaRepository<FlightSeat, Long>, JpaSpecificationExecutor<FlightSeat> {
    List<FlightSeat> findByFlightId(Long flightId);
}

