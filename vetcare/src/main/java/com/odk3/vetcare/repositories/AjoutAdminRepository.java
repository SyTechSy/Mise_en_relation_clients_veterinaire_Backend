package com.odk3.vetcare.repositories;

import com.odk3.vetcare.models.AjoutAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AjoutAdminRepository extends JpaRepository<AjoutAdmin, Long> {

    AjoutAdmin findByAjoutAdminId(long id);

    AjoutAdmin findByEmail(String email);

    AjoutAdmin findByEmailAndMotDePasse(String email, String mot_de_passe);
}
