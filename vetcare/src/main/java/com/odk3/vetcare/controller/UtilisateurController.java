package com.odk3.vetcare.controller;

import com.odk3.vetcare.models.Utilisateur;
import com.odk3.vetcare.models.Veterinaire;
import com.odk3.vetcare.service.UtilisateurService;
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

@RestController
@RequestMapping("api/utilisateur")
public class UtilisateurController {

    @Autowired
    UtilisateurService utilisateurService;

    ///////////////////////////// Pour ajouter un Vétériniare
    @Operation(summary = "Ajouter un Utilisateur ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur ajouter", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Utilisateur.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409", description = "Qustion existe déjà", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @PostMapping("/ajouter")
    public ResponseEntity<Object> ajouterUtilista(@Valid @RequestBody Utilisateur utilisateur) {

        Utilisateur verifUtilisateur = utilisateurService.creerUtilisateur(utilisateur);
        if (verifUtilisateur != null) {
            return new ResponseEntity<>(verifUtilisateur, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("existe pas", HttpStatus.NOT_FOUND);
        }
    }



    ////////////////////////////// Pour La connexion des Vétérinaire

    @Operation(summary = "Connexion d'un Utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Veterinaire inserer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Veterinaire.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409",description = "Utilisateur exist déjà", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PostMapping("/connexion")
    public Utilisateur connexion(@Parameter(description = "email de l'Utilisateur") @RequestParam("email") String email,
                                 @Parameter(description = "Mot de passe de l'Utilisateur") @RequestParam("mot_de_passe") String mot_de_passe) {
        return utilisateurService.connexionUtilisateur(email, mot_de_passe);
    }



    ////////////////////////////// Pour voir les des Vétérinaire

    @Operation(summary = "Renvoie la liste des utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "List renvoyer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Veterinaire.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "204",description = "List des Utilisateur est vide", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @GetMapping("/list")
    public List<Utilisateur> allUtilisateur() {
        return utilisateurService.listUtilisateu();
    }



    ////////////////////////////// Pour le modification de utilisateur

    @Operation(summary = "Modifier un Utilisateur ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur modifier", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Utilisateur.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404", description = "introuvable", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @PutMapping("/modifier")
    public ResponseEntity<Object> modifierUtilisateur(@RequestBody Utilisateur utilisateur) {
        Utilisateur verifUtilisateur = utilisateurService.modifierUtilisateur(utilisateur);
        if (verifUtilisateur != null) {
            return new ResponseEntity<>(verifUtilisateur, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("existe pas", HttpStatus.NOT_FOUND);
        }
    }

    /////////////////////////////// Pour la suppression d'un utilisateur
    @Operation(summary = "Supprimer un Utilisateur ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utiisateur supprimer", content = {
                    @Content(mediaType = "text/plain", schema = @Schema(implementation = Utilisateur.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404", description = "Utilisateur existe pas", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> deleteVeterinaire(@PathVariable Long id) {
        String messageDelete = utilisateurService.deleteUtilisateur(id);
        if (messageDelete.equals("succès")) {
            return new ResponseEntity<>(messageDelete, HttpStatus.OK);
        } else
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }
}
