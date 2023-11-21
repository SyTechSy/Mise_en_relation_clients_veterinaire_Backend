package com.odk3.vetcare.service;

import com.odk3.vetcare.exceptions.DuplicateException;
import com.odk3.vetcare.exceptions.NoContentException;
import com.odk3.vetcare.exceptions.NotFoundException;
import com.odk3.vetcare.models.Veterinaire;
import com.odk3.vetcare.repositories.VeterinaireRepository;
import jakarta.persistence.Id;
import jdk.javadoc.doclet.StandardDoclet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class VeterinaireService {

    @Autowired
    VeterinaireRepository veterinaireRepository;

    /////////////////////////////// Pour ajouter un vétérinaire
    /*public Veterinaire creerVeterinaire(Veterinaire veterinaire) {
        if (veterinaireRepository.findByEmail(veterinaire.getEmail()) == null) {
            return veterinaireRepository.save(veterinaire);
        } else
            throw new DuplicateException("Cet email existe déjà");
    }*/

    ////////////////////////////// ajouyter un veterinaire  avec  Pour Un image de cv

    public Veterinaire creerVeterinaire(Veterinaire veterinaire, MultipartFile imageFile) throws Exception {
        if (veterinaireRepository.findByEmail(veterinaire.getEmail()) == null) {

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
                    veterinaire.setImageCV("http://localhost/vetCareFile/images/" + imageName);
                    //"Erreur lors du traitement du fichier image : " + e.getM);
                } catch (IOException e) {
                    throw new Exception("Erreur lors du traitement du fichier image : " + e.getMessage());
                }
            }

            return veterinaireRepository.save(veterinaire);
        } else {
            throw new DuplicateException("Email " + veterinaire.getEmail() + "existe déjà");
        }
    }

    /////////////////////////////// Pour voir les list des vétérinaire

    public Veterinaire connexionVeterinaire(String email, String mot_de_passe) {
        if (veterinaireRepository.findByEmailAndMotDePasse(email, mot_de_passe) != null) {
            return veterinaireRepository.findByEmailAndMotDePasse(email, mot_de_passe);
        } else
            throw new NotFoundException("Vétérinaire n'existe pas veille céer un compte merci !");
    }

    /////////////////////////////// Pour voir les list des vétérinaire

    public List<Veterinaire> listVeterinaire() {
        if (!veterinaireRepository.findAll().isEmpty())
            return veterinaireRepository.findAll();
        else
            throw new NoContentException("Aucun Vétériniare n'a été trouver");
    }

    ////////////////////////////// Pour la modification des Véterinaire
    public Veterinaire modifierVeterinaire(Veterinaire veterinaire, MultipartFile imageFile) throws Exception {
        Veterinaire verifVeteriniare = veterinaireRepository.findByVeteriniareId(veterinaire.getVeteriniareId());
        if (verifVeteriniare == null) {
            throw new NotFoundException("Vétérinaire non trouver");
        }
        if (imageFile != null) {
            try {
                String emplacementImage = "C:\\xamps\\\\htdocs\\vetCareFile\\images";
                String nomImage = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
                Path cheminImage = Paths.get(emplacementImage).resolve(nomImage);

                Files.copy(imageFile.getInputStream(), cheminImage, StandardCopyOption.REPLACE_EXISTING);
                veterinaire.setImageCV("http://localhost/vetCareFile/images/" + nomImage);
            } catch (NotFoundException ex) {
                throw new NotFoundException("Une erreur s'est produite lors de la mise à jour de l'annonce avec l'ID : ");
            }
        }
        return veterinaireRepository.save(veterinaire);
    }


    ////////////////////////////// Pour la suppression des Véterinaire

    public String deleteVeterinaire(Long id) {
        if (veterinaireRepository.findByVeteriniareId(id) != null) {
            veterinaireRepository.deleteById(id);
            return "succès";
        } else
            throw new NotFoundException("Vétérinaire non trouver");
    }




}
