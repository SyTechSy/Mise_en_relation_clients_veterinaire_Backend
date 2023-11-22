package com.odk3.vetcare.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Utilisateur {

    // Id principale de votre Id User
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // Not nul de base de donnée
    @Column(nullable = false)
    private Long utilisateurId;


    //=========================== POUR NOM ===========================

    // Not nul de Spring boot
    @NotNull(message = "bien")

    @Size(min = 2, message = "champs NOM trop courte")

    // Not null de basse de donnée
    @Column(nullable = false)
    private String nom;

    //=========================== POUR PRENOM ===========================
    @NotNull(message = "Champs vide")
    @Size(min = 2,message = "veuillez saisir un prenom correcte")
    // Not Null de base donnée
    @Column(nullable = false)
    private String prenom;

    //=========================== POUR PRENOM ===========================
    @NotNull(message = "Champs vide")
    @Size(min = 2,message = "veuillez saisir un numero correcte")
    // Not Null de base donnée
    @Column(nullable = false)
    private String numero;

    //=========================== POUR PRENOM ===========================
    @NotNull(message = "Champs vide")
    @Size(min = 2,message = "veuillez saisir un genre correcte")
    // Not Null de base donnée
    @Column(nullable = false)
    private String genre;

    //=========================== POUR PRENOM ===========================
    @NotNull(message = "Champs vide")
    @Size(min = 2,message = "veuillez saisir un dateNaissance correcte")
    // Not Null de base donnée
    @Column(nullable = false)
    private String dateNaissance;


    //========================= POUR LE IMAGE =============================


    @NotNull(message = "Champs vide")
    @Column(nullable = false)
    private String photo;

    //=========================== POUR PRENOM ===========================
    @NotNull(message = "Champs vide")
    @Size(min = 2,message = "veuillez saisir un quartier correcte")
    // Not Null de base donnée
    @Column(nullable = false)
    private String quartier;

    //=========================== POUR PRENOM ===========================
    @NotNull(message = "Champs vide")
    @Size(min = 2,message = "veuillez saisir un description correcte")
    // Not Null de base donnée
    @Column(nullable = false)
    private String description;


    //======================= POUR MOT DE PASSE ===============================

    // Not Null de Spring
    @NotNull(message = "Champs vide")

    // size : lenght de notre Mot de passe
    // message : message afficher si le champs de notre Mot de passe est vide
    @Size(min = 6, message = "Saisissez un Mot de pass correct")

    // Not Null de base de donnée
    @Column(nullable = false)
    private String motDePasse;

    //========================== POUR EMAIL ============================
    @NotNull(message = "Champs vide")

    @Email(message = "email incorrect")
    // Not Null de base de donnée
    @Column(nullable = false)
    private String email;

}
