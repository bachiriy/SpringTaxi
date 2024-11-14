package com.springtaxi.app.service;

import com.springtaxi.app.dao.DriverDao;
import com.springtaxi.app.dto.DriverAnalytics;
import com.springtaxi.app.dto.DriverDto;
import com.springtaxi.app.entity.Driver;
import com.springtaxi.app.mapper.DriverMapper;
import com.springtaxi.app.repository.DriverRepository;
import com.springtaxi.app.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class DriverService {
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;
    private final DriverDao driverDao;

    @Autowired
    public DriverService(DriverRepository driverRepository , DriverMapper driverMapper , DriverDao driverDao) {
        this.driverRepository = driverRepository;
        this.driverMapper = driverMapper;
        this.driverDao = driverDao;
    }
    public List<DriverDto> getAllDrivers() {
        List<Driver> drivers = driverRepository.findAll();
        return driverMapper.toDtoList(drivers);
    }
    public DriverDto getDriverById(int id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Driver Not Found"));
        return driverMapper.toDto(driver);
    }

    public DriverDto createDriver(DriverDto driverDto) {
        if (driverDto == null) {
                throw new IllegalArgumentException("La formation ne peut pas être nulle");
        }
        Driver driver = driverMapper.toEntity(driverDto);
        Driver savedDriver = driverRepository.save(driver);
        return driverMapper.toDto(savedDriver);
    }
    public DriverDto updateDriver(DriverDto driverDto, int id) {
        Driver existingDriver = driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver Not Found"));
        existingDriver.setFirstName(driverDto.getFirstName());
        existingDriver.setLastName(driverDto.getLastName());
        existingDriver.setDisponibiliteDebut(driverDto.getDisponibiliteDebut());
        existingDriver.setDisponibiliteFin(driverDto.getDisponibiliteFin());
        existingDriver.setStatut(driverDto.getStatut());

        LoggerUtil.logInfo("Driver updated successfuly : " + existingDriver);
        Driver updatedDriver = driverRepository.save(existingDriver);
        return driverMapper.toDto(updatedDriver);
    }
    public void deleteDriver(int id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver Not Found"));
        driverRepository.delete(driver);
        LoggerUtil.logInfo("Driver deleted successfuly : " + driver);
    }

    public DriverAnalytics getDriverAnalytics() {
        return driverDao.getDriverAnalytics();
    }


}
