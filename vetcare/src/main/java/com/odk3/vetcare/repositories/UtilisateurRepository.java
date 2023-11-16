package com.odk3.vetcare.repositories;

import com.odk3.vetcare.models.Utilisateur;
import com.odk3.vetcare.models.Veterinaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Utilisateur findByEmail(String email);
    Utilisateur findByEmailAndMotDePasse(String email, String mot_de_passe);
    Utilisateur findByUtilisateurId(Long id);
}
