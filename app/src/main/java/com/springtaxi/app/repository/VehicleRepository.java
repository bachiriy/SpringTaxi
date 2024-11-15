package com.springtaxi.app.repository;

import com.springtaxi.app.entity.Vehicle;
import com.springtaxi.app.entity.VehicleStatus;
import com.springtaxi.app.entity.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByStatus(VehicleStatus status);

    Optional<Vehicle> findByLicensePlate(String licensePlate);

    List<Vehicle> findByType(VehicleType type);

    Optional<Vehicle> findByModel(String model);
}
