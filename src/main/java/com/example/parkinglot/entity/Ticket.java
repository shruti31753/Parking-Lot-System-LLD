package com.example.parkinglot.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @OneToOne
    @JoinColumn(name = "spot_id")
    private ParkingSpot parkingSpot;

    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;
}