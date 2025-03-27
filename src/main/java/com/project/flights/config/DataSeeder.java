package com.project.flights.config;

import com.project.flights.model.Flight;
import com.project.flights.repository.FlightRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedFlights(FlightRepository flightRepository) {
        return CommandLineRunner-> {
            if (flightRepository.count() > 0) return;

            Faker faker = new Faker();
            Random random = new Random();
            List<String> departureCapitals = List.of("London", "Helsinki", "Riga", "Tallinn", "Berlin", "Vilnius", "Tartu", "Paris");

            for (int i = 0; i < 500; i++) {
                String departure = departureCapitals.get(random.nextInt(departureCapitals.size()));
                String destination = faker.country().capital();
                LocalDateTime departureTime = LocalDateTime.now().plusDays(random.nextInt(30)).plusHours(random.nextInt(24));
                LocalDateTime arrivalTime = departureTime.plusHours(random.nextInt(3)+1).plusMinutes(random.nextInt(60));

                Duration duration = Duration.between(departureTime, arrivalTime);
                int price = 50 + random.nextInt(300);

                Flight flight = new Flight();
                flight.setDeparture(departure);
                flight.setArrival(destination);
                flight.setDepartureTime(departureTime);
                flight.setArrivalTime(arrivalTime);
                flight.setFlightDuration(duration);
                flight.setPrice(price);

                flightRepository.save(flight);
            }

            System.out.println("inserted 500 flights");
        };
    }
}
