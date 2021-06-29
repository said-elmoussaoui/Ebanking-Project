package com.bank.ebanking.repo;


import com.bank.ebanking.model.Operateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperateurRepository extends JpaRepository<Operateur, Long> {


}
