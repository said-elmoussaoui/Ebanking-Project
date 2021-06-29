package com.bank.ebanking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.ebanking.model.Compte;
import com.bank.ebanking.model.Operation;
import com.bank.ebanking.repo.CompteRepository;
import com.bank.ebanking.repo.OperationRepository;

@Service
public class OperationService {
	@Autowired
	OperationRepository opeRep;
	@Autowired
	CompteRepository compteRep;
	@Autowired
	CompteService compteService;
	
	public void addOperation(Operation operation) {
		Compte compte = operation.getCompte();
		operation.setDevise("DH");
		operation.setSommeCompte(5);
		if(operation.getType().equals("versement"))
			compte.setSolde(compte.getSolde()+operation.getSommeEspece()-5);
		else if(operation.getType().equals("retrait"))
			compte.setSolde(compte.getSolde()-operation.getSommeEspece()-5);
		compteRep.save(compte);
		operation.setDate(LocalDateTime.now());
		opeRep.save(operation);
	}
	public List<Operation> getAllOper(long idCompte){
		Compte compte = compteService.getById(idCompte);
		return opeRep.findByCompte(compte);
	}

}
