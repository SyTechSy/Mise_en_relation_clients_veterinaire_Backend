package com.odk3.vetcare.service;

import com.odk3.vetcare.repositories.CommentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentaireService {

    @Autowired
    CommentaireRepository commentaireRepository;
}
