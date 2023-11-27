package com.odk3.vetcare.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.odk3.vetcare.models.SuiviSante;
import com.odk3.vetcare.models.Utilisateur;
import com.odk3.vetcare.models.Veterinaire;
import com.odk3.vetcare.service.SuiviSanteService;
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

@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/api/suiviSante")
@RestController
public class SuiviSanteController {

    @Autowired
    SuiviSanteService suiviSanteService;

    ///////////////////////////// Pour ajouter un Vétériniare
    @Operation(summary = "Ajouter un Animal ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal ajouter", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Utilisateur.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409", description = "Cette medicament existe déjà existe déjà", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @RequestMapping("/ajouter")
    public ResponseEntity<Object> ajouterSuiviSante(
            @Valid @RequestParam("suiviSante") String suiviSanteString,
            @RequestParam(value = "image", required = false) MultipartFile imageFile
    ) throws  Exception {

        SuiviSante suiviSante;
        try {
            JsonMapper jsonMapper = new JsonMapper();
            jsonMapper.registerModule(new JavaTimeModule());
            suiviSante = jsonMapper.readValue(suiviSanteString, SuiviSante.class);
        } catch (JsonProcessingException e) {
            throw new Exception(e.getMessage());
        }

        SuiviSante savedSuiviSante = suiviSanteService.ajouterAnimalAvecImage(suiviSante, imageFile);
        return new ResponseEntity<>(savedSuiviSante, HttpStatus.CREATED);
    }



    ////////////////////////////// Pour voir les des Vétérinaire

    @Operation(summary = "Renvoie la liste des Animals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "List des animal renvoyer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Veterinaire.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "204",description = "List des Animal est vide", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @GetMapping("/list")
    public List<SuiviSante> allSante() {
        return suiviSanteService.listAnimal();
    }



    ////////////////////////////// Pour le modification de utilisateur

    @Operation(summary = "Modifier un Animal ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal modifier", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Utilisateur.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404", description = "Animal introuvable", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })

    @PutMapping("/modifier")
    public ResponseEntity<SuiviSante> modifierSanteAvecImage(
            @Valid @RequestParam("suiviSante") String suiviSanteString,
            @RequestParam(value = "image", required = false) MultipartFile imageFile
    ) throws Exception {
        SuiviSante suiviSanteMiseAJour;
        try {
            JsonMapper jsonMapper = new JsonMapper();
            jsonMapper.registerModule(new JavaTimeModule());
            suiviSanteMiseAJour = jsonMapper.readValue(suiviSanteString, SuiviSante.class);
        } catch (JsonProcessingException e) {
            throw new Exception(e.getMessage());
        }
        try {
            SuiviSante suiviSanteMiseAJourner = suiviSanteService.modifierAnimalAvecImage(suiviSanteMiseAJour, imageFile);
            return new ResponseEntity<>(suiviSanteMiseAJourner, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    ////////////////////////// SUIvi id

    @GetMapping("/suivi/{id}")
    public ResponseEntity<Object> modifierParId(@PathVariable long id) {
        return new ResponseEntity<>(suiviSanteService.suiviSanteById(id), HttpStatus.OK);
    }


    /////////////////////////////// Pour la suppression d'un utilisateur
    @Operation(summary = "Supprimer un Animal ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal supprimer", content = {
                    @Content(mediaType = "text/plain", schema = @Schema(implementation = Utilisateur.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404", description = "Animal existe pas", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> deleteAnimal(@PathVariable Long id) {
        String messageDelete = suiviSanteService.deleteAnimal(id);
        if (messageDelete.equals("succès")) {
            return new ResponseEntity<>(messageDelete, HttpStatus.OK);
        } else
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }




}
