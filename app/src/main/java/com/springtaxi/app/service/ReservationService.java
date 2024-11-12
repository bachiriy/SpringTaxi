package com.springtaxi.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtaxi.app.entity.Reservation;
import com.springtaxi.app.repository.ReservationRepository;
import java.util.List;

@Service
public class ReservationService {
    @Autowired private ReservationRepository repository;


    public List<Reservation> getAll(){
        return repository.findAll();
    }

    public void add(Reservation reservation){
        
    }
}
