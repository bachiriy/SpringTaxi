package com.springtaxi.app.DriverTest;

import com.springtaxi.app.dto.DriverDto;
import com.springtaxi.app.entity.Driver;
import com.springtaxi.app.entity.enums.DriverStatut;
import com.springtaxi.app.repository.DriverRepository;
import com.springtaxi.app.service.DriverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@ActiveProfiles("prod")

public class DriverIntegrationTest {

    @Autowired
    private DriverService driverService;

    @Autowired
    private DriverRepository driverRepository;

    private Driver savedDriver;

    @BeforeEach
    void setUp() {
        Driver driver = Driver.builder()
                .firstName("John")
                .lastName("Doe")
                .disponibiliteDebut(LocalDateTime.now())
                .disponibiliteFin(LocalDateTime.now().plusDays(4))
                .statut(DriverStatut.AVAILABLE)
                .build();
        savedDriver = driverRepository.save(driver);
    }

    @Test
    void testCreateDriver() {
        DriverDto driverDto = DriverDto.builder()
                .firstName("Jane")
                .lastName("Doe")
                .disponibiliteDebut(LocalDateTime.now())
                .disponibiliteFin(LocalDateTime.now().plusDays(3))
                .statut(DriverStatut.AVAILABLE)
                .build();

        DriverDto createdDriver = driverService.createDriver(driverDto);

        assertNotNull(createdDriver);
        assertEquals("Jane", createdDriver.getFirstName());
        assertEquals("Doe", createdDriver.getLastName());
    }

    @Test
    void testGetAllDrivers() {
        List<DriverDto> drivers = driverService.getAllDrivers();

        assertNotNull(drivers);
        assertFalse(drivers.isEmpty());
        assertTrue(drivers.stream().anyMatch(d -> d.getFirstName().equals("John")));
    }

    @Test
    void testGetDriverById() {
        DriverDto driver = driverService.getDriverById(savedDriver.getId());

        assertNotNull(driver);
        assertEquals(savedDriver.getId(), driver.getId());
        assertEquals("John", driver.getFirstName());
    }

    @Test
    void testUpdateDriver() {
        DriverDto driverDto = DriverDto.builder()
                .firstName("Johnny")
                .lastName("Smith")
                .disponibiliteDebut(LocalDateTime.now())
                .disponibiliteFin(LocalDateTime.now().plusDays(3))
                .statut(DriverStatut.UNAVAILABLE)
                .build();

        DriverDto updatedDriver = driverService.updateDriver(driverDto, savedDriver.getId());

        assertNotNull(updatedDriver);
        assertEquals("Johnny", updatedDriver.getFirstName());
        assertEquals("Smith", updatedDriver.getLastName());
        assertEquals(DriverStatut.UNAVAILABLE, updatedDriver.getStatut());
    }

    @Test
    void testDeleteDriver() {
        driverService.deleteDriver(savedDriver.getId());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            driverService.getDriverById(savedDriver.getId());
        });

        assertEquals("Driver Not Found", exception.getMessage());
    }
}
