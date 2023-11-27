package com.odk3.vetcare.service;

import com.odk3.vetcare.exceptions.DuplicateException;
import com.odk3.vetcare.exceptions.NoContentException;
import com.odk3.vetcare.exceptions.NotFoundException;
import com.odk3.vetcare.models.AjoutAdmin;
import com.odk3.vetcare.models.PlanningVeterinaire;
import com.odk3.vetcare.models.Utilisateur;
import com.odk3.vetcare.repositories.AjoutAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class AjoutAdminService {

    @Autowired
    AjoutAdminRepository ajoutAdminRepository;

    //////////////// Ajouter Nouveau admin
    public AjoutAdmin creeNouveauAdmin(AjoutAdmin ajoutAdmin) {
        if (ajoutAdminRepository.findByEmail(ajoutAdmin.getEmail()) == null) {
            return ajoutAdminRepository.save(ajoutAdmin);
        } else {
            throw new DuplicateException("Cet email existe déjà");
        }
    }

    /////////////////////////////// Pour La connexion  de utilisateur
    public AjoutAdmin connexionNewAdmin(String email, String mot_de_passe) {
        if (ajoutAdminRepository.findByEmailAndMotDePasse(email, mot_de_passe) != null) {
            return ajoutAdminRepository.findByEmailAndMotDePasse(email, mot_de_passe);
        } else
            throw new NotFoundException("New Admin n'existe pas veille créer un compte merci !");
    }


    ///////////////// List des  Nouveau admin
    public List<AjoutAdmin> listNouveauAdmin() {
        return ajoutAdminRepository.findAll();
    }


    ////////////////// Modification de nouveau admin
    public AjoutAdmin modifierNouveauAdminId(Long idAjoutAdmin) {
        if (ajoutAdminRepository.findByAjoutAdminId(idAjoutAdmin) != null) {
            return ajoutAdminRepository.findByAjoutAdminId(idAjoutAdmin);
        } else
            throw new NoContentException("Ajouter nouveau admin");
    }

    ///////// Modifier
    public AjoutAdmin modifierNNewAdmin(AjoutAdmin ajoutAdmin) {
        if (ajoutAdminRepository.findByAjoutAdminId(ajoutAdmin.getAjoutAdminId()) != null) {
            return ajoutAdminRepository.save(ajoutAdmin);
        } else
            return null;
    }

    ////////////////// Suppression de nouveau admin
    public String supprimerNouveauAdmin(@PathVariable Long id) {
        if (ajoutAdminRepository.findByAjoutAdminId(id) != null) {
            ajoutAdminRepository.deleteById(id);
            return "succès";
        } else {
            throw new NotFoundException("cette Nouveau admin existe pas");
        }
    }
}
