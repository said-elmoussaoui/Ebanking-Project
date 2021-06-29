package com.bank.ebanking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="AGENCE")
@Data
public class Agence {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID_AGENCE")
    Long id;

    @Column(name="NOM_AGENCE", unique=true)
    String nom;

    @Column(name="ADRESSE_AGENCE")
    String adresse;

    @Column(name="TELEPHONE_AGENCE", unique=true)
    String telephone;

    @Column(name="EMAIL_AGENCE")
    String email;

    @ManyToOne
    @JoinColumn(name="CREATION_ADMIN_AGENCE")
    Admin creationAdmin;

    @JsonIgnore
    @OneToMany(mappedBy="agence",cascade=CascadeType.ALL)
    @Column(name="AGENTS_AGENCE")
    List<Agent> agents;

    @JsonIgnore
    @OneToMany(mappedBy="agence",cascade=CascadeType.ALL)
    @Column(name="CLIENTS_AGENCE")
    List<Client> clients;

}
