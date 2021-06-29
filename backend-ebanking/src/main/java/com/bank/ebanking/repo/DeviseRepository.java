package com.bank.ebanking.repo;


import com.bank.ebanking.model.Devise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviseRepository extends JpaRepository<Devise, Long> {

	
	
	
}
