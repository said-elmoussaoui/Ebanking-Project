package com.bank.ebanking.controller;


import com.bank.ebanking.model.RendezVous;
import com.bank.ebanking.service.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/rendezVous")
public class RendezVousController {
    @Autowired
    RendezVousService rdvService;


    @PostMapping("/add/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createRDV(@RequestBody RendezVous rendezVous, @PathVariable Long id){
        rdvService.createRV(rendezVous, id);
    }

    @GetMapping("/getRDV/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<RendezVous> getMesRDV(@PathVariable Long id){
    return rdvService.getMesRDV(id);
    }

    @GetMapping("/getRdvAgent")
    @ResponseStatus(HttpStatus.OK)
    public List<RendezVous> getRdvByAgent(){
        return rdvService.getRdvByAgent();
    }

    @PutMapping("/validateRDV/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void validateRDV(@PathVariable Long id){
        rdvService.validateRDV(id);
    }

    @PutMapping("/rejeterRDV/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void rejeterRDV(@PathVariable Long id){
        rdvService.rejeterRDV(id);
    }
}
