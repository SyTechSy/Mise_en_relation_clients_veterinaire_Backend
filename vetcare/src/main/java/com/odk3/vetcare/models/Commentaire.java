package com.odk3.vetcare.models;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private Long commentaireId;



}
