package com.springtaxi.app.dto;

import com.springtaxi.app.entity.enums.DriverStatut;

import java.time.LocalDate;

public class DriverDto {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate disponibiliteDebut;
    private LocalDate disponibiliteFin;
    private DriverStatut statut;
}
