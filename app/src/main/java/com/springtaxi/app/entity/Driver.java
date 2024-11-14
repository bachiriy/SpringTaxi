package com.springtaxi.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springtaxi.app.entity.enums.DriverStatut;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "the first name must not be empty")
    private String firstName;

    @NotBlank(message = "the last name must not be empty")
    private String lastName;

    @NotNull(message = "Availability start time is required")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime disponibiliteDebut;

    @NotNull(message = "Availability end time is required")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime disponibiliteFin;


    @Enumerated(EnumType.STRING)
    private DriverStatut statut;

    @OneToMany(mappedBy = "driver")
    private List<Reservation> reservations;

    @OneToOne(mappedBy = "driver")
    private Vehicle vehicle;

}
