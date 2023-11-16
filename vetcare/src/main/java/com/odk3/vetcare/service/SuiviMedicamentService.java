package com.odk3.vetcare.service;

import com.odk3.vetcare.exceptions.DuplicateException;
import com.odk3.vetcare.exceptions.NoContentException;
import com.odk3.vetcare.exceptions.NotFoundException;
import com.odk3.vetcare.models.SuiviMedicament;
import com.odk3.vetcare.models.Utilisateur;
import com.odk3.vetcare.repositories.SuiviMedicamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuiviMedicamentService {

    @Autowired
    SuiviMedicamentRepository suiviMedicamentRepository;

    /////////////////////////////// Pour ajouter un Medicament
    public SuiviMedicament ajouterMedicament(SuiviMedicament suiviMedicament) {
        if (suiviMedicamentRepository.findBySuiviMedicamentId(suiviMedicament.getSuiviMedicamentId()) == null) {
            return suiviMedicamentRepository.save(suiviMedicament);
        } else
            throw new DuplicateException("Cet medicament existe déjà");
    }

    /////////////////////////////// Pour voir les list des medicament

    public List<SuiviMedicament> listUtilisateu() {
        if (!suiviMedicamentRepository.findAll().isEmpty())
            return suiviMedicamentRepository.findAll();
        else
            throw new NoContentException("Aucun Utulisateur n'a été trouver");
    }

    ////////////////////////////// Pour la modification des medicament

    public SuiviMedicament modifierMedicament(SuiviMedicament suiviMedicament) {
        if (suiviMedicamentRepository.findBySuiviMedicamentId(suiviMedicament.getSuiviMedicamentId()) != null) {
            suiviMedicamentRepository.save(suiviMedicament);
            return suiviMedicamentRepository.findBySuiviMedicamentId(suiviMedicament.getSuiviMedicamentId());
        }else
            return null;
    }

    ////////////////////////////// Pour la suppression des Utilisateur

    public String deleteMedicament(Long id) {
        if (suiviMedicamentRepository.findBySuiviMedicamentId(id) != null) {
            suiviMedicamentRepository.deleteById(id);
            return "succès";
        } else {
            throw new NotFoundException("Medicament non trouver");
        }
    }
}
