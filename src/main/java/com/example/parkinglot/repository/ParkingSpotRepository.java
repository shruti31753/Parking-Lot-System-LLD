package com.example.parkinglot.repository;

import com.example.parkinglot.entity.ParkingSpot;
import com.example.parkinglot.entity.SpotStatus;
import com.example.parkinglot.entity.SpotType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {

    Optional<ParkingSpot> findFirstByStatusAndSpotType(
            SpotStatus status,
            SpotType spotType);

    List<ParkingSpot> findByStatus(SpotStatus status);

    List<ParkingSpot> findBySpotType(SpotType spotType);

}