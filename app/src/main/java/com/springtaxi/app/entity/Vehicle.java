package com.springtaxi.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;

    @Column(unique = true, nullable = false)
    private String licensePlate;

    private int mileage;

    @Enumerated(EnumType.STRING)
    private VehicleStatus status;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

}
