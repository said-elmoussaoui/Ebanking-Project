package com.bank.ebanking.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bank.ebanking.model.*;
import com.bank.ebanking.service.AdminServices;
import com.bank.ebanking.service.ClientService;
import com.bank.ebanking.service.CompteService;
import com.itextpdf.text.DocumentException;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/compte")
public class CompteController {
	ClientService clientService;
	CompteService compteService;
	@Autowired
    public CompteController(CompteService service) {

		compteService = service;
		
    }
	@PostMapping("/add/{idClient}")
    @ResponseStatus(HttpStatus.CREATED)
    public Compte add(@RequestBody Compte compte,@PathVariable(name="idClient") Long idClient) throws FileNotFoundException, DocumentException {
		return compteService.addCompte(compte, idClient);
    }
	@GetMapping("/getComptes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Compte> getComptes(@PathVariable(name="id") long id) {

        return compteService.getComptes(id);
    }
	 @DeleteMapping("/deleteCompte/{id}")
	 @ResponseStatus(HttpStatus.OK)
	 public void deleteCompte(@PathVariable(name="id") Long id) {
		   
		   this.compteService.deleteCompte(id);
	   }
	
}
