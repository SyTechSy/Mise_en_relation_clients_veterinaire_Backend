package com.odk3.vetcare.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class SuiviMedicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private Long suiviMedicamentId;

    //=========================== POUR DATE DEBUT ===========================

    // Not nul de Spring boot
    @NotNull(message = "date de debut")
    // Not null de basse de donnée
    @Column(nullable = false)
    private LocalDate dateDebut;

    //=========================== POUR DATE FIN ===========================

    // Not nul de Spring boot
    @NotNull(message = "date de fin")
    // Not null de basse de donnée
    @Column(nullable = false)
    private LocalDate finDebut;

    //=========================== POUR NOM MEDICAL ===========================

    // Not nul de Spring boot
    @NotNull(message = "nom de medicament")

    @Size(min = 2, message = "champs trop courte")

    // Not null de basse de donnée
    @Column(nullable = false)
    private String nomMedicament;

    //=========================== POUR NOM animal ===========================

    // Not nul de Spring boot
    @NotNull(message = "nom de animal")

    @Size(min = 2, message = "champs trop courte")

    // Not null de basse de donnée
    @Column(nullable = false)
    private String nomAnimal;

    //=========================== POUR FREQEUNCE ===========================

    // Not nul de Spring boot
    @NotNull(message = " exemple : (2) comprimer")

    @Size(min = 2, message = "champs trop courte")

    // Not null de basse de donnée
    @Column(nullable = false)
    private String frequense;

    //=========================== POUR DOSAGE ===========================

    // Not nul de Spring boot
    @NotNull(message = " exemple : 1 / jours")

    @Size(min = 2, message = "champs trop courte")

    // Not null de basse de donnée
    @Column(nullable = false)
    private String dosage;

    //=========================== POUR REVEIL ===========================

    // Not nul de Spring boot
    @NotNull(message = " heure")

    // Not null de basse de donnée
    @Column(nullable = false)
    private LocalTime reveil;

    //=========================== POUR DESCRIPTION ===========================

    // Not nul de Spring boot
    @NotNull(message = " description")

    @Size(min = 2, message = "champs trop courte")

    // Not null de basse de donnée
    @Column(nullable = false)
    private String descrition;

}
