package com.bank.ebanking.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class RendezVous {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @JoinColumn(name="RENDEZVOUS_CLIENT")
    @ManyToOne
    Client client;

    @JoinColumn(name="RENDEZVOUS_AGENT")
    @ManyToOne
    Agent agent;

    LocalDateTime creationDate;

    String forDate;

    String status;

    String title;
    String description;
}
