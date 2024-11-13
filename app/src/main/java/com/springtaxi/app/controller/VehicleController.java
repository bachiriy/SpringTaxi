package com.springtaxi.app.controller;

import com.springtaxi.app.dto.VehicleDto;
import com.springtaxi.app.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<VehicleDto>> getAllVehicles() {
        List<VehicleDto> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/getVehicle/{id}")
    public ResponseEntity<VehicleDto> getVehicleById(@PathVariable Long id) {
        try {
            VehicleDto vehicleDto = vehicleService.getVehicleById(id);
            return ResponseEntity.ok(vehicleDto);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<VehicleDto> createVehicle(@RequestBody VehicleDto vehicleDto) {
        VehicleDto savedVehicleDto = vehicleService.createVehicle(vehicleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicleDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VehicleDto> updateVehicle(@PathVariable Long id, @RequestBody VehicleDto vehicleDto) {
        VehicleDto updatedVehicleDto = vehicleService.updateVehicle(vehicleDto, id);
        if (updatedVehicleDto != null) {
            return ResponseEntity.ok(updatedVehicleDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        boolean deleted = vehicleService.deleteVehicle(id);
        if (deleted) {
            return ResponseEntity.ok("Vehicle deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not found.");
    }
}
