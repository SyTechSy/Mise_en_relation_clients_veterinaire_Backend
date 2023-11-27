package com.odk3.vetcare.SuiviSanter.modelSante;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AjouterNom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long nomId;

    @Column(nullable = false)
    private String nom;
}
