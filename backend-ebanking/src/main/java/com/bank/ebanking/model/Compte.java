package com.bank.ebanking.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="COMPTE")
public @Data
class Compte {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID_COMPTE")
    Long id;

    @Column(name="NUMERO_COMPTE", unique=true)
    String numero;

    @Column(name="TYPE_COMPTE")
    String type;

    @Column(name="SOLDE_COMPTE")
    double solde;

    @Column(name="DEVISE_COMPTE")
    String devise;

    @Column(name="CREATION_DATE_COMPTE")
    LocalDateTime creationDate;


    @JoinColumn(name="PROPRIETAIRE_COMPTE")
    @ManyToOne
    Client proprietaire;

    @JoinColumn(name="CREATION_AGENT_COMPTE")
    @ManyToOne
    Agent creationAgent;


    @JsonIgnore
    @Column(name="VIREMENTS_ENVOYES_COMPTE")
    @OneToMany(mappedBy="creancier",cascade=CascadeType.ALL)
    List<Virement> virementsEnvoyes;

    @JsonIgnore
    @Column(name="VIREMENTS_RECUS_COMPTE")
    @OneToMany(mappedBy="debiteur",cascade=CascadeType.ALL)
    List<Virement> virementsRecus;

    @JsonIgnore
    @Column(name="OPERATIONS_COMPTE")
    @OneToMany(mappedBy="compte",cascade=CascadeType.ALL)
    List<Operation> operations;

}
