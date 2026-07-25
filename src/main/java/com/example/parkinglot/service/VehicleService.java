package com.example.parkinglot.service;

import com.example.parkinglot.entity.Vehicle;
import com.example.parkinglot.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    // Find Vehicle
    public Vehicle findVehicle(String vehicleNumber) {

        return vehicleRepository
                .findByVehicleNumber(vehicleNumber)
                .orElseThrow(() ->
                        new RuntimeException("Vehicle Not Found"));
    }

    // Get All Vehicles
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Delete Vehicle
    public void deleteVehicle(String vehicleNumber) {

        Vehicle vehicle = findVehicle(vehicleNumber);

        vehicleRepository.delete(vehicle);
    }
}