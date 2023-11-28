package com.odk3.vetcare.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class SanteAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private Long santeId;

    //=========================== Nom de animal ===========================

    // Not nul de Spring boot
    @NotNull(message = "Nom de l'animal")
    // Not null de basse de donnée
    @Column(nullable = false)
    private String nom;

    //=========================== race de animal ===========================

    // Not nul de Spring boot
    @NotNull(message = "race de l'animal")
    // Not null de basse de donnée
    @Column(nullable = false)
    private String race;

    //=========================== sexe de animal ===========================

    // Not nul de Spring boot
    @NotNull(message = "sexe de l'animal")
    // Not null de basse de donnée
    @Column(nullable = false)
    private String sexe;

    //=========================== age de animal ===========================

    // Not nul de Spring boot
    @NotNull(message = "Age de l'animal")
    // Not null de basse de donnée
    @Column(nullable = false)
    private String age;


    //========================= POUR LE IMAGE =============================


    @NotNull(message = "Champs vide")
    @Column(nullable = false)
    private String photo;

    //=========================== date de vaccin ===========================

    // Not nul de Spring boot
    @NotNull(message = "date de vaccin")
    // Not null de basse de donnée
    @Column(nullable = false)
    private LocalDate dateVaccin;

    //=========================== Nom de vaccin ===========================

    // Not nul de Spring boot
    @NotNull(message = "Nom de vaccin")
    // Not null de basse de donnée
    @Column(nullable = false)
    private String nomVaccin;

    //=========================== age de animal ===========================

    // Not nul de Spring boot
    @NotNull(message = "nom de vete qui a fait le vaccin")
    // Not null de basse de donnée
    @Column(nullable = false)
    private String nomVeteVaccin;

    //=========================== date de traitement ===========================

    // Not nul de Spring boot
    @NotNull(message = "date de traitement")
    // Not null de basse de donnée
    @Column(nullable = false)
    private LocalDate dateTraitement;

    //=========================== Nom de traitement ===========================

    // Not nul de Spring boot
    @NotNull(message = "Nom de traitement")
    // Not null de basse de donnée
    @Column(nullable = false)
    private String nomTraitement;

    //=========================== age de traitement ===========================

    // Not nul de Spring boot
    @NotNull(message = "nom de vete de traitement")
    // Not null de basse de donnée
    @Column(nullable = false)
    private String nomVeteTraitement;

    //=========================== date de Maladie ===========================

    // Not nul de Spring boot
    @NotNull(message = "date de maladie")
    // Not null de basse de donnée
    @Column(nullable = false)
    private LocalDate dateMaladie;

    //=========================== Nom de Maladie ===========================

    // Not nul de Spring boot
    @NotNull(message = "Nom de maladie")
    // Not null de basse de donnée
    @Column(nullable = false)
    private String nomMaladie;

    //=========================== age de Maladie ===========================

    // Not nul de Spring boot
    @NotNull(message = "nom de vete de l'animal maladie")
    // Not null de basse de donnée
    @Column(nullable = false)
    private String nomVeteMaladie;

    //=========================== Nom de Maladie ===========================

    // Not nul de Spring boot
    @NotNull(message = "poid de l'animal")
    // Not null de basse de donnée
    @Column(nullable = false)
    private String poids;

    //=========================== age de Maladie ===========================

    // Not nul de Spring boot
    @NotNull(message = "description")
    // Not null de basse de donnée
    @Column(nullable = false)
    private String description;


    ////////////////////////////// LES RELATIONS ENTRES LES TABLES

    @ManyToOne
    @JoinColumn(name = "utilisateurId", nullable = false)
    private Utilisateur utilisateur;
}
