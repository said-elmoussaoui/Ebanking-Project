package com.bank.ebanking.service;

import com.bank.ebanking.exception.NotFoundException;
import com.bank.ebanking.model.Agence;
import com.bank.ebanking.repo.AgenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgenceService {

    @Autowired
    AgenceRepository agenceRep;

    public Agence getAgenceByNom(String nomAgence) {
        Agence agence = agenceRep.findByNom(nomAgence).orElseThrow(()-> new NotFoundException("Agence "+nomAgence+"not found"));
        return agence;
    }

    public Agence addAgence(Agence agencee){
        Agence agence = agenceRep.save(agencee);
        return agence;
    }
    public List<Agence> getall(){
    	return agenceRep.findAll();
    }
}
