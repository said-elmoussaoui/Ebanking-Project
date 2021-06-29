package com.bank.ebanking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bank.ebanking.exception.NotFoundException;
import com.bank.ebanking.model.*;
import com.bank.ebanking.repo.*;

@Service
public class UserService {

	@Autowired
    UserRepository rep;
	@Autowired
	AdminServices  adminService;
	@Autowired
	ClientService clientService;
	@Autowired
	AgentService agentService;
	
    public Utilisateur getUserByUserName(String username,String password){
       return  rep.findByUsernameAndPassword(username,password);
    }

    public List<Utilisateur> getAll(){
    	return rep.findAll();
    }
    
   
    public Utilisateur updateUser(Long id,Utilisateur user){
       Utilisateur updated = rep.findById(id).orElseThrow(()-> new NotFoundException("user with id "+id+" not found"));

       if(user.getNom()!=null && !user.getNom().isEmpty()) updated.setNom(user.getNom());
       if(user.getPrenom()!=null && !user.getPrenom().isEmpty()) updated.setPrenom(user.getPrenom());
       if(user.getCin()!=null && !user.getCin().isEmpty()) updated.setCin(user.getCin());
       if(user.getTelephone()!=null && !user.getTelephone().isEmpty()) updated.setTelephone(user.getTelephone());
       if(user.getAdresse()!=null && !user.getAdresse().isEmpty()) updated.setAdresse(user.getAdresse());
       if(user.getUsername()!=null && !user.getUsername().isEmpty()) updated.setUsername(user.getUsername());
       if(user.getEmail()!=null && !user.getEmail().isEmpty()) updated.setEmail(user.getEmail());
       if(user.getPassword()!=null && !user.getPassword().isEmpty()) updated.setPassword(user.getPassword());

       rep.save(updated);
       return updated;
   }



}
