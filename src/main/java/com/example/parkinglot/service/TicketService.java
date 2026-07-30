package com.example.parkinglot.service;


import com.example.parkinglot.entity.ParkingSpot;
import com.example.parkinglot.entity.Ticket;
import com.example.parkinglot.entity.TicketStatus;
import com.example.parkinglot.exception.TicketNotFoundException;
import com.example.parkinglot.manager.ParkingSpotManager;
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
    private ParkingSpotManager parkingSpotManager;


    public Ticket getTicket(Long ticketId) {


        return ticketRepository
                .findById(ticketId)
                .orElseThrow(() ->
                        new TicketNotFoundException(
                                "Ticket not found with id : "
                                        + ticketId
                        )
                );
    }



    public double calculateFee(Long ticketId) {


        Ticket ticket =
                getTicket(ticketId);



        LocalDateTime entryTime =
                ticket.getEntryTime();


        LocalDateTime exitTime =
                LocalDateTime.now();



        long hours =
                Duration
                        .between(
                                entryTime,
                                exitTime
                        )
                        .toHours();



        if(hours == 0){
            hours = 1;
        }



        double rate = 50;


        return hours * rate;

    }

    public Ticket exitVehicle(Long ticketId) {

        Ticket ticket =
                getTicket(ticketId);

        ticket.setExitTime(
                LocalDateTime.now()
        );

        double amount =
                calculateFee(ticketId);



        ticket.setAmount(
                amount
        );


        ticket.setStatus(
                TicketStatus.COMPLETED
        );

        ParkingSpot spot =
                ticket.getParkingSpot();



        parkingSpotManager.releaseSpot(
                spot
        );


        return ticketRepository.save(ticket);

    }

}