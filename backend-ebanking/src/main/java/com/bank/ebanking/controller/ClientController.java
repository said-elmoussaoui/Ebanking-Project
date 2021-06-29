package com.bank.ebanking.controller;

import com.bank.ebanking.exception.NotFoundException;
import com.bank.ebanking.model.Agent;
import com.bank.ebanking.model.Client;
import com.bank.ebanking.model.Compte;
import com.bank.ebanking.service.ClientService;
import com.bank.ebanking.service.CompteService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/client")
public class ClientController {

    ClientService clientService;
    CompteService compteService;
    
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

/*
    @PostMapping("/add/{cin}/{nomAgence}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAdmin(@RequestBody Client client, @PathVariable(name="cin") String cin, @PathVariable(name="nomAgence") String nomAgence  ) {
        clientService.addClient(client, nomAgence, cin);
    }
    
    */
    
    @GetMapping("/getall")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getAll(){
        List<Client> clients =  clientService.getAll();
        if(clients.size()>0){
            return clients;
        }else throw new NotFoundException("il n'y pas de client .");
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }
    
    @PutMapping("/updateClient/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Client updateClient(@PathVariable(name="id") Long id,@RequestBody Client client) {
    	return this.clientService.updateclient(id, client);
    }
    
    @GetMapping("/validate/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void validateClien(@PathVariable(name="id") Long id) {
    	this.clientService.validateClient(id);;
    }

    @GetMapping("/rejeter/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void rejeterClient(@PathVariable Long id){
        clientService.rejeterClient(id);
    }

    /*
     * @GetMapping("/validate/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void validateClient(@PathVariable Long id){
        clientService.validateClient(id);
    }

    @GetMapping("/rejeter/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void rejeterClient(@PathVariable Long id){
        clientService.rejeterClient(id);
    }
     */
    

}
