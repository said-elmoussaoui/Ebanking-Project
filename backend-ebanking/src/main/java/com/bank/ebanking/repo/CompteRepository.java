package com.bank.ebanking.repo;


import com.bank.ebanking.model.Agent;
import com.bank.ebanking.model.Client;
import com.bank.ebanking.model.Compte;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompteRepository extends JpaRepository<Compte, Long> {

	List<Compte> findByProprietaire(Client client);
	Compte findByNumero(String num);
	List<Compte> findByCreationAgent(Agent agent);

}
