package com.springtaxi.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "the first name must not be empty")
    private String firstName;

    @NotBlank(message = "the last name must not be empty")
    private String lastName;

    private LocalDate disponibiliteDebut;
    private LocalDate disponibiliteFin;

    @Enumerated(EnumType.STRING)
    private DriverStatut statut;

    @OneToMany
    private List<Reservation> reservations;

    @OneToOne
    private Vehicle vehicle;

}
