package com.odk3.vetcare.repositories;

import com.odk3.vetcare.models.PlanningVeterinaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanningVeterinaireRepository extends JpaRepository<PlanningVeterinaire, Long> {

    PlanningVeterinaire findByPlanningId(long id);
    PlanningVeterinaire findByPrioriteAndVeterinaireVeteriniareId(int priorite, Long veterinaireId);
}
