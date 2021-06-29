package com.bank.ebanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.bank.ebanking.model.Utilisateur;
import com.bank.ebanking.service.ClientService;
import com.bank.ebanking.service.UserService;
import com.bank.ebanking.exception.NotFoundException;
import com.bank.ebanking.exception.UserNotFoundException;
import com.bank.ebanking.model.Client;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class UserController {
	
	@Autowired
	UserService service;
	@Autowired
	ClientService clientService;
	
	@Autowired
	public UserController(UserService service) {
		
		this.service = service;
	}
	
	
	@GetMapping("/utilisateur/login/{username}/{password}")
	@ResponseStatus(HttpStatus.OK)
	public Utilisateur getByUsernameAndPassword(@PathVariable(name="username") String username,@PathVariable(name="password") String password)
	
	{
		
		Utilisateur user = service.getUserByUserName(username, password);
		if(user.getRole().equals("Client")) {
			Client client = (Client) clientService.getClientById(user.getId());
			if(client.getStatus().equals("En Attente de Validation")) {
				
				throw new UserNotFoundException("Client non validé");
			}
			else 
				return user;
		}else
			return user;
		
	}
	
	@PostMapping("/utilisateur/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Utilisateur updateUser(@PathVariable Long id , @RequestBody(required=false) Utilisateur user)
	
	{
		 return service.updateUser(id, user);
	}
	
	@GetMapping("/utilisateur/getstring")
	@ResponseStatus(HttpStatus.CREATED)
	public String getString() 
	
	{
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}

