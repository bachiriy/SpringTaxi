package com.springtaxi.app.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springtaxi.app.entity.Reservation;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation findByPickupAddressAndDropoffAddressAndPrice(String pickupAddress, String dropoffAddress, Double price); 
}
