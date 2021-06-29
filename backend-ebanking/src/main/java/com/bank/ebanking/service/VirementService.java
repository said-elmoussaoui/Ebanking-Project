package com.bank.ebanking.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.ebanking.exception.AlreadyExistsException;
import com.bank.ebanking.exception.NotFoundException;
import com.bank.ebanking.model.Compte;
import com.bank.ebanking.model.Virement;
import com.bank.ebanking.model.VirementAdj;
import com.bank.ebanking.repo.CompteRepository;
import com.bank.ebanking.repo.VirementRepository;

@Service
public class VirementService {
	@Autowired
	VirementRepository virementRepository;
	@Autowired
	CompteRepository compteRep;
	@Autowired
	CompteService compteService;
	public void addVirement(Virement virement,String numcreancier,String numdebiteur) {
		Compte creancier = compteService.getByNumero(numcreancier);
		Compte debiteur = compteService.getByNumero(numdebiteur);
		if(creancier.getSolde()>=virement.getSommeEnv()) {
			virement.setCreancier(creancier);
			virement.setDebiteur(debiteur);
			virement.setDate(LocalDateTime.now());
			virement.setSommeRecu(virement.getSommeEnv());
			virementRepository.save(virement);
			debiteur.setSolde(debiteur.getSolde()+virement.getSommeRecu());
			creancier.setSolde(creancier.getSolde()-virement.getSommeEnv());
			compteRep.save(creancier);
			compteRep.save(debiteur);
		}else {
			 throw new NotFoundException("le solde insuffisant !! ");
		}
	}
	public List<Virement> addVirementMultiple(List<VirementAdj> virements) {
		    List<Virement> virementsEffecu = new ArrayList<Virement>();
			for(VirementAdj virement:virements) {
				Compte debiteur = compteService.getByNumero(virement.getDebiteur());
				if(debiteur == null) throw new NotFoundException("Le compte n'exist pas.");
			}
			for(VirementAdj virement:virements) {
				    Compte creancier = compteService.getByNumero(virement.getCreancier());
				    Compte debiteur = compteService.getByNumero(virement.getDebiteur());
				    if(creancier.getSolde()>=virement.getMontant()) {
						Virement newver = new Virement();
						newver.setSommeEnv(virement.getMontant());
						newver.setSommeRecu(virement.getMontant()-virement.getMontant()*0.02);
						newver.setCreancier(creancier);
						newver.setDebiteur(debiteur);
						newver.setDate(LocalDateTime.now());
						virementRepository.save(newver);
						debiteur.setSolde(debiteur.getSolde()+newver.getSommeRecu());
						creancier.setSolde(creancier.getSolde()-newver.getSommeEnv());
						compteRep.save(creancier);
						compteRep.save(debiteur);
						virementsEffecu.add(newver);
				    }
			 } 
			return virementsEffecu;
	}
	public List<Virement> getAllVirEnv(long idCompte){
		Compte compte = compteService.getById(idCompte);
		return virementRepository.findByCreancier(compte);
	}
	public List<Virement> getAllVirRec(long idCompte){
		Compte compte = compteService.getById(idCompte);
		List<Virement> lalist = virementRepository.findByDebiteur(compte);
		return lalist;
	}
	
}
