import { Component, OnInit } from '@angular/core';
import { Agence } from 'app/interfaces/agence';
import { Agent } from 'app/interfaces/agent';
import { User } from 'app/interfaces/utilisateur';
import { AgentServiceService } from 'app/services/agent-service.service';
import { DashboardService } from 'app/services/dashboard.service';
import { sha256 } from 'js-sha256';
import Swal from 'sweetalert2'

declare interface TableData {
    headerRow: string[];
    dataRows: string[][];
}

@Component({
    selector: 'table-cmp',
    moduleId: module.id,
    templateUrl: 'table.component.html'
})

export class TableComponent implements OnInit{
    public tableData1: TableData;
    public tableData2: TableData;
    agents:Array<Agent>=[];
    currentUser:User;
    agences:Array<Agence>=[];
    agent:Agent;
    agentt:Agent;
    nomagence:string;
    newAgence:string;
    constructor(private agentService: AgentServiceService,private dashboardService:DashboardService){

    }
    ngOnInit(){
      var getUser = sessionStorage.getItem("user");
      this.currentUser = JSON.parse(getUser);
      if(this.currentUser.role ==  'Admin'){
        this.agentService.getAll().subscribe((response:Array<Agent>)=>{
          console.log(response);
          this.agents=response;
          },err=>{
            console.log(err)
          });
      }
    }
    selectOption(varr) {
      this.nomagence=varr;
    }
    public getAgents(): void {
        this.agentService.getAll().subscribe((response: Agent[])=>{this.agents=response; console.log(this.agents)});
     }
     getAllAgence(){
        this.agentService.getAllAgences().subscribe((response:Array<Agence>)=>{
          this.agences = response;
        },err=>{
          console.log(err)
        });
     }
     public onOpenModal(agent: Agent, mode: string): void {
        const container = document.getElementById('main-container');
        const button = document.createElement('button');
        button.type = 'button';
        button.style.display = 'none';
        button.setAttribute('data-toggle', 'modal');
  
        if (mode === 'edit') {
          this.getAllAgence();
          this.agent = agent;
          button.setAttribute('data-target', '#updateAgentModal');
        }
        if (mode === 'createAgent') {
          this.getAllAgence();
          this.agent = agent;
          button.setAttribute('data-target', '#createAgentModal');
        }
        if (mode === 'createAgence') {
          button.setAttribute('data-target', '#createAgenceModal');
        }
        container.appendChild(button);
        button.click();
      }

    deleteAgent(id:number,nom:string){
      Swal.fire({
        title: 'Confirmation',
        text: "Voulez-vous supprimer l'agent"+nom,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#50C7C7',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Oui'
      }).then((result) => {
        if (result.isConfirmed) {
           this.agentService.deleteAgent(id).subscribe((res:User)=>{
             console.log(res);
             Swal.fire(
              'Modifié!',
              "L'agent  a été supprimé .",
              'success'
            )
            this.ngOnInit();
           },err=>{
            Swal.fire(
              'Erreur!',
              "Vous ne pouvez pas supprimer cet agent",
              'error'
            )
             console.log(err);
           })
        }
      })
    }
    updateAgenceOfAgent(nomAgence:string){
      console.log(nomAgence,this.agent.id);
      if(nomAgence == undefined) {
        Swal.fire(
          'Erreur!',
          "L'agence n'est pas définit",
          'error'
        )
      }else{
      Swal.fire({
        title: 'Confirmation',
        text: "Voulez-vous modifier l'agence de l'agent"+this.agent.nom,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#50C7C7',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Oui'
      }).then((result) => {
        if (result.isConfirmed) {
          console.log(nomAgence)
           this.agentService.updateAgenceOfAgent(this.agent.id,nomAgence).subscribe((res:Agent)=>{
             console.log(res);
             Swal.fire(
              'Modifié!',
              "L'agent  a été modifié .",
              'success'
            )
            this.ngOnInit();
           },err=>{
            Swal.fire(
              'Erreur!',
              "Vous ne pouvez pas modifier cet agent",
              'error'
            )
             console.log(err);
           })
        }
      })
    }
    }


    onAddAgent(form){ 
     form.value.password = sha256(form.value.password);
     this.dashboardService.addAgent(form.value,this.nomagence).subscribe((response: any)=>{
      Swal.fire(
        'Ajouté!',
        "L'agent  a été ajouté .",
        'success'
      )
      this.ngOnInit();
      },err=>{
        Swal.fire(
          'Erreur!',
          "Une erreur s'est produite",
          'error'
        )
      });
   }
   

   onAddAgence(form){
     this.dashboardService.addAgence(form.value).subscribe((response: any)=>{
      Swal.fire(
        'Ajouté!',
        "L'agence  a été ajouté .",
        'success'
      )
        console.log(response)
      },err=>{
        Swal.fire(
          'Erreur!',
          "L'agence n'est pas définit",
          'error'
        )
      });
   }
  
   


 }
