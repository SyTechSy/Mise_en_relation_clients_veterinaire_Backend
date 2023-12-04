package com.odk3.vetcare.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private Long commentaireId;


    @NotNull(message = "Commentaire de vétérinaire")
    @Column(nullable = false)
    private String content;


    @NotNull(message = "Date de commentaire")
    @Column(nullable = false)
    private LocalDate Date = LocalDate.now();

    @NotNull(message = "Heure de commentaire")
    @Column(name = "creation_time")
    private LocalTime creationTime = LocalTime.now();

    @ManyToOne
    @JoinColumn(name = "utilisateurId", nullable = false)
    private Utilisateur utilisateur;

}
