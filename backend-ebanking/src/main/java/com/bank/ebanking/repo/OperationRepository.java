package com.bank.ebanking.repo;


import com.bank.ebanking.model.Compte;
import com.bank.ebanking.model.Operation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
	List<Operation> findByCompte(Compte compte);

}
