package com.odk3.vetcare.repositories;

import com.odk3.vetcare.models.PlanningVeterinaire;
import com.odk3.vetcare.models.Rdv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RdvRepository extends JpaRepository<Rdv, Long> {

    Rdv findByRdvId (long id);
    //Rdv findByRdvIdAndUtilisateurUtilisateurIdAndVeterinaireVeteriniareId (Long id, Long veterinaireId, Long utilisateurId);
}
