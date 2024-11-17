package com.springtaxi.app.mapper;

import com.springtaxi.app.dto.DriverDto;
import com.springtaxi.app.entity.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface DriverMapper {
    DriverDto toDto(Driver driver);

    @Mapping(target = "reservations", ignore = true)
    @Mapping(target = "vehicle", ignore = true)
    Driver toEntity(DriverDto driverDto);
    List<DriverDto> toDtoList(List<Driver> drivers);
}