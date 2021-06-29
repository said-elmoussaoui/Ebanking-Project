package com.bank.ebanking.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="VIREMENT")
public @Data class Virement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_VIREMENT")
	Long id;
	
	@JoinColumn(name="CREANCIER_VIREMENT")
	@ManyToOne 
	Compte creancier; //Le créancier reçoit de l'argent
	
	@JoinColumn(name="DEBITEUR_VIREMENT")
	@ManyToOne
	Compte debiteur; //Le débiteur envoie de l'argent
	
	@Column(name="DATE_VIREMENT")
	LocalDateTime date;
	
	@Column(name="SOMME_ENV_VIREMENT")
	double sommeEnv;
	
	@Column(name="SOMME_RECU_VIREMENT")
	double sommeRecu;


}