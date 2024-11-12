package com.springtaxi.app.mapper;

import com.springtaxi.app.dto.DriverDto;
import com.springtaxi.app.entity.Driver;
import org.mapstruct.Mapper;

@Mapper
public class DriverMapper {
    public DriverDto toDto (Driver driver) {
        return DriverDto.builder()
                .id(driver.getId())
                .firstName(driver.getFirstName())
                .lastName(driver.getLastName())
                .disponibiliteDebut(driver.getDisponibiliteDebut())
                .disponibiliteFin(driver.getDisponibiliteFin())
                .statut(driver.getStatut())
                .build();
    }

}
