package com.bank.ebanking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bank.ebanking.model.Compte;
import com.bank.ebanking.model.Operation;
import com.bank.ebanking.model.Virement;
import com.bank.ebanking.service.OperationService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/operation")
public class OperationController {
	OperationService operationService;
	@Autowired
	public OperationController(OperationService service) {
		operationService = service;
	}
	@PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody Operation operation) {
		operationService.addOperation(operation);
    }
	@GetMapping("/getAllOper/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Operation> getAllOper(@PathVariable(name="id") long id) {

        return operationService.getAllOper(id);
    }

}
