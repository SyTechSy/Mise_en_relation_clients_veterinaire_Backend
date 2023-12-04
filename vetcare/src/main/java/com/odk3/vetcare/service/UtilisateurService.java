package com.odk3.vetcare.service;

import com.odk3.vetcare.exceptions.DuplicateException;
import com.odk3.vetcare.exceptions.NoContentException;
import com.odk3.vetcare.exceptions.NotFoundException;
import com.odk3.vetcare.models.SanteAnimal;
import com.odk3.vetcare.models.Utilisateur;
import com.odk3.vetcare.models.Veterinaire;
import com.odk3.vetcare.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class UtilisateurService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    /////////////////////////////// Pour ajouter un Utilisateur
    /*public Utilisateur creerUtilisateur(Utilisateur utilisateur) {
        if (utilisateurRepository.findByEmail(utilisateur.getEmail()) == null) {
            return utilisateurRepository.save(utilisateur);
        } else {
            throw new NoContentException("Cet email existe déjà");
        }
    }*/

    public Utilisateur creerUtilisateur(Utilisateur utilisateur, MultipartFile imageFile) throws Exception {
        if (utilisateurRepository.findByEmail(utilisateur.getEmail()) == null) {

            // Traitement du fichier image
            if (imageFile != null ) {
                String imageLocation = "C:\\xampp\\\\htdocs\\vetCareFile\\images";
                try {
                    Path imageRootLocation = Paths.get(imageLocation);
                    if (!Files.exists(imageRootLocation)) {
                        Files.createDirectories(imageRootLocation);
                    }

                    String imageName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
                    Path imagePath = imageRootLocation.resolve(imageName);
                    Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
                    utilisateur.setPhoto("http://localhost/vetCareFile/images/" + imageName);
                    //"Erreur lors du traitement du fichier image : " + e.getM);
                } catch (IOException e) {
                    throw new Exception("Erreur lors du traitement du fichier image : " + e.getMessage());
                }
            }

            return  utilisateurRepository.save(utilisateur);
        } else {
            throw new DuplicateException("Email " + utilisateur.getEmail() + "existe déjà");
        }
    }




    /////////////////////// SERVISE DE MODICAMENTION PAR ID

    public Utilisateur utilisateurById(long idUser) {
        if (utilisateurRepository.findByUtilisateurId(idUser) != null)
            return utilisateurRepository.findByUtilisateurId(idUser);
        else
            throw new NoContentException("user invalid");
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
            throw new NotFoundException("Utilisateur n'existe pas veille créer un compte merci !");
    }



    /////////////////////////////// Pour voir les list des Utilisateur
    public List<Utilisateur> listUtilisateu() {
        if (!utilisateurRepository.findAll().isEmpty())
            return utilisateurRepository.findAll();
        else
            throw new NoContentException("Aucun Utulisateur n'a été trouver");
    }

    ////////////////////////////// Pour la modification des Utilisateur

    public Utilisateur modifierUtilisateur(Utilisateur utilisateur, MultipartFile imageFile) throws Exception {
        Utilisateur verifUtilisateur = utilisateurRepository.findByUtilisateurId(utilisateur.getUtilisateurId());
        if (verifUtilisateur == null) {
            throw new NotFoundException("Utilisateur non trouver");
        }
        if (imageFile != null) {
            try {
                String emplacementImage = "C:\\xampp\\\\htdocs\\vetCareFile\\images";
                String nomImage = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
                Path cheminImage = Paths.get(emplacementImage).resolve(nomImage);

                Files.copy(imageFile.getInputStream(), cheminImage, StandardCopyOption.REPLACE_EXISTING);
                utilisateur.setPhoto("http://localhost/vetCareFile/images/" + nomImage);
            } catch (NotFoundException ex) {
                throw new NotFoundException("Une erreur s'est produite lors de la mise à jour de l'annonce avec l'ID : ");
            }
        }
        return utilisateurRepository.save(utilisateur);
    }


    /*public Utilisateur modifierUtilisateur(Utilisateur utilisateur) {
        if (utilisateurRepository.findByUtilisateurId(utilisateur.getUtilisateurId()) != null) {
            return utilisateurRepository.save(utilisateur);
        } else {
            return null;
        }
    }*/

    ////////////////////////////// Pour la suppression des Utilisateur

    public String deleteUtilisateur(@PathVariable Long id) {
        if (utilisateurRepository.findByUtilisateurId(id) != null) {
            utilisateurRepository.deleteById(id);
            return "succès";
        } else
            throw new NotFoundException("Utilisateur non trouver");
    }


    public long countUtilisateurs() {
        return utilisateurRepository.count();
    }


}
