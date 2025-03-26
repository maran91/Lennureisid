package com.project.flights.config;

import com.project.flights.model.Flight;
import com.project.flights.repository.FlightRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedFlights(FlightRepository flightRepository) {
        return args -> {
            if (flightRepository.count() == 0) {
                List<Flight> flights = List.of(
                        new Flight("London", "Paris",
                                LocalDateTime.of(2025, 4, 2, 14, 0),
                                LocalDateTime.of(2025, 4, 2, 15, 30),
                                99),

                        new Flight("Berlin", "Rome",
                                LocalDateTime.of(2025, 4, 3, 9, 15),
                                LocalDateTime.of(2025, 4, 3, 11, 0),
                                120),

                        new Flight("Madrid", "Barcelona",
                                LocalDateTime.of(2025, 4, 4, 11, 45),
                                LocalDateTime.of(2025, 4, 4, 12, 55),
                                85),

                        new Flight("Amsterdam", "Brussels",
                                LocalDateTime.of(2025, 4, 5, 16, 15),
                                LocalDateTime.of(2025, 4, 5, 17, 0),
                                70),

                        new Flight("Vienna", "Prague",
                                LocalDateTime.of(2025, 4, 6, 8, 0),
                                LocalDateTime.of(2025, 4, 6, 9, 20),
                                90),

                        new Flight("Copenhagen", "Stockholm",
                                LocalDateTime.of(2025, 4, 7, 10, 30),
                                LocalDateTime.of(2025, 4, 7, 12, 0),
                                110),

                        new Flight("Oslo", "Helsinki",
                                LocalDateTime.of(2025, 4, 8, 13, 45),
                                LocalDateTime.of(2025, 4, 8, 15, 30),
                                105),

                        new Flight("Warsaw", "Budapest",
                                LocalDateTime.of(2025, 4, 9, 15, 0),
                                LocalDateTime.of(2025, 4, 9, 17, 10),
                                95),

                        new Flight("Lisbon", "Madrid",
                                LocalDateTime.of(2025, 4, 10, 9, 0),
                                LocalDateTime.of(2025, 4, 10, 10, 30),
                                88),

                        new Flight("Zurich", "Vienna",
                                LocalDateTime.of(2025, 4, 11, 12, 15),
                                LocalDateTime.of(2025, 4, 11, 13, 50),
                                100),

                        new Flight("Dublin", "Amsterdam",
                                LocalDateTime.of(2025, 4, 12, 7, 30),
                                LocalDateTime.of(2025, 4, 12, 10, 0),
                                115),

                        new Flight("Athens", "Rome",
                                LocalDateTime.of(2025, 4, 13, 18, 10),
                                LocalDateTime.of(2025, 4, 13, 20, 0),
                                125)
                );
                flightRepository.saveAll(flights);
            }
        };
    }
}
