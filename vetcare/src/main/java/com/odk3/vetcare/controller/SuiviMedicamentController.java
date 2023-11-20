package com.odk3.vetcare.controller;

import com.odk3.vetcare.models.SuiviMedicament;
import com.odk3.vetcare.models.Utilisateur;
import com.odk3.vetcare.models.Veterinaire;
import com.odk3.vetcare.service.SuiviMedicamentService;
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

import java.util.List;

@CrossOrigin(origins = "http://localhost:8100")
@RestController
@RequestMapping("api/suiviMedicament")
public class SuiviMedicamentController {

    @Autowired
    SuiviMedicamentService suiviMedicamentService;

    ///////////////////////////// Pour ajouter un Vétériniare
    @Operation(summary = "Ajouter un Medicament ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medicament ajouter", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Utilisateur.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409", description = "Cette medicament existe déjà existe déjà", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @RequestMapping("/ajouter")
    public ResponseEntity<Object> ajouterMedicament(@Valid @RequestBody SuiviMedicament suiviMedicament) {
        SuiviMedicament verifMedicament = suiviMedicamentService.ajouterMedicament(suiviMedicament);

        if (verifMedicament != null) {
            return new ResponseEntity<>(verifMedicament, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("existe", HttpStatus.NOT_FOUND);
        }
    }



    ////////////////////////////// Pour voir les des Vétérinaire

    @Operation(summary = "Renvoie la liste des Medeicament")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "List renvoyer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Veterinaire.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "204",description = "List des Medicament est vide", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @GetMapping("/list")
    public List<SuiviMedicament> allMedicament() {
        return suiviMedicamentService.listUtilisateu();
    }



    ////////////////////////////// Pour le modification de utilisateur

    @Operation(summary = "Modifier un Medicament ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medicament modifier", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Utilisateur.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404", description = "introuvable", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @PutMapping("/modifier")
    public ResponseEntity<Object> modifierMedicament(@RequestBody SuiviMedicament suiviMedicament) {
        SuiviMedicament verifMedicament = suiviMedicamentService.modifierMedicament(suiviMedicament);
        if (verifMedicament != null) {
            return new ResponseEntity<>(verifMedicament, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("existe pas", HttpStatus.NOT_FOUND);
        }
    }


    ////////////////////////// SUIvi id

    @GetMapping("/suivi/{id}")
    public ResponseEntity<Object> modifierParId(@PathVariable long id) {
        return new ResponseEntity<>(suiviMedicamentService.suiviMedicamentById(id), HttpStatus.OK);
    }



    /////////////////////////////// Pour la suppression d'un utilisateur
    @Operation(summary = "Supprimer un Medicament ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medicament supprimer", content = {
                    @Content(mediaType = "text/plain", schema = @Schema(implementation = Utilisateur.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404", description = "Medicament existe pas", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> deleteMedicament(@PathVariable Long id) {
        String messageDelete = suiviMedicamentService.deleteMedicament(id);
        if (messageDelete.equals("succès")) {
            return new ResponseEntity<>(messageDelete, HttpStatus.OK);
        } else
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

}