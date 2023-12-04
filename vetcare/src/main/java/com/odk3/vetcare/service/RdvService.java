package com.odk3.vetcare.service;



import com.odk3.vetcare.exceptions.NoContentException;
import com.odk3.vetcare.exceptions.NotFoundException;
import com.odk3.vetcare.models.Rdv;
import com.odk3.vetcare.models.SanteAnimal;
import com.odk3.vetcare.repositories.RdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class RdvService {

    @Autowired
    RdvRepository rdvRepository;



    /////////// Creation de rdv
    public Rdv creerRdv(Rdv rdv) {
        return rdvRepository.save(rdv);
    }


    //// MODIFICATION PAR ID Rdv

    public Rdv rdvById(long idRdv) {
        if (rdvRepository.findByRdvId(idRdv) != null)
            return rdvRepository.findByRdvId(idRdv);
        else
            throw new NoContentException("Rdv invalid");
    }

    /////////// Liste des Rdv

    /*public List<Rdv> rdvList(){
        return rdvRepository.findAll();
    }

     */

    public List<Rdv> rdvList() {
        if (!rdvRepository.findAll().isEmpty()) {
            return rdvRepository.findAll();
        } else {
            throw new NoContentException("Aucun Rdv n'a été trouver");
        }
    }


    //////////////// Modification des plannings
    public Rdv modifierRdv(Rdv rdv) {
        if (rdvRepository.findByRdvId(rdv.getRdvId()) != null) {
            return rdvRepository.save(rdv);
        } else
            return null;
    }

    /////////// Suppression des plannings des veterinaires

    public String supprimerRdv(@PathVariable Long id) {
        if (rdvRepository.findByRdvId(id) != null) {
            rdvRepository.deleteById(id);
            return "succès";
        } else {
            throw new NotFoundException("Cette rendez-vous n'existe pas");
        }
    }


}
