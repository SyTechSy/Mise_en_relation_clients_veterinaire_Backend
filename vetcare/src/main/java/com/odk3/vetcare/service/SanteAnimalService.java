package com.odk3.vetcare.service;

import com.odk3.vetcare.exceptions.DuplicateException;
import com.odk3.vetcare.exceptions.NoContentException;
import com.odk3.vetcare.exceptions.NotFoundException;
import com.odk3.vetcare.models.SanteAnimal;
import com.odk3.vetcare.models.SuiviMedicament;
import com.odk3.vetcare.repositories.SanteAnimalRepository;
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
public class SanteAnimalService {

    @Autowired
    SanteAnimalRepository santeAnimalRepository;

    public SanteAnimal ajouterSanteAvecImage(SanteAnimal santeAnimal, MultipartFile imageFile) throws Exception {
        if (santeAnimalRepository.findBySanteId(santeAnimal.getSanteId()) == null) {


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
                    santeAnimal.setPhoto("http://localhost/vetCareFileMedi/images/" + imageName);
                    //"Erreur lors du traitement du fichier image : " + e.getM);
                } catch (IOException e) {
                    throw new Exception("Erreur lors du traitement du fichier image : " + e.getMessage());
                }
            }

            return santeAnimalRepository.save(santeAnimal);

        } else {
            throw new DuplicateException("L'id de " + santeAnimal.getNomMaladie() + "existe déjà");
        }
    }





    /////////////////////// SERVISE DE MODICAMENTION PAR ID

    public SanteAnimal santeAnimalById(long idSante) {
        if (santeAnimalRepository.findBySanteId(idSante) != null)
            return santeAnimalRepository.findBySanteId(idSante);
        else
            throw new NoContentException("Sante invalid");
    }

    /////////////////////////////// Pour voir les list des medicament

    public List<SanteAnimal> listSante() {
        if (!santeAnimalRepository.findAll().isEmpty())
            return santeAnimalRepository.findAll();
        else
            throw new NoContentException("Aucun Animal Sante n'a été trouver");
    }

    public SanteAnimal modifierSanteAvecImage(SanteAnimal santeAnimal, MultipartFile imageFile) throws  Exception {
        SanteAnimal verifSanteAnimal = santeAnimalRepository.findBySanteId(santeAnimal.getSanteId());
        if (verifSanteAnimal == null) {
            throw new NotFoundException("Sante animal non trouver");
        }
        if (imageFile != null) {
            try {
                String emplacementImage =  "C:\\xampp\\\\htdocs\\vetCareFileMedi\\images";
                String nomImage = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
                Path cheminImage = Paths.get(emplacementImage).resolve(nomImage);

                Files.copy(imageFile.getInputStream(), cheminImage, StandardCopyOption.REPLACE_EXISTING);
                santeAnimal.setPhoto("http://localhost/vetCareFileMedi/images/" + nomImage);
            } catch (NotFoundException ex) {
                throw  new NotFoundException("Une erreur s'est produite lors de la mise à jour de l'annonce avec l'ID : ");
            }
        }
        return  santeAnimalRepository.save(santeAnimal);
    }

    ////////////////////////////// Pour la suppression des Utilisateur

    public String deleteSante(Long id) {
        if (santeAnimalRepository.findBySanteId(id) != null) {
            santeAnimalRepository.deleteById(id);
            return "succès";
        } else {
            throw new NotFoundException("Sante animal non trouver");
        }
    }

}
