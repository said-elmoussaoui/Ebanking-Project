package com.bank.ebanking.repo;



import com.bank.ebanking.model.Compte;
import com.bank.ebanking.model.Virement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VirementRepository extends JpaRepository<Virement, Long> {
	List<Virement> findByCreancier(Compte compte);
	List<Virement> findByDebiteur(Compte compte);

}
