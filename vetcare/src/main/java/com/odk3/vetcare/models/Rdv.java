package com.odk3.vetcare.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Rdv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private Long rdvId;




    ////////////////////////// Nom person

    @NotNull(message = "nom de user est vide")
    @Column(nullable = false)
    private String nom;

    ////////////////////////// Nom person

    @NotNull(message = "nom de user est vide")
    @Column(nullable = false)
    private String prenom;


    ////////////////////////// adresse person

    @NotNull(message = "adress de user est vide")
    @Column(nullable = false)
    private String adresse;


    ////////////////////////// numero person

    @NotNull(message = "numero de user")
    @Column(nullable = false)
    private String numero;

    ////////////////////////// date de creation

    @NotNull(message = "Date de creation de rdv")
    @Column(nullable = false)
    private LocalDate date = LocalDate.now();

    ////////////////////////// heur de creation

    @NotNull(message = "Heure de rdv")
    @Column(name = "creation_time")
    private LocalTime creationTime = LocalTime.now();

    ////////////////////////// numero person

    @NotNull(message = "numero de user")
    @Column(nullable = false)
    private String especeAnimal;

    ////////////////////////// numero person

    @NotNull(message = "numero de user")
    @Column(nullable = false)
    private String ageAnimal;

    ////////////////////////// Jour rdv person

    @NotNull(message = "joure de rdv")
    @Column(nullable = false)
    private String jour;

    ////////////////////////// Mois rdv person

    @NotNull(message = "mois de rdv")
    @Column(nullable = false)
    private String mois;

    ////////////////////////// Année rdv person

    @NotNull(message = "Année de rdv")
    @Column(nullable = false)
    private String annee;

    ////////////////////////// sexe rdv person

    @NotNull(message = "Champs de priorite vide")
    @Column(nullable = false)
    private String priorite;

    ////////////////////////// non maladie rdv person

    @NotNull(message = "nom maladie de rdv")
    @Column(nullable = false)
    private String nonMaladie;

    ////////////////////////// description rdv person

    @NotNull(message = "description maladie de rdv")
    @Column(nullable = false)
    private String description;



    ////////////////////////////// LES RELATIONS ENTRES LES TABLES

    @ManyToOne
    @JoinColumn(name = "utilisateurId", nullable = false)
    private Utilisateur utilisateur;


    /*@ManyToOne
    @JoinColumn(name = "veteriniareId", nullable = false)
    private Veterinaire veterinaire;*/

}
