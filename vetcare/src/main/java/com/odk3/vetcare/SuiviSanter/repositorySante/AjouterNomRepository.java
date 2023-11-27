package com.odk3.vetcare.SuiviSanter.repositorySante;

import com.odk3.vetcare.SuiviSanter.modelSante.AjouterNom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AjouterNomRepository extends JpaRepository<AjouterNom, Long> {

    AjouterNom findByNomId(Long id);
}
