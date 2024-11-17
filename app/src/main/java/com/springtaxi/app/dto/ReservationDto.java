package com.springtaxi.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.stereotype.Component;

import com.springtaxi.app.entity.enums.ReservationStatus;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    private Long id;
    private LocalDateTime reservationDateTime;
    private LocalTime tripStartTime;
    private LocalTime tripEndTime;
    private String pickupAddress;
    private String dropoffAddress;
    private Double price;
    private ReservationStatus status;
    private Double distanceKm;
    private String driver;
    private String vehicle;
}
