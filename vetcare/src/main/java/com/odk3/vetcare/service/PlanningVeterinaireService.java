package com.odk3.vetcare.service;

import com.odk3.vetcare.models.PlanningVeterinaire;
import com.odk3.vetcare.repositories.PlanningVeterinaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class PlanningVeterinaireService {

    @Autowired
    PlanningVeterinaireRepository planningVeterinaireRepository;

    /////////// Creation de planning
    public PlanningVeterinaire creerPlanningVete(PlanningVeterinaire planningVeterinaire) {
        if (planningVeterinaireRepository.findByPrioriteAndVeterinaireVeteriniareId(planningVeterinaire.getPriorite(), planningVeterinaire.getVeterinaire().getVeteriniareId()) == null) {
            return planningVeterinaireRepository.save(planningVeterinaire);

        } else
            return null;
    }

    /////////// Liste des planing

    public List<PlanningVeterinaire> planningVeterinaireList(){
        return planningVeterinaireRepository.findAll();
    }


    //////////////// Modification des plannings
    public PlanningVeterinaire modifierPlannigVete(PlanningVeterinaire planningVeterinaire) {
        if (planningVeterinaireRepository.findByPlanningId(planningVeterinaire.getPlanningId()) != null) {
            return planningVeterinaireRepository.save(planningVeterinaire);
        } else
            return null;
    }

    /////////// Suppression des plannings des veterinaires

    public String supprimerPlanning(@PathVariable Long id) {
        if (planningVeterinaireRepository.findByPlanningId(id) != null) {
            planningVeterinaireRepository.deleteById(id);
            return "Succès";
        } else
            return "cette planning existe pas";
    }

}
