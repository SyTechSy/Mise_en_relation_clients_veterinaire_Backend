package com.odk3.vetcare.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.odk3.vetcare.models.Utilisateur;
import com.odk3.vetcare.models.Veterinaire;
import com.odk3.vetcare.service.VeterinaireService;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8100")
@RestController
@RequestMapping("/api/veterinaire")
public class VeterinaireController {

    @Autowired
    VeterinaireService veterinaireService;

    ///////////////////////////// Pour ajouter un Vétériniare
    @Operation(summary = "Inserer un Veterinaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Veterinaire inserer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Veterinaire.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409",description = "Veteriniare exist déjà", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PostMapping("/ajouter")
    public ResponseEntity<Object> ajouterVeterinaire(
            @Valid @RequestParam("veterinaire") String veterinaireString,
            @RequestParam(value = "image", required = false) MultipartFile imageFile
            //@RequestParam(value = "image2", required = false) MultipartFile imageFile2
        )

            throws Exception {

        Veterinaire veterinaires ;
        try {
            veterinaires = new JsonMapper().readValue(veterinaireString, Veterinaire.class);
        } catch (JsonProcessingException e ) {
            throw new Exception(e.getMessage());
        }

        Veterinaire savedVeterinaire = veterinaireService.creerVeterinaire(veterinaires, imageFile);
        return new ResponseEntity<>(savedVeterinaire, HttpStatus.CREATED);
    }

    ///////////////////// suiviVete
    @GetMapping("/suiviVete/{id}")
    public ResponseEntity<Object> voirVeterinaireParId(@PathVariable long id) {
        return new ResponseEntity<>(veterinaireService.suiviVeterinaireById(id), HttpStatus.OK);
    }



    @GetMapping("/count")
    public long countUtilisateurs() {
        return veterinaireService.countVeterinaire();
    }




    ////////////////////////////// Pour La connexion des Vétérinaire

    @Operation(summary = "Connexion d'un Veterinaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Veterinaire inserer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Veterinaire.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409",description = "Veteriniare exist déjà", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @GetMapping("/connexion")
    public Veterinaire connexion(@Parameter(description = "email de vétérinaire") @RequestParam("email") String email,
                                 @Parameter(description = "Mot de passe de vétériniare") @RequestParam("mot_de_passe") String mot_de_passe) {
        return veterinaireService.connexionVeterinaire(email, mot_de_passe);

    }


    ////////////////////////////// Pour voir les des Vétérinaire

    @Operation(summary = "Renvoie la liste des Vétérinaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "List renvoyer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Veterinaire.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "204",description = "List des vétérinaire est vide", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @GetMapping("/list")
    public List<Veterinaire> allVeterinaire() {
        return veterinaireService.listVeterinaire();
    }

    ////////////////////////////// Pour la modification des Véterinaire
    @Operation(summary = "Modifier un Vétérinaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Vétérinaire modifier",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Veterinaire.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Vétérinaire n'existe pas", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PutMapping("/modifier")
    public  ResponseEntity<Veterinaire> modifierVeterinaire(
            @Valid @RequestParam("veterinaire") String veterinaireString,
            @RequestParam(value = "image", required = false) MultipartFile imageFile
            //@RequestParam(value = "image2", required = false) MultipartFile imageFile2
        ) throws Exception {
        Veterinaire veterinaireMiseAJour;
        try {
            veterinaireMiseAJour = new  JsonMapper().readValue(veterinaireString, Veterinaire.class);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Veterinaire veterinaireMiseAJourner = veterinaireService.modifierVeterinaire(veterinaireMiseAJour, imageFile);
            return new ResponseEntity<>(veterinaireMiseAJourner, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    ////////////////////////////// Pour la suppression des Véterinaire

    @Operation(summary = "Supprimer un Vétérinaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Veterinaire supprimer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Veterinaire.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Vétériniare introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> deleteVeterinaire(@PathVariable Long id) {
        String messageDelete = veterinaireService.deleteVeterinaire(id);
        if (messageDelete.equals("succès")) {
            return new ResponseEntity<>(messageDelete, HttpStatus.OK);
        } else
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }


}
