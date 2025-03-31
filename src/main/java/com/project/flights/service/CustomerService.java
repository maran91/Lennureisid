package com.project.flights.service;

import com.project.flights.dto.CustomerBookingRequest;
import com.project.flights.dto.CustomerResponseDTO;
import com.project.flights.dto.FlightDTO;
import com.project.flights.dto.SeatDTO;
import com.project.flights.mapper.FlightMapper;
import com.project.flights.model.Customer;
import com.project.flights.model.Flight;
import com.project.flights.model.FlightSeat;
import com.project.flights.repository.CustomerRepository;
import com.project.flights.repository.FlightRepository;
import com.project.flights.repository.FlightSeatRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FlightRepository flightRepository;
    private final FlightSeatRepository flightSeatRepository;

    public CustomerService(CustomerRepository customerRepository, FlightRepository flightRepository, FlightSeatRepository flightSeatRepository) {
        this.customerRepository = customerRepository;
        this.flightRepository = flightRepository;
        this.flightSeatRepository = flightSeatRepository;
    }

    public CustomerResponseDTO mapToDTO(Customer customer) {
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setId(customer.getId());
        customerResponseDTO.setFirstName(customer.getFirstName());
        customerResponseDTO.setLastName(customer.getLastName());
        customerResponseDTO.setEmail(customer.getEmail());

        FlightDTO flightDTO = FlightMapper.toDTO(customer.getFlight());
        customerResponseDTO.setFlight(flightDTO);

        List<SeatDTO> seats = customer.getBookedSeats().stream().map(flightSeat -> {
            SeatDTO seatDTO = new SeatDTO();
            seatDTO.setRowName(flightSeat.getSeat().getRowName());
            seatDTO.setSeatNumber(flightSeat.getSeat().getSeatNumber());
            seatDTO.setWindowSeat(flightSeat.getSeat().isWindowSeat());
            seatDTO.setExtraLegRoom(flightSeat.getSeat().isExtraLegRoom());
            seatDTO.setNearExit(flightSeat.getSeat().isNearExit());
            return seatDTO;
        }).toList();

        customerResponseDTO.setBookedSeats(seats);

        return customerResponseDTO;
    }

    public CustomerResponseDTO bookCustomer(CustomerBookingRequest request) {
        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setFlight(flight);
        Customer savedCustomer = customerRepository.save(customer);

        List<FlightSeat> seatsToBook = new ArrayList<>();
        for (Long seatId : request.getSeatIds()) {
            FlightSeat seat = flightSeatRepository.findById(seatId)
                    .orElseThrow(() -> new RuntimeException("Seat " + seatId + " not found"));

            if (seat.isBooked()) {
                throw new IllegalStateException("Seat " + seat.getSeat().getRowName() + seat.getSeat().getSeatNumber() + " is already booked.");
            }

            if (!seat.getFlight().getId().equals(request.getFlightId())) {
                throw new IllegalStateException("Seat " + seatId + " does not belong to flight " + request.getFlightId());
            }

            seat.setBooked(true);
            seat.setCustomer(savedCustomer);
            seatsToBook.add(seat);
        }

        flightSeatRepository.saveAll(seatsToBook);

        savedCustomer.setBookedSeats(seatsToBook);

        return mapToDTO(savedCustomer);
    }
}
