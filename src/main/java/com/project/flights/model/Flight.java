package com.project.flights.model;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String departure;
    @Column(nullable = false)
    private String arrival;
    @Column(nullable = false)
    private LocalDateTime departureTime;
    @Column(nullable = false)
    private LocalDateTime arrivalTime;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private Duration flightDuration;

    public Flight() {
    }

    public Flight(String departure, String arrival, LocalDateTime departureTime, LocalDateTime arrivalTime, int price) {
        this.departure = departure;
        this.arrival = arrival;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.flightDuration = Duration.between(departureTime, arrivalTime);
    }

    public Long getId() {
        return id;
    }

    public String getDeparture() {
        return departure;
    }

    public Duration getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(Duration flightDuration) {
        this.flightDuration = flightDuration;
    }

    public String getArrival() {
        return arrival;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public int getPrice() {
        return price;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
