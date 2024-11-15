package com.springtaxi.app.controller;

import com.springtaxi.app.dto.DriverAnalytics;
import com.springtaxi.app.dto.DriverDto;
import com.springtaxi.app.entity.Driver;
import com.springtaxi.app.entity.enums.DriverStatut;
import com.springtaxi.app.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/chauffeurs")
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public ResponseEntity<List<DriverDto>> getAllDrivers() {
       List<DriverDto> drivers =  driverService.getAllDrivers();
       return ResponseEntity.ok(drivers);
     }

     @GetMapping("/{id}")
    public ResponseEntity<DriverDto> getDriverById(@PathVariable int id) {
        DriverDto result = driverService.getDriverById(id);
        return ResponseEntity.ok(result);
     }
     @PostMapping
    public ResponseEntity<DriverDto> createDriver(@RequestBody @Valid DriverDto driverDto) {
        DriverDto result = driverService.createDriver(driverDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
     }
     @PutMapping("/{id}")
    public ResponseEntity<DriverDto> updateDriver(@PathVariable int id, @RequestBody @Valid DriverDto driverDto) {
        DriverDto result = driverService.updateDriver(driverDto,id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
     }
     @DeleteMapping("/{id}")
    public ResponseEntity<DriverDto> deleteDriver(@PathVariable int id) {
        driverService.deleteDriver(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
     }

     @GetMapping("/analytics")
    public ResponseEntity<DriverAnalytics> getAnalytics (){
        DriverAnalytics driverAnalytics = driverService.getDriverAnalytics();
        return ResponseEntity.status(HttpStatus.OK).body(driverAnalytics);
     }
     @GetMapping("/statut/{statut}")
    public ResponseEntity<List<DriverDto>> getDriversByStatut(@PathVariable DriverStatut statut) {
        List<DriverDto> drivers = driverService.getDriverByStatut(statut);
        return ResponseEntity.status(HttpStatus.OK).body(drivers);
     }

    @GetMapping("/available")
    public ResponseEntity<Page<DriverDto>> getAvailable(
            @RequestParam String date,  // Format: "2024-11-24 08:30:00"
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<DriverDto> driverPage = driverService.getDriversByDate(date, page, size);
        return  ResponseEntity.status(HttpStatus.OK).body(driverPage);
    }

}
