package com.project.flights.config;

import com.project.flights.model.Customer;
import com.project.flights.model.Flight;
import com.project.flights.model.FlightSeat;
import com.project.flights.model.Seat;
import com.project.flights.repository.AirplaneRepository;
import com.project.flights.repository.CustomerRepository;
import com.project.flights.repository.FlightRepository;
import com.project.flights.repository.FlightSeatRepository;
import jakarta.transaction.Transactional;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Configuration
public class FlightSeeder {

    @Bean
    @Transactional
    CommandLineRunner seedFlights(
            FlightRepository flightRepository,
            AirplaneRepository airplaneRepository,
            FlightSeatRepository flightSeatRepository,
            CustomerRepository customerRepository

    ) {
        return CommandLineRunner -> {
            if (flightRepository.count() > 0) return;

            var airplane = airplaneRepository.findByModel("EMBRAER E190JET")
                    .orElseThrow(() -> new IllegalStateException("Airplane model has not been seeded"));

            var seats = airplane.getSeats();
            if (seats == null || seats.isEmpty()) throw new IllegalStateException("No seats found in airplane");

            Faker faker = new Faker();
            Random random = new Random();
            List<String> departureCapitals = List.of("London", "Helsinki", "Riga", "Tallinn", "Berlin", "Vilnius", "Tartu", "Paris");

            for (int i = 0; i < 50; i++) {
                String departure = departureCapitals.get(random.nextInt(departureCapitals.size()));
                String destination = faker.country().capital();
                LocalDateTime departureTime = LocalDateTime.now().plusDays(random.nextInt(7)).plusHours(random.nextInt(24));
                LocalDateTime arrivalTime = departureTime.plusHours(random.nextInt(3) + 1).plusMinutes(random.nextInt(60));

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

                for (Seat seat : seats) {
                    FlightSeat flightSeat = new FlightSeat();
                    flightSeat.setFlight(flight);
                    flightSeat.setSeat(seat);

                    if (random.nextInt(100) < 24) {
                        Customer customer = new Customer();
                        customer.setFirstName(faker.name().firstName());
                        customer.setLastName(faker.name().lastName());
                        customer.setEmail(faker.internet().emailAddress());
                        customer.setFlight(flight);
                        customer = customerRepository.save(customer);

                        flightSeat.setBooked(true);
                        flightSeat.setCustomer(customer);
                    } else {

                        flightSeat.setBooked(false);
                    }
                    flightSeatRepository.save(flightSeat);
                }
            }

            System.out.println("50 flights with flight seats and fake customers inserted");
        };
    }
}
