package com.springtaxi.app.dto;

import com.springtaxi.app.entity.VehicleStatus;
import com.springtaxi.app.entity.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {

    private Long id;
    private String model;
    private String licensePlate;
    private int mileage;
    private VehicleStatus status;
    private VehicleType type;
    private Long driverId;  // Use driverId instead of driver object
}
