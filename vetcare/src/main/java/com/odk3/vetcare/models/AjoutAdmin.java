package com.odk3.vetcare.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class AjoutAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private Long ajoutAdminId;


    ////////////////////////// Nom admin

    @NotNull(message = "nom de nouveau admin est vide")
    @Column(nullable = false)
    private String nom;


    ////////////////////////// Nom admin

    @NotNull(message = "prenom de nouveau admin est vide")
    @Column(nullable = false)
    private String prenom;


    ////////////////////////// email admin

    @NotNull(message = "email de nouveau admin est vide")
    @Email(message = "email incorrect")
    @Column(nullable = false)
    private String email;


    ////////////////////////// prenom admin

    @NotNull(message = "prenom de nouveau admin est vide")
    @Column(nullable = false)
    private String motDePasse;
}
