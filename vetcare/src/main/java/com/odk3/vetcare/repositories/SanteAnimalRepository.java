package com.odk3.vetcare.repositories;

import com.odk3.vetcare.models.SanteAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanteAnimalRepository extends JpaRepository<SanteAnimal, Long> {
    SanteAnimal findBySanteId(Long id);
}
