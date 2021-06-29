package com.bank.ebanking.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public @Data  class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nom;
    String prenom;
    String cin;
    String adresse;
    String telephone;
    String email;
    @Column(unique=true,nullable=false)
    String username;
    @Column(nullable=false)
    String password;
    @Column(nullable=false)
    String role;
}
