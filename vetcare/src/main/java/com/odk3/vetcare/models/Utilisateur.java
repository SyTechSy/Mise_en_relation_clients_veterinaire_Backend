package com.odk3.vetcare.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Utilisateur {

    // Id principale de votre Id User
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
