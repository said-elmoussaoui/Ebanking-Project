package com.bank.ebanking.model;

import lombok.Data;

public @Data class VirementAdj {
	
	private String creancier;
	private String debiteur;
	private Double montant; 
	
}
