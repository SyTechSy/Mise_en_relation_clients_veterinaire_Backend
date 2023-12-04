package com.odk3.vetcare.controller;

import com.odk3.vetcare.models.Commentaire;
import com.odk3.vetcare.models.SanteAnimal;
import com.odk3.vetcare.service.CommentaireService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8100")
@RestController
@RequestMapping("/api/commentaire")
public class CommentaireController {

    @Autowired
    CommentaireService commentaireService;


    @PostMapping("/add")
    public ResponseEntity<Object> addComment(@Valid @RequestBody Commentaire commentaire) {
        Commentaire verifCommentainer = commentaireService.creerCommentaire(commentaire);
        if (verifCommentainer != null) {
            return new ResponseEntity<>(verifCommentainer, HttpStatus.OK);
        } else
            return new ResponseEntity<>("Cet id dans controller n'existe déjà", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/list")
    public List<Commentaire> commentaireList() {
        return commentaireService.commentaireList();
    }

}
