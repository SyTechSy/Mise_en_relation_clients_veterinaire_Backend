package com.odk3.vetcare.SuiviSanter.controllerSante;

import com.odk3.vetcare.SuiviSanter.serviceSante.AjouterNomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AjouterNomController {
    @Autowired
    AjouterNomService ajouterNomService;
}
