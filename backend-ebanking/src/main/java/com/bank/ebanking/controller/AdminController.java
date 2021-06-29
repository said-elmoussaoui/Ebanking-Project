package com.bank.ebanking.controller;

import com.bank.ebanking.model.Admin;
import com.bank.ebanking.model.Agence;
import com.bank.ebanking.model.Agent;
import com.bank.ebanking.service.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/admin")
public class AdminController {

    AdminServices service;

    @Autowired
    public AdminController(AdminServices service) {

        this.service = service;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAdmin(@RequestBody Admin admin) {
    	System.out.println("add Post request");
        service.addAdmin(admin);
    }

    @GetMapping("/getall")
    @ResponseStatus(HttpStatus.OK)
    public List<Admin> getAdmins(@RequestParam(name = "id", required = false) Long id) {
    	System.out.println("getAll get request");
        return service.getAdmins(id);

    }

    @GetMapping("/getbyusername/{username}")
    @ResponseStatus(HttpStatus.OK)
    public Admin getAdminByUsername(@PathVariable(name="username") String username){

        return service.getAdminByUsername(username);
    }

    @GetMapping("/getbycin/{cin}")
    @ResponseStatus(HttpStatus.OK)
    public Admin getAdminByCin(@PathVariable(name="cin") String cin) {

        return service.getAdminByCin(cin);
    }

    @PutMapping("/updateAdmin/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateAdmin(@PathVariable(name="id") Long id , @RequestBody(required=false) Admin admin)
    {
        service.updateAdmin(id,admin);
    }

    @PostMapping("/addagent/{nomAgence}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAdmin(@RequestBody Agent agent, @PathVariable(name="nomAgence") String nomAgence  ) {
      service.addAgent(agent, nomAgence);
    }
    @PostMapping("/addagence")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAgence(@RequestBody Agence agence ) {
      service.addAgence(agence);
    }
}
