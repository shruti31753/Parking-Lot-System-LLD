package com.example.parkinglot.dto;

import com.example.parkinglot.entity.SpotType;
import com.example.parkinglot.entity.VehicleType;
import lombok.Data;

@Data
public class ParkVehicleRequest {

    private String vehicleNumber;

    private VehicleType vehicleType;

    private SpotType spotType;
}