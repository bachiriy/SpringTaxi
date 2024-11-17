package com.springtaxi.app.mapper;

import com.springtaxi.app.dto.VehicleDto;
import com.springtaxi.app.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface VehicleMapper {

    @Mapping(source = "driver.id", target = "driverId")  // Map driver's ID to driverId
    VehicleDto toDto(Vehicle vehicle);

    @Mapping(target = "driver", ignore = true)  // Ignore the driver relationship
    Vehicle toEntity(VehicleDto vehicleDto);

    List<VehicleDto> toDtoList(List<Vehicle> vehicles);
}
