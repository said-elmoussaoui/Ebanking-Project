import { Component,OnInit } from '@angular/core';
import { Agence } from 'app/interfaces/agence';
import { Client } from 'app/interfaces/client';
import { Compte } from 'app/interfaces/compte';
import { User } from 'app/interfaces/utilisateur';
import { AgentServiceService } from 'app/services/agent-service.service';
import { Agent } from 'app/interfaces/agent';
import { sha256 } from 'js-sha256';
import Swal from 'sweetalert2'
import { Router } from '@angular/router';

@Component({
    selector: 'typography-cmp',
    moduleId: module.id,
    templateUrl: 'typography.component.html'
})

export class TypographyComponent implements OnInit{

    soldee: number;
    selectedType: string;
    client : Client;
    compte : Compte;
    selected: string;
    agent:Agent;
    nomagence:string;
    currentUser:User;
    agences:Array<Agence>=[];
    constructor(private agentService : AgentServiceService, private router:Router){

    }
    ngOnInit(){
        var getUser = sessionStorage.getItem("user");
        this.currentUser = JSON.parse(getUser);
        this.getAllAgence();
    }
      selectOption1(varr) {
       this.selectedType=varr;
      }
    
    public addClient(varr){
        this.client=varr.value;
        this.compte=varr.value;
        this.client.password = sha256(this.client.password);
        this.compte.type=this.selectedType;
        this.agentService.addClient(this.client).subscribe((response:Client)=>{
            this.client=response;
            this.agentService.addCompte(this.compte,this.client.id).subscribe((response)=>{
                Swal.fire(
                    'Modifié!',
                    "Le client a été crée .",
                    'success'
                  )
                console.log(response)
            },err=>{
                Swal.fire(
                    'Erreur',
                    "Le compte n'a pas été créé.",
                    'error'
                  )
                  this.router.navigate(['/dashboard'])
            });
        },err=>{
            Swal.fire(
                'Erreur',
                "Le client n'a pas été créé.",
                'error'
              )
        });

        
    }
    getAllAgence(){
        this.agentService.getAllAgences().subscribe((response:Array<Agence>)=>{
          this.agences = response;
          console.log("esponse"+response);
        },err=>{
          console.log(err)
        });
     }
    
}
