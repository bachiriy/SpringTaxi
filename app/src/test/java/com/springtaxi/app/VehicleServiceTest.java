package com.springtaxi.app;

import com.springtaxi.app.dto.VehicleDto;
import com.springtaxi.app.entity.Driver;
import com.springtaxi.app.entity.Vehicle;
import com.springtaxi.app.entity.VehicleStatus;
import com.springtaxi.app.entity.VehicleType;
import com.springtaxi.app.entity.enums.Status;
import com.springtaxi.app.mapper.VehicleMapper;
import com.springtaxi.app.repository.DriverRepository;
import com.springtaxi.app.repository.VehicleRepository;
import com.springtaxi.app.service.VehicleService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private VehicleMapper vehicleMapper;

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private VehicleService vehicleService;

    private VehicleDto vehicleDto;
    private Vehicle vehicle;
    private Driver driver;

    @BeforeEach
    void setUp() {
        // Initialize mock objects
        driver = new Driver();
        driver.setId(1);

        vehicleDto = new VehicleDto();
        vehicleDto.setModel("Tesla");
        vehicleDto.setLicensePlate("1234XYZ");
        vehicleDto.setMileage(1000);
        vehicleDto.setStatus(VehicleStatus.IN_SERVICE);
        vehicleDto.setType(VehicleType.BERLINE);
        vehicleDto.setDriverId(1L);

        vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setModel("Tesla");
        vehicle.setLicensePlate("1234XYZ");
        vehicle.setMileage(1000);
        vehicle.setStatus(VehicleStatus.IN_SERVICE);
        vehicle.setType(VehicleType.BERLINE);
        vehicle.setDriver(driver);
    }

    @Test
    void testCreateVehicle() {
        when(driverRepository.findById(1)).thenReturn(Optional.of(driver));
        when(vehicleMapper.toEntity(vehicleDto)).thenReturn(vehicle);
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
        when(vehicleMapper.toDto(vehicle)).thenReturn(vehicleDto);

        VehicleDto result = vehicleService.createVehicle(vehicleDto);

        assertNotNull(result);
        assertEquals(vehicleDto.getModel(), result.getModel());
        assertEquals(vehicleDto.getLicensePlate(), result.getLicensePlate());
        verify(vehicleRepository, times(1)).save(vehicle);
    }

    @Test
    void testCreateVehicleWithNullDriverId() {
        vehicleDto.setDriverId(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            vehicleService.createVehicle(vehicleDto);
        });

        assertEquals("Driver ID cannot be null", exception.getMessage());
    }

    @Test
    void testUpdateVehicle() {
        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
        when(vehicleMapper.toDto(vehicle)).thenReturn(vehicleDto);

        VehicleDto result = vehicleService.updateVehicle(vehicleDto, 1L);

        assertNotNull(result);
        assertEquals(vehicleDto.getModel(), result.getModel());
        assertEquals(vehicleDto.getLicensePlate(), result.getLicensePlate());
        verify(vehicleRepository, times(1)).save(vehicle);
    }

    @Test
    void testGetVehicleById() {
        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));
        when(vehicleMapper.toDto(vehicle)).thenReturn(vehicleDto);

        VehicleDto result = vehicleService.getVehicleById(1L);

        assertNotNull(result);
        assertEquals(vehicleDto.getModel(), result.getModel());
        assertEquals(vehicleDto.getLicensePlate(), result.getLicensePlate());
    }

    @Test
    void testGetVehicleByIdNotFound() {
        when(vehicleRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            vehicleService.getVehicleById(1L);
        });

        assertEquals("Vehicle Not Found", exception.getMessage());
    }

    @Test
    void testDeleteVehicle() {
        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));

        boolean result = vehicleService.deleteVehicle(1L);

        assertTrue(result);
        verify(vehicleRepository, times(1)).delete(vehicle);
    }

    @Test
    void testDeleteVehicleNotFound() {
        when(vehicleRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            vehicleService.deleteVehicle(1L);
        });

        assertEquals("Vehicle Not Found", exception.getMessage());
    }
}
