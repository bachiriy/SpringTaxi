package com.springtaxi.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springtaxi.app.entity.Reservation;
import com.springtaxi.app.exception.ElementNotFoundException;
import com.springtaxi.app.exception.ElementAlreadyExistsException;
import com.springtaxi.app.repository.ReservationRepository;
import com.springtaxi.app.util.ResponseObj;

import java.util.List;

@Service
public class ReservationService {
    @Autowired private ReservationRepository repository;


    public List<Reservation> getAll(){
        return repository.findAll();
    }

    public ResponseObj add(Reservation reservation) {

        Reservation foundReservation = repository.findByStartingAddressAndDestinationAddressAndPrice(
            reservation.getStartingAddress(), 
            reservation.getDestinationAddress(), 
            reservation.getPrice());

        if (foundReservation == null) {
            repository.save(reservation);
            return new ResponseObj(HttpStatus.CREATED.value(), "Reservation created.");
        } else 
            throw new ElementAlreadyExistsException(
                "Reservation with addresses " +
                reservation.getStartingAddress() + ", " + 
                reservation.getDestinationAddress() + " and the price " + 
                reservation.getPrice() + " already exists.");
    }

    public Reservation getById(Long id){
        return repository.findById(id).orElseThrow(() -> new ElementNotFoundException("Reservation not found."));
    }

    public ResponseObj delete(Long id) {
        Reservation foundReservation = getById(id);
        repository.delete(foundReservation);
        return new ResponseObj(HttpStatus.OK.value(), "Reservation deleted.");
    }

    public ResponseObj update(Reservation reservation) {
        Reservation foundReservation = getById(reservation.getId());
        if (!reservation.getDestinationAddress().isEmpty()) foundReservation.setDestinationAddress(reservation.getDestinationAddress());
        if (!reservation.getStartingAddress().isEmpty()) foundReservation.setStartingAddress(reservation.getStartingAddress());
        if (reservation.getPrice() != null) foundReservation.setPrice(reservation.getPrice());
        if (reservation.getStatus() != null) foundReservation.setStatus(reservation.getStatus());
        if (reservation.getDistanceKm() != null) foundReservation.setDistanceKm(reservation.getDistanceKm());
        repository.save(foundReservation);
        return new ResponseObj(HttpStatus.OK.value(), "Reservation updated.");
    }
}
