package com.odk3.vetcare.repositories;

import com.odk3.vetcare.models.SuiviSante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuiviSanteRepository extends JpaRepository<SuiviSante, Long> {

    SuiviSante findBySuiviSanterId(Long id);
}
