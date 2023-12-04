package com.odk3.vetcare.service;

import com.odk3.vetcare.exceptions.DuplicateException;
import com.odk3.vetcare.exceptions.NoContentException;
import com.odk3.vetcare.models.Commentaire;
import com.odk3.vetcare.models.PlanningVeterinaire;
import com.odk3.vetcare.models.SanteAnimal;
import com.odk3.vetcare.repositories.CommentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentaireService {

    @Autowired
    CommentaireRepository commentaireRepository;

    /*public void saveComment(String content) {
        Commentaire comment = new Commentaire();
        comment.setCommentaireId(comment.getCommentaireId());
        commentaireRepository.save(comment);
    }*/

    /*public Commentaire creerCommentaire (Commentaire commentaire) {
        Commentaire comment = new Commentaire();
        comment.setContent(String.valueOf(commentaire));
        commentaireRepository.save(comment);
        return comment;
    }

     */


    public Commentaire creerCommentaire(Commentaire commentaire) {
            return commentaireRepository.save(commentaire);
    }


    /////////// Liste des commentaire

    public List<Commentaire> commentaireList() {
        if (!commentaireRepository.findAll().isEmpty()) {
            return commentaireRepository.findAll();
        } else {
            throw new NoContentException("Aucun Commentaire n'a été trouver");
        }
    }

}