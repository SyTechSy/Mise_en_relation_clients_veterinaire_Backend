package com.odk3.vetcare.service;

import com.odk3.vetcare.exceptions.DuplicateException;
import com.odk3.vetcare.exceptions.NoContentException;
import com.odk3.vetcare.exceptions.NotFoundException;
import com.odk3.vetcare.models.SuiviMedicament;
import com.odk3.vetcare.models.Utilisateur;
import com.odk3.vetcare.models.Veterinaire;
import com.odk3.vetcare.repositories.SuiviMedicamentRepository;
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
public class SuiviMedicamentService {

    @Autowired
    SuiviMedicamentRepository suiviMedicamentRepository;

    /////////////////////////////// Pour ajouter un Medicament
    /*public SuiviMedicament ajouterMedicament(SuiviMedicament suiviMedicament) {
        if (suiviMedicamentRepository.findBySuiviMedicamentId(suiviMedicament.getSuiviMedicamentId()) == null) {
            return suiviMedicamentRepository.save(suiviMedicament);
        } else
            throw new DuplicateException("Cet medicament existe déjà");
    }*/

    public  SuiviMedicament ajouterMedicamentAvecImage(SuiviMedicament suiviMedicament,  MultipartFile imageFile) throws  Exception {
        if (suiviMedicamentRepository.findBySuiviMedicamentId(suiviMedicament.getSuiviMedicamentId()) == null) {

            // Traitement du fichier imùages pour suivi medicament
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
                    suiviMedicament.setPhoto("http://localhost/vetCareFileMedi/images/" + imageName);
                    //"Erreur lors du traitement du fichier image : " + e.getM);
                } catch (IOException e) {
                    throw new Exception("Erreur lors du traitement du fichier image : " + e.getMessage());
                }
            }

            return suiviMedicamentRepository.save(suiviMedicament);
        } else {
            //throw new DuplicateException("Email " + veterinaire.getEmail() + "existe déjà");
            throw new DuplicateException("L'id de " + suiviMedicament.getNomMedicament() + "existe déjà");
        }
    }



    /////////////////////// SERVISE DE MODICAMENTION PAR ID

    public SuiviMedicament suiviMedicamentById(long idSuivi) {
        if (suiviMedicamentRepository.findBySuiviMedicamentId(idSuivi) != null)
            return suiviMedicamentRepository.findBySuiviMedicamentId(idSuivi);
        else
            throw new NoContentException("suivi invalid");
    }

    /////////////////////////////// Pour voir les list des medicament

    public List<SuiviMedicament> listUtilisateu() {
        if (!suiviMedicamentRepository.findAll().isEmpty())
            return suiviMedicamentRepository.findAll();
        else
            throw new NoContentException("Aucun Medicament n'a été trouver");
    }

    ////////////////////////////// Pour la modification des medicament

    /*public SuiviMedicament modifierMedicament(SuiviMedicament suiviMedicament) {
        if (suiviMedicamentRepository.findBySuiviMedicamentId(suiviMedicament.getSuiviMedicamentId()) != null) {
            suiviMedicamentRepository.save(suiviMedicament);
            return suiviMedicamentRepository.findBySuiviMedicamentId(suiviMedicament.getSuiviMedicamentId());
        }else
            return null;
    }*/

    public SuiviMedicament modifierMedicamentAvecImage(SuiviMedicament suiviMedicament, MultipartFile imageFile) throws  Exception {
        SuiviMedicament verifSuiviMedicament = suiviMedicamentRepository.findBySuiviMedicamentId(suiviMedicament.getSuiviMedicamentId());
        if (verifSuiviMedicament == null) {
            throw new NotFoundException("Suivi medicament non trouver");
        }
        if (imageFile != null) {
            try {
                String emplacementImage =  "C:\\xampp\\\\htdocs\\vetCareFileMedi\\images";
                String nomImage = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
                Path cheminImage = Paths.get(emplacementImage).resolve(nomImage);

                Files.copy(imageFile.getInputStream(), cheminImage, StandardCopyOption.REPLACE_EXISTING);
                suiviMedicament.setPhoto("http://localhost/vetCareFileMedi/images/" + nomImage);
            } catch (NotFoundException ex) {
                throw  new NotFoundException("Une erreur s'est produite lors de la mise à jour de l'annonce avec l'ID : ");
            }
        }
        return  suiviMedicamentRepository.save(suiviMedicament);
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
