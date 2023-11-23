package com.odk3.vetcare.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Veterinaire {

    // Id principale de votre Id User
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // Not nul de base de donnée
    @Column(nullable = false)
        private Long veteriniareId;


    //=========================== POUR NOM ===========================

    // Not nul de Spring boot
    @NotNull(message = "bien")

    @Size(min = 2, message = "champs trop courte")

    // Not null de basse de donnée
    @Column(nullable = false)
    private String nom;

    //=========================== POUR PRENOM ===========================
    @NotNull(message = "Champs vide")
    @Size(min = 2,message = "veuillez saisir un noms correcte")
    // Not Null de base donnée
    @Column(nullable = false)
    private String prenom;

    //========================== POUR EMAIL ============================
    @NotNull(message = "Champs vide")

    @Email(message = "email incorrect")
    // Not Null de base de donnée
    @Column(nullable = false)
    private String email;

    //========================= POUR NUMERO =============================

    @NotNull(message = "Champs vide")
    @Column(nullable = false)
    private String numero;

    //========================= POUR LE SEXE =============================

    @NotNull(message = "Champs vide")
    @Column(nullable = false)
    private String genre;

    //========================= POUR LE LANGUE PARLER =============================


    @NotNull(message = "Champs vide")
    @Column(nullable = false)
    private String langueParler;

    //========================= POUR LE PAYS =============================

    @NotNull(message = "Champs vide")
    @Column(nullable = false)
    private String pays;

    //========================= POUR LE QUARTIER =============================

    @NotNull(message = "Champs vide")
    @Column(nullable = false)
    private String quartier;

    //========================= POUR LE RUE =============================

    @NotNull(message = "Champs vide")
    @Column(nullable = false)
    private int rue;

    //========================= POUR LE CODE COSTAL  =============================

    @NotNull(message = "Champs vide")
    @Column(nullable = false)
    private int codePostal;

    //========================= POUR LE DIPLÔME OU CERTIFICAT  =============================

    @NotNull(message = "Champs vide")
    @Column(nullable = false)
        private String diplomeOuCertificat;


    //========================= POUR LE DIPLÔME OU CERTIFICAT  =============================

    @NotNull(message = "Champs vide")
    @Column(nullable = false)
    private String domaineSpecialisation;

    //========================= POUR LE IMAGE =============================

    /*@NotNull(message = "Champs vide")
    @Column(nullable = false)
    private String dateNaissance;*/

    @NotNull(message = "Champs vide")
    @Column(nullable = false)
    private String jours;

    @NotNull(message = "Champs vide")
    @Column(nullable = false)
    private String mois;

    @NotNull(message = "Champs vide")
    @Column(nullable = false)
    private String annee;


    //========================= POUR LE ANNEE D'EXPERIENCE  =============================

    @NotNull(message = "Champs vide")
    @Column(nullable = false)
    private String anneeExperience;

    //========================= POUR LE IMAGE =============================

/*
    @NotNull(message = "Champs vide")
    @Column(nullable = false)
    private String imageCV;*/


    //========================= POUR LE IMAGE =============================


    @NotNull(message = "Champs vide")
    @Column(nullable = false)
    private String imagePRO;


    //======================= POUR MOT DE PASSE ===============================

    // Not Null de Spring
    @NotNull(message = "Champs vide")

    // size : lenght de notre Mot de passe
    // message : message afficher si le champs de notre Mot de passe est vide
    @Size(min = 6, message = "Saisissez un Mot de pass correct")

    // Not Null de base de donnée
    @Column(nullable = false)
    private String motDePasse;



    ////////////////////// LES RELATIONS ENTRE LES TABLES

    //@OneToMany(mappedBy = "veterinaire", orphanRemoval = true)
    //private List<PlanningVeterinaire> planningVeterinaireList = new ArrayList<>();
}
