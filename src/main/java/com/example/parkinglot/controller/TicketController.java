package com.example.parkinglot.controller;

import com.example.parkinglot.dto.ExitVehicleRequest;
import com.example.parkinglot.entity.Ticket;
import com.example.parkinglot.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // Exit Vehicle
    @PostMapping("/exit")
    public Ticket exitVehicle(@RequestBody ExitVehicleRequest request) {
        return ticketService.exitVehicle(request.getTicketId());
    }

    // Get Ticket Details
    @GetMapping("/{ticketId}")
    public Ticket getTicket(@PathVariable Long ticketId) {
        return ticketService.getTicket(ticketId);
    }

    // Calculate Parking Fee
    @GetMapping("/fee/{ticketId}")
    public double calculateFee(@PathVariable Long ticketId) {
        return ticketService.calculateFee(ticketId);
    }
}