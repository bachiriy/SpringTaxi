package com.springtaxi.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.springtaxi.app.dto.ReservationDto;
import com.springtaxi.app.entity.Reservation;


@Mapper(componentModel = "spring", uses = {DriverMapper.class})
public interface ReservationMapper {
   @Mapping(target = "vehicle.reservations", ignore = true)
   ReservationDto toDto(Reservation reservation);
}

// public List<ReservationDto> getAllDto(List<Reservation> reservations){
//     if (this.modelMapper.getTypeMap(Reservation.class, ReservationDto.class) == null) {
//         TypeMap<Reservation, ReservationDto> propretyMaper = this.modelMapper.createTypeMap(Reservation.class, ReservationDto.class);
//         propretyMaper.addMappings(
//             mapper -> mapper.map(src -> src.getDriver().toString(), ReservationDto::setDriver)
//         );
//     }
//     return reservations.stream().map(l -> this.modelMapper.map(l, ReservationDto.class)).collect(Collectors.toList());
// }