package com.odk3.vetcare.controller;

import com.odk3.vetcare.models.PlanningVeterinaire;
import com.odk3.vetcare.models.Rdv;
import com.odk3.vetcare.service.RdvService;
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
@RequestMapping("/api/rdv")
public class RdvController {

    @Autowired
    RdvService rdvService;


    //////// POUR AJOUTER UN Rdv
    @Operation(summary = "Ajouter un rendez-vous ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rdv ajouter", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Rdv.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409", description = "Rdv existe déjà", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @PostMapping("/ajouter")
    public ResponseEntity<Object> ajouterRdv(@Valid @RequestBody Rdv rdv) {
        Rdv verifRdv = rdvService.creerRdv(rdv);
        if (verifRdv != null) {
            return new ResponseEntity<>(verifRdv, HttpStatus.OK);
        } else
            return new ResponseEntity<>("Cet rdv existe déjà", HttpStatus.NOT_FOUND);
    }


    ////////// CONTROLLER DE MODIFICATION PAR ID

    @GetMapping("/rdv/{id}")
    public ResponseEntity<Object> modifierRdvParId(@PathVariable long id) {
        return new ResponseEntity<>(rdvService.rdvById(id), HttpStatus.OK);
    }


    //////////////////// POUR LISTER LES RDV

    @Operation(summary = "List des Rdv")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rdv list", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Rdv.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "204", description = "List des rdvs vite", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @GetMapping("/list")
    public List<Rdv> rdvList() {
        return rdvService.rdvList();
    }


    ///////////////// POUR MODIFIER UN Rdv

    @Operation(summary = "Modifier un rdv ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rdv modifier", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Rdv.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404", description = "Rdv introuvable", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @PutMapping(value = "/modifier", consumes = {"*/*"})
    public ResponseEntity<Object> modifierRdv(@RequestBody Rdv rdv) {
        Rdv verifrdv = rdvService.modifierRdv(rdv);
        if (verifrdv != null) {
            return new ResponseEntity<>(verifrdv, HttpStatus.OK);
        } else
            return new ResponseEntity<>("cette Rdv n'existe pas donc on peut pas modifier", HttpStatus.NOT_FOUND);
    }

    ////////// POUR LA SUPPRESSION DES PLANNING
    @Operation(summary = "Supprimer un Rdv  ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rdv supprimer", content = {
                    @Content(mediaType = "text/plain", schema = @Schema(implementation = Rdv.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404", description = "cette Rdv n'existe pas", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> suppressionRdv(@PathVariable Long id) {
        String message = rdvService.supprimerRdv(id);
        if (message.equals("succès")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

}
