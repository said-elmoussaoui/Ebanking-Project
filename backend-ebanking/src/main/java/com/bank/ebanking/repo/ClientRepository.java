package com.bank.ebanking.repo;


import com.bank.ebanking.model.Agent;
import com.bank.ebanking.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	 List<Client> findByCreationAgent(Agent agent);

}
