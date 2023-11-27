package com.odk3.vetcare.controller;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.odk3.vetcare.models.PlanningVeterinaire;
import com.odk3.vetcare.service.PlanningVeterinaireService;
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
@RequestMapping("api/planning")
public class PlanningVeterinaireController {

    @Autowired
    PlanningVeterinaireService planningVeterinaireService;

    ////////////////////////////////////////////////////////////


    //////// POUR AJOUTER UN PLANNING
    @Operation(summary = "Ajouter un planning ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Planning ajouter", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PlanningVeterinaire.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409", description = "Planninh existe déjà", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @PostMapping("/ajouter")
    public ResponseEntity<Object> ajouterPlanningVeterinaire(@Valid @RequestBody PlanningVeterinaire planningVeterinaire) {
        PlanningVeterinaire verifPlanningVeterinaire = planningVeterinaireService.creerPlanningVete(planningVeterinaire);
        if (verifPlanningVeterinaire != null) {
            return new ResponseEntity<>(verifPlanningVeterinaire, HttpStatus.OK);
        } else
            return new ResponseEntity<>("Cet plannin n'existe déjà", HttpStatus.NOT_FOUND);
    }


    ////////// CONTROLLER DE MODIFICATION PAR ID

    @GetMapping("/planning/{id}")
    public ResponseEntity<Object> modifierPlanningParId(@PathVariable long id) {
        return new ResponseEntity<>(planningVeterinaireService.planningVeterinaireById(id), HttpStatus.OK);
    }


    //////////////////// POUR LISTER LES PLANNINGS

    @Operation(summary = "List des PLANNING")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Participer list", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PlanningVeterinaire.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "204", description = "List des planning veterinaire vite", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @GetMapping("/list")
    public List<PlanningVeterinaire> ListPanningVeterinaire() {
        return planningVeterinaireService.planningVeterinaireList();
    }


    ///////////////// POUR MODIFIER UN PLANNING

    @Operation(summary = "Modifier un planning ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Participer modifier", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PlanningVeterinaire.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404", description = "Planning vétérinaire introuvable", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @PutMapping(value = "/modifier", consumes = {"*/*"})
    public ResponseEntity<Object> modifierPlanningVete(@RequestBody PlanningVeterinaire planningVeterinaire) {
        PlanningVeterinaire verifPlanningVeterinaire = planningVeterinaireService.modifierPlannigVete(planningVeterinaire);
        if (verifPlanningVeterinaire != null) {
            return new ResponseEntity<>(verifPlanningVeterinaire, HttpStatus.OK);
        } else
            return new ResponseEntity<>("Planning n'existe pas donc on peut pas modifier", HttpStatus.NOT_FOUND);
    }

    ////////// POUR LA SUPPRESSION DES PLANNING
    @Operation(summary = "Supprimer un Planning  ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Participer supprimer", content = {
                    @Content(mediaType = "text/plain", schema = @Schema(implementation = PlanningVeterinaire.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404", description = "cette planning existe pas", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> suppressionPlanningVete(@PathVariable Long id) {
        String message = planningVeterinaireService.supprimerPlanning(id);
        if (message.equals("succès")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

}
