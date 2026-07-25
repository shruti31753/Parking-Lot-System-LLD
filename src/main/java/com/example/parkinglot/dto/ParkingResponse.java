package com.example.parkinglot.dto;

import lombok.Data;

@Data
public class ParkingResponse {

    private Long ticketId;

    private String spotNumber;

    private String message;
}