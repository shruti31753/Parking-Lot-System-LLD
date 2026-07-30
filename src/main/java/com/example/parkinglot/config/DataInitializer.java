package com.example.parkinglot.config;


import com.example.parkinglot.entity.ParkingSpot;
import com.example.parkinglot.entity.SpotStatus;
import com.example.parkinglot.entity.SpotType;
import com.example.parkinglot.manager.ParkingSpotManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
public class DataInitializer implements CommandLineRunner {


    private final ParkingSpotManager parkingSpotManager;


    public DataInitializer(
            ParkingSpotManager parkingSpotManager
    ) {

        this.parkingSpotManager = parkingSpotManager;
    }



    @Override
    public void run(String... args) {
        
        for(int i = 1; i <= 10; i++) {


            ParkingSpot spot =
                    new ParkingSpot();



            spot.setSpotNumber(
                    "A" + i
            );



            spot.setSpotType(
                    SpotType.MEDIUM
            );



            spot.setStatus(
                    SpotStatus.AVAILABLE
            );

            parkingSpotManager.addSpot(
                    spot
            );
        }



        System.out.println(
                "Parking spots loaded: "
                        +
                        parkingSpotManager
                                .getAvailableSpotCount()
        );
    }
}