package com.springtaxi.app.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.springtaxi.app.entity.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "date_time")
    private LocalDateTime dateTime = LocalDateTime.now();
    
    @Column(name = "starting_address")
    @NotNull(message = "Starting address is required.")
    private String startingAddress;

    @Column(name = "destination_address")
    @NotNull(message = "Destination address is required.")
    private String destinationAddress;

    @NotNull(message = "Price is required.")
    private Double price;

    @NotNull(message = "Status is required (CREATED, CONFIRMED, COMPLETED, CANCELED).")
    private Status status;

    @Column(name = "distance_km")
    @NotNull(message = "Distance km is required.")
    @Max(100)
    private Double distanceKm;
}

