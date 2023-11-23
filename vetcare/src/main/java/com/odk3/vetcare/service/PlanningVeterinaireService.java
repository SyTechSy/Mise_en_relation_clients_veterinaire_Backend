package com.odk3.vetcare.service;

import com.odk3.vetcare.repositories.PlanningVeterinaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanningVeterinaireService {

    @Autowired
    PlanningVeterinaireRepository planningVeterinaireRepository;


}

