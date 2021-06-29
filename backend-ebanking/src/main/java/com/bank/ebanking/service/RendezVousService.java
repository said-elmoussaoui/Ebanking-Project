package com.bank.ebanking.service;

import com.bank.ebanking.exception.NotFoundException;
import com.bank.ebanking.model.Agent;
import com.bank.ebanking.model.Client;
import com.bank.ebanking.model.RendezVous;
import com.bank.ebanking.repo.AgentRepository;
import com.bank.ebanking.repo.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RendezVousService {
    @Autowired
    RendezVousRepository rvRepo;
    @Autowired
    ClientService clientService;
    @Autowired
    AgentRepository agRep;

    public void createRV(RendezVous rv, Long id){
        Client client = clientService.getClientById(id);
        rv.setClient(client);
        rv.setAgent(client.getCreationAgent());
        rv.setCreationDate(LocalDateTime.now());
        rv.setStatus("En attente de validation");
        rvRepo.save(rv);
    }

    public List<RendezVous> getMesRDV(Long id){
        Client client = clientService.getClientById(id);
        return rvRepo.findByClient(client);
    }

    public List<RendezVous> getRdvByAgent(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Agent agent = agRep.findByUsername(username);
        return rvRepo.findByAgent(agent);
    }

    public void validateRDV(Long id){
        RendezVous rdv = rvRepo.findById(id).get();
        rdv.setStatus("Valide");
        rvRepo.save(rdv);
    }

    public void rejeterRDV(Long id){
        RendezVous rdv = rvRepo.findById(id).get();
        rdv.setStatus("Rejet, essayez une autre date");
        rvRepo.save(rdv);
    }
}
