package com.springtaxi.app.dto;

import com.springtaxi.app.entity.Driver;
import com.springtaxi.app.entity.Vehicle;
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
    private Integer driverId;

    public static VehicleDto toDto(Vehicle vehicle) {
        return new VehicleDto(
                vehicle.getId(),
                vehicle.getModel(),
                vehicle.getLicensePlate(),
                vehicle.getMileage(),
                vehicle.getStatus(),
                vehicle.getType(),
                vehicle.getDriver() != null ? vehicle.getDriver().getId() : null
        );
    }


    public Vehicle toEntity() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(this.id);
        vehicle.setModel(this.model);
        vehicle.setLicensePlate(this.licensePlate);
        vehicle.setMileage(this.mileage);
        vehicle.setStatus(this.status);
        vehicle.setType(this.type);

        if (this.driverId != null) {
            Driver driver = new Driver();
            driver.setId(this.driverId);
            vehicle.setDriver(driver);
        }

        return vehicle;
    }


}
