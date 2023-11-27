package com.odk3.vetcare.controller;

import com.odk3.vetcare.models.AjoutAdmin;
import com.odk3.vetcare.models.Veterinaire;
import com.odk3.vetcare.service.AjoutAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/api/ajoutAdmin")
public class AjoutAdminController {

    @Autowired
    AjoutAdminService ajoutAdminService;

    @Operation(summary = "Ajouter un new admin ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New ajoute Admin ajouter", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AjoutAdmin.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409", description = "Ajout des new admin existe déjà", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @PostMapping("/ajouter")
    public ResponseEntity<Object> ajouterNewAdmin(@Valid @RequestBody AjoutAdmin ajoutAdmin) {
        AjoutAdmin verifAjoutAdmin = ajoutAdminService.creeNouveauAdmin(ajoutAdmin);
        if (verifAjoutAdmin != null) {
            return new ResponseEntity<>(verifAjoutAdmin, HttpStatus.OK);
        } else
            return new ResponseEntity<>("Cet New admin n'existe déjà", HttpStatus.NOT_FOUND);
    }


    ////////////////////////////// Pour La connexion des Vétérinaire

    @Operation(summary = "Connexion d'un New Admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "New Admin inserer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Veterinaire.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409",description = "New Admin exist déjà", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @GetMapping("/connexion")
    public AjoutAdmin connexion(@Parameter(description = "email de new admin") @RequestParam("email") String email,
                                 @Parameter(description = "Mot de passe de new admin") @RequestParam("mot_de_passe") String mot_de_passe) {
        return ajoutAdminService.connexionNewAdmin(email, mot_de_passe);
    }


    ////////// CONTROLLER DE MODIFICATION  de new admin PAR ID

    @GetMapping("/newAdmin/{id}")
    public ResponseEntity<Object> modifierNewAdminParId(@PathVariable long id) {
        return new ResponseEntity<>(ajoutAdminService.modifierNouveauAdminId(id), HttpStatus.OK);
    }


    //////////////////// POUR LISTER LES New admin qui est ajouter

    @Operation(summary = "List des New admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New admin list", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AjoutAdmin.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "204", description = "List des news admins veterinaire vite", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @GetMapping("/list")
    public List<AjoutAdmin> ajoutAdminList() {
        return ajoutAdminService.listNouveauAdmin();
    }

    @Operation(summary = "Modifier le new admin ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "new admin modifier", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AjoutAdmin.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404", description = "Nouveau Admin  introuvable", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @PutMapping("/modifier")
    public ResponseEntity<Object> modifierNouveauAdmin(@RequestBody AjoutAdmin ajoutAdmin) {
        AjoutAdmin verifAjoutAdmin = ajoutAdminService.modifierNNewAdmin(ajoutAdmin);
        if (verifAjoutAdmin != null) {
            return new ResponseEntity<>(verifAjoutAdmin, HttpStatus.OK);
        } else
            return new ResponseEntity<>("New admin n'existe pas donc on peut pas modifier", HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Supprimer un new admin  ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New admin supprimer", content = {
                    @Content(mediaType = "text/plain", schema = @Schema(implementation = AjoutAdmin.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404", description = "cette planning existe pas", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> suppressionNewAdmin(@PathVariable Long id) {
        String message = ajoutAdminService.supprimerNouveauAdmin(id);
        if (message.equals("succès")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }


}
