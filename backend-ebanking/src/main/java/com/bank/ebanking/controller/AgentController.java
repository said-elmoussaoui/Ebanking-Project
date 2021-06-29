package com.bank.ebanking.controller;
import com.bank.ebanking.model.Agence;
import com.bank.ebanking.model.Agent;
import com.bank.ebanking.model.Client;
import com.bank.ebanking.service.AgentService;
import com.bank.ebanking.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/agent")
public class AgentController {


    AgentService agentService;
    @Autowired
    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    

    @GetMapping("/getall")
    @ResponseStatus(HttpStatus.OK)
    public List<Agent> getAllAgents(){
        return agentService.getAllAgents();
    }

    @PutMapping("/updateAgent/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateAgent(@PathVariable(name="id") Long id , @RequestBody(required=false) Agent agent)
    {
        agentService.updateAgent(id,agent);
    }
    
    @PutMapping("/updateAgenceOfAgent/{id}/{nom}")
    @ResponseStatus(HttpStatus.OK)
    public Agent updateAgenceOfAgent(@PathVariable(name="id") Long idAgent , @PathVariable(name="nom") String nomAgence)
    {
       return  agentService.updateAgenceOfAgent(idAgent, nomAgence);
    }
    
    
   @GetMapping("/clients/{id}")
   @ResponseStatus(HttpStatus.OK)
   public List<Client> getClientsOfAgent(@PathVariable(name="id") Long id){
	   return this.agentService.getClientsByAgent(id);
   }
   @GetMapping("/agence/{id}")
   @ResponseStatus(HttpStatus.OK)
   public Optional<Agence> getAgenceOfAgent(@PathVariable(name="id") Long id) {
	   return this.agentService.getAgenceOfAgent(id);
   }
   @DeleteMapping("/deleteAgent/{id}")
   @ResponseStatus(HttpStatus.OK)
   public void deleteAgent(@PathVariable(name="id") Long id) {
	   
	   this.agentService.deteleAgent(id);
   }
   @DeleteMapping("/deleteClient/{id}")
   @ResponseStatus(HttpStatus.OK)
   public void deleteClient(@PathVariable(name="id") Long id) {
	   
	   this.agentService.deleteClient(id);
   }

}
