package com.bank.ebanking.service;

import com.bank.ebanking.exception.NotFoundException;
import com.bank.ebanking.model.Agence;
import com.bank.ebanking.model.Agent;
import com.bank.ebanking.model.Client;
import com.bank.ebanking.model.Compte;
import com.bank.ebanking.model.Utilisateur;
import com.bank.ebanking.repo.AgenceRepository;
import com.bank.ebanking.repo.AgentRepository;
import com.bank.ebanking.repo.ClientRepository;
import com.bank.ebanking.repo.CompteRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class ClientService {
    @Autowired
    AgentService agentService;
    @Autowired
    AgenceService agenceService;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CompteRepository compteRepository;
    @Autowired
    AgentRepository agentRepository;
    
    
    public Client getClientById(long id) {
    	return clientRepository.findById(id).orElseThrow(()-> new NotFoundException("Client with id "+id+"not found"));
    }
    public Client updateclient(Long id,Client client){
        Client updatedClient = clientRepository.findById(id).orElseThrow(()-> new NotFoundException("client with id "+id+" not found"));

       if(client.getNom()!=null && !client.getNom().isEmpty()) updatedClient.setNom(client.getNom());
       if(client.getPrenom()!=null && !client.getPrenom().isEmpty()) updatedClient.setPrenom(client.getPrenom());
       if(client.getCin()!=null && !client.getCin().isEmpty()) updatedClient.setCin(client.getCin());
       if(client.getTelephone()!=null && !client.getTelephone().isEmpty()) updatedClient.setTelephone(client.getTelephone());
       if(client.getAdresse()!=null && !client.getAdresse().isEmpty()) updatedClient.setAdresse(client.getAdresse());
       if(client.getUsername()!=null && !client.getUsername().isEmpty()) updatedClient.setUsername(client.getUsername());
       if(client.getEmail()!=null && !client.getEmail().isEmpty()) updatedClient.setEmail(client.getEmail());
       if(client.getPassword()!=null && !client.getPassword().isEmpty()) updatedClient.setPassword(client.getPassword());
       if(client.getStatus()!=null && !client.getStatus().isEmpty()) updatedClient.setStatus(client.getStatus());

       clientRepository.save(updatedClient);
       return updatedClient;
   }
    public void addClient(Client client, String cin){
        Agent agent = agentService.getAgentByCin(cin);
        client.setCreationAgent(agent);
        Agence agence = agent.getAgence();
        client.setAgence(agence);
        client.setRole("Client");
        clientRepository.save(client);
    }
    public List<Client> getAll() {
        return clientRepository.findAll();
    }
    
    public void validateClient(Long id){
        Client client = clientRepository.findById(id).orElseThrow(()-> new NotFoundException("Client with id "+id+"not found"));
        client.setStatus("Valide");
        updateclient(id,client);
    }
    public void rejeterClient(Long id){
        Client client = clientRepository.findById(id).orElseThrow(()-> new NotFoundException("Client with id "+id+"not found"));
        client.setStatus("Rejet");
        updateclient(id,client);
    }
    public Client addClient(Client client){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Agent agent = agentRepository.findByUsername(username);
        client.setCreationAgent(agent);
        Agence agence = agent.getAgence();
        client.setAgence(agence);
        client.setRole("Client");
        client.setStatus("En Attente de Validation");
        clientRepository.save(client);
        return client;
    }
     
  


}
