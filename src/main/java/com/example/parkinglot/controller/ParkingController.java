package com.example.parkinglot.controller;

import com.example.parkinglot.dto.ParkVehicleRequest;
import com.example.parkinglot.dto.ParkingLotRequest;
import com.example.parkinglot.dto.ParkingResponse;
import com.example.parkinglot.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;


    @PostMapping("/create")
    public String createParkingLot(@RequestBody ParkingLotRequest request) {
        parkingService.createParkingLot(request);
        return "Parking Lot Created Successfully";
    }


    @PostMapping("/park")
    public ParkingResponse parkVehicle(@RequestBody ParkVehicleRequest request) {
        return parkingService.parkVehicle(request);
    }


    @GetMapping("/status")
    public Object getParkingStatus() {
        return parkingService.getParkingStatus();
    }


    @GetMapping("/available")
    public Object getAvailableSpots() {
        return parkingService.getAvailableSpots();
    }
}