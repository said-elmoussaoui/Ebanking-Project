import { Component, OnInit } from '@angular/core';
import { Rendezvous } from 'app/interfaces/Rendezvous';
import { AgentServiceService } from 'app/services/agent-service.service';

@Component({
  selector: 'app-mes-rdv',
  templateUrl: './mes-rdv.component.html',
  styleUrls: ['./mes-rdv.component.css']
})
export class MesRDVComponent implements OnInit {
  tempRDV:Rendezvous
  rendezVous:Rendezvous[]
  constructor(private agentService : AgentServiceService) { }

  ngOnInit(): void {
    this.getMesRDV();
  }

  public getMesRDV(){
    this.agentService.getMesRDV().subscribe((response:Rendezvous[])=>{this.rendezVous=response})
  }

  public validate(id:number){
    this.agentService.validateRDV(id).subscribe((response)=>{console.log(response); this.getMesRDV()})
  }
  public rejeter(id:number){
    this.agentService.rejeterRDV(id).subscribe((response)=>{console.log(response); this.getMesRDV()})
  }

  public onOpenModal(rdv:Rendezvous, mode:string): void {
    console.log('clicked');
   const container = document.getElementById('main-container');
   const button = document.createElement('button');
   button.type = 'button';
   button.style.display = 'none';
   button.setAttribute('data-toggle', 'modal');

   if (mode === 'validate') {
     this.tempRDV = rdv;
     button.setAttribute('data-target', '#validateClientModal');
   }
   if (mode === 'rejeter') {
     this.tempRDV = rdv;
     button.setAttribute('data-target', '#rejeterClientModal');
   }
   container.appendChild(button);
   button.click();
 }

}
