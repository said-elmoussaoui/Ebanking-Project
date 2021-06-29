package com.bank.ebanking.service;

import com.bank.ebanking.exception.NotFoundException;
import com.bank.ebanking.model.Admin;
import com.bank.ebanking.model.Agence;
import com.bank.ebanking.model.Agent;
import com.bank.ebanking.model.Utilisateur;
import com.bank.ebanking.repo.AdminRepository;
import com.bank.ebanking.repo.AgenceRepository;

import com.bank.ebanking.repo.AgentRepository;
import com.bank.ebanking.repo.ClientRepository;
import com.bank.ebanking.repo.CompteRepository;
import com.bank.ebanking.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.bank.ebanking.model.Client;
import com.bank.ebanking.model.Compte;
@Service
public class AgentService {
    @Autowired
    AgentRepository agRep;
    @Autowired
    AgenceRepository agenceRep;
    @Autowired
    AdminRepository adminRep;
    @Autowired
    AdminServices adminService;
    @Autowired
    AgenceService agenceService;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CompteRepository compteRepo;


  
    public Agent getAgentByCin(String cin){
        return agRep.findByCin(cin).orElseThrow(()-> new NotFoundException("agent with cin "+cin+" not found"));
    }
    public Agent getAgentById(Long id){
        return agRep.findById(id).orElseThrow(()-> new NotFoundException("agent with id "+id+" not found"));
    }

    public List<Agent> getAllAgents(){
        return agRep.findAll();
    }

    public void updateAgent(Long id, Agent agent) {
        Agent updatedAgent = agRep.findById(id).orElseThrow(()-> new NotFoundException("Agent with id "+id+" not found"));

        if(agent.getNom()!=null && !agent.getNom().isEmpty()) updatedAgent.setNom(agent.getNom());
        if(agent.getPrenom()!=null && !agent.getPrenom().isEmpty()) updatedAgent.setPrenom(agent.getPrenom());
        if(agent.getCin()!=null && !agent.getCin().isEmpty()) updatedAgent.setCin(agent.getCin());
        if(agent.getTelephone()!=null && !agent.getTelephone().isEmpty()) updatedAgent.setTelephone(agent.getTelephone());
        if(agent.getAdresse()!=null && !agent.getAdresse().isEmpty()) updatedAgent.setAdresse(agent.getAdresse());
        if(agent.getUsername()!=null && !agent.getUsername().isEmpty()) updatedAgent.setUsername(agent.getUsername());
        if(agent.getEmail()!=null && !agent.getEmail().isEmpty()) updatedAgent.setEmail(agent.getEmail());
        if(agent.getPassword()!=null && !agent.getPassword().isEmpty()) updatedAgent.setPassword(agent.getPassword());
       // if(agent.getCreationAdmin()!=null ) updatedAgent.setCreationAdmin(agent.getCreationAdmin());
       // if(agent.getAgence()!=null) updatedAgent.setAgence(agent.getAgence());

        agRep.save(updatedAgent);
    }
    public Agent updateAgenceOfAgent(Long idAgent,String nomAgence) {
    	Agent agent = agRep.findById(idAgent).orElseThrow(()-> new NotFoundException("Agent with id "+idAgent+" not found"));
    	agent.setAgence(agenceRep.findByNom(nomAgence).orElseThrow());
    	agRep.save(agent);
    	return agent;
    }
    public Agent getAgentById(long id) {
    	return agRep.findById(id).orElseThrow(()-> new NotFoundException("agent with id "+id+" not found"));
    }
    public List<Client> getClientsByAgent(Long id) {
    	Agent agent = this.getAgentById(id);
    	return this.clientRepository.findByCreationAgent(agent);
    }
    public Optional<Agence> getAgenceOfAgent(Long id) {
    	Agent agent = this.getAgentById(id);
    	return this.agenceRep.findById(agent.getAgence().getId());
    	
    }
    public void deteleAgent(long id) {
    	
    	Agence agence = agenceRep.findById(agRep.findById(id).get().getAgence().getId()).orElseThrow();
    	/*
    	int index = agence.getAgents().indexOf(agRep.findById(id).orElseThrow());
    	System.out.println(index);
    	System.out.println("before"+agence.getAgents().size());
    	agence.getAgents().remove(index);
    	System.out.println("affter"+agence.getAgents().size());
    	agenceRep.save(agence);
    	List<Client> clients = clientRepository.findByCreationAgent(agRep.findById(id).orElseThrow());
    	Agent agent = agence.getAgents().get(0);
    	for(Client client : clients) {
    		client.setCreationAgent(agent);
    	}
    	List<Compte> comptes = compteRepo.findByCreationAgent(agRep.findById(id).orElseThrow());
    	for(Compte compte : comptes) {
    		compte.setCreationAgent(agent);
    	}
    	compteRepo.saveAll(comptes);
    	clientRepository.saveAll(clients);
    	*/
    	
    	agRep.deleteById(id);
    }
    public void deleteClient(long id) {
    	clientRepository.deleteById(id);
    }
}
