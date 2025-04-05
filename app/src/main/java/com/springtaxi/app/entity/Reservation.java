package com.springtaxi.app.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springtaxi.app.entity.enums.ReservationStatus;

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
    @NotNull(message = "Trip start time is required.")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime tripStartTime;

    @Column(name = "trip_end_time")
    @DateTimeFormat(pattern = "HH:mm")
    @NotNull(message = "Trip end time is required.")
    private LocalTime tripEndTime;

    @Column(name = "pickup_address")
    @NotNull(message = "Pickup address is required.")
    private String pickupAddress;

    @Column(name = "dropoff_address")
    @NotNull(message = "Drop-off address is required.")
    private String dropoffAddress;

    @NotNull(message = "Price is required.")
    private Double price;

    @NotNull(message = "Status is required.")
    @Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Column(name = "distance_km")
    @NotNull(message = "Distance in kilometers is required.")
    @Max(100)
    private Double distanceKm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    @NotNull(message = "Must be assosiated with a driver.")
    @JsonIgnore
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    @NotNull(message = "Must be assosiated with a veicle.")
    @JsonIgnore
    private Vehicle vehicle;

    @Min(0)
    @Max(value = 3, message = "Maximum 3 bagages valumnerux par reservation")
    @Column(name = "bagages_volumineux")
    private int bagagesVolumineux;
}
