package com.springtaxi.app.DriverTest;

import com.springtaxi.app.entity.Driver;
import com.springtaxi.app.dto.DriverDto;
import com.springtaxi.app.repository.DriverRepository;
import com.springtaxi.app.mapper.DriverMapper;
import com.springtaxi.app.service.DriverService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverUnitTest {
    @Mock private DriverRepository driverRepository;
    @Mock private DriverMapper driverMapper;
    @InjectMocks private DriverService driverService;

    @Test
    void testGetAllDrivers() {
        List<Driver> drivers = createDriverList();
        List<DriverDto> driverDtos = createDriverDtoList();

        when(driverRepository.findAll()).thenReturn(drivers);
        when(driverMapper.toDtoList(drivers)).thenReturn(driverDtos);

        List<DriverDto> result = driverService.getAllDrivers();

        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstName());
        verify(driverRepository, times(1)).findAll();
    }

    @Test
    void testGetDriverById() {
        Driver driver = createDriver();
        DriverDto driverDto = createDriverDto();

        when(driverRepository.findById(1)).thenReturn(Optional.of(driver));
        when(driverMapper.toDto(driver)).thenReturn(driverDto);

        DriverDto result = driverService.getDriverById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(driverRepository, times(1)).findById(1);
    }

    @Test
    void testCreateDriver() {
        DriverDto driverDto = createDriverDto();
        Driver driver = createDriver();
        Driver savedDriver = createSavedDriver();
        DriverDto savedDriverDto = createSavedDriverDto();

        when(driverMapper.toEntity(driverDto)).thenReturn(driver);
        when(driverRepository.save(driver)).thenReturn(savedDriver);
        when(driverMapper.toDto(savedDriver)).thenReturn(savedDriverDto);

        DriverDto result = driverService.createDriver(driverDto);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(driverRepository, times(1)).save(driver);
    }

    @Test
    void testUpdateDriver() {
        Driver existingDriver = createDriver();
        DriverDto updatedDriverDto = createUpdatedDriverDto();
        Driver updatedDriver = createUpdatedDriver();

        when(driverRepository.findById(1)).thenReturn(Optional.of(existingDriver));
        when(driverRepository.save(existingDriver)).thenReturn(updatedDriver);
        when(driverMapper.toDto(updatedDriver)).thenReturn(updatedDriverDto);

        DriverDto result = driverService.updateDriver(updatedDriverDto, 1);

        assertEquals("Johnny", result.getFirstName());
        verify(driverRepository, times(1)).findById(1);
        verify(driverRepository, times(1)).save(existingDriver);
    }

    @Test
    void testDeleteDriver() {
        Driver driver = createDriver();

        when(driverRepository.findById(1)).thenReturn(Optional.of(driver));
        doNothing().when(driverRepository).delete(driver);

        driverService.deleteDriver(1);

        verify(driverRepository, times(1)).findById(1);
        verify(driverRepository, times(1)).delete(driver);
    }

    private List<Driver> createDriverList() {
        return Arrays.asList(
                Driver.builder().id(1).firstName("John").lastName("Doe").build(),
                Driver.builder().id(2).firstName("Jane").lastName("Smith").build()
        );
    }

    private List<DriverDto> createDriverDtoList() {
        return Arrays.asList(
                DriverDto.builder().id(1).firstName("John").lastName("Doe").build(),
                DriverDto.builder().id(2).firstName("Jane").lastName("Smith").build()
        );
    }

    private Driver createDriver() {
        return Driver.builder().id(1).firstName("John").lastName("Doe").build();
    }

    private DriverDto createDriverDto() {
        return DriverDto.builder().id(1).firstName("John").lastName("Doe").build();
    }

    private Driver createSavedDriver() {
        return Driver.builder().id(1).firstName("John").lastName("Doe").build();
    }

    private DriverDto createSavedDriverDto() {
        return DriverDto.builder().id(1).firstName("John").lastName("Doe").build();
    }

    private DriverDto createUpdatedDriverDto() {
        return DriverDto.builder().firstName("Johnny").lastName("Doe").build();
    }

    private Driver createUpdatedDriver() {
        return Driver.builder().id(1).firstName("Johnny").lastName("Doe").build();
    }
}
