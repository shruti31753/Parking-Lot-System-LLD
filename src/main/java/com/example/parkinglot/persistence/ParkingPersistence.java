package com.example.parkinglot.persistence;

import com.example.parkinglot.entity.ParkingSpot;
import com.example.parkinglot.entity.SpotStatus;
import com.example.parkinglot.entity.SpotType;
import com.example.parkinglot.entity.Ticket;
import com.example.parkinglot.entity.Vehicle;
import com.example.parkinglot.repository.ParkingSpotRepository;
import com.example.parkinglot.repository.TicketRepository;
import com.example.parkinglot.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ParkingPersistence {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private TicketRepository ticketRepository;


    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }


    public Optional<Vehicle> findVehicle(String vehicleNumber) {
        return vehicleRepository.findByVehicleNumber(vehicleNumber);
    }


    public void deleteVehicle(Vehicle vehicle) {
        vehicleRepository.delete(vehicle);
    }


    public Optional<ParkingSpot> findAvailableSpot(SpotType spotType) {
        return parkingSpotRepository.findFirstByStatusAndSpotType(
                SpotStatus.AVAILABLE,
                spotType
        );
    }


    public ParkingSpot saveParkingSpot(ParkingSpot parkingSpot) {
        return parkingSpotRepository.save(parkingSpot);
    }


    public List<ParkingSpot> getAllParkingSpots() {
        return parkingSpotRepository.findAll();
    }


    public List<ParkingSpot> getAvailableParkingSpots() {
        return parkingSpotRepository.findByStatus(SpotStatus.AVAILABLE);
    }


    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }


    public Optional<Ticket> findTicket(Long ticketId) {
        return ticketRepository.findById(ticketId);
    }


    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
}