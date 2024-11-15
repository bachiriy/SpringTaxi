package com.springtaxi.app.controller;

import com.springtaxi.app.dto.VehicleDto;
import com.springtaxi.app.entity.Vehicle;
import com.springtaxi.app.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/getAllVehicles")
    public ResponseEntity<List<VehicleDto>> getAllVehicles() {
        List<VehicleDto> vehicles = vehicleService.getAllVehicles().stream()
                .map(VehicleDto::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/getVehicle/{id}")
    public ResponseEntity<VehicleDto> getVehicleById(@PathVariable Long id) {
        Optional<Vehicle> vehicleOpt = vehicleService.getVehicleById(id);
        return vehicleOpt.map(vehicle -> ResponseEntity.ok(VehicleDto.toDto(vehicle)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }




    @PostMapping("/create")
    public ResponseEntity<String> createVehicle(@RequestBody VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleDto.toEntity();
        Vehicle savedVehicle = vehicleService.createVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body("Vehicle created successfully with ID: " + savedVehicle.getId());
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateVehicle(@PathVariable Long id, @RequestBody VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleDto.toEntity();
        Vehicle updatedVehicle = vehicleService.updateVehicle(id, vehicle);
        if (updatedVehicle != null) {
            return ResponseEntity.ok("Vehicle updated successfully with ID: " + updatedVehicle.getId());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not found.");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        boolean deleted = vehicleService.deleteVehicle(id);
        if (deleted) {
            return ResponseEntity.ok("Vehicle deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not found.");
    }
}
