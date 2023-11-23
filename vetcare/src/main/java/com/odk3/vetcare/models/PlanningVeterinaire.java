package com.odk3.vetcare.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Data
public class PlanningVeterinaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private Long planningId;

    ////////////////////////// Heur de debut

    // Not nul de Spring boot
    @NotNull(message = " heure")

    // Not null de basse de donnée
    @Column(nullable = false)
    private LocalTime heureDebut;

    ////////////////////////// Heur de debut

    // Not nul de Spring boot
    @NotNull(message = " heure")

    // Not null de basse de donnée
    @Column(nullable = false)
    private LocalTime heureFin;

    ////////////////////////// Ordre priorite

    @NotNull(message = "Champs vide")
    @Column(nullable = false)
    private int priorite;


    ////////////////////////////// LES RELATIONS ENTRES LES TABLES

    @ManyToOne
    @JoinColumn(name = "veteriniareId", nullable = false)
    private Veterinaire veterinaire;
}
