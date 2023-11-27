package com.odk3.vetcare.service;

import com.odk3.vetcare.exceptions.DuplicateException;
import com.odk3.vetcare.exceptions.NoContentException;
import com.odk3.vetcare.exceptions.NotFoundException;
import com.odk3.vetcare.models.SuiviMedicament;
import com.odk3.vetcare.models.SuiviSante;
import com.odk3.vetcare.repositories.SuiviSanteRepository;
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
public class SuiviSanteService {

    @Autowired
    SuiviSanteRepository suiviSanteRepository;

    /////////////////////////// POUR AJOUTER
    public SuiviSante ajouterAnimalAvecImage(SuiviSante suiviSante, MultipartFile imageFile) throws Exception {
        if (suiviSanteRepository.findBySuiviSanterId(suiviSante.getSuiviSanterId()) == null) {

            if (imageFile != null) {
                String imageLocation = "C:\\xampp\\\\htdocs\\vetCareFileMedi\\images";
                try {
                    Path imageRootLocation = Paths.get(imageLocation);
                    if (!Files.exists((imageRootLocation))) {
                        Files.createDirectories(imageRootLocation);
                    }

                    String imageName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
                    Path imagePath = imageRootLocation.resolve(imageName);
                    Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
                    suiviSante.setPhoto("http://localhost/vetCareFileMedi/images/" + imageName);
                    //"Erreur lors du traitement du fichier image : " + e.getM);
                }   catch (IOException e) {
                        throw new Exception("Erreur lors du traitement du fichier image : " + e.getMessage());
                }
            }

            return  suiviSanteRepository.save(suiviSante);
        } else {
            throw new DuplicateException("L'id de " + suiviSante.getNom() + "existe déjà");
        }
    }

    /////////////////////// SERVISE DE MODICAMENTION PAR ID

    public SuiviSante suiviSanteById(long idSuivi) {
        if (suiviSanteRepository.findBySuiviSanterId(idSuivi) != null) {
            return suiviSanteRepository.findBySuiviSanterId(idSuivi);
        } else
            throw new NoContentException("suivi invalid");
    }

    /////////////////////////////// Pour voir les list des medicament

    public List<SuiviSante> listAnimal() {
        if (!suiviSanteRepository.findAll().isEmpty())
            return suiviSanteRepository.findAll();
        else
            throw new NoContentException("Aucun Animal n'a été trouver");
    }

    ////////////////////////////// Pour la modification des medicament

    public SuiviSante modifierAnimalAvecImage(SuiviSante suiviSante, MultipartFile imageFile) throws  Exception {
        SuiviSante verifSuiviAnimal= suiviSanteRepository.findBySuiviSanterId(suiviSante.getSuiviSanterId());
        if (verifSuiviAnimal == null) {
            throw new NotFoundException("Suivi Animal non trouver");
        }
        if (imageFile != null) {
            try {
                String emplacementImage =  "C:\\xampp\\\\htdocs\\vetCareFileMedi\\images";
                String nomImage = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
                Path cheminImage = Paths.get(emplacementImage).resolve(nomImage);

                Files.copy(imageFile.getInputStream(), cheminImage, StandardCopyOption.REPLACE_EXISTING);
                suiviSante.setPhoto("http://localhost/vetCareFileMedi/images/" + nomImage);
            } catch (NotFoundException ex) {
                throw  new NotFoundException("Une erreur s'est produite lors de la mise à jour de l'annonce avec l'ID : ");
            }
        }
        return  suiviSanteRepository.save(suiviSante);
    }

    ////////////////////////////// Pour la suppression des Utilisateur

    public String deleteAnimal(Long id) {
        if (suiviSanteRepository.findBySuiviSanterId(id) != null) {
            suiviSanteRepository.deleteById(id);
            return "succès";
        } else {
            throw new NotFoundException("Animal non trouver");
        }
    }

}
