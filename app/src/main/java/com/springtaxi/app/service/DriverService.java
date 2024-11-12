package com.springtaxi.app.service;

import com.springtaxi.app.entity.Driver;
import com.springtaxi.app.repository.DriverRepository;
import com.springtaxi.app.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class DriverService {
    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }
    public Driver getDriverById(int id) {
        return driverRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Driver Not Found"));
    }

    public Driver createDriver(Driver driver) {
        if (driver == null) {
                throw new IllegalArgumentException("La formation ne peut pas être nulle");
        }
        return driverRepository.save(driver);
    }
    public Driver updateDriver(Driver driver, int id) {
        Driver driverExist = getDriverById(id);

        driverExist.setFirstName(driver.getFirstName());
        driverExist.setLastName(driver.getLastName());
        driverExist.setDisponibiliteDebut(driver.getDisponibiliteDebut());
        driverExist.setDisponibiliteFin(driver.getDisponibiliteFin());
        driverExist.setStatut(driver.getStatut());

        LoggerUtil.logInfo("Driver updated successfuly : " + driverExist);
        return driverRepository.save(driverExist);
    }
    public void deleteDriver(int id) {
        Driver driver = getDriverById(id);
        driverRepository.delete(driver);
        LoggerUtil.logInfo("Driver deleted successfuly : " + driver);
    }

}
