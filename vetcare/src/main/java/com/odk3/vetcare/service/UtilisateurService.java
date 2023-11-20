package com.odk3.vetcare.service;

import com.odk3.vetcare.exceptions.DuplicateException;
import com.odk3.vetcare.exceptions.NoContentException;
import com.odk3.vetcare.exceptions.NotFoundException;
import com.odk3.vetcare.models.Utilisateur;
import com.odk3.vetcare.models.Veterinaire;
import com.odk3.vetcare.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    /////////////////////////////// Pour ajouter un Utilisateur
    public Utilisateur creerUtilisateur(Utilisateur utilisateur) {
        if (utilisateurRepository.findByEmail(utilisateur.getEmail()) == null) {
            return utilisateurRepository.save(utilisateur);
        } else {
            throw new NoContentException("Cet email existe déjà");
        }
    }

    public Utilisateur userId(Utilisateur utilisateur) {
        if (utilisateurRepository.findByUtilisateurId(utilisateur.getUtilisateurId()) != null) {
            return utilisateurRepository.findByUtilisateurId(utilisateur.getUtilisateurId());
        } else
            throw new RuntimeException("user");
    }


    /////////////////////////////// Pour La connexion  de utilisateur
    public Utilisateur connexionUtilisateur(String email, String mot_de_passe) {
        if (utilisateurRepository.findByEmailAndMotDePasse(email, mot_de_passe) != null) {
            return utilisateurRepository.findByEmailAndMotDePasse(email, mot_de_passe);
        } else
            throw new NotFoundException("Utilisateur n'existe pas veille céer un compte merci !");
    }



    /////////////////////////////// Pour voir les list des Utilisateur
    public List<Utilisateur> listUtilisateu() {
        if (!utilisateurRepository.findAll().isEmpty())
            return utilisateurRepository.findAll();
        else
            throw new NoContentException("Aucun Utulisateur n'a été trouver");
    }

    ////////////////////////////// Pour la modification des Utilisateur

    public Utilisateur modifierUtilisateur(Utilisateur utilisateur) {
        if (utilisateurRepository.findByUtilisateurId(utilisateur.getUtilisateurId()) != null) {
            return utilisateurRepository.save(utilisateur);
        } else {
            return null;
        }
    }

    ////////////////////////////// Pour la suppression des Utilisateur

    public String deleteUtilisateur(Long id) {
        if (utilisateurRepository.findByUtilisateurId(id) != null) {
            utilisateurRepository.deleteById(id);
            return "succès";
        } else
            throw new NotFoundException("Utilisateur non trouver");
    }
}
