package com.odk3.vetcare.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class SuiviSante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private Long suiviSanterId;

    //=========================== Nom de animal ===========================

    // Not nul de Spring boot
    @NotNull(message = "Nom de l'animal")
    // Not null de basse de donnée
    @Column(nullable = false)
    private String nom;

    //=========================== age de animal ===========================

    // Not nul de Spring boot
    @NotNull(message = "Age de l'animal")
    // Not null de basse de donnée
    @Column(nullable = false)
    private String age;

    //=========================== categorie de animal ===========================

    // Not nul de Spring boot
    @NotNull(message = "Categorie de l'animal")
    // Not null de basse de donnée
    @Column(nullable = false)
    private String categorie;


    //========================= POUR LE IMAGE =============================


    @NotNull(message = " photo Champs vide")
    @Column(nullable = false)
    private String photo;

    //=========================== sexe de animal ===========================

    /*
    // Not nul de Spring boot
    @NotNull(message = "Sexe de l'animal")
    // Not null de basse de donnée
    @Column(nullable = false)
    private String sexe;
    */

    ////////////////////////// Ordre priorite

    @NotNull(message = "Champs de priorite vide")
    @Column(nullable = false)
    private String priorite;

    //=========================== Descrition de animal ===========================

    // Not nul de Spring boot
    @NotNull(message = "Description de l'animal")
    // Not null de basse de donnée
    @Column(nullable = false)
    private String description;



}
