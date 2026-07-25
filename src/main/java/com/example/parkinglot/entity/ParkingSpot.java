package com.example.parkinglot.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String spotNumber;

    @Enumerated(EnumType.STRING)
    private SpotType spotType;

    @Enumerated(EnumType.STRING)
    private SpotStatus status;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "floor_id")
    private ParkingFloor parkingFloor;
}