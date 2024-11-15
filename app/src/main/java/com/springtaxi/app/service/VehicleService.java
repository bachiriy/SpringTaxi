package com.springtaxi.app.service;

import com.springtaxi.app.dao.VehicleDao;
import com.springtaxi.app.dto.VehicleDto;
import com.springtaxi.app.entity.Driver;
import com.springtaxi.app.entity.Vehicle;
import com.springtaxi.app.mapper.VehicleMapper;
import com.springtaxi.app.repository.DriverRepository;
import com.springtaxi.app.repository.VehicleRepository;
import com.springtaxi.app.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;
    private final DriverRepository driverRepository;

    private final VehicleDao vehicleDao;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper , DriverRepository driverRepository, VehicleDao vehicleDao) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
        this.driverRepository =driverRepository;
        this.vehicleDao = vehicleDao;
    }

    public List<VehicleDto> getAllVehicles(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Vehicle> vehiclesPage = vehicleRepository.findAll(pageable);
        List<Vehicle> vehicles = vehiclesPage.getContent();
        return vehicleMapper.toDtoList(vehicles);
    }
    public VehicleDto getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle Not Found"));
        return vehicleMapper.toDto(vehicle);
    }

    public VehicleDto createVehicle(VehicleDto vehicleDto) {

        if (vehicleDto.getDriverId() == null) {
            throw new IllegalArgumentException("Driver ID cannot be null");
        }

        Driver driver = driverRepository.findById(Math.toIntExact((Long) vehicleDto.getDriverId()))
                .orElseThrow(() -> new EntityNotFoundException("Driver not found"));

        Vehicle vehicle = vehicleMapper.toEntity(vehicleDto);

        vehicle.setDriver(driver);

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return vehicleMapper.toDto(savedVehicle);
    }

    public VehicleDto updateVehicle(VehicleDto vehicleDto, Long id) {
        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle Not Found"));
        existingVehicle.setModel(vehicleDto.getModel());
        existingVehicle.setLicensePlate(vehicleDto.getLicensePlate());
        existingVehicle.setMileage(vehicleDto.getMileage());
        existingVehicle.setStatus(vehicleDto.getStatus());
        existingVehicle.setType(vehicleDto.getType());

        String vehicleInfo = "Vehicle updated successfully: id=" + existingVehicle.getId() +
                ", model=" + existingVehicle.getModel() +
                ", licensePlate=" + existingVehicle.getLicensePlate();
        LoggerUtil.logInfo(vehicleInfo);

        Vehicle updatedVehicle = vehicleRepository.save(existingVehicle);
        return vehicleMapper.toDto(updatedVehicle);
    }


    public boolean deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle Not Found"));
        vehicleRepository.delete(vehicle);

        LoggerUtil.logInfo("Vehicle deleted successfully with ID: " + id);
        return true;
    }

    public Map<String, Object> getVehicleStatistics() {
        Map<String, Object> response = new HashMap<>();

        response.put("averageMileageByType", vehicleDao.calculateAverageMileageByType());
        response.put("fleetStatus", vehicleDao.calculateFleetStatus());
        response.put("usageRateByType", vehicleDao.calculateUsageRateByType());

        return response;
    }

    public List<VehicleDto> findByModel(String model) {
        List<Vehicle> vehicles = vehicleRepository.findByModel(model);
        return vehicleMapper.toDtoList(vehicles);
    }
}
