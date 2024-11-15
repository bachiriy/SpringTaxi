package com.springtaxi.app.dto;

import com.springtaxi.app.entity.enums.DriverStatut;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverDto {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate disponibiliteDebut;
    private LocalDate disponibiliteFin;
    private DriverStatut statut;
}
