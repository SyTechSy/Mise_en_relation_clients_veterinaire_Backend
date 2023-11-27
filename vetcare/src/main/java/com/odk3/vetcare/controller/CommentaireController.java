package com.odk3.vetcare.controller;

import com.odk3.vetcare.service.CommentaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/commentaire")
public class CommentaireController {

    @Autowired
    CommentaireService commentaireService;
}
