package com.springtaxi.app.repository;

import com.springtaxi.app.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository  extends JpaRepository<Vehicle, Long> {

}
