package com.springtaxi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springtaxi.app.entity.enums.DriverStatut;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverDto {
    private int id;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime disponibiliteDebut;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime disponibiliteFin;
    private DriverStatut statut;
}
