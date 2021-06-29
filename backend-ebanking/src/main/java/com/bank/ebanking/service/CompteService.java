package com.bank.ebanking.service;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bank.ebanking.exception.NotFoundException;
import com.bank.ebanking.model.Agent;
import com.bank.ebanking.model.Client;
import com.bank.ebanking.model.Compte;
import com.bank.ebanking.repo.AgentRepository;
import com.bank.ebanking.repo.ClientRepository;
import com.bank.ebanking.repo.CompteRepository;
import com.itextpdf.text.DocumentException;

@Service
public class CompteService {
	@Autowired
	CompteRepository compteRep;
	@Autowired
	ClientRepository clientRep;
	@Autowired
	AgentService agentService;
	@Autowired
	ClientService clientService;
	@Autowired
	ContratService contratService;
	@Autowired
	AgentRepository agentRepository;
	public Compte getById(long id) {
		return compteRep.findById(id).orElseThrow(()-> new NotFoundException("Compte with id "+id+" not found"));
	}
	public void addCompte(Compte compte,long idClient,long idAgent) throws FileNotFoundException, DocumentException {
		Client client = clientService.getClientById(idClient);
		Agent agent = agentService.getAgentById(idAgent);
		compte.setCreationAgent(agent);
		compte.setProprietaire(client);
		compte.setCreationDate(LocalDateTime.now());
//		contratService.createContrat(compte,client);
		compteRep.save(compte);
		
	}
	public List<Compte> getComptes(long idClient){
		Client client = clientRep.findById(idClient).orElseThrow(()-> new NotFoundException("Client with id "+idClient+" not found"));
		return compteRep.findByProprietaire(client);
	}
	public Compte getByNumero(String generatedString) {
        return compteRep.findByNumero(generatedString);
    }
    
	public Compte addCompte(Compte compte,long idClient) throws FileNotFoundException, DocumentException {
		Client client = clientService.getClientById(idClient);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Agent agent = agentRepository.findByUsername(username);
		Random rand = new Random();
		int rand_int1 = rand.nextInt(1000000000);
		compte.setNumero(Integer.toString(rand_int1));
		compte.setCreationAgent(agent);
		compte.setProprietaire(client);
		compte.setDevise("DH");
		compte.setCreationDate(LocalDateTime.now());
     //	contratService.createContrat(compte,client);
		compteRep.save(compte);
		return compte;
	}
	 public void deleteCompte(long id) {
	    	compteRep.deleteById(id);
	    }
	
}
