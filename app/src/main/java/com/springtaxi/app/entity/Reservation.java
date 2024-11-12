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

    @Column(name = "reservation_date_time")
    private LocalDateTime reservationDateTime = LocalDateTime.now();

    @Column(name = "trip_start_time")
    private LocalDateTime tripStartTime;

    @Column(name = "trip_end_time")
    private LocalDateTime tripEndTime;

    @Column(name = "pickup_address")
    @NotNull(message = "Pickup address is required.")
    private String pickupAddress;

    @Column(name = "dropoff_address")
    @NotNull(message = "Drop-off address is required.")
    private String dropoffAddress;

    @NotNull(message = "Price is required.")
    private Double price;

    @NotNull(message = "Status is required (CREATED, CONFIRMED, COMPLETED, CANCELED).")
    private Status status;

    @Column(name = "distance_km")
    @NotNull(message = "Distance in kilometers is required.")
    @Max(100)
    private Double distanceKm;
}
