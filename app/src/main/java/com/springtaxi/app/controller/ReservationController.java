package com.springtaxi.app.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.springtaxi.app.dto.ReservationDto;
import com.springtaxi.app.entity.Reservation;
import com.springtaxi.app.service.ReservationService;
import com.springtaxi.app.util.ResponseObj;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    
    @Autowired private ReservationService service;

    @GetMapping()
    public List<Reservation> getReservations() {
        return service.getAll();
    }
    
    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable("id") Long id){
        return service.getById(id);
    }
    
    @PostMapping()
    public ResponseObj add(@Valid @ModelAttribute Reservation reservation){
        return service.add(reservation);
    }

    @DeleteMapping("/{id}")
    public ResponseObj deleteReservation(@PathVariable("id") Long id) {
        return service.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseObj update(@ModelAttribute Reservation reservation) {
        return service.update(reservation);
    }

    @GetMapping("/analytics")
    public Map<String, Object> getAnalytics() {
        return service.analytics();
    } 

    @GetMapping("/bagages")
    public List<Reservation> getBagages() {
        return service.getAll().stream().filter(r -> r.getBagagesVolumineux() != 0).collect(Collectors.toList());
    } 

}
