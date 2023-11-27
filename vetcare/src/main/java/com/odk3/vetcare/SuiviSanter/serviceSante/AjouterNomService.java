package com.odk3.vetcare.SuiviSanter.serviceSante;

import com.odk3.vetcare.SuiviSanter.modelSante.AjouterNom;
import com.odk3.vetcare.SuiviSanter.repositorySante.AjouterNomRepository;
import com.odk3.vetcare.exceptions.NoContentException;
import com.odk3.vetcare.exceptions.NotFoundException;
import com.odk3.vetcare.models.AjoutAdmin;
import com.odk3.vetcare.models.PlanningVeterinaire;
import com.odk3.vetcare.models.SuiviMedicament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class AjouterNomService {

    @Autowired
    AjouterNomRepository ajouterNomRepository;

    //////////// Ajouter
    public AjouterNom CreerAjouterNom(AjouterNom ajouterNom) {
        if (ajouterNomRepository.findByNomId(ajouterNom.getNomId()) == null) {
            return ajouterNomRepository.save(ajouterNom);
        } else {
            return null;
        }
    }

    //// MODIFICATION PAR ID PLANNING

    public AjouterNom ajouterNomById(long idAjouterNom) {
        if (ajouterNomRepository.findByNomId(idAjouterNom) != null) {
            return ajouterNomRepository.findByNomId(idAjouterNom);
        }
        else
            throw new NoContentException("planning invalid");
    }



    //////// List
    public List<AjouterNom> ajouterNomList() {
        if (!ajouterNomRepository.findAll().isEmpty())
            return ajouterNomRepository.findAll();
        else
            throw new NoContentException("Aucun nom n'a été trouver");
    }


    ///////// modifier
    public AjouterNom modifierNom(AjouterNom ajouterNom) {
        if (ajouterNomRepository.findByNomId(ajouterNom.getNomId()) != null) {
            return ajouterNomRepository.save(ajouterNom);
        } else {
            return null;
        }
    }


    public String supprimerAnouterNom(@PathVariable Long id) {
        if (ajouterNomRepository.findByNomId(id) != null) {
            ajouterNomRepository.deleteById(id);
            return "succès";
        } else {
            throw new NotFoundException("cette ajout nom existe pas");
        }
    }
}
