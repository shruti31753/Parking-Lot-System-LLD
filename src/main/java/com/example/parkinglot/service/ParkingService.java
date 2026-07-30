package com.example.parkinglot.service;


import com.example.parkinglot.dto.ParkVehicleRequest;
import com.example.parkinglot.dto.ParkingResponse;
import com.example.parkinglot.entity.*;
import com.example.parkinglot.manager.ParkingSpotManager;
import com.example.parkinglot.repository.TicketRepository;
import com.example.parkinglot.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class ParkingService {


    @Autowired
    private VehicleRepository vehicleRepository;


    @Autowired
    private TicketRepository ticketRepository;



    @Autowired
    private ParkingSpotManager parkingSpotManager;




    public ParkingResponse parkVehicle(
            ParkVehicleRequest request
    ){


        Vehicle vehicle =
                new Vehicle();


        vehicle.setVehicleNumber(
                request.getVehicleNumber()
        );


        vehicle.setVehicleType(
                request.getVehicleType()
        );


        vehicleRepository.save(vehicle);


        ParkingSpot spot =
                parkingSpotManager.allocateSpot(
                        request.getSpotType()
                );


        spot.setVehicle(vehicle);


        Ticket ticket =
                new Ticket();


        ticket.setVehicle(vehicle);

        ticket.setParkingSpot(spot);

        ticket.setEntryTime(
                LocalDateTime.now()
        );


        ticket.setStatus(
                TicketStatus.ACTIVE
        );


        ticketRepository.save(ticket);

        ParkingResponse response =
                new ParkingResponse();


        response.setTicketId(
                ticket.getId()
        );


        response.setSpotNumber(
                spot.getSpotNumber()
        );


        response.setMessage(
                "Vehicle parked successfully"
        );


        return response;

    }

}