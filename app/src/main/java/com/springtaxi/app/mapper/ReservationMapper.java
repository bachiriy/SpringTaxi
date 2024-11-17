package com.springtaxi.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.springtaxi.app.dto.ReservationDto;
import com.springtaxi.app.entity.Driver;
import com.springtaxi.app.entity.Reservation;
import com.springtaxi.app.entity.Vehicle;


@Mapper(componentModel = "spring", imports = {Driver.class, Vehicle.class})
@Component
public interface ReservationMapper {
    // ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    // @Mapping(target = "driver", expression = "java(reservation.getDriver.toString())")
    // @Mapping(target = "vehicle", expression = "java(reservation.getVehicle.toString())")
    // ReservationDto toDto(Reservation reservation);
}