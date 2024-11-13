package com.springtaxi.app;

import com.springtaxi.app.entity.Vehicle;
import com.springtaxi.app.entity.VehicleStatus;
import com.springtaxi.app.entity.VehicleType;
import com.springtaxi.app.repository.VehicleRepository;
import com.springtaxi.app.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    private Vehicle vehicle;

    @BeforeEach
    public void setUp() {
        vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setModel("Toyota Prius");
        vehicle.setLicensePlate("XYZ-1234");
        vehicle.setMileage(15000);
        vehicle.setStatus(VehicleStatus.AVAILABLE);
        vehicle.setType(VehicleType.BERLINE);
    }

    @Test
    public void testCreateVehicle() {
        when(vehicleRepository.save(Mockito.any(Vehicle.class))).thenReturn(vehicle);

        Vehicle createdVehicle = vehicleService.createVehicle(vehicle);

        assertNotNull(createdVehicle);
        assertEquals(vehicle.getId(), createdVehicle.getId());
        verify(vehicleRepository, times(1)).save(vehicle);
    }

    @Test
    public void testGetVehicleById() {

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));

        Optional<Vehicle> foundVehicle = vehicleService.getVehicleById(1L);

        assertTrue(foundVehicle.isPresent());
        assertEquals(vehicle.getId(), foundVehicle.get().getId());
        verify(vehicleRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateVehicle() {

        Vehicle updatedVehicle = new Vehicle();
        updatedVehicle.setId(1L);
        updatedVehicle.setModel("Toyota Camry");

        when(vehicleRepository.existsById(1L)).thenReturn(true);
        when(vehicleRepository.save(Mockito.any(Vehicle.class))).thenReturn(updatedVehicle);

        Vehicle result = vehicleService.updateVehicle(1L, updatedVehicle);

        assertNotNull(result);
        assertEquals("Toyota Camry", result.getModel());
        verify(vehicleRepository, times(1)).existsById(1L);
        verify(vehicleRepository, times(1)).save(updatedVehicle);
    }

    @Test
    public void testDeleteVehicle() {

        when(vehicleRepository.existsById(1L)).thenReturn(true);

        boolean isDeleted = vehicleService.deleteVehicle(1L);

        assertTrue(isDeleted);
        verify(vehicleRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteVehicle_NotFound() {

        when(vehicleRepository.existsById(1L)).thenReturn(false);

        boolean isDeleted = vehicleService.deleteVehicle(1L);

        assertFalse(isDeleted);
        verify(vehicleRepository, times(0)).deleteById(1L);
    }
}
