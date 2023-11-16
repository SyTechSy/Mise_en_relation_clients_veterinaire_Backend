package com.odk3.vetcare.repositories;

import com.odk3.vetcare.models.Veterinaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface VeterinaireRepository extends JpaRepository<Veterinaire, Long> {

    Veterinaire findByEmail(String email);

    Veterinaire findByEmailAndMotDePasse(String email, String mot_de_passe);
    Veterinaire findByVeteriniareId(Long id);

}
