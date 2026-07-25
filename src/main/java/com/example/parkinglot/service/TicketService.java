package com.example.parkinglot.service;

import com.example.parkinglot.entity.ParkingSpot;
import com.example.parkinglot.entity.SpotStatus;
import com.example.parkinglot.entity.Ticket;
import com.example.parkinglot.entity.TicketStatus;
import com.example.parkinglot.repository.ParkingSpotRepository;
import com.example.parkinglot.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;


    public Ticket getTicket(Long ticketId) {

        return ticketRepository
                .findById(ticketId)
                .orElseThrow(() ->
                        new RuntimeException("Ticket Not Found"));
    }


    public double calculateFee(Long ticketId) {

        Ticket ticket = getTicket(ticketId);

        LocalDateTime entryTime = ticket.getEntryTime();
        LocalDateTime exitTime = LocalDateTime.now();

        long hours = Duration.between(entryTime, exitTime).toHours();

        if (hours == 0) {
            hours = 1;
        }

        return hours * 50;
    }


    public Ticket exitVehicle(Long ticketId) {

        Ticket ticket = getTicket(ticketId);

        ticket.setExitTime(LocalDateTime.now());

        double fee = calculateFee(ticketId);

        ticket.setAmount(fee);
        ticket.setStatus(TicketStatus.COMPLETED);

        ParkingSpot spot = ticket.getParkingSpot();
        spot.setStatus(SpotStatus.AVAILABLE);
        spot.setVehicle(null);

        parkingSpotRepository.save(spot);

        return ticketRepository.save(ticket);
    }
}