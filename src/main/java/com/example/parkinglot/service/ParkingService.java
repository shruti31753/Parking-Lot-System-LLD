package com.example.parkinglot.service;

import com.example.parkinglot.dto.ParkVehicleRequest;
import com.example.parkinglot.dto.ParkingLotRequest;
import com.example.parkinglot.dto.ParkingResponse;
import com.example.parkinglot.entity.*;
import com.example.parkinglot.repository.ParkingLotRepository;
import com.example.parkinglot.repository.ParkingSpotRepository;
import com.example.parkinglot.repository.TicketRepository;
import com.example.parkinglot.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private TicketRepository ticketRepository;


    public void createParkingLot(ParkingLotRequest request) {

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName(request.getName());
        parkingLot.setAddress(request.getAddress());

        parkingLotRepository.save(parkingLot);
    }


    public ParkingResponse parkVehicle(ParkVehicleRequest request) {

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber(request.getVehicleNumber());
        vehicle.setVehicleType(request.getVehicleType());

        vehicleRepository.save(vehicle);

        ParkingSpot spot = parkingSpotRepository
                .findFirstByStatusAndSpotType(
                        SpotStatus.AVAILABLE,
                        request.getSpotType())
                .orElseThrow(() ->
                        new RuntimeException("No Parking Spot Available"));

        spot.setStatus(SpotStatus.OCCUPIED);
        spot.setVehicle(vehicle);

        parkingSpotRepository.save(spot);

        Ticket ticket = new Ticket();
        ticket.setVehicle(vehicle);
        ticket.setParkingSpot(spot);
        ticket.setEntryTime(LocalDateTime.now());
        ticket.setStatus(TicketStatus.ACTIVE);

        ticketRepository.save(ticket);

        ParkingResponse response = new ParkingResponse();
        response.setTicketId(ticket.getId());
        response.setSpotNumber(spot.getSpotNumber());
        response.setMessage("Vehicle Parked Successfully");

        return response;
    }


    public List<ParkingSpot> getParkingStatus() {
        return parkingSpotRepository.findAll();
    }


    public List<ParkingSpot> getAvailableSpots() {
        return parkingSpotRepository.findByStatus(SpotStatus.AVAILABLE);
    }
}