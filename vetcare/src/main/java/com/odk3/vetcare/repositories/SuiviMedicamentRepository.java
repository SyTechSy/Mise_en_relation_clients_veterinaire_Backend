package com.odk3.vetcare.repositories;

import com.odk3.vetcare.models.SuiviMedicament;
import com.odk3.vetcare.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuiviMedicamentRepository extends JpaRepository<SuiviMedicament, Long> {

    SuiviMedicament findBySuiviMedicamentId(Long id);
}
