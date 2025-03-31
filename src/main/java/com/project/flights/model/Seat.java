package com.project.flights.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "airplane_id", nullable = false)
    @JsonIgnore
    private Airplane airplane;

    private String rowName;

    private Integer seatNumber;

    private boolean windowSeat;

    private boolean extraLegRoom;

    private boolean nearExit;

    public Seat() {
    }
}
