package com.odk3.vetcare.repositories;

import com.odk3.vetcare.models.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {

    Commentaire findByCommentaireId(long id);

}
