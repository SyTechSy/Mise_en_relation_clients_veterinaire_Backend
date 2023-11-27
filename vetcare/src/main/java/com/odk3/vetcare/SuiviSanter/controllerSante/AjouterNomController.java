package com.odk3.vetcare.SuiviSanter.controllerSante;

import com.odk3.vetcare.SuiviSanter.modelSante.AjouterNom;
import com.odk3.vetcare.SuiviSanter.serviceSante.AjouterNomService;
import com.odk3.vetcare.models.AjoutAdmin;
import com.odk3.vetcare.models.PlanningVeterinaire;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ajoutNom")
public class AjouterNomController {
    @Autowired
    AjouterNomService ajouterNomService;


    @Operation(summary = "Ajouter un nom ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "nom ajouter", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AjouterNom.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409", description = "nom existe déjà", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @PostMapping("/ajouter")
    public ResponseEntity<Object> ajouterNewNom(@Valid @RequestBody AjouterNom ajouterNom) {
        AjouterNom verifAjouterNom = ajouterNomService.CreerAjouterNom(ajouterNom);
        if (verifAjouterNom != null) {
            return new ResponseEntity<>(verifAjouterNom, HttpStatus.OK);
        } else
            return new ResponseEntity<>("Cet nom n'existe déjà", HttpStatus.NOT_FOUND);
    }


    ////////// CONTROLLER DE MODIFICATION PAR ID

    @GetMapping("/nom/{id}")
    public ResponseEntity<Object> modifierAjoutNomParId(@PathVariable long id) {
        return new ResponseEntity<>(ajouterNomService.ajouterNomById(id), HttpStatus.OK);
    }

    //////////////////// POUR LISTER LES PLANNINGS

    @Operation(summary = "List des nom")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "nom list", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AjouterNom.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "204", description = "nom vite", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @GetMapping("/list")
    public List<AjouterNom> ajouterNomList() {
        return ajouterNomService.ajouterNomList();
    }

    ///////////////// POUR MODIFIER UN PLANNING

    @Operation(summary = "Modifier un nom ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "nom modifier", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AjouterNom.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404", description = "ajoute nom introuvable", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @PutMapping(value = "/modifier", consumes = {"*/*"})
    public ResponseEntity<Object> modifierAjoutNom(@RequestBody AjouterNom ajouterNom) {
        AjouterNom verifAjouterNom = ajouterNomService.modifierNom(ajouterNom);
        if (verifAjouterNom != null) {
            return new ResponseEntity<>(verifAjouterNom, HttpStatus.OK);
        } else
            return new ResponseEntity<>("Nom n'existe pas donc on peut pas modifier", HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Supprimer un nom  ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "nom supprimer", content = {
                    @Content(mediaType = "text/plain", schema = @Schema(implementation = AjouterNom.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404", description = "cette nom existe pas", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> suppressionPlanningVete(@PathVariable Long id) {
        String message = ajouterNomService.supprimerAjouterNom(id);
        if (message.equals("succès")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }


}
