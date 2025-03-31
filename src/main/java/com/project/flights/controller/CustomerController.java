package com.project.flights.controller;

import com.project.flights.dto.CustomerBookingRequest;
import com.project.flights.dto.CustomerResponseDTO;
import com.project.flights.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody CustomerBookingRequest request) {
        CustomerResponseDTO responseDTO = customerService.bookCustomer(request);
        return ResponseEntity.ok(responseDTO);
    }
}