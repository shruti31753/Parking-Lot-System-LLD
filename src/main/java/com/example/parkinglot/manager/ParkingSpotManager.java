package com.example.parkinglot.manager;

import com.example.parkinglot.entity.ParkingSpot;
import com.example.parkinglot.entity.SpotStatus;
import com.example.parkinglot.entity.SpotType;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class ParkingSpotManager {

    private final Map<String, ParkingSpot> spotMap =
            new ConcurrentHashMap<>();


    public void addSpot(ParkingSpot spot){

        spot.setStatus(SpotStatus.AVAILABLE);

        spotMap.put(
                spot.getSpotNumber(),
                spot
        );
    }



    public synchronized ParkingSpot allocateSpot(
            SpotType spotType
    ){


        ParkingSpot spot =
                spotMap.values()
                        .stream()
                        .filter(s ->
                                s.getStatus()
                                        == SpotStatus.AVAILABLE
                                        &&
                                        s.getSpotType()
                                                == spotType
                        )
                        .sorted(
                                Comparator.comparing(
                                        ParkingSpot::getSpotNumber
                                )
                        )
                        .findFirst()
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "No parking spot available"
                                )
                        );

        spot.setStatus(
                SpotStatus.OCCUPIED
        );


        spotMap.remove(
                spot.getSpotNumber()
        );


        return spot;
    }


    public void releaseSpot(
            ParkingSpot spot
    ){

        spot.setStatus(
                SpotStatus.AVAILABLE
        );


        spot.setVehicle(null);


        spotMap.put(
                spot.getSpotNumber(),
                spot
        );
    }


    public int getAvailableSpotCount(){

        return spotMap.size();
    }

}