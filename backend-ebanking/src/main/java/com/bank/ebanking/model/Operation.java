package com.bank.ebanking.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="OPERATION")
public @Data class Operation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_OPERATION")
	Long id;
	
	@JoinColumn(name="COMPTE_OPERATION")
	@ManyToOne 
	Compte compte;

	
	@Column(name="DATE_OPERATION")
	LocalDateTime date;
	
	@Column(name="SOMME_ESPECE_OPERATION")
	double sommeEspece;
	
	@Column(name="SOMME_COMPTE_OPERATION")
	double sommeCompte;
	
	@Column(name="TYPE_OPERATION")
	String type; // (versement ou retrait  )
	
	@Column(name="DEVISE_OPERATION")
	String devise;
	

}
