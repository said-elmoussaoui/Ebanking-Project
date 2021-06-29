package com.bank.ebanking.repo;


import com.bank.ebanking.model.Agence;
import com.bank.ebanking.model.Agent;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AgenceRepository extends JpaRepository<Agence, Long> {



    Optional<Agence> findByNom(String nomAgence);
}
