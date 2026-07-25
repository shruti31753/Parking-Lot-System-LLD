package com.example.parkinglot.controller;

import com.example.parkinglot.entity.Vehicle;
import com.example.parkinglot.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;


    @GetMapping("/{vehicleNumber}")
    public Vehicle getVehicle(@PathVariable String vehicleNumber) {
        return vehicleService.findVehicle(vehicleNumber);
    }


    @GetMapping("/all")
    public Object getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @DeleteMapping("/{vehicleNumber}")
    public String deleteVehicle(@PathVariable String vehicleNumber) {
        vehicleService.deleteVehicle(vehicleNumber);
        return "Vehicle Removed Successfully";
    }
}