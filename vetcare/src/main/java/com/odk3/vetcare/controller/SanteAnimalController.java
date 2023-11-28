package com.odk3.vetcare.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.odk3.vetcare.models.SanteAnimal;
import com.odk3.vetcare.service.SanteAnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/santeAnimal")
public class SanteAnimalController {

    @Autowired
    SanteAnimalService santeAnimalService;

    ///////////////////////////// Pour ajouter un Sante d'un animal
    @Operation(summary = "Ajouter un sante animal ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "nouveau santer animal ajouter", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SanteAnimal.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409", description = "Cette Sante animal existe déjà existe déjà", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @RequestMapping("/ajouter")
    public ResponseEntity<Object> ajouterSanterAnimal(
            @Valid @RequestParam("santeAnimal") String santeAnimalString,
            @RequestParam(value = "image", required = false) MultipartFile imageFile
    ) throws  Exception {

        SanteAnimal santeAnimal;
        try {
            JsonMapper jsonMapper = new JsonMapper();
            jsonMapper.registerModule(new JavaTimeModule());
            santeAnimal = jsonMapper.readValue(santeAnimalString, SanteAnimal.class);
        } catch (JsonProcessingException e) {
            throw new Exception(e.getMessage());
        }

        SanteAnimal savedSanteAnimal = santeAnimalService.ajouterSanteAvecImage(santeAnimal, imageFile);
        return new ResponseEntity<>(savedSanteAnimal, HttpStatus.CREATED);
    }



    ////////////////////////////// Pour voir les des sante d'un animal

    @Operation(summary = "Renvoie la liste des santer d'un animal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "List animal sante renvoyer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = SanteAnimal.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "204",description = "List des Sante animal est vide", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @GetMapping("/list")
    public List<SanteAnimal> allSanteAnimal() {
        return santeAnimalService.listSante();
    }



    ////////////////////////////// Pour le modification de utilisateur

    @Operation(summary = "Modifier un Sante Animal ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sante animal modifier", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SanteAnimal.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404", description = "Santer animal introuvable", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @PutMapping(value = "/modifier", consumes = {"*/*"})
    public ResponseEntity<SanteAnimal> modifierSanteAnimalAvecImage(
            @Valid @RequestParam("santeAnimal") String santeAnimalString,
            @RequestParam(value = "image", required = false) MultipartFile imageFile
    ) throws Exception {
        SanteAnimal santeAnimalMiseAJour;
        try {
            JsonMapper jsonMapper = new JsonMapper();
            jsonMapper.registerModule(new JavaTimeModule());
            santeAnimalMiseAJour = jsonMapper.readValue(santeAnimalString, SanteAnimal.class);
            //suiviMedicamentMiseAJour = new JsonMapper().readValue(suiviMedicamentString, SuiviMedicament.class);
        } catch (JsonProcessingException e) {
            throw new Exception(e.getMessage());
        }
        try {
            SanteAnimal santeAnimalMiseAJourner = santeAnimalService.modifierSanteAvecImage(santeAnimalMiseAJour, imageFile);
            return new ResponseEntity<>(santeAnimalMiseAJourner, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    ////////////////////////// SUIvi id

    @GetMapping("/sante/{id}")
    public ResponseEntity<Object> modifierParId(@PathVariable long id) {
        return new ResponseEntity<>(santeAnimalService.santeAnimalById(id), HttpStatus.OK);
    }


    /////////////////////////////// Pour la suppression d'un utilisateur
    @Operation(summary = "Supprimer un sante animal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal sante supprimer", content = {
                    @Content(mediaType = "text/plain", schema = @Schema(implementation = SanteAnimal.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404", description = "New Animal sante existe pas", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> deleteSanteAnimal(@PathVariable Long id) {
        String messageDelete = santeAnimalService.deleteSante(id);
        if (messageDelete.equals("succès")) {
            return new ResponseEntity<>(messageDelete, HttpStatus.OK);
        } else
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }


}
