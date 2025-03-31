package com.project.flights.config;

import com.project.flights.model.Airplane;
import com.project.flights.model.Seat;
import com.project.flights.repository.AirplaneRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AirplaneSeeder {

    private final AirplaneRepository airplaneRepository;

    public AirplaneSeeder(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    @PostConstruct
    public void seedAirplane() {
        if (airplaneRepository.findByModel("EMBRAER E190JET").isEmpty()) {
            Airplane airplane = new Airplane();
            airplane.setModel("EMBRAER E190JET");

            List<Seat> seats = new ArrayList<>();
            for (int i = 1; i <= 40; i++) {
                Seat seat = new Seat();
                seat.setSeatNumber(i);
                seat.setAirplane(airplane);
                seat.setRowName((i % 4 == 1) ? "A":(i % 4 == 3) ? "C" : (i % 4 == 2) ? "B"  : "D");
                seat.setWindowSeat(i % 4 == 1 || i % 4 == 0);
                seat.setExtraLegRoom(i <= 4);
                seat.setNearExit(i == 1 || i == 30);
                seats.add(seat);
            }

            airplane.setSeats(seats);
            airplaneRepository.save(airplane);
            System.out.println("Seeded airplane EMBRAER E190JET with 60 seats");
        } else {
            System.out.println("Airplane EMBRAER E190JET already exists");
        }
    }
}
