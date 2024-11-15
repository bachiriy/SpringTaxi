package com.springtaxi.app.repository;

import com.springtaxi.app.entity.Vehicle;
import com.springtaxi.app.entity.VehicleStatus;
import com.springtaxi.app.entity.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {


  
    @Query("SELECT v FROM Vehicle v WHERE v.model LIKE %:model%")
    List<Vehicle> findByModel(@Param("model") String model);
}
